package io.github.eokeefe;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter implements InputProcessor {

    private SpriteBatch batch;
    private Obj gdxLogo;

    private float w, h;

    private final Color background = new Color(0f, 0f, 0f, 1f);

    private boolean paused = false;

    private final List<Obj> renderItems = new ArrayList<Obj>();

    Player player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Gdx.graphics.setTitle("LibGDX Game");
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        player = new Player("player.png", 140, 110);

        renderItems.add(player);

        InputMultiplexer inputs = new InputMultiplexer();

        inputs.addProcessor(this);
        inputs.addProcessor(player.getInputProcessor());

        Gdx.input.setInputProcessor(inputs);
    }

    @Override
    public void render() {
        updateScreen();

        ScreenUtils.clear(background);

        batch.begin();

        for (Obj obj : renderItems) {
            batch.draw(obj, obj.x, obj.y);
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        gdxLogo.dispose();
    }

    private void updateScreen() {
        if (paused) {
            return;
        }

        for (Obj o : renderItems){
            o.update();
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
    public boolean keyUp(int keycode) {return false;}
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
