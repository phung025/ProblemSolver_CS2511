package bridge;

import framework_puzzle.Move;
import framework_puzzle.State;

/**
 * This class represents moves in the Bridge Crossing problem.
 * A move object stores its move name and knows how to apply itself
 * to a bridge state to create a new state representing the result
 * of the move.
 * Note that this class extends the abstract class <b>Move</b> and
 * therefore imports <b>framework.Move</b>.
 * This class inherits the <b>getMoveName()</b> method from its parent
 * and thus it should not have an instance field for the move name.
 * Other than that, it can be essentially the same as in the previous
 * assignment.
 * @author your name here
 */
public class BridgeMove extends Move {

    /**
     * Constructs a new bridge move object.
     * Note that the move name is passed to the parent constructor
     * using <b>super</b>.
     * @param moveName the name of this move.
     * It is an error if the name is not one of the following:
     * <ul>
     * <li> "P1 crosses alone" </li>
     * <li> "P2 crosses alone" </li>
     * <li> "P5 crosses alone" </li>
     * <li> "P10 crosses alone" </li>
     * <li> "P1 crosses with P2" </li>
     * <li> "P1 crosses with P5" </li>
     * <li> "P1 crosses with P10" </li>
     * <li> "P2 crosses with P5" </li>
     * <li> "P2 crosses with P10" </li>
     * <li> "P5 crosses with P10" </li>
     * </ul>
     */
    public BridgeMove(String moveName) {
        super(moveName);
        // You must provide the rest
    }
    
    /**
     * Attempts to perform this move on a given bridge state.
     * Note that this method implements the abstract <b>doMove</b> method declared
     * in the parent.
     * Thus the argument of type <b>State</b> must be cast to type
     * <b>BridgeState</b> before processing.
     * The move to perform is determined by this object's move name.
     * If the move can be performed a new bridge state object is returned that
     * reflects this move.
     * A move cannot be performed if the flashlight is not on the same side
     * as the crossing person(s), or if a pair of persons who are crossing are not
     * on the same side.
     * If the move cannot be performed <b>null</b> is returned.
     * @param otherState the bridge state on which this move is to be performed
     * @return a new bridge state reflecting the move, or <b>null</b> if it
     * cannot be performed
     */
    @Override
    public State doMove(State otherState) {
        
        //Cast state to bridge state
        BridgeState state = (BridgeState) otherState;
        
        //Create new state to be returned
        BridgeState newState = null;
        
        if (this.getMoveName().equals("P1 crosses alone")) {
            newState = this.doP1Move(state);
        } else if (this.getMoveName().equals("P2 crosses alone")) {
            newState = this.doP2Move(state);
        } else if (this.getMoveName().equals("P5 crosses alone")) {
            newState = this.doP5Move(state);
        } else if (this.getMoveName().equals("P10 crosses alone")) {
            newState = this.doP10Move(state);
        } else if (this.getMoveName().equals("P1 crosses with P2")) {
            newState = this.doP1P2Move(state);
        } else if (this.getMoveName().equals("P1 crosses with P5")) {
            newState = this.doP1P5Move(state);
        } else if (this.getMoveName().equals("P1 crosses with P10")) {
            newState = this.doP1P10Move(state);
        } else if (this.getMoveName().equals("P2 crosses with P5")) {
            newState = this.doP2P5Move(state);
        } else if (this.getMoveName().equals("P2 crosses with P10")) {
            newState = this.doP2P10Move(state);
        } else if (this.getMoveName().equals("P5 crosses with P10")) {
            newState = this.doP5P10Move(state);
        }
        
        return newState;
    }
    
    // Private methods and instance fields should go here
    /**
     * Move person with 1 minute travel time. Require state of the bridge as
     * parameter
     *
     * @param state
     * @return the new BridgeState from state after moving P1 if conditions met
     * else return null
     */
    private BridgeState doP1Move(BridgeState state) {

        BridgeState newState = null;

        if (state.getFlashlightPosition() == state.getP1Position()) { //If p1 has the flashlight

            int newTime = state.getTimeSoFar() + 1; //Updated time

            if (state.getFlashlightPosition() == Position.EAST) { //If both on east, move to west
                newState = new BridgeState(Position.WEST,
                        state.getP2Position(),
                        Position.WEST,
                        state.getP5Position(),
                        state.getP10Position(),
                        newTime);
            } else { //If both on west, move to east
                newState = new BridgeState(Position.EAST,
                        state.getP2Position(),
                        Position.EAST,
                        state.getP5Position(),
                        state.getP10Position(),
                        newTime);
            }
        }

        return newState;
    }

