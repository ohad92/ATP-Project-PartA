package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{
    public EmptyMazeGenerator(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public Maze generatore(int row, int col) {
        int[][] emptymaze = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                emptymaze[i][j] = 0;
            }
        }
        Maze maze = new Maze(row,col,emptymaze);
        return maze;
    }
}
