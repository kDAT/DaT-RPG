package dat.datrpg.utils;

import dat.datrpg.assets.Assets;

public class HexTools {

    private static byte[][][] createHexArray(int hexRadius, int numPropreties) {
        byte[][][] hexArray = new byte[2 * hexRadius + 1][][];
        for (int i = 0; i < hexArray.length; i++) {
            int rowSize = 2 * hexRadius + 1 - Math.abs(hexRadius - i);
            hexArray[i] = new byte[rowSize][numPropreties];
        }
        for (int i = 0; i < hexArray.length; i++) {
            for (int j = 0; j < hexArray[i].length; j++) {
                hexArray[i][j][0] = 0; // Collision
                hexArray[i][j][1] = Assets.ID_GRASS_1; // Texture
            }
        }
        return hexArray;
    }

    public static int[] addDirection(int oldq, int oldr, int direction) {
        int[] newqr = new int[]{oldq, oldr};
        switch (direction) {
            case 0:
                newqr[0]++;
                return newqr;
            case 1:
                newqr[0]++;
                newqr[1]--;
                return newqr;
            case 2:
                newqr[1]--;
                return newqr;
            case 3:
                newqr[0]--;
                return newqr;
            case 4:
                newqr[0]--;
                newqr[1]++;
                return newqr;
            case 5:
                newqr[1]++;
                return newqr;
            default:
                return newqr;
        }
    }

    public static byte getDirection(int q, int r) {
        // q and r must be between -1 and 1, --> -1 <= q,r <= 1
        int[][] directions = {
                {1, 0},
                {1, -1},
                {0, -1},
                {-1, 0},
                {-1, 1},
                {0, 1}
        };
        for (byte i = 0; i < 6; i++) {
            if (q == directions[i][0] && r == directions[i][1])
                return i;
        }
        return -1;
    }

    public static int[] roundHex(float fx, float fz) {
        float fy = -fx - fz;

        int ix = Math.round(fx);
        int iz = Math.round(fz);
        int iy = Math.round(fy);

        float xDiff = Math.abs(ix - fx);
        float zDiff = Math.abs(iz - fz);
        float yDiff = Math.abs(iy - fy);

        if (xDiff > yDiff && xDiff > zDiff) {
            ix = -iy - iz;
        } else if (yDiff > zDiff) {
            iy = -ix - iz;
        } else {
            iz = -ix - iy;
        }
        return new int[]{ix, iz};
    }

}
