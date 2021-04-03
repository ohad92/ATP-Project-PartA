package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected int NumberOfNodesEvaluated = 0;

    @Override
    public int getNumberOfNodesEvaluated() {
        return this.NumberOfNodesEvaluated;
    }
}
