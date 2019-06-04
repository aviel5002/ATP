package algorithms.mazeGenerators;

/**
 * An abstract class for maze generators
 *  @author aviel avitan,ron shakutai
 */

public abstract class AMazeGenerator implements IMazeGenerator {

    Maze  maze;

    public AMazeGenerator() {
        maze=null;
    }
    /**
     * This functions gets the number of rows and columns wanted for the maze and then generates it using different algorithms
     * @param rows number of rows
     * @param columns number of columns
     * @return new maze
     *
     */
    @Override
    public abstract Maze generate(int rows, int columns);

    /**
     * This function measures how much time it takes to generate a maze in milliSeconds
     * @param rows number of rows
     * @param columns number of columns
     * @return time in milliSeconds
     */
    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        if(rows<0||columns<0)
            return 0;
        long start=System.currentTimeMillis();
        generate(rows,columns);
        long end=System.currentTimeMillis();
        return end-start;
    }

}

