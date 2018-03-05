/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework_puzzle.test;

import blocks.BlockCanvas;
import blocks.BlockProblem;
import bridge.BridgeCanvas;
import bridge.BridgeProblem;
import framework_puzzle.GUI;
import hanoiTower.HanoiTowerCanvas;
import hanoiTower.HanoiTowerProblem;
import java.awt.ScrollPane;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import eight_puzzle.PuzzleCanvas;
import eight_puzzle.PuzzleProblem;
import waterjug.WaterJugCanvas;
import waterjug.WaterJugProblem;

/**
 *
 * @author user
 */
public class AppletFrame extends JApplet {

    @Override
    public void init() {
        JTabbedPane tabbedPane = new JTabbedPane();

        BridgeProblem bridgeProblem = new BridgeProblem();
        tabbedPane.add("Bridge",
                new GUI(bridgeProblem, new BridgeCanvas(bridgeProblem.getCurrentState()),
                        new BridgeCanvas(bridgeProblem.getFinalState())));

        WaterJugProblem waterProblem = new WaterJugProblem();
        tabbedPane.add("Water Jug", new GUI(waterProblem, new WaterJugCanvas(waterProblem.getCurrentState()),
                new WaterJugCanvas(waterProblem.getFinalState())));

        PuzzleProblem puzzleProblem = new PuzzleProblem();
        tabbedPane.add("8-Puzzle", new GUI(puzzleProblem, new PuzzleCanvas(puzzleProblem.getCurrentState()),
                new PuzzleCanvas(puzzleProblem.getFinalState())));

        BlockProblem blockProblem = new BlockProblem();
        tabbedPane.add("Blocks World", new GUI(blockProblem, new BlockCanvas(blockProblem.getCurrentState()),
                new BlockCanvas(blockProblem.getFinalState())));

        HanoiTowerProblem hanoiProblem = new HanoiTowerProblem();
        tabbedPane.add("Tower of Hanoi", new GUI(hanoiProblem, new HanoiTowerCanvas(hanoiProblem.getCurrentState()),
                new HanoiTowerCanvas(hanoiProblem.getFinalState())));

        ScrollPane wrapperPane = new ScrollPane();
        wrapperPane.add(tabbedPane);
        wrapperPane.setPreferredSize(tabbedPane.getPreferredSize());

        add(wrapperPane);
    }
}
