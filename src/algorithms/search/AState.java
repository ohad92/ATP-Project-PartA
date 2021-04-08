package algorithms.search;

public abstract class AState {
    private String state;
    private int cost;
    private AState cameFrom;

    public AState(String state, int cost) {
        this.state = state;
        this.cost = cost;
        this.cameFrom = null;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

// Integer.compare return -1 if A<B, 0 if A==B, 1 if A>B
    public int CompereTowAstates(AState other) {
        return Integer.compare(this.cost, other.cost);
    }
}
