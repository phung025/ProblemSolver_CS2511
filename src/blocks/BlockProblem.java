package blocks;

import framework_puzzle.Canvas;
import framework_puzzle.Move;
import framework_puzzle.Problem;
import framework_puzzle.State;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a problem in the Blocks World.
 * @author tcolburn
 */
public class BlockProblem extends Problem {
    
    /**
     * This constructor stores an introductory message describing the problem,
     * the problem's possible moves, the initial state, and final state,
     * by sending them to the parent <b>Problem</b> class.
     * It also creates and sends lists of other initial states and final states
     * for selection in a problem chooser.
     */
    public BlockProblem() {
        
        List<Move> moves = new ArrayList<Move>();
        moves.add(new BlockMove("Move block from p to q"));
        moves.add(new BlockMove("Move block from p to r"));
        moves.add(new BlockMove("Move block from q to p"));
        moves.add(new BlockMove("Move block from q to r"));
        moves.add(new BlockMove("Move block from r to p"));
        moves.add(new BlockMove("Move block from r to q"));
        
        BlockState start1 = new BlockState("AB","","");
        BlockState final1 = new BlockState("","AB","");

        BlockState start2 = new BlockState("ABC","","");
        BlockState final2 = new BlockState("","ABC","");

        BlockState start3 = new BlockState("","AB","CD");
        BlockState final3 = new BlockState("AB","CD","");

        BlockState start4 = new BlockState("ABCD","","");
        BlockState final4 = new BlockState("","BCAD","");

        BlockState start5 = new BlockState("AD","BE","CF");
        BlockState final5 = new BlockState("DA","EB","FC");

        BlockState start6 = new BlockState("ADG","BEH","CFI");
        BlockState final6 = new BlockState("BEH","IFC","GDA");

        BlockState start7 = new BlockState("ABC", "DEF", "GHI");  // a complex problem
        BlockState final7 = new BlockState("ABF", "DEI", "GHC");

        List<State> startStates = new ArrayList<State>();
        List<State> finalStates = new ArrayList<State>();
        List<Integer> moveCounts = new ArrayList<Integer>();

        startStates.add(start1);  finalStates.add(final1);  moveCounts.add(3);
        startStates.add(start2);  finalStates.add(final2);  moveCounts.add(5);
        startStates.add(start3);  finalStates.add(final3);  moveCounts.add(6);
        startStates.add(start4);  finalStates.add(final4);  moveCounts.add(8);
        startStates.add(start5);  finalStates.add(final5);  moveCounts.add(12);
        startStates.add(start6);  finalStates.add(final6);  moveCounts.add(17);
        startStates.add(start7);  finalStates.add(final7);  moveCounts.add(21);
        
        setInitialStates(startStates);
        setFinalStates(finalStates);
        setMoveCounts(moveCounts);
        
        setCurrentState(start1);
        setFinalState(final1);
        
        List<Canvas>initialCanvas = new ArrayList();
        for (State s : startStates) {
            initialCanvas.add(new BlockCanvasIconified(s));
        }
        
        List<Canvas>finalCanvas = new ArrayList();
        for (State s : finalStates) {
            finalCanvas.add(new BlockCanvasIconified(s));
        }
        
        setInitialCanvas(initialCanvas);
        setFinalCanvas(finalCanvas);

        setMoves(moves);
        setIntroduction(
                "Welcome to the Blocks Problem\n\n"
                + "The blocks world consists of a table top with three places p, q, and r,\n"
                + "and a variable number of blocks A, B, C, etc. that can be arranged in \n"
                + "places on the table or stacked on one another.  A legal action is to \n"
                + "move a block from one place or block onto another place or block, with\n"
                + "the only restrictions being that the moved block must not have another \n"
                + "block on top of it, and no other blocks are moved in the process.");
    }
    
    /**
     * Checks for whether this problem has been successfully solved.
     * @return true if the current state is equal to the final state,
     * false otherwise
     */
    @Override
    public boolean success() {
        BlockState currentState = (BlockState) getCurrentState();
        BlockState finalState = (BlockState) getFinalState();
        return currentState.equals(finalState);
    }

    @Override
    public boolean isFinalState(State currentState) {
        return currentState.equals(this.getFinalState());
    }
}
