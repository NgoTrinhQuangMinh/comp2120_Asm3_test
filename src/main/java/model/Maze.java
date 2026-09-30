package model;

import java.util.List;

/** Immutable text map with horizontal and vertical walls. */
public class Maze {
    private final List<String> rows;

    /**
     * Stores maze rows for the current model scaffold.
     *
     * <p>Copies the supplied list so caller changes cannot alter stored rows. Layout and marker validation are still TODO work on this branch and are not performed here.</p>
     *
     * @param rows non-null row list to copy; layout contents are not validated here
     */
    public Maze(List<String> rows) {
        // TODO: Validate dimensions and markers (-, |, P, X and NPC ids 1-9).
        this.rows = List.copyOf(rows);
    }

    /**
     * Declares the planned operation: returns the number of columns in this maze.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public int width() {
        // TODO: Implement width according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: width");
    }
    /**
     * Declares the planned operation: returns the number of rows in this maze.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public int height() {
        // TODO: Implement height according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: height");
    }

    /**
     * Declares the planned operation: reads the tile at a map coordinate.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @param position non-null zero-based coordinate to inspect
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public char at(Position position) {
        // TODO: Implement at according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: at");
    }

    /**
     * Declares the planned operation: checks whether a coordinate blocks movement.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @param position non-null coordinate to test
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public boolean isWall(Position position) {
        // TODO: Implement isWall according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: isWall");
    }

    /**
     * Declares the planned operation: collects the numbered NPC markers in the layout.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public List<Character> npcMarkers() {
        // TODO: Implement npcMarkers according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: npcMarkers");
    }

    /**
     * Declares the planned operation: locates the first occurrence of a tile marker.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @param marker tile character to locate, such as P, X, or an NPC digit
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public Position find(char marker) {
        // TODO: Implement find according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: find");
    }
}
