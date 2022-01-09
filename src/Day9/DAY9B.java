package Day9;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.awt.Point;

public class DAY9B {
    public static void main(String[] args){

        File file = new File("src/Inputs/day9.txt");
        final int ARRAYWIDTH = 100;
        final int ARRAYHEIGHT = 100;

        try{

            int[][] heightmap = new int[ARRAYWIDTH][ARRAYHEIGHT];
            boolean[][] assignedBasin = new boolean[ARRAYWIDTH][ARRAYHEIGHT];
            ArrayList<Point> lowPoints = new ArrayList<>();
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
                        lowPoints.add(new Point(x,y));
                    }
                }
            }

            //Look at each lowpoint and recursively loop through adjacent points

            ArrayList<Integer> sizes = new ArrayList<>();
            for(int i=0; i<lowPoints.size();i++){
                sizes.add(findBasin(lowPoints.get(i), heightmap, assignedBasin));
            }
            Collections.sort(sizes);
            Collections.reverse(sizes);
            System.out.println(sizes.get(0) *sizes.get(1) *sizes.get(2));

        }catch(Exception e){
            System.out.println(e);
        }
    }

    private static int findBasin(Point curr, int[][] heightMap, boolean[][] assignedBasin){
        int x = curr.x;
        int y = curr.y;

        if(assignedBasin[x][y]){
            return 0;
        }
        if(heightMap[x][y] == 9) {
            return 0;
        }else{
            int sum = 1;
            assignedBasin[x][y] = true;
            if(x>0){
                sum += findBasin(new Point(x-1,y), heightMap, assignedBasin);
            }
            if(x<99){
                sum+= findBasin(new Point(x+1,y), heightMap, assignedBasin);
            }
            if(y>0){
                sum+= findBasin(new Point(x,y-1), heightMap, assignedBasin);
            }
            if(y<99){
                sum+= findBasin(new Point(x,y+1), heightMap, assignedBasin);
            }
            return sum;
        }
    }

}
