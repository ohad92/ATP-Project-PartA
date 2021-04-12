package algorithms.search;

import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    private BestFirstSearch best = new BestFirstSearch();
    private EmptyMazeGenerator emptyGene = new EmptyMazeGenerator();
    private MyMazeGenerator mazeGene = new MyMazeGenerator();
    private IMazeGenerator mg = new MyMazeGenerator();
    private ISearchable searchable;
    private Maze maze;
    private Solution solution;


    @Test
    void getName() {
        assertEquals("BestFirstSearch",best.getName());
    }

    @Test
    void nullTest() {
        assertEquals(null,best.solve(null));
    }

    @Test
    public void solve1500() throws Exception {
        for(int i=0 ; i<10 ; i++) {
            maze = mazeGene.generate(1500, 1500);
            searchable = new SearchableMaze(maze);
            solution = best.solve(searchable);
            assertTrue(solution.getSolutionPath().size() > 0);
        }
    }
    @Test
    void solve1000() throws Exception {
        Maze maze = mg.generate(1000, 1000);
        ISearchable domain = new SearchableMaze(maze);
        Solution sol = best.solve(domain);
        assertNotNull(sol);
    }
    @Test
    void solve4() throws Exception {
        Maze maze = mg.generate(4, 4);
        ISearchable domain = new SearchableMaze(maze);
        Solution sol = best.solve(domain);
        assertNotNull(sol);
    }
    @Test
    void getTime() throws Exception {
        long first = System.currentTimeMillis();
        Maze maze = mg.generate(1000,1000);
        ISearchable domain = new SearchableMaze(maze);
        best.solve(domain);
        long second = System.currentTimeMillis();
        assertTrue((second-first)/1000<60);
    }

    @Test
    void getNumberOfNodesEvaluated() throws Exception {
        Maze maze = mg.generate(1000, 1000);
        ISearchable domain = null;
        best.solve(domain);
        assertEquals(0, best.getNumberOfNodesEvaluated());
    }
}