package dat.datrpg.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import dat.datrpg.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.width = 1080;
		config.height = 720;
		config.title = MainGame.TITLE + "  v." + MainGame.VERSION;
		config.addIcon("icon.png", Files.FileType.Internal);
		new LwjglApplication(new MainGame(), config);
	}
}
