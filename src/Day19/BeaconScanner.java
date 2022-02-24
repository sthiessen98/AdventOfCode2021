package Day19;

import java.util.LinkedList;

public class BeaconScanner {
    private int id;
    private LinkedList<Coordinates> beacons;

    public BeaconScanner(int id){
        this.id = id;
        beacons = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public LinkedList<Coordinates> getBeacons() {
        return beacons;
    }
}
