package framework_puzzle;

import graph.DequeAdder;
import graph.Vertex;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * This abstract class represents a problem in a problem solving domain. Note
 * that this class does not provide a constructor. It provides getters and
 * setters for the current state of the problem, the list of moves for the
 * problem, and the problem's introduction string. Extending classes need not
 * have instance fields for these attributes, as they are inherited from this
 * class. Extending classes must set these values in their constructors using
 * the setters (mutators) provided in this class. Extending classes must also
 * override the abstract <b>success</b> method.
 */
public abstract class Problem {

    /**
     * Search the state using depth first search algorithm
     *
     * @param startState
     * @return stack containing all solution moves
     */
    public Stack<Vertex> depthFirstSearch(Vertex startState) {

        DequeAdder adder = (Vertex vertex, Deque<Vertex> deque) -> {
            deque.addFirst(vertex);
        };

        return search(startState, adder);
    }

    /**
     * Search the state using breadth first search algorithm
     *
     * @param startState
     * @return stack containing all solution moves
     */
    public Stack<Vertex> breadthFirstSearch(Vertex startState) {

        DequeAdder adder = (Vertex vertex, Deque<Vertex> deque) -> {
            deque.addLast(vertex);
        };

        return search(startState, adder);
    }

    /**
     * Search the problem state to find the moves to get to solution
     *
     * @param start the initial start state
     * @param adder
     * @return a stack containing all states that lead to the final state
     */
    private Stack<Vertex> search(Vertex start, DequeAdder adder) {

        resetStats();

        //Stack containings all states from start -> final
        Stack<Vertex> solution = new Stack<>();

        //Create stack/queue used for storing nodes of the search tree
        Deque<Vertex> vertices = new ArrayDeque<>();

        //Push initial state into stack/queue
        adder.add(start, vertices);
        ++queueOps;
        ++queueSize;
        maxQueueSize = Math.max(queueSize, maxQueueSize);

        while (!vertices.isEmpty()) {
            //Pop out the first vertex
            Vertex toBeExpanded = vertices.pop();
            ++queueOps;
            --queueSize;

            //If found the solution, go up the tree to get all states lead to it
            if (isFinalState((State) toBeExpanded)) {

                //Push the final state onto the solution stack
                solution.push(toBeExpanded);

                //Keep pushing all predecessors onto the solution stack until it reach the start state
                for (Vertex pred = toBeExpanded.getPredecessor(); !pred.equals(start);) {
                    solution.push(pred);
                    pred = pred.getPredecessor();
                }

                return solution;

            } else { //Else if the final state hasn't been found

                //Expand the state
                List<Vertex> children = expand(toBeExpanded);

                //Add the child states to the stack/queue
                Iterator<Vertex> iter = children.iterator();

                while (iter.hasNext()) {
                    adder.add(iter.next(), vertices);
                    ++queueOps;
                    ++queueSize;
                    maxQueueSize = Math.max(queueSize, maxQueueSize);
                }
            }
        }

        return null;
    }

    /**
     * Search the current problem using the normal A* search
     *
     * @param start
     * @return the stack containing all possible moves to get to goal if not
     * found goal, return null
     */
    public Stack<Vertex> aStarSearch(Vertex start) {

        //Clear all previous stats if there are any
        resetStats();

        //Comparator used for the priority queue operation
        Comparator comp = (Comparator) (Object o1, Object o2) -> {
            int d1 = ((Vertex) o1).getDistance();
            int h1 = ((State) o1).getHeuristic(getFinalState());
            
            int d2 = ((Vertex) o2).getDistance();
            int h2 = ((State) o2).getHeuristic(getFinalState());
            
            return (h1+d1) - (h2+d2);
        } /**
         * Create an a min binary heap
         *
         * @param Object o1 and o2
         * @return -1 if o1 < o2 else 1
         */ ;

        //The stack containing the solution moves
        Stack<Vertex> solution = new Stack<>();

        //Create the initial priority queue
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(1, comp);

        //Push start state to the priority queue
        priorityQueue.add(start);

        //Update statistics
        ++queueOps;
        ++queueSize;
        maxQueueSize = Math.max(queueSize, maxQueueSize);

        while (!priorityQueue.isEmpty()) {

            //Pop out the vertex
            Vertex toBeExpanded = priorityQueue.remove();

            //Update statistics
            ++queueOps;
            --queueSize;

            //Check if the vertex is final state
            if (this.isFinalState(((State) toBeExpanded))) {

                //Push the final state onto the solution stack
                solution.push(toBeExpanded);

                //Keep pushing all predecessors onto the solution stack until it reach the start state
                for (Vertex pred = toBeExpanded.getPredecessor(); !pred.equals(start);) {
                    solution.push(pred);
                    pred = pred.getPredecessor();
                }

                return solution;

            } else { //If not, expand it

                //Expand the node, depend on the algorithms that it will expand in differnt way
                List<Vertex> children = expand(toBeExpanded);
                Iterator<Vertex> iter = children.iterator();

                //Add the children to the priorityQueue
                while (iter.hasNext()) {

                    //Add discovered node to the priority queue
                    priorityQueue.add(iter.next());

                    //Update statistic
                    ++queueOps;
                    ++queueSize;
                    maxQueueSize = Math.max(queueSize, maxQueueSize);
                }
            }
        }

        return null;
    }

