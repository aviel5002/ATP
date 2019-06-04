package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;
/**
 * A class that generates a maze using prim algorithm
 *  @author aviel avitan,ron shakutai
 */
public class MyMazeGenerator extends AMazeGenerator {


    /**
     * A function that generates a maze using prim algorithm
     * @param rows number of rows for current maze
     * @param columns number of columns for current maze
     * @return new maze
     */
    public Maze generate(int rows, int columns) {
        Maze maze = new Maze(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze.setValueToIndex(i, j, 1);
            }
        }
        build(maze, maze.getStartPosition());
        Position goal = new Position(maze.getStartPosition().getRowIndex(), maze.getStartPosition().getColumnIndex());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (distance(maze.getStartPosition(), i, j) > distance(maze.getStartPosition(), goal.getRowIndex(), goal.getColumnIndex()) && maze.Getvalue(i, j) == 0 && getPathNum(maze, i, j) == 1) {
                    goal.setRow(i);
                    goal.setCol(j);
                }
            }
        }
        maze.setGoal(goal);
        return maze;
    }

    /**
     * builds the maze by breaking walls until every point in the maze is reachable
     * @param maze the current maze
     * @param curr the starting position
     */

    public void build (Maze maze, Position curr) {
        if(maze==null||curr==null)
            return;
        Random r=new Random();
        maze.setValueToIndex(curr.getRowIndex(),curr.getColumnIndex(),0);
        ArrayList<Position> walls=new ArrayList<>();
        walls.addAll(getNeighbor(curr,maze));
        Position add=curr;
        while(!walls.isEmpty()){
            Position prev=add;
            int count=r.nextInt(walls.size());
            add=walls.remove(count);
            walls.addAll(getNeighbor(add,maze));
        }
        }


    /**
     * computes the distance between the starting position to get the goal position as far as possible
     * @param start starting position of the maze
     * @param row the row number of the position we check
     * @param col the column number of the position we check
     * @return distance between the positions
     */

    public Double distance(Position start, int row, int col) {
        return Math.sqrt(Math.pow(row - start.getRowIndex(), 2) + (Math.pow(col - start.getColumnIndex(), 2)));
    }

    /**
     * checks how many neighbors a cell have
     * @param maze current maze
     * @param currRow row number for the position we check
     * @param currCol column number for the position we check
     * @return number of neighbors
     */
    public int getPathNum(Maze maze, int currRow, int currCol) {
        int rows = maze.getRows();
        int cols = maze.getCols();
        int count = 0;
        if (currRow + 1 < rows)
            if (maze.Getvalue(currRow + 1, currCol) == 0)
                count++;
        if (currRow - 1 > 0)
            if (maze.Getvalue(currRow - 1, currCol) == 0)
                count++;
        if (currCol + 1 < cols)
            if (maze.Getvalue(currRow, currCol + 1) == 0)
                count++;
        if (currCol - 1 > 0)
            if (maze.Getvalue(currRow, currCol - 1) == 0)
                count++;
        return count;
    }

    /**
     * returns the neighbors of a given position
     * @param p the position we check
     * @param m the current maze
     * @return arraylist of the positions
     */
    public ArrayList<Position> getNeighbor(Position p, Maze m) {
        int r = p.getRowIndex();
        int c = p.getColumnIndex();
        int cborder = m.getCols();
        int rborder = m.getRows();
        int count = 0;
        ArrayList<Position> ans=new ArrayList<>();
        if(r+2<rborder) {
            if (m.Getvalue(r + 1, c) == 1) {
                if (m.Getvalue(r + 2, c) == 1) {
                    ans.add(new Position(r + 2, c));
                    m.setValueToIndex(r+1,c,0);
                    m.setValueToIndex(r+2,c,0);
                }
            }
        }
        if(r-2>=0) {
            if (m.Getvalue(r -1, c) == 1) {
                if (m.Getvalue(r - 2, c) == 1) {
                   ans.add( new Position(r - 2, c));
                    m.setValueToIndex(r-1,c,0);
                    m.setValueToIndex(r-2,c,0);
                }
            }
        }
        if(c+2<cborder) {
            if (m.Getvalue(r, c + 1) == 1) {
                if (m.Getvalue(r, c + 2) == 1) {
                  ans.add(new Position(r, c + 2));
                    m.setValueToIndex(r,c+1,0);
                    m.setValueToIndex(r,c+2,0);
                }
            }
        }
        if(c-2>=0) {
            if (m.Getvalue(r, c - 1) == 1) {
                if (m.Getvalue(r, c - 2) == 1) {
                    ans.add( new Position(r, c - 2));
                    m.setValueToIndex(r,c-1,0);
                    m.setValueToIndex(r,c-2,0);
                }
            }
        }
        return ans;

    }
}


