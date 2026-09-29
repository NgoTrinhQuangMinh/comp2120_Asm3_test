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
    private boolean weaponEquipped;
    private final List<String> inventory = new ArrayList<>();

    /** @param position starting position */
    public Player(Position position) { this.position = position; }
    /** @return current position */
    public Position position() { return position; }
    /** @param position checked destination */
    public void moveTo(Position position) { this.position = position; }
    /** @return remaining health */
    public int health() { return health; }
    /** @return attack including the equipped weapon bonus */
    public int attack() { return baseAttack + (weaponEquipped ? 2 : 0); }
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

    /** Uses one herb, or equips a sword without stacking its bonus.
     * @param item item command argument
     * @return feedback
     */
    public String use(String item) {
        if (item.equalsIgnoreCase("herb") || item.equalsIgnoreCase(HERB)) {
            if (!has(HERB)) { return "You have no herb."; }
            if (health == MAX_HEALTH) { return "Your health is already full. Herb kept."; }
            int healed = Math.min(4, MAX_HEALTH - health);
            health += healed;
            inventory.remove(HERB);
            return "You use a herb and restore " + healed + " health.";
        }
        if (item.equalsIgnoreCase("weapon") || item.equalsIgnoreCase(WEAPON)) {
            if (!has(WEAPON)) { return "You have no weapon."; }
            if (weaponEquipped) { return "Your sword is already equipped."; }
            weaponEquipped = true;
            return "Sword equipped. Attack increased by 2.";
        }
        return "Use herb or use weapon.";
    }
}
