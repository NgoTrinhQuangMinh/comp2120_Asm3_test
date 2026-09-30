package ui;

/** Contract for a game interface. */
public interface GameUI {
    /**
     * Starts the user interface for its connected game.
     *
     * <p>Implementations own input/output interaction and delegate gameplay rules to the game engine. The method returns when the UI loop ends.</p>
     */
    void run();
}
