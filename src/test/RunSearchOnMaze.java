package test;
import algorithms.mazeGenerators.*;
import algorithms.search.*;
import java.util.ArrayList;



public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(50, 90);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        //solveProblem(searchableMaze, new BreadthFirstSearch());
        //solveProblemWithMaze(searchableMaze, new BreadthFirstSearch(),maze);
        //solveProblem(searchableMaze, new DepthFirstSearch());
        //solveProblemWithMaze(searchableMaze, new DepthFirstSearch(),maze);
        //solveProblem(searchableMaze, new BestFirstSearch());
        solveProblemWithMaze(searchableMaze, new BestFirstSearch(),maze);

    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm  searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated:%s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();

        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s.%s",i,solutionPath.get(i)));
        }
    }

    private static void solveProblemWithMaze(ISearchable domain, ISearchingAlgorithm  searcher, Maze m) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated:%s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        m.printRealMazeWithSolution(solutionPath);
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s.%s",i,solutionPath.get(i)));
        }
    }
}

