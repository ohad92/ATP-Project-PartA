package algorithms.mazeGenerators;

public interface IMazeGenerator {
    Maze generatore(int row, int col);
    long measureAlgorithmTimeMillis(int row, int col);

}
