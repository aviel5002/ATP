package algorithms.mazeGenerators;

import java.io.Serializable;

/**
 * A class that represents a position in the maze
 *  @author aviel avitan,ron shakutai
 */
public class Position implements Serializable {
    int row;
    int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * gets the row index of this position
     * @return row index
     */
    public int getRowIndex() {
        return row;
    }
    /**
     * gets the column index of this position
     * @return column index
     */
    public int getColumnIndex() {
        return col;
    }

    /**
     * sets the row index of this position
     * @param row row number
     */
    public void setRow(int row) {
        this.row = row;
    }
    /**
     * sets the column index of this position
     * @param col column number
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * turns the position to a string
     * @return string of the position
     */
    public  String toString(){
        return "{"+this.row+","+this.col+"}";
    }
}
