package dat.datrpg.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class World implements Serializable {

    private String worldVersion;
    private String worldName;
    private String worldSeed;
    private String worldRadius;
//    private ArrayList<bHex> hexes;

    public World(String worldVersion, String worldName, String worldSeed, String worldRadius) {
        this.worldVersion = worldVersion;
        this.worldName = worldName;
        this.worldSeed = worldSeed;
        this.worldRadius = worldRadius;
        // cria novo
    }

//    public World(String nameOfSave){
//        // load no save
//    }

    private void save(){
        // TODO
    }

//    private void load(){
//        //
//    }

}
