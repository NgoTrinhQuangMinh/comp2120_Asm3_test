package config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import model.Maze;
import model.Npc;
import model.Player;

/** Loads NPC stats, riddles, answers and drops from a properties file. */
public final class NpcLoader {
    /**
     * Prevents construction of the NPC-loading utility.
     *
     * <p>NPC creation is accessed through the static configuration-loading method.</p>
     */
    private NpcLoader() { }

    /**
     * Creates fresh NPCs for the numbered markers in a maze.
     *
     * <p>Reads /npcs.properties as UTF-8, uses npc.&lt;marker&gt; properties, and maps herb, weapon and key reward tokens to stored item names. Repeated drops are retained. Each call creates new encounter state and closes its resource reader.</p>
     *
     * @param maze validated maze supplying the NPC markers and their coordinates
     * @return NPCs in the maze marker scan order
     * @throws IllegalStateException if the configuration resource is missing or cannot be read
     * @throws IllegalArgumentException if required values, numeric stats, rewards or NPC model data are invalid
     */
    public static List<Npc> loadDefault(Maze maze) {
        var stream = NpcLoader.class.getResourceAsStream("/npcs.properties");
        if (stream == null) { throw new IllegalStateException("Missing npcs.properties."); }
        Properties config = new Properties();
        try (var reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            config.load(reader);
        } catch (IOException exception) {
            throw new IllegalStateException("Could not read NPC configuration.", exception);
        }
        List<Npc> npcs = new ArrayList<>();
        for (char marker : maze.npcMarkers()) {
            String prefix = "npc." + marker + ".";
            List<String> drops = new ArrayList<>();
            for (String drop : required(config, prefix + "drops").split(",", -1)) {
                drops.add(switch (drop.trim()) {
                    case "herb" -> Player.HERB;
                    case "weapon" -> Player.WEAPON;
                    case "key" -> Player.KEY;
                    default -> throw new IllegalArgumentException("Unknown drop: " + drop);
                });
            }
            npcs.add(new Npc(maze.find(marker),
                    Integer.parseInt(required(config, prefix + "health")),
                    Integer.parseInt(required(config, prefix + "attack")),
                    required(config, prefix + "riddle"),
                    required(config, prefix + "answer"), drops));
        }
        return npcs;
    }

    /**
     * Reads a mandatory non-blank configuration value.
     *
     * <p>Trims surrounding whitespace after checking that the property is present and contains non-whitespace text. The supplied Properties object is not modified.</p>
     *
     * @param config properties loaded from the NPC configuration
     * @param key required property name
     * @return the trimmed property value
     * @throws IllegalArgumentException if the property is absent or blank
     */
    private static String required(Properties config, String key) {
        String value = config.getProperty(key);
        if (value == null || value.isBlank()) { throw new IllegalArgumentException("Missing property: " + key); }
        return value.trim();
    }
}
