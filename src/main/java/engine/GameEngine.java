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
