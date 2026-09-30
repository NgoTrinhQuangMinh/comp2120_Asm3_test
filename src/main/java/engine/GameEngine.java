package engine;

import config.NpcLoader;
import java.util.List;
import model.Npc;
import model.Maze;
import model.Player;
import model.Position;

/** Minimal combat and riddle rules, independent of terminal input/output. */
public class GameEngine {
    public static final String KEY = Player.KEY;
    private final Maze maze;
    private final Player player;
    private final List<Npc> npcs;
    private boolean won;
    private boolean quit;

    /**
     * Initialises a fresh game session for the supplied maze.
     *
     * <p>Creates the player at the P marker and loads fresh NPCs from the bundled configuration. The engine retains the supplied maze and owns the session's mutable player and NPC state.</p>
     *
     * @param maze validated maze containing the player start and numbered NPC markers
     * @throws IllegalArgumentException if a required marker or NPC configuration value is invalid
     * @throws IllegalStateException if the NPC resource cannot be loaded
     */
    public GameEngine(Maze maze) {
        this.maze = maze;
        player = new Player(maze.find('P'));
        npcs = NpcLoader.loadDefault(maze);
    }

    /**
     * Exposes the current session's player model.
     *
     * <p>Returns the live mutable player rather than a copy; callers can inspect its state and must respect the model's ownership rules.</p>
     *
     * @return the player owned by this game session
     */
    public Player player() { return player; }
    /**
     * Reports whether the player has escaped successfully.
     *
     * <p>Quitting or losing all health does not by itself set the victory flag.</p>
     *
     * @return true once the engine has recorded a successful exit
     */
    public boolean won() { return won; }
    /**
     * Checks whether the session has reached an end condition.
     *
     * <p>A recorded victory, a quit request or zero player health ends play. This query does not modify state.</p>
     *
     * @return true if the session was won, was quit, or the player has zero health
     */
    public boolean finished() { return won || quit || player.health() == 0; }


    /**
     * Attempts to move the player by a coordinate offset.
     *
     * <p>Blocks walls, out-of-bounds destinations and the exit when no key is held. A permitted move updates position, records victory at the exit, or reports an active NPC's stats. Arrival alone does not start combat.</p>
     *
     * @param dx horizontal movement offset in columns
     * @param dy vertical movement offset in rows
     * @return movement, blocking, encounter or victory feedback
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

    /**
     * Finds the active encounter on the player's current tile.
     *
     * <p>Searches the existing session collection and ignores resolved NPCs. Returning the same stored object preserves encounter progress when the player leaves and returns.</p>
     *
     * @return the first unresolved NPC at the player position, or null if none exists
     */
    private Npc currentNpc() {
        return npcs.stream().filter(n -> !n.resolved() && n.position().equals(player.position()))
                .findFirst().orElse(null);
    }





}
