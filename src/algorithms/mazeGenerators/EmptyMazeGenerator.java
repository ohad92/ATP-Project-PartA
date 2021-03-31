package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{
    public EmptyMazeGenerator() {
        super();
    }

    @Override
    public Maze generate(int row, int col) {

        Maze maze = new Maze(row,col);
        maze.setuniformmaze(0);

        return maze;
    }
}
