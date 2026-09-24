package ui;

import engine.GameEngine;
import java.util.Scanner;

/** Simple terminal game loop. */
public class ConsoleUI {
    private final GameEngine game;

    /** Connects the terminal to a game.
     * @param game game to play
     */
    public ConsoleUI(GameEngine game) { this.game = game; }

    /** Reads commands until the game ends or input closes. */
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
