package Day18;

public class Node {

    private int value;
    private int depth;

    public Node(int value, int depth){
        this.value = value;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Node copy(){
        int v = this.value;
        int d = this.depth;

        return new Node(v, d);
    }
}
