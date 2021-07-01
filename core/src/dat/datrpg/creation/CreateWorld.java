package dat.datrpg.creation;

import dat.datrpg.MainGame;
import dat.datrpg.assets.Assets;
import dat.datrpg.entities.Hex;
import dat.datrpg.entities.Player;
import dat.datrpg.entities.World;
import dat.datrpg.saveload.WorldInfo;
import dat.datrpg.utils.HexTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CreateWorld {

    public static World newWorld(WorldInfo info, Player player){

        ArrayList<Hex> hexes = new ArrayList<Hex>();
        ArrayList<Player> players = new ArrayList<Player>();
        int worldSeed = info.getWorldSeed();
        int worldRadius = info.getWorldRadius();

        short[][] worldArray = createWorldArray(worldRadius);
        World world = new World(MainGame.VERSION, info, hexes,
                worldArray, players);

        // TMP
        Random worldCreationRandom = new Random(worldSeed);
        Hex hex = CreateHex.newHex(worldCreationRandom, CreateHex.HEX_CITY_SMALL);
        world.hexes.add(hex);
        int q = 0;
        int r = 0;
        short index = 0;
        setWorldArrayIndex(world, q, r, index);

        for (int k = 1; k <= worldRadius; k++){
            q = -k;
            r = k;
            for (int i = 0; i < 6; i++){
                for (int j = 0; j < k; j++) {
                    int[] newqr = HexTools.addDirection(q, r, i);
                    q = newqr[0];
                    r = newqr[1];
                    // TODO Decide the type of Hex
                    world.hexes.add(CreateHex.newHex(worldCreationRandom, CreateHex.HEX_CITY_SMALL));
                    index++;
                    setWorldArrayIndex(world, q, r, index);
                }
            }
        }

        world.players.add(player);

        return world;
    }

    private static short[][] createWorldArray(int worldRadius) {
        short[][] worldArray = new short[2 * worldRadius + 1][];
        for (int i = 0; i < worldArray.length; i++) {
            int rowSize = 2 * worldRadius + 1 - Math.abs(worldRadius - i);
            worldArray[i] = new short[rowSize];
        }
//        for (short[] bytes : worldArray) {
//            Arrays.fill(bytes, (byte) 0);
//        }
        return worldArray;
    }

    private static void setWorldArrayIndex(World world, int q, int r, short index){
        int arrayX = world.worldRadius + r;
        int arrayY = world.worldRadius + q - Math.max(0, -r);
        world.worldArray[arrayX][arrayY] = index;
    }
}
