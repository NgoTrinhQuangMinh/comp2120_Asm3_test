package model;

import java.util.ArrayList;
import java.util.List;

/** Immutable text map with horizontal and vertical walls. */
public class Maze {
    private final List<String> rows;

    /**
     * Constructs an immutable maze from rectangular text rows.
     *
     * <p>Validates the supported tile alphabet, exactly one player start and exit, and uniqueness of each numbered NPC marker. Copies the row list so subsequent caller changes cannot alter the maze.</p>
     *
     * @param rows non-null, non-empty rows containing only walls (- or |), floor (.), P, X, and NPC digits 1 through 9
     * @throws IllegalArgumentException if the layout is empty, non-rectangular, contains unsupported symbols, or violates the marker rules
     */
    public Maze(List<String> rows) {
        if (rows.isEmpty() || rows.get(0).isEmpty()) { throw new IllegalArgumentException("Maze must not be empty."); }
        int width = rows.get(0).length();
        for (String row : rows) {
            if (row.length() != width || !row.matches("[-|.PX1-9]+")) {
                throw new IllegalArgumentException("Maze must be rectangular with valid symbols.");
            }
        }
        String cells = String.join("", rows);
        for (char marker : new char[] {'P', 'X'}) {
            if (cells.chars().filter(c -> c == marker).count() != 1) {
                throw new IllegalArgumentException("Maze needs exactly one " + marker + ".");
            }
        }
        for (char marker = '1'; marker <= '9'; marker++) {
            final char id = marker;
            if (cells.chars().filter(c -> c == id).count() > 1) {
                throw new IllegalArgumentException("NPC markers must be unique: " + marker);
            }
        }
        this.rows = List.copyOf(rows);
    }

    /**
     * Returns the number of columns in this maze.
     *
     * <p>All stored rows have this width after successful validation.</p>
     *
     * @return the width of the first stored row
     */
    public int width() { return rows.get(0).length(); }
    /**
     * Returns the number of rows in this maze.
     *
     * <p>The value describes the stored layout and does not depend on the player or NPC positions.</p>
     *
     * @return the number of stored rows
     */
    public int height() { return rows.size(); }

    /**
     * Reads the tile at a map coordinate.
     *
     * <p>Coordinates outside the stored rectangle are treated as vertical walls, allowing callers to handle boundaries using the same collision rules as other walls.</p>
     *
     * @param position non-null zero-based coordinate to inspect
     * @return the stored tile symbol, or | for an out-of-bounds coordinate
     */
    public char at(Position position) {
        if (position.x() < 0 || position.y() < 0 || position.x() >= width() || position.y() >= height()) { return '|'; }
        return rows.get(position.y()).charAt(position.x());
    }

    /**
     * Checks whether a coordinate blocks movement.
     *
     * <p>Both horizontal and vertical wall symbols block movement. Out-of-bounds positions also block movement because tile lookup treats them as walls.</p>
     *
     * @param position non-null coordinate to test
     * @return true when the tile is - or |; false otherwise
     */
    public boolean isWall(Position position) { return at(position) == '-' || at(position) == '|'; }

    /**
     * Collects the numbered NPC markers in the layout.
     *
     * <p>Scans rows from top to bottom and columns from left to right. The returned immutable list contains markers only, not live NPC encounter state.</p>
     *
     * @return NPC digits in map scan order, or an empty immutable list when none are present
     */
    public List<Character> npcMarkers() {
        List<Character> markers = new ArrayList<>();
        for (String row : rows) {
            for (char cell : row.toCharArray()) {
                if (cell >= '1' && cell <= '9') { markers.add(cell); }
            }
        }
        return List.copyOf(markers);
    }

    /**
     * Locates the first occurrence of a tile marker.
     *
     * <p>Searches rows in order and returns zero-based column and row coordinates. This method does not change the layout.</p>
     *
     * @param marker tile character to locate, such as P, X, or an NPC digit
     * @return the position of the first matching tile
     * @throws IllegalArgumentException if the marker does not occur in the maze
     */
    public Position find(char marker) {
        for (int y = 0; y < height(); y++) {
            int x = rows.get(y).indexOf(marker);
            if (x >= 0) { return new Position(x, y); }
        }
        throw new IllegalArgumentException("Missing marker: " + marker);
    }
}
