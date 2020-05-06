/**
 * Node.java
 *  contains the Node class that is used by the Solver
 *  used in the priority queue of the solver
 * @author Tommy Jacobs
 * @author Alex Lavery
 * @author Will Richman
 */

package eightpuzzle;

public class Node {
    Node parent;
    Board state;

    // The level the node is at 
    int level;

    // the manhattan cost + the misplaced tiles cost
    int cost;
    
    public Node(Board board){
        this.state = board;
        this.parent = null;
    }
    
    public Node(Board board, Node parent, String move) {
        this.parent = parent;
        this.state = new Board();
        for (int i = 0; i < 3; i++) {
            this.state.puzzle[i] = board.puzzle[i].clone();
        }
        
        this.state = Board.move(this.state, move);	
        this.cost = Integer.MAX_VALUE;
    }
    
    /**
    * Gets the goal row for state state
    * @param state an int
    * @return the row of the goal for state
    */
    public int getRow(int state){
        if(state == 1 || state == 2 || state == 3){
            return 0;
        }
        if(state == 4 || state == 5 || state == 6){
            return 1;
        }
        else
        return 2;
    }
    
    /**
    * Gets the goal column for state state
    * @param state an int
    * @return the column of the goal for state
    */
    public int getCol(int state){
        if(state == 1 || state == 4 || state == 7){
            return 0;
        }
        if(state == 2 || state == 5 || state == 8){
            return 1;
        }
        else
        return 2;
    }
    
    /**
    * Find the manhattan distance for all pieces in the board
    * @return g, the distance
    */
    public int manhattanCost(){
        int cost = 0;
        for(int i = 0; i < 3; i++){
            for(int j =0; j < 0; j++){
                int goalX = getRow(this.state.puzzle[i][j]);
                int goalY = getCol(this.state.puzzle[i][j]);
                cost += Math.abs((i - goalX)) + Math.abs((j - goalY));
            }
        }
        return cost;
    }
    
    /**
     * Finds the amount of misplaced tiles in the puzzle
     * @param goalState, the goal state for the puzzle
     * @return the amount of misplaced tiles
     */
    public int misplacedTile(int[][] goalState){
        int cost = 0;
        for(int i = 0; i < 3; i++)
        for(int j = 0; j <3 ; j++) {
            if(this.state.puzzle[i][j] != goalState[i][j])
            cost++;
        }
        return cost;
    }
}
