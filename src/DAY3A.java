import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class DAY3A {

    private static final Integer ARRAYSIZE = 5;

    public static void main(String[] args){

        File file = new File("src/Inputs/day3.txt");

        try{
            Integer[] binary0 = new Integer[ARRAYSIZE];
            Integer[] binary1 = new Integer[ARRAYSIZE];
            Integer[] gamma = new Integer[ARRAYSIZE];
            Integer[] epsilon = new Integer[ARRAYSIZE];
            for(int i = 0; i < binary0.length; i++) {
                binary0[i] = 0;
                binary1[i] = 0;
                gamma[i] = 0;
                epsilon[i] = 0;
            }
            String zero = "0";


            //Get counts
            Scanner sc =new Scanner(file);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                for(int x=0; x <line.length();x++){
                    if(line.charAt(x) == zero.charAt(0)){
                        binary0[x]++;
                    }else{
                        binary1[x]++;
                    }
                }

            }

            //Fill out result int array with results
            for(int x=0; x < binary0.length;x++){
                if(binary0[x] < binary1[x]){
                    gamma[x] = 1;
                    epsilon[x] = 0;
                }else{
                    gamma[x] = 0;
                    epsilon[x] = 1;
                }
            }

            //Convert from int[] to string(binary) to decimal
            String gammaBinary = "";
            String epsilonBinary = "";
            for(int x=0; x < gamma.length;x++){
                gammaBinary = gammaBinary.concat(Integer.toString(gamma[x]));
                epsilonBinary = epsilonBinary.concat(Integer.toString(epsilon[x]));
            }

            System.out.println(Integer.parseInt(epsilonBinary,2) * Integer.parseInt(gammaBinary,2));

        }catch(Exception e){
        }
    }
}
