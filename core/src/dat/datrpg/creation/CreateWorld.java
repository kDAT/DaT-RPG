package dat.datrpg.creation;

import dat.datrpg.MainGame;
import dat.datrpg.entities.City;
import dat.datrpg.entities.Player;
import dat.datrpg.entities.World;

import java.util.ArrayList;
import java.util.Random;

public class CreateWorld {

    public static World newWorld(String name, String seed, String radius, Player player){

        ArrayList<City> cities = new ArrayList<City>(); // TODO Change to a more general Object
        ArrayList<Player> players = new ArrayList<Player>();

        World world = new World(MainGame.VERSION, name, seed, radius, cities, players);

        // TMP
        City city = CreateCity.newCity(Integer.parseInt(radius), new Random(Integer.parseInt(seed)));
        world.cities.add(city);
        world.players.add(player);

        return world;
    }
}
