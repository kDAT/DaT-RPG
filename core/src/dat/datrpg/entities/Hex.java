package dat.datrpg.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Hex implements Serializable {
    private static final long serialVersionUID = 7359401307939456022L;
    public int mapRadius;
    public byte[][][] mapArray;
    public ArrayList<House> houses;

    public Hex(int mapRadius, byte[][][] mapArray, ArrayList<House> houses) {
        this.mapRadius = mapRadius;
        this.mapArray = mapArray;
        this.houses = houses;
    }
}
