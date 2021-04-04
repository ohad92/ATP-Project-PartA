package algorithms.search;
import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm{
    private Stack<AState> stack;
    private ArrayList<AState> visited;
    private ArrayList<AState> successors;

    public DepthFirstSearch() {
        this.stack = new Stack<>();
        this.visited = new ArrayList<>();;
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
            if (!this.visited.contains(c)) {
                NumberOfNodesEvaluated++;
                visited.add(c);
                if (c.equals(domain.getGoalState()))
                    return solutionPath(c);

                successors = domain.getAllSuccessors(c);
                for (int i = 0; i < successors.size(); i++) {
                    if (!visited.contains(successors.get(i))){
                        stack.push(successors.get(i));
                        successors.get(i).setCameFrom(c);
                    }
                }
            }

        }
        return (new Solution(successors));
    }

        public Solution solutionPath(AState position){
            ArrayList<AState> sol = new ArrayList<>();
            while (position.getCameFrom() != null){
                sol.add(position);
                position = position.getCameFrom();
            }
            sol.add(position); // the start position
            //now the arraylist is from the goal position. reverse it so the start position is at index 0
            Collections.reverse(sol);

            Solution solution = new Solution(sol);
            return solution;
        }


    @Override
    public String getName() {return "DeapthFirstSearch";  }
}
