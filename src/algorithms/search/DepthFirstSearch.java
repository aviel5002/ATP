package algorithms.search;

import java.util.ArrayList;

/**
 * A searching algorithm using Depth first search pseudo code
 *  @author aviel avitan,ron shakutai
 */
public class DepthFirstSearch extends ASearchingAlgorithm {
    ArrayList<AState> Openlis;


    public DepthFirstSearch() {
        Openlis = new ArrayList<AState>();
    }

    /**
     * returns the name of the searching algorithm
     * @return string fo the name
     */
    public String getName() {
        return "Depth first search";
    }

    /**
     * A function that finds a solution given a searchable object
     * @param s an object that implements the ISearchable interface
     * @return a new solution
     */
    @Override
    public Solution solve(ISearchable s) {
        AState temp, end = s.getGoalState();
        temp=s.getStartState();
        closed.put(temp.getState(),temp);
        visitedNodes++;
        temp.setCost(0);
            while (temp != null) {
                if (temp.equals(end) == true) {
                    return new Solution(temp);
                }// i got the solution
                if (UpdatetSuccesors(s.getAllSuccessors(temp),temp) == true) {
                    closed.put(temp.getState(),temp);
                    visitedNodes++;
                    temp = Openlis.remove(0);// taking random successor
                    open.remove(temp.getState());
                } else {
                    while (temp.getCamefrom().equals(Openlis.get(0).getCamefrom()) == false) {// check if it equal to the father if yes so its good if not i keep taking them out
                        closed.put(temp.getState(),temp);
                        temp=temp.getCamefrom();// insert to the closed list .
                    }
                    temp = Openlis.remove(0);
                    open.remove(temp.getState());
                }
            }
        return null;
    }
    /**
     * A function that gets a list of successors and updates their cost and where they came from
     * @param s the list of successors
     * @param check the state which the list came from
     */
    public boolean UpdatetSuccesors(ArrayList<AState> s,AState temp){
        boolean flag=false;
        for (AState Checker : s) {
            if(closed.get(Checker.getState())!=null)
                continue;
            else if (open.get(Checker.getState())!=null){
                continue;
            }
            Checker.setCost(temp.getCost()+1);
            Checker.setCamefrom(temp);
            Openlis.add(0,Checker);
            open.put(Checker.getState(),Checker);
            flag=true;
        }
        return flag;
    }
}

