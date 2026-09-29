package model;

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





}
