package com.isekario.hazu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector3;

public class TileTest extends ApplicationAdapter implements InputProcessor {
    Texture img;
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    SpriteBatch sb;
    Texture texture;
    Sprite sprite;

    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();

        tiledMap = new TmxMapLoader().load("maps/school/school.tmx");
        tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);

        Gdx.input.setInputProcessor(this);

        sb = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("icons/teddy.png"));
        sprite = new Sprite(texture);

        //how not to do sprites behind stuff
        int mapWidth = tiledMap.getProperties().get("width", Integer.class) / 2;
        int mapHeight = tiledMap.getProperties().get("height", Integer.class) / 2;

        TiledMapTileLayer tileLayer = new TiledMapTileLayer(mapWidth, mapHeight, 64, 64);
        Cell cell = new Cell();
        TextureRegion textureRegion = new TextureRegion(texture, 64, 64);

        cell.setTile(new StaticTiledMapTile(textureRegion));
        tileLayer.setCell(4, 10, cell);

        MapLayer tempLayer = tiledMap.getLayers().get(tiledMap.getLayers().getCount() - 1);
        tiledMap.getLayers().remove(tiledMap.getLayers().getCount() - 1);
        tiledMap.getLayers().add(tileLayer);
        tiledMap.getLayers().add(tempLayer);
        //end of what not to do
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        sb.setProjectionMatrix(camera.combined);

        sb.begin();
        sprite.draw(sb);
        sb.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT -> camera.translate(-32, 0);
            case Input.Keys.RIGHT -> camera.translate(32, 0);
            case Input.Keys.UP -> camera.translate(0, 32);
            case Input.Keys.DOWN -> camera.translate(0, -32);
            case Input.Keys.NUM_1 -> tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
            //case Input.Keys.NUM_2 -> tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 clickCoordinates = new Vector3(screenX, screenY, 0);
        Vector3 position = camera.unproject(clickCoordinates);
        sprite.setPosition(position.x, position.y);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public void dispose() {
        super.dispose();

        //img.dispose();
        tiledMap.dispose();
        sb.dispose();
        texture.dispose();
    }
}
