package waterjug;

import framework_puzzle.GUI;
import javax.swing.JFrame;

/**
 * A class to test your GUI class on the water jug problem.
 *
 * @author tcolburn
 */
public class WaterJugGUI extends JFrame {

    public WaterJugGUI() {

        WaterJugProblem waterProblem = new WaterJugProblem();

        add("Water Jug", new GUI(waterProblem,
                new WaterJugCanvas(waterProblem.getCurrentState()),
                new WaterJugCanvas(waterProblem.getFinalState())));
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
        new WaterJugGUI();
    }

}
