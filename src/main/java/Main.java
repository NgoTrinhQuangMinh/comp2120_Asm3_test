import config.MazeLoader;
import engine.GameEngine;
import ui.ConsoleUI;

/** Entry point for the maze game skeleton. */
public class Main {
    /**
     * Starts the default maze game.
     *
     * <p>Loads the bundled maze, creates a fresh engine and starts the terminal UI. Configuration failures propagate to the caller rather than being silently replaced with another game.</p>
     *
     * @param args command-line arguments; currently unused
     * @throws IllegalStateException if bundled game configuration cannot be loaded
     * @throws IllegalArgumentException if the loaded game data is invalid
     */
    public static void main(String[] args) {
        new ConsoleUI(new GameEngine(MazeLoader.loadDefault())).run();
    }
}
