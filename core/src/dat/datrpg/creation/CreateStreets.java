package dat.datrpg.creation;

import dat.datrpg.assets.Assets;
import dat.datrpg.entities.City;

public class CreateStreets {

    public static final int SIZE_SMALL = 1;

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
    // TODO medium and large coords

    private static int[][] coords;

    public static void newStreetLayout(City city, int size){
        if (size == SIZE_SMALL) coords = smallCoords;

        for (int i=0; i < coords.length; i++) drawStreets(city, i);
    }

    private static void drawStreets(City city, int ind){
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
                arrayX = city.mapRadius + nRow;
                arrayY = city.mapRadius + nCol - (nRow + (nRow&1))/2 - Math.max(0, -nRow);
                city.mapArray[arrayX][arrayY][0] = 1;
                city.mapArray[arrayX][arrayY][1] = Assets.ID_DIRT_1;
            }
        }

        // Empty hor
        for (int i=1; i < (colF-colI); i++){
            nRow = rowI;
            nCol = colI + i;
            arrayX = city.mapRadius + nRow;
            arrayY = city.mapRadius + nCol - (nRow + (nRow&1))/2 - Math.max(0, -nRow);
            city.mapArray[arrayX][arrayY][0] = 3;
            city.mapArray[arrayX][arrayY][1] = Assets.ID_EMPTY;
            nRow = rowF;
            arrayX = city.mapRadius + nRow;
            arrayY = city.mapRadius + nCol - (nRow + (nRow&1))/2 - Math.max(0, -nRow);
            city.mapArray[arrayX][arrayY][0] = 3;
            city.mapArray[arrayX][arrayY][1] = Assets.ID_EMPTY;
        }

        // Empty ver
        for (int i=0; i <= ((rowF-rowI)/2); i++){
            nCol = colI;
            nRow = rowI + 2*i;
            arrayX = city.mapRadius + nRow;
            arrayY = city.mapRadius + nCol - (nRow + (nRow&1))/2 - Math.max(0, -nRow);
            city.mapArray[arrayX][arrayY][0] = 3;
            city.mapArray[arrayX][arrayY][1] = Assets.ID_EMPTY;
            nCol = colF;
            arrayX = city.mapRadius + nRow;
            arrayY = city.mapRadius + nCol - (nRow + (nRow&1))/2 - Math.max(0, -nRow);
            city.mapArray[arrayX][arrayY][0] = 3;
            city.mapArray[arrayX][arrayY][1] = Assets.ID_EMPTY;
        }

    }

}
