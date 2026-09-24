package model;

import java.util.List;

/** An NPC with combat stats, a riddle, and a single set of rewards. */
public class Npc {
    private final Position position;
    private final int attack;
    private final String riddle;
    private final String answer;
    private final List<String> drops;
    private int health;
    private boolean resolved;
    private boolean riddleOffered;

    /** Creates an NPC from configuration.
     * @param position map position
     * @param health starting health
     * @param attack damage per attack
     * @param riddle question to ask
     * @param answer accepted answer
     * @param drops rewards for combat or solving the riddle
     */
    public Npc(Position position, int health, int attack, String riddle, String answer, List<String> drops) {
        // TODO: Validate configured stats, riddle, answer and drops.
        this.position = position;
        this.health = health;
        this.attack = attack;
        this.riddle = riddle;
        this.answer = answer;
        this.drops = List.copyOf(drops);
    }

    /** @return map position */
    public Position position() {
        // TODO: Implement position according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: position");
    }
    /** @return remaining health */
    public int health() {
        // TODO: Implement health according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: health");
    }
    /** @return damage per attack */
    public int attack() {
        // TODO: Implement attack according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: attack");
    }
    /** @return whether the encounter has ended */
    public boolean resolved() {
        // TODO: Implement resolved according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: resolved");
    }
    /** @return configured rewards */
    public List<String> drops() {
        // TODO: Implement drops according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: drops");
    }
    /** @return whether the NPC has asked its riddle */
    public boolean riddleOffered() {
        // TODO: Implement riddleOffered according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: riddleOffered");
    }

    /** Offers the riddle.
     * @return the configured question
     */
    public String offerRiddle() {
        // TODO: Implement offerRiddle according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: offerRiddle");
    }

    /** Checks an answer after the riddle has been offered.
     * @param attempt player's answer
     * @return whether it is correct
     */
    public boolean accepts(String attempt) {
        // TODO: Implement accepts according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: accepts");
    }

    /** Applies player damage.
     * @param damage damage dealt
     */
    public void hit(int damage) {
        // TODO: Implement hit according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: hit");
    }

    /** Ends the encounter so rewards cannot be claimed twice. */
    public void resolve() {
        // TODO: Implement resolve according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: resolve");
    }
}
