package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected Queue<AState> queue;
    private HashSet<String> visited;
    private ArrayList<AState> successors;

    public BreadthFirstSearch() {
        this.queue = new LinkedList<>();
        this.visited = new HashSet<>();
        this.successors = new ArrayList<>();
    }

    /**
     * Solves a given domain by BFS algorithm
     * @param domain - an ISearchable object to search
     * @return solution for the maze
     */
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
        visited.add(start.toString());
        while (queue.size()>0){
            c = queue.poll();
            if (c.equals(domain.getGoalState()))
                return new Solution(c);

            successors = domain.getAllSuccessors(c);
            NumberOfNodesEvaluated++;
            for (int i=0; i<successors.size();i++){
                cost = c.getCost() + successors.get(i).getCost();
                successors.get(i).setCost(cost);
                if (!visited.contains(successors.get(i).toString())){
                    successors.get(i).setCameFrom(c);
                    visited.add(successors.get(i).toString());
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