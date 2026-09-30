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
     * Builds a textual map and player-status display.
     *
     * <p>Overlays unresolved NPCs and the player on the stored maze, with the player taking precedence. Original start and numeric NPC markers appear as floor. Appends a legend, health, attack and key status without changing the session.</p>
     *
     * @return a multiline string ready for terminal display
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
