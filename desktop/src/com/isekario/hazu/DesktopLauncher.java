package com.isekario.hazu;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.isekario.hazu.tilemaps.TileTest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Hazu");
		config.setWindowIcon("icon.png");
		//new Lwjgl3Application(new Hazu(), config);
		new Lwjgl3Application(new TileTest(), config);
	}
}
