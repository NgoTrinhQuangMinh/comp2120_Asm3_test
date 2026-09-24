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
    public Position position() {
        // TODO: Implement position according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: position");
    }
    /** @param position checked destination */
    public void moveTo(Position position) {
        // TODO: Implement moveTo according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: moveTo");
    }
    /** @return remaining health */
    public int health() {
        // TODO: Implement health according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: health");
    }
    /** @return attack including the equipped weapon bonus */
    public int attack() {
        // TODO: Implement attack according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: attack");
    }
    /** @param amount incoming damage */
    public void damage(int amount) {
        // TODO: Implement damage according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: damage");
    }
    /** @return immutable inventory snapshot */
    public List<String> inventory() {
        // TODO: Implement inventory according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: inventory");
    }
    /** @param item item name
     * @return whether the item is held
     */
    public boolean has(String item) {
        // TODO: Implement has according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: has");
    }
    /** @param item collected drop */
    public void collect(String item) {
        // TODO: Implement collect according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: collect");
    }

    /** Uses one herb, or equips a sword without stacking its bonus.
     * @param item item command argument
     * @return feedback
     */
    public String use(String item) {
        // TODO: Implement use according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: use");
    }
}
