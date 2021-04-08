package algorithms.mazeGenerators;

public class Position {
    private int rowindex;
    private int colindex;


    public Position(int rowindex, int colindex) {
        this.rowindex = rowindex;
        this.colindex = colindex;
    }

    public int getRowIndex() {
        return rowindex;
    }

    public void setRowIndex(int rowindex) {
        this.rowindex = rowindex;
    }

    public int getColumnIndex() {
        return colindex;
    }

    public void setColIndex(int colindex) {
        this.colindex = colindex;
    }

    @Override
    public String toString() {
        return "{" + rowindex + "," + colindex + '}';
    }

    @Override
    public boolean equals(Object obj) {
        Position p = (Position)obj;
        if (this.getRowIndex() == p.getRowIndex() && this.getColumnIndex() == p.getColumnIndex())
            return true;
        return false;
    }


}

