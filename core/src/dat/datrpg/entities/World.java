package dat.datrpg.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class World implements Serializable {

    private String worldVersion;
    private String worldName;
    private String worldSeed;
    private String worldRadius;
    public ArrayList<City> cities; // TODO Change to a more general Object
    public ArrayList<Player> players;

    public World(String worldVersion, String worldName, String worldSeed, String worldRadius, ArrayList<City> cities, ArrayList<Player> players) {
        this.worldVersion = worldVersion;
        this.worldName = worldName;
        this.worldSeed = worldSeed;
        this.worldRadius = worldRadius;
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
