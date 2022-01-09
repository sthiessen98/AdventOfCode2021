package Day9;

import java.io.File;
import java.util.Scanner;

public class DAY9A {
    public static void main(String[] args){

        File file = new File("src/Inputs/day9.txt");
        final int ARRAYWIDTH = 100;
        final int ARRAYHEIGHT = 100;

        try{

            int[][] heightmap = new int[ARRAYWIDTH][ARRAYHEIGHT];
            int riskLevel = 0;
            Scanner sc =new Scanner(file);

            //Read txt file and create array
            int lineCount = 0;
            while(sc.hasNextLine()){
              char[] charArray = sc.nextLine().toCharArray();
              for(int x=0;x<charArray.length;x++){
                  heightmap[x][lineCount] = Integer.parseInt(String.valueOf(charArray[x]));
              }
              lineCount++;
            }

            //Loop through array
            for(int x=0; x<ARRAYWIDTH;x++){
                for(int y=0; y<ARRAYHEIGHT; y++){

                    int curr = heightmap[x][y];
                    //Check each cardinal direction of curr point
                    boolean lowPoint = true;
                    if(x != 0){
                        if(curr >= heightmap[x-1][y]){
                            lowPoint = false;
                        }
                    }
                    if(x != ARRAYWIDTH-1){
                        if(curr >= heightmap[x+1][y]){
                            lowPoint = false;
                        }
                    }
                    if(y != 0){
                        if(curr >= heightmap[x][y-1]){
                            lowPoint = false;
                        }
                    }
                    if(y != ARRAYHEIGHT-1){
                        if(curr >= heightmap[x][y+1]){
                            lowPoint = false;
                        }
                    }

                    if(lowPoint){
                        System.out.println("x:" + x + " y:" + y + " curr:" + curr);
                        riskLevel += curr + 1;
                    }
                }
            }

            System.out.println(riskLevel);

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
