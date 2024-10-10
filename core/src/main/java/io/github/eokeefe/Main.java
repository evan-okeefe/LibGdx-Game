package io.github.eokeefe;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter implements InputProcessor {

    private SpriteBatch batch;
    private Texture gdxLogo;

    private float w, h;

    private Color background = new Color(0f, 0f, 0f, 1f);

    private boolean paused = false;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Gdx.graphics.setTitle("LibGDX Game");
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        gdxLogo = new Texture("libgdx.png");

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {

        updateScreen();

        ScreenUtils.clear(background);

        batch.begin();

        batch.draw(gdxLogo, (w - gdxLogo.getWidth()) / 2, (h - gdxLogo.getHeight()) / 2);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        gdxLogo.dispose();
    }

    private void updateScreen(){
        if (paused){
            return;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            paused = !paused;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {return false;}
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {return false;}
    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {return false;}
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}
    @Override
    public boolean mouseMoved(int screenX, int screenY) {return false;}
    @Override
    public boolean scrolled(float amountX, float amountY) {return false;}
}
