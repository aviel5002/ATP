package algorithms.mazeGenerators;


/**
 * An interface for maze generators
 *  @author aviel avitan,ron shakutai
 */

public interface IMazeGenerator {
    Maze generate(int rows,int columns);
    long measureAlgorithmTimeMillis(int rows,int columns);
}
