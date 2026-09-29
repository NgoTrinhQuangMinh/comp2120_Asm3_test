package engine;

import config.NpcLoader;
import java.util.List;
import model.Npc;
import model.Maze;
import model.Player;

/** Minimal combat and riddle rules, independent of terminal input/output. */
public class GameEngine {
    private final Maze maze;
    private final Player player;
    private final List<Npc> npcs;

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








}
