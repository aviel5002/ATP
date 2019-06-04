package algorithms.mazeGenerators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
/**
 * A class that represents a maze
 *  @author aviel avitan,ron shakutai
 */
public class Maze implements Serializable {
    protected Position Start;
    protected Position Goal;
    protected int [][] CurrMaze;
    protected int rows;
    protected int cols;

    public Maze(byte[] maze) {
        int rows = ((maze[1] & 0xff) << 8) | (maze[0] & 0xff);
        int cols = ((maze[3] & 0xff) << 8) | (maze[2] & 0xff);
        int StartRow = ((maze[5] & 0xff) << 8) | (maze[4] & 0xff);
        int StartoCol = ((maze[7] & 0xff) << 8) | (maze[6] & 0xff);
        int GoalRow = ((maze[9] & 0xff) << 8) | (maze[8] & 0xff);
        int GoalColumn = ((maze[11] & 0xff) << 8) | (maze[10] & 0xff);
        this.CurrMaze=new int[rows][cols];
        Start=new Position(StartRow,StartoCol);
        Goal=new Position(GoalRow,GoalColumn);
        this.rows=rows;
        this.cols=cols;
        int cells=0,k=11,curr=0;
        for (int i = 0; i <rows ; i++) {
            for (int j = 0; j <cols ; j++) {
                while(cells==0){
                    k++;
                    cells=maze[k]+128;
                    if(curr==1)
                        curr=0;
                    else
                        curr=1;
                    }

                if(cells>0) {
                    this.setValueToIndex(i,j,curr);
                    cells--;
                }
            }
            }
        }


    /**
     * A constructor that creates a maze from rows and columns
     * @param rows number of rows for the maze
     * @param cols number of columns for the maze
     */
    public Maze(int rows, int cols) {
        if(rows<0)
            rows=0;
        if(cols<0)
            cols=0;
        this.rows = rows;
        this.cols = cols;
        Position start=generatePoint(rows,cols);
        Position goal=generatePoint(rows,cols);
        if(rows>1||cols>1)
        while(start.getRowIndex()==goal.getColumnIndex()&&start.getColumnIndex()==goal.getColumnIndex())
            goal=generatePoint(rows,cols);
        Goal=goal;
        Start=start;
        CurrMaze=new int[rows][cols];
    }

    /**
     * A copy constructor
     * @param m maze given to copy
     */
    public Maze(Maze m){
        if(m==null){
            cols=0;
            CurrMaze=null;
            Goal=null;
            Start=null;
            rows=0;
        } else {
            this.cols = m.getCols();
            this.CurrMaze = m.getCurrMaze();
            this.Goal = m.getGoalPosition();
            this.Start = m.getStartPosition();
            this.rows = m.getRows();
        }
    }

    /**
     * A function that prints the maze with a start position and an end position
     */
    public void  print (){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <cols; j++) {
                if(i==Start.getRowIndex() && j==Start.getColumnIndex())
                    System.out.print("S ");
                else if(i==Goal.getRowIndex() && j==Goal.getColumnIndex()) {
                    System.out.print("E ");
                }
                    else {
                    System.out.print(CurrMaze[i][j]+" ");
                    }
                }
            System.out.println("");
            }

        }

    /**
     * Sets value into one of the cells in the maze
     * @param i row number
     * @param j column number
     * @param value the value needed to be inserted
     */
    public void setValueToIndex(int i,int j,int value){
        if(i>=0 && j>=0 && i<this.rows && j<this.cols)
        CurrMaze[i][j]=value;
    }

    /**
     * returns the value in a cell using given indexes
     * @param i row number
     * @param j column number
     * @return value in cell
     */

    public int Getvalue(int i,int j){
        if(i>=0 && j>=0 && i<this.rows && j<this.cols)
        return CurrMaze[i][j];
        return -1;
    }

    /**
     * sets the starting position of the maze
     * @param begin a position to set
     */

    public void setBegin(Position begin) {
        this.Start = begin;
    }
    /**
     * sets the goal position of the maze
     * @param goal a position to set
     */

    public void setGoal(Position goal) {
        Goal = goal;
    }

    /**
     *returns the start position of the maze
     * @return start position
     */

    public Position getStartPosition() {
        return Start;
    }
    /**
     *returns the goal position of the maze
     * @return goal position
     */
    public Position getGoalPosition() {
        return Goal;
    }
    /**
     *returns the matrix of the maze
     * @return the matrix that represents the current maze
     */
    public int[][] getCurrMaze() {
        return CurrMaze;
    }
    /**
     *returns the number of rows in the maze
     * @return number of rows in the maze
     */
    public int getRows() {
        return rows;
    }
    /**
     *returns the number of columns in the maze
     * @return number of columns in the maze
     */
    public int getCols() {
        return cols;
    }
    /**
     * returns a random position in the maze
     * @param rows row number
     * @param columns column number
     * @return random position
     */

    public Position generatePoint(int rows,int columns){
        Random r=new Random();
        int ans=r.nextInt(rows);
        int ans1=r.nextInt(columns);
        return new Position(ans,ans1);
    }
    public byte[] toByteArray(){
        ArrayList<Byte> ans=new ArrayList<>();
        int rows=this.getRows(),cols=this.getCols(),StRow=this.getStartPosition().getRowIndex(),StCol=this.getStartPosition().getColumnIndex(),GoRow=this.getGoalPosition().getRowIndex(),GoCol=this.getGoalPosition().getColumnIndex();
        ans.add(((byte)(rows & 0xFF)));
        ans.add((byte)((rows >>> 8 )& 0xFF));
        ans.add((byte)(cols & 0xFF));
        ans.add((byte)((cols >>> 8 )& 0xFF));
        ans.add((byte)(StRow & 0xFF));
        ans.add((byte)((StRow >>> 8 )& 0xFF));
        ans.add((byte)(StCol & 0xFF));
        ans.add((byte)((StCol >>> 8 )& 0xFF));
        ans.add((byte)(GoRow & 0xFF));
        ans.add((byte)((GoRow >>> 8 )& 0xFF));
        ans.add((byte)(GoCol & 0xFF));
        ans.add((byte)((GoCol >>> 8 )& 0xFF));
        byte count=-128,check=1;
        for (int i = 0; i <this.getRows() ; i++) {
            for (int j = 0; j <this.getCols() ; j++) {
                if(count==127){
                    ans.add(count);
                    count=-128;
                    if(check==1)
                        check=0;
                    else
                        check=1;
                }
                if(check==this.Getvalue(i,j)){
                    if(i==this.getRows()-1&&j==this.getCols()-1) {
                        count++;
                        ans.add(count);
                    }
                    else
                    count++;
                }
                else{
                    if(check==1)
                        check=0;
                    else
                        check=1;
                    if(i==this.getRows()-1&&j==this.getCols()-1) {
                        ans.add(count);
                        count=-127;
                        ans.add(count);
                        break;
                    }
                    ans.add(count);
                    count=-127;
                }
            }
        }
        byte[] add=new byte[ans.size()];
        for (int i = 0; i <ans.size() ; i++) {
            add[i]=ans.get(i);
        }
        return add;
    }
}
