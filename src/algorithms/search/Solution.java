package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    ArrayList<AState> solution;

    public Solution(ArrayList<AState> solution) {
        this.solution = solution;
    }

    public Solution(AState position) {this.solution = solutionPath(position);}


    public ArrayList<AState> solutionPath(AState position){
        ArrayList<AState> sol = new ArrayList<>();
        while (position.getCameFrom() != null){
            sol.add(position);
            position = position.getCameFrom();
        }
        sol.add(position); // the start position
        //now the arraylist is from the goal position. reverse it so the start position is at index 0
        Collections.reverse(sol);

        return sol;
    }

    public ArrayList<AState> getSolutionPath() {
        return this.solution;
    }
}
