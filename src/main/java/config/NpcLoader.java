package config;

import java.util.Properties;

/** Loads NPC stats, riddles, answers and drops from a properties file. */
public final class NpcLoader {
    /** Prevents utility construction. */
    private NpcLoader() { }


    /** Reads a required non-empty property.
     * @param config configuration
     * @param key property name
     * @return trimmed value
     */
    private static String required(Properties config, String key) {
        String value = config.getProperty(key);
        if (value == null || value.isBlank()) { throw new IllegalArgumentException("Missing property: " + key); }
        return value.trim();
    }
}
