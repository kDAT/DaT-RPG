package dat.datrpg.entities;

import dat.datrpg.assets.Assets;
import dat.datrpg.utils.HexTools;

import java.io.Serializable;

public class Entity implements Serializable {

    private static final long serialVersionUID = 7043979704338378302L;
    public String name;
    public String race;
    public int hexQ;
    public int hexR;
    public int centerQ;
    public int centerR;
    private byte rotation;
    private short texture_id;
    private boolean flipX;
    private boolean flipY;
    private int color;

    public Entity(int color, String name, String race,
                  int hexQ,
                  int hexR,
                  int centerQ,
                  int centerR) {
        this.color = color;
        this.name = name;
        this.race = race;
        this.hexQ = hexQ;
        this.hexR = hexR;
        this.centerQ = centerQ;
        this.centerR = centerR;
        this.rotation = 0;
        updateTexture();
    }

    public void update() {
        // updates the rotation
        // TMP
        updateTexture();
    }

    public void updateRotation(int centerQ, int centerR) {
        // to remove
        updateTexture();
    }

    private void updateTexture() {
        // update the rest of the texture information
        switch (this.rotation) {
            case 0:
                this.texture_id = Assets.ID_ENTITY_0;
                this.flipX = false;
                this.flipY = false;
                break;
            case 1:
                this.texture_id = Assets.ID_ENTITY_1;
                this.flipX = false;
                this.flipY = false;
                break;
            case 2:
                this.texture_id = Assets.ID_ENTITY_1;
                this.flipX = true;
                this.flipY = false;
                break;
            case 3:
                this.texture_id = Assets.ID_ENTITY_0;
                this.flipX = true;
                this.flipY = false;
                break;
            case 4:
                this.texture_id = Assets.ID_ENTITY_1;
                this.flipX = true;
                this.flipY = true;
                break;
            case 5:
                this.texture_id = Assets.ID_ENTITY_1;
                this.flipX = false;
                this.flipY = true;
                break;

        }
    }

    public void updateCoords(int hexQ, int hexR, int centerQ, int centerR) {
        // Update the rotation
        if (hexQ == this.hexQ && hexR == this.hexR) {
            //dist
            int dist = (Math.abs(centerQ - this.centerQ)
                    + Math.abs(centerQ + centerR - this.centerQ - this.centerR)
                    + Math.abs(centerR - this.centerR)) / 2;
            if (dist > 0) {
                float lerpQ = this.centerQ + (centerQ - this.centerQ) * 1f * (dist - 1) / dist;
                float lerpR = this.centerR + (centerR - this.centerR) * 1f * (dist - 1) / dist;
                int[] neiHex = HexTools.roundHex(lerpQ, lerpR);
                int[] diffHex = {centerQ - neiHex[0], centerR - neiHex[1]};
                byte dir = HexTools.getDirection(diffHex[0], diffHex[1]);
                if (dir >= 0) setRotation(dir);

                // TODO Print
                System.out.println(diffHex[0] + "\t" + diffHex[1] + "\t" + dir);
            }
        }

        this.hexQ = hexQ;
        this.hexR = hexR;
        this.centerQ = centerQ;
        this.centerR = centerR;
    }

    public void setRotation(byte rotation) {
        this.rotation = rotation;
        updateTexture();
    }

    public int getHexQ() {
        return hexQ;
    }

    public int getHexR() {
        return hexR;
    }

    public int getCenterQ() {
        return centerQ;
    }

    public int getCenterR() {
        return centerR;
    }

    public short getTexture_id() {
        return texture_id;
    }

    public boolean isFlipX() {
        return flipX;
    }

    public boolean isFlipY() {
        return flipY;
    }

    public int getColor() {
        return color;
    }
}
