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



    /** Adds the resolved encounter's rewards to inventory.
     * @param npc resolved NPC
     * @return reward description
     */
    private String awardDrops(Npc npc) {
        npc.drops().forEach(player::collect);
        return "Drops collected: " + String.join(", ", npc.drops()) + ".";
    }

}
