package Day16;

public class Packet {
    private int version;
    private int id;
    private int startPos;
    private int length;

    public Packet(int v, int id, int s, int l){
        version = v;
        this.id = id;
        startPos = s;
        length = l;
    }

    public int getVersion() {
        return version;
    }

    public int getId() {
        return id;
    }

    public int getStartPos() {
        return startPos;
    }

    public int getLength(){
        return length;
    }
}
