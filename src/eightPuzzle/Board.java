package eightpuzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    // Two dimensional array for the puzzle
    public int[][] puzzle;
    
    // Moves to solve, and nnumber of moves taken already
    public int toSolve, moves;
    
    // Position of blank space 
    public int x, y;
    
    public Board(int[][] puzzle) {
        this.puzzle = puzzle;
        this.toSolve = 0;
        this.moves = 0;
        this.x = 0;
        this.y = 0;
    }
    
    /**
    * isGoal checks if the puzzle is in its goal state
    *  Goal state starts with 1 in the top left corner
    *  Goal state:
    *      {1, 2. 3}
    *      {4, 5, 6}
    *      {7, 8, 0}
    *  Where 0 is the blank space
    */
    public boolean isGoal() {
        int current = 1;
        boolean soFar = true;
        outer: for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (current == 9) {
                    break outer;
                }
                if (this.puzzle[i][j] != current) {
                    soFar = false;
                }
                current++;
            }
        }
        return soFar;
    }
    
    /**
    * isSolvable checks if a puzzle is solvable
    *  uses inversion to check
    *  if inversion is odd, it is not solvable
    * @return a boolean value
    */
    public static boolean isSolvable(int[][] puzzle) {
        int count = 0;
        for (int i = 0; i < 3 - 1; i++)
            for (int j = i + 1; j < 3; j++)
                if (puzzle[j][i] > 0 && puzzle[j][i] > puzzle[i][j])
                    count++;

        return (count % 2 == 0);
    }

    public static int[][] randomBoard() {
        boolean solvable = false;
        int[][] problem = new int[3][3];
        while (!solvable) {
            List<Integer> starter = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                starter.add(i);
            }
            Random rand = new Random();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int rando = rand.nextInt(starter.size());
                    problem[i][j] = starter.get(rando);
                    starter.remove(rando);
                }
            }
            if (isSolvable(problem))
                solvable = true;
        }
        return problem;
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
}
