package eightpuzzle;

import java.util.List;

public class Node {
    Node parent;
    Board board;
    Node[] childNodes;
    String moveFromParent;
    List<String> moves;
    int fCost, gCost, hCost;

    public Node(Board board){
        this.board = board;
        this.gCost = 0;
        this.parent = null;
    }

    public Node(){
        this.board = new Board();
    }

    /**
     * Gets the goal row for state state
     * @param state an int
     * @return the row of the goal for state
     */
    public int getRow(int state){
        if(state == 1 || state == 2 || state == 3){
            return 0;
        }
        if(state == 4 || state == 5 || state == 6){
            return 1;
        }
        else
            return 2;
    }

    /**
     * Gets the goal column for state state
     * @param state an int
     * @return the column of the goal for state
     */
    public int getCol(int state){
        if(state == 1 || state == 4 || state == 7){
            return 0;
        }
        if(state == 2 || state == 5 || state == 8){
            return 1;
        }
        else
            return 2;
    }

    /**
     * Find the manhattan distance for all pieces in the board
     * @return g, the distance
     */
    public int manhattan(){
        int g = 0;
        for(int i = 0; i < 3; i++){
            for(int j =0; j < 0; j++){
                int goalX = getRow(this.board.puzzle[i][j]);
                int goalY = getCol(this.board.puzzle[i][j]);
                g += Math.abs((i - goalX)) + Math.abs((j - goalY));
            }
        }
        return g;
    }

    public void addChildren(){
        int i = 0;
        int f = this.fCost + 1;
        
        this.moves = this.board.getMoves();
        this.childNodes = new Node[moves.size()];

        Board test = new Board(this.board);

        for(String move : this.moves){
            //System.out.println(move + " " + i);
            
            this.childNodes[i] = new Node();
            this.childNodes[i].parent = this;
			this.childNodes[i].moveFromParent = move;
            this.childNodes[i].board = Board.move(test, move);
            //this.board.printBoard();
            //this.childNodes[i].board.printBoard();
			this.childNodes[i].fCost = f;
			this.childNodes[i].gCost = childNodes[i].manhattan();
			this.childNodes[i].hCost = this.childNodes[i].fCost + this.childNodes[i].gCost;
			i++;
        }
    }
}
        