package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected Queue<AState> queue;
    private ArrayList<AState> visited;
    private ArrayList<AState> successors;

    public BreadthFirstSearch() {
        this.queue = new LinkedList<>();
        this.visited = new ArrayList<>();
        this.successors = new ArrayList<>();
    }

    @Override
    public Solution solve(ISearchable domain) {
        if (domain == null)
            return null;

        int cost;
        AState start = domain.getStartState();
        if (start == null)
            return new Solution(start);
        AState c; // like shown in the class
        queue.add(start);

        while (queue.size()>0){
            c = queue.poll();
            if (c.equals(domain.getGoalState()))
                return new Solution(c);

            successors = domain.getAllSuccessors(c);
            NumberOfNodesEvaluated++;
            for (int i=0; i<successors.size();i++){
                cost = c.getCost() + successors.get(i).getCost();
                successors.get(i).setCost(cost);
                if (!visited.contains(successors.get(i))){
                    successors.get(i).setCameFrom(c);
                    visited.add(successors.get(i));
                    queue.add(successors.get(i));
                }
            }
        }
        return (new Solution(successors));

    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }

}