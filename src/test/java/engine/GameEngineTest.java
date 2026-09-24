package engine;

import config.MazeLoader;
import model.Player;
import model.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Behaviour checks for NPC combat, riddles, and drops. */
class GameEngineTest {
    /** @return fresh game */
    private GameEngine newGame() { return new GameEngine(MazeLoader.loadDefault()); }

    /** Executes movement or single-word commands.
     * @param game game under test
     * @param commands space-separated commands
     */
    private void play(GameEngine game, String commands) {
        for (String command : commands.split(" ")) { game.execute(command); }
    }

    /** Both wall types block movement and missing items cannot be used. */
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

    /** The exit is locked until a key is obtained. */
    @Test
    void exitRequiresKey() {
        GameEngine game = newGame();
        play(game, "d d s s d d d d d d w w");
        assertEquals(new Position(9, 2), game.player().position());
        assertFalse(game.won());
    }

    /** Combat awards drops once, herbs heal, and the key permits escape. */
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

    /** Wrong answers give nothing; solving gives the same drops as combat. */
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

    /** Each NPC has its own riddle; a weapon changes actual combat damage. */
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

    /** The second NPC's configured health and attack govern its combat. */
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

    /** Leaving an NPC prevents remote answers; quit ends play. */
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
