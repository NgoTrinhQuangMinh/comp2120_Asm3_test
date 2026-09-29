package model;


/** Player position and combat state. */
public class Player {
    public static final int MAX_HEALTH = 10;
    private Position position;
    private int health = MAX_HEALTH;
    private final int baseAttack = 3;

    /** @param position starting position */
    public Player(Position position) { this.position = position; }
    /** @return current position */
    public Position position() { return position; }
    /** @param position checked destination */
    public void moveTo(Position position) { this.position = position; }
    /** @return remaining health */
    public int health() { return health; }
    /** @return base attack */
    public int attack() { return baseAttack; }
    /** @param amount incoming damage */
    public void damage(int amount) { health = Math.max(0, health - Math.max(0, amount)); }

}
