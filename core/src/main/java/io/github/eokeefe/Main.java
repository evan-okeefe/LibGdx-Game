package io.github.eokeefe;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.eokeefe.laser.Laser;
import java.util.ArrayList;
import java.util.List;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter implements InputProcessor {

    private SpriteBatch batch;
    private Obj gdxLogo;

    private float w, h;

    private final Color background = new Color(0f, 0f, 0f, 1f);

    private boolean paused = false;

    public static final List<Obj> renderItems = new ArrayList<Obj>();
    public static final List<Obj> renderAdd = new ArrayList<Obj>();
    public static final List<Obj> renderRemove = new ArrayList<Obj>();

    private boolean running = true;

    Player player;

    List<Boulder> boulders = new ArrayList<Boulder>();
    List<Laser> lasers = new ArrayList<Laser>();

    Obj lose;

    int numBoulders = 5;
    int numLasers = 1;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Gdx.graphics.setTitle("LibGDX Game");
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        player = new Player("player.png", 140, 110);

        for (int i = 0; i < numBoulders; i++){
            addBoulder();
        }
        for (int i = 0; i < numLasers; i++){
            addLaser();
        }

        renderAdd.add(player);

        Texture img = new Texture("lose.png");

        lose = new Obj("lose.png", (w/2) - ((float) img.getWidth() /2), (h/2) - ((float) img.getHeight() /2 ));

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
        renderItems.addAll(renderAdd);
        renderItems.removeAll(renderRemove);

        renderRemove.clear();
        renderAdd.clear();

        if (paused || !running) {
            return;
        }


        for (Obj o : renderItems){
            o.update();
        }

        for (Boulder b : boulders){
            if (player.getBoundingBox().overlaps(b.getBoundingBox())){
                player.collision(b);

                running = false;

                renderAdd.add(lose);
            }
        }

        for (Laser l : lasers){
            if (player.getBoundingBox().overlaps(l.getBoundingBox())){
                player.collision(l);

                running = false;

                renderAdd.add(lose);
            }
        }


    }

    private void addBoulder(){
        Boulder b = new Boulder(w, h, "boulder.png");

        boulders.add(b);
        renderAdd.add(b);
    }

    private void addLaser(){
        Laser l = new Laser(w, h, "laser-2.png", 40);

        lasers.add(l);
        renderAdd.add(l);
    }

    //INPUTS:
    @Override
    public boolean keyDown(int k) {
        if (k == Input.Keys.ESCAPE) {
            paused = !paused;
        }
        if (k == Input.Keys.R){
            renderRemove.add(lose);

            player.x = 140;
            player.y = 110;

            for (Boulder b : boulders){
                b.reset();
            }

            for (Laser l : lasers){
                l.resetFull();
            }
            running = true;

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
