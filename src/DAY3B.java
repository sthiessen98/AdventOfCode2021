import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DAY3B {

    private static final Integer ARRAYSIZE = 12;

    public static void main(String[] args) {

        File file = new File("src/Inputs/day3.txt");

        try {
            Integer[] binary0 = new Integer[ARRAYSIZE];
            Integer[] binary1 = new Integer[ARRAYSIZE];
            Integer[] co2binary0 = new Integer[ARRAYSIZE];
            Integer[] co2binary1 = new Integer[ARRAYSIZE];
            ArrayList<String> oxRating = new ArrayList<>();
            ArrayList<String> co2Rating = new ArrayList<>();
            for (int i = 0; i < binary0.length; i++) {
                binary0[i] = 0;
                binary1[i] = 0;
            }
            String zero = "0";


            //Get initial counts
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                oxRating.add(line);
                co2Rating.add(line);
                /*
                for (int x = 0; x < line.length(); x++) {
                    if (line.charAt(x) == zero.charAt(0)) {
                        binary0[x]++;
                    } else {
                        binary1[x]++;
                    }
                }
                */

            }

            for(int x=0; x <binary0.length;x++) {

                //Recount bits of remaining lines
                for(int i=0; i < binary0.length;i++){
                    binary0[i] = 0;
                    binary1[i] = 0;
                    co2binary0[i] = 0;
                    co2binary1[i] = 0;
                }

                for(int i=0; i < oxRating.size();i++){
                    if (oxRating.get(i).charAt(x) == zero.charAt(0)) {
                        binary0[x]++;
                    } else {
                        binary1[x]++;
                    }
                }

                for(int i=0; i < co2Rating.size();i++){
                    if (co2Rating.get(i).charAt(x) == zero.charAt(0)) {
                        co2binary0[x]++;
                    } else {
                        co2binary1[x]++;
                    }
                }


                System.out.println("");

                //Check Oxygen levels and filter by x bit
                for (int y = 0; y < oxRating.size(); y++) {
                    if ((binary0[x] > binary1[x]) && (oxRating.get(y).charAt(x) != zero.charAt(0))) {
                        if(oxRating.size() != 1){
                            oxRating.remove(y);
                            y--;
                        }
                    }else if((binary0[x] <= binary1[x]) && (oxRating.get(y).charAt(x) == zero.charAt(0))){
                        if(oxRating.size() != 1){
                            oxRating.remove(y);
                            y--;
                        }
                    }
                }


                //Check co2 levels and filter by x bit.
                for (int y = 0; y < co2Rating.size(); y++) {
                        if ((co2binary0[x] > co2binary1[x]) && (co2Rating.get(y).charAt(x) == zero.charAt(0))) {
                            if(co2Rating.size() != 1){
                                co2Rating.remove(y);
                                y--;
                            }
                        }else if((co2binary0[x] <= co2binary1[x]) && (co2Rating.get(y).charAt(x) != zero.charAt(0))){
                            if(co2Rating.size() != 1){
                                co2Rating.remove(y);
                                y--;
                            }
                        }
                }
            }

            System.out.println(oxRating);
            System.out.println(co2Rating);
            System.out.println(Integer.parseInt(oxRating.get(0),2) * Integer.parseInt(co2Rating.get(0),2));

        } catch (Exception e) {
        }
    }
}