    /**
     * Search the current problem using the enhanced A* search
     *
     * @param start
     * @return the stack containing all possible moves to get to goal if not
     * found goal, return null
     */
    public Stack<Vertex> enhancedAStarSearch(Vertex start) {

        //Clear all stats
        resetStats();

        //Comparator used for the priority queue operation
        Comparator comp = (Comparator) (Object o1, Object o2) -> {
            int d1 = ((Vertex) o1).getDistance();
            int h1 = ((State) o1).getHeuristic(getFinalState());
            
            int d2 = ((Vertex) o2).getDistance();
            int h2 = ((State) o2).getHeuristic(getFinalState());
            
            return (h1+d1) - (h2+d2);
        };
        /**
         * Create an a min binary heap
         *
         * @param Object o1 and o2
         * @return -1 if o1 < o2 else 1
         */

        //The stack containing the solution moves
        Stack<Vertex> solution = new Stack<>();

        //Create 2 hash table that will be used for maintining the opened and closed vertices
        HashMap<State, Vertex> opened = new HashMap<>();
        HashMap<State, Vertex> closed = new HashMap<>();

        //Create the initial priority queue
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(1, comp);

        //Push start state to the priority queue
        priorityQueue.add(start);
        opened.put((State) start, start);

        //Update statistics
        ++queueOps; //Add start node
        ++queueSize; //Increase size by 1
        maxQueueSize = Math.max(queueSize, maxQueueSize);

        while (!priorityQueue.isEmpty()) {

            //Pop out the vertex
            Vertex toBeExpanded = priorityQueue.remove();

            //Remove it from opened hash table
            opened.remove((State) toBeExpanded);
            closed.put((State) toBeExpanded, toBeExpanded);

            //Update statistics
            ++queueOps; //Operation remove[priorityQueue]
            --queueSize; //Pop out the first node in the queue

            //Check if the vertex is final state
            if (this.isFinalState(((State) toBeExpanded))) {

                //Push the final state onto the solution stack
                solution.push(toBeExpanded);

                //Keep pushing all predecessors onto the solution stack until it reach the start state
                for (Vertex pred = toBeExpanded.getPredecessor() ; !pred.equals(start) ;) {
                    solution.push(pred);
                    pred = pred.getPredecessor();
                }

                return solution;

            } else { //If not, expand it

                //Expand the node, depend on the algorithms that it will expand in differnt way
                List<Vertex> children = enhancedExpand(toBeExpanded);
                Iterator<Vertex> iter = children.iterator();

                //Add the children to the priorityQueue
                while (iter.hasNext()) {

                    Vertex child = iter.next();

                    if (opened.containsKey((State) child)) {

                        if (child.getDistance() < opened.get((State) child).getDistance()) {

                            //Get the node about to be promoted
                            Vertex promotedNode = opened.get(((State) child));

                            //Set new distance
                            promotedNode.setDistance(child.getDistance());

                            //Set new predecessor
                            promotedNode.setPredecessor(child.getPredecessor());

                            //Promote the node in the priority queue
                            priorityQueue.remove(opened.get((State) child));
                            priorityQueue.add(promotedNode);

                            ++queueOps; //The node is promoted
                            ++openedRediscover; //Promote opened node
                        }
                    } else if (closed.containsKey((State) child)) {
                        if (child.getDistance() < closed.get((State) child).getDistance()) {

                            Vertex restoredNode = closed.get((State) child);

                            //Set new distance
                            restoredNode.setDistance(child.getDistance());

                            //Set new predecessor
                            restoredNode.setPredecessor(child.getPredecessor());

                            //Restore the state
                            priorityQueue.add(restoredNode);
                            opened.put((State) restoredNode, restoredNode);
                            closed.remove((State) child);

                            //Update stats
                            ++queueOps; //The node is removed
                            ++queueSize; //Increase queue size
                            ++closedRediscover; //Restore old node
                        }
                    } else {

                        priorityQueue.add(child);
                        opened.put((State) child, child);
                        ++queueOps; //Increase queue operation
                        ++queueSize; //Put new discovered node on to the queue
                    }

                    maxQueueSize = Math.max(queueSize, maxQueueSize); //Find max queue
                }
            }
            
        }

        return null;
    }

