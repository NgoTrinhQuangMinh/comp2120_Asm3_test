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
        if (health <= 0 || attack <= 0 || riddle.isBlank() || answer.isBlank() || drops.isEmpty()) {
            throw new IllegalArgumentException("NPC stats must be positive and riddle, answer, drops must be present.");
        }
        this.position = position;
        this.health = health;
        this.attack = attack;
        this.riddle = riddle;
        this.answer = answer.trim();
        this.drops = List.copyOf(drops);
    }

    /** @return map position */
    public Position position() { return position; }
    /** @return remaining health */
    public int health() { return health; }
    /** @return damage per attack */
    public int attack() { return attack; }
    /** @return whether the encounter has ended */
    public boolean resolved() { return resolved; }
    /** @return configured rewards */
    public List<String> drops() { return drops; }
    /** @return whether the NPC has asked its riddle */
    public boolean riddleOffered() { return riddleOffered; }

    /** Offers the riddle.
     * @return the configured question
     */
    public String offerRiddle() { riddleOffered = true; return riddle; }

    /** Checks an answer after the riddle has been offered.
     * @param attempt player's answer
     * @return whether it is correct
     */
    public boolean accepts(String attempt) { return riddleOffered && answer.equalsIgnoreCase(attempt.trim()); }

    /** Applies player damage.
     * @param damage damage dealt
     */
    public void hit(int damage) {
        health = Math.max(0, health - Math.max(0, damage));
        if (health == 0) { resolve(); }
    }

    /** Ends the encounter so rewards cannot be claimed twice. */
    public void resolve() { resolved = true; }
}
