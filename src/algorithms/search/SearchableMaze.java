package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    private MazeState start;
    private MazeState goal;
    private Maze maze;

    public SearchableMaze(MazeState start, MazeState goal, Maze maze) {
        this.start = start;
        this.goal = goal;
        this.maze = maze;
    }


    public SearchableMaze(Maze maze) {
        this.maze = maze;
        MazeState startstate = new MazeState(maze.getStartPosition());
        this.start = startstate;

        MazeState goalstate = new MazeState(maze.getGoalPosition());
        this.goal = goalstate;
    }

    @Override
    public AState getStartState() {
        return this.start;
    }

    @Override
    public AState getGoalState() {
        return this.goal;
    }

    /**
     *
     * @param s State
     * @return all the possible states a maze state can go to
     */

    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        if (s == null)
            return null;

        ArrayList<AState> allseccessors = new ArrayList<>();
        MazeState mazeState = (MazeState) s;
        Position p = mazeState.getCurrent();

        int prow = p.getRowIndex();
        int pcol = p.getColumnIndex();

        MazeState legal;

        //start check states from above, clockwise

        if (prow > 0 && this.maze.getMaze()[prow - 1][pcol] == 0) {
            legal = new MazeState((new Position(prow - 1, pcol)), 10);
            allseccessors.add(legal);
        }

        if (prow > 0 && pcol < this.maze.getCols() - 1 && this.maze.getMaze()[prow - 1][pcol + 1] == 0) {
            legal = new MazeState((new Position(prow - 1, pcol + 1)), 15);
            allseccessors.add(legal);
        }

        if (pcol < this.maze.getCols() - 1 && this.maze.getMaze()[prow][pcol + 1] == 0) {
            legal = new MazeState((new Position(prow, pcol + 1)), 10);
            allseccessors.add(legal);
        }

        if (pcol < this.maze.getCols() - 1 && prow < this.maze.getRows() - 1 && this.maze.getMaze()[prow + 1][pcol + 1] == 0) {
            legal = new MazeState((new Position(prow + 1, pcol + 1)), 15);
            allseccessors.add(legal);
        }

        if (prow < this.maze.getRows() - 1 && this.maze.getMaze()[prow + 1][pcol] == 0) {
            legal = new MazeState((new Position(prow + 1, pcol)), 10);
            allseccessors.add(legal);
        }

        if (pcol > 0 && prow < this.maze.getRows() - 1 && this.maze.getMaze()[prow + 1][pcol - 1] == 0) {
            legal = new MazeState((new Position(prow + 1, pcol - 1)), 15);
            allseccessors.add(legal);
        }

        if (pcol > 0 && this.maze.getMaze()[prow][pcol - 1] == 0) {
            legal = new MazeState((new Position(prow, pcol - 1)), 10);
            allseccessors.add(legal);
        }

        if (pcol > 0 && prow > 0 && this.maze.getMaze()[prow - 1][pcol - 1] == 0) {
            legal = new MazeState((new Position(prow - 1, pcol - 1)), 15);
            allseccessors.add(legal);
        }

        return allseccessors;
    }



}
