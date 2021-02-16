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
        DOOR_HORIZONTAL
    }

    public static final int NUM_TEXTURES = id.values().length;

    public static final int
            ID_EMPTY = id.EMPTY.ordinal(),
            ID_CENTER = id.CENTER.ordinal(),
            ID_CLICK = id.CLICK.ordinal(),
            ID_DIRT_0 = id.DIRT_0.ordinal(),
            ID_DIRT_1 = id.DIRT_1.ordinal(),
            ID_GRASS_0 = id.GRASS_0.ordinal(),
            ID_GRASS_1 = id.GRASS_1.ordinal(),
            ID_WALL_VERTICAL = id.WALL_VERTICAL.ordinal(),
            ID_WALL_HORIZONTAL = id.WALL_HORIZONTAL.ordinal(),
            ID_DOOR_VERTICAL = id.DOOR_VERTICAL.ordinal(),
            ID_DOOR_HORIZONTAL = id.DOOR_HORIZONTAL.ordinal();


    private ArrayList<Texture> assetsList;
    private Stage stage;
    private Skin skin;

    public Assets() {

        skin = new Skin(Gdx.files.internal("skin/skin.json"));

        assetsList = new ArrayList<Texture>();

        for (int i = 0; i < NUM_TEXTURES; i++){
            assetsList.add(null);
        }

        assetsList.set(ID_EMPTY, new Texture("int_half/empty.png"));
        assetsList.set(ID_CENTER, new Texture("int_half/center.png"));
        assetsList.set(ID_CLICK, new Texture("int_half/click.png"));
        assetsList.set(ID_DIRT_0, new Texture("int_half/brown_0.png"));
        assetsList.set(ID_DIRT_1, new Texture("int_half/brown_1.png"));
        assetsList.set(ID_GRASS_0, new Texture("int_half/green_0.png"));
        assetsList.set(ID_GRASS_1, new Texture("int_half/green_1.png"));
        assetsList.set(ID_WALL_VERTICAL, new Texture("int_half/wall_v.png"));
        assetsList.set(ID_WALL_HORIZONTAL, new Texture("int_half/wall_h.png"));
        assetsList.set(ID_DOOR_VERTICAL, new Texture("int_half/door_v.png"));
        assetsList.set(ID_DOOR_HORIZONTAL, new Texture("int_half/door_h.png"));
    }

    public Texture getTexture(int id) {
        return assetsList.get(id);
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
