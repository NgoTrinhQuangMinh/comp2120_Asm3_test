package engine;

import command.Command;
import config.NpcLoader;
import java.util.List;
import model.Npc;
import model.Maze;
import model.Player;
import model.Position;

/** Minimal combat and riddle rules, independent of terminal input/output. */
public class GameEngine {
    public static final String KEY = Player.KEY;
    public static final String HERB = Player.HERB;
    public static final String HELP = "Move: w/a/s/d or forward/left/backward/right\n"
            + "Forward is up the map; backward is down.\n"
            + "On N: fight (f), or talk (t) then answer <your answer>.\n"
            + "Drops enter your inventory automatically. Use herb to heal; use weapon to equip.\n"
            + "Other commands: inventory (i), look, help, quit (q).";
    private final Maze maze;
    private final Player player;
    private final List<Npc> npcs;
    private boolean won;
    private boolean quit;

    /** Creates a fresh game from the map and NPC configuration.
     * @param maze maze to play
     */
    public GameEngine(Maze maze) {
        this.maze = maze;
        player = new Player(maze.find('P'));
        npcs = NpcLoader.loadDefault(maze);
    }

    /** @return player state */
    public Player player() { return player; }
    /** @return whether the player escaped */
    public boolean won() { return won; }
    /** @return whether play has ended */
    public boolean finished() { return won || quit || player.health() == 0; }

    /** Executes a player command.
     * @param input raw command
     * @return player feedback
     */
    public String execute(String input) {
        if (finished()) { return "The game has ended."; }
        String[] parts = input == null ? new String[0] : input.trim().split("\\s+", 2);
        String argument = parts.length == 2 ? parts[1].trim() : "";
        return switch (Command.parse(input)) {
            case LEFT -> move(-1, 0);
            case RIGHT -> move(1, 0);
            case FORWARD -> move(0, -1);
            case BACKWARD -> move(0, 1);
            case FIGHT -> fight();
            case TALK -> talk();
            case ANSWER -> answer(argument);
            case USE -> player.use(argument);
            case INVENTORY -> "Inventory: " + (player.inventory().isEmpty() ? "empty" : String.join(", ", player.inventory()));
            case HELP -> HELP;
            case LOOK -> "Obtain the key from an NPC and reach the exit (X).";
            case QUIT -> { quit = true; yield "Goodbye."; }
            case UNKNOWN -> "Unknown command. Type help for controls.";
        };
    }

    /** Moves through walkable cells, checking the exit key.
     * @param dx horizontal offset
     * @param dy vertical offset
     * @return movement feedback
     */
    private String move(int dx, int dy) {
        Position next = player.position().move(dx, dy);
        if (maze.isWall(next)) { return "A wall blocks your way."; }
        if (maze.at(next) == 'X' && !player.has(KEY)) { return "The exit is locked. An NPC holds its key."; }
        player.moveTo(next);
        if (maze.at(next) == 'X') { won = true; return "You unlock the exit and escape the maze. You win!"; }
        Npc npc = currentNpc();
        if (npc != null) {
            return "NPC: Health " + npc.health() + ", Attack " + npc.attack() + ". Choose fight or talk, or move away.";
        }
        return "You move through the maze.";
    }

    /** @return unresolved NPC on the current tile, or null */
    private Npc currentNpc() {
        return npcs.stream().filter(n -> !n.resolved() && n.position().equals(player.position()))
                .findFirst().orElse(null);
    }

    /** Performs one exchange of attacks using both participants' stats.
     * @return combat result
     */
    private String fight() {
        Npc npc = currentNpc();
        if (npc == null) { return "There is no NPC here to fight."; }
        npc.hit(player.attack());
        if (npc.resolved()) { return "You defeat the NPC. " + awardDrops(npc); }
        player.damage(npc.attack());
        if (player.health() == 0) { return "You have fallen. Game over."; }
        return "You deal " + player.attack() + " damage. NPC has " + npc.health()
                + " health and hits you for " + npc.attack() + ".";
    }

    /** Offers the current NPC's riddle.
     * @return dialogue
     */
    private String talk() {
        Npc npc = currentNpc();
        if (npc == null) { return "There is no NPC here to talk to."; }
        return "NPC: " + npc.offerRiddle() + "\nType answer <your answer>.";
    }

    /** Checks an answer only against the NPC at the current tile.
     * @param attempt player answer
     * @return riddle outcome
     */
    private String answer(String attempt) {
        Npc npc = currentNpc();
        if (npc == null) { return "There is no NPC here to answer."; }
        if (!npc.riddleOffered()) { return "Talk to the NPC to hear its riddle first."; }
        if (attempt.isBlank()) { return "Type answer <your answer>."; }
        if (!npc.accepts(attempt)) { return "NPC: Incorrect. Try again, or choose to fight."; }
        npc.resolve();
        return "NPC: Correct! " + awardDrops(npc);
    }

    /** Adds the resolved encounter's rewards to inventory.
     * @param npc resolved NPC
     * @return reward description
     */
    private String awardDrops(Npc npc) {
        npc.drops().forEach(player::collect);
        return "Drops collected: " + String.join(", ", npc.drops()) + ".";
    }

    /** Draws the level and current stats.
     * @return terminal-ready map
     */
    public String render() {
        StringBuilder output = new StringBuilder();
        for (int y = 0; y < maze.height(); y++) {
            for (int x = 0; x < maze.width(); x++) {
                Position position = new Position(x, y);
                char symbol = maze.at(position);
                if (symbol == 'P' || Character.isDigit(symbol)) { symbol = '.'; }
                for (Npc npc : npcs) {
                    if (!npc.resolved() && npc.position().equals(position)) { symbol = 'N'; }
                }
                if (player.position().equals(position)) { symbol = '@'; }
                output.append(symbol);
            }
            output.append('\n');
        }
        return output + "@ You  - | Wall  N NPC  X Exit\nHealth: " + player.health()
                + "/" + Player.MAX_HEALTH + " | Attack: " + player.attack() + " | Key: " + (player.has(KEY) ? "yes" : "no");
    }
}
