package Day13;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DAY13B {
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

            //Loop through instructions
            for(int i=0;i <instructions.size(); i++){

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

            //Create visual to display answer for part B
            int maxX = 0;
            int maxY = 0;
            for(int i=0; i < points.size(); i++){
                Point point = points.get(i);
                if(point.x > maxX){
                    maxX = point.x;
                }
                if(point.y > maxY){
                    maxY = point.y;
                }
            }

            for(int i=0; i <= maxY; i++){
                String result = "";
                for(int j=0; j <= maxX; j++){
                    Point p = new Point(j,i);
                    if(points.contains(p)){
                        result = result.concat("$");
                    }else{
                        result = result.concat(".");
                    }
                }
                System.out.println(result);
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
