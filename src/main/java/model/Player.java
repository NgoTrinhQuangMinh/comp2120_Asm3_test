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
     * Declares the planned operation: returns the player's current coordinate.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public Position position() {
        // TODO: Implement position according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: position");
    }
    /**
     * Declares the planned operation: replaces the player's current coordinate.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @param position destination coordinate already checked by the caller
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public void moveTo(Position position) {
        // TODO: Implement moveTo according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: moveTo");
    }
    /**
     * Declares the planned operation: returns the player's remaining health.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public int health() {
        // TODO: Implement health according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: health");
    }
    /**
     * Declares the planned operation: calculates the damage of one player attack.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public int attack() {
        // TODO: Implement attack according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: attack");
    }
    /**
     * Declares the planned operation: applies incoming damage to the player.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @param amount requested damage amount; negative values have no effect
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public void damage(int amount) {
        // TODO: Implement damage according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: damage");
    }
    /**
     * Declares the planned operation: returns an immutable snapshot of the player's items.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public List<String> inventory() {
        // TODO: Implement inventory according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: inventory");
    }
    /**
     * Declares the planned operation: checks whether the inventory contains an item name.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @param item stored item name to look up
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public boolean has(String item) {
        // TODO: Implement has according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: has");
    }
    /**
     * Declares the planned operation: adds one item entry to the inventory.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @param item item name to append to the inventory
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public void collect(String item) {
        // TODO: Implement collect according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: collect");
    }

    /**
     * Declares the planned operation: attempts to consume a healing herb or equip a sword.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @param item non-null item name or supported alias; matching ignores case
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public String use(String item) {
        // TODO: Implement use according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: use");
    }
}
