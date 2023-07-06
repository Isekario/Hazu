package com.isekario.hazu;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.isekario.maker.Maker;

public class MakerLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("Maker");
        config.setWindowedMode(1600, 900);
        new Lwjgl3Application(new Maker(), config);
    }
}
