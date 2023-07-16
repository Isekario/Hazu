package com.isekario.maker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;

public class MainScreen implements Screen {
    private final Maker applicationContext;
    private final ImmediateModeRenderer20 lineRenderer;
    private final OrthographicCamera camera;

    public MainScreen(Maker applicationContext) {
        this.applicationContext = applicationContext;
        lineRenderer = new ImmediateModeRenderer20(false, true, 0);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        lineRenderer.begin(camera.combined, GL20.GL_LINES);
        grid(10, 10, 32);
        lineRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    /**
     * Draws a line
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param r
     * @param g
     * @param b
     * @param a
     */
    private void line(float x1, float y1, float x2, float y2, float r, float g, float b, float a) {
        lineRenderer.color(r, g, b, a);
        lineRenderer.vertex(x1, y1, 0);
        lineRenderer.color(r, g, b, a);
        lineRenderer.vertex(x2, y2, 0);
    }

    private void grid(int columns, int rows, int spacing) {
        for (int x = 0; x < columns; x++)
            line(x * spacing, spacing, x * spacing, -rows * spacing, 1f, 1f, 1f, 1f);

        for (int y = 0; y < rows; y++)
            line(-spacing, -y * spacing, columns * spacing, -y * spacing, 1f, 1f, 1f, 1f);
    }
}
