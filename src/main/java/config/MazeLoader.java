package config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import model.Maze;

/** Loads the small editable text map without external dependencies. */
public final class MazeLoader {
    /** Prevents utility-class construction. */
    private MazeLoader() { }

    /** @return the bundled maze definition
     * @throws IllegalStateException if the resource is missing or unreadable
     */
    public static Maze loadDefault() {
        var stream = MazeLoader.class.getResourceAsStream("/maze.txt");
        if (stream == null) { throw new IllegalStateException("Missing maze.txt resource."); }
        try (var reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            return new Maze(reader.lines().toList());
        } catch (IOException exception) {
            throw new IllegalStateException("Could not read maze.txt.", exception);
        }
    }
}
