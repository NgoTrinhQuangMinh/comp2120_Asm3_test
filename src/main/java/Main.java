import config.MazeLoader;
import engine.GameEngine;
import ui.ConsoleUI;

/** Entry point for the maze game skeleton. */
public class Main {
    /** Starts a new game using the bundled maze.
     * @param args unused command-line arguments
     */
    public static void main(String[] args) {
        new ConsoleUI(new GameEngine(MazeLoader.loadDefault())).run();
    }
}
