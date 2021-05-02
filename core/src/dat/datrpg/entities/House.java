package dat.datrpg.entities;

import java.io.Serializable;

public class House implements Serializable {

    private static final long serialVersionUID = -4333999082114371723L;
    private final int hexHeight;
    private final int hexWidth;
    private final int hexInQ;
    private final int hexInR;
    private final int hexOutQ;
    private final int hexOutR;

    public House(int hexHeight, int hexWidth, int hexInQ, int hexInR, int hexOutQ, int hexOutR) {
        this.hexHeight = hexHeight;
        this.hexWidth = hexWidth;
        this.hexInQ = hexInQ;
        this.hexInR = hexInR;
        this.hexOutQ = hexOutQ;
        this.hexOutR = hexOutR;
    }

    public int getHexHeight() {
        return hexHeight;
    }

    public int getHexWidth() {
        return hexWidth;
    }

    public int getHexInQ() {
        return hexInQ;
    }

    public int getHexInR() {
        return hexInR;
    }

    public int getHexOutQ() {
        return hexOutQ;
    }

    public int getHexOutR() {
        return hexOutR;
    }
}
