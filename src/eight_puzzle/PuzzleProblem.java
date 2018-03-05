/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eight_puzzle;

import framework_puzzle.Canvas;
import framework_puzzle.Move;
import framework_puzzle.Problem;
import framework_puzzle.State;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author user
 */
public class PuzzleProblem extends Problem {

    public PuzzleProblem() {

        //Set introduction
        super.setIntroduction("Welcome to the 8-Puzzle Problem\n"
                + "\n"
                + "You are given a 3x3 board in which 8 numbered tiles can slide around.\n"
                + "There is one blank space that holds no tile.  A legal move consists\n"
                + "of sliding a tile into the blank space if the tile is adjacent to it.\n"
                + "The goal is to move tiles around until the board looks like the final\n"
                + "state below.\n"
                + "            +---+---+---+\n"
                + "            | 1 | 2 | 3 |\n"
                + "            +---+---+---+\n"
                + "            | 8 |   | 4 |\n"
                + "            +---+---+---+\n"
                + "            | 7 | 6 | 5 |\n"
                + "            +---+---+---+");

        //Set list of moves
        ArrayList<Move> availableMove = new ArrayList<Move>();

        availableMove.add(new PuzzleMove("Slide Tile 1"));
        availableMove.add(new PuzzleMove("Slide Tile 2"));
        availableMove.add(new PuzzleMove("Slide Tile 3"));
        availableMove.add(new PuzzleMove("Slide Tile 4"));
        availableMove.add(new PuzzleMove("Slide Tile 5"));
        availableMove.add(new PuzzleMove("Slide Tile 6"));
        availableMove.add(new PuzzleMove("Slide Tile 7"));
        availableMove.add(new PuzzleMove("Slide Tile 8"));

        super.setMoves(availableMove);

        //Set initial state
        PuzzleState initialState = new PuzzleState(new char[]{'2', '8', '3', '1', '6', '4', '7', ' ', '5'});
        super.setCurrentState(initialState);

        //Set the final State
        PuzzleState successState = new PuzzleState(new char[]{'1', '2', '3', '8', ' ', '4', '7', '6', '5'});
        this.setFinalState(successState);

        //Provide different problems used for the problem chooser
        List<State> initialStates = new LinkedList();
        initialStates.add(new PuzzleState(new char[]{'2', '8', '3', '1', '6', '4', '7', ' ', '5'}));
        initialStates.add(new PuzzleState(new char[]{'3', '6', '4', '1', ' ', '2', '8', '7', '5'}));
        initialStates.add(new PuzzleState(new char[]{'3', ' ', '4', '1', '6', '5', '8', '2', '7'}));
        initialStates.add(new PuzzleState(new char[]{'2', '1', '3', '8', ' ', '4', '6', '7', '5'}));
        initialStates.add(new PuzzleState(new char[]{'4', '2', ' ', '8', '3', '6', '7', '5', '1'}));
        initialStates.add(new PuzzleState(new char[]{'1', '6', '3', '4', ' ', '8', '7', '2', '5'}));
        initialStates.add(new PuzzleState(new char[]{'5', '2', '7', '8', ' ', '4', '3', '6', '1'}));
        initialStates.add(new PuzzleState(new char[]{'5', '6', '7', '4', ' ', '8', '3', '2', '1'}));

        this.setInitialStates(initialStates);

        //Add final states
        List<State> finalStates = new LinkedList();
        State finalState = new PuzzleState(new char[]{'1', '2', '3', '8', ' ', '4', '7', '6', '5'});
        finalStates.add(finalState);
        finalStates.add(finalState);
        finalStates.add(finalState);
        finalStates.add(finalState);
        finalStates.add(finalState);
        finalStates.add(finalState);
        finalStates.add(finalState);
        finalStates.add(finalState);

        this.setFinalStates(finalStates);

        //Add move counts
        List<Integer> moveCounts = new LinkedList();
        moveCounts.add(5);
        moveCounts.add(10);
        moveCounts.add(13);
        moveCounts.add(18);
        moveCounts.add(20);
        moveCounts.add(24);
        moveCounts.add(30);
        moveCounts.add(30);

        this.setMoveCounts(moveCounts);

        //Add iconified intial canvas
        List<Canvas> initialCanvas = new LinkedList();
        for (State i : initialStates) {
            initialCanvas.add(new PuzzleCanvasIconified(i));
        }

        this.setInitialCanvas(initialCanvas);

        //Add iconified final canvas
        List<Canvas> finalCanvas = new LinkedList();
        for (State i : finalStates) {
            finalCanvas.add(new PuzzleCanvasIconified(i));
        }

        this.setFinalCanvas(finalCanvas);
    }

    @Override
    public boolean success() {
        return this.getCurrentState().equals(this.getFinalState());
    }

    @Override
    public boolean isFinalState(State currentState) {
        return currentState.equals(this.getFinalState());
    }
}
