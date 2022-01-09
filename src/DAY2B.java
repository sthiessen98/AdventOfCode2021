import java.io.File;
import java.util.Scanner;

public class DAY2B {
    public static void main(String[] args){

        File file = new File("src/Inputs/day2.txt");
        int distance = 0;
        int depth = 0;
        int aim =0;
        try{
            Scanner sc =new Scanner(file);
            while(sc.hasNextLine()){
                String[] line = sc.nextLine().split(" ");
                if(line[0].equals("forward")){
                    distance += Integer.parseInt(line[1]);
                    depth += Integer.parseInt(line[1]) * aim;
                }else if(line[0].equals("up")){
                    aim -= Integer.parseInt(line[1]);
                }else if(line[0].equals("down")){
                    aim += Integer.parseInt(line[1]);
                }

            }

            System.out.println(distance * depth);

        }catch(Exception e){
        }
    }
}
