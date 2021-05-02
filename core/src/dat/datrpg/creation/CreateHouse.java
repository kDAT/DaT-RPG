package dat.datrpg.creation;

import dat.datrpg.assets.Assets;
import dat.datrpg.entities.Hex;
import dat.datrpg.entities.House;

import java.util.Random;

public class CreateHouse {

    // Constants
    private static final short MIN_HEX_HEIGHT = 3;
    private static final short MAX_HEX_HEIGHT = 5;
    private static final short MIN_HEX_WIDTH = 6;
    private static final short MAX_HEX_WIDTH = 9;


    public static House newHouse(Hex hex, Random gameRandom, int hexBaseQ, int hexBaseR, short direction){
        int hexHeight;
        int hexWidth;
        int hexInQ;
        int hexInR;
        int hexOutQ;
        int hexOutR;

        // To build each house, it needs the top left hex and the direction (0, 1, 2, 3)(right, top, left, bottom)

        // TODO Set 3 different sizes
        hexHeight = gameRandom.nextInt(MAX_HEX_HEIGHT - MIN_HEX_HEIGHT + 1) + MIN_HEX_HEIGHT;
        hexWidth = gameRandom.nextInt(MAX_HEX_WIDTH - MIN_HEX_WIDTH + 1) + MIN_HEX_WIDTH;

//        System.out.println("Height: " + hexHeight + "\nWidth:  " + hexWidth);

        int heightOffset;
        int widthOffset;
        int qBase; // Base build
        int rBase;
        int qDoor;
        int rDoor;
        int arrayX;
        int arrayY;

        if (direction == 0){
            // Right
            heightOffset = gameRandom.nextInt(MAX_HEX_HEIGHT - hexHeight + 1);
            qBase = hexBaseQ - heightOffset;
            rBase = hexBaseR + 2 + 2*heightOffset;
            buildHouse(hex, hexHeight, hexWidth, qBase, rBase);
            // Door
            heightOffset = gameRandom.nextInt(hexHeight - 2); // For the door
            qDoor = qBase - heightOffset - 1;
            rDoor = rBase + 2*heightOffset + 2;
            buildEntrance(hex, qDoor - 1, rDoor - 1);
            arrayX = hex.mapRadius + rDoor;
            arrayY = hex.mapRadius + qDoor - Math.max(0, -rDoor);
            hex.mapArray[arrayX][arrayY][0] = 2;
            hex.mapArray[arrayX][arrayY][1] = Assets.ID_DOOR_VERTICAL; // Door
            hexInQ = qDoor - 1;
            hexInR = rDoor;
            hexOutQ = qDoor + 1;
            hexOutR = rDoor;
        } else if (direction == 1){
            // Top
            widthOffset = gameRandom.nextInt(MAX_HEX_WIDTH - hexWidth + 1);
            qBase = hexBaseQ + widthOffset - (MAX_HEX_HEIGHT - hexHeight);
            rBase = hexBaseR + 2*(MAX_HEX_HEIGHT - hexHeight) + 2;
            buildHouse(hex, hexHeight, hexWidth, qBase, rBase);
            // Door
            widthOffset = gameRandom.nextInt(hexWidth - 3); // For the door
            qDoor = qBase - hexHeight + 2 + widthOffset;
            rDoor = rBase + 2*hexHeight - 1;
            arrayX = hex.mapRadius + rDoor;
            arrayY = hex.mapRadius + qDoor - Math.max(0, -rDoor);
            hex.mapArray[arrayX][arrayY][0] = 2;
            hex.mapArray[arrayX][arrayY][1] = Assets.ID_DOOR_HORIZONTAL; // Door
            // Entrance
            for (int i = 0; i < 3; i++){
                arrayX = hex.mapRadius + rDoor + 1;
                arrayY = hex.mapRadius + qDoor - 1 + i - Math.max(0, -(rDoor + 1));
                hex.mapArray[arrayX][arrayY][0] = 1;
                hex.mapArray[arrayX][arrayY][1] = Assets.ID_DIRT_1; // Brown
            }
            hexInQ = qDoor;
            hexInR = rDoor + 1;
            hexOutQ = qDoor + 1;
            hexOutR = rDoor - 1;
            qDoor++;
            arrayX = hex.mapRadius + rDoor;
            arrayY = hex.mapRadius + qDoor - Math.max(0, -rDoor);
            hex.mapArray[arrayX][arrayY][0] = 2;
            hex.mapArray[arrayX][arrayY][1] = Assets.ID_DOOR_HORIZONTAL; // Door
        } else if (direction == 2){
            // Left
            heightOffset = gameRandom.nextInt(MAX_HEX_HEIGHT - hexHeight + 1);
            qBase = hexBaseQ - heightOffset + (MAX_HEX_WIDTH - hexWidth); // ############  - heightOffset
            rBase = hexBaseR + 2*heightOffset + 2;
            buildHouse(hex, hexHeight, hexWidth, qBase, rBase);
            // Door
            heightOffset = gameRandom.nextInt(hexHeight - 2); // For the door
            qDoor = qBase + hexWidth - heightOffset - 1;
            rDoor = rBase + 2*heightOffset + 2;
            buildEntrance(hex, qDoor + 1, rDoor - 1);
            arrayX = hex.mapRadius + rDoor;
            arrayY = hex.mapRadius + qDoor - Math.max(0, -rDoor);
            hex.mapArray[arrayX][arrayY][0] = 2;
            hex.mapArray[arrayX][arrayY][1] = Assets.ID_DOOR_VERTICAL; // Door
            hexInQ = qDoor + 1;
            hexInR = rDoor;
            hexOutQ = qDoor - 1;
            hexOutR = rDoor;
        } else if (direction == 3){
            // Bottom
            widthOffset = gameRandom.nextInt(MAX_HEX_WIDTH - hexWidth + 1);
            qBase = hexBaseQ + widthOffset;
            rBase = hexBaseR + 2;
            buildHouse(hex, hexHeight, hexWidth, qBase, rBase);
            // Door
            widthOffset = gameRandom.nextInt(hexWidth - 3); // For the door
            qDoor = qBase + 2 + widthOffset;
            rDoor = rBase - 1;
            arrayX = hex.mapRadius + rDoor;
            arrayY = hex.mapRadius + qDoor - Math.max(0, -rDoor);
            hex.mapArray[arrayX][arrayY][0] = 2;
            hex.mapArray[arrayX][arrayY][1] = Assets.ID_DOOR_HORIZONTAL; // Door
            // Entrance
            for (int i = 0; i < 3; i++){
                arrayX = hex.mapRadius + rDoor - 1;
                arrayY = hex.mapRadius + qDoor + i - Math.max(0, -(rDoor - 1));
                hex.mapArray[arrayX][arrayY][0] = 1;
                hex.mapArray[arrayX][arrayY][1] = Assets.ID_DIRT_1; // Brown
            }
            hexInQ = qDoor + 1;
            hexInR = rDoor - 1;
            hexOutQ = qDoor;
            hexOutR = rDoor + 1;
            qDoor++;
            arrayX = hex.mapRadius + rDoor;
            arrayY = hex.mapRadius + qDoor - Math.max(0, -rDoor);
            hex.mapArray[arrayX][arrayY][0] = 2;
            hex.mapArray[arrayX][arrayY][1] = Assets.ID_DOOR_HORIZONTAL; // Door
        }
        else {
            return null;
        }
        //

        return new House(hexHeight, hexWidth, hexInQ, hexInR, hexOutQ, hexOutR);
    }

