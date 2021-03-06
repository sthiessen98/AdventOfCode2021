package Day16;

import java.io.File;
import java.text.StringCharacterIterator;
import java.util.Scanner;
import java.util.Stack;

//TODO: Clean up code and shorten
public class DAY16B {
    public static void main(String[] args) {

        final String zero = "0";
        File file = new File("src/Inputs/day16.txt");

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
            long total = 0;
            Stack<Packet> stack = new Stack<>();

            while (iterator.getIndex() <= iterator.getEndIndex()) {
                total += readPacket(iterator, stack);
                System.out.println("total = " + total);
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

    public static Long binToLong(String bin){
        return Long.parseLong(bin, 2);
    }

    public static long readPacket(StringCharacterIterator iterator, Stack stack) {


        //Get packet startPos
        int start = iterator.getIndex();

        //Get packet version number
        String versionString = "";
        for (int x = 0; x < 3; x++) {

            if (iterator.getIndex() == 0 && x == 0) {
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


        //Determine type of packet
        switch (id) {
            case 0:
                //Sum Operator Packet
                //length type = 0
                if (String.valueOf(iterator.next()).equals("0")) {
                    String lengthBin = "";
                    for (int x = 0; x < 15; x++) {
                        lengthBin += String.valueOf(iterator.next());
                    }
                    int length = binToInt(lengthBin);
                    System.out.println("length = " + length);
                    Packet packet = new Packet(version, id, iterator.getIndex(), length);
                    stack.push(packet);
                    long subPacketTotal = 0;
                    while (iterator.getIndex() < packet.getStartPos() + packet.getLength()) {
                        subPacketTotal += readPacket(iterator, stack);
                    }
                    stack.pop();
                    return subPacketTotal;
                } else {
                    //length type = 1

                    //Find # of subpackets stored
                    String countBin = "";
                    for (int x = 0; x < 11; x++) {
                        countBin += String.valueOf(iterator.next());
                    }
                    int count = binToInt(countBin);
                    System.out.println("# of subpackets = " + count);

                    //Read subpackets
                    Packet packet = new Packet(version, id, iterator.getIndex(), count);
                    stack.push(packet);
                    long subPacketTotal = 0;
                    for (int x = 0; x < count; x++) {
                        subPacketTotal += readPacket(iterator, stack);
                    }
                    stack.pop();

                    return subPacketTotal;
                }
            case 1:
                //Product Operator Packet
                //length type = 0
                if (String.valueOf(iterator.next()).equals("0")) {
                    String lengthBin = "";
                    for (int x = 0; x < 15; x++) {
                        lengthBin += String.valueOf(iterator.next());
                    }
                    int length = binToInt(lengthBin);
                    System.out.println("length = " + length);
                    Packet packet = new Packet(version, id, iterator.getIndex(), length);
                    stack.push(packet);
                    long subPacketTotal = 1;
                    while (iterator.getIndex() < packet.getStartPos() + packet.getLength()) {
                        subPacketTotal *= readPacket(iterator, stack);
                    }
                    stack.pop();
                    return subPacketTotal;
                } else {
                    //length type = 1

                    //Find # of subpackets stored
                    String countBin = "";
                    for (int x = 0; x < 11; x++) {
                        countBin += String.valueOf(iterator.next());
                    }
                    int count = binToInt(countBin);
                    System.out.println("# of subpackets = " + count);

                    //Read subpackets
                    Packet packet = new Packet(version, id, iterator.getIndex(), count);
                    stack.push(packet);
                    long subPacketTotal = 1;
                    for (int x = 0; x < count; x++) {
                        subPacketTotal *= readPacket(iterator, stack);
                    }
                    stack.pop();
                    return subPacketTotal;
                }
            case 2:
                //Minimum Operator Packet
                //length type = 0
                if (String.valueOf(iterator.next()).equals("0")) {
                    String lengthBin = "";
                    for (int x = 0; x < 15; x++) {
                        lengthBin += String.valueOf(iterator.next());
                    }
                    int length = binToInt(lengthBin);
                    System.out.println("length = " + length);
                    Packet packet = new Packet(version, id, iterator.getIndex(), length);
                    stack.push(packet);
                    long minPacket = Long.MAX_VALUE;
                    while (iterator.getIndex() < packet.getStartPos() + packet.getLength()) {
                        long newPacket = readPacket(iterator, stack);
                        if (newPacket < minPacket) {
                            minPacket = newPacket;
                        }
                    }
                    stack.pop();
                    return minPacket;
                } else {
                    //length type = 1

                    //Find # of subpackets stored
                    String countBin = "";
                    for (int x = 0; x < 11; x++) {
                        countBin += String.valueOf(iterator.next());
                    }
                    int count = binToInt(countBin);
                    System.out.println("# of subpackets = " + count);

                    //Read subpackets
                    Packet packet = new Packet(version, id, iterator.getIndex(), count);
                    stack.push(packet);
                    long minPacket = Long.MAX_VALUE;
                    for (int x = 0; x < count; x++) {
                        long newPacket = readPacket(iterator, stack);
                        if (newPacket < minPacket) {
                            minPacket = newPacket;
                        }
                    }
                    stack.pop();
                    return minPacket;
                }
            case 3:
                //Maximum Operator Packet
                //length type = 0
                if (String.valueOf(iterator.next()).equals("0")) {
                    String lengthBin = "";
                    for (int x = 0; x < 15; x++) {
                        lengthBin += String.valueOf(iterator.next());
                    }
                    int length = binToInt(lengthBin);
                    System.out.println("length = " + length);
                    Packet packet = new Packet(version, id, iterator.getIndex(), length);
                    stack.push(packet);
                    long maxPacket = Long.MIN_VALUE;
                    while (iterator.getIndex() < packet.getStartPos() + packet.getLength()) {
                        long newPacket = readPacket(iterator, stack);
                        if (newPacket > maxPacket) {
                            maxPacket = newPacket;
                        }
                    }
                    stack.pop();
                    return maxPacket;
                } else {
                    //length type = 1

                    //Find # of subpackets stored
                    String countBin = "";
                    for (int x = 0; x < 11; x++) {
                        countBin += String.valueOf(iterator.next());
                    }
                    int count = binToInt(countBin);
                    System.out.println("# of subpackets = " + count);

                    //Read subpackets
                    Packet packet = new Packet(version, id, iterator.getIndex(), count);
                    stack.push(packet);
                    long maxPacket = Long.MIN_VALUE;
                    for (int x = 0; x < count; x++) {
                        long newPacket = readPacket(iterator, stack);
                        if (newPacket > maxPacket) {
                            maxPacket = newPacket;
                        }
                    }
                    stack.pop();
                    return maxPacket;
                }

            case 4:
                //Literal Packet
                //5-bit value
                String literalValueBin = "";
                while (String.valueOf(iterator.next()).equals("1")) {
                    iterator.previous();

                    for (int x = 0; x < 5; x++) {
                        if (x == 0) {
                            iterator.next();
                        } else {
                            literalValueBin += iterator.next();
                        }
                    }
                }

                //Last 5 bit value in literal packet
                iterator.previous();
                for (int x = 0; x < 5; x++) {
                    if (x == 0) {
                        iterator.next();
                    } else {
                        literalValueBin += iterator.next();
                    }
                }
                Long literalValue = binToLong(literalValueBin);
                System.out.println("literal value = " + literalValue);

                //Deal with trailing zeros if a surface level packet
                if (stack.isEmpty()) {
                    int trailingZeros = (iterator.getIndex() + 1 - start) % 4;
                    for (int x = 0; x < 4 - trailingZeros; x++) {
                        iterator.next();
                    }
                }

                //End of literal packet
                System.out.println(" ");
                return literalValue;

            case 5:
                //Greater Than Operator Packet
                //length type info doesnt matter, as we can just call readPacket() twice.
                if (String.valueOf(iterator.next()).equals("0")) {
                    for (int x = 0; x < 15; x++) {
                        iterator.next();
                    }
                } else {
                    for (int x = 0; x < 11; x++) {
                        iterator.next();
                    }
                }
                //Read subpackets
                Packet packet = new Packet(version, id, iterator.getIndex(), 1);
                stack.push(packet);
                long firstPacket = readPacket(iterator, stack);
                long secondPacket = readPacket(iterator, stack);
                int value;
                if (firstPacket > secondPacket) {
                    value = 1;
                } else {
                    value = 0;
                }
                stack.pop();
                return value;


            case 6:
                //Less Than Operator Packet
                //length type info doesnt matter, as we can just call readPacket() twice.
                if (String.valueOf(iterator.next()).equals("0")) {
                    for (int x = 0; x < 15; x++) {
                        iterator.next();
                    }
                } else {
                    for (int x = 0; x < 11; x++) {
                        iterator.next();
                    }
                }
                //Read subpackets
                Packet newPacket = new Packet(version, id, iterator.getIndex(), 1);
                stack.push(newPacket);
                long packet1 = readPacket(iterator, stack);
                long packet2 = readPacket(iterator, stack);
                int answer;
                if (packet1 < packet2) {
                    answer = 1;
                } else {
                    answer = 0;
                }
                stack.pop();
                return answer;

            case 7:
                //Equal Operator Packet
                //length type info doesnt matter, as we can just call readPacket() twice.
                if (String.valueOf(iterator.next()).equals("0")) {
                    for (int x = 0; x < 15; x++) {
                        iterator.next();
                    }
                } else {
                    for (int x = 0; x < 11; x++) {
                        iterator.next();
                    }
                }
                //Read subpackets
                Packet p = new Packet(version, id, iterator.getIndex(), 1);
                stack.push(p);
                long p1 = readPacket(iterator, stack);
                long p2 = readPacket(iterator, stack);
                int result;
                if(p1 == p2){
                    result = 1;
                }else{
                    result = 0;
                }
                stack.pop();
                return result;

            default:
                System.err.println("Could not find operator packet type");
        }
        return 0;
    }
}
