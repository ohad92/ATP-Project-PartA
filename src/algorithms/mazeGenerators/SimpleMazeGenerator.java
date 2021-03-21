package algorithms.mazeGenerators;
import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{

    public SimpleMazeGenerator(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public Maze generatore(int row, int col) {
        Random r = new Random();
        int[][] randonMaze = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                randonMaze[i][j] = r.nextInt(1);
            }
        }
        for (int i = 0; i < col; i++) {
            randonMaze[0][i] = 1;
        }
        for (int i = row; i < col; i++) {
            randonMaze[row][i] = 1;
        }
        for (int i = 0; i < row; i++) {
            randonMaze[0][i] = 1;
        }
        for (int i = col; i < row; i++) {
            randonMaze[col][i] = 1;
        }
        Maze maze = new Maze(row,col,randonMaze);
        return maze;
    }
}
