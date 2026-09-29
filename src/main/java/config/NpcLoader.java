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
    /** Prevents utility construction. */
    private NpcLoader() { }

    /** Creates fresh NPCs for the numbered map markers.
     * @param maze map containing NPC markers 1-9
     * @return fresh NPC instances
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
