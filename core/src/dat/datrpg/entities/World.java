package dat.datrpg.entities;

import dat.datrpg.saveload.WorldInfo;

import java.io.Serializable;
import java.util.ArrayList;

public class World implements Serializable {

    private String worldVersion;
    public WorldInfo worldInfo;
    private String worldName;
    private int worldSeed;
    private int worldRadius;
    public ArrayList<City> cities; // TODO Change to a more general Object
    public ArrayList<Player> players;

    public World(String worldVersion, WorldInfo worldInfo, ArrayList<City> cities, ArrayList<Player> players) {
        this.worldVersion = worldVersion;
        this.worldInfo = worldInfo;
        this.worldName = worldInfo.getWorldName();
        this.worldSeed = worldInfo.getWorldSeed();
        this.worldRadius = worldInfo.getWorldRadius();
        this.cities = cities;
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
