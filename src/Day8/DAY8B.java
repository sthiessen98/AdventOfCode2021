package Day8;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DAY8B {
    public static void main(String[] args){

        File file = new File("src/Inputs/day8.txt");

        try{

            int result = 0;
            Scanner sc =new Scanner(file);
            while(sc.hasNextLine()){
                String[] line = sc.nextLine().split(" \\| ");

                //Setup variables needed per line
                String[] signalPatterns = line[0].split(" ");
                String[] output = line[1].split("\\s");
                Map<Integer, String> knownNumbers = new HashMap<>();
                Map<Character, Character> knownMappings = new HashMap<>();

                //Find out which signals are 1,4,7,8
                for(int x=0; x<signalPatterns.length;x++){
                    int length = signalPatterns[x].length();
                    switch(length){
                        case 2:
                            knownNumbers.put(1, signalPatterns[x]);
                            break;
                        case 3:
                            knownNumbers.put(7, signalPatterns[x]);
                            break;
                        case 4:
                            knownNumbers.put(4, signalPatterns[x]);
                            break;
                        case 7:
                            knownNumbers.put(8, signalPatterns[x]);
                    }
                }

                //Find which signals are 0,2,3,5,6,9
                for(int x=0; x<signalPatterns.length;x++){
                    int length = signalPatterns[x].length();
                    if(length == 6 && countMissingChar(knownNumbers.get(4), signalPatterns[x]) == 0){
                        knownNumbers.put(9,signalPatterns[x]);
                    }
                    else if(length == 6 && countMissingChar(knownNumbers.get(1), signalPatterns[x]) == 0){
                        knownNumbers.put(0, signalPatterns[x]);
                    }else if(length == 6){
                        knownNumbers.put(6, signalPatterns[x]);
                    }
                    else if(length == 5 && countMissingChar(knownNumbers.get(1), signalPatterns[x]) == 0){
                        knownNumbers.put(3, signalPatterns[x]);
                    }
                    else if(length == 5 && countMissingChar(knownNumbers.get(4), signalPatterns[x]) == 2){
                        knownNumbers.put(2, signalPatterns[x]);
                    }else if(length == 5){
                        knownNumbers.put(5, signalPatterns[x]);
                    }
                }

                //Find wire a,b,c,d,e,f
                knownMappings.put(findMissingChar(knownNumbers.get(7), knownNumbers.get(1)),'a');
                knownMappings.put(findMissingChar(knownNumbers.get(4), knownNumbers.get(3)),'b');
                knownMappings.put(findMissingChar(knownNumbers.get(9), knownNumbers.get(5)),'c');
                knownMappings.put(findMissingChar(knownNumbers.get(8), knownNumbers.get(0)),'d');
                knownMappings.put(findMissingChar(knownNumbers.get(8), knownNumbers.get(9)),'e');
                knownMappings.put(findMissingChar(knownNumbers.get(1), knownNumbers.get(2)),'f');

                //Find wire g
                String possibilities = "abcdefg";
                for(int x=0;x<possibilities.length();x++){
                    if(!knownMappings.containsKey(possibilities.charAt(x))){
                        knownMappings.put(possibilities.charAt(x), 'g');
                    }
                }

                //Convert output value
                String intValue = "";
                for(int x=0; x< output.length; x++){

                    //Create new output string
                    String newOutput = output[x];
                    /*
                    for(int y=0; y < output[x].length(); y++){
                        newOutput = newOutput + knownMappings.get(output[x].charAt(y));
                    }
*/
                    //Check for match against knownNumbers
                    if(countMissingChar(knownNumbers.get(8), newOutput)==0){
                        intValue = intValue + "8";
                    }else if(countMissingChar(knownNumbers.get(0), newOutput)==0){
                        intValue = intValue + "0";
                    }else if(countMissingChar(knownNumbers.get(6), newOutput)==0){
                        intValue = intValue + "6";
                    }else if(countMissingChar(knownNumbers.get(9), newOutput)==0){
                        intValue = intValue + "9";
                    }else if(countMissingChar(knownNumbers.get(2), newOutput)==0){
                        intValue = intValue + "2";
                    }else if(countMissingChar(knownNumbers.get(3), newOutput)==0){
                        intValue = intValue + "3";
                    }else if(countMissingChar(knownNumbers.get(5), newOutput)==0){
                        intValue = intValue + "5";
                    }else if(countMissingChar(knownNumbers.get(4), newOutput)==0){
                        intValue = intValue + "4";
                    }else if(countMissingChar(knownNumbers.get(7), newOutput)==0){
                        intValue = intValue + "7";
                    }else if(countMissingChar(knownNumbers.get(1), newOutput)==0){
                        intValue = intValue + "1";
                    }
                }

                result += Integer.parseInt(intValue);
            }

            System.out.println(result);

        }catch(Exception e){
            System.out.println();
        }
    }

    /*
    Find the character in string a that doesn't appear in string b.
    Returns character for first unique character, otherwise z.
     */
    private static char findMissingChar(String a, String b){
        for(int x=0; x<a.length();x++){
            boolean exists = false;
            for(int y=0;y<b.length();y++){
                if(a.charAt(x) == b.charAt(y)){
                    exists = true;
                }
            }
            if(!exists){
                return a.charAt(x);
            }
        }
        return 'z';
    }
    /*
    Find the number of characters in string a that doesn't appear in string b
    Return that amount
     */
    private static int countMissingChar(String a, String b){
        int count = 0;
        for(int x=0; x<a.length();x++){
            boolean exists = false;
            for(int y=0; y<b.length();y++){
                if(a.charAt(x) == b.charAt(y)){
                    exists = true;
                }
            }
            if(!exists){
                count++;
            }
            exists= false;
        }
        return count;
    }
}
