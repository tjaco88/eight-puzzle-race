package eightpuzzle;

import java.util.List;
import java.util.Random;

public class Solved {
    public Board board;
    String solution[];
    int moves;
    
    public Solved(int moves){
        this.moves = moves;
        this.solution = new String[moves];
        this.board = new Board(moves);
        
        Random rand = new Random();
        for(int i = 0; i < moves; i++){
            List<String> possibleMoves = this.board.getMoves();
            int rando = rand.nextInt(possibleMoves.size());
            String move = possibleMoves.get(rando);
            this.board = Board.move(this.board, move);
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

    public String viewSolution(int move){
        return this.solution[move];
    }
    
}