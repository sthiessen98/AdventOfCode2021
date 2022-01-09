import java.io.File;
import java.util.Scanner;

public class DAY1B {
    public static void main(String[] args){

        File file = new File("src/Inputs/day1.txt");

        try{

            int count = 0;

            Scanner sc =new Scanner(file);
            int secondLastNum = Integer.parseInt(sc.nextLine());
            int lastNum = Integer.parseInt(sc.nextLine());
            int currNum = Integer.parseInt(sc.nextLine());
            int lastSum = secondLastNum + lastNum + currNum;
            while(sc.hasNextLine()){
                secondLastNum = lastNum;
                lastNum = currNum;
                currNum = Integer.parseInt(sc.nextLine());

                if(secondLastNum + lastNum + currNum > lastSum){
                    count++;
                }
                lastSum = secondLastNum + lastNum + currNum;
            }

            System.out.println(count);
        }catch(Exception e){
        }
    }
}
