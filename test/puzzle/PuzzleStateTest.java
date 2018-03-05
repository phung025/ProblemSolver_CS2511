/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

import eight_puzzle.PuzzleState;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class PuzzleStateTest {

    String state = "-------------\n"
            + "+ 1 + 2 + 3 +\n"
            + "+---+---+---+\n"
            + "+ 8 +   + 4 +\n"
            + "+---+---+---+\n"
            + "+ 7 + 6 + 5 +\n"
            + "+---+---+---+\n";

    @Test
    public void testToString() {
        char[] tiles = new char[]{'1','2','3','8',' ','4','7','6','5'};
        PuzzleState state1 = new PuzzleState(tiles);
        System.out.println(state1.toString());
        assertTrue(state1.toString().equals(state));

    }
}
