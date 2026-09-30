package engine;

/** Engine contract shared by the terminal UI and future automatic tester. */
public interface Game {
    /**
     * Defines execution of one command against a game session.
     *
     * <p>Implementations interpret the input and return player-facing feedback. This contract does not require terminal input or output, so it can be used by both a UI and a tester.</p>
     *
     * @param input command text supplied by a player or automated caller
     * @return feedback describing the action or its rejection
     */
    String execute(String input);

    /**
     * Reports whether the session ended in a successful escape.
     *
     * <p>Implementations distinguish victory from other end conditions such as quitting or player death.</p>
     *
     * @return true when the player has won
     */
    boolean won();

    /**
     * Reports whether the session has ended.
     *
     * <p>Implementations expose their completion state so callers know when to stop submitting gameplay input.</p>
     *
     * @return true if play ended through victory, quitting or player death
     */
    boolean finished();

    /**
     * Produces the current textual game display.
     *
     * <p>The returned representation is intended for presentation by a caller rather than direct terminal output by this method.</p>
     *
     * @return the map and current player-status text
     */
    String render();
}
