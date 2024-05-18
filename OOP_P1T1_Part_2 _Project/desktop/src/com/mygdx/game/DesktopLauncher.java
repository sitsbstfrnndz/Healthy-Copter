package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.core.GameMaster;



// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static int width = 900;
	public static int height = 530;

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Healthy-Copter");
		config.setWindowedMode(width,height);
		config.setResizable(false); // Prevent the window from being resizable
		new Lwjgl3Application(new GameMaster(width, height), config);
	}
}
