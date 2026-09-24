package model;

import java.util.List;

/** Immutable text map with horizontal and vertical walls. */
public class Maze {
    private final List<String> rows;

    /** Validates map dimensions and markers.
     * @param rows map rows using - | . P X and unique NPC markers 1-9
     */
    public Maze(List<String> rows) {
        // TODO: Validate dimensions and markers (-, |, P, X and NPC ids 1-9).
        this.rows = List.copyOf(rows);
    }

    /** @return map width */
    public int width() {
        // TODO: Implement width according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: width");
    }
    /** @return map height */
    public int height() {
        // TODO: Implement height according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: height");
    }

    /** Reads a tile; outside positions count as walls.
     * @param position position to inspect
     * @return tile symbol
     */
    public char at(Position position) {
        // TODO: Implement at according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: at");
    }

    /** @param position position to inspect
     * @return whether movement is blocked
     */
    public boolean isWall(Position position) {
        // TODO: Implement isWall according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: isWall");
    }

    /** @return NPC markers present in this map */
    public List<Character> npcMarkers() {
        // TODO: Implement npcMarkers according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: npcMarkers");
    }

    /** @param marker marker to locate
     * @return marker position
     */
    public Position find(char marker) {
        // TODO: Implement find according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: find");
    }
}
