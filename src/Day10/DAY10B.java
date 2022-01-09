package Day10;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class DAY10B {
    public static void main(String[] args){

        File file = new File("src/Inputs/day10.txt");
        ArrayList<BigInteger> results = new ArrayList<>();

        try{
            Scanner sc =new Scanner(file);
            while(sc.hasNextLine()){
                char[] symbols = sc.nextLine().toCharArray();
                Stack<String> stack = new Stack<>();

                boolean keepGoing = true;
                int x = 0;
                while(keepGoing && x<symbols.length){
                    char currSymbol = symbols[x];
                    if(String.valueOf(currSymbol).equals("(")){
                        stack.push("(");
                    }else if(String.valueOf(currSymbol).equals("[")){
                        stack.push("[");
                    }else if(String.valueOf(currSymbol).equals("{")){
                        stack.push("{");
                    }else if(String.valueOf(currSymbol).equals("<")){
                        stack.push("<");
                    }else if(String.valueOf(currSymbol).equals(")")){
                        if(stack.peek().equals("(")){
                            stack.pop();
                        }else{
                            keepGoing=false;
                        }
                    }else if(String.valueOf(currSymbol).equals("]")){
                        if(stack.peek().equals("[")){
                            stack.pop();
                        }else{
                            keepGoing=false;
                        }
                    }else if(String.valueOf(currSymbol).equals("}")){
                        if(stack.peek().equals("{")){
                            stack.pop();
                        }else{
                            keepGoing=false;
                        }
                    }else if(String.valueOf(currSymbol).equals(">")){
                        if(stack.peek().equals("<")){
                            stack.pop();
                        }else{
                            keepGoing=false;
                        }
                    }
                    x++;
                }

                BigInteger result = BigInteger.ZERO;

                if(keepGoing && !stack.empty()){
                   while(!stack.empty()){
                       result = result.multiply(BigInteger.valueOf(5));
                       String symbol = stack.pop();
                       if(symbol.equals("(")){
                           result = result.add(BigInteger.ONE);
                       }else if(symbol.equals("[")){
                           result = result.add(BigInteger.TWO);
                       }else if(symbol.equals("{")){
                           result = result.add(BigInteger.valueOf(3));
                       }else if(symbol.equals("<")){
                           result = result.add(BigInteger.valueOf(4));
                       }else{
                           System.out.println("result fell through if chain");
                       }
                   }

                   results.add(result);

                }

            }

            Collections.sort(results);
            System.out.println(results.get((results.size()/2)));

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
