package Day12;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class DAY12A {
    public static void main(String[] args){

        File file = new File("src/Inputs/day12.txt");

        try{

            ArrayList<Pair> pairs = new ArrayList<>();
            ArrayList<String> paths = new ArrayList<>();

            Scanner sc =new Scanner(file);
            while(sc.hasNextLine()){
                String[] strArray = sc.nextLine().split("-");
                pairs.add(new Pair(strArray[0], strArray[1]));
            }

            paths = traverseCaveSystem("start","start", pairs, "", new ArrayList<String>());
            System.out.println(paths);
            //test git push


        }catch(Exception e){
            System.out.println(e);
        }
    }



    /*
    Implementation of recursive BFS
     */
    public static ArrayList<String> traverseCaveSystem(String nextCave, String currPath, ArrayList<Pair> pairs, String smallCavesVisited, ArrayList<String> allPaths){
        for(int x=0; x<pairs.size();x++){
            if(pairs.get(x).getEntry().equals(nextCave)){
                String exit = pairs.get(x).getExit();

                //Are we at the end?
                if(exit.equals("end")){
                    currPath = currPath.concat("," + exit);
                    allPaths.add(currPath);
                }


                else{
                    //Are we in a small cave we've visited before?
                    String[] caves = smallCavesVisited.split(",");
                    boolean visited = false;
                    for(int i=0; i<caves.length;i++){
                        if(exit.equals(caves[i])){
                            visited = true;
                        }
                    }
                    if(!visited){
                        //Add next cave and recurse through it's entries.
                        currPath = currPath.concat("," + exit);
                        if(exit.matches("[A-Z]+")){
                            smallCavesVisited = smallCavesVisited.concat("," + exit);
                        }
                        allPaths =  traverseCaveSystem(exit, currPath, pairs, smallCavesVisited, allPaths);
                    }
                }
            }
        }
        return allPaths;
    }

}
