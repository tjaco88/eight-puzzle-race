/**
 * Board.java
 *  contains the Board class that contains the physcal puzzle
 * @author Tommy Jacobs
 * @author Alex Lavery
 * @author Will Richman
 */

package eightpuzzle;

import java.util.ArrayList;
import java.util.List;

public class Board {
    // Two dimensional array for the puzzle
    public int[][] puzzle;
    
    // Moves to solve, and nnumber of moves taken already
    public int toSolve;
    
    // Position of blank space 
    public int x, y;
    
    public Board() {
        this.puzzle = new int[3][3];
        this.toSolve = 0;
        this.x = 0;
        this.y = 0;
    }

    public Board(Board b){
        this.puzzle = b.puzzle;
        this.x = b.x;
        this.y = b.y;
    }

    public Board(int moves){
        int[][] start = { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
        this.toSolve = moves;
        this.x = 2;
        this.y = 2;
        this.puzzle = start;
    }

    public Board(int[][] puzzle ,int x, int y){
        this.puzzle = puzzle;
        this.x = x;
        this.y = y;
    }
    
    public boolean isEquals(int[][] puzzle){
        boolean equal = true;
        outer : for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(this.puzzle[i][j] != puzzle[i][j]){
                    equal = false;
                    break outer;
                }
            }
        }
        return equal;
    }

    /**
    * isGoal checks if the puzzle is in its goal state
    *  Goal state starts with 1 in the top left corner
    *  Goal state:
    *      {1, 4. 7}
    *      {2, 5, 8}
    *      {3, 6, 0}
    *  Where 0 is the blank space
    */
    public boolean isGoal() {
        int[][] goal = { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
        
        return this.isEquals(goal);
    }
    
    /**
    * isSolvable checks if a puzzle is solvable
    *  uses inversion to check
    *  if inversion is odd, it is not solvable
    * @return a boolean value
    */
	public static boolean isSolvable(int[][] puzzle) {
		int count = 0;
		List<Integer> array = new ArrayList<Integer>();
		
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle.length; j++) {
				array.add(puzzle[i][j]);
			}
		}
		
		Integer[] anotherArray = new Integer[array.size()];
		array.toArray(anotherArray);
		
		for (int i = 0; i < anotherArray.length - 1; i++) {
			for (int j = i + 1; j < anotherArray.length; j++) {
				if (anotherArray[i] != 0 && anotherArray[j] != 0 && anotherArray[i] > anotherArray[j]) {
					count++;
				}
			}
		}
		
		return count % 2 == 0;
	}
    
    public void printBoard(){
        for(int i = 0; i < 3; i++){
            System.out.print("{");
            for(int j = 0; j < 3; j++){
                System.out.print(" " + this.puzzle[j][i]);
            }
            System.out.print(" }\n");
        }
    }

    /**
     * gets all of the moves available for the current state of the puzzle
     * @return a list of all possible moves given the state
     */
    public List<String> getMoves(){
        List<String> moves = new ArrayList<>();
        if(this.y == 0){
            moves.add("DOWN");
        }
        if(this.y == 1){
            moves.add("UP");
            moves.add("DOWN");
        }
        if(this.y == 2){
            moves.add("UP");
        }
        if(this.x == 0){
            moves.add("RIGHT");
        }
        if(this.x == 1){
            moves.add("LEFT");
            moves.add("RIGHT");
        }
        if(this.x == 2){
            moves.add("LEFT");
        }
        return moves;
    }
    
    /**
     * Moves the blank space in the appropriate direction
     * @param board, the current state of the puzzle
     * @param move, the desired move
     * @return, a board with the updated move and x,y coordinates of the blank space
     */
    public static Board move(Board board, String move){
        int temp = 0;
        Board b = new Board();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                int value = board.puzzle[i][j];
                b.puzzle[i][j] = value;
                if(value == 0){
                    b.x = i;
                    b.y = j;
                }
            }
        }

        if(move.equals("LEFT")){
            temp = b.puzzle[b.x - 1][b.y];
            b.puzzle[b.x - 1][b.y] = b.puzzle[b.x][b.y] ;
            b.puzzle[b.x][b.y] = temp;
            b.x--;
        }
        if(move.equals("RIGHT")){
            temp = b.puzzle[b.x + 1][b.y];
            b.puzzle[b.x + 1][b.y] = b.puzzle[b.x][b.y] ;
            b.puzzle[b.x][b.y] = temp;
            b.x++;
        }
        if(move.equals("UP")){
            temp = b.puzzle[b.x][b.y - 1];
            b.puzzle[b.x][b.y - 1] = b.puzzle[b.x][b.y] ;
            b.puzzle[b.x][b.y] = temp;
            b.y--;
        }
        if(move.equals("DOWN")){
            temp = b.puzzle[b.x][b.y + 1];
            b.puzzle[b.x][b.y + 1] = b.puzzle[b.x][b.y] ;
            b.puzzle[b.x][b.y] = temp;
            b.y++;
        }
        return b;
    }

    /**
     * Checks to see if the user input a valid move
     * @param input, user inputted move
     * @return a boolean value, true if move is valid
     */
    public boolean validMove(String input){
        boolean valid = false;
        List<String> moves = this.getMoves();
        for(String move : moves){
            if(move.equals(input))
            valid = true;
        }
        return valid;
    }
}
