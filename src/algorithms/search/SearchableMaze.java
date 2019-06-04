package algorithms.search;

        import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

/**
 * A class that represents a searchable maze
 *  @author aviel avitan,ron shakutai
 */
public class SearchableMaze implements ISearchable {
    Maze m;
    public SearchableMaze(int rows, int cols) {
        m=new Maze(rows,cols);
    }
    public SearchableMaze(Maze m){
       this.m=m;
    }

    public AState getStartState(){
    return new MazeState (m.getStartPosition().toString(),0,null);
    }

    public AState getGoalState(){
        return new MazeState(m.getGoalPosition().toString(),0,null);
    }

    /**
     * returns a list of all of the successors of a given state
     * @param statee the state that you return the successors of
     * @return a list of the successors
     */
    public ArrayList<AState> getAllSuccessors(AState statee){
        if(statee==null)
            return null;
        MazeState state =new MazeState(toPosition(statee.getState()));
        ArrayList<AState> res= new ArrayList<AState> ();
        Position[] p= PositionOptions(state);
        for (int i = 0; i <8 ; i++) {
            if(p[i]!=null){
                if(p[i].getRowIndex()!=state.getP().getRowIndex() && p[i].getColumnIndex()!=state.getP().getColumnIndex()){// need to find away to check that both cordinates is diffrent from the curr
                    res.add(new MazeState(p[i].toString(),15,state));
                }else {res.add(new MazeState(p[i].toString(),10,state));}
            }
        }
        return res;

    }

    /**
     * returns an array of the positions you can go to
     * @param Camefrom the state you came from
     * @return array of positions
     */
    public Position [] PositionOptions(MazeState Camefrom) {
        Position p = Camefrom.getP(); // return all the successors include the crossers succcesors
        int r = p.getRowIndex();
        int c = p.getColumnIndex();
        int cborder = m.getCols();
        int rborder = m.getRows();
        int count = 0;
        Position[] MyOptions = new Position[8];
        for (int i = 0; i < 8; i++) {
            MyOptions[i] = null;
        }
        if (r + 1 < rborder) {
            if (m.Getvalue(r + 1, c) == 0) {
                MyOptions[count] = new Position(r + 1, c);
                count++;
            }
                if (c - 1 >= 0) {
                    if (m.Getvalue(r + 1, c - 1) == 0) {
                        MyOptions[count] = new Position(r + 1, c - 1);
                        count++;
                    }
                }
                if (c + 1 <cborder) {
                    if (m.Getvalue(r + 1, c + 1) == 0) {
                        MyOptions[count] = new Position(r + 1, c + 1);
                        count++;
                    }
                }

        }
        if (r - 1 >= 0) {
            if (m.Getvalue(r - 1, c) == 0) {
                MyOptions[count] = new Position(r - 1, c);
                count++;
            }
                if (c - 1 >= 0) {
                    if (m.Getvalue(r - 1, c - 1) == 0) {
                        MyOptions[count] = new Position(r - 1, c - 1);
                        count++;
                    }
                }
                if (c + 1 < cborder) {
                    if (m.Getvalue(r - 1, c + 1) == 0) {
                        MyOptions[count] = new Position(r - 1, c + 1);
                        count++;
                    }
                }

        }
        if (c + 1 < cborder) {
            if (m.Getvalue(r, c + 1) == 0) {
                MyOptions[count] = new Position(r, c + 1);
                count++;
            }
        }
        if (c - 1 >= 0) {
            if (m.Getvalue(r, c - 1) == 0) {
                MyOptions[count] = new Position(r, c - 1);
                count++;
            }
        }
        return MyOptions;

    }

    /**
     * converts string to a position by parsing
     * @param pos the string that represents the position
     * @return new position
     */
    public Position toPosition(String pos){
        String[] parts=pos.split(",");
        String first=parts[0].substring(1);
        String second=parts[1].substring(0,parts[1].length()-1);
        int i=Integer.parseInt(first);
        int j=Integer.parseInt(second);
        return new Position(i,j);
    }
}
