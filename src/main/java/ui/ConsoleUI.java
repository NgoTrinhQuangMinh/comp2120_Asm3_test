package ui;

import engine.Game;

/** Simple terminal game loop. */
public class ConsoleUI implements GameUI {
    private final Game game;

    /**
     * Connects a terminal UI to an existing game session.
     *
     * <p>Retains the supplied game; construction does not start reading input or running the game loop.</p>
     *
     * @param game game session whose commands, status and display are used by this UI
     */
    public ConsoleUI(Game game) { this.game = game; }

    /**
     * Declares the planned operation: runs the line-based terminal game loop.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public void run() {
        // TODO: Print the story and map, then read commands with Scanner.
        // TODO: Enter submits each command; no JLine or immediate key handling.
        throw new UnsupportedOperationException("TODO: terminal command loop");
    }
}
