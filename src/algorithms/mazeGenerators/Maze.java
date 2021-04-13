package algorithms.mazeGenerators;
import algorithms.search.AState;
import algorithms.search.MazeState;

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

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }


     //change a cell in the maze to wallORpass(0/1)

    public void setcell(Position p, int wallORpass){
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
            this.maze[i][cols-1] = type;
        }
        for (int i = 0; i < this.cols; i++) {
            this.maze[0][i] = type;
            this.maze[rows-1][i] = type;
        }

    }

//    set random start + end position
    public void setRandomPositoins(){
        Random rand = new Random();
        this.start = new Position(rand.nextInt(this.rows),0);

        int goalcol=1;
        if (this.start.getRowIndex() % 2 != 0){
            while (goalcol % 2 !=0)
                goalcol = rand.nextInt(this.rows-1);
        }
        this.goal = new Position(goalcol,this.cols-1);


    }
//  compute the opposite neighbors in distance 1 from a position, if it is a wall or path
    public ArrayList<Position> frontiers(Position p,int wallorpassage) {
        ArrayList<Position> Pfrontiers = new ArrayList<>();
        // check if there is neighbor above and it is a wall
        if (p.getRowIndex() > 0 && this.maze[p.getRowIndex() - 1][p.getColumnIndex()] == wallorpassage)
            Pfrontiers.add(new Position(p.getRowIndex() + 1, p.getColumnIndex()));

        // check if there is neighbor below and it is a wall
        if (p.getRowIndex() < this.rows - 1 && this.maze[p.getRowIndex() + 1][p.getColumnIndex()] == wallorpassage)
            Pfrontiers.add(new Position(p.getRowIndex() - 1, p.getColumnIndex()));

        // check if there is neighbor to the right and it is a wall
        if (p.getColumnIndex() < this.cols - 1 && this.maze[p.getRowIndex()][p.getColumnIndex() + 1] == wallorpassage)
            Pfrontiers.add(new Position(p.getRowIndex(), p.getColumnIndex() - 1));

        // check if there is neighbor to the left and it is a wall
        if (p.getColumnIndex() > 0 && this.maze[p.getRowIndex()][p.getColumnIndex() - 1] == wallorpassage)
            Pfrontiers.add(new Position(p.getRowIndex(), p.getColumnIndex() + 1));

        return Pfrontiers;
    }

    public void print(){
        for (int i=0; i<rows; i++){
            System.out.print("{");
            for (int j=0; j<cols; j++){
                System.out.print(" ");
                if ((i==this.start.getRowIndex()) && (j==this.start.getColumnIndex()))
                    System.out.print("S");

                else if ((i==this.goal.getRowIndex()) && (j==this.goal.getColumnIndex()))
                    System.out.print("E");

                else
                    System.out.print(maze[i][j]);
                System.out.print(" ");
            }
            System.out.print("}");
            System.out.println();
        }
    }


    // printing real maze to the screen
    public void printRealMaze(){
        for(int i = 0; i < maze.length; ++i) {
                for(int j = 0; j < maze[i].length; ++j) {

                    if ((i==start.getRowIndex()) && (j==start.getColumnIndex()))
                        System.out.print(" \u001b[41m ");

                    else if ((i==goal.getRowIndex()) && (j==goal.getColumnIndex()))
                        System.out.print(" \u001b[41m ");

                    else if (maze[i][j] == 1) {
                        System.out.print(" \u001b[45m ");
                    }
                    else if (maze[i][j] == 0) {
                        System.out.print(" \u001b[107m ");
                    }
                    else if (maze[i][j] == 2) {
                         System.out.print(" \u001b[42m ");
                    }

                    else{
                        System.out.print(" \u001b[42m ");
                    }
                }

                System.out.println(" \u001b[107m");
            }



    }
    public void printRealMazeWithSolution(ArrayList<AState> sol){
        for(int i = 0; i < sol.size(); ++i) {
                MazeState m = (MazeState)sol.get(i);
                int a = m.getCurrent().getRowIndex();
                int b = m.getCurrent().getColumnIndex();
                Position p = new Position(a,b);
                setcell(p,2);


            }
        printRealMaze();
        }

}

