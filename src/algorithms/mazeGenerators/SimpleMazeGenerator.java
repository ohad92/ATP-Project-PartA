package algorithms.mazeGenerators;
import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{

    public SimpleMazeGenerator() {
        super();
    }

    @Override
    public Maze generate(int row, int col) {
        Random r = new Random();
        int[][] randonMaze = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                randonMaze[i][j] = r.nextInt(2);
            }
        }
        for (int i = 0; i < col; i++) {
            randonMaze[0][i] = 0;
        }
        for (int i = 0; i < col; i++) {
            randonMaze[row-1][i] = 0;
        }
        for (int i = 0; i < row; i++) {
            randonMaze[i][0] = 0;
        }
        for (int i = 0; i < row; i++) {
            randonMaze[i][col-1] = 0;
        }
        Maze maze = new Maze(row,col);
        maze.setMaze(randonMaze);
        maze.setRandomPositoins();
        return maze;

    }
}
