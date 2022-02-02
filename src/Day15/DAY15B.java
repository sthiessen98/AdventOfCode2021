package Day15;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.min;

public class DAY15B {
    public static void main(String[] args){

        File file = new File("src/Inputs/day15.txt");
        final int SIZE = 500;

        try{

            Scanner sc =new Scanner(file);

            int[][] weightArray = new int[SIZE/5][SIZE/5];
            Node[][] nodeArray = new Node[SIZE][SIZE];

            //Create weightmap
            int lineCount =0;
            while(sc.hasNextLine()){
                String[] inputs = sc.nextLine().split("");
                for(int x=0; x < inputs.length; x++){
                    weightArray[lineCount][x] = Integer.parseInt(inputs[x]);
                }
                lineCount++;
            }

            //Create node map
            for(int x=0; x < SIZE; x++){
                for(int y=0; y < SIZE; y++){
                    int xArea = x/(SIZE/5);
                    int yArea = y/(SIZE/5);

                    int newWeight = weightArray[x%(SIZE/5)][y%(SIZE/5)] + xArea + yArea;
                    while(newWeight > 9){
                        newWeight -= 9;
                    }
                    nodeArray[x][y] = new Node(newWeight);
                }
            }

            ArrayList<Node> unvisitedSet = new ArrayList<>();
            //Set up edges and unvisited set
            for(int x=0; x < SIZE; x++){
                for(int y=0; y < SIZE; y++){
                    if(x !=0){
                        nodeArray[x][y].addNeighbour(nodeArray[x-1][y]);
                    }
                    if(x < SIZE-1){
                        nodeArray[x][y].addNeighbour(nodeArray[x+1][y]);
                    }
                    if(y !=0){
                        nodeArray[x][y].addNeighbour(nodeArray[x][y-1]);
                    }
                    if(y < SIZE-1){
                        nodeArray[x][y].addNeighbour(nodeArray[x][y+1]);
                    }

                    unvisitedSet.add(nodeArray[x][y]);
                }
            }

            //Set origin
            nodeArray[0][0].setTentativeDistance(0);

            //Dijkstras
            while(!unvisitedSet.isEmpty()){

                int smallestDist = Integer.MAX_VALUE;
                int node = 0;
                //Choose smallest distance node in unvisted set
                for(int x=0;x <unvisitedSet.size();x++){
                    if(unvisitedSet.get(x).getTentativeDistance() <= smallestDist){
                        smallestDist = unvisitedSet.get(x).getTentativeDistance();
                        node = x;
                    }
                }

                Node currentNode = unvisitedSet.get(node);
                unvisitedSet.remove(node);



                //Check distances of neighbours
                for(Node neighbour: currentNode.getNeighbours()){
                    if(unvisitedSet.contains(neighbour)){
                        neighbour.setTentativeDistance(min(neighbour.getTentativeDistance(), currentNode.getTentativeDistance() + neighbour.getWeight()));
                    }
                }

            }


            System.out.println("Lowest Risk: " + nodeArray[SIZE-1][SIZE-1].getTentativeDistance());


        }catch(Exception e){
            System.out.println(e);
        }
    }
}
