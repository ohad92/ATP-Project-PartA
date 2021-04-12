

package algorithms.mazeGenerators;
import java.util.*;

public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int col) {
        //Prim maze algorithm implementation

        Maze maze = new Maze(row, col);

        if(row == 2 && col == 2){
            IMazeGenerator mazeGenerator = new SimpleMazeGenerator();
            maze = mazeGenerator.generate(row,col);
            return maze;
        }
        maze.setRandomPositoins();
        maze.setuniformmaze(1);

        maze.setcell(maze.getStartPosition(), 0);
        maze.setcell(maze.getGoalPosition(),0);

        ArrayList<Position> frontiers = getNeighbors(maze.getStartPosition(),maze);
        ArrayList<Position> unVisited = new ArrayList<>();

        while (!frontiers.isEmpty()) {
            Position randomPosition = randomPosition(frontiers);
            unVisited.clear();
            unVisited = maze.frontiers(randomPosition,0);
            Position A,B;
            if (unVisited.size() == 1) {
                A = new Position(randomPosition.getRowIndex(), randomPosition.getColumnIndex());
                maze.setcell(A, 0);

                A = unVisited.get(0);
                if ((A.getRowIndex() >= 0) && (A.getRowIndex() < row) && (A.getColumnIndex() >= 0) && (A.getColumnIndex() < col)) {
                    B = new Position(A.getRowIndex(), A.getColumnIndex());
                    maze.setcell(B, 0);
                    frontiers.addAll(getNeighbors(A,maze));
                }
            }
            frontiers.remove(randomPosition);
        }

        return maze;
    }

    public Position randomPosition(ArrayList<Position> arr) {
        Random rand = new Random();
        if (arr.size() == 0)
            return null;
        return arr.get(rand.nextInt(arr.size()));
    }

    //  compute the neighbors of a position
    public ArrayList<Position> getNeighbors(Position p, Maze maze) {
        ArrayList<Position> Neighbors = new ArrayList<>();

        // check if there is neighbor above
        if (p.getRowIndex() > 0)
            Neighbors.add(new Position(p.getRowIndex() - 1, p.getColumnIndex()));

        // check if there is neighbor below
        if (p.getRowIndex() < maze.getRows() - 1)
            Neighbors.add(new Position(p.getRowIndex() + 1, p.getColumnIndex()));

        // check if there is neighbor to the right
        if (p.getColumnIndex() < maze.getCols() - 1)
            Neighbors.add(new Position(p.getRowIndex(), p.getColumnIndex() + 1));

        // check if there is neighbor to the left
        if (p.getColumnIndex() > 0 )
            Neighbors.add(new Position(p.getRowIndex(), p.getColumnIndex() - 1));

        return Neighbors;
    }
}