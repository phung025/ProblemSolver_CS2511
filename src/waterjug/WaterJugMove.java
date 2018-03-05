package waterjug;

import framework_puzzle.Move;
import framework_puzzle.State;

/**
 * This class represents moves in the Water Jug problem. A move object stores
 * its move name and knows how to apply itself to a water jug state to create a
 * new state representing the result of the move. Note that this class extends
 * the abstract class <b>Move</b> and therefore imports <b>framework.Move</b>.
 * This class inherits the <b>getMoveName()</b> method from its parent and thus
 * it should not have an instance field for the move name.
 *
 * @author your name here
 */
public class WaterJugMove extends Move {

    /**
     * Constructs a new water jug move object. Note that the move name is passed
     * to the parent constructor using <b>super</b>.
     *
     * @param moveName the name of this move. It is an error if the name is not
     * one of the following:
     * <ul>
     * <li> "Fill Jug X" </li>
     * <li> "Fill Jug Y" </li>
     * <li> "Empty Jug X" </li>
     * <li> "Empty Jug Y" </li>
     * <li> "Transfer Jug X to Jug Y" </li>
     * <li> "Transfer Jug Y to Jug X" </li>
     * </ul>
     */
    public WaterJugMove(String moveName) {
        super(moveName);
        // You must provide the rest
    }

    /**
     * Attempts to perform this move on a given water jug state. Note that this
     * method implements the abstract <b>doMove</b> method declared in the
     * parent. Thus the argument of type <b>State</b> must be cast to type
     * <b>WaterJugState</b> before processing. The move to perform is determined
     * by this object's move name. If the move can be performed a new water jug
     * state object is returned that reflects this move. A move cannot be
     * performed if trying to fill or transfer to an already full jug, or if
     * trying to empty or transfer from an empty jug. If the move cannot be
     * performed <b>null</b> is returned.
     *
     * @param otherState the water jug state on which this move is to be
     * performed
     * @return a new water jug state reflecting the move, or <b>null</b> if it
     * cannot be performed
     */
    public State doMove(State otherState) {

        WaterJugState state = (WaterJugState) otherState;
        WaterJugState newState = null;

        if (this.getMoveName().equals("Fill Jug X")) {
            newState = this.fillJugX(state);
        } else if (this.getMoveName().equals("Fill Jug Y")) {
            newState = this.fillJugY(state);
        } else if (this.getMoveName().equals("Empty Jug X")) {
            newState = this.emptyJugX(state);
        } else if (this.getMoveName().equals("Empty Jug Y")) {
            newState = this.emptyJugY(state);
        } else if (this.getMoveName().equals("Transfer Jug X to Jug Y")) {
            newState = this.transferXtoY(state);
        } else if (this.getMoveName().equals("Transfer Jug Y to Jug X")) {
            newState = this.transferYtoX(state);
        }
        
        return newState; // You must provide
    }

    // Private methods and instance fields should go here
    /**
     * Fill jug X with water
     *
     * @param state
     * @return new state for the jug if successful else return null
     */
    private WaterJugState fillJugX(WaterJugState state) {
        WaterJugState newState = null;

        if (state.getAmountInJugX() < 3) {
            newState = new WaterJugState(3, state.getAmountInJugY());
        }

        return newState;

    }

    /**
     * Fill water in jug Y
     *
     * @param state
     * @return new WaterJugState if successful else null
     */
    private WaterJugState fillJugY(WaterJugState state) {
        WaterJugState newState = null;

        if (state.getAmountInJugY() < 4) {
            newState = new WaterJugState(state.getAmountInJugX(), 4);
        }

        return newState;
    }

    /**
     * Empty jug X
     *
     * @param state
     * @return new water jug state if successful else null
     */
    private WaterJugState emptyJugX(WaterJugState state) {
        WaterJugState newState = null;

        if (state.getAmountInJugX() != 0) {
            newState = new WaterJugState(0, state.getAmountInJugY());
        }

        return newState;
    }

    /**
     * Empty jug Y
     *
     * @param state
     * @return new water jug state if successful else null
     */
    private WaterJugState emptyJugY(WaterJugState state) {
        WaterJugState newState = null;

        if (state.getAmountInJugY() != 0) {
            newState = new WaterJugState(state.getAmountInJugX(), 0);
        }

        return newState;
    }

    /**
     * Transfer water from jug X to Y
     *
     * @param state
     * @return new water jug state if successful else null
     */
    private WaterJugState transferXtoY(WaterJugState state) {
        WaterJugState newState = null;

        if (state.getAmountInJugY() < 4 && state.getAmountInJugX() != 0) {

            //Get the free space in jug Y
            int freeSpace = 4 - state.getAmountInJugY();

            if (state.getAmountInJugX() <= freeSpace) {
                newState = new WaterJugState(0,
                        state.getAmountInJugY() + state.getAmountInJugX());
            } else {
                newState = new WaterJugState(state.getAmountInJugX() - freeSpace,
                        4);
            }
        }

        return newState;
    }

    /**
     * Transfer water from jug Y to X
     *
     * @param state
     * @return new water jug state if successful else null
     */
    private WaterJugState transferYtoX(WaterJugState state) {
        WaterJugState newState = null;

        if (state.getAmountInJugX() < 3 && state.getAmountInJugY() != 0) {

            //Get the free space in jug X
            int freeSpace = 3 - state.getAmountInJugX();

            if (state.getAmountInJugY() <= freeSpace) {
                newState = new WaterJugState(state.getAmountInJugX() + state.getAmountInJugY(),
                        0);
            } else {
                newState = new WaterJugState(3,
                        state.getAmountInJugY() - freeSpace);
            }
        }

        return newState;
    }
}
