package Day7;

import java.io.File;
import java.util.Scanner;

import static java.lang.Math.abs;

public class DAY7A {
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

            for(int x=0; x<= maxPos; x++){

                int fuel = 0;

                for(int i=0; i<input.length; i++){
                    fuel += abs(input[i] - x);
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
}
