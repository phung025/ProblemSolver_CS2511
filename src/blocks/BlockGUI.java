/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocks;

import framework_puzzle.GUI;
import javax.swing.JFrame;

/**
 *
 * @author Nam Phung
 */
public class BlockGUI extends JFrame {
    public BlockGUI() {

        BlockProblem blockProblem = new BlockProblem();

        // Add game frame
        add(new GUI(blockProblem,
                new BlockCanvas(blockProblem.getCurrentState()),
                new BlockCanvas(blockProblem.getFinalState())));
        setTitle("Blocks World");
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
        new BlockGUI();
    }
}