    /**
     * Checks to see if a vertex appears on the predecessor path of an ancestor
     * vertex.
     *
     * @param v the vertex to check
     * @param ancestor the ancestor of v
     * @return true if v equals ancestor or any ancestor of ancestor
     */
    private boolean occursOnPath(Vertex v, Vertex ancestor) {
        // Do-while version  
        do {
            if (v.equals(ancestor)) {
                return true;
            }
            ancestor = ancestor.getPredecessor();
        } while (ancestor != null);

        return false;
    }

    /**
     * Expands a vertex v in a state space search tree by creating a list (its
     * children) of all vertices adjacent to it in the state space. The list may
     * not include any vertex on the predecessor path leading to v. Each child
     * on the list has its predecessor set to v and its distance from the root
     * (its depth in the search tree) set to one more than v's distance.
     *
     * @param v the vertex being expanded
     * @return a list of the children
     */
    private List<Vertex> expand(Vertex v) {

        List<Vertex> expandedVertices = new LinkedList<>();

        State parent = (State) v;

        List<Move> availableMove = this.getMoves();
        Iterator<Move> iter = availableMove.iterator();

        while (iter.hasNext()) {
            Vertex child = (Vertex) (iter.next().doMove(parent));

            if (child != null && !child.equals(v) && !this.occursOnPath(child, v)) {

                child.setPredecessor(v);
                child.setDistance(v.getDistance() + 1);
                expandedVertices.add(child);
            }
        }

        return expandedVertices;
    }

    /**
     * Expands a vertex v in a state space search tree by creating a list (its
     * children) of all vertices adjacent to it in the state space. The list may
     * not include any vertex on the predecessor path leading to v. Each child
     * on the list has its predecessor set to v and its distance from the root
     * (its depth in the search tree) set to one more than v's distance.
     *
     * <b>NOTE</b> This enhanced expand method is used by the enhanced A* search
     *
     * @param v the vertex being expanded
     * @return a list of the children
     */
    private List<Vertex> enhancedExpand(Vertex v) {

        List<Vertex> expandedVertices = new LinkedList<>();

        State parent = (State) v;

        List<Move> availableMove = this.getMoves();
        Iterator<Move> iter = availableMove.iterator();

        while (iter.hasNext()) {
            Vertex child = (Vertex) (iter.next().doMove(parent));

            /* It doesnt check for occured on path because the enhanced A*
             * search maintains 2 hash table for checking duplicates
             */
            if (child != null && !child.equals(v)) {

                child.setPredecessor(v);
                child.setDistance(v.getDistance() + 1);
                expandedVertices.add(child);
            }
        }

        return expandedVertices;
    }

    /**
     * Determines whether the current state of this problem is a success.
     * Extending classes need to override this method.
     *
     * @return whether the current state is a success
     */
    public abstract boolean success();

    /**
     * Gets the current state of the problem.
     *
     * @return the current state
     */
    public State getCurrentState() {
        return currentState;
    }

    /**
     * Sets the current state of the problem.
     *
     * @param currentState the current state
     */
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    /**
     * Gets the final state of the problem.
     *
     * @return the final state
     */
    public State getFinalState() {
        return finalState;
    }

    /**
     * Sets the current state of the problem.
     *
     * @param finalState the final state of problem
     */
    public void setFinalState(State finalState) {
        this.finalState = finalState;
    }

    /**
     * Gets an explanatory introduction string for the problem.
     *
     * @return the introduction string
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * Sets the introduction string for this problem.
     *
     * @param introduction the introduction string
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * Gets the list of moves for this problem.
     *
     * @return the list of moves
     */
    public List<Move> getMoves() {
        return moves;
    }

    /**
     * Sets the list of moves for this problem.
     *
     * @param moves the list of moves
     */
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    /**
     * Get number of queue operations
     *
     * @return queue operations in string
     */
    public String getQueueOps() {
        return String.valueOf(queueOps);
    }

    /**
     * Get the max queue size
     *
     * @return max queue size as string
     */
    public String getMaxQueueSize() {
        return String.valueOf(maxQueueSize);
    }

    /**
     * Get the number of opened nodes rediscovered
     *
     * @return number of opened nodes rediscovered as string
     */
    public String getOpenedRediscovered() {
        return String.valueOf(openedRediscover);
    }

    /**
     * Get the number of closed nodes rediscovered
     *
     * @return number of closed nodes rediscovered as string
     */
    public String getClosedRediscovered() {
        return String.valueOf(closedRediscover);
    }

    /**
     * The current state of this problem
     */
    private State currentState;

    /**
     * The final state of the problem
     */
    private State finalState;

    /**
     * The explanatory string for this problem.
     */
    private String introduction;

    /**
     * The list of moves for this problem.
     */
    private List<Move> moves;

    /**
     * Check if the parameter state is the final state of the problem
     *
     * @param currentState state of the problem
     * @return true if it is final state else false
     */
    public abstract boolean isFinalState(State currentState);

    //DEQ statistics
    private long queueOps = 0;
    private long queueSize = 0;
    private long maxQueueSize = 0;
    private long openedRediscover = 0;
    private long closedRediscover = 0;

    /**
     * The list containing the initial states for problem chooser
     */
    List<State> initialStates = new LinkedList<State>();

    /**
     * The list containing the final states for the problem chooser
     */
    List<State> finalStates = new LinkedList<>();

    /**
     * The list containing the initial canvas for problem chooser
     */
    List<Canvas> inititalCanvas = new LinkedList<>();

    /**
     * The list containing the final canvas for problem chooser
     */
    List<Canvas> finalCanvas = new LinkedList<>();

    /**
     * The list containing all move count for all problems in problem chooser
     */
    List<Integer> moveCountList = new LinkedList<>();

    /**
     * Setter for the list containing all move counts for problems in problem
     * chooser
     *
     * @param counts total move counts of each problem stored in a list
     */
    public void setMoveCounts(List<Integer> counts) {
        this.moveCountList = counts;
    }

    /**
     * Getter for the list containing all move counts for problems in problem
     * chooser
     *
     * @return total move counts of each problem stored in a list
     */
    public List<Integer> getMoveCounts() {
        return this.moveCountList;
    }

    /**
     * Setter for the list of initial states
     *
     * @param states the list of initial states
     */
    public void setInitialStates(List<State> states) {
        this.initialStates = states;
    }

    /**
     * Setter for the list of initial states
     *
     * @return list of the initial states of problem
     */
    public List<State> getInitialStates() {
        return this.initialStates;
    }

    /**
     * Setter for the list of initial states
     *
     * @param states the list of initial states
     */
    public void setFinalStates(List<State> states) {
        this.finalStates = states;
    }

    /**
     * Setter for the list of initial states
     *
     * @return the final states of problem
     */
    public List<State> getFinalStates() {
        return this.finalStates;
    }

    /**
     * Setter for the list of initial canvas
     *
     * @param canvas the list of initial canvas
     */
    public void setInitialCanvas(List<Canvas> canvas) {
        this.inititalCanvas = canvas;
    }

    /**
     * Getter for the list of initial canvas
     *
     * @return the list of initial problem's canvas
     */
    public List<Canvas> getInitialCanvas() {
        return this.inititalCanvas;
    }

    /**
     * Setter for the list of final canvas
     *
     * @param canvas the list of final canvas for problems
     */
    public void setFinalCanvas(List<Canvas> canvas) {
        this.finalCanvas = canvas;
    }

    /**
     * Getter for the list of initial states
     *
     * @return the list containing final canvas used in problem chooser
     */
    public List<Canvas> getFinalCanvas() {
        return this.finalCanvas;
    }

    /**
     * Reset all statistics
     */
    private void resetStats() {
        //Clear all previous stats if there are any
        queueOps = 0;
        queueSize = 0;
        maxQueueSize = 0;
        openedRediscover = 0;
        closedRediscover = 0;
    }

}
