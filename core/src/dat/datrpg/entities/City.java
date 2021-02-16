package dat.datrpg.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class City implements Serializable {
    public int mapRadius;
    public int[][][] mapArray;
    public ArrayList<House> houses;

    public City(int mapRadius, int[][][] mapArray, ArrayList<House> houses) {
        this.mapRadius = mapRadius;
        this.mapArray = mapArray;
        this.houses = houses;
    }
}
