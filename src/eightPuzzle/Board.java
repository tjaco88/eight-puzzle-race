package eightpuzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        this.puzzle = new int[3][3];
        this.toSolve = moves;
        this.x = 2;
        this.y = 2;
        this.puzzle[0][0] = 1;
        this.puzzle[1][0] = 2;
        this.puzzle[2][0] = 3;
        this.puzzle[0][1] = 4;
        this.puzzle[1][1] = 5;
        this.puzzle[2][1] = 6;
        this.puzzle[0][2] = 7;
        this.puzzle[1][2] = 8;
        this.puzzle[2][2] = 0; 
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
    *      {1, 2. 3}
    *      {4, 5, 6}
    *      {7, 8, 0}
    *  Where 0 is the blank space
    */
    public boolean isGoal() {
        int[][] goal = new int[3][3];
        goal[0][0] = 1;
        goal[1][0] = 2;
        goal[2][0] = 3;
        goal[0][1] = 4;
        goal[1][1] = 5;
        goal[2][1] = 6;
        goal[0][2] = 7;
        goal[1][2] = 8;
        goal[2][2] = 0; 
        
        return this.isEquals(goal);
    }
    
    /**
    * isSolvable checks if a puzzle is solvable
    *  uses inversion to check
    *  if inversion is odd, it is not solvable
    * @return a boolean value
    */
    public boolean isSolvable(int[][] puzzle) {
        int count = 0;
        int index = 0;
        int[] inversion = new int[8];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(!(puzzle[i][j] == 0)){
                    inversion[index] = puzzle[i][j];
                    index++;
                }
            }
        }
        
        for (int i = 0; i < inversion.length; i++){
            for (int j = i + 1; j < inversion.length; j++){
                if (inversion[j] > inversion[i]){
                    count++;
                }
            }
        }
        
        return !(count % 2 == 1);
    }
    
    /**
    * randomBoard produces a random eight puzzle board
    *  guranteed to be solvable because of loop
    *  sets index of blank space
    */
    public void randomBoard() {
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
                    if(starter.get(rando) == 0){
                        this.x = i;
                        this.y = j;
                    }
                    starter.remove(rando);
                }
            }
            if (isSolvable(problem))
            solvable = true;
        }
        this.puzzle = problem;
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
