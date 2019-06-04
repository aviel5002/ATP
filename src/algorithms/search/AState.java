package algorithms.search;

import java.io.Serializable;

/**
 * An abstract class that represents a state
 *  @author aviel avitan,ron shakutai
 */
public abstract class AState implements Serializable {
    private String state;
    private int cost;
    private AState camefrom;

    /**
     * constructor that initializes the state
     * @param state a string that represents the state
     * @param cost represents the cost of the state
     * @param camefrom represents the father of the state
     */
    public AState(String state, int cost, AState camefrom) {
        this.state = state;
        this.cost = cost;
        this.camefrom = camefrom;
    }

    public AState() { }

    public AState(AState state) {
        this.camefrom=state.camefrom;
        this.cost=state.cost;
        this.state=state.state;
    }


    public AState(String state) {
        this.state=state;
    }

    public AState getCamefrom() {
        return camefrom;
    }


    public int getCost() {
        return cost;
    }

    /**
     * Overrides the equals function by comparing the states and using the string equals function
     * @param o the state that needs to be compared
     * @return returns true if they are equal
     */
    public  boolean equals(AState o){
        return this.state.equals(o.state);
    }

    /**
     * overrides the hashCode function
     * @return the hashCode
     */
    @Override
    public int hashCode() {
        return getState().hashCode();
    }

    public String getState() {
        return state;
    }

    public void setCamefrom(AState camefrom) {
        this.camefrom = camefrom;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * converts the state to a string
     * @return string that represents the state
     */
    public String toString(){
        return "state:"+state;
    }
}