    private static void buildHouse(Hex hex, int hexHeight, int hexWidth, int qBase, int rBase){
        // Builds the house
        System.out.println("qBase: " + qBase + "\nrBase: " + rBase);
        int col = qBase + (rBase + (rBase&1))/2;  // Converts from Axial to Offset
        int row = rBase;
        int nCol;
        int nRow;
        int arrayX;
        int arrayY;

        // Setting the floor
        for (int i = 0; i < (2*hexHeight-1); i++){
            for (int j = 0; j < hexWidth; j++){
                nCol = col + j;
                nRow = row + i;
                arrayX = hex.mapRadius + nRow;
                arrayY = hex.mapRadius + nCol - (nRow - (nRow&1))/2 - Math.max(0, -nRow);
                hex.mapArray[arrayX][arrayY][0] = 1;
                hex.mapArray[arrayX][arrayY][1] = Assets.ID_DIRT_1; // Brown**************
            }
        }

        // Setting the walls
        // Horizontal
        for (int i = 0; i < hexWidth; i++){
            // Up Wall
            nCol = col + i;
            nRow = row - 1;
            arrayX = hex.mapRadius + nRow;
            arrayY = hex.mapRadius + nCol - (nRow - (nRow&1))/2 - Math.max(0, -nRow);
            hex.mapArray[arrayX][arrayY][0] = 2;
            hex.mapArray[arrayX][arrayY][1] = Assets.ID_WALL_HORIZONTAL; // Horizontal wall

            // Down Wall
            nRow = row + 2*hexHeight - 1;
            arrayX = hex.mapRadius + nRow;
            arrayY = hex.mapRadius + nCol - (nRow - (nRow&1))/2 - Math.max(0, -nRow);
            hex.mapArray[arrayX][arrayY][0] = 2;
            hex.mapArray[arrayX][arrayY][1] = Assets.ID_WALL_HORIZONTAL;
        }
        // Vertical
        arrayX = hex.mapRadius + row;
        arrayY = hex.mapRadius + col - (row + (row&1))/2 - Math.max(0, -row);
        hex.mapArray[arrayX][arrayY][0] = 2;
        hex.mapArray[arrayX][arrayY][1] = Assets.ID_WALL_VERTICAL; // Vertical wall
        arrayY = hex.mapRadius + col + hexWidth - (row + (row&1))/2 - Math.max(0, -row);
        hex.mapArray[arrayX][arrayY][0] = 2;
        hex.mapArray[arrayX][arrayY][1] = Assets.ID_WALL_VERTICAL; // Vertical wall
        for (int i = 1; i < hexHeight; i++){
            // Left Wall
            nCol = col;
            nRow = row + 2*i;
            arrayX = hex.mapRadius + nRow;
            arrayY = hex.mapRadius + nCol - (nRow + (nRow&1))/2 - Math.max(0, -nRow);
            hex.mapArray[arrayX][arrayY][0] = 2;
            hex.mapArray[arrayX][arrayY][1] = Assets.ID_WALL_VERTICAL; // Vertical wall
            for (int j = 0; j < 2; j++){
                arrayX = hex.mapRadius + nRow - 1;
                arrayY = hex.mapRadius + nCol + j - 1 - (nRow - (nRow&1))/2 - Math.max(0, -nRow);
//                game.mapArray[arrayX][arrayY][3] = 1; // Sets as a wall, but without the texture
            }

            // Right Wall
            nCol = col + hexWidth;
            arrayX = hex.mapRadius + nRow;
            arrayY = hex.mapRadius + nCol - (nRow + (nRow&1))/2 - Math.max(0, -nRow); //#########
            hex.mapArray[arrayX][arrayY][0] = 2;
            hex.mapArray[arrayX][arrayY][1] = Assets.ID_WALL_VERTICAL; // Vertical wall
            for (int j = 0; j < 2; j++){
                arrayX = hex.mapRadius + nRow - 1;
                arrayY = hex.mapRadius + nCol + j - 1 - (nRow - (nRow&1))/2 - Math.max(0, -nRow);
//                game.mapArray[arrayX][arrayY][3] = 1; // Sets as a wall, but without the texture
            }
        }
    }

    private static void buildEntrance(Hex hex, int qBase, int rBase){
        System.out.println("qBaseE: " + qBase + "\nrBaseE: " + rBase);
        // Builds the entrance of the house
        int col = qBase + (rBase - (rBase&1))/2;  // Converts from Axial to Offset
        int row = rBase;
        int nCol;
        int nRow;
        int arrayX;
        int arrayY;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                nCol = col + j;
                nRow = row + i;
                arrayX = hex.mapRadius + nRow;
                arrayY = hex.mapRadius + nCol - (nRow - (nRow&1))/2 - Math.max(0, -nRow);
                hex.mapArray[arrayX][arrayY][0] = 1;
                hex.mapArray[arrayX][arrayY][1] = Assets.ID_DIRT_1; // Brown
            }
        }
    }
}
