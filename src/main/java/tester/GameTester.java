package tester;

import engine.Game;
import java.util.List;

/** Scaffold for automatically sending commands to the engine. */
public class GameTester {
    /**
     * Declares the planned operation: defines the planned automatic command-runner entry point.
     *
     * <p>This branch contains an unimplemented placeholder that always throws before performing the operation. The intended behaviour is described by the parameters; no gameplay state is changed by this placeholder.</p>
     *
     * @param game game intended to receive the scripted inputs
     * @param commands ordered command strings intended for execution
     * @return no value in this scaffold; normal completion is not implemented
     * @throws UnsupportedOperationException always, because this method is not implemented on this branch
     */
    public List<String> run(Game game, List<String> commands) {
        // TODO: Execute inputs against an initialised engine and collect results.
        // TODO: Verify walls, NPC combat/riddles, drops, healing, weapons and escape.
        throw new UnsupportedOperationException("TODO: automatic game tester");
    }
}
