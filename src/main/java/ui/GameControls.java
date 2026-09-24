package ui;

import engine.GameEngine;
import java.util.Locale;

/** Immediate game controls, with a separate buffer for riddle answers. */
public class GameControls {
    private final GameEngine game;
    private final StringBuilder answer = new StringBuilder();
    private boolean answering;
    private boolean inventoryVisible;
    private String message = "Find the key. Fight an NPC or solve its riddle for drops.";

    /** @param game game receiving commands */
    public GameControls(GameEngine game) { this.game = game; }
    /** @return latest game feedback */
    public String message() { return message; }
    /** @return current answer text */
    public String answer() { return answer.toString(); }
    /** @return whether text is being entered */
    public boolean answering() { return answering; }
    /** @return whether to show inventory */
    public boolean inventoryVisible() { return inventoryVisible; }

    /** Handles a character or named arrow key; typing never triggers game actions.
     * @param key character, or UP/DOWN/LEFT/RIGHT
     */
    public void handle(String key) {
        if (game.finished()) { return; }
        if (key.equals("\u0003") || key.equals("\u0004")) {
            message = game.execute("quit");
            return;
        }
        if (answering) {
            typeAnswer(key);
            return;
        }
        String command = switch (key.toLowerCase(Locale.ROOT)) {
            case "w", "up" -> "forward";
            case "s", "down" -> "backward";
            case "a", "left" -> "left";
            case "d", "right" -> "right";
            case "f" -> "fight";
            case "t" -> "talk";
            case "h" -> "use herb";
            case "e" -> "use weapon";
            case "q" -> "quit";
            default -> "";
        };
        if (key.equalsIgnoreCase("i")) { inventoryVisible = !inventoryVisible; }
        if (!command.isEmpty()) {
            message = game.execute(command);
            if (command.equals("talk") && game.canAnswerRiddle()) {
                answering = true;
                answer.setLength(0);
                message = message.replace("\nType answer <your answer>.", "");
            }
        }
    }

    /** Edits or submits an answer while movement controls are suspended.
     * @param key input character or named key
     */
    private void typeAnswer(String key) {
        if (key.equals("\u001b")) {
            answering = false;
            answer.setLength(0);
            message = "Answer cancelled. Press T to retry, or F to fight.";
        } else if (key.equals("\r") || key.equals("\n")) {
            if (answer.toString().isBlank()) { return; }
            message = game.execute("answer " + answer);
            answering = game.canAnswerRiddle();
            answer.setLength(0);
        } else if (key.equals("\u007f") || key.equals("\b")) {
            if (!answer.isEmpty()) { answer.deleteCharAt(answer.length() - 1); }
        } else if (key.length() == 1 && !Character.isISOControl(key.charAt(0)) && answer.length() < 80) {
            answer.append(key);
        }
    }
}
