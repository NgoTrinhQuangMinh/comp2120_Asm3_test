package model;

import java.util.ArrayList;
import java.util.List;

/** Player stats and inventory for the base game. */
public class Player {
    public static final String KEY = "Exit key";
    public static final String HERB = "Healing herb";
    public static final String WEAPON = "Sword";
    public static final int MAX_HEALTH = 10;
    private Position position;
    private int health = MAX_HEALTH;
    private final int baseAttack = 3;
    private final List<String> inventory = new ArrayList<>();

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
    /** @return immutable inventory snapshot */
    public List<String> inventory() { return List.copyOf(inventory); }
    /** @param item item name
     * @return whether the item is held
     */
    public boolean has(String item) { return inventory.contains(item); }
    /** @param item collected drop */
    public void collect(String item) { inventory.add(item); }

}
