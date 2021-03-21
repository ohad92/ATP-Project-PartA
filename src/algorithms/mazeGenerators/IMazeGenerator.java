package algorithms.mazeGenerators;

public interface IMazeGenerator {
    public Maze generatore(int row, int col);
    public long measureAlgorithmTimeMillis(int row, int col);
}
