package Day6;

import java.io.File;
import java.math.BigInteger;
import java.util.Scanner;

public class DAY6B {
    public static void main(String[] args){

        File file = new File("src/Inputs/day6.txt");

        try{

            final int CYCLE = 9;
            final int DAYS = 256;

            BigInteger[] daysToSpawn = new BigInteger[CYCLE];
            BigInteger[] newSpawnTimers = new BigInteger[CYCLE];

            //Initialize arrays
            for(int x=0;x<CYCLE;x++){
                daysToSpawn[x] = BigInteger.valueOf(0);
                newSpawnTimers[x] = BigInteger.valueOf(0);
            }

            Scanner sc =new Scanner(file);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] strArray = line.split(",");
                for(int x=0;x<strArray.length;x++){
                    int fishTimer = Integer.parseInt(strArray[x]);
                    daysToSpawn[fishTimer] = daysToSpawn[fishTimer].add(BigInteger.valueOf(1));
                }
            }

            for(int i=0; i<DAYS; i++){
                for(int x=CYCLE-1; x >= 0; x--){
                    if(x==0){
                        newSpawnTimers[8] = daysToSpawn[0];
                        newSpawnTimers[6] = newSpawnTimers[6].add(daysToSpawn[0]);
                    }else{
                        newSpawnTimers[x-1] = daysToSpawn[x];
                    }
                }
                for(int x=CYCLE-1; x>=0; x--){
                    daysToSpawn[x] = newSpawnTimers[x];
                    newSpawnTimers[x] = BigInteger.valueOf(0);
                }
            }

            BigInteger totalFish =BigInteger.valueOf(0);
            for(int x=0; x<daysToSpawn.length;x++){
                totalFish = totalFish.add(daysToSpawn[x]);
            }

            System.out.println(totalFish);

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
