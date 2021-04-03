package algorithms.search;
import algorithms.mazeGenerators.Position;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    private Queue<AState> queue;
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
            return solutionPath(start);
        AState c; // like shown in the class
        queue.add(start);

        while (queue.size()>0){
            c = queue.poll();
            if (!visited.contains(c)) {
                NumberOfNodesEvaluated++;
                visited.add(c);
                if (c.equals(domain.getGoalState()))
                    return solutionPath(c);


                successors = domain.getAllSuccessors(c);
                for (int i=0; i<successors.size();i++){
                    if (!visited.contains(successors.get(i))){
                        queue.add(successors.get(i));
                        successors.get(i).setCameFrom(c);
                        cost = c.getCost() + successors.get(i).getCost();
                        successors.get(i).setCost(cost);
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
    public String getName() {
        return "BreadthFirstSearch";
    }

    public Solution solve2(ISearchable domain) {
        return null;
    }
}

