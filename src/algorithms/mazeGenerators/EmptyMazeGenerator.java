package algorithms.mazeGenerators;

/**
 * A class that generates empty mazes
 *  @author aviel avitan,ron shakutai
 */

public class EmptyMazeGenerator extends AMazeGenerator{
    public EmptyMazeGenerator() {
    }
    /**
     * This functions gets the number of rows and columns wanted for the maze and then genrerates an empty maze with no walls
     * @param rows number of rows
     * @param columns number of columns
     * @return new maze
     *
     */

    @Override
    public Maze generate(int rows, int columns) {
        if(rows<0||columns<0)
            return null;
        Maze newmaze=new Maze(rows,columns);
        Position start=newmaze.generatePoint(rows,columns);
        Position goal=newmaze.generatePoint(rows,columns);
        while(start.getRowIndex()==goal.getColumnIndex()&&start.getColumnIndex()==goal.getColumnIndex())
            goal=newmaze.generatePoint(rows,columns);
        for (int i = 0; i <rows ; i++) {
            for (int j = 0; j <columns ; j++) {
                newmaze.setValueToIndex(i,j,0);
            }
        }
        newmaze.setBegin(start);
        newmaze.setGoal(goal);
        return newmaze;
    }
}
