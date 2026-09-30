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
     * Adds all rewards from an NPC to the player inventory.
     *
     * <p>Preserves configured order and duplicates and does not automatically use or equip items. The caller must ensure this is called only once for a completed encounter; this helper does not enforce that condition itself.</p>
     *
     * @param npc NPC whose configured drops are to be awarded
     * @return feedback listing the collected item names
     */
    private String awardDrops(Npc npc) {
        npc.drops().forEach(player::collect);
        return "Drops collected: " + String.join(", ", npc.drops()) + ".";
    }

}
