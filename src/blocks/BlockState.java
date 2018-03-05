package blocks;

import framework_puzzle.State;
import graph.SimpleVertex;
import java.util.Stack;

/**
 * This class represents a state of the Blocks World.
 *
 * @author tcolburn
 */
public class BlockState extends SimpleVertex implements State {

    /**
     * A block state is represented by three stacks of characters, with the top
     * block on the stack top.
     *
     * @param p the stack at place p
     * @param q the stack at place q
     * @param r the stack at place r
     */
    public BlockState(Stack<Character> p, Stack<Character> q, Stack<Character> r) {
        this.p = p;
        this.q = q;
        this.r = r;
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
    public BlockState(String p, String q, String r) {
        this.p = stringToStack(p);
        this.q = stringToStack(q);
        this.r = stringToStack(r);
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
    public BlockState(BlockState state, char source, char target) {
        p = (Stack<Character>) state.p.clone();
        q = (Stack<Character>) state.q.clone();
        r = (Stack<Character>) state.r.clone();
        Character block = source == 'p' ? p.pop()
                : source == 'q' ? q.pop() : r.pop();
        Stack<Character> stack = target == 'p' ? p
                : target == 'q' ? q : r;
        stack.push(block);
    }

    /**
     * Indicates whether a place is empty in this state. Precondition: the place
     * must be either 'p', 'q', or 'r'.
     *
     * @param place either 'p', 'q', or 'r'
     * @return true if the place is empty, false otherwise.
     */
    public boolean placeEmpty(char place) {

        if (place == 'p') {
            return p.empty();
        } else if (place == 'q') {
            return q.empty();
        } else // place must be 'r'
        {
            return r.empty();
        }
    }

    /**
     * Getter for the stack at place p. Provided primarily for the BlockCanvas
     * class
     *
     * @return the stack at place p
     */
    public Stack<Character> getP() {
        return p;
    }

    /**
     * Getter for the stack at place q. Provided primarily for the BlockCanvas
     * class
     *
     * @return the stack at place q
     */
    public Stack<Character> getQ() {
        return q;
    }

    /**
     * Getter for the stack at place r. Provided primarily for the BlockCanvas
     * class
     *
     * @return the stack at place r
     */
    public Stack<Character> getR() {
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
        BlockState state = (BlockState) other;
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
            Character pBlock = getBlock(p, i);
            Character qBlock = getBlock(q, i);
            Character rBlock = getBlock(r, i);
            buf.append(" ").append(pBlock).append(" ").append(qBlock).append(" ").append(rBlock).append(" \n");
        }

        buf.append(" ----- \n p q r ");
        return buf.toString();
    }

    private static Character getBlock(Stack<Character> s, int index) {
        Character block = ' ';
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
     * @param goal the goal state for the problem currently being solved
     * @return the heuristic estimate of the number of moves to the goal
     */
    @Override
    public int getHeuristic(State goal) {

        int heuristic = 0;

        Stack<Character> pFinal = ((BlockState) goal).p;
        Stack<Character> qFinal = ((BlockState) goal).q;
        Stack<Character> rFinal = ((BlockState) goal).r;

        //h(1) - number of blocks that are out of place
        //Stack p
        for (Character c : pFinal) {

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
        for (Character c : qFinal) {
            if (q.indexOf(c) == -1) { //The block is in wrong stack
                //h(1)
                ++heuristic;
            } else //It is in right stack, but is it in right place?
            if (q.indexOf(c) != qFinal.indexOf(c)) {
                ++heuristic;
            }
        }

        //Stack r
        for (Character c : rFinal) {
            if (r.indexOf(c) == -1) { //The block is not in the stack
                //h(1)
                ++heuristic;
            } else //It is in right stack, but is it in right place?
            if (r.indexOf(c) != rFinal.indexOf(c)) {
                ++heuristic;
            }
        }

        //h(2), h(3) - Blocks to be moved, blocks in right place counted from bottom
        //Stack p
        int currentSizeP = Math.min(p.size(), pFinal.size());

        for (int i = currentSizeP - 1; i >= 0; --i) {

            if (p.get(i).equals(pFinal.get(i))) {
                //--heuristic;
            } else {

                int j = i;
                heuristic += j + 1;

                //Get number of blocks in the right stack that needed to be moved
                if (qFinal.indexOf(p.get(j)) != -1) {
                    heuristic += qFinal.indexOf(p.get(j)) + 1;
                } else if (rFinal.indexOf(p.get(j)) != -1) {
                    heuristic += rFinal.indexOf(p.get(j)) + 1;
                }
                break;
            }
        }

        //Stack q
        int currentSizeQ = Math.min(q.size(), qFinal.size());
        for (int i = currentSizeQ - 1; i >= 0; --i) {
            if (q.get(i).equals(qFinal.get(i))) {
                //--heuristic;
            } else {

                int j = i;

                //Get number of blocks in stack p that needed to be moved
                heuristic += j + 1;

                //Get number of blocks in the right stack that needed to be moved
                if (pFinal.indexOf(q.get(j)) != -1) {
                    heuristic += pFinal.indexOf(q.get(j)) + 1;
                } else if (rFinal.indexOf(q.get(j)) != -1) {
                    heuristic += rFinal.indexOf(q.get(j)) + 1;
                }

                break;
            }
        }

        //Stack r
        int currentSizeR = Math.min(r.size(), rFinal.size());
        for (int i = currentSizeR - 1; i >= 0; --i) {
            if (r.get(i).equals(rFinal.get(i))) {
                //--heuristic;
            } else {

                int j = i;
                //Get number of blocks in stack p that needed to be moved
                heuristic += j + 1;

                //Get number of blocks in the right stack that needed to be moved
                if (pFinal.indexOf(r.get(j)) != -1) {
                    heuristic += pFinal.indexOf(r.get(j)) + 1;
                } else if (qFinal.indexOf(r.get(j)) != -1) {
                    heuristic += qFinal.indexOf(r.get(j)) + 1;
                }

                break;
            }
        }

        //h(4) - Number of blocks that only takes one more move - reduce heuristic
        //CHECK STACK P
        if (!p.isEmpty()) {
            char pTop = p.peek();
            //If the block is supposed to be in stack q
            if (qFinal.indexOf(pTop) != -1 && q.size() < qFinal.size() && qFinal.size() - qFinal.indexOf(pTop) == q.size() + 1) {
                --heuristic;
            } else if (rFinal.indexOf(pTop) != -1 && r.size() < rFinal.size() && rFinal.size() - rFinal.indexOf(pTop) == r.size() + 1) {
                --heuristic;
            }
        }

        //CHECK STACK Q
        if (!q.isEmpty()) {
            char qTop = q.peek();
            //If the block is supposed to be in stack r
            if (rFinal.indexOf(qTop) != -1 && r.size() < rFinal.size() && rFinal.size() - rFinal.indexOf(qTop) == r.size() + 1) {
                --heuristic;
            } else if (pFinal.indexOf(qTop) != -1 && p.size() < pFinal.size() && pFinal.size() - pFinal.indexOf(qTop) == p.size() + 1) {
                --heuristic;
            }
        }

        //CHECK STACK R
        if (!r.isEmpty()) {
            char rTop = r.peek();
            //If the block is supposed to be in stack q
            if (qFinal.indexOf(rTop) != -1 && q.size() < qFinal.size() && qFinal.size() - qFinal.indexOf(rTop) == q.size() + 1) {
                --heuristic;
            } else if (pFinal.indexOf(rTop) != -1 && p.size() < pFinal.size() && pFinal.size() - pFinal.indexOf(rTop) == p.size() + 1) {
                --heuristic;
            }
        }
        
        return heuristic;
    }

    @Override
    public int hashCode() {

        int hash = 101;

        for (Character c : p) {
            hash = 13 * hash + c.hashCode();
        }

        for (Character c : q) {
            hash = 41 * hash + c.hashCode();
        }

        for (Character c : r) {
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
    private static Stack<Character> stringToStack(String string) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = string.length() - 1; i >= 0; i--) {
            stack.push(string.charAt(i));
        }
        return stack;
    }

    private Stack<Character> p;
    private Stack<Character> q;
    private Stack<Character> r;

}
