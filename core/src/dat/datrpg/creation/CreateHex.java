package dat.datrpg.creation;

import dat.datrpg.assets.Assets;
import dat.datrpg.entities.Hex;
import dat.datrpg.entities.House;

import java.util.ArrayList;
import java.util.Random;

public class CreateHex {


    public static final int NUM_PROPERTIES = 2;  // TODO 0: collision, 1: Texture

    public static final int HEX_CREATIVE = 0;
    public static final int HEX_CITY_SMALL = 1;
    public static final int HEX_CITY_MEDIUM_L = 2;
    public static final int HEX_CITY_MEDIUM_R = 3;
    public static final int HEX_CITY_LARGE = 4;

//    public int mapRadius;
//    public int[][][] mapArray;

    public static Hex newHex(/*int mapRadius, */Random random, int hexType){

//        byte[][][] mapArray = new byte[2 * mapRadius + 1][][];
//        for (int i = 0; i < mapArray.length; i++) {
//            int rowSize = 2 * mapRadius + 1 - Math.abs(mapRadius - i);
//            mapArray[i] = new byte[rowSize][NUM_PROPERTIES];
//        }
//        for (int i = 0; i < mapArray.length; i++) {
//            for (int j = 0; j < mapArray[i].length; j++) {
//                mapArray[i][j][0] = 1; // Level
////                mapArray[i][j][0] = 2; // Level
//                mapArray[i][j][1] = Assets.ID_GRASS_1; // Texture
////                mapArray[i][j][1] = (byte)(random.nextInt(4)+7); // Texture
////				mapArray[i][j] = (int) (i%2);  // 0, 1 or 2
//            }
//        }

//        ArrayList<House> houses = new ArrayList<House>();
//        Hex hex = new Hex(mapRadius, hexArray, houses);

        // TODO draws the city

        int hexRadius;
        byte[][][] hexArray;
        byte[][] jumpHexArray;
        ArrayList<House> houses = new ArrayList<House>();
        Hex hex;

        switch (hexType) {
            case HEX_CREATIVE:
                hexRadius = 100;
                hexArray = createHexArray(hexRadius);
                jumpHexArray = CreateJumpHex.newJumpHexArray(HEX_CREATIVE, hexRadius);
                hex = new Hex(hexRadius, hexArray, jumpHexArray, houses);
                CreateJumpHex.drawJumpHex(hex);
                break;
            case HEX_CITY_SMALL:
                hexRadius = 63;
                hexArray = createHexArray(hexRadius);
                jumpHexArray = CreateJumpHex.newJumpHexArray(HEX_CITY_SMALL, hexRadius);
                hex = new Hex(hexRadius, hexArray, jumpHexArray, houses);
                CreateStreets.newStreetLayout(hex, CreateStreets.SIZE_SMALL);
                CreateJumpHex.drawJumpHex(hex);
                break;
            case HEX_CITY_MEDIUM_L:
                hexRadius = 75;
                hexArray = createHexArray(hexRadius);
                jumpHexArray = CreateJumpHex.newJumpHexArray(HEX_CITY_MEDIUM_L, hexRadius);
                hex = new Hex(hexRadius, hexArray, jumpHexArray, houses);
                CreateStreets.newStreetLayout(hex, CreateStreets.SIZE_MEDIUM_L);
                CreateJumpHex.drawJumpHex(hex);
                break;
            case HEX_CITY_MEDIUM_R:
                hexRadius = 75;
                hexArray = createHexArray(hexRadius);
                jumpHexArray = CreateJumpHex.newJumpHexArray(HEX_CITY_MEDIUM_R, hexRadius);
                hex = new Hex(hexRadius, hexArray, jumpHexArray, houses);
                CreateStreets.newStreetLayout(hex, CreateStreets.SIZE_MEDIUM_R);
                CreateJumpHex.drawJumpHex(hex);
                break;
            case HEX_CITY_LARGE:
                hexRadius = 87;
                hexArray = createHexArray(hexRadius);
                jumpHexArray = CreateJumpHex.newJumpHexArray(HEX_CITY_LARGE, hexRadius);
                hex = new Hex(hexRadius, hexArray, jumpHexArray, houses);
                CreateStreets.newStreetLayout(hex, CreateStreets.SIZE_LARGE);
                CreateJumpHex.drawJumpHex(hex);
                break;
            default:
                return null;
        }

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

    private static byte[][][] createHexArray(int hexRadius) {
        byte[][][] hexArray = new byte[2 * hexRadius + 1][][];
        for (int i = 0; i < hexArray.length; i++) {
            int rowSize = 2 * hexRadius + 1 - Math.abs(hexRadius - i);
            hexArray[i] = new byte[rowSize][NUM_PROPERTIES];
        }
        for (int i = 0; i < hexArray.length; i++) {
            for (int j = 0; j < hexArray[i].length; j++) {
                hexArray[i][j][0] = 0; // Collision
                hexArray[i][j][1] = Assets.ID_GRASS_1; // Texture
            }
        }
        return hexArray;
    }
}
