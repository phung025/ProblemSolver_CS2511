package waterjug;

import java.util.List;
import framework_puzzle.Problem;
import framework_puzzle.State;
import framework_puzzle.Move;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test the WaterJugProblem class. You should use the
 * BridgeProblemTest class as a model.
 *
 * @author your name here
 */
public class WaterJugProblemTest {

    // You should use the BridgeProblemTest class as a model for setting up
    // the tests with private instance fields and methods here.
    Problem problem = new WaterJugProblem();

    List<Move> moves = problem.getMoves();

    Move fillX = null;
    Move fillY = null;
    Move emptyX = null;
    Move emptyY = null;
    Move transferX = null;
    Move transferY = null;

    private Move getMove(String moveName) {
        for (Move move : moves) {
            if (move.getMoveName().equals(moveName)) {
                return move;
            }
        }

        return null;
    }

    /**
     *
     */
    private void initializeMoves() {

        fillX = getMove("Fill Jug X");
        fillY = getMove("Fill Jug Y");
        emptyX = getMove("Empty Jug X");
        emptyY = getMove("Empty Jug Y");
        transferX = getMove("Transfer Jug X to Jug Y");
        transferY = getMove("Transfer Jug Y to Jug X");
    }

    /**
     * Tests to be sure the problem represents all the moves.
     */
    @Test
    public void testMoves() {

        this.initializeMoves();

        assertTrue(fillX != null);
        assertTrue(fillY != null);
        assertTrue(emptyX != null);
        assertTrue(emptyY != null);
        assertTrue(transferX != null);
        assertTrue(transferY != null);
    }

    private void tryMove(Move move) {
        State state = problem.getCurrentState();
        State next = move.doMove(state);
        problem.setCurrentState(next);
    }

    /**
     * Tests the 4-move solution to the water jug problem
     */
    @Test
    public void testSolution1() {
        // Fill X
        // Transfer X to Y
        // Fill X
        // Transfer X to Y
        initializeMoves();
        
        this.tryMove(fillX);
        assertFalse(problem.success());
        
        this.tryMove(transferX);
        assertFalse(problem.success());
        
        this.tryMove(fillX);
        assertFalse(problem.success());
        
        this.tryMove(transferX);
        assertTrue(problem.success());
    }

    /**
     * Tests the 6-move solution to the water jug problem
     */
    @Test
    public void testSolution2() {
        // Fill Y
        // Transfer Y to X
        // Empty X
        // Transfer Y to X
        // Fill Y
        // Transfer Y to X
        this.initializeMoves();
        
        this.tryMove(fillY);
        assertFalse(problem.success());
        
        this.tryMove(transferY);
        assertFalse(problem.success());
        
        this.tryMove(emptyX);
        assertFalse(problem.success());
        
        this.tryMove(transferY);
        assertFalse(problem.success());
        
        this.tryMove(fillY);
        assertFalse(problem.success());
        
        this.tryMove(transferY);
        assertTrue(problem.success());
        
    }

    /**
     * Tests the problem's introduction string.
     */
    @Test
    public void testIntro() {
        String introduction = "Welcome to the Water Jug Problem.\n"
                + "\n"
                + "You are given two empty jugs: jug X holds 3 gallons, jug Y holds 4.\n"
                + "Neither has any measuring markers on it. You have a ready supply\n"
                + "of water. You can fill either jug, empty either jug on the ground,\n"
                + "or pour all or some of either jug into the other.  The goal is to\n"
                + "get exactly 2 gallons of water into either jug.\n"
                + "\n"
                + "Here is your initial state:\n";
    }
}
