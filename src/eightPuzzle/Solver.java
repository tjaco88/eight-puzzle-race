package eightpuzzle;

public class Solver {


    public static void main(String[] args){

        int[][] board = Board.randomBoard();
        Board eightPuzzle = new Board(board);
        eightPuzzle.printBoard();

    }
}
