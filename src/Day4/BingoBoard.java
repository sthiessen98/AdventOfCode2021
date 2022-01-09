package Day4;

public class BingoBoard {

    private int[][] gameBoard;
    private boolean[][] marked;
    private boolean won;

    public BingoBoard(int[][] input){
        gameBoard = new int[5][5];
        marked = new boolean[5][5];
        for(int i=0; i < input.length;i++){
            for(int j=0; j <input[i].length;j++){
                gameBoard[i][j] = input[i][j];
                marked[i][j] = false;
            }
        }
        won = false;
    }

    public void checkNewNumber(int bingoNum){
        for(int i=0; i < gameBoard.length;i++){
            for(int j=0; j <gameBoard[i].length;j++){
                if(gameBoard[i][j] == bingoNum){
                    marked[i][j] = true;
                }
            }
        }
    }

    public boolean checkWin(){
        boolean isWin = false;
        //Check rows and columns
        for(int i=0; i<marked.length;i++){
            int rowCount = 0;
            int columnCount=0;
            for(int j=0;j<marked[i].length;j++){
                if(marked[i][j]){
                    rowCount++;
                }
                if(marked[j][i]){
                    columnCount++;
                }
            }
            if(rowCount==5 || columnCount==5){
                isWin=true;
            }
        }

        return isWin;
    }

    public int getUnmarkedSum(){
        int sum=0;
        for(int i=0; i<marked.length;i++){
            for(int j=0; j<marked[i].length;j++){
               if(!marked[i][j]){
                   sum += gameBoard[i][j];
               }
            }
        }
        return sum;
    }

    public boolean getWon(){
        return won;
    }

    public void setWon(boolean w){
        won = w;
    }
}
