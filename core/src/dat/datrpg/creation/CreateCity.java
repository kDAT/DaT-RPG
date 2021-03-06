package dat.datrpg.creation;

import dat.datrpg.assets.Assets;
import dat.datrpg.entities.City;
import dat.datrpg.entities.House;

import java.util.ArrayList;
import java.util.Random;

public class CreateCity {

    public static final int NUM_PROPERTIES = 2;

//    public int mapRadius;
//    public int[][][] mapArray;

    public static City newCity(int mapRadius, Random random){

        byte[][][] mapArray = new byte[2 * mapRadius + 1][][];
        // TODO Make it byte instead of int
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
        City city = new City(mapRadius, mapArray, houses);

        // TODO draws the city

        CreateStreets.newStreetLayout(city, CreateStreets.SIZE_SMALL);

//        city.houses.add(CreateHouse.newHouse(city, random, 0, 0, (short) 0));

        return city;
    }
}
