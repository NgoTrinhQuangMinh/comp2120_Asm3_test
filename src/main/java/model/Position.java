package model;

/** A zero-based column and row in the maze.
 * @param x column
 * @param y row
 */
public record Position(int x, int y) {
    /** Returns a neighbouring position.
     * @param dx horizontal offset
     * @param dy vertical offset
     * @return the offset position
     */
    public Position move(int dx, int dy) {
        return new Position(x + dx, y + dy);
    }
}
