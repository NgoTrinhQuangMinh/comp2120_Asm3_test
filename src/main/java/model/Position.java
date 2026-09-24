package model;

/** A map coordinate.
 * @param x zero-based column
 * @param y zero-based row
 */
public record Position(int x, int y) {
    /** Calculates a neighbouring coordinate.
     * @param dx horizontal offset
     * @param dy vertical offset
     * @return destination coordinate
     */
    public Position move(int dx, int dy) {
        // TODO: Calculate the position without changing this coordinate.
        throw new UnsupportedOperationException("TODO: calculate movement");
    }
}
