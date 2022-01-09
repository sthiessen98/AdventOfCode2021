package Day5;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class DAY5A {
    public static void main(String[] args){

        File file = new File("src/Inputs/day5.txt");

        try{
            Mapping map = new Mapping();

            Scanner sc =new Scanner(file);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] linePoints = line.split(" -> ");
                String[] coord1 = linePoints[0].split(",");
                String[] coord2 = linePoints[1].split(",");

                //Do x-values equal? --> Horizontal Line
                if(coord1[0].equals(coord2[0])){
                    int x = Integer.parseInt(coord1[0]);
                    int y1 = min(Integer.parseInt(coord1[1]), Integer.parseInt(coord2[1]));
                    int y2 = max(Integer.parseInt(coord1[1]), Integer.parseInt(coord2[1]));

                    for(int i=y1;i<=y2;i++){
                        map.add(x, i);
                    }
                }

                //Do Y-values equal? --> Vertical Line
                if(coord1[1].equals(coord2[1])){
                    int y = Integer.parseInt(coord1[1]);
                    int x1 = min(Integer.parseInt(coord1[0]), Integer.parseInt(coord2[0]));
                    int x2 = max(Integer.parseInt(coord1[0]), Integer.parseInt(coord2[0]));

                    for(int i=x1; i<=x2; i++){
                        map.add(i, y);
                    }
                }
            }

            System.out.println(map.getOverlaps());



        }catch(Exception e){
        }
    }
}

