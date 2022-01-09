package Day11;

import java.io.File;
import java.util.Scanner;

public class DAY11B {
    public static void main(String[] args){

        final int SIZE = 10;

        File file = new File("src/Inputs/day11.txt");
        final int[][] board = new int[SIZE][SIZE];

        try{
            Scanner sc =new Scanner(file);
            int x=0;
            while(sc.hasNextLine()){
                char[] charArray = sc.nextLine().toCharArray();
                for(int y=0;y<charArray.length;y++){
                    board[x][y] = Integer.parseInt(String.valueOf(charArray[y]));
                }
                x++;
            }

            int currStep = 0;
            boolean proceed = true;
            while(proceed){

                //Increase each space by 1
                for(int i=0; i<SIZE; i++){
                    for(int j=0; j < SIZE; j++){
                        board[i][j]++;
                    }
                }

                //Check if any are power level 9; flash and update adjacent if so
                boolean keepGoing = true;
                boolean[][] hasFlashed = new boolean[SIZE][SIZE];

                while(keepGoing){
                    keepGoing = false;
                    for(int i=0; i<SIZE; i++){
                        for(int j=0; j < SIZE; j++){
                            if(board[i][j] > 9 && !hasFlashed[i][j]){
                                hasFlashed[i][j] = true;
                                keepGoing = true;;

                                if(i != 0 && j != 0){
                                    board[i-1][j-1]++;
                                }
                                if(i!= 0){
                                    board[i-1][j]++;
                                }
                                if(j!= 0){
                                    board[i][j-1]++;
                                }
                                if(i!=0 && j != SIZE-1){
                                    board[i-1][j+1]++;
                                }
                                if(i!= SIZE -1 && j != 0){
                                    board[i+1][j-1]++;
                                }
                                if(i!= SIZE -1 && j != SIZE -1){
                                    board[i+1][j+1]++;
                                }
                                if(i != SIZE-1){
                                    board[i+1][j]++;
                                }
                                if(j != SIZE-1){
                                    board[i][j+1]++;
                                }

                            }
                        }
                    }
                }

                boolean allSynched = true;
                for(int i=0; i<SIZE; i++){
                    for(int j=0; j < SIZE; j++) {
                        if(board[i][j] > 9){
                            board[i][j] = 0;
                        }else{
                            allSynched = false;
                        }
                    }
                }

                if(allSynched){
                    proceed = false;
                }

                currStep++;
            }

            System.out.println(currStep);

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
