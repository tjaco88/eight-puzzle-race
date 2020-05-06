
/**
 * Solved.java
 *  contains the way to make a random board and solve it 
 * @author Tommy Jacobs
 * @author Alex Lavery
 * @author Will Richman
 */

package eightpuzzle;

import java.util.List;
import java.util.Random;

public class Solved {
    public Board board;

    // String array with the solution based on the randomly genererated moves
    String solution[];

    // User inputted amount of moves to solve
    int moves;
    
    public Solved(int moves){
        this.moves = moves;
        this.solution = new String[moves];
        this.board = new Board(moves);
        
        // Generates random board 
        Random rand = new Random();
        for(int i = 0; i < moves; i++){
            List<String> possibleMoves = this.board.getMoves();
            int rando = rand.nextInt(possibleMoves.size());
            String move = possibleMoves.get(rando);
            this.board = Board.move(this.board, move);

            // Adds the opposite of the move to the solution array
            if(move.equals("LEFT"))
            solution[moves - i - 1] = "RIGHT";
            if(move.equals("RIGHT"))
            solution[moves - i - 1] = "LEFT";
            if(move.equals("UP"))
            solution[moves - i - 1] = "DOWN";
            if(move.equals("DOWN"))
            solution[moves - i - 1] = "UP";
        }
    }

    /**
     * Shows the user the solution that was used to generate the random board
     * @param move, finds where the user should be in the solution
     * @return the move or a string stating that al the moves have been shown already
     */
    public String viewSolution(int move){
        try{
            return "You should move " + this.solution[move];
        } 
        catch(Exception e){
            return "You should have solved it by now";
        }
    }
    
}