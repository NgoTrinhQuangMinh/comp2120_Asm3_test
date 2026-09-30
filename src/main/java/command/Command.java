package command;


/** Supported commands for the skeleton's terminal interface. */
public enum Command {
    LEFT, RIGHT, FORWARD, BACKWARD, FIGHT, TALK, ANSWER, USE, INVENTORY, HELP, LOOK, QUIT, UNKNOWN;

    /**
     * Declares the planned operation: converts the first input token into a supported command.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @param input raw player command, optionally followed by an argument; may be null
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public static Command parse(String input) {
        // TODO: Implement parse according to feature/game-skeleton.
        throw new UnsupportedOperationException("TODO: parse");
    }
}
