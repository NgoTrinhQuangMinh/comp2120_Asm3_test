package config;

import java.util.List;
import model.Maze;
import model.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Validates the level resource and invalid map handling. */
class MazeLoaderTest {
    /** The shipped map contains the required markers. */
    @Test
    void loadsBundledMaze() {
        Maze maze = MazeLoader.loadDefault();
        assertEquals(new Position(1, 1), maze.find('P'));
        assertEquals('1', maze.at(maze.find('1')));
        assertTrue(maze.isWall(new Position(-1, 0)));
        assertTrue(maze.isWall(new Position(maze.width(), maze.height())));
        var npcs = NpcLoader.loadDefault(maze);
        assertEquals(2, npcs.size());
        assertEquals(6, npcs.get(0).health());
        assertEquals(2, npcs.get(0).attack());
        assertEquals("What has hands but cannot clap?", npcs.get(0).offerRiddle());
        assertTrue(npcs.get(0).accepts("clock"));
    }

    /** Rejects maps that cannot support the skeleton's rules. */
    @Test
    void rejectsMalformedMaps() {
        assertThrows(IllegalArgumentException.class, () -> new Maze(List.of()));
        assertThrows(IllegalArgumentException.class, () -> new Maze(List.of("P1.X", "|")));
        assertThrows(IllegalArgumentException.class, () -> new Maze(List.of("P1?X")));
        assertThrows(IllegalArgumentException.class, () -> new Maze(List.of("P1.")));
        assertThrows(IllegalArgumentException.class, () -> new Maze(List.of("PP1X")));
        assertThrows(IllegalArgumentException.class, () -> new Maze(List.of("P11X")));
    }
}
