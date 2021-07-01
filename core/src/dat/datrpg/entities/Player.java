package dat.datrpg.entities;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = -2910863532051912334L;

    // TODO

    public String name;
    public String race;
    private int hexQ;
    private int hexR;
    private int centerQ;
    private int centerR;

    public Player(String name, String race) {
        this.name = name;
        this.race = race;
        this.hexQ = 0;
        this.hexR = 0;
        this.centerQ = 0;
        this.centerR = 0;
    }

    public void updateCoords(int hexQ, int hexR, int centerQ, int centerR){
        this.hexQ = hexQ;
        this.hexR = hexR;
        this.centerQ = centerQ;
        this.centerR = centerR;
    }

    public int getHexQ() {
        return hexQ;
    }

    public int getHexR() {
        return hexR;
    }

    public int getCenterQ() {
        return centerQ;
    }

    public int getCenterR() {
        return centerR;
    }
}
