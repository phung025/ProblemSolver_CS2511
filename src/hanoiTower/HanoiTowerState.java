/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hanoiTower;

import framework_puzzle.State;
import graph.SimpleVertex;
import java.util.Stack;

/**
 *
 * @author user
 */
public class HanoiTowerState extends SimpleVertex implements State {

    /**
     * A block state is represented by three stacks of characters, with the top
     * block on the stack top.
     *
     * @param p the stack at place p
     * @param q the stack at place q
     * @param r the stack at place r
     */
    public HanoiTowerState(Stack<Integer> p, Stack<Integer> q, Stack<Integer> r) {
        this.p = p;
        this.q = q;
        this.r = r;
    }

    /**
     * A block state constructor that takes a state, a source place, and a
     * target place, and creates a new state in which the block on top of the
     * source is moved to the top of the target. Precondition: the source place
     * cannot be empty. Note the use of clone() to copy the stacks.
     *
     * @param state
     * @param source
     * @param target
     */
    public HanoiTowerState(HanoiTowerState state, char source, char target) {
        p = (Stack<Integer>) state.p.clone();
        q = (Stack<Integer>) state.q.clone();
        r = (Stack<Integer>) state.r.clone();
        Integer block = source == 'p' ? p.pop() : source == 'q' ? q.pop() : r.pop();
        Stack<Integer> stack = target == 'p' ? p : target == 'q' ? q : r;
        stack.push(block);
    }

    /**
     * A convenience constructor that allows stacks of blocks to be presented as
     * strings that are then converted to stacks of characters. The first
     * character of the string represents the top block of the stack.
     *
     * @param p string representing place p
     * @param q string representing place q
     * @param r string representing place r
     */
    public HanoiTowerState(String p, String q, String r) {
        this.p = stringToStack(p);
        this.q = stringToStack(q);
        this.r = stringToStack(r);
    }

    /**
     * Indicates whether a place is empty in this state. Precondition: the place
     * must be either 'p', 'q', or 'r'.
     *
     * @param place either 'p', 'q', or 'r'
     * @return true if the place is empty, false otherwise.
     */
    public boolean placeEmpty(char place) {

        switch (place) {
            case 'p':
                return p.empty();
            case 'q':
                return q.empty();
        // place must be 'r'
            default:
                return r.empty();
        }
    }

    /**
     * Getter for the stack at place p. Provided primarily for the BlockCanvas
     * class
     *
     * @return the stack at place p
     */
    public Stack<Integer> getP() {
        return p;
    }

    /**
     * Getter for the stack at place q. Provided primarily for the BlockCanvas
     * class
     *
     * @return the stack at place q
     */
    public Stack<Integer> getQ() {
        return q;
    }

    /**
     * Getter for the stack at place r. Provided primarily for the BlockCanvas
     * class
     *
     * @return the stack at place r
     */
    public Stack<Integer> getR() {
        return r;
    }

    /**
     * Checks for equality between this block state and another object.
     *
     * @param other the other object
     * @return true if all corresponding stacks are equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        HanoiTowerState state = (HanoiTowerState) other;
        return (p.equals(state.p)
                && q.equals(state.q)
                && r.equals(state.r));
    }

    /**
     * Creates and returns a string representation of this block state.
     *
     * @return a string representation of this block state
     */
    @Override
    public String toString() {

        StringBuilder buf = new StringBuilder();

        int maxSize = Math.max(Math.max(p.size(), q.size()), r.size());

        for (int i = maxSize - 1; i >= 0; i--) {
            Integer pBlock = getBlock(p, i);
            Integer qBlock = getBlock(q, i);
            Integer rBlock = getBlock(r, i);
            buf.append(" ").append(pBlock).append(" ").append(qBlock).append(" ").append(rBlock).append(" \n");
        }

        buf.append(" ----- \n p q r ");
        return buf.toString();
    }

