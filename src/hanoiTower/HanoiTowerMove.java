/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hanoiTower;

import framework_puzzle.Move;
import framework_puzzle.State;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Nam Phung
 */
public class HanoiTowerMove extends Move {

    /**
     * Constructs a new block move object.
     *
     * @param moveName the name of this move. It is an error if the name is not
     * one of the following:
     * <ul>
     * <li> "Move block from p to q" </li>
     * <li> "Move block from p to r" </li>
     * <li> "Move block from q to p" </li>
     * <li> "Move block from q to r" </li>
     * <li> "Move block from r to p" </li>
     * <li> "Move block from r to q" </li>
     * </ul>
     */
    public HanoiTowerMove(String moveName) {
        super(moveName);
        if (!moveNames.contains(moveName)) {
            throw new RuntimeException("Bad move name: " + moveName);
        }
        
    }

    /**
     * Attempts to perform this move on a given block state. The move to perform
     * is determined by this object's move name. A move is from a source place
     * to a target place. If the move can be performed a new block state object
     * is returned that reflects this move. If the move cannot be performed
     * <b>null</b> is returned. A move cannot be performed if the source place
     * is empty.
     *
     * @param state the block state on which this move is to be performed
     * @return a new block state reflecting the move, or <b>null</b> if it
     * cannot be performed
     */
    public State doMove(State state) {

        HanoiTowerState s = (HanoiTowerState) state;

        if (getMoveName().equals(PQ)) {
            if (s.placeEmpty('p')) {
                return null;
            } else {
                if (s.placeEmpty('q') || s.getP().peek() < s.getQ().peek()) {
                    return new HanoiTowerState(s, 'p', 'q');
                } else {
                    return null;
                }
            }
        } else if (getMoveName().equals(PR)) {
            if (s.placeEmpty('p')) {
                return null;
            } else {
                if (s.placeEmpty('r') || s.getP().peek() < s.getR().peek()) {
                    return new HanoiTowerState(s, 'p', 'r');
                } else {
                    return null;
                }
            }
        } else if (getMoveName().equals(QP)) {
            if (s.placeEmpty('q')) {
                return null;
            } else {
                if (s.placeEmpty('p') || s.getQ().peek() < s.getP().peek()) {
                    return new HanoiTowerState(s, 'q', 'p');
                } else {
                    return null;
                }
            }
        } else if (getMoveName().equals(QR)) {
            if (s.placeEmpty('q')) {
                return null;
            } else {
                if (s.placeEmpty('r') || s.getQ().peek() < s.getR().peek()) {
                    return new HanoiTowerState(s, 'q', 'r');
                } else {
                    return null;
                }
            }
        } else if (getMoveName().equals(RP)) {
            if (s.placeEmpty('r')) {
                return null;
            } else {
                if (s.placeEmpty('p') || s.getR().peek() < s.getP().peek()) {
                    return new HanoiTowerState(s, 'r', 'p');
                } else {
                    return null;
                }
            }
        } else // Move must be RQ
        if (s.placeEmpty('r')) {
            return null;
        } else {
            if (s.placeEmpty('q') || s.getR().peek() < s.getQ().peek()) {
                return new HanoiTowerState(s, 'r', 'q');
            } else {
                return null;
            }
        }
    }

    private static final String PQ = "Move disk from p to q";
    private static final String PR = "Move disk from p to r";
    private static final String QP = "Move disk from q to p";
    private static final String QR = "Move disk from q to r";
    private static final String RP = "Move disk from r to p";
    private static final String RQ = "Move disk from r to q";

    private static final List<String> moveNames
            = Arrays.asList(new String[]{PQ, PR, QP, QR, RP, RQ});

}
