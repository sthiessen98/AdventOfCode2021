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

            paths = traverseCaveSystem("start","", pairs, "start", new ArrayList<String>());
            System.out.println(paths.size());


        }catch(Exception e){
            System.out.println(e);
        }
    }



    /*
    Implementation of recursive BFS
     */
    public static ArrayList<String> traverseCaveSystem(String nextCave, String currPath, ArrayList<Pair> pairs, String smallCavesVisited, ArrayList<String> allPaths){

        if(currPath.isEmpty()){
            currPath = currPath.concat(nextCave);
        }else{
            currPath = currPath.concat("," + nextCave);
        }

        //Are we at the end?
        if(nextCave.equals("end")){
            allPaths.add(currPath);
        }

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
                for(int i=0; i<caves.length;i++){
                    if(exit.equals(caves[i])){
                        visited = true;
                    }
                }
                if(!visited){
                    //Add next cave and recurse through it's entries.
                    if(exit.matches("[a-z]+")){
                        allPaths =  traverseCaveSystem(exit, currPath, pairs, smallCavesVisited + "," + exit, allPaths);
                    }else{
                        allPaths =  traverseCaveSystem(exit, currPath, pairs, smallCavesVisited, allPaths);
                    }
                }
            }
        }
        return allPaths;
    }

}
