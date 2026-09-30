package config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import model.Maze;

/** Loads the small editable text map without external dependencies. */
public final class MazeLoader {
    /**
     * Prevents construction of the maze-loading utility.
     *
     * <p>Maze loading is accessed through the static loadDefault method.</p>
     */
    private MazeLoader() { }

    /**
     * Loads the bundled default maze from the classpath.
     *
     * <p>Reads /maze.txt as UTF-8, passes all lines to Maze validation, and closes the reader after loading. Each call creates a separate Maze instance.</p>
     *
     * @return a validated maze created from the bundled resource
     * @throws IllegalStateException if the resource is missing or cannot be read
     * @throws IllegalArgumentException if the resource contents fail Maze validation
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
