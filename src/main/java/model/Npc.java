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
     * Stores configured NPC data in the model scaffold.
     *
     * <p>Assigns the supplied position, stats and puzzle text and copies the reward list. Stat and text validation and answer trimming are not implemented in this constructor on this branch.</p>
     *
     * @param position configured NPC location
     * @param health configured starting health; not validated here
     * @param attack configured attack; not validated here
     * @param riddle question text stored as supplied
     * @param answer answer text stored without trimming
     * @param drops non-null reward list to copy
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

    /**
     * Declares the planned operation: returns the NPC's configured map coordinate.
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
     * Declares the planned operation: returns the NPC's remaining health.
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
     * Declares the planned operation: returns the NPC's configured counterattack damage.
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
     * Declares the planned operation: reports whether the encounter has ended.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public boolean resolved() {
        // TODO: Implement resolved according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: resolved");
    }
    /**
     * Declares the planned operation: returns the configured encounter rewards.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public List<String> drops() {
        // TODO: Implement drops according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: drops");
    }
    /**
     * Declares the planned operation: reports whether this NPC has offered its riddle.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public boolean riddleOffered() {
        // TODO: Implement riddleOffered according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: riddleOffered");
    }

    /**
     * Declares the planned operation: makes the NPC's riddle available for answering.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public String offerRiddle() {
        // TODO: Implement offerRiddle according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: offerRiddle");
    }

    /**
     * Declares the planned operation: checks an answer against this NPC's offered riddle.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @param attempt answer text to compare; must be non-null when the riddle has been offered
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public boolean accepts(String attempt) {
        // TODO: Implement accepts according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: accepts");
    }

    /**
     * Declares the planned operation: applies player damage to this NPC.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @param damage requested damage amount; negative values are treated as zero
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public void hit(int damage) {
        // TODO: Implement hit according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: hit");
    }

    /**
     * Declares the planned operation: marks this encounter as completed.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public void resolve() {
        // TODO: Implement resolve according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: resolve");
    }
}
