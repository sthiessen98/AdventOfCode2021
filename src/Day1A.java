import java.io.File;
import java.util.Scanner;

public class Day1A {

    public static void main(String[] args){

        File file = new File("src/Inputs/day1.txt");

        try{

            int count = 0;
            int lastNum = 10000;

            Scanner sc =new Scanner(file);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                if(lastNum < Integer.parseInt(line)){
                    count++;
                }
                lastNum = Integer.parseInt(line);
            }

            System.out.println(count);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
