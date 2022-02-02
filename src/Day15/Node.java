package Day15;

import java.util.ArrayList;

public class Node {

    private int weight;
    private ArrayList<Node> neighbours;
    private boolean visited;
    int tentativeDistance;


    public Node(int w){
        visited = false;
        weight = w;
        tentativeDistance = Integer.MAX_VALUE;
        neighbours = new ArrayList<>();
    }

    public int getWeight(){
        return weight;
    }

    public void addNeighbour(Node n){
        neighbours.add(n);
    }

    public ArrayList<Node> getNeighbours(){
        return neighbours;
    }

    public boolean isVisited(){ return visited;}

    public void setVisited(){ visited = true;}

    public int getTentativeDistance(){ return tentativeDistance;}

    public void setTentativeDistance(int t){ tentativeDistance = t;}
}
