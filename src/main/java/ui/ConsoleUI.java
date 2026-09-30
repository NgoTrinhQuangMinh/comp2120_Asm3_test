package ui;

import engine.GameEngine;
import java.util.Scanner;

/** Simple terminal game loop. */
public class ConsoleUI {
    private final GameEngine game;

    /**
     * Connects a terminal UI to an existing game session.
     *
     * <p>Retains the supplied game; construction does not start reading input or running the game loop.</p>
     *
     * @param game game session whose commands, status and display are used by this UI
     */
    public ConsoleUI(GameEngine game) { this.game = game; }

    /**
     * Runs the line-based terminal game loop.
     *
     * <p>Prints startup instructions and the initial map, then reads full lines from standard input. Displays command feedback and refreshed state until the game finishes or input reaches end-of-file.</p>
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        System.out.println("MAZE ESCAPE\nOne NPC holds the exit key. Fight NPCs or solve their riddles to collect drops.");
        System.out.println(GameEngine.HELP);
        System.out.println(game.render());
        while (!game.finished()) {
            System.out.print("\n> ");
            if (!input.hasNextLine()) { break; }
            System.out.println(game.execute(input.nextLine()));
            System.out.println(game.render());
        }
    }
}
