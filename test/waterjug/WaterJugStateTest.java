package waterjug;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test the WaterJugState class
 *
 * @author your name here
 */
public class WaterJugStateTest {

    private String state1String = "       |   |\n"
            + "|   |  |   |\n"
            + "|   |  |   |\n"
            + "|   |  |   |\n"
            + "+---+  +---+\n"
            + "  X      Y  \n";

    private String state2String = "       |***|\n"
            + "|***|  |***|\n"
            + "|***|  |***|\n"
            + "|***|  |***|\n"
            + "+---+  +---+\n"
            + "  X      Y  \n";

    // You may want to add more instance fields for testing
    /**
     * This method tests the accessors (getters) for <b>WaterJugState</b>
     * objects.
     */
    @Test
    public void testAccessors() {

        WaterJugState jugState1 = new WaterJugState(3, 2);
        WaterJugState jugState2 = new WaterJugState(2, 1);
        WaterJugState jugState3 = new WaterJugState(3, 4);
        WaterJugState jugState4 = new WaterJugState(2, 3);
        WaterJugState jugState5 = new WaterJugState(3, 4);
        
        assertTrue(jugState1.getAmountInJugX() == 3);
        assertTrue(jugState2.getAmountInJugY() == 1);
        assertTrue(jugState3.getAmountInJugX() == jugState5.getAmountInJugX());
        assertFalse(jugState3.getAmountInJugX() == 1);
        assertFalse(jugState4.getAmountInJugY() == 0);
        
    }

    /**
     * This method tests the <b>equals</b> method for <b>WaterJugState</b>
     * objects.
     */
    @Test
    public void testEquals() {
        WaterJugState jugState1 = new WaterJugState(3, 2);
        WaterJugState jugState2 = new WaterJugState(2, 1);
        WaterJugState jugState3 = new WaterJugState(3, 4);
        WaterJugState jugState4 = new WaterJugState(2, 3);
        WaterJugState jugState5 = new WaterJugState(3, 4);

        assertFalse(jugState1.equals(jugState2));
        assertFalse(jugState2.equals(jugState3));
        assertFalse(jugState1.equals(jugState4));

        assertTrue(jugState3.equals(jugState5));
    }

    /**
     * This method tests the <b>toString</b> method for <b>WaterJugState</b>
     * objects. Look at the definitions of <b>state1String</b> and
     * <b>state2String</b> to see how <b>toString</b> should format a state's
     * string representation.
     */
    @Test
    public void testToString() {
        WaterJugState jugState1 = new WaterJugState(0, 0);
        WaterJugState jugState2 = new WaterJugState(3, 4);
        WaterJugState jugState3 = new WaterJugState(2, 4);
        
        assertTrue(jugState1.toString().equals(state1String));
        assertTrue(jugState2.toString().equals(state2String));
        assertFalse(jugState3.toString().equals(state2String));
        
        

    }
}
