package engine;

import config.NpcLoader;
import java.util.List;
import model.Npc;
import model.Maze;
import model.Player;

/** Minimal combat and riddle rules, independent of terminal input/output. */
public class GameEngine {
    public static final String HERB = Player.HERB;
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



    /** @return unresolved NPC on the current tile, or null */
    private Npc currentNpc() {
        return npcs.stream().filter(n -> !n.resolved() && n.position().equals(player.position()))
                .findFirst().orElse(null);
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

}
