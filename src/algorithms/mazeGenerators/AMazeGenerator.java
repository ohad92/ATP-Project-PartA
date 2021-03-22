package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    public AMazeGenerator() {
    }

    @Override
    public long measureAlgorithmTimeMillis(int row, int col) {
        long start;
        long end;
        start=System.currentTimeMillis();
        generate(row,col);
        end=System.currentTimeMillis();

        return end-start;
    }
}
