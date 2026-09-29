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





}
