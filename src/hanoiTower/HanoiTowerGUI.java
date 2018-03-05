/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hanoiTower;

import framework_puzzle.GUI;
import javax.swing.JFrame;

/**
 * This is the Tower of Hanoi problem. I want to thank Dr. Timothy Colburn at 
 * the University of Minnesota Duluth for the design of the framework, the problemChooser class, etc. 
 * so that I could implement this problem and its algorithm.
 * @author Nam Phung
 */
public class HanoiTowerGUI extends JFrame {

    public HanoiTowerGUI() {

        HanoiTowerProblem problem = new HanoiTowerProblem();

        add(new GUI(problem,
                new HanoiTowerCanvas(problem.getCurrentState()),
                new HanoiTowerCanvas(problem.getFinalState())));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * This method launches the GUI.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        new HanoiTowerGUI();
    }
}
