package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * A class that represents a solution of searchable objects
 *  @author aviel avitan,ron shakutai
 */
public class Solution implements Serializable {
    private AState solution;

    public Solution(AState solution) {
        this.solution = solution;
    }

    public void setSolution(AState solution) {
        this.solution = solution;
    }

    public AState getSolution() {
        return solution;
    }

    /**
     * returns the full solution path by using the cameFrom
     * @return a list of the states
     */
    public ArrayList<AState> getSolutionPath() {
        ArrayList<AState> solution=new ArrayList<>();
        solution.add(this.solution);
        AState found=this.solution;
        while(found.getCamefrom()!=null) {
            solution.add(0, found.getCamefrom());
            found=found.getCamefrom();
        }
        return solution;
        }

    @Override
    public String toString() {
        if(solution==null)
            return "0";
        return this.getSolutionPath().size()+"";
    }
}
