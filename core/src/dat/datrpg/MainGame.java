package dat.datrpg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dat.datrpg.assets.Assets;
import dat.datrpg.creation.CreateWorld;
import dat.datrpg.entities.Player;
import dat.datrpg.entities.World;
import dat.datrpg.saveload.WorldInfo;
import dat.datrpg.states.TestHex;
import dat.datrpg.states.menus.MainMenu;
import dat.datrpg.states.menus.MenuStateTmp;
import dat.datrpg.states.menus.TestLoad;

public class MainGame extends Game {

    public static final String TITLE = "DAT RPG";
    public static final String VERSION = "0.0.0.0";


    SpriteBatch batch;

    public Assets assets;

    public int mapRadius;
    public int[][][] mapArray;

    @Override
    public void create() {

        assets = new Assets();

//        mapRadius = 50;
//
//        mapArray = new int[2 * mapRadius + 1][][];
//        for (int i = 0; i < mapArray.length; i++) {
//            int rowSize = 2 * mapRadius + 1 - Math.abs(mapRadius - i);
//            mapArray[i] = new int[rowSize][2];
//        }

        batch = new SpriteBatch();

//        Player player = new Player("world default", "lol");
//        WorldInfo worldInfo = new WorldInfo("default world", "56", "63");
//        World world = CreateWorld.newWorld(worldInfo, player);

//        setScreen(new TestHex(this, batch, assets, world));
        setScreen(new MainMenu(this, batch, assets));
    }

//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
//	}

    @Override
    public void dispose() {
        batch.dispose();
        assets.dispose();
    }
}
