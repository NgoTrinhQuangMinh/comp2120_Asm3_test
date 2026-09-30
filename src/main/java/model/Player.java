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
     * Calculates the damage of one player attack.
     *
     * <p>Adds the single weapon bonus when a sword has been equipped. Repeated equipment requests do not stack this bonus.</p>
     *
     * @return base attack plus 2 when equipped, otherwise base attack
     */
    public int attack() { return baseAttack + (weaponEquipped ? 2 : 0); }
    /**
     * Applies incoming damage to the player.
     *
     * <p>Negative amounts are treated as zero, and remaining health is clamped at zero. This operation does not itself print feedback or stop the game loop.</p>
     *
     * @param amount requested damage amount; negative values have no effect
     */
    public void damage(int amount) { health = Math.max(0, health - Math.max(0, amount)); }
    /**
     * Returns an immutable snapshot of the player's items.
     *
     * <p>The snapshot preserves order and duplicate items. Later collection or consumption does not change a previously returned snapshot.</p>
     *
     * @return an immutable copy of the current inventory
     */
    public List<String> inventory() { return List.copyOf(inventory); }
    /**
     * Checks whether the inventory contains an item name.
     *
     * <p>Uses exact string equality on stored names; command aliases and case-insensitive matching are handled by item-use parsing instead.</p>
     *
     * @param item stored item name to look up
     * @return true if at least one matching item is held
     */
    public boolean has(String item) { return inventory.contains(item); }
    /**
     * Adds one item entry to the inventory.
     *
     * <p>Preserves duplicate rewards and does not automatically consume or equip the item. The caller supplies a supported item name.</p>
     *
     * @param item item name to append to the inventory
     */
    public void collect(String item) { inventory.add(item); }

    /**
     * Attempts to consume a healing herb or equip a sword.
     *
     * <p>A herb heals up to four points without exceeding maximum health and is consumed only when healing occurs. A held sword grants one persistent attack bonus. Missing items, repeated equipment and unsupported names return feedback without applying the requested effect.</p>
     *
     * @param item non-null item name or supported alias; matching ignores case
     * @return feedback describing the effect, missing item, or available choices
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
