package algorithms.search;

/**
 * An interface for objects that represents searching algorithms
 *  @author aviel avitan,ron shakutai
 */
public interface ISearchingAlgorithm {
    public Solution solve(ISearchable doamin);
    public String getName();
    public int getNumberOfNodesEvaluated();
}
