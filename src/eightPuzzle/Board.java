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
    
    public Board() {
        this.puzzle = new int[3][3];
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
    public boolean isSolvable(int[][] puzzle) {
        int count = 0;
        for (int i = 0; i < 3 - 1; i++)
            for (int j = i + 1; j < 3; j++)
                if (puzzle[j][i] > 0 && puzzle[j][i] > puzzle[i][j])
                    count++;

        return (count % 2 == 0);
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

    public void getMoves(){
        if(this.y == 0){
            System.out.print("DOWN, ");
        }
        if(this.y == 1){
            System.out.print("UP, " + "DOWN, ");
        }
        if(this.y == 2){
            System.out.print("UP, ");
        }
        if(this.x == 0){
            System.out.print("RIGHT");
        }
        if(this.x == 1){
            System.out.print("LEFT, " + "RIGHT");
        }
        if(this.x == 2){
            System.out.print("LEFT");
        }
        System.out.print(")\n");
    }

    public void move(String move){
        int temp = 0;
        if(move.equals("LEFT")){
            temp = this.puzzle[this.x - 1][this.y];
            this.puzzle[this.x - 1][this.y] = this.puzzle[this.x][this.y] ;
            this.puzzle[this.x][this.y] = temp;
            this.x -= 1;
        }
        if(move.equals("RIGHT")){
            temp = this.puzzle[this.x + 1][this.y];
            this.puzzle[this.x + 1][this.y] = this.puzzle[this.x][this.y] ;
            this.puzzle[this.x][this.y] = temp;
            this.x += 1;
        }
        if(move.equals("UP")){
            temp = this.puzzle[this.x][this.y - 1];
            this.puzzle[this.x][this.y - 1] = this.puzzle[this.x][this.y] ;
            this.puzzle[this.x][this.y] = temp;
            this.y -= 1;
        }
        if(move.equals("DOWN")){
            temp = this.puzzle[this.x][this.y + 1];
            this.puzzle[this.x][this.y + 1] = this.puzzle[this.x][this.y] ;
            this.puzzle[this.x][this.y] = temp;
            this.y +=1;
        }
    }
}
