package Day16;

import java.io.File;
import java.text.StringCharacterIterator;
import java.util.Scanner;
import java.util.Stack;

public class DAY16A {
    public static void main(String[] args) {

        final String zero = "0";
        File file = new File("src/Inputs/test16.txt");

        try {


            String binaryString;

            Scanner sc = new Scanner(file);

            //Only 1 line
            binaryString = sc.nextLine();
            StringCharacterIterator iterator = new StringCharacterIterator(binaryString);
            //Hex to Binary
            String binaryInput = "";
            for (int i = 0; i < binaryString.length(); i++) {
                String hex = String.valueOf(binaryString.charAt(i));
                binaryInput = binaryInput + hexToBin(hex);
            }

            System.out.println(binaryInput);

            iterator = new StringCharacterIterator(binaryInput);
            int total = 0;
            Stack<Packet> stack = new Stack<>();

            while (iterator.getIndex() <= iterator.getEndIndex()) {

                total += readPacket(iterator, stack);
            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String hexToBin(String hex) {

        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        return hex;
    }

    public static int binToInt(String bin) {
        return Integer.parseInt(bin, 2);
    }

    public static int readPacket(StringCharacterIterator iterator, Stack stack) {


        //Get packet startPos
        int start = iterator.getIndex();

        //Get packet version number
        String versionString = "";
        for (int x = 0; x < 3; x++) {

            if (iterator.getIndex() == 0 && x==0) {
                versionString += String.valueOf(iterator.current());
            } else {
                versionString += String.valueOf(iterator.next());
            }
        }
        int version = binToInt(versionString);
        System.out.println("version = " + version);

        //Get packet ID
        String idString = "";
        for (int x = 0; x < 3; x++) {
            idString += String.valueOf(iterator.next());
        }
        int id = binToInt(idString);

        System.out.println("id = " + id);

        //If ID = 4, it's a literal packet and doesnt need to be put on stack, otherwise need to find length of packet
        if(id == 4){

            //5-bit value
            while(String.valueOf(iterator.next()).equals("1")){
                iterator.previous();
                String literalValue = "";
                for(int x=0;x<5;x++){
                    literalValue += iterator.next();
                }
                System.out.println("literal value bin = " + literalValue);
            }

            //Last 5 bit value in literal packet
            iterator.previous();
            String literalValue = "";
            for(int x=0;x<5;x++){
                literalValue += iterator.next();
            }
            System.out.println("literal value bin = " + literalValue);

            int trailingZeros = (iterator.getIndex() + 1 - start)%4;
            for(int x=0;x < 4 - trailingZeros; x++){
                iterator.next();
            }

            //End of literal packet
            return version;
        }else{

            //Operator packet
            //length type ID
            //length type = 0
            if(String.valueOf(iterator.next()).equals("0")) {
                String lengthBin = "";
                for(int x=0;x<15;x++){
                    lengthBin += String.valueOf(iterator.next());
                }
                int length = binToInt(lengthBin);
                Packet packet = new Packet(version, id, start, length);
                stack.push(packet);
            }else{
                //length type = 1
                return 0;
            }

        }
        return 0;
    }
}