    /**
     * Move person with 2 minutes travel time. Require state of the bridge as
     * parameter
     *
     * @param state
     * @return the new BridgeState from state after moving P2 if conditions met
     * else return null
     */
    private BridgeState doP2Move(BridgeState state) {

        BridgeState newState = null;

        if (state.getFlashlightPosition() == state.getP2Position()) { //If p2 has the flashlight

            int newTime = state.getTimeSoFar() + 2; //Updated time

            if (state.getFlashlightPosition() == Position.EAST) { //If both on east, move to west
                newState = new BridgeState(state.getP1Position(),
                        Position.WEST,
                        Position.WEST,
                        state.getP5Position(),
                        state.getP10Position(),
                        newTime);
            } else { //If both on west, move to east
                newState = new BridgeState(state.getP1Position(),
                        Position.EAST,
                        Position.EAST,
                        state.getP5Position(),
                        state.getP10Position(),
                        newTime);
            }
        }

        return newState;
    }

    /**
     * Move person with 5 minutes travel time. Require state of the bridge as
     * parameter
     *
     * @param state
     * @return the new BridgeState from state after moving P5 if conditions met
     * else return null
     */
    private BridgeState doP5Move(BridgeState state) {

        BridgeState newState = null;

        if (state.getFlashlightPosition() == state.getP5Position()) { //If p5 has the flashlight

            int newTime = state.getTimeSoFar() + 5; //Updated time

            if (state.getFlashlightPosition() == Position.EAST) { //If both on east, move to west
                newState = new BridgeState(state.getP1Position(),
                        state.getP2Position(),
                        Position.WEST,
                        Position.WEST,
                        state.getP10Position(),
                        newTime);
            } else { //If both on west, move to east
                newState = new BridgeState(state.getP1Position(),
                        state.getP2Position(),
                        Position.EAST,
                        Position.EAST,
                        state.getP10Position(),
                        newTime);
            }
        }

        return newState;
    }

    /**
     * Move person with 10 minutes travel time. Require state of the bridge as
     * parameter
     *
     * @param state
     * @return the new BridgeState from state after moving P10 if conditions met
     * else return null
     */
    private BridgeState doP10Move(BridgeState state) {

        BridgeState newState = null;

        if (state.getFlashlightPosition() == state.getP10Position()) { //If p10 has the flashlight

            int newTime = state.getTimeSoFar() + 10; //Updated time

            if (state.getFlashlightPosition() == Position.EAST) { //If both on east, move to west
                newState = new BridgeState(state.getP1Position(),
                        state.getP2Position(),
                        Position.WEST,
                        state.getP5Position(),
                        Position.WEST,
                        newTime);
            } else { //If both on west, move to east
                newState = new BridgeState(state.getP1Position(),
                        state.getP2Position(),
                        Position.EAST,
                        state.getP5Position(),
                        Position.EAST,
                        newTime);
            }
        }

        return newState;
    }

    /**
     * Move person with 2 minutes travel time and 1 minute travel time. Require
     * state of the bridge as parameter
     *
     * @param state
     * @return the new BridgeState from state after moving P2 & P1 if conditions
     * met else return null
     */
    private BridgeState doP1P2Move(BridgeState state) {

        BridgeState newState = null;

        //If p1, p2, and flashlight on the same side
        if (state.getP1Position() == state.getP2Position()
                && state.getP1Position() == state.getFlashlightPosition()) {

            int newTime = state.getTimeSoFar() + 2;

            //If on the east side, move to west
            if (state.getFlashlightPosition() == Position.EAST) {
                newState = new BridgeState(Position.WEST,
                        Position.WEST,
                        Position.WEST,
                        state.getP5Position(),
                        state.getP10Position(),
                        newTime);
            } else { //If on the west, move to east
                newState = new BridgeState(Position.EAST,
                        Position.EAST,
                        Position.EAST,
                        state.getP5Position(),
                        state.getP10Position(),
                        newTime);
            }
        }

        return newState;
    }

    /**
     * Move person with 5 minutes travel time and 1 minute travel time. Require
     * state of the bridge as parameter
     *
     * @param state
     * @return the new BridgeState from state after moving P5 & P1 if conditions
     * met else return null
     */
    private BridgeState doP1P5Move(BridgeState state) {

        BridgeState newState = null;

        //If p1, p5, and flashlight on the same side
        if (state.getP1Position() == state.getP5Position()
                && state.getP1Position() == state.getFlashlightPosition()) {

            int newTime = state.getTimeSoFar() + 5;

            //If on the east side, move to west
            if (state.getFlashlightPosition() == Position.EAST) {
                newState = new BridgeState(Position.WEST,
                        state.getP2Position(),
                        Position.WEST,
                        Position.WEST,
                        state.getP10Position(),
                        newTime);
            } else { //If on the west, move to east
                newState = new BridgeState(Position.EAST,
                        state.getP2Position(),
                        Position.EAST,
                        Position.EAST,
                        state.getP10Position(),
                        newTime);
            }
        }

        return newState;
    }

    /**
     * Move person with 10 minutes travel time and 1 minute travel time. Require
     * state of the bridge as parameter
     *
     * @param state
     * @return the new BridgeState from state after moving P10 & P1 if
     * conditions met else return null
     */
    private BridgeState doP1P10Move(BridgeState state) {

        BridgeState newState = null;

        //If p1, p10, and flashlight on the same side
        if (state.getP1Position() == state.getP10Position()
                && state.getP1Position() == state.getFlashlightPosition()) {

            int newTime = state.getTimeSoFar() + 10;

            //If on the east side, move to west
            if (state.getFlashlightPosition() == Position.EAST) {
                newState = new BridgeState(Position.WEST,
                        state.getP2Position(),
                        Position.WEST,
                        state.getP5Position(),
                        Position.WEST,
                        newTime);
            } else { //If on the west, move to east
                newState = new BridgeState(Position.EAST,
                        state.getP2Position(),
                        Position.EAST,
                        state.getP5Position(),
                        Position.EAST,
                        newTime);
            }
        }

        return newState;
    }

    /**
     * Move person with 2 minutes travel time and 5 minutes travel time. Require
     * state of the bridge as parameter
     *
     * @param state
     * @return the new BridgeState from state after moving P2 & P5 if conditions
     * met else return null
     */
    private BridgeState doP2P5Move(BridgeState state) {

        BridgeState newState = null;

        //If p2, p5, and flashlight on the same side
        if (state.getP2Position() == state.getP5Position()
                && state.getP2Position() == state.getFlashlightPosition()) {

            int newTime = state.getTimeSoFar() + 5;

            //If on the east side, move to west
            if (state.getFlashlightPosition() == Position.EAST) {
                newState = new BridgeState(state.getP1Position(),
                        Position.WEST,
                        Position.WEST,
                        Position.WEST,
                        state.getP10Position(),
                        newTime);
            } else { //If on the west, move to east
                newState = new BridgeState(state.getP1Position(),
                        Position.EAST,
                        Position.EAST,
                        Position.EAST,
                        state.getP10Position(),
                        newTime);
            }
        }

        return newState;
    }

    /**
     * Move person with 2 minutes travel time and 10 minutes travel time.
     * Require state of the bridge as parameter
     *
     * @param state
     * @return the new BridgeState from state after moving P2 & P10 if
     * conditions met else return null
     */
    private BridgeState doP2P10Move(BridgeState state) {

        BridgeState newState = null;

        //If p2, p10, and flashlight on the same side
        if (state.getP2Position() == state.getP10Position()
                && state.getP2Position() == state.getFlashlightPosition()) {

            int newTime = state.getTimeSoFar() + 10;

            //If on the east side, move to west
            if (state.getFlashlightPosition() == Position.EAST) {
                newState = new BridgeState(state.getP1Position(),
                        Position.WEST,
                        Position.WEST,
                        state.getP5Position(),
                        Position.WEST,
                        newTime);
            } else { //If on the west, move to east
                newState = new BridgeState(state.getP1Position(),
                        Position.EAST,
                        Position.EAST,
                        state.getP5Position(),
                        Position.EAST,
                        newTime);
            }
        }

        return newState;
    }

    /**
     * Move person with 5 minutes travel time and 10 minutes travel time.
     * Require state of the bridge as parameter
     *
     * @param state
     * @return the new BridgeState from state after moving P5 & P10 if
     * conditions met else return null
     */
    private BridgeState doP5P10Move(BridgeState state) {

        BridgeState newState = null;

        //If p2, p10, and flashlight on the same side
        if (state.getP5Position() == state.getP10Position()
                && state.getP5Position() == state.getFlashlightPosition()) {

            int newTime = state.getTimeSoFar() + 10;

            //If on the east side, move to west
            if (state.getFlashlightPosition() == Position.EAST) {
                newState = new BridgeState(state.getP1Position(),
                        state.getP2Position(),
                        Position.WEST,
                        Position.WEST,
                        Position.WEST,
                        newTime);
            } else { //If on the west, move to east
                newState = new BridgeState(state.getP1Position(),
                        state.getP2Position(),
                        Position.EAST,
                        Position.EAST,
                        Position.EAST,
                        newTime);
            }
        }

        return newState;
    }
}
