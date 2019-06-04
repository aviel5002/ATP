package algorithms.search;

import java.util.*;
/**
 * A searching algorithm using breadth first search pseudo code using a linked list that works like a queue
 *  @author aviel avitan,ron shakutai
 */
public class BreadthFirstSearch extends ASearchingAlgorithm {

     Queue <AState> openlist;

    public BreadthFirstSearch() {
        super();
        openlist=new LinkedList<AState>();
    }
    /**
     * returns the name of the searching algorithm
     * @return string fo the name
     */
    @Override
    public String getName() {
        return "Breadth first search";
    }


    /**
     * A function that finds a solution given a searchable object
     * @param s an object that implements the ISearchable interface
     * @return a new solution
     */
    @Override
    public Solution solve(ISearchable s) {
        if(s==null)
            return null;
        ArrayList<AState> solution=new ArrayList<>();
        AState start=s.getStartState();
        AState goal=s.getGoalState();
        start.setCost(0);
        start.setCamefrom(null);
        openlist.add(start);
        open.put(start.getState(),start);
        ArrayList<AState> add=new ArrayList<>();
        while(!openlist.isEmpty()){
            AState check=remove();
            closed.put(check.getState(),check);
            if(check.equals(goal)){
                return new Solution(check);
            }
            else
                UpdateSuccesors(s.getAllSuccessors(check),check);
                }
                 return null;
            }

    /**
     * A function that gets a list of successors and updates their cost and where they came from
     * @param s the list of successors
     * @param check the state which the list came from
     */
    public void UpdateSuccesors (ArrayList<AState> s,AState check){
        if(s==null||check==null)
            return;
        for(AState sc :s){
            if(this.closed.get(sc.getState())!=null) {
                continue;
            }
            else if(this.open.get(sc.getState())!=null) {
                continue;
            }
            else {
                sc.setCamefrom(check);
                sc.setCost(check.getCost()+sc.getCost());
                this.openlist.add(sc);
                this.open.put(sc.getState(),sc);
            }
        }
    }

    /**
     * removes the first element from the queue and updates the number of visited nodes
     * @return the first element in the queue
     */
    public AState remove(){
        if(openlist.isEmpty())
            return null;
        visitedNodes++;
        return openlist.poll();
    }
}
