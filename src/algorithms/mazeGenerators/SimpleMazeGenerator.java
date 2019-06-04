package algorithms.mazeGenerators;

import java.util.Random;
/**
 * A maze generator that creates a simple maze
 *  @author aviel avitan,ron shakutai
 */
public class SimpleMazeGenerator extends AMazeGenerator{
    public SimpleMazeGenerator(){}
    /**
     * This functions gets the number of rows and columns wanted for the maze and then generates walls randomly after finding a solution
     * @param rows number of rows
     * @param columns number of columns
     * @return new maze
     *
     */
    @Override
    public Maze generate(int rows, int columns) {
        Maze newmaze=new Maze(rows,columns);
        Random r=new Random();
        for (int i = 0; i <rows ; i++) {
            for (int j = 0; j <columns ; j++) {
                newmaze.setValueToIndex(i,j,-1);
            }
        }
        int sr=newmaze.getStartPosition().getRowIndex(),sc=newmaze.getStartPosition().getColumnIndex(),gr=newmaze.getGoalPosition().getRowIndex(),gc=newmaze.getGoalPosition().getColumnIndex();

        newmaze.setValueToIndex(sr,sc,2);
        while(sr!=gr||sc!=gc){
            if(gr-sr<0)
                sr--;
            else if(gr-sr>0)
                sr++;
            else if(gc-sc<0)
                sc--;
            else if(gc-sc>0)
                sc++;
            newmaze.setValueToIndex(sr,sc,2);
        }
        for (int i = 0; i <rows ; i++) {
            for (int j = 0; j <columns ; j++) {
                if(newmaze.Getvalue(i,j)==-1)
                newmaze.setValueToIndex(i,j,r.nextInt(2));
                else if(newmaze.Getvalue(i,j)==2)
                    newmaze.setValueToIndex(i,j,0);
            }
        }
        return newmaze;
    }
}
