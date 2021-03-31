package algorithms.mazeGenerators;
import java.util.*;

public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int col) {

        //Prim maze algorithm implementation

        Maze maze = new Maze(row, col);
        maze.setRandomPositoins();
        maze.setuniformmaze(1);

        ArrayList<Position> frontiers = new ArrayList<>();
        ArrayList<Position> neighbors = new ArrayList<>();

        maze.setcell(maze.getStartPosition(),0);
        maze.setcell(maze.getGoalPosition(),0);
        frontiers.addAll(maze.frontiers(maze.getStartPosition(),1));

        while (!frontiers.isEmpty()){
            Position randomPosition = randomPosition(frontiers);

            neighbors.clear();
            neighbors.addAll(maze.frontiers(randomPosition,0));

            // choose random position from neighbors array
            Position randomNeighbor = randomPositionNeighbor(neighbors);

            Position toset;

            //randomPosition and randomNeighbor in the same row
            if (randomPosition.getRowIndex() == randomNeighbor.getRowIndex())
            {
                toset = new Position(randomPosition.getRowIndex(),(randomPosition.getColumnIndex()+randomNeighbor.getColumnIndex())/2);
                maze.setcell(toset,0);
            }

            //randomPosition and randomNeighbor in the same column
            else
            {
                toset = new Position((randomPosition.getRowIndex()+randomNeighbor.getRowIndex())/2,randomPosition.getColumnIndex());
                maze.setcell(toset,0);
            }
            maze.setcell(randomPosition,0);
            frontiers.addAll(maze.frontiers(randomPosition,1));
            frontiers.remove(randomPosition);
/*
            // make sure there is no duplicate positions
            Set<Position> set = new HashSet<Position>(frontiers);
            frontiers.clear();
            frontiers.addAll(set);*/
        }
        maze.setMisgeretpathes(0);
        return maze;
    }


    public Position randomPosition(ArrayList<Position> arr)
    {
        Random rand = new Random();
        if (arr.size() == 0)
            return null;
        return arr.get(rand.nextInt(arr.size()));
    }
    public Position randomPositionNeighbor(ArrayList<Position> arr)
    {
        if (arr.size() == 0)
            return null;
        else if (arr.size() == 1)
            return arr.get(0);
        else if (arr.size() % 2 == 0)
            return arr.get(0);
        else if (arr.size() % 2 == 1)
            return arr.get(0);
        return null;
    }


}
