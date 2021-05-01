package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.io.Serializable;

public class MazeState extends AState implements Serializable {
    private Position current;

    public MazeState(String state, int cost, Position current) {
        super(state, cost);
        this.current = current;
    }

    public MazeState(Position current) {
        super(null,0);
        this.current = current;
    }

    public MazeState(Position current, int cost) {
        super(null,cost);
        this.current = current;
    }

    public Position getCurrent() {
        return current;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        MazeState p = (MazeState) obj;
        return this.current.equals(p.getCurrent());
    }

    @Override
    public String toString() {
        return this.current.toString();
    }
}
