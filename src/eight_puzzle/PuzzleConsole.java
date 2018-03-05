/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eight_puzzle;

import framework_puzzle.Console;

/**
 *
 * @author user
 */
public class PuzzleConsole {
 
     /**
     * This method launches the console.
     * @param args ignored
     */
    public static void main(String[] args) {
        Console console = new Console(new PuzzleProblem());
        console.start();
    } 
}
