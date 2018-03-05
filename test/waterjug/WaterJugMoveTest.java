package waterjug;

import framework_puzzle.Move;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test the WaterJugMove class.
 *
 * @author your name here
 */
public class WaterJugMoveTest {

    // You should use the BridgeMoveTest class as a model for setting up
    // the tests with private instance fields here.
    private WaterJugState state1 = new WaterJugState(0, 0); //Empty jug
    private WaterJugState state2 = new WaterJugState(2, 3);
    private WaterJugState state3 = new WaterJugState(3, 2);
    private WaterJugState state4 = new WaterJugState(1, 4);
    private WaterJugState state5 = new WaterJugState(3, 0);
    private WaterJugState state6 = new WaterJugState(0, 4);

    private WaterJugMove fillX = new WaterJugMove("Fill Jug X");
    private WaterJugMove fillY = new WaterJugMove("Fill Jug Y");
    private WaterJugMove emptyX = new WaterJugMove("Empty Jug X");
    private WaterJugMove emptyY = new WaterJugMove("Empty Jug Y");
    private WaterJugMove transferX = new WaterJugMove("Transfer Jug X to Jug Y");
    private WaterJugMove transferY = new WaterJugMove("Transfer Jug Y to Jug X");

    /**
     * Tests filling jug X
     */
    @Test
    public void testFillX() {
        WaterJugState next = (WaterJugState) fillX.doMove(state1);

        assertTrue(next.equals(new WaterJugState(3, 0)));
        assertTrue(next.getAmountInJugX() == 3);
        assertTrue(fillX.doMove(state3) == null);
    }

    /**
     * Tests filling jug Y
     */
    @Test
    public void testFillY() {
        WaterJugState next = (WaterJugState) fillY.doMove(state1);

        assertTrue(next.equals(new WaterJugState(0, 4)));
        assertTrue(next.getAmountInJugY() == 4);
        assertTrue(fillY.doMove(state4) == null);
    }

    /**
     * Tests emptying jug X
     */
    @Test
    public void testEmptyX() {
        WaterJugState next = (WaterJugState) emptyX.doMove(state2);

        assertTrue(next.equals(new WaterJugState(0, 3)));
        assertTrue(next.getAmountInJugX() == 0);
        assertTrue(emptyX.doMove(state1) == null);
    }

    /**
     * Tests emptying jug Y
     */
    @Test
    public void testEmptyY() {
        WaterJugState next = (WaterJugState) emptyY.doMove(state2);

        assertTrue(next.equals(new WaterJugState(2, 0)));
        assertTrue(next.getAmountInJugY() == 0);
        assertTrue(emptyY.doMove(state1) == null);
    }

    /**
     * Tests transferring jug X to jug Y
     */
    @Test
    public void testXToY() {
        WaterJugState next = (WaterJugState) transferX.doMove(state1);
        assertTrue(next == null);
        assertTrue(transferX.doMove(state3).equals(new WaterJugState(1, 4)));
        assertTrue(transferX.doMove(state4) == null);
        assertTrue(transferX.doMove(state5).equals(new WaterJugState(0, 3)));
    }

    /**
     * Tests transferring jug Y to jug X
     */
    @Test
    public void testYToX() {

        WaterJugState next = (WaterJugState) transferY.doMove(state1);
        assertTrue(next == null);
        assertTrue(transferY.doMove(state4).equals(new WaterJugState(3, 2)));
        assertTrue(transferY.doMove(state5) == null);
        assertTrue(transferY.doMove(state6).equals(new WaterJugState(3, 1)));
    }
}
