package Day8;

import java.io.File;
import java.util.Scanner;

public class DAY8A {
    public static void main(String[] args){

        File file = new File("src/Inputs/day8.txt");

        try{

            int result = 0;
            Scanner sc =new Scanner(file);
            while(sc.hasNextLine()){
               String[] line = sc.nextLine().split("\\|");
               String[] output = line[1].split(" ");
               for(int x=0; x<output.length;x++){
                   //System.out.println(output[x].length() + "  " + output[x]);
                   int length = output[x].length();
                   if(length == 2 || length == 3 || length == 4 || length == 7){
                       result++;
                   }
               }
            }

            System.out.println(result);

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
