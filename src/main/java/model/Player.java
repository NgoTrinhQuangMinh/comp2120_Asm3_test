package model;


/** Player position and combat state. */
public class Player {
    private Position position;

    /** @param position starting position */
    public Player(Position position) { this.position = position; }
    /** @return current position */
    public Position position() { return position; }
    /** @param position checked destination */
    public void moveTo(Position position) { this.position = position; }

}
