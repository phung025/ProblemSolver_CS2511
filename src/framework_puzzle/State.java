package framework_puzzle;

/**
 * This interface represents the state of affairs of a problem-solving
 * domain.  Implementing classes will store the representation details
 * of concrete problem states.
 */
public interface State {
    
    /**                                                                                                 
     * Tests for equality between this state and the argument state. 
     * Implementing classes will need to cast the argument to a specific class type.                                                    
     * @param other the state to test against this state                                                
     * @return true if this state and the other are equal, false otherwise                                                             
    */
    @Override
    public boolean equals(Object other);
    
    /**                                                                                                 
     *Creates a primitive, non-GUI representation of this State object.                        
     *@return the string representation of this state                                       
    */
    @Override
    public String toString();

    /**
     * Get heuristic values of the state
     * @param finalState the state that the current state will compare with to compute the heuristic
     * @return heuristic value of the state
     */
    default public int getHeuristic(State finalState) {
        return 0;
    }
}
