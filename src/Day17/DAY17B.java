package Day17;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class DAY17B {
    public static void main(String[] args){

        File file = new File("src/Inputs/day17.txt");

        try{

            //Get and parse input into max and min bounds of target area
            Scanner sc =new Scanner(file);
            String line = sc.nextLine();
            line = line.replace("target area: ","");
            String[] strArray = line.split(", ");
            strArray[0] = strArray[0].replace("x=","");
            strArray[1] = strArray[1].replace("y=","");

            String[] xArray = strArray[0].split("\\.\\.");
            String[] yArray = strArray[1].split("\\.\\.");

            int minX = Integer.parseInt(xArray[0]);
            int maxX = Integer.parseInt(xArray[1]);
            int minY = Integer.parseInt(yArray[0]);
            int maxY = Integer.parseInt(yArray[1]);

            int validShots = 0;
            //All possible moves exist within x= 0 -> maxX, and y = minY -> 0 - minY
            for(int x=0;x<=maxX;x++){
                for(int y=minY;y<=(-minY);y++){

                    int velocityX = x;
                    int velocityY= y;
                    int xPos = 0;
                    int yPos = 0;

                    //While projectile hasn't overshot the target, calculate
                    boolean cont = true;
                    while(xPos < maxX && yPos > minY && cont){

                        xPos += velocityX;
                        yPos += velocityY;

                        if(velocityX > 0){
                            velocityX--;
                        }else if(velocityX < 0) {
                            velocityX++;
                        }

                        velocityY--;

                        //Check if in target area
                        if(xPos >= minX && xPos <= maxX && yPos >= minY && yPos <= maxY){
                            validShots++;
                            cont = false;
                        }
                        else if(xPos > maxX || yPos < minY){
                            cont = false;
                        }
                    }
                }
            }

            System.out.println("Total valid answers: " + validShots);



        }catch(Exception e){
            System.out.println(e);
        }
    }


}
