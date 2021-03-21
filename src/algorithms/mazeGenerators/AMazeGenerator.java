package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    private int rows;
    private int cols;

    public AMazeGenerator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    @Override
    public long measureAlgorithmTimeMillis(int row, int col) {

        return 0;
    }
}
