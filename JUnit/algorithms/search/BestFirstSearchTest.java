package algorithms.search;

import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    private BestFirstSearch best = new BestFirstSearch();

    @Test
    void getName() {
        assertEquals("BestFirstSearch",best.getName());
    }

    @Test
    void nullTest() {
        assertEquals(null,best.solve(null));
    }

}