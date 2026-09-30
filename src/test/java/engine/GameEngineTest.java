package engine;

import config.MazeLoader;
import model.Player;
import model.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Behaviour checks for NPC combat, riddles, and drops. */
class GameEngineTest {
    /**
     * Creates an isolated game for a test scenario.
     *
     * <p>Loads the bundled maze and constructs fresh player and NPC state so one test cannot depend on another test's mutations.</p>
     *
     * @return a fresh engine using the bundled configuration
     */
    private GameEngine newGame() { return new GameEngine(MazeLoader.loadDefault()); }

    /**
     * Executes a sequence of single-token test commands.
     *
     * <p>Splits the supplied string on spaces and submits each token separately. Multi-word commands such as riddle answers must be sent directly to the engine instead.</p>
     *
     * @param game engine whose state is advanced by the sequence
     * @param commands space-separated movement or other single-token commands
     */
    private void play(GameEngine game, String commands) {
        for (String command : commands.split(" ")) { game.execute(command); }
    }

    /**
     * Verifies that blocked movement and unavailable actions preserve state.
     *
     * <p>Exercises both wall behaviour and empty interactions, missing items, unknown commands and rendering expectations before checking an ordinary floor tile.</p>
     */
    @Test
    void wallsAndEmptyInteractionsAreSafe() {
        GameEngine game = newGame();
        play(game, "w a nonsense take fight talk");
        game.execute("use herb");
        game.execute("use weapon");
        assertEquals(new Position(1, 1), game.player().position());
        assertEquals(10, game.player().health());
        assertEquals(3, game.player().attack());
        assertTrue(game.player().inventory().isEmpty());
        assertFalse(game.render().contains("#"));
        assertFalse(game.render().contains("Guardian"));
        play(game, "s s s s");
        assertEquals('.', MazeLoader.loadDefault().at(game.player().position()));
        game.execute("take");
        assertTrue(game.player().inventory().isEmpty());
    }

    /**
     * Verifies that reaching the exit without a key cannot win.
     *
     * <p>Follows a fixed route toward the exit and checks that the player remains on the preceding tile with the victory flag unset.</p>
     */
    @Test
    void exitRequiresKey() {
        GameEngine game = newGame();
        play(game, "d d s s d d d d d d w w");
        assertEquals(new Position(9, 2), game.player().position());
        assertFalse(game.won());
    }

    /**
     * Verifies a complete combat, reward, healing and escape route.
     *
     * <p>Checks counterattack damage, one-time drops, herb consumption, successful keyed escape and the rejection of movement after victory.</p>
     */
    @Test
    void combatRouteWinsAndHerbHeals() {
        GameEngine game = newGame();
        play(game, "d d s s d d d fight");
        assertEquals(8, game.player().health());
        assertFalse(game.player().has(Player.KEY));
        play(game, "fight fight talk");
        game.execute("answer clock");
        assertEquals(2, game.player().inventory().size());
        assertTrue(game.player().has(Player.HERB));
        game.execute("use herb");
        assertEquals(10, game.player().health());
        assertFalse(game.player().has(Player.HERB));
        play(game, "d d d w w");
        assertTrue(game.won());
        Position exit = game.player().position();
        game.execute("s");
        assertEquals(exit, game.player().position());
    }

    /**
     * Verifies the peaceful route through an NPC riddle to the exit.
     *
     * <p>Checks premature, empty and incorrect answers, case-insensitive success, unchanged health, one-time rewards, retention of an unneeded herb and eventual victory.</p>
     */
    @Test
    void riddleRouteWinsWithoutDamage() {
        GameEngine game = newGame();
        play(game, "d d s s d d d");
        game.execute("answer clock");
        assertTrue(game.player().inventory().isEmpty());
        game.execute("talk");
        game.execute("answer");
        game.execute("answer wrong");
        assertTrue(game.player().inventory().isEmpty());
        game.execute(" ANSWER   CLOCK ");
        assertEquals(10, game.player().health());
        assertEquals(2, game.player().inventory().size());
        game.execute("use herb");
        assertTrue(game.player().has(Player.HERB));
        play(game, "fight talk");
        game.execute("answer clock");
        assertEquals(2, game.player().inventory().size());
        play(game, "d d d w w");
        assertTrue(game.won());
    }

    /**
     * Verifies NPC-specific answers and the effect of equipping a reward weapon.
     *
     * <p>Ensures the wrong NPC answer grants nothing, the correct encounter grants a sword, repeated equipment does not stack attack, and the increased attack affects later combat.</p>
     */
    @Test
    void weaponDropIncreasesAttackOnce() {
        GameEngine game = newGame();
        play(game, "d d s s d d d talk d s s talk");
        game.execute("answer clock");
        assertTrue(game.player().inventory().isEmpty());
        game.execute("answer egg");
        assertTrue(game.player().has(Player.WEAPON));
        assertFalse(game.player().has(Player.KEY));
        assertEquals(3, game.player().attack());
        game.execute("use weapon");
        game.execute("equip sword");
        assertEquals(5, game.player().attack());
        play(game, "w w a");
        assertTrue(game.execute("fight").contains("NPC has 1 health"));
        assertEquals(8, game.player().health());
        game.execute("fight");
        assertTrue(game.player().has(Player.KEY));
    }

    /**
     * Verifies combat and rewards for the second configured NPC.
     *
     * <p>Checks its counterattack damage, the timing of its rewards, absence of the first NPC's key and healing after combat.</p>
     */
    @Test
    void secondNpcCombatUsesConfiguredStats() {
        GameEngine game = newGame();
        play(game, "d d s s d d d d s s fight fight");
        assertEquals(4, game.player().health());
        assertTrue(game.player().inventory().isEmpty());
        game.execute("fight");
        assertTrue(game.player().has(Player.WEAPON));
        assertFalse(game.player().has(Player.KEY));
        game.execute("use herb");
        assertEquals(8, game.player().health());
    }

    /**
     * Verifies that riddle answers cannot target an NPC remotely.
     *
     * <p>Offers a riddle, moves away, submits its answer and checks that nothing is awarded; also confirms quitting ends play without victory.</p>
     */
    @Test
    void answersRequireCurrentNpc() {
        GameEngine game = newGame();
        play(game, "d d s s d d d talk a");
        game.execute("answer clock");
        assertTrue(game.player().inventory().isEmpty());
        game.execute("q");
        assertTrue(game.finished());
        assertFalse(game.won());
    }
}
