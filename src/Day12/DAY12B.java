package Day12;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DAY12B {
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

            paths = traverseCaveSystem("start","",false,  pairs, "start", new ArrayList<String>());
            System.out.println(paths.size());


        }catch(Exception e){
            System.out.println(e);
        }
    }



    /*
    Implementation of recursive BFS
     */
    public static ArrayList<String> traverseCaveSystem(String nextCave, String currPath, boolean doubleVisit, ArrayList<Pair> pairs, String smallCavesVisited, ArrayList<String> allPaths){

        if(currPath.isEmpty()){
            currPath = currPath.concat(nextCave);
        }else{
            currPath = currPath.concat("," + nextCave);
        }

        //Are we at the end?
        //Check if duplicate path exists
        if(nextCave.equals("end")){
            boolean pathExists = false;
            for(int x=0;x<allPaths.size();x++){
                if(allPaths.get(x).equals(currPath)){
                    pathExists=true;
                }
            }

            if(!pathExists){
                allPaths.add(currPath);
            }
        }
        else{
            for(int x=0; x<pairs.size();x++){

                boolean cont = false;
                String entry = "";
                String exit = "";

                if(pairs.get(x).getEntry().equals(nextCave)) {
                    entry = pairs.get(x).getEntry();
                    exit = pairs.get(x).getExit();
                    cont=true;
                }else if(pairs.get(x).getExit().equals(nextCave)){
                    exit = pairs.get(x).getEntry();
                    entry = pairs.get(x).getExit();
                    cont = true;
                }

                if(cont){
                    //Are we in a small cave we've visited before?
                    String[] caves = smallCavesVisited.split(",");
                    boolean visited = false;
                    boolean nextLevelDoubleVisit = doubleVisit;

                    if(exit.equals("start")){
                        visited = true;
                    }

                    for(int i=0; i<caves.length;i++){
                        if(exit.equals(caves[i])){
                            if(!doubleVisit && !exit.equals("start")){
                                nextLevelDoubleVisit = true;
                            }else{
                                visited = true;
                            }
                        }
                    }

                    if(!visited){
                        //Add next cave and recurse through it's entries.
                        if(exit.matches("[a-z]+")){
                            allPaths =  traverseCaveSystem(exit, currPath, nextLevelDoubleVisit, pairs, smallCavesVisited + "," + exit, allPaths);
                        }else{
                            allPaths =  traverseCaveSystem(exit, currPath, nextLevelDoubleVisit, pairs, smallCavesVisited, allPaths);
                        }
                    }
                }
            }
        }
        return allPaths;
    }

}
