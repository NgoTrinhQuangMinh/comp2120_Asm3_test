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

    /**
     * Creates an NPC with configured combat data, a riddle and rewards.
     *
     * <p>Requires positive combat values and non-blank puzzle text, trims the accepted answer, and copies the reward list. A new encounter begins unresolved with its riddle not yet offered.</p>
     *
     * @param position map coordinate occupied by this NPC
     * @param health strictly positive starting health
     * @param attack strictly positive counterattack damage
     * @param riddle non-null, non-blank question shown when talking
     * @param answer non-null, non-blank accepted answer; surrounding whitespace is removed
     * @param drops non-null, non-empty reward list whose entries are copied
     * @throws IllegalArgumentException if combat values are non-positive, puzzle text is blank, or rewards are empty
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

    /**
     * Returns the NPC's configured map coordinate.
     *
     * <p>The position is fixed for this NPC and is used to select the encounter at the player's location.</p>
     *
     * @return the NPC position
     */
    public Position position() { return position; }
    /**
     * Returns the NPC's remaining health.
     *
     * <p>Combat reduces this value; resolving a riddle does not require reducing health to zero.</p>
     *
     * @return current NPC health
     */
    public int health() { return health; }
    /**
     * Returns the NPC's configured counterattack damage.
     *
     * <p>The engine applies this value when the NPC survives a player attack.</p>
     *
     * @return the configured attack value
     */
    public int attack() { return attack; }
    /**
     * Reports whether the encounter has ended.
     *
     * <p>An encounter can be resolved by defeat or explicit resolution after a correct riddle answer.</p>
     *
     * @return true when the NPC is no longer an active encounter
     */
    public boolean resolved() { return resolved; }
    /**
     * Returns the configured encounter rewards.
     *
     * <p>The list is immutable and retains repeated entries. Reading it neither grants rewards nor resolves the encounter.</p>
     *
     * @return the immutable list of reward item names
     */
    public List<String> drops() { return drops; }
    /**
     * Reports whether this NPC has offered its riddle.
     *
     * <p>The flag records dialogue progress independently of the resolved state.</p>
     *
     * @return true after offerRiddle has been called
     */
    public boolean riddleOffered() { return riddleOffered; }

    /**
     * Makes the NPC's riddle available for answering.
     *
     * <p>Sets the offered flag and returns the same configured question on subsequent calls. It does not damage or resolve the NPC.</p>
     *
     * @return the configured riddle text
     */
    public String offerRiddle() { riddleOffered = true; return riddle; }

    /**
     * Checks an answer against this NPC's offered riddle.
     *
     * <p>Returns false until the question has been offered. Once offered, comparison ignores case and surrounding whitespace. A match does not itself resolve the NPC or award items.</p>
     *
     * @param attempt answer text to compare; must be non-null when the riddle has been offered
     * @return true if the riddle was offered and the normalised answer matches
     */
    public boolean accepts(String attempt) { return riddleOffered && answer.equalsIgnoreCase(attempt.trim()); }

    /**
     * Applies player damage to this NPC.
     *
     * <p>Negative damage has no effect and health cannot drop below zero. Reaching zero resolves the encounter; this model method does not award inventory items.</p>
     *
     * @param damage requested damage amount; negative values are treated as zero
     */
    public void hit(int damage) {
        health = Math.max(0, health - Math.max(0, damage));
        if (health == 0) { resolve(); }
    }

    /**
     * Marks this encounter as completed.
     *
     * <p>The operation is idempotent and does not change health, riddle text or configured rewards. The engine excludes resolved NPCs from later interactions.</p>
     */
    public void resolve() { resolved = true; }
}
