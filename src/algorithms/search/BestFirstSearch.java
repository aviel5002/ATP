package algorithms.search;

import java.util.PriorityQueue;
/**
 * A searching algorithm improves the runtime of breadth first search by using priority queue
 *  @author aviel avitan,ron shakutai
 */
public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch() {

        super();
        openlist=new PriorityQueue<AState>(new AStateComprator());
    }


    /**
     * returns the name of the searching algorithm
     * @return string fo the name
     */

    @Override
    public String getName() {
        return "Best first search";
    }



}
