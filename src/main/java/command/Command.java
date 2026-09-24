package command;


/** Supported commands for the skeleton's terminal interface. */
public enum Command {
    LEFT, RIGHT, FORWARD, BACKWARD, FIGHT, TALK, ANSWER, USE, INVENTORY, HELP, LOOK, QUIT, UNKNOWN;

    /** Parses a command or keyboard shortcut, ignoring case and surrounding spaces.
     * @param input user input
     * @return parsed command, or UNKNOWN
     */
    public static Command parse(String input) {
        // TODO: Implement parse according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: parse");
    }
}