    private static Integer getBlock(Stack<Integer> s, int index) {
        Integer block = 0;
        try {
            block = (s.elementAt(index));
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        return block;
    }

    /**
     * Computes and returns a heuristic estimate of the number of moves from
     * this state to the goal state.
     *
     * h(1) - number of blocks that are in wrong stack h(2) - distance of blocks
     * that are in right stack but wrong position h(3) - distance to right
     * position of blocks that are in wrong stack
     *
     * @param goal the goal state for the problem currently being solved
     * @return the heuristic estimate of the number of moves to the goal
     */
    @Override
    public int getHeuristic(State goal) {

        int heuristic = 0;

        //h(1) - number of blocks that are out of place
        //Stack p
        Stack<Integer> pFinal = ((HanoiTowerState) goal).p;
        for (Integer c : pFinal) {

            //The block is in wrong stack
            if (p.indexOf(c) == -1) {
                //h(1)
                //Number of block in wrong stack
                ++heuristic;
            } else //It is in right stack, but is it in right place?
            if (p.indexOf(c) != pFinal.indexOf(c)) {
                ++heuristic;
            }
        }

        //Stack q
        Stack<Integer> qFinal = ((HanoiTowerState) goal).q;
        for (Integer c : qFinal) {
            if (q.indexOf(c) == -1) { //The block is in wrong stack
                //h(1)
                ++heuristic;
            } else //It is in right stack, but is it in right place?
            if (q.indexOf(c) != qFinal.indexOf(c)) {
                ++heuristic;
            }
        }

        //Stack r
        Stack<Integer> rFinal = ((HanoiTowerState) goal).r;
        for (Integer c : rFinal) {
            if (r.indexOf(c) == -1) { //The block is not in the stack
                //h(1)
                ++heuristic;
            } else //It is in right stack, but is it in right place?
            if (r.indexOf(c) != rFinal.indexOf(c)) {
                ++heuristic;
            }
        }

        //h(2)
        //Stack p
        int currentSizeP = Math.min(p.size(), pFinal.size());

        if (p.isEmpty()) {
            heuristic += pFinal.size();
        } else {
        for (int i = currentSizeP - 1; i >= 0; --i) {

            if (p.get(i).equals(pFinal.get(i))) {
                --heuristic;
            } else {
                break;
            }
        }
        }

        //Stack q
        int currentSizeQ = Math.min(q.size(), qFinal.size());
        if (q.isEmpty()) {
            heuristic += qFinal.size();
        } else {
        for (int i = currentSizeQ - 1; i >= 0; --i) {

            if (q.get(i).equals(qFinal.get(i))) {
                --heuristic;
            } else {
                break;
            }
        }
        }
        
        //Stack r
        int currentSizeR = Math.min(r.size(), rFinal.size());
        if (r.isEmpty()) {
            heuristic += rFinal.size();
        } else {
        for (int i = currentSizeR - 1; i >= 0; --i) {
            if (r.get(i).equals(rFinal.get(i))) {
                --heuristic;
            } else {
                break;
            }
        }
        }
        
        return heuristic;
    }

    @Override
    public int hashCode() {

        int hash = 101;

        for (Integer c : p) {
            hash = 13 * hash + c.hashCode();
        }

        for (Integer c : q) {
            hash = 41 * hash + c.hashCode();
        }

        for (Integer c : r) {
            hash = 23 * hash + c.hashCode();
        }

        return hash;
    }

    /**
     * Helper method that converts a string to a stack of characters.
     *
     * @param string a string representing a stack of blocks on a place
     * @return a stack of characters with the top character corresponding to the
     * first character in the string
     */
    private static Stack<Integer> stringToStack(String string) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = string.length() - 1; i >= 0; i--) {
            stack.push(Integer.parseInt(Character.toString(string.charAt(i))));
        }
        return stack;
    }

    private Stack<Integer> p;
    private Stack<Integer> q;
    private Stack<Integer> r;
}
