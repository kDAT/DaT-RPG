package dat.datrpg.entities;

import dat.datrpg.saveload.WorldInfo;

import java.io.Serializable;
import java.util.ArrayList;

public class World implements Serializable {

    private static final long serialVersionUID = -8257835827393254554L;
    private String worldVersion;
    public WorldInfo worldInfo;
    private String worldName;
    private int worldSeed;
    public int worldRadius;
    public ArrayList<Hex> hexes;
    public short[][] worldArray;
    public ArrayList<Player> players;

    public World(String worldVersion, WorldInfo worldInfo,
                 ArrayList<Hex> hexes, short[][] worldArray, ArrayList<Player> players) {
        this.worldVersion = worldVersion;
        this.worldInfo = worldInfo;
        this.worldName = worldInfo.getWorldName();
        this.worldSeed = worldInfo.getWorldSeed();
        this.worldRadius = worldInfo.getWorldRadius();
        this.hexes = hexes;
        this.worldArray = worldArray;
        this.players = players;
        // cria novo
    }

//    public World(String nameOfSave){
//        // load no save
//    }

//    private void save(){
//        //
//    }

//    private void load(){
//        //
//    }

}
