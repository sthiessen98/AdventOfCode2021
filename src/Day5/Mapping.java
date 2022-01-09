package Day5;

public class Mapping {
    private int[][] map = new int[1000][1000];
    private int overlaps;

    public Mapping(){
        overlaps = 0;
    }

    public void add(int x, int y){
        map[x][y] += 1;
        if(map[x][y] == 2){
            overlaps++;
        }
    }

    public int getOverlaps(){
        return overlaps;
    }
}
