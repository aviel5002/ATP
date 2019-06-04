package algorithms.search;

import java.util.ArrayList;
/**
 * An interface for objects that can be searched
 *  @author aviel avitan,ron shakutai
 */
public interface ISearchable {
    AState getStartState();
    AState getGoalState();
    ArrayList<AState> getAllSuccessors(AState state);
}
