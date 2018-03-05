package framework_puzzle.test;

import blocks.BlockCanvas;
import blocks.BlockProblem;
import bridge.BridgeCanvas;
import bridge.BridgeProblem;

import waterjug.WaterJugProblem;
import waterjug.WaterJugCanvas;

import framework_puzzle.GUI;
import hanoiTower.HanoiTowerCanvas;
import hanoiTower.HanoiTowerProblem;
import java.awt.ScrollPane;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import eight_puzzle.PuzzleCanvas;
import eight_puzzle.PuzzleProblem;

/**
 * A class to display the bridge crossing and water jug problems in a tabbed
 * pane within an application frame.
 *
 * @author tcolburn
 */
public class TestFrame {

    public TestFrame() {

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

        JFrame frame = new JFrame("Problem solver");
        frame.add(wrapperPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new TestFrame();
    }

}
