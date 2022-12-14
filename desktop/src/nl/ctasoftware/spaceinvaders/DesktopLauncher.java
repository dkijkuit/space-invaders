package nl.ctasoftware.spaceinvaders;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import nl.ctasoftware.spaceinvaders.SpaceInvadersGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		//config.setWindowedMode(800, 600);
		config.setWindowedMode(1200, 900);
		config.useVsync(true);
		config.setTitle("SpaceInvaders");
		new Lwjgl3Application(new SpaceInvadersGame(), config);
	}
}
