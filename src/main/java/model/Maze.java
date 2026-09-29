package model;

import java.util.ArrayList;
import java.util.List;

/** Immutable text map with horizontal and vertical walls. */
public class Maze {
    private final List<String> rows;

    /** Validates map dimensions and markers.
     * @param rows map rows using - | . P X and unique NPC markers 1-9
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

    /** @return map width */
    public int width() { return rows.get(0).length(); }
    /** @return map height */
    public int height() { return rows.size(); }

    /** Reads a tile; outside positions count as walls.
     * @param position position to inspect
     * @return tile symbol
     */
    public char at(Position position) {
        if (position.x() < 0 || position.y() < 0 || position.x() >= width() || position.y() >= height()) { return '|'; }
        return rows.get(position.y()).charAt(position.x());
    }

    /** @param position position to inspect
     * @return whether movement is blocked
     */
    public boolean isWall(Position position) { return at(position) == '-' || at(position) == '|'; }

    /** @return NPC markers present in this map */
    public List<Character> npcMarkers() {
        List<Character> markers = new ArrayList<>();
        for (String row : rows) {
            for (char cell : row.toCharArray()) {
                if (cell >= '1' && cell <= '9') { markers.add(cell); }
            }
        }
        return List.copyOf(markers);
    }

    /** @param marker marker to locate
     * @return marker position
     */
    public Position find(char marker) {
        for (int y = 0; y < height(); y++) {
            int x = rows.get(y).indexOf(marker);
            if (x >= 0) { return new Position(x, y); }
        }
        throw new IllegalArgumentException("Missing marker: " + marker);
    }
}
