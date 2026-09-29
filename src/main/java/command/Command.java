package command;

import java.util.Locale;

/** Supported commands for the skeleton's terminal interface. */
public enum Command {
    LEFT, RIGHT, FORWARD, BACKWARD, FIGHT, TALK, ANSWER, USE, INVENTORY, HELP, LOOK, QUIT, UNKNOWN;

    /** Parses a command or keyboard shortcut, ignoring case and surrounding spaces.
     * @param input user input
     * @return parsed command, or UNKNOWN
     */
    public static Command parse(String input) {
        if (input == null) { return UNKNOWN; }
        return switch (input.trim().toLowerCase(Locale.ROOT).split("\\s+", 2)[0]) {
            case "left", "a" -> LEFT;
            case "right", "d" -> RIGHT;
            case "forward", "w" -> FORWARD;
            case "backward", "backwards", "s" -> BACKWARD;
            case "fight", "f" -> FIGHT;
            case "talk", "t" -> TALK;
            case "answer" -> ANSWER;
            case "use", "equip" -> USE;
            case "inventory", "i" -> INVENTORY;
            case "help", "?" -> HELP;
            case "look", "map" -> LOOK;
            case "quit", "q" -> QUIT;
            default -> UNKNOWN;
        };
    }
}
