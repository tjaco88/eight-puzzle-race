import eightpuzzle.Board;
import eightpuzzle.Solved;
import eightpuzzle.Solver;

import java.util.List;
import java.util.Scanner;

public class EightPuzzle {
    
    public static void main(String[] args){
        
        // used for while loop
        Boolean game = true;
        System.out.println("Welcome to my eight puzzle game!");
        
        // Loop over game
        while(game){
            
            // Scanner to determine user inputs
            Scanner in = new Scanner(System.in);
            System.out.println("If you would like to play type YES. If you would like to stop type EXIT");
            String answer = in.nextLine().toUpperCase();
            
            int movesAI = 0;
            int moves = 0;
            boolean goal = false;
            
            // They want to play game
            if(answer.equals("YES")){
                int[][] goalState = { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
                
                // keeps track of the moves they have taken so far
                int numMove = 0;
                System.out.println("Would you like to face me?\n (Works best when doing less than 40 moves)");
                String playMe = in.nextLine();
                System.out.println("How many moves would you like to take to solve the puzzle? ");
                String movesToSolve = in.nextLine();
                System.out.println("Awesome! Generating game board...");
                System.out.println("The '0' is the black space. Try to get the '0' to the bottom right corner. The numbers should move in order from top to bottom, left to right.");
                Board eightPuzzle = new Board();
                
                Solved solve = new Solved(Integer.parseInt(movesToSolve));
                boolean showSolution = false;
                int pointInSolution = 0;
                movesAI = Integer.parseInt(movesToSolve);
                
                eightPuzzle.puzzle = solve.board.puzzle.clone();
                eightPuzzle.x = solve.board.x;
                eightPuzzle.y = solve.board.y;
                
                if(playMe.toUpperCase().equals("YES")){
                    // Calling solver to solve the puzzle
                    // Can run out of space if truing to solve puzzles that have 
                    Solver solveMe = new Solver(eightPuzzle);
                    solveMe.solve(eightPuzzle, goalState);
                    movesAI = solveMe.numMoves;
                    System.out.println("It took me " + movesAI + " move(s) to beat.");
                }
                
                boolean checkSolution = false;
                
                // Loop until they solve puzzle or enter quit
                while(!eightPuzzle.isGoal()){
                    
                    // Checks to see if the user wants to see the solution
                    if(showSolution){
                        if(checkSolution){
                            // Resets the game baord for the user to use the solution to solve
                            System.out.println("Generating move for you...");
                            eightPuzzle.puzzle = solve.board.puzzle.clone();
                            eightPuzzle.x = solve.board.x;
                            eightPuzzle.y = solve.board.y;
                            checkSolution = false;
                            numMove = 0;
                        }
                        System.out.println(solve.viewSolution(pointInSolution));
                        pointInSolution++;
                        
                    }
                    eightPuzzle.printBoard();
                    System.out.println("Total moves: " + numMove);
                    System.out.print("Available Moves: ");
                    List<String> listMoves = eightPuzzle.getMoves();
                    for(String move : listMoves){
                        System.out.print(" " + move);
                    }
                    System.out.print("\n");
                    System.out.print("What move do you want to do?\n (Enter QUIT to quit)\n (Enter SOLUTON to show the solution)\n");
                    
                    
                    String input = in.nextLine().toUpperCase();
                    if(input.equals("QUIT"))
                    break;
                    if(input.equals("SOLUTION")){
                        showSolution = true;
                        checkSolution = true;
                    }
                    else if(eightPuzzle.validMove(input)){
                        eightPuzzle = Board.move(eightPuzzle, input);
                        numMove++;
                    }
                    else
                    System.out.println("Not a valid input. Please try again"); 
                    
                    if(eightPuzzle.isGoal()){
                        moves = numMove;
                        goal = true;
                    }
                    
                }
            }
            
            // They want to leave 
            if(answer.equals("EXIT")){
                game = false;
                System.out.println("Thanks for playing!");
                in.close();
            } else if(goal){
                // Checks to see who won the game
                if(movesAI >= moves){
                    System.out.println("Congratulations! You soloved the puzzle in " + moves + " move(s). You beat me!");
                }
                else{
                    System.out.println("I beat you. you solved the puzzle in " + moves + " moves");
                }
            } else 
            System.out.println("I didn't get that. Please try again");
        }
    }
}