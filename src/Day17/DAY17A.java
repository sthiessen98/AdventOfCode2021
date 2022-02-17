package Day17;

import java.io.File;
import java.util.Scanner;

public class DAY17A {
    public static void main(String[] args){

        File file = new File("src/Inputs/day17.txt");

        try{

            Scanner sc =new Scanner(file);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] strArray = line.split(", y=");
                String[] coords = strArray[1].split("\\.\\.");

                int lowPoint = (-Integer.parseInt(coords[0])) - 1;
                int height = 0;
                while(lowPoint > 0){
                    height += lowPoint;
                    lowPoint--;
                }

                System.out.println(height);

            }

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
