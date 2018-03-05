package bridge;

import framework_puzzle.GUI;
import javax.swing.JFrame;

/**
 * A class to test your GUI class on the bridge crossing problem.
 *
 * @author tcolburn
 */
public class BridgeGUI extends JFrame {

    public BridgeGUI() {

        BridgeProblem problem = new BridgeProblem();

        add(new GUI(problem,
                new BridgeCanvas(problem.getCurrentState()),
                new BridgeCanvas(problem.getFinalState())));
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
        new BridgeGUI();
    }

}
