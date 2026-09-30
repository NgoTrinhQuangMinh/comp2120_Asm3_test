package model;

/** A map coordinate.
 * @param x zero-based column
 * @param y zero-based row
 */
public record Position(int x, int y) {
    /**
     * Declares the planned operation: calculates a position offset from this coordinate.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @param dx horizontal offset in columns; negative values move left
     * @param dy vertical offset in rows; negative values move upward
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public Position move(int dx, int dy) {
        // TODO: Calculate the position without changing this coordinate.
        throw new UnsupportedOperationException("TODO: calculate movement");
    }
}
