package eightpuzzle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Solver {
    Board initial;
    public String[] solution;
    public StringBuffer moves;
    public int numMoves;
    
    public Solver(Board board){
        moves = new StringBuffer();
        moves.append("{ 1 2 3 }\n" + "{ 4 5 6 }\n" + "{ 7 8 0 }\n");
        this.initial = board;
        this.numMoves = 0;
    }
    
    public void path(Node current){
        Node temp = current;
        int i = 0;
        int j;
        String[] temps = new String[50];
        
        
        while(temp.parent != null){
            temps[i++] = temp.moveFromParent;
            temp = temp.parent;
            this.numMoves++;
        }
        
        int size = i;
        this.solution = new String[size];
        for(i = 0, j = size-1; i < size; i++, j--)
        {
            this.solution[i] = temps[j];
            this.moves.append( "Move " + this.solution[i] + "\n");
        }
    }
    
    public boolean alreadyIn(List<Node> list, Node child){
        for(Node n : list){
            if(n.board.isEquals(child.board.puzzle)){
                return true;
            }
        }
        return false;
    }
    
    public boolean alreadyIn(PriorityQueue<Node> queue, Node child){
        Iterator<Node> value = queue.iterator();
        while(value.hasNext())
        if(value.next().board.isEquals(child.board.puzzle)){
            return true;
        }
        return false;
    }
    
    public void solve(){
        Comparator<Node> solveComparator= new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return node1.hCost - node2.hCost;
            }
        };
        PriorityQueue<Node> solveQueue = new PriorityQueue<>(solveComparator);
        List<Node> visited = new ArrayList<>();
        
        Node intialState = new Node(this.initial);
        Node current = new Node();
        boolean solved = false;
        solveQueue.add(intialState);
        while(!solveQueue.isEmpty()){
            current = solveQueue.poll();
            current.addChildren();
            System.out.println(visited.size() + solveQueue.size());
            for(Node child : current.childNodes){
                
                if(child.board.isGoal()){
                    this.path(child);
                    System.out.println("solved");
                    solved = true;
                    break;
                }
                if(!(alreadyIn(solveQueue, child) || alreadyIn(visited, child))){
                    solveQueue.add(child);
                }
            }
            visited.add(current);
            if(solved == true){
                break;
            }
        }
    }
}

