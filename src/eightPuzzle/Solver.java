/**
 * Solver.java
 *  contains the Solver for the eight pussle
 *  uses A* search to find a solution
 *  can run out of space for more difficult puzzles
 * @author Tommy Jacobs
 * @author Alex Lavery
 * @author Will Richman
 */

package eightpuzzle;

import java.util.List;
import java.util.PriorityQueue;

public class Solver {
    Board initial;

    //keep tracks of the numMoves taken to solve
    public int numMoves;

    // Bottom, left, top, right
	int[] row = { 1, 0, -1, 0 };
	int[] col = { 0, -1, 0, 1 };

    
    public Solver(Board board){
        this.initial = board;
        numMoves = 0;
    }
    
    /**
     * print the path that is the soloution
     *  also finds the moves taken to solve
     * @param root
     */
    public void printPath(Node root) {
		if (root == null) {
			return;
		}
        printPath(root.parent);
        this.numMoves++;
		root.state.printBoard();
		System.out.println();
	}
    
    /**
     * Solves the puzzle using A* search
     *  heuristic is the manhattan cost + the cost of misplaced tiles
     * @param initial, a board that the user tries to solve
     * @param goal, the goal state
     */
    public void solve(Board initial, int[][] goal) {
        // priority queue to sort the nodes 
        // help from geeksforgeeks.com to earn more about how I can change the priority queue
        // sorted by cost
		PriorityQueue<Node> pq = new PriorityQueue<Node>(1000, (a, b) -> (a.cost + a.level) - (b.cost + b.level));
		Node root = new Node(initial);
		root.cost = root.manhattanCost() + root.misplacedTile(goal);
		pq.add(root);
        
        // Loop that goes until the queue is empty
		while (!pq.isEmpty()) {
            Node min = pq.poll();

            // Checks to see if node is goal state
			if (min.cost == 0) {
                System.out.println("Solved!");
				printPath(min);
				return;
            }
            
            // If not goal state, add child nodes to queue
			List<String> moves = min.state.getMoves();
			for (String move : moves) {
	            	Node child = new Node(min.state, min, move);
	            	child.cost = child.manhattanCost() + child.misplacedTile(goal);
	            	pq.add(child);
	        }
		}
	}
}

