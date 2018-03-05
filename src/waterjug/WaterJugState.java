package waterjug;

import framework_puzzle.State;
import graph.SimpleVertex;

/**
 * This class represents states of the Water Jug problem. It creates new water
 * jug states, tests states for equality, and produces string representations of
 * them. Note that this class implements the <b>State</b> interface and
 * therefore imports <b>framework.State</b>.
 */
public class WaterJugState extends SimpleVertex implements State {

    // You must provide a constructor
    public WaterJugState(int amountInJugX, int amountInJugY) {
        
        //Throw exception for invalid amount
        if (amountInJugX > 3 || 
                amountInJugX < 0 || 
                amountInJugY > 4 || 
                amountInJugY < 0)
            throw new IllegalArgumentException("Invalid amount of water in a jug");
        
        this.setAmountInJugX(amountInJugX);
        this.setAmountInJugY(amountInJugY);
        
    }
    
    /**
     * Tests for equality between this state and the argument state. Two states
     * are equal if the X jugs have the same amount of water and the Y jugs have
     * the same amount of water.
     *
     * @param other the state to test against this state
     * @return whether the states are equal
     */
    @Override
    public boolean equals(Object other) {

        WaterJugState otherState = (WaterJugState) other;
        
        if ((this.amountInJugX == otherState.amountInJugX) &&
                (this.amountInJugY == otherState.amountInJugY)) 
            return true;
        
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.amountInJugX;
        hash = 97 * hash + this.amountInJugY;
        return hash;
    }

    /**
     * Creates a primitive, non-GUI representation of this WaterJugState object.
     *
     * @return the string representation of this water jug state
     */
    public String toString() {

        String jugState[] = {"     ", "  ", "|   |\n",
                             "|   |", "  ", "|   |\n",
                             "|   |", "  ", "|   |\n",   
                             "|   |", "  ", "|   |\n",
                             "+---+", "  ", "+---+\n",
                             "  X  ", "  ", "  Y  \n"        };
        
        int jugX = this.getAmountInJugX();
        int jugY = this.getAmountInJugY();
        
        int positionX = 9;
        while (jugX > 0) {
            jugState[positionX] = "|***|";
            positionX -= 3;
            --jugX;
        }
        
        int positionY = 11;
        while (jugY > 0) {
            jugState[positionY] = "|***|\n";
            positionY -= 3;
            --jugY;
        }
        
        String returnedString = "";

        for (String word : jugState) {
            returnedString += word;
        }
        
        return returnedString;
    }

    // Private methods and instance fields should go here
    private int amountInJugX = 0;
    private int amountInJugY = 0;
    
    /**
     * Set amount of water in jug X
     * param int jugX - amount of water
     */
    private void setAmountInJugX(int jugX) {
        this.amountInJugX = jugX;
    }
    
    /**
     * Get amount of water in jug X
     * @return int representation of the amount of water in jug X
     */
    public int getAmountInJugX() {
        return this.amountInJugX;
    }
    
    /**
     * Set amount of water in jug Y
     * param int jugY - amount of water
     */
    private void setAmountInJugY(int jugY) {
        this.amountInJugY = jugY;
    }
    
    /**
     * Get amount of water in jug Y
     * @return int representation of the amount of water in jug Y
     */
    public int getAmountInJugY() {
        return this.amountInJugY;
    }

    /**
     * Get heuristic value of the current state. For this problem, it return 0
     * @param finalState the state used to compare with the current state to compute the heuristic
     * @return heuristic values stored in array
     */
    @Override
    public int getHeuristic(State finalState) {
        return 0;
    }
}
