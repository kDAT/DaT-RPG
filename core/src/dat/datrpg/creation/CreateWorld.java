package dat.datrpg.creation;

import dat.datrpg.MainGame;
import dat.datrpg.entities.City;
import dat.datrpg.entities.Player;
import dat.datrpg.entities.World;
import dat.datrpg.saveload.WorldInfo;

import java.util.ArrayList;
import java.util.Random;

public class CreateWorld {

    public static World newWorld(WorldInfo info, Player player){

        ArrayList<City> cities = new ArrayList<City>(); // TODO Change to a more general Object
        ArrayList<Player> players = new ArrayList<Player>();
        int worldSeed = info.getWorldSeed();
        int worldRadius = info.getWorldRadius();

        World world = new World(MainGame.VERSION, info, cities, players);

        // TMP
        // TODO create more than 1 Hex
        // TODO matrix of indexes of Hexes(cities)
        City city = CreateCity.newCity(worldRadius, new Random(worldSeed));
        world.cities.add(city);
        world.players.add(player);

        return world;
    }
}
