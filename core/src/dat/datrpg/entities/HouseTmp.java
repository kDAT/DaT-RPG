package dat.datrpg.entities;

import dat.datrpg.MainGame;

import java.io.Serializable;
import java.util.Random;

public class HouseTmp implements Serializable {

    // Constants
    private static final short MIN_HEX_HEIGHT = 3;
    private static final short MAX_HEX_HEIGHT = 5;
    private static final short MIN_HEX_WIDTH = 6;
    private static final short MAX_HEX_WIDTH = 9;

    Random gameRandom;
    MainGame game;

    private int hexHeight;
    private int hexWidth;
    private int hexInQ;
    private int hexInR;
    private int hexOutQ;
    private int hexOutR;
    private final int hexBaseQ; // Base Area
    private final int hexBaseR;
    private final short direction; // 0 1 2 3 - right top left bottom

    public HouseTmp(MainGame game, Random gameRandom, int hexBaseQ, int hexBaseR, short direction) {
        this.game = game;
        this.gameRandom = gameRandom;
        this.hexBaseQ = hexBaseQ;
        this.hexBaseR = hexBaseR;
        this.direction = direction;
        createHouse();
    }

    private void createHouse(){
        // To build each house, it needs the top left hex and the direction (0, 1, 2, 3)(right, top, left, bottom)

        hexHeight = gameRandom.nextInt(MAX_HEX_HEIGHT - MIN_HEX_HEIGHT + 1) + MIN_HEX_HEIGHT;
        hexWidth = gameRandom.nextInt(MAX_HEX_WIDTH - MIN_HEX_WIDTH + 1) + MIN_HEX_WIDTH;

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
            heightOffset = gameRandom.nextInt(MAX_HEX_HEIGHT - hexHeight);
            qBase = hexBaseQ - heightOffset;
            rBase = hexBaseR + 2 + 2*heightOffset;
            buildHouse(qBase, rBase);
            // Door
            heightOffset = gameRandom.nextInt(hexHeight - 2); // For the door
            qDoor = qBase - heightOffset - 1;
            rDoor = rBase + 2*heightOffset + 2;
            buildEntrance(qDoor - 1, rDoor - 1);
            arrayX = game.mapRadius + rDoor;
            arrayY = game.mapRadius + qDoor - Math.max(0, -rDoor);
            game.mapArray[arrayX][arrayY][0] = 2;
            game.mapArray[arrayX][arrayY][1] = 2; // Door
            hexInQ = qDoor - 1;
            hexInR = rDoor;
            hexOutQ = qDoor + 1;
            hexOutR = rDoor;
        } else if (direction == 1){
            // Top
            widthOffset = gameRandom.nextInt(MAX_HEX_WIDTH - hexWidth);
            qBase = hexBaseQ + widthOffset - (MAX_HEX_HEIGHT - hexHeight);
            rBase = hexBaseR + 2*(MAX_HEX_HEIGHT - hexHeight) + 2;
            buildHouse(qBase, rBase);
            // Door
            widthOffset = gameRandom.nextInt(hexWidth - 4); // For the door
            qDoor = qBase - hexHeight + 3 + widthOffset;
            rDoor = rBase + 2*hexHeight - 1;
            arrayX = game.mapRadius + rDoor;
            arrayY = game.mapRadius + qDoor - Math.max(0, -rDoor);
            game.mapArray[arrayX][arrayY][0] = 2;
            game.mapArray[arrayX][arrayY][1] = 3; // Door
            // Entrance
            for (int i = 0; i < 3; i++){
                arrayX = game.mapRadius + rDoor + 1;
                arrayY = game.mapRadius + qDoor - 1 + i - Math.max(0, -(rDoor + 1));
                game.mapArray[arrayX][arrayY][0] = 1;
                game.mapArray[arrayX][arrayY][1] = 0; // Brown
            }
            hexInQ = qDoor;
            hexInR = rDoor + 1;
            hexOutQ = qDoor + 1;
            hexOutR = rDoor - 1;
            qDoor++;
            arrayX = game.mapRadius + rDoor;
            arrayY = game.mapRadius + qDoor - Math.max(0, -rDoor);
            game.mapArray[arrayX][arrayY][0] = 2;
            game.mapArray[arrayX][arrayY][1] = 3; // Door
        } else if (direction == 2){
            // Left
            heightOffset = gameRandom.nextInt(MAX_HEX_HEIGHT - hexHeight);
            qBase = hexBaseQ - heightOffset + (MAX_HEX_HEIGHT - hexHeight);
            rBase = hexBaseR + 2*heightOffset + 2;
            buildHouse(qBase, rBase);
            // Door
            heightOffset = gameRandom.nextInt(hexHeight - 2); // For the door
            qDoor = qBase + hexWidth - heightOffset - 1;
            rDoor = rBase + 2*heightOffset + 2;
            buildEntrance(qDoor + 1, rDoor - 1);
            arrayX = game.mapRadius + rDoor;
            arrayY = game.mapRadius + qDoor - Math.max(0, -rDoor);
            game.mapArray[arrayX][arrayY][0] = 2;
            game.mapArray[arrayX][arrayY][1] = 2; // Door
            hexInQ = qDoor + 1;
            hexInR = rDoor;
            hexOutQ = qDoor - 1;
            hexOutR = rDoor;
        } else if (direction == 3){
            // Bottom
            widthOffset = gameRandom.nextInt(MAX_HEX_WIDTH - hexWidth);
            qBase = hexBaseQ + widthOffset;
            rBase = hexBaseR + 2;
            buildHouse(qBase, rBase);
            // Door
            widthOffset = gameRandom.nextInt(hexWidth - 4); // For the door
            qDoor = qBase + 3 + widthOffset;
            rDoor = rBase - 1;
            arrayX = game.mapRadius + rDoor;
            arrayY = game.mapRadius + qDoor - Math.max(0, -rDoor);
            game.mapArray[arrayX][arrayY][0] = 2;
            game.mapArray[arrayX][arrayY][1] = 3; // Door
            // Entrance
            for (int i = 0; i < 3; i++){
                arrayX = game.mapRadius + rDoor - 1;
                arrayY = game.mapRadius + qDoor + i - Math.max(0, -(rDoor - 1));
                game.mapArray[arrayX][arrayY][0] = 1;
                game.mapArray[arrayX][arrayY][1] = 0; // Brown
            }
            hexInQ = qDoor + 1;
            hexInR = rDoor - 1;
            hexOutQ = qDoor;
            hexOutR = rDoor + 1;
            qDoor++;
            arrayX = game.mapRadius + rDoor;
            arrayY = game.mapRadius + qDoor - Math.max(0, -rDoor);
            game.mapArray[arrayX][arrayY][0] = 3;
            game.mapArray[arrayX][arrayY][1] = 2; // Door
        }
    }

    private void buildHouse(int qBase, int rBase){
        // Builds the house
        int col = qBase + (rBase - (rBase&1))/2;  // Converts from Axial to Offset
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
                arrayX = game.mapRadius + nRow;
                arrayY = game.mapRadius + nCol - (nRow - (nRow&1))/2 - Math.max(0, -nRow);
                game.mapArray[arrayX][arrayY][0] = 1;
                game.mapArray[arrayX][arrayY][1] = 0; // Brown**************
            }
        }

        // Setting the walls
        // Horizontal
        for (int i = 0; i < hexWidth; i++){
            // Up Wall
            nCol = col + i;
            nRow = row - 1;
            arrayX = game.mapRadius + nRow;
            arrayY = game.mapRadius + nCol - (nRow - (nRow&1))/2 - Math.max(0, -nRow);
            game.mapArray[arrayX][arrayY][0] = 2;
            game.mapArray[arrayX][arrayY][1] = 1; // Horizontal wall

            // Down Wall
            nRow = row + 2*hexHeight - 1;
            arrayX = game.mapRadius + nRow;
            arrayY = game.mapRadius + nCol - (nRow - (nRow&1))/2 - Math.max(0, -nRow);
            game.mapArray[arrayX][arrayY][0] = 2;
            game.mapArray[arrayX][arrayY][1] = 1;
        }
        // Vertical
        arrayX = game.mapRadius + row;
        arrayY = game.mapRadius + col - (row - (row&1))/2 - Math.max(0, -row);
        game.mapArray[arrayX][arrayY][0] = 2;
        game.mapArray[arrayX][arrayY][1] = 0; // Vertical wall
        arrayY = game.mapRadius + col + hexWidth - (row - (row&1))/2 - Math.max(0, -row);
        game.mapArray[arrayX][arrayY][0] = 2;
        game.mapArray[arrayX][arrayY][1] = 0; // Vertical wall
        for (int i = 1; i < hexHeight; i++){
            // Left Wall
            nCol = col;
            nRow = row + 2*i;
            arrayX = game.mapRadius + nRow;
            arrayY = game.mapRadius + nCol - (nRow - (nRow&1))/2 - Math.max(0, -nRow);
            game.mapArray[arrayX][arrayY][0] = 2;
            game.mapArray[arrayX][arrayY][1] = 0; // Vertical wall
            for (int j = 0; j < 2; j++){
                arrayX = game.mapRadius + nRow - 1;
                arrayY = game.mapRadius + nCol + j - 1 - (nRow - (nRow&1))/2 - Math.max(0, -nRow);
//                game.mapArray[arrayX][arrayY][3] = 1; // Sets as a wall, but without the texture
            }

            // Right Wall
            nCol = col + hexWidth;
            arrayX = game.mapRadius + nRow;
            arrayY = game.mapRadius + nCol - (nRow - (nRow&1))/2 - Math.max(0, -nRow);
            game.mapArray[arrayX][arrayY][0] = 2;
            game.mapArray[arrayX][arrayY][1] = 0; // Vertical wall
            for (int j = 0; j < 2; j++){
                arrayX = game.mapRadius + nRow - 1;
                arrayY = game.mapRadius + nCol + j - 1 - (nRow - (nRow&1))/2 - Math.max(0, -nRow);
//                game.mapArray[arrayX][arrayY][3] = 1; // Sets as a wall, but without the texture
            }
        }
    }

    private void buildEntrance(int qBase, int rBase){
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
                arrayX = game.mapRadius + nRow;
                arrayY = game.mapRadius + nCol - (nRow - (nRow&1))/2 - Math.max(0, -nRow);
                game.mapArray[arrayX][arrayY][0] = 1;
                game.mapArray[arrayX][arrayY][1] = 0; // Brown
            }
        }
    }
}
