package dat.datrpg.creation;

import dat.datrpg.MainGame;
import dat.datrpg.entities.Hex;
import dat.datrpg.entities.Player;
import dat.datrpg.entities.World;
import dat.datrpg.saveload.WorldInfo;

import java.util.ArrayList;
import java.util.Random;

public class CreateWorld {

    public static World newWorld(WorldInfo info, Player player){

        ArrayList<Hex> hexes = new ArrayList<Hex>();
        ArrayList<Player> players = new ArrayList<Player>();
        int worldSeed = info.getWorldSeed();
        int worldRadius = info.getWorldRadius();

        World world = new World(MainGame.VERSION, info, hexes, players);

        // TMP
        // TODO create more than 1 Hex
        // TODO matrix of indexes of Hexes(cities)
        Hex hex = CreateHex.newHex(worldRadius, new Random(worldSeed));
        world.hexes.add(hex);
        world.players.add(player);

        return world;
    }
}
