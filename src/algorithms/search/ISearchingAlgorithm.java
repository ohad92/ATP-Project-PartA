package algorithms.search;

public interface ISearchingAlgorithm {
    Solution solve(ISearchable domain);
    String getName();
    int getNumberOfNodesEvaluated();
}
