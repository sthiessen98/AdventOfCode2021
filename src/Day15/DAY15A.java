package Day15;

import java.io.File;
import java.util.Scanner;

public class DAY15A {

    public static void main(String[] args){

        File file = new File("src/Inputs/day15.txt");
        final int LINECOUNT = 100;

        try{

            Scanner sc =new Scanner(file);

            System.out.println(file.length());

            while(sc.hasNextLine()){

            }

        }catch(Exception e){
            System.out.println(e);
        }
    }

}
