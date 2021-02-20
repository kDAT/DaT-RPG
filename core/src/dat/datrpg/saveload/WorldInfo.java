package dat.datrpg.saveload;

import java.io.Serializable;

public class WorldInfo implements Serializable {

    private String worldName;
    private int worldSeed;
    private int worldRadius;

    public WorldInfo(String worldName, String worldSeed, String worldRadius) {
        this.worldName = worldName;
        this.worldSeed = Integer.parseInt(worldSeed);
        this.worldRadius = Integer.parseInt(worldRadius);
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public int getWorldSeed() {
        return worldSeed;
    }

    public void setWorldSeed(int worldSeed) {
        this.worldSeed = worldSeed;
    }

    public int getWorldRadius() {
        return worldRadius;
    }

    public void setWorldRadius(int worldRadius) {
        this.worldRadius = worldRadius;
    }
}
