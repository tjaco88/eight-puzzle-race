import eightpuzzle.Board;
import eightpuzzle.Solver;

import java.util.List;
import java.util.Scanner;

public class EightPuzzle {
    
    public static void main(String[] args){
        
        Boolean game = true;
        System.out.println("Welcome to my eight puzzle game!");
        
        // Loop over game
        while(game){

            Scanner in = new Scanner(System.in);
            System.out.println("If you would like to play type YES. If you would like to stop type EXIT");
            String answer = in.nextLine().toUpperCase();
            
            int movesAI = 0;
            int moves = 0;
            boolean goal = false;

            // They want to play game
            if(answer.equals("YES")){
                int numMove = 0;
                System.out.println("Awesome! Generating game board...");
                System.out.println("The '0' is the black space. Try to get the '0' to the bottom right corner. The numbers should move in order from left to right.");
                Board eightPuzzle = new Board();
                eightPuzzle.randomBoard();
                

                int[][] testPuzzle = new int[3][3];
                testPuzzle[0][0] = 1;
                testPuzzle[1][0] = 2;
                testPuzzle[2][0] = 3;
                testPuzzle[0][1] = 4;
                testPuzzle[1][1] = 0;
                testPuzzle[2][1] = 6;
                testPuzzle[0][2] = 7;
                testPuzzle[1][2] = 5;
                testPuzzle[2][2] = 8; 
                /*eightPuzzle.puzzle = testPuzzle;
                eightPuzzle.x = 1;
                eightPuzzle.y = 1;
                */
                int[][] temp = new int[3][3];
                temp = eightPuzzle.puzzle;
                int tempX = eightPuzzle.x;
                int tempy = eightPuzzle.y;

                Board eightPuzzleAI = new Board();
                eightPuzzleAI.puzzle = temp;
                eightPuzzleAI.x = tempX;
                eightPuzzleAI.y = tempy;

                
                
                Solver solveMe = new Solver(eightPuzzleAI);
                solveMe.solve();
                System.out.println("It took me " + solveMe.numMoves + " move(s) to beat.");
                
                // Loop until they solve puzzle or enter quit
                while(!eightPuzzle.isGoal()){
                    eightPuzzle.printBoard();
                    System.out.println("Total moves: " + numMove);
                    System.out.print("Available Moves: ");
                    List<String> listMoves = eightPuzzle.getMoves();
                    for(String move : listMoves){
                        System.out.print(" " + move);
                    }
                    System.out.print("\n");
                    System.out.println("What move do you want to do? (Enter QUIT to exit)");
                    String input = in.nextLine().toUpperCase();
                    if(input.equals("QUIT"))
                    break;
                    eightPuzzle = Board.move(eightPuzzle, input);
                    
                    if(eightPuzzle.isGoal()){
                        moves = numMove;
                        movesAI = 0;//solveMe.numMoves;
                        goal = true;
                    }
                    numMove++;
                    
                }
            }
            
            // They want to leave 
            if(answer.equals("EXIT")){
                game = false;
                System.out.println("Thanks for playing!");
                in.close();
            }
            if(goal){
                if(movesAI >= moves){
                    System.out.println("Congratulations! You soloved the puzzle in " + moves + " move(s). You beat me!");
                }
                else{
                    System.out.println("I beat you. you solved the puzzle in " + moves + " moves");
                }
            }
            else 
            System.out.println("I didn't get that. Please try again");
        }
    }
}