package waterjug;

import framework_puzzle.Move;
import framework_puzzle.Problem;
import framework_puzzle.State;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Water Jug problem. It provides an introductory
 * message describing the problem, stores the problem's possible moves and
 * current state, and checks for whether the problem has been successfully
 * solved. Note that this class extends the abstract class <b>Problem</b> and
 * therefore imports <b>framework.Problem</b>. This class inherits the
 * <b>setIntroduction(), setCurrentState()</b>, and
 * <b>setMoves()</b> methods from its parent and thus it should not have any
 * instance fields for these attributes.
 *
 * @author your name here
 */
public class WaterJugProblem extends Problem {

    /**
     * Constructs a new water jug problem object. A new water jug state object
     * should be constructed with zero gallons in both jugs. This state should
     * be set as the current state of the problem using the inherited
     * <b>setCurrentState()</b> method. The six valid water jug moves should be
     * created and stored on a list using the inherited <b>setMoves()</b>
     * method. The appropriate introduction string for this problem should be
     * stored using the inherited <b>setIntroduction()</b>.
     */
    public WaterJugProblem() {

        //Set initial state of the problem
        WaterJugState initialState = new WaterJugState(0, 0);
        this.setCurrentState(initialState);

        //Create set of moves
        List<Move> listMoves = new ArrayList();

        listMoves.add(new WaterJugMove("Fill Jug X"));
        listMoves.add(new WaterJugMove("Fill Jug Y"));
        listMoves.add(new WaterJugMove("Empty Jug X"));
        listMoves.add(new WaterJugMove("Empty Jug Y"));
        listMoves.add(new WaterJugMove("Transfer Jug X to Jug Y"));
        listMoves.add(new WaterJugMove("Transfer Jug Y to Jug X"));

        this.setMoves(listMoves);

        //Set introduction string
        this.setIntroduction("Welcome to the Water Jug Problem.\n"
                + "\n"
                + "You are given two empty jugs: jug X holds 3 gallons, jug Y holds 4.\n"
                + "Neither has any measuring markers on it. You have a ready supply\n"
                + "of water. You can fill either jug, empty either jug on the ground,\n"
                + "or pour all or some of either jug into the other.  The goal is to\n"
                + "get exactly 2 gallons of water into either jug.\n"
                + "\n"
                + "Here is your initial state:\n");

        //Set the initial State
        this.setCurrentState(new WaterJugState(0, 0));

        //Set the final State
        this.setFinalState(new WaterJugState(2, 2));
    }

    /**
     * Returns whether the current state of this problem is a success. Note that
     * this method implements the abstract <b>success</b> method declared in the
     * parent. Note also that the current state of the problem must be gotten
     * using the inherited <b>getCurrentState()</b> method. Since that method
     * returns a value of type <b>State</b>, it must be cast to
     * <b>WaterJugState</b> before processing. The current state is a success if
     * either jug has 2 gallons.
     *
     * @return <b>true</b> if the current state is a success, <b>false</b>
     * otherwise
     */
    @Override
    public boolean success() {

        WaterJugState cur = (WaterJugState) getCurrentState();
        return cur.getAmountInJugX() == 2 || cur.getAmountInJugY() == 2;
    }

    // Private instance fields are not necessary since they are inherited.
    @Override
    public boolean isFinalState(State currentState) {

        WaterJugState cur = (WaterJugState) currentState;
        return cur.getAmountInJugX() == 2 || cur.getAmountInJugY() == 2;
    }
}
