package engine;

import java.util.List;
import model.Npc;
import model.Maze;
import model.Player;

/** Minimal combat and riddle rules, independent of terminal input/output. */
public class GameEngine implements Game {
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
        // TODO: Create the player and load NPCs from configuration.
        this.player = null;
        this.npcs = List.of();
    }

    /** @return player state */
    public Player player() {
        // TODO: Implement player according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: player");
    }
    /** @return whether the player escaped */
    public boolean won() {
        // TODO: Implement won according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: won");
    }
    /** @return whether play has ended */
    public boolean finished() {
        // TODO: Implement finished according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: finished");
    }

    /** Executes a player command.
     * @param input raw command
     * @return player feedback
     */
    public String execute(String input) {
        // TODO: Implement execute according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: execute");
    }

    /** Moves through walkable cells, checking the exit key.
     * @param dx horizontal offset
     * @param dy vertical offset
     * @return movement feedback
     */
    private String move(int dx, int dy) {
        // TODO: Implement move according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: move");
    }

    /** @return unresolved NPC on the current tile, or null */
    private Npc currentNpc() {
        // TODO: Implement currentNpc according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: currentNpc");
    }

    /** Performs one exchange of attacks using both participants' stats.
     * @return combat result
     */
    private String fight() {
        // TODO: Implement fight according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: fight");
    }

    /** Offers the current NPC's riddle.
     * @return dialogue
     */
    private String talk() {
        // TODO: Implement talk according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: talk");
    }

    /** Checks an answer only against the NPC at the current tile.
     * @param attempt player answer
     * @return riddle outcome
     */
    private String answer(String attempt) {
        // TODO: Implement answer according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: answer");
    }

    /** Adds the resolved encounter's rewards to inventory.
     * @param npc resolved NPC
     * @return reward description
     */
    private String awardDrops(Npc npc) {
        // TODO: Implement awardDrops according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: awardDrops");
    }

    /** Draws the level and current stats.
     * @return terminal-ready map
     */
    public String render() {
        // TODO: Implement render according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: render");
    }
}
