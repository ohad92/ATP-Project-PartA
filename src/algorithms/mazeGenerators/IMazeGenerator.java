package algorithms.mazeGenerators;

public interface IMazeGenerator {
    Maze generate(int row, int col);
    long measureAlgorithmTimeMillis(int row, int col);

}
