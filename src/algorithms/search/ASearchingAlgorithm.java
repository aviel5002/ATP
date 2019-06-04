package algorithms.search;
import java.util.HashMap;
/**
 * An abstract class for searching algorithms
 *  @author aviel avitan,ron shakutai
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected int visitedNodes;
    HashMap<String,AState> open;
    HashMap<String,AState> closed;

    /**
     * constructor that sets all the parameters to the initial state
     */
    public ASearchingAlgorithm() {
        visitedNodes=0;
        open=new HashMap<>();
        closed=new HashMap<>();
    }

    /**
     * returns the number of nodes the algorithm has visited
     * @return number of nodes visited
     */
    @Override
    public int getNumberOfNodesEvaluated(){
        return visitedNodes;
    }

}
