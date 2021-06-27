package dat.datrpg.creation;

import dat.datrpg.assets.Assets;
import dat.datrpg.entities.Hex;

public class CreateJumpHex {

    private static final byte[][] smallCoords = {
            {31, 31},
            {62, -31},
            {31, -62},
            {-31, -31},
            {-62, 31},
            {-31, 62}
    };
    private static final byte[][] mediumCoords = {
            {42, 32},
            {74, -32},
            {37, -74},
            {-42, -32},
            {-74, 32},
            {-37, 74}
    };
    private static final byte[][] largeCoords = {
            {42, 44},
            {86, -44},
            {43, -86},
            {-42, -44},
            {-86, 44},
            {-43, 86}
    };

    public static byte[][] newJumpHexArray(int size, int hexRadius){
        byte[][] jumpHexArray;
        int newHexRadius;
        if (size == CreateHex.HEX_CITY_SMALL) jumpHexArray = smallCoords;
        else if (size == CreateHex.HEX_CITY_MEDIUM_R) jumpHexArray = mediumCoords;
        else if (size == CreateHex.HEX_CITY_MEDIUM_L) jumpHexArray = mediumCoords;
        else if (size == CreateHex.HEX_CITY_LARGE) jumpHexArray = largeCoords;
        else {
            if (hexRadius%2==0) newHexRadius = (hexRadius - 2);
            else newHexRadius = (hexRadius - 1);
            jumpHexArray = new byte[][]{
                    {(byte) (newHexRadius/2),   (byte) (newHexRadius/2)},   // 0    r/2  r/2
                    {(byte) newHexRadius,       (byte) (-newHexRadius/2)},  // 1    r    -r/2
                    {(byte) (newHexRadius/2),   (byte) -newHexRadius},      // 2    r/2  -r
                    {(byte) (-newHexRadius/2),  (byte) (-newHexRadius/2)},  // 3    -r/2 -r/2
                    {(byte) -newHexRadius,      (byte) (newHexRadius/2)},   // 4    -r   r/2
                    {(byte) (-newHexRadius/2),  (byte) newHexRadius},       // 5    -r/2 r
            };
        }

        return jumpHexArray;
    }

    public static void drawJumpHex(Hex hex){
        for (int i=0; i<6; i++){
            int cq = hex.jumpHexArray[i][0];
            int cr = hex.jumpHexArray[i][1];
            int arrayX = hex.mapRadius + cr;
            int arrayY = hex.mapRadius + cq - Math.max(0, -cr);
            hex.mapArray[arrayX][arrayY][0] = 1;
            hex.mapArray[arrayX][arrayY][1] = Assets.ID_JUMP;
        }
    }

}
