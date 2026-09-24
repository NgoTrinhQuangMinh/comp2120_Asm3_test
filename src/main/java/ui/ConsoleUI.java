package ui;

import engine.Game;

/** Simple terminal game loop. */
public class ConsoleUI implements GameUI {
    private final Game game;

    /** Connects the terminal to a game.
     * @param game game to play
     */
    public ConsoleUI(Game game) { this.game = game; }

    /** Reads commands until the game ends or input closes. */
    public void run() {
        // TODO: Print the story and map, then read commands with Scanner.
        // TODO: Enter submits each command; no JLine or immediate key handling.
        throw new UnsupportedOperationException("TODO: terminal command loop");
    }
}
