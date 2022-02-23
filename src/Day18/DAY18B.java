package Day18;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DAY18B {
    public static void main(String[] args){

        File file = new File("src/Inputs/day18.txt");

        try{

            Scanner sc =new Scanner(file);

            ArrayList<LinkedList<Node>> list1 = new ArrayList<>();
            ArrayList<LinkedList<Node>> list2 = new ArrayList<>();

            while(sc.hasNextLine()){
                String line = sc.nextLine();
                LinkedList<Node> snailNum = createSnailNumber(line);
                list1.add(snailNum);
                list2.add(snailNum);



            }


            System.out.println(calculateMagnitude(currNum));

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static LinkedList<Node> createSnailNumber(String s){
        LinkedList<Node> snailNumber = new LinkedList<>();
        int height = 0;
        char leftBracket = '[';
        char rightBracket = ']';
        char comma = ',';


        for(int i=0; i < s.length();i++){
            char c = s.charAt(i);

            if(c == leftBracket){
                height++;
            }
            else if(c == rightBracket){
                height--;
            }
            else if(c == comma){
                //do nothing on comma
            }else{
                int value = Integer.parseInt(String.valueOf(c));
                Node node = new Node(value, height);
                snailNumber.add(node);
            }
        }

        return snailNumber;
    }

    //Adds the second linkedlist to the first and increases depth of all nodes by 1
    public static LinkedList<Node> addSnailNumbers(LinkedList<Node> list1, LinkedList<Node> list2){
        list1.addAll(list2);
        for(int x=0; x<list1.size();x++){
            list1.get(x).setDepth(list1.get(x).getDepth()+1);
        }
        return list1;
    }

    public static LinkedList<Node> reduceSnailNumber(LinkedList<Node> list){
        boolean allDone = false;
        while(!allDone) {
            int x = 0;
            boolean cont = true;
            while (x < list.size() && cont) {

                Node currNode = list.get(x);

                if (currNode.getDepth() >= 5) {
                    //explode
                    if (x != 0) {
                        list.get(x - 1).setValue(currNode.getValue() + list.get(x - 1).getValue());
                    }
                    if (x + 2 < list.size()) {
                        list.get(x + 2).setValue(list.get(x + 1).getValue() + list.get(x + 2).getValue());
                    }
                    currNode.setValue(0);
                    currNode.setDepth(currNode.getDepth() - 1);
                    list.remove(x + 1);
                    cont = false;

                }
                x++;
            }

            x = 0;
            while(x< list.size() && cont){

                Node currNode = list.get(x);

                if (currNode.getValue() >= 10) {
                    //Split
                    int preSplitValue = currNode.getValue();
                    currNode.setValue(preSplitValue / 2);
                    currNode.setDepth(currNode.getDepth() + 1);

                    int postSplitValue = 0;
                    if ((preSplitValue % 2) == 0) {
                        postSplitValue = preSplitValue / 2;
                    } else {
                        postSplitValue = (preSplitValue / 2) + 1;
                    }
                    Node node = new Node(postSplitValue, currNode.getDepth());
                    list.add(x+1, node);
                    cont = false;
                }
                x++;
            }

            if(cont){
                allDone = true;
            }
        }
        return list;
    }

    public static int calculateMagnitude(LinkedList<Node> nodes){
        while(nodes.size() != 2){
            for(int x=0; x < nodes.size()-1;x++){
                Node thisNode = nodes.get(x);
                Node nextNode = nodes.get(x+1);
                if(thisNode.getDepth() == nextNode.getDepth()){
                    thisNode.setValue(thisNode.getValue() * 3 + nextNode.getValue() * 2);
                    thisNode.setDepth(thisNode.getDepth() - 1);
                    nodes.remove(x+1);
                }
            }
        }


        return nodes.get(0).getValue() * 3 + nodes.get(1).getValue() * 2;
    }
}
