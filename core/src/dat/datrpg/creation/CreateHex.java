package dat.datrpg.creation;

import dat.datrpg.assets.Assets;
import dat.datrpg.entities.Hex;
import dat.datrpg.entities.House;

import java.util.ArrayList;
import java.util.Random;

public class CreateHex {


    public static final int NUM_PROPERTIES = 2;  // TODO 0: collision, 1: Texture

//    public int mapRadius;
//    public int[][][] mapArray;

    public static Hex newHex(int mapRadius, Random random){

        byte[][][] mapArray = new byte[2 * mapRadius + 1][][];
        for (int i = 0; i < mapArray.length; i++) {
            int rowSize = 2 * mapRadius + 1 - Math.abs(mapRadius - i);
            mapArray[i] = new byte[rowSize][NUM_PROPERTIES];
        }
        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {
                mapArray[i][j][0] = 1; // Level
//                mapArray[i][j][0] = 2; // Level
                mapArray[i][j][1] = Assets.ID_GRASS_1; // Texture
//                mapArray[i][j][1] = (byte)(random.nextInt(4)+7); // Texture
//				mapArray[i][j] = (int) (i%2);  // 0, 1 or 2
            }
        }

        ArrayList<House> houses = new ArrayList<House>();
        Hex hex = new Hex(mapRadius, mapArray, houses);

        // TODO draws the city

//        CreateStreets.newStreetLayout(hex, CreateStreets.SIZE_SMALL);
//        CreateStreets.newStreetLayout(hex, CreateStreets.SIZE_MEDIUM_R);
//        CreateStreets.newStreetLayout(hex, CreateStreets.SIZE_LARGE);
//
//        hex.houses.add(CreateHouse.newHouse(hex, random, 23, 4,
//                CreateHouse.HOUSE_DIRECTION_BOTTOM, CreateHouse.HOUSE_SIZE_MEDIUM));
//        hex.houses.add(CreateHouse.newHouse(hex, random, 6, 16,
//                CreateHouse.HOUSE_DIRECTION_BOTTOM, CreateHouse.HOUSE_SIZE_LARGE));
//        hex.houses.add(CreateHouse.newHouse(hex, random, 11, 28,
//                CreateHouse.HOUSE_DIRECTION_LEFT, CreateHouse.HOUSE_SIZE_MEDIUM));
//        hex.houses.add(CreateHouse.newHouse(hex, random, 0, 28,
//                CreateHouse.HOUSE_DIRECTION_TOP, CreateHouse.HOUSE_SIZE_MEDIUM));
//        hex.houses.add(CreateHouse.newHouse(hex, random, -11, 28,
//                CreateHouse.HOUSE_DIRECTION_TOP, CreateHouse.HOUSE_SIZE_SMALL));
//        hex.houses.add(CreateHouse.newHouse(hex, random, -5, 16,
//                CreateHouse.HOUSE_DIRECTION_RIGHT, CreateHouse.HOUSE_SIZE_MEDIUM));

        return hex;
    }
}
