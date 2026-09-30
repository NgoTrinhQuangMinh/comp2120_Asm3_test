package command;

import java.util.Locale;

/** Supported commands for the skeleton's terminal interface. */
public enum Command {
    LEFT, RIGHT, FORWARD, BACKWARD, FIGHT, TALK, ANSWER, USE, INVENTORY, HELP, LOOK, QUIT, UNKNOWN;

    /**
     * Converts the first input token into a supported command.
     *
     * <p>Ignores surrounding whitespace and letter case using the root locale. Recognises command aliases while leaving argument extraction to the engine. Null, blank and unsupported input map to UNKNOWN.</p>
     *
     * @param input raw player command, optionally followed by an argument; may be null
     * @return the recognised action, or UNKNOWN
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
