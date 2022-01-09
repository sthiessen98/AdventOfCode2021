package Day5;

import java.io.File;
import java.util.Scanner;

import static java.lang.Math.*;

public class DAY5B {
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

                //Do x-values equal? --> Must be vertical line
                if(coord1[0].equals(coord2[0])){
                    int x = Integer.parseInt(coord1[0]);
                    int y1 = min(Integer.parseInt(coord1[1]), Integer.parseInt(coord2[1]));
                    int y2 = max(Integer.parseInt(coord1[1]), Integer.parseInt(coord2[1]));

                    for(int i=y1;i<=y2;i++){
                        map.add(x, i);
                    }
                }

                //Do Y-values equal? --> Must be horizontal line
                else if(coord1[1].equals(coord2[1])){
                    int y = Integer.parseInt(coord1[1]);
                    int x1 = min(Integer.parseInt(coord1[0]), Integer.parseInt(coord2[0]));
                    int x2 = max(Integer.parseInt(coord1[0]), Integer.parseInt(coord2[0]));

                    for(int i=x1; i<=x2; i++){
                        map.add(i, y);
                    }
                }

                //Must be diagonal line
                else{
                    int x1 = Integer.parseInt(coord1[0]);
                    int x2 = Integer.parseInt(coord2[0]);
                    int y1 = Integer.parseInt(coord1[1]);
                    int y2 = Integer.parseInt(coord2[1]);


                    if(x1<=x2 && y1<=y2) {
                        for (int i = 0; i <= x2 - x1; i++) {
                            map.add(x1 + i, y1 + i);
                        }
                    }else if(x2<= x1 && y2<=y1){
                        for (int i = 0; i <= x1 - x2; i++) {
                            map.add(x2 + i, y2 + i);
                        }
                    }else{

                        for(int i=0;i <= abs(x2-x1);i++){
                            if(x1 <= x2){
                                map.add(x1+i, y1-i);
                            }else{
                                map.add(x2+i, y2-i);
                            }
                        }
                    }
                }
            }

            System.out.println(map.getOverlaps());

        }catch(Exception e){
        }
    }
}



