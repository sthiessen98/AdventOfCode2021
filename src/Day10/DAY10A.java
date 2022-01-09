package Day10;

import java.io.File;
import java.util.Scanner;
import java.util.Stack;

public class DAY10A {
    public static void main(String[] args){

        File file = new File("src/Inputs/day10.txt");

        try{
            int result = 0;
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

                if(!keepGoing){
                    x--;
                    if(String.valueOf(symbols[x]).equals(")")){
                        result += 3;
                    }else if(String.valueOf(symbols[x]).equals("]")){
                        result += 57;
                    }else if(String.valueOf(symbols[x]).equals("}")){
                        result += 1197;
                    }else if(String.valueOf(symbols[x]).equals(">")){
                        result += 25137;
                    }
                }
            }

            System.out.println(result);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
