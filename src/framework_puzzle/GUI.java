package framework_puzzle;

import graph.Vertex;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 * A class that creates GUI components for solving search problems.
 *
 * @author Nam Phung
 */
public class GUI extends JComponent {

    /**
     *
     * @param problem the problem the program will display
     * @param aCanvas canvas that display the initial state
     * @param finalStateCanvas canvas that display the final state
     */
    public GUI(Problem problem, Canvas aCanvas, Canvas finalStateCanvas) {

        //Load problem and canvas
        this.problem = problem;
        initialState = this.problem.getCurrentState();
        moves = problem.getMoves();
        moveCount = 0;
        this.stateCanvas = aCanvas;//this.problem.getIntialCanvas().get(0);
        this.finalStateCanvas = finalStateCanvas;

        //Set up all auto solving options
        setUpSearchTypes();
        setUpShowSolutionButtons();

        //Set GUI Layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Introduction text area
        introductionTextArea.setText(problem.getIntroduction());
        introductionTextArea.setEditable(false);
        introductionTextArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));

        //Add current state and final state visualization
        final JPanel currentStatePanel = new JPanel();
        currentStatePanel.add(stateCanvas);
        currentStatePanel.setBorder(BorderFactory.createTitledBorder("Current State"));
        updateCurrentStateGraphic();

        final JPanel finalStatePanel = new JPanel();
        finalStatePanel.add(this.finalStateCanvas);
        finalStatePanel.setBorder(BorderFactory.createTitledBorder("Final State"));

        //Move buttons panel
        movesPanel.setBorder(BorderFactory.createTitledBorder("Possible Moves"));
        movesPanel.setLayout(new GridLayout(moves.size(), 1));

        //Add move buttons to the panel
        for (int i = 0; i < moves.size(); ++i) {

            JButton moveButton = new JButton();

            //Set button text
            moveButton.setText(moves.get(i).getMoveName());

            //Get index of the move in the list
            final int moveIndex = i;

            moveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Process move
                    processMove(moveIndex);
                }
            });

            //Add button to container
            movesPanel.add(moveButton);
        }

        //Add components to bottom panel
        JPanel solvePanel = new JPanel();
        solvePanel.setLayout(new BoxLayout(solvePanel, BoxLayout.Y_AXIS));

        //Group the search algorithm radio buttons together
        ButtonGroup algorithms = new ButtonGroup();
        algorithms.add(bfsButton);
        algorithms.add(dfsButton);
        algorithms.add(aStarButton);
        algorithms.add(enhanceAStarButton);

        //Panel containing search algorithms radio buttons
        JPanel searchTypes = new JPanel();
        searchTypes.setLayout(new BoxLayout(searchTypes, BoxLayout.Y_AXIS));
        searchTypes.setBorder(BorderFactory.createTitledBorder("Search types"));
        searchTypes.add(dfsButton);
        searchTypes.add(bfsButton);
        searchTypes.add(aStarButton);
        searchTypes.add(enhanceAStarButton);

        //Add all sub-components to wrapper panel
        solvePanel.add(searchTypes);
        solvePanel.add(solveProblemButton);

        //Add reset button to bottom panel
        resetButton.setText("RESET");
        resetButton.addActionListener(event -> {
            resetProblem();
        });

        //Add algorithm's statistic to the bottom panel
        JPanel statisticPanel = new JPanel();
        statisticPanel.setLayout(new BoxLayout(statisticPanel, BoxLayout.Y_AXIS));
        statisticPanel.setBorder(BorderFactory.createTitledBorder("Solution Statistics"));
        statisticPanel.add(solutionLengthLabel);
        statisticPanel.add(dequeOperationLabel);
        statisticPanel.add(maxDequeSizeLabel);
        statisticPanel.add(openedNodeLabel);
        statisticPanel.add(closedNodeLabel);
        statisticPanel.add(showNextMoveButton);
        showNextMoveButton.setEnabled(false);
        statisticPanel.add(showAllMovesButton);
        showAllMovesButton.setEnabled(false);

        //Add the wrapper panels to outer panel
        topPanel.add(introductionTextArea);
        add(topPanel);

        midPanel.add(currentStatePanel);
        midPanel.add(movesPanel);
        midPanel.add(finalStatePanel);
        add(midPanel);

        //Add solve panel, reset button, stats panel, etc to the bottom panel
        bottomPanel.add(solvePanel);
        bottomPanel.add(resetButton);
        bottomPanel.add(statisticPanel);
        add(bottomPanel);

    }

    /**
     * Set up the search types action listener for the radio button
     */
    private void setUpSearchTypes() {

        //Solve button action listener for breadth first search
        solveProblemButton.addActionListener(event -> {
            
            if (bfsButton.isSelected()) {
                solution = problem.breadthFirstSearch((Vertex) problem.getCurrentState());
            } else if (dfsButton.isSelected()) {
                solution = problem.depthFirstSearch((Vertex) problem.getCurrentState());
            } else if (aStarButton.isSelected()) {
                solution = problem.aStarSearch((Vertex) problem.getCurrentState());
            } else if (enhanceAStarButton.isSelected()) {
                solution = problem.enhancedAStarSearch((Vertex) problem.getCurrentState());
            } else {
                return;
            }
            
            //If solution is found
            if (solution != null) {
                //Display statistics
                solutionLengthLabel.setText(solutionLengthLabel.getText().substring(0, 16) + " " + solution.size());
                dequeOperationLabel.setText(dequeOperationLabel.getText().substring(0, 23) + " " + problem.getQueueOps());
                maxDequeSizeLabel.setText(maxDequeSizeLabel.getText().substring(0, 18) + " " + problem.getMaxQueueSize());
                openedNodeLabel.setText(openedNodeLabel.getText().substring(0, 25) + " " + problem.getOpenedRediscovered());
                closedNodeLabel.setText(closedNodeLabel.getText().substring(0, 27) + " " + problem.getClosedRediscovered());
                
                //Disable solve problem
                solveProblemButton.setEnabled(false);
                showNextMoveButton.setEnabled(true);
                showAllMovesButton.setEnabled(true);
            }
        });

    }

    /**
     * Set up all action listeners for the show move buttons
     */
    private void setUpShowSolutionButtons() {

        //When user press the button, it will show next move to get to the solution
        showNextMoveButton.addActionListener((ActionEvent e) -> {
            if (solution == null || solution.isEmpty()) {
                return;
            }
            
            //Pop out the solution step and change current state
            State nextState = (State) solution.pop();
            getProblem().setCurrentState(nextState);
            
            //Update the graphics
            updateCurrentStateGraphic();
        });

        //This button will provoke the button show next move using a timer
        showAllMovesButton.addActionListener((ActionEvent e) -> {
            //Disable show all move button while it's running
            showAllMovesButton.setEnabled(false);
            
            //Create timer for auto displaying solution
            final Timer t = new Timer(1000, null);
            
            //Add ation listener for the timer
            t.addActionListener((ActionEvent e1) -> {
                showNextMoveButton.doClick();
                
                //Stop timer when finish displaying solution moves
                if (solution.isEmpty()) {
                    t.stop();
                }
            });
            
            //Start timer
            t.start();
        });

    }

    /**
     * Reset the current problem
     */
    private void resetProblem() {

        if (this.problem.getInitialStates().isEmpty()) {

            //Reset state
            this.problem.setCurrentState(initialState);
        } else {

            ProblemChooser problemChooser = new ProblemChooser(problem.getInitialStates(),
                    problem.getFinalStates(),
                    problem.getMoveCounts(),
                    problem.getInitialCanvas(),
                    problem.getFinalCanvas());

            this.problem.setCurrentState(problemChooser.getStart());
            this.problem.setFinalState(problemChooser.getFinal());
        }

        //Clear the solution stack
        solution.removeAllElements();

        //Reset the stats
        solutionLengthLabel.setText(solutionLengthLabel.getText().substring(0, 16));
        dequeOperationLabel.setText(dequeOperationLabel.getText().substring(0, 23));
        maxDequeSizeLabel.setText(maxDequeSizeLabel.getText().substring(0, 18));
        openedNodeLabel.setText(openedNodeLabel.getText().substring(0, 25));
        closedNodeLabel.setText(closedNodeLabel.getText().substring(0, 27));

        //Enable solve problem button
        solveProblemButton.setEnabled(true);
        showNextMoveButton.setEnabled(false);
        showAllMovesButton.setEnabled(false);

        //Update graphics, reset count
        updateCurrentStateGraphic();
        moveCount = 0;
    }

    /**
     * Update the canvas object for graphical illustration
     */
    private void updateCurrentStateGraphic() {

        //Get current state of problem
        stateCanvas.setCurrentState(getProblem().getCurrentState());
        finalStateCanvas.setCurrentState(getProblem().getFinalState());
                
        //Repaint the state
        stateCanvas.render();
        finalStateCanvas.render();
    }

    /**
     * Process the move when user clicked a button
     *
     * @param moveIndex
     */
    private void processMove(int moveIndex) {

        Move move = moves.get(moveIndex);

        State newState = move.doMove(getProblem().getCurrentState());
        if (newState == null) {
            display("That move is not possible. Try again.\n");
        } else {
            getProblem().setCurrentState(newState);
            updateCurrentStateGraphic();
            ++moveCount;
        }

        if (this.problem.success()) {
            display("Congratulations. You solved the problem using "
                    + moveCount + " moves.\n");
        }

    }

    /**
     * Display a dialog box with input text
     *
     * @param input
     */
    private void display(String input) {
        JOptionPane.showMessageDialog(null, input);
    }

    /**
     * Getter for the number of moves the user has tried so far.
     *
     * @return the move count
     */
    private int getMoveCount() {
        return moveCount;
    }

    /**
     * Setter for the number of moves the user has tried so far
     *
     * @param moveCount the new move count
     */
    private void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    /**
     * Getter for the problem object associated with this console.
     *
     * @return this console's problem object
     */
    private Problem getProblem() {
        return problem;
    }

    /**
     * Top panel containing introduction text
     */
    final private JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    /**
     * Introduction text area
     */
    final private JTextArea introductionTextArea = new JTextArea();

    /**
     * Mid panel containing current state & move buttons
     */
    final private JPanel midPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    /**
     * Panel containing move buttons
     */
    final private JPanel movesPanel = new JPanel();

    /**
     * Bottom panel containing reset button
     */
    final private JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    /**
     * Reset problem button
     */
    final private JButton resetButton = new JButton();

    /**
     * The puzzle problem object
     */
    final private Problem problem;

    /**
     * The legal moves for the problem.
     */
    final private List<Move> moves;

    /**
     * Number of moves user has taken in solving the problem.
     */
    private int moveCount;

    /**
     * Initial state of the problem
     */
    final private State initialState;

    /**
     * Canvas object for displaying current state of problem
     */
    private Canvas stateCanvas = null;

    /**
     * Canvas object for displaying final state of problem
     */
    private Canvas finalStateCanvas = null;

    /**
     * Radio button for depth-first search option
     */
    final private JRadioButton dfsButton = new JRadioButton("Depth-first");

    /**
     * Radio button for breadth-first search option
     */
    final private JRadioButton bfsButton = new JRadioButton("Breadth-first");

    /**
     * Radio button for A* search option
     */
    final private JRadioButton aStarButton = new JRadioButton("Regular A*");

    /**
     * Radio button for enhanced A* search option
     */
    final private JRadioButton enhanceAStarButton = new JRadioButton("Enhanced A*");

    /**
     * Button used for solving problem with different algorithms
     */
    final private JButton solveProblemButton = new JButton("SOLVE");

    /**
     * Solution length label
     */
    final private JLabel solutionLengthLabel = new JLabel("Solution Length: ");

    /**
     * Deque operation label
     */
    private final JLabel dequeOperationLabel = new JLabel("Number of DEQUE/PQ ops: ");

    /**
     * Max deque size label
     */
    private final JLabel maxDequeSizeLabel = new JLabel("Max DEQUE/PQ size: ");

    /**
     * Number of opened nodes rediscovered label
     */
    private final JLabel openedNodeLabel = new JLabel("Num of Open Rediscoveries: ");

    /**
     * Number of closed nodes rediscovered label
     */
    private final JLabel closedNodeLabel = new JLabel("Num of Closed Rediscoveries: ");

    /**
     * Button show next move after running search algorithm
     */
    private final JButton showNextMoveButton = new JButton("Show next move");

    /**
     * Show all moves button, it will run timer and display all moves to get to
     * final solution
     */
    private final JButton showAllMovesButton = new JButton("Show all moves");

    /**
     * The stack containing all moves that lead to the solution
     */
    private Stack<Vertex> solution = new Stack<>();

}
