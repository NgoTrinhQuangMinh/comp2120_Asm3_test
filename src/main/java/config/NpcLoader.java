package config;

import java.util.List;
import java.util.Properties;
import model.Maze;
import model.Npc;

/** Loads NPC stats, riddles, answers and drops from a properties file. */
public final class NpcLoader {
    /**
     * Prevents construction of the NPC-loading utility.
     *
     * <p>NPC creation is accessed through the static configuration-loading method.</p>
     */
    private NpcLoader() { }

    /**
     * Declares the planned operation: creates fresh NPCs for the numbered markers in a maze.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @param maze validated maze supplying the NPC markers and their coordinates
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public static List<Npc> loadDefault(Maze maze) {
        // TODO: Implement loadDefault according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: loadDefault");
    }

    /**
     * Declares the planned operation: reads a mandatory non-blank configuration value.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @param config properties loaded from the NPC configuration
     * @param key required property name
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    private static String required(Properties config, String key) {
        // TODO: Implement required according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: required");
    }
}
