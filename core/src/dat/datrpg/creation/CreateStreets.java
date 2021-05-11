package dat.datrpg.creation;

import dat.datrpg.assets.Assets;
import dat.datrpg.entities.Hex;

public class CreateStreets {

    public static final int SIZE_SMALL = 0;
    public static final int SIZE_MEDIUM_L = 1;
    public static final int SIZE_MEDIUM_R = 2;
    public static final int SIZE_LARGE = 3;

    private static final int[][] smallCoords = {
            {-3, -15, 16, -11},
            {-16, 11, 3, 15},
            {-6, -15, -18, 15},
            {18, -15, 6, 15},
            {-30, -33, 61, -29},
            {-61, 29, 30, 33},
            {-14, -29, -40, 29},
            {40, -29, 14, 29},
            {-38, -3, -12, 3},
            {12, -3, 38, 3},
            {29, -63, 8, -11},
            {-8, 11, -29, 63}
    };
    private static final int[][] mediumCoordsL = {
            {-32, -15, 16, -11},
            {-45, 11, 3, 15},
//            {-6, -15, -18, 15},
            {18, -15, 6, 15},
            {-40, -35, 72, -29},
            {-72, 29, 40, 35},
            {-25, -29, -51, 29},
            {51, -29, 25, 29},
            {-49, -3, -38, 3},
            {12, -3, 49, 3},
            {35, -75, 8, -11},
            {-8, 11, -35, 75},
            {1, -59, -8, -35},
            {8, 35, -1, 59},
            {55, -59, 46, -35},
            {-46, 35, -55, 59}
    };
    private static final int[][] mediumCoordsR = {
            {-3, -15, 45, -11},
            {-16, 11, 32, 15},
            {-6, -15, -18, 15},
//            {18, -15, 6, 15},
            {-40, -35, 72, -29},
            {-72, 29, 40, 35},
            {-25, -29, -51, 29},
            {51, -29, 25, 29},
            {-49, -3, -12, 3},
            {38, -3, 49, 3},
            {35, -75, 8, -11},
            {-8, 11, -35, 75},
            {1, -59, -8, -35},
            {8, 35, -1, 59},
            {55, -59, 46, -35},
            {-46, 35, -55, 59}
    };
    private static final int[][] largeCoords = {
            {-14, -15, 27, -11},
            {-27, 11, 14, 15},
            {-17, -15, -29, 15},
            {29, -15, 17, 15},
            {-40, -47, 84, -41},
            {-84, 41, 40, 47},
            {-19, -41, -57, 41},
            {57, -41, 19, 41},
            {-60, -3, -23, 3},
            {23, -3, 60, 3},
            {41, -87, 8, -11},
            {-8, 11, -41, 87},
            {7, -71, -2, -47},
            {2, 47, -7, 71},
            {61, -71, 52, -47},
            {-52, 47, -61, 71}
    };

    private static int[][] coords;

    public static void newStreetLayout(Hex hex, int size){
        if (size == SIZE_SMALL) coords = smallCoords;
        if (size == SIZE_MEDIUM_L) coords = mediumCoordsL;
        if (size == SIZE_MEDIUM_R) coords = mediumCoordsR;
        if (size == SIZE_LARGE) coords = largeCoords;

        for (int i=0; i < coords.length; i++) drawStreets(hex, i);
    }

    private static void drawStreets(Hex hex, int ind){
        int colI = coords[ind][0] + (coords[ind][1] + (coords[ind][1]&1))/2;
        int rowI = coords[ind][1];
        int colF = coords[ind][2] + (coords[ind][3] + (coords[ind][3]&1))/2;
        int rowF = coords[ind][3];
        int nCol;
        int nRow;
        int arrayX;
        int arrayY;

        for (int i=0; i <= (rowF-rowI); i++){
            for (int j=0; j < (colF-colI); j++){
                nCol = colI + j;
                nRow = rowI + i;
                arrayX = hex.mapRadius + nRow;
                arrayY = hex.mapRadius + nCol - (nRow + (nRow&1))/2 - Math.max(0, -nRow);
                hex.mapArray[arrayX][arrayY][0] = 1;
                hex.mapArray[arrayX][arrayY][1] = Assets.ID_DIRT_1;
            }
        }

        // Empty hor
        for (int i=1; i < (colF-colI); i++){
            nRow = rowI;
            nCol = colI + i;
            arrayX = hex.mapRadius + nRow;
            arrayY = hex.mapRadius + nCol - (nRow + (nRow&1))/2 - Math.max(0, -nRow);
            hex.mapArray[arrayX][arrayY][0] = 3;
            hex.mapArray[arrayX][arrayY][1] = Assets.ID_EMPTY;
            nRow = rowF;
            arrayX = hex.mapRadius + nRow;
            arrayY = hex.mapRadius + nCol - (nRow + (nRow&1))/2 - Math.max(0, -nRow);
            hex.mapArray[arrayX][arrayY][0] = 3;
            hex.mapArray[arrayX][arrayY][1] = Assets.ID_EMPTY;
        }

        // Empty ver
        for (int i=0; i <= ((rowF-rowI)/2); i++){
            nCol = colI;
            nRow = rowI + 2*i;
            arrayX = hex.mapRadius + nRow;
            arrayY = hex.mapRadius + nCol - (nRow + (nRow&1))/2 - Math.max(0, -nRow);
            hex.mapArray[arrayX][arrayY][0] = 3;
            hex.mapArray[arrayX][arrayY][1] = Assets.ID_EMPTY;
            nCol = colF;
            arrayX = hex.mapRadius + nRow;
            arrayY = hex.mapRadius + nCol - (nRow + (nRow&1))/2 - Math.max(0, -nRow);
            hex.mapArray[arrayX][arrayY][0] = 3;
            hex.mapArray[arrayX][arrayY][1] = Assets.ID_EMPTY;
        }

    }

}
