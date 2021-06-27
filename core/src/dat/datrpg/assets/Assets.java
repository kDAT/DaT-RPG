package dat.datrpg.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class Assets {


//    hHorizontalSpace = (int) (scaleTexture * 56);
//    hVerticalSpace = (int) (scaleTexture * 48);
//    hWidth = (int) (scaleTexture * 56);
//    hHeight = (int) (scaleTexture * 64);
//    hSize = (int) (scaleTexture * 32);
//    sWidth = scaleTexture * 56;//brown_n0.getWidth();
//    sHeight = scaleTexture * 96;//brown_n0.getHeight();

    // Constants
    private static final float scaleTexture = 1f;
    public static final int HORIZONTAL_SPACE = (int)(scaleTexture * 28);
    public static final int VERTICAL_SPACE = (int)(scaleTexture * 24);
    public static final int HEX_WIDTH = (int)(scaleTexture * 28);
    public static final int HEX_HEIGHT = (int)(scaleTexture * 32);
    public static final int HEX_SIZE = (int)(scaleTexture * 16);
    public static final int SPRITE_WIDTH = (int)(scaleTexture * 28);
    public static final int SPRITE_HEIGHT = (int)(scaleTexture * 48);

    private enum id {
        EMPTY,
        CENTER,
        CLICK,
        DIRT_0,
        DIRT_1,
        GRASS_0,
        GRASS_1,
        WALL_VERTICAL,
        WALL_HORIZONTAL,
        DOOR_VERTICAL,
        DOOR_HORIZONTAL,
        JUMP
    }

    public static final int NUM_TEXTURES = id.values().length;
    public static final int NUM_LEVELS = 4;

    public static final byte
            ID_EMPTY = (byte)id.EMPTY.ordinal(),
            ID_CENTER = (byte)id.CENTER.ordinal(),
            ID_CLICK = (byte)id.CLICK.ordinal(),
            ID_JUMP = (byte)id.JUMP.ordinal(),
            ID_DIRT_0 = (byte)id.DIRT_0.ordinal(),
            ID_DIRT_1 = (byte)id.DIRT_1.ordinal(),
            ID_GRASS_0 = (byte)id.GRASS_0.ordinal(),
            ID_GRASS_1 = (byte)id.GRASS_1.ordinal(),
            ID_WALL_VERTICAL = (byte)id.WALL_VERTICAL.ordinal(),
            ID_WALL_HORIZONTAL = (byte)id.WALL_HORIZONTAL.ordinal(),
            ID_DOOR_VERTICAL = (byte)id.DOOR_VERTICAL.ordinal(),
            ID_DOOR_HORIZONTAL = (byte)id.DOOR_HORIZONTAL.ordinal();


    private final ArrayList<Texture> assetsList;
    private final byte[] levels;
    private final byte[] collisions;
    private Stage stage;
    private final Skin skin;

    public Assets() {

        skin = new Skin(Gdx.files.internal("skin/skin.json"));

        assetsList = new ArrayList<>();

        levels = new byte[NUM_TEXTURES];
        collisions = new byte[NUM_TEXTURES];

        for (int i = 0; i < NUM_TEXTURES; i++){
            assetsList.add(null);
        }

        // TODO Atlas for all the textures
        // TODO More textures: wall, gate, house floor
        assetsList.set(ID_EMPTY, new Texture("int_half/empty.png"));
        assetsList.set(ID_CENTER, new Texture("int_half/center.png"));
        assetsList.set(ID_CLICK, new Texture("int_half/click.png"));
        assetsList.set(ID_JUMP, new Texture("int_half/jump.png"));
        assetsList.set(ID_DIRT_0, new Texture("int_half/brown_0.png"));
        assetsList.set(ID_DIRT_1, new Texture("int_half/brown_1.png"));
        assetsList.set(ID_GRASS_0, new Texture("int_half/green_0.png"));
        assetsList.set(ID_GRASS_1, new Texture("int_half/green_1.png"));
        assetsList.set(ID_WALL_VERTICAL, new Texture("int_half/wall_v.png"));
        assetsList.set(ID_WALL_HORIZONTAL, new Texture("int_half/wall_h.png"));
        assetsList.set(ID_DOOR_VERTICAL, new Texture("int_half/door_v.png"));
        assetsList.set(ID_DOOR_HORIZONTAL, new Texture("int_half/door_h.png"));

        levels[ID_EMPTY] = 3;
        levels[ID_CENTER] = 3;
        levels[ID_CLICK] = 3;
        levels[ID_JUMP] = 3;
        levels[ID_DIRT_0] = 0;
        levels[ID_DIRT_1] = 1;
        levels[ID_GRASS_0] = 0;
        levels[ID_GRASS_1] = 1;
        levels[ID_WALL_VERTICAL] = 2;
        levels[ID_WALL_HORIZONTAL] = 2;
        levels[ID_DOOR_VERTICAL] = 2;
        levels[ID_DOOR_HORIZONTAL] = 2;

        collisions[ID_EMPTY] = 2;
        collisions[ID_CENTER] = 2;
        collisions[ID_CLICK] = 2;
        collisions[ID_DIRT_0] = 0;
        collisions[ID_DIRT_1] = 0;
        collisions[ID_GRASS_0] = 0;
        collisions[ID_GRASS_1] = 0;
        collisions[ID_WALL_VERTICAL] = 1;
        collisions[ID_WALL_HORIZONTAL] = 1;
        collisions[ID_DOOR_VERTICAL] = 1;
        collisions[ID_DOOR_HORIZONTAL] = 1;
    }

    public Texture getTexture(int id) {
        return assetsList.get(id);
    }

    public int getLevel(int id){
        return levels[id];
    }

    public int getCollision(int id) {
        return collisions[id];
    }

    public Skin getSkin() {
        return skin;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Viewport viewport) {
//        stage.dispose();
        stage = new Stage(viewport);
    }

    public void dispose() {
        for (Texture texture : assetsList) {
            texture.dispose();
        }
        assetsList.clear();

        stage.dispose();
        skin.dispose();
    }
}
