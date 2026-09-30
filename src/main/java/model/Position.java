package model;

/** A zero-based column and row in the maze.
 * @param x column
 * @param y row
 */
public record Position(int x, int y) {
    /**
     * Calculates a position offset from this coordinate.
     *
     * <p>Returns a new value without modifying this position. The offset is not checked against map boundaries or walls; callers perform those checks.</p>
     *
     * @param dx horizontal offset in columns; negative values move left
     * @param dy vertical offset in rows; negative values move upward
     * @return the coordinate obtained by adding the offsets to x and y
     */
    public Position move(int dx, int dy) {
        return new Position(x + dx, y + dy);
    }
}
