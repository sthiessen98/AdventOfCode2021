package Day6;

import java.io.File;
import java.util.Scanner;

public class DAY6A {
    public static void main(String[] args){

        File file = new File("src/Inputs/day6.txt");

        try{

            final int CYCLE = 9;
            final int DAYS = 80;

            int[] daysToSpawn = new int[CYCLE];
            int[] newSpawnTimers = new int[CYCLE];

            Scanner sc =new Scanner(file);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] strArray = line.split(",");
                for(int x=0;x<strArray.length;x++){
                    int fishTimer = Integer.parseInt(strArray[x]);
                    daysToSpawn[fishTimer]++;
                }
            }

            for(int i=0; i<DAYS; i++){
                for(int x=CYCLE-1; x >= 0; x--){
                    if(x==0){
                        newSpawnTimers[8] = daysToSpawn[0];
                        newSpawnTimers[6] += daysToSpawn[0];
                    }else{
                        newSpawnTimers[x-1] = daysToSpawn[x];
                    }
                }
                for(int x=CYCLE-1; x>=0; x--){
                    daysToSpawn[x] = newSpawnTimers[x];
                    newSpawnTimers[x] = 0;
                }
            }

            int totalFish = 0;
            for(int x=0; x<daysToSpawn.length;x++){
                totalFish += daysToSpawn[x];
            }

            System.out.println(totalFish);

        }catch(Exception e){
        }
    }
}
