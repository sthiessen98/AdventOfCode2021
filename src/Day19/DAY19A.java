package Day19;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class DAY19A {
    public static void main(String[] args){

        File file = new File("src/Inputs/day19.txt");

        try{

            java.util.Scanner sc =new Scanner(file);
            LinkedList<BeaconScanner> scanners = new LinkedList<>();
            BeaconScanner currScanner = new BeaconScanner(0);

            while(sc.hasNextLine()){
                String line = sc.nextLine();

                //Create new Scanner Object
                if(line.startsWith("---")) {

                    line = line.replace("--- scanner ", "");
                    line = line.replace(" ---", "");

                    BeaconScanner scanner = new BeaconScanner(Integer.parseInt(line));
                    scanners.add(scanner);
                    currScanner = scanner;
                }else if(line.contains(",")){
                    //Add Coordinates to last scanner object
                    String[] strArray = line.split(",");
                    Coordinates coord = new Coordinates(Integer.parseInt(strArray[0]), Integer.parseInt(strArray[1]), Integer.parseInt(strArray[2]));
                    currScanner.getBeacons().add(coord);
                }
            }

            //TODO: Create DFS to rotate and compare beacons to each other.

        }catch(Exception e){
            System.out.println(e);
        }
    }

}
