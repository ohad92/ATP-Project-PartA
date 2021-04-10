package algorithms.search;
import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm{
    private Stack<AState> stack;
    private HashSet<String> visited;
    private ArrayList<AState> successors;

    public DepthFirstSearch() {
        this.stack = new Stack<>();
        this.visited = new HashSet<>();
        this.successors = new ArrayList<>();;
    }

    @Override
    public Solution solve(ISearchable domain) {
        if (domain == null)
            return null;

        AState start = domain.getStartState();
        AState c;
        stack.push(start);
        while (!stack.isEmpty()) {
            c = stack.pop();
            if (!this.visited.contains(c.toString())) {
                NumberOfNodesEvaluated++;
                visited.add(c.toString());
                if (c.equals(domain.getGoalState()))
                    return new Solution(c);

                successors = domain.getAllSuccessors(c);
                for (int i = 0; i < successors.size(); i++) {
                    if (!visited.contains(successors.get(i).toString())){
                        stack.push(successors.get(i));
                        successors.get(i).setCameFrom(c);
                    }
                }
            }

        }
        return (new Solution(successors));
    }

    @Override
    public String getName() {
        return "DeapthFirstSearch";
    }
}


