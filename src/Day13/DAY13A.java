package Day13;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DAY13A {
    public static void main(String[] args){

        File file = new File("src/Inputs/day13.txt");

        try{

            ArrayList<Point> points = new ArrayList<>();
            ArrayList<String> instructions = new ArrayList<>();

            Scanner sc =new Scanner(file);
            boolean isInstruction = false;

            while(sc.hasNextLine()){
                String line = sc.nextLine();

                if(line.equals("")){
                    isInstruction = true;
                }
                else if(!isInstruction){
                    String[] strArray = line.split(",");
                    Point point = new Point(Integer.parseInt(strArray[0]), Integer.parseInt(strArray[1]));
                    points.add(point);
                }else{
                    String[] strArray = line.split(" ");
                    instructions.add(strArray[2]);
                }
            }

            //First instruction only
            for(int i=0;i <1; i++){

                String[] instruction = instructions.get(i).split("=");
                String direction = instruction[0];
                int coord = Integer.parseInt(instruction[1]);

                //Loop through points
                for(int j=0; j<points.size();j++){
                    Point point = points.get(j);
                    if(direction.equals("x") && point.x > coord){
                        int diff = point.x - coord;

                        Point newPoint = new Point(coord - diff, point.y);
                        if(!points.contains(newPoint)){
                            points.add(newPoint);
                        }

                        points.remove(point);
                        j--;

                    }else if(direction.equals("y") && point.y > coord){
                        int diff = point.y - coord;

                        Point newPoint = new Point(point.x, coord- diff);
                        if(!points.contains(newPoint)){
                            points.add(newPoint);
                        }

                        points.remove(point);
                        j--;
                    }
                }
            }

            System.out.println(points.size());


        }catch(Exception e){
            System.out.println(e);
        }
    }
}
