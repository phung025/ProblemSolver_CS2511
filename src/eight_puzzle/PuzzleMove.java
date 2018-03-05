/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eight_puzzle;

import framework_puzzle.Move;
import framework_puzzle.State;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author user
 */
public class PuzzleMove extends Move {

    public PuzzleMove(String moveName) {
        super(moveName);
    }

    /**
     * Return new move from a state
     *
     * @param state
     * @return new state according to move command, null if not able to do
     */
    @Override
    public State doMove(State state) {

        PuzzleState currentState = (PuzzleState) state;

        //Get the index of the tile about to be slided
        int tileIndex = currentState.getPositionOfTile(getMoveName().charAt(getMoveName().length() - 1));

        //Array containing directions that a tile can be slided to
        ArrayList directionIndices = new ArrayList();

        directionIndices.add(currentState.outOfBoundary(tileIndex - 3) ? -1 : tileIndex - 3);
        directionIndices.add(currentState.outOfBoundary(tileIndex - 1) ? -1 : tileIndex - 1);
        directionIndices.add(currentState.outOfBoundary(tileIndex + 1) ? -1 : tileIndex + 1);
        directionIndices.add(currentState.outOfBoundary(tileIndex + 3) ? -1 : tileIndex + 3);

        Iterator<Object> iter = directionIndices.iterator();
        
        //Clear all illegal moves from the directionIndices
        while (iter.hasNext()) {
            int index = (Integer) iter.next();

            if (index != -1) {

                int openX = index % 3;
                int openY = index / 3;

                int tileX = tileIndex % 3;
                int tileY = tileIndex / 3;
                
                if (openX != tileX && openY != tileY)
                    iter.remove();
            } else {
                iter.remove();
            }
        }

        //Current position of all tiles
        char[] currentTiles = currentState.getTiles().clone();

        //Index of the "open" tile
        int openTileIndex = String.valueOf(currentTiles).indexOf(' ');

        //If the tile can not be slided, return null
        if (directionIndices.indexOf(openTileIndex) == -1) {
            return null;
        }

        //Swapping the tiles
        char tileA = currentTiles[tileIndex];
        currentTiles[tileIndex] = currentTiles[openTileIndex];
        currentTiles[openTileIndex] = tileA;

        PuzzleState newState = new PuzzleState(currentTiles);

        return newState;
    }

}
