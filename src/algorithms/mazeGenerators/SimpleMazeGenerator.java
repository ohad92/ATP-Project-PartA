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
        Maze maze = new Maze(row,col,randonMaze);



        return maze;
    }
}
