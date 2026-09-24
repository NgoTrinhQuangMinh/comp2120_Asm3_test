package config;

import java.util.List;
import java.util.Properties;
import model.Maze;
import model.Npc;

/** Loads NPC stats, riddles, answers and drops from a properties file. */
public final class NpcLoader {
    /** Prevents utility construction. */
    private NpcLoader() { }

    /** Creates fresh NPCs for the numbered map markers.
     * @param maze map containing NPC markers 1-9
     * @return fresh NPC instances
     */
    public static List<Npc> loadDefault(Maze maze) {
        // TODO: Implement loadDefault according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: loadDefault");
    }

    /** Reads a required non-empty property.
     * @param config configuration
     * @param key property name
     * @return trimmed value
     */
    private static String required(Properties config, String key) {
        // TODO: Implement required according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: required");
    }
}
