package dat.datrpg.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class City implements Serializable {
    public int mapRadius;
    public byte[][][] mapArray;
    public ArrayList<House> houses;

    public City(int mapRadius, byte[][][] mapArray, ArrayList<House> houses) {
        this.mapRadius = mapRadius;
        this.mapArray = mapArray;
        this.houses = houses;
    }
}
