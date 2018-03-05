/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework_puzzle;

import javax.swing.JComponent;

/**
 * This class visualize a current state of the problem using graphics
 *
 * @author Nam Phung
 */
public abstract class Canvas extends JComponent {

    /**
     * Parameterized constructor, take in a state to be drawn by the canvas
     *
     * @param aState
     */
    public Canvas(State aState) {
        
        //Set current state, initially current and previous state are the same
        setCurrentState(aState);
        setPreviousState(aState);
    }

    /**
     * Get current state
     *
     * @return currentState
     */
    protected State getCurrentState() {
        return this.currentState;
    }

    /**
     * Set current state
     *
     * @param aState
     */
    protected void setCurrentState(State aState) {
        setPreviousState(getCurrentState());
        this.currentState = aState;
    }

    /**
     * Set previous state
     *
     * @param aState
     */
    private void setPreviousState(State aState) {
        this.previousState = aState;
    }
    
    /**
     * Get previous state
     *
     * @return currentState
     */
    public State getPreviousState() {
        return this.previousState;
    }

    /**
     * Render animation for the state of the problem
     */
    public void render() {
        repaint();
    }

    /**
     * Instance fields
     */
    /**
     * Current state to be drawn
     */
    private State currentState = null;

    /**
     * Previous state stored for rendering
     */
    private State previousState = null;
}
