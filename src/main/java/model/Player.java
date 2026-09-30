package model;


/** Player position and combat state. */
public class Player {
    public static final int MAX_HEALTH = 10;
    private Position position;
    private int health = MAX_HEALTH;
    private final int baseAttack = 3;

    /**
     * Creates a player at the supplied starting coordinate.
     *
     * <p>Initialises player state using the class field defaults. The constructor does not check whether the coordinate belongs to a maze or is walkable.</p>
     *
     * @param position initial player coordinate, normally the map start marker
     */
    public Player(Position position) { this.position = position; }
    /**
     * Returns the player's current coordinate.
     *
     * <p>The immutable coordinate can be read without changing player state.</p>
     *
     * @return the position currently stored for the player
     */
    public Position position() { return position; }
    /**
     * Replaces the player's current coordinate.
     *
     * <p>Performs no collision, boundary or exit checks. The engine must validate the destination before applying the update.</p>
     *
     * @param position destination coordinate already checked by the caller
     */
    public void moveTo(Position position) { this.position = position; }
    /**
     * Returns the player's remaining health.
     *
     * <p>Damage and healing operations update this value; reading it does not mutate player state.</p>
     *
     * @return the current health value, initially MAX_HEALTH
     */
    public int health() { return health; }
    /**
     * Returns the player's base attack strength.
     *
     * <p>This branch has not introduced equipment bonuses; the returned value is the stored base attack.</p>
     *
     * @return the configured base attack value
     */
    public int attack() { return baseAttack; }
    /**
     * Applies incoming damage to the player.
     *
     * <p>Negative amounts are treated as zero, and remaining health is clamped at zero. This operation does not itself print feedback or stop the game loop.</p>
     *
     * @param amount requested damage amount; negative values have no effect
     */
    public void damage(int amount) { health = Math.max(0, health - Math.max(0, amount)); }

}
