package engine;

/** Engine contract shared by the terminal UI and future automatic tester. */
public interface Game {
    /** @param input typed user command
     * @return feedback describing the action
     */
    String execute(String input);

    /** @return whether the player escaped */
    boolean won();

    /** @return whether play ended by winning, losing or quitting */
    boolean finished();

    /** @return text map and player status */
    String render();
}
