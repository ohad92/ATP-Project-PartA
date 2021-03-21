package algorithms.mazeGenerators;

public class Maze {

    private int rows;
    private int cols;
    private int[][] maze;

    public Maze(int rows, int cols, int[][] maze) {
        this.rows = rows;
        this.cols = cols;
        this.maze = maze;
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }
}

