package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * A class that represents a state in a maze
 *  @author aviel avitan,ron shakutai
 */
public class MazeState extends AState {
    protected Position p;



    /**
     * a constructor that creates a state using a maze position
     * @param position position from the maze
     */

    public MazeState(Position position) {
        super(position.toString());
        p=position;
    }

    /**
     * a constructor that gets all the parameters to create an AState
     * @param state string for state
     * @param cost number of cost
     * @param camefrom where the state came from
     */
    public MazeState(String state, int cost, AState camefrom) {
        super(state, cost, camefrom);
    }

    public Position getP() {
        return p;
    }
}

