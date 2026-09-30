package config;

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
     * Declares the planned operation: loads the bundled default maze from the classpath.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public static Maze loadDefault() {
        // TODO: Implement loadDefault according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: loadDefault");
    }
}
