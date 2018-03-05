package bridge;

import framework_puzzle.State;
import java.util.Arrays;
import graph.SimpleVertex;

/**
 * This class represents states of the Bridge Crossing problem. It creates new
 * bridge states, tests states for equality, and produces string representations
 * of them. Note that this class implements the <b>State</b> interface and
 * therefore imports <b>framework.State</b>. Except for the import and the class
 * header, this class can be the same as in the previous assignment.
 *
 * @author your name here
 */
public class BridgeState extends SimpleVertex implements State {

    /**
     * Creates a new bridge state. Besides storing the positions of the persons
     * and flashlight, a bridge state should also store the time taken to get to
     * this state in integer minutes.
     *
     * @param p1Position position of the person who can cross in 1 minute
     * @param p2Position position of the person who can cross in 2 minutes
     * @param flashlightPosition position of the flashlight
     * @param p5Position position of the person who can cross in 5 minutes
     * @param p10Position position of the person who can cross in 10 minutes
     * @param timeSoFar time taken so far
     */
    public BridgeState(Position p1Position,
            Position p2Position,
            Position flashlightPosition,
            Position p5Position,
            Position p10Position,
            int timeSoFar) {

        this.positions[0] = p1Position;
        this.positions[1] = p2Position;
        this.positions[2] = flashlightPosition;
        this.positions[3] = p5Position;
        this.positions[4] = p10Position;

        this.timeSoFar = timeSoFar;
    }

    /**
     * Compares this bridge state with another for equality. Note that this
     * method overrides the <b>equals</b> method defined in
     * <b>java.lang.Object</b>. Thus the argument of type <b>Object</b> must be
     * cast to type
     * <b>BridgeState</b> before processing. Two bridge states are equal if the
     * positions of the persons and flashlight in one state are matched by their
     * positions in the other. Note that the time taken to cross so far is not
     * taken into account when considering equality.
     *
     * @param other the other bridge state to be compared with this one.
     * @return whether this state is equal to the other state
     */
    @Override
    public boolean equals(Object other) {
        BridgeState state = (BridgeState) other;
        return Arrays.equals(positions, state.positions);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Arrays.deepHashCode(this.positions);
        hash = 41 * hash + this.timeSoFar;
        return hash;
    }

    /**
     * Creates a string representation of this state for display to the user
     * trying to solve the problem. Note that the time so far to cross is part
     * of the string representation.
     *
     * @return the string representation of this state
     */
    @Override
    public String toString() {

        String line1 = " P1 |   |\n";
        String line2 = " P2 |   |\n";
        String line3 = "  f |===|\n";
        String line4 = " P5 |   |\n";
        String line5 = "P10 |   |\n";

        if (this.getP1Position() == Position.EAST) {
            line1 = "    |   | P1\n";
        }
        if (this.getP2Position() == Position.EAST) {
            line2 = "    |   | P2\n";
        }
        if (this.getFlashlightPosition() == Position.EAST) {
            line3 = "    |===| f\n";
        }
        if (this.getP5Position() == Position.EAST) {
            line4 = "    |   | P5\n";
        }
        if (this.getP10Position() == Position.EAST) {
            line5 = "    |   | P10\n";
        }

        return line1
                + line2
                + line3
                + line4
                + line5
                + "Time elapsed so far: " + String.format("%02d", this.getTimeSoFar()) + " minutes.\n";
    }

    /**
     * Get position of the flash light
     *
     * @return the position of flash light as enum value
     */
    public Position getFlashlightPosition() {
        return this.positions[2];  // You must provide
    }

    /**
     * Get position of person who cross the bridge in 10 minutes
     *
     * @return the position of P10 as enum value
     */
    public Position getP10Position() {
        return this.positions[4];  // You must provide
    }

    /**
     * Get position of person who cross the bridge in 1 minute
     *
     * @return the position of P1 as enum value
     */
    public Position getP1Position() {
        return this.positions[0];  // You must provide
    }

    /**
     * Get position of person who cross the bridge in 2 minutes
     *
     * @return the position of P2 as enum value
     */
    public Position getP2Position() {
        return this.positions[1];  // You must provide
    }

    /**
     * Get position of person who cross the bridge in 5 minutes
     *
     * @return the position of P5 as enum value
     */
    public Position getP5Position() {
        return this.positions[3];  // You must provide
    }

    /**
     * Get heuristic value of the current state. For this problem, it return 0
     * @return heuristic value as integer
     */
    @Override
    public int getHeuristic(State finalState) {
        return 0;
    }

    /**
     * Get the current time elapsed after the moves user input
     *
     * @return the integer representation of time
     */
    public int getTimeSoFar() {
        return this.timeSoFar;  // You must provide
    }

    // Private methods and instance fields should go here
    /**
     * Positions of each people
     */
    private Position[] positions = {
        Position.WEST,
        Position.WEST,
        Position.WEST,
        Position.WEST,
        Position.WEST
    };

    /**
     * Time elapsed
     */
    private int timeSoFar = 0;

}
