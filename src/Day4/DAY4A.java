package Day4;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DAY4A {

    public static void main(String[] args){

        File file = new File("src/Inputs/day4.txt");
        try{
            Scanner sc =new Scanner(file);
            String line = null;

            //Get bingo input numbers
            line = sc.nextLine();
            String[] bingoNumberString = line.split(",");

            int[] bingoNumbers = new int[bingoNumberString.length];
            for(int x=0;x<bingoNumberString.length;x++){
                bingoNumbers[x] = Integer.parseInt(bingoNumberString[x]);
            }

            line = sc.nextLine();


            //create bingo boards
            ArrayList<BingoBoard> boards = new ArrayList<>();
            int[][] boardNumbers = new int[5][5];
            int row = 0;
            while(sc.hasNextLine()){
                line = sc.nextLine();
                if(!line.isEmpty()){
                    line = line.trim();
                    String[] strArray = line.split("\\s+");
                    for(int x=0; x<strArray.length;x++){
                        boardNumbers[row][x] = Integer.parseInt(strArray[x]);
                    }
                    row++;
                }
                //Create new board if empty line
                else{
                    boards.add(new BingoBoard(boardNumbers));
                    row=0;
                }
            }
            boards.add(new BingoBoard(boardNumbers));

            //Now we play bingo
            boolean stillPlaying = true;
            int currNum = 0;
            while(stillPlaying){
                for(int x = 0; x<boards.size();x++){
                    BingoBoard board = boards.get(x);
                    board.checkNewNumber(bingoNumbers[currNum]);
                    if(board.checkWin()){
                        System.out.println(board.getUnmarkedSum() * bingoNumbers[currNum]);
                        stillPlaying = false;
                    }
                }
                if(stillPlaying){
                    currNum++;
                }
            }

        }catch(Exception e){
        }
    }
}
