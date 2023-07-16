package com.isekario.maker;

import com.badlogic.gdx.Game;

public class Maker extends Game {
    @Override
    public void create() {
        setScreen(new MainScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
