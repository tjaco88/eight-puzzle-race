package eightpuzzle;

import java.util.Scanner;

public class Solver {
    
    
    public static void main(String[] args){
        Boolean game = true;
        int moves = 0;
        System.out.println("Welcome to my eight puzzle game!");
        while(game){
            Scanner in = new Scanner(System.in);
            System.out.println("If you would like to play type YES. If you would like to stop type EXIT");
            String answer = in.nextLine().toUpperCase();
            if(answer.equals("YES")){
                System.out.println("Awesome! Generating game board...");
                System.out.println("The '0' is the black space. Try to get the '0' to the bottom right corner. The numbers should move in order from left to right.");
                Board eightPuzzle = new Board();
                eightPuzzle.randomBoard();
                while(!eightPuzzle.isGoal()){
                    eightPuzzle.printBoard();
                    System.out.println("Total moves: " + moves);
                    System.out.print("Available Moves: (");
                    eightPuzzle.getMoves();
                    moves++;
                    System.out.println("What move do you want to do? (Enter QUIT to exit)");
                    String input = in.nextLine().toUpperCase();
                    if(input.equals("QUIT"))
                        break;
                    eightPuzzle.move(input);
                }
            }
            if(answer.equals("EXIT")){
                game = false;
                System.out.println("Thanks for playing!");
                in.close();
            }
            else 
            System.out.println("I didn't get that. Please try again");
        }
    }
}
