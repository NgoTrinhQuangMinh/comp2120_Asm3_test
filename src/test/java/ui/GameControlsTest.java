package ui;

import config.MazeLoader;
import engine.GameEngine;
import model.Player;
import model.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Verifies that immediate controls and answer typing stay separate. */
class GameControlsTest {
    /** Movement needs no Enter, and arrows work like WASD. */
    @Test
    void immediateMovementAndInventory() {
        GameEngine game = new GameEngine(MazeLoader.loadDefault());
        GameControls controls = new GameControls(game);
        controls.handle("RIGHT");
        assertEquals(new Position(2, 1), game.player().position());
        controls.handle("D");
        assertEquals(new Position(3, 1), game.player().position());
        controls.handle("i");
        assertTrue(controls.inventoryVisible());
        controls.handle("I");
        assertFalse(controls.inventoryVisible());
    }

    /** Typing, deleting, and submitting answers cannot trigger action keys. */
    @Test
    void riddleTypingDoesNotMoveOrQuit() {
        GameEngine game = new GameEngine(MazeLoader.loadDefault());
        GameControls controls = new GameControls(game);
        for (String key : new String[] {"d", "d", "s", "s", "d", "d", "d", "t"}) { controls.handle(key); }
        assertTrue(controls.answering());
        Position position = game.player().position();
        for (char key : "wasdfq".toCharArray()) { controls.handle(String.valueOf(key)); }
        controls.handle("UP");
        assertEquals(position, game.player().position());
        assertEquals(10, game.player().health());
        assertFalse(game.finished());
        controls.handle("\r");
        assertTrue(controls.answering());
        for (char key : "clockx".toCharArray()) { controls.handle(String.valueOf(key)); }
        controls.handle("\u007f");
        assertEquals("clock", controls.answer());
        controls.handle("\r");
        assertFalse(controls.answering());
        assertTrue(game.player().has(Player.KEY));
    }

    /** Cancellation returns to combat; item shortcuts apply their effects. */
    @Test
    void cancelFightHealAndEquip() {
        GameEngine game = new GameEngine(MazeLoader.loadDefault());
        GameControls controls = new GameControls(game);
        controls.handle("t");
        assertFalse(controls.answering());
        for (String key : new String[] {"d", "d", "s", "s", "d", "d", "d", "t", "\u001b", "f", "f"}) { controls.handle(key); }
        assertFalse(controls.answering());
        assertEquals(8, game.player().health());
        controls.handle("h");
        assertEquals(10, game.player().health());
        game.player().collect(Player.WEAPON);
        controls.handle("e");
        assertEquals(5, game.player().attack());
        controls.handle("q");
        assertTrue(game.finished());
    }
}
