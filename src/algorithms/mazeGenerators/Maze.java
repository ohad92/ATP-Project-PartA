package algorithms.mazeGenerators;
import java.util.ArrayList;
import java.util.Random;

public class Maze {

    protected int rows;
    protected int cols;
    private int[][] maze;
    private Position start;
    private Position goal;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.maze = new int[rows][cols];
        this.start = new Position(0,0);
        this.goal = new Position(rows-1,cols-1);
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public Position getStartPosition() {
        return start;
    }

    public Position getGoalPosition() {
        return goal;
    }

    public void setcell(Position p,int wallORpass){
       this.maze[p.getRowIndex()][p.getColumnIndex()] =  wallORpass;
    }

    // set maze only with walls or pathes
    public void setuniformmaze(int type){
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.maze[i][j] = type;
            }
        }
    }

    //set the frame of the maze all pathes or walls
    public void setMisgeretpathes(int type) {
        for (int i = 0; i < this.rows; i++) {
            this.maze[i][0] = type;
            this.maze[cols-1][i] = type;
        }
        for (int i = 0; i < this.cols; i++) {
            this.maze[0][i] = type;
            this.maze[rows-1][i] = type;
        }

    }


//    set random start + end position
    public void setRandomPositoins(){
        Random rand = new Random();
        this.start = new Position(0, rand.nextInt(this.cols));
        this.goal = new Position(rows-1,rand.nextInt(this.cols));

    }
//  compute the neighbors in distance 2 from a position, if it is a wall or path
    public ArrayList<Position> frontiers(Position p,int wallorpassage) {
        ArrayList<Position> Pfrontiers = new ArrayList<>();
        // check if there is neighbor above and it is a wall
        if (p.getRowIndex() > 1 && this.maze[p.getRowIndex() - 2][p.getColumnIndex()] == wallorpassage)
            Pfrontiers.add(new Position(p.getRowIndex() - 2, p.getColumnIndex()));

        // check if there is neighbor below and it is a wall
        if (p.getRowIndex() < this.rows - 2 && this.maze[p.getRowIndex() + 2][p.getColumnIndex()] == wallorpassage)
            Pfrontiers.add(new Position(p.getRowIndex() + 2, p.getColumnIndex()));

        // check if there is neighbor to the right and it is a wall
        if (p.getColumnIndex() < this.cols - 2 && this.maze[p.getRowIndex()][p.getColumnIndex() + 2] == wallorpassage)
            Pfrontiers.add(new Position(p.getRowIndex(), p.getColumnIndex() + 2));

        // check if there is neighbor to the left and it is a wall
        if (p.getColumnIndex() > 1 && this.maze[p.getRowIndex()][p.getColumnIndex() - 2] == wallorpassage)
            Pfrontiers.add(new Position(p.getRowIndex(), p.getColumnIndex() - 2));

        return Pfrontiers;
    }

    public void print(){
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                if ((i==this.start.getRowIndex()) && (j==this.start.getColumnIndex()))
                    System.out.print("S");

                else if ((i==this.goal.getRowIndex()) && (j==this.goal.getColumnIndex()))
                    System.out.print("E");

                else
                    System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
}

