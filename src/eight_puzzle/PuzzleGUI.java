/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eight_puzzle;

import framework_puzzle.GUI;
import javax.swing.JFrame;

/**
 *
 * @author user
 */
public class PuzzleGUI extends JFrame {

    public PuzzleGUI() {

        PuzzleProblem puzzleProblem = new PuzzleProblem();

        add("8-Puzzle", new GUI(puzzleProblem,
                new PuzzleCanvas(puzzleProblem.getCurrentState()),
                new PuzzleCanvas(puzzleProblem.getFinalState())));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * This method launches the gui.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        new PuzzleGUI();
    }
}
