package Day7;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Math.abs;

public class DAY7B {
    public static void main(String[] args){

        File file = new File("src/Inputs/Day7.txt");

        try{

            //Get and parse input
            Scanner sc =new Scanner(file);
            String[] strArray = sc.nextLine().split(",");
            int[] input = new int[strArray.length];
            int maxPos = 0;
            for(int x=0; x<input.length;x++){
                input[x] = Integer.parseInt(strArray[x]);
                if(input[x] > maxPos){
                    maxPos = input[x];
                }
            }

            int minFuelUsed = Integer.MAX_VALUE;
            Map<Integer, Integer> costTable = new HashMap<>();

            for(int x=0; x<= maxPos; x++){

                int fuel = 0;

                for(int i=0; i<input.length; i++){
                    int dist = abs(input[i] - x);
                    fuel += lookupCost(dist, costTable);
                }

                if(fuel < minFuelUsed){
                    minFuelUsed = fuel;
                }
            }

            System.out.println(minFuelUsed);




        }catch(Exception e){
            System.out.println(e);
        }
    }

    private static int lookupCost(int dist, Map<Integer, Integer> memoizationTable){
        if(dist == 1){
            return 1;
        }
        if(memoizationTable.containsKey(dist)){
            return memoizationTable.get(dist);
        }
        else{
            int cost = calculateCost(dist);
            memoizationTable.put(dist, cost);
            return cost;
        }
    }

    private static int calculateCost(int dist){
        int cost = 0;
        for(int x =1; x<=dist;x++){
            cost+= x;
        }
        return cost;
    }
}
