package Day14;

import java.io.File;
import java.security.Key;
import java.util.*;

public class DAY14B {
    public static void main(String[] args) {

        File file = new File("src/Inputs/day14.txt");

        try {
            final int STEPS = 40;

            Scanner sc = new Scanner(file);

            //get initial polymer and break into pairs
            //Pairs are stored in map as there are millions of  repeated calculations for identical pairs
            String initialPolymer = sc.nextLine();
            Map<String, Long> polymerPairs = new HashMap<>();

            for(int x =0; x < initialPolymer.length()-1; x++){
                String newPair = initialPolymer.charAt(x) + "" + initialPolymer.charAt(x+1);
                if(polymerPairs.containsKey(newPair)){
                    long oldValue = polymerPairs.get(newPair);
                    polymerPairs.replace(newPair, oldValue+1);
                }else{
                    polymerPairs.put(newPair, Long.valueOf(1));
                }
            }

            //Get Polymer Rules and store in map
            //Skip line first to get to rules
            sc.nextLine();

            Map<String, String> polymerRules = new HashMap<>();
            while(sc.hasNextLine()){
                String[] strArray = sc.nextLine().split(" -> ");
                polymerRules.put(strArray[0], strArray[0].charAt(0) + strArray[1] + strArray[0].charAt(1));
            }

            //Go through polymerPairs, and create new map each step
            int currStep = 0;
            while(currStep<STEPS){

                Map<String, Long> nextStep = new HashMap<>();

                for(String key: polymerPairs.keySet()) {
                    Long count = polymerPairs.get(key);

                    String nextPairs = polymerRules.get(key);
                    String pair1 = nextPairs.charAt(0) + "" + nextPairs.charAt(1);
                    String pair2 = nextPairs.charAt(1) + "" + nextPairs.charAt(2);

                    if(nextStep.containsKey(pair1)){
                        Long currValue = nextStep.get(pair1);
                        nextStep.replace(pair1, count + currValue);
                    }else{
                        nextStep.put(pair1, count);
                    }

                    if(nextStep.containsKey(pair2)){
                        Long currValue = nextStep.get(pair2);
                        nextStep.replace(pair2, count + currValue);
                    }else{
                        nextStep.put(pair2, count);
                    }
                }



                polymerPairs = nextStep;
                currStep++;
            }
            for(String key: polymerPairs.keySet()) {
                System.out.println("Key: " + key + ",  Value: " + polymerPairs.get(key));
            }


        }catch(Exception e){
            System.out.println(e);
        }

    }
}
