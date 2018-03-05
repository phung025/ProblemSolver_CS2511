/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eight_puzzle;

import framework_puzzle.State;
import graph.SimpleVertex;
import java.util.Arrays;

/**
 *
 * @author Nam Phung
 */
public class PuzzleState extends SimpleVertex implements State {

    public PuzzleState(char[] num) {
        list = num;
    }

    /**
     * Tests for equality between this state and the argument state.
     * Implementing classes will need to cast the argument to a specific class
     * type.
     *
     * @param other the state to test against this state
     * @return true if this state and the other are equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {

        PuzzleState compareState = (PuzzleState) other;
        return Arrays.equals(this.getTiles(), compareState.getTiles());
    }

    @Override
    public int hashCode() {
        long hash = 7;
        hash += 83 * hash + Arrays.hashCode(this.list);
        String tiles = String.valueOf(list);
        hash += 31 * tiles.hashCode();
        hash = hash >>> 16;
        return (int) hash;
    }

    /**
     * Creates a primitive, non-GUI representation of this State object.
     *
     * @return the string representation of this state
     */
    @Override
    public String toString() {

        String state = "-------------\n"
                + "+ " + list[0] + " + " + list[1] + " + " + list[2] + " +\n"
                + "+---+---+---+\n"
                + "+ " + list[3] + " + " + list[4] + " + " + list[5] + " +\n"
                + "+---+---+---+\n"
                + "+ " + list[6] + " + " + list[7] + " + " + list[8] + " +\n"
                + "+---+---+---+\n";
        return state;
    }

    /**
     * Get current position of tile i on the array
     *
     * @param c char represents the tile name
     * @return position of the tile, -1 if not found
     */
    public int getPositionOfTile(char c) {
        return String.valueOf(list).indexOf(c);
    }

    /**
     * Check to see if the index is greater than array.length-1
     *
     * @param i
     * @return true if (i > length-1) else false
     */
    public boolean outOfBoundary(int i) {
        return i > list.length - 1;
    }

    /**
     * Get all position of the tiles stored in an array of type char
     *
     * @return all tiles in the current state together with their position
     */
    public char[] getTiles() {
        return this.list;
    }

    /**
     * Instance field
     */
    /*Array representing the slots in the puzzle
    *
    *    0  1  2 
    *   ________
    * 0|__|__|__|       _____________________________________
    * 1|__|__|__|   ==> | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
    * 2|__|__|__|       -------------------------------------
     */
    private char[] list = new char[9];

    /**
     * Get heuristic values of the current state
     *
     * @return heuristic values in the puzzle state
     * <b>h[0] = Number of tiles out of place</b>
     * <b>h[1] = Sum of Manhattan distance</b>
     */
    @Override
    public int getHeuristic(State finalState) {

        int heuristicValue = 0;

        //Get number of tiles out of place
        for (int i = 0; i < list.length; ++i) {
            
            if (list[i] != ' ') {
                if (list[i] != ((PuzzleState) finalState).list[i]) {
                    ++heuristicValue;
                }
            }
        }

        //Get the Manhattan sum
        String finalTilesPosition = String.valueOf(((PuzzleState)finalState).list);
        String currentTilesPosition = String.valueOf(this.list);
        for (char c : list) {
            //Don't consider "open tile" is a real tile
            if (c != ' ') {
                int currentTileIndex = currentTilesPosition.indexOf(c);
                int finalTileIndex = finalTilesPosition.indexOf(c);

                heuristicValue += getManhattanDistance(currentTileIndex, finalTileIndex);
            }
        }
        
        return heuristicValue;
    }

    /**
     * Get Manhattan distance of 2 tiles
     * @param i index of tile 1
     * @param f index of tile 2
     * @return Manhattan distance of 2 tiles
     */
    private int getManhattanDistance(int i, int f) {
        return Math.abs((f%3)-(i%3))+Math.abs((f/3)-(i/3));
    }
}
