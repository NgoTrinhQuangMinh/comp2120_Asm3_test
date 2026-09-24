package config;

import model.Maze;

/** Loads the small editable text map without external dependencies. */
public final class MazeLoader {
    /** Prevents utility-class construction. */
    private MazeLoader() { }

    /** @return the bundled maze definition
     * @throws IllegalStateException if the resource is missing or unreadable
     */
    public static Maze loadDefault() {
        // TODO: Implement loadDefault according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: loadDefault");
    }
}
