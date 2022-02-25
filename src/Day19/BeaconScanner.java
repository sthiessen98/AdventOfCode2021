package Day19;

import java.util.LinkedList;

public class BeaconScanner {
    private final int id;
    private LinkedList<Coordinates> beacons;
    private Coordinates rotation;

    public BeaconScanner(int id){
        this.id = id;
        beacons = new LinkedList<>();
        rotation = new Coordinates(1,2,3);
    }

    public int getId() {
        return id;
    }

    public LinkedList<Coordinates> getBeacons() {
        return beacons;
    }

    public void setRotation(Coordinates c){
        this.rotation = c;
    }

    // Apply the rotation matrix to a set of coordinates
    public Coordinates rotateCoordinates(Coordinates c){
        int newX = 0;
        int newY = 0;
        int newZ = 0;

        //New X coordinate
        if(this.rotation.getX() == 1 || this.rotation.getX() == -1){
            newX = c.getX() * this.rotation.getX();
        }else if(this.rotation.getY() == 1 || this.rotation.getY() == -1){
            newX = c.getY() * this.rotation.getX();
        }else if(this.rotation.getZ() == 1 || this.rotation.getZ() == -1){
            newX = c.getZ() * this.rotation.getX();
        }else{
            System.err.println("ERROR: Invalid rotation matrix for X-Coordinate");
        }

        //New Y coordinate
        if(this.rotation.getX() == 2 || this.rotation.getX() == -2){
            newY = c.getX() * (this.rotation.getY()/2);
        }else if(this.rotation.getY() == 2 || this.rotation.getY() == -2){
            newY = c.getY() * (this.rotation.getY()/2);
        }else if(this.rotation.getZ() == 2 || this.rotation.getZ() == -2){
            newY = c.getZ() * (this.rotation.getY()/2);
        }else{
            System.err.println("ERROR: Invalid rotation matrix for Y-Coordinate");
        }

        //New Z coordinate
        if(this.rotation.getX() == 3 || this.rotation.getX() == -3){
            newZ = c.getX() * this.rotation.getZ();
        }else if(this.rotation.getY() == 3 || this.rotation.getY() == -3){
            newZ = c.getY() * this.rotation.getZ();
        }else if(this.rotation.getZ() == 3 || this.rotation.getZ() == -3){
            newZ = c.getZ() * this.rotation.getZ();
        }else{
            System.err.println("ERROR: Invalid rotation matrix for Z-Coordinate");
        }

        return new Coordinates(newX, newY, newZ);
    }
}
