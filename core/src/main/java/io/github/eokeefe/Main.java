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
    private Texture dice;

    private float w, h;

    private Color background = new Color(0f, 0f, 0f, 1f);

    private final Color[] colors = {
            Color.BLACK,
            new Color(148f, 0f, 0f, 1f),
            new Color(148f, 148f, 0f, 1f),
            new Color(0f, 148f, 0f, 1f),
            new Color(0f, 148f, 148f, 1f),
            new Color(0f, 0f, 148f, 1f),
            new Color(148f, 148f, 148f, 1f),


    };

    private int currentColorIndex = 0;
    private float transitionProgress = 0f;
    private final float transitionDuration = 5f;

    private float diceX = 0, diceY = 0;

    private boolean gdxOnTop = false;
    private boolean shake = true;
    private boolean paused = false;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Gdx.graphics.setTitle("LibGDX Game");
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        gdxLogo = new Texture("libgdx.png");
        dice = new Texture("dice.png");

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {

        updateScreen();

        ScreenUtils.clear(background);
        batch.begin();

        if (gdxOnTop){
            batch.draw(dice, diceX, diceY);
            batch.draw(gdxLogo, (w - gdxLogo.getWidth()) / 2, (h - gdxLogo.getHeight()) / 2);
        }
        else{
            batch.draw(gdxLogo, (w - gdxLogo.getWidth()) / 2, (h - gdxLogo.getHeight()) / 2);
            batch.draw(dice, diceX, diceY);
        }


        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        gdxLogo.dispose();
        dice.dispose();
    }

    private void updateScreen(){
        if (paused){
            return;
        }
        //Color in the background
        int nextColorIndex = (currentColorIndex + 1) % colors.length;
        transitionProgress += Gdx.graphics.getDeltaTime();
        if (transitionProgress >= transitionDuration) {
            transitionProgress = 0f;
            currentColorIndex = nextColorIndex;
        }
        Color currentColor = colors[currentColorIndex];
        Color nextColor = colors[nextColorIndex];
        background = new Color(
                currentColor.r + (nextColor.r - currentColor.r) * (transitionProgress / transitionDuration),
                currentColor.g + (nextColor.g - currentColor.g) * (transitionProgress / transitionDuration),
                currentColor.b + (nextColor.b - currentColor.b) * (transitionProgress / transitionDuration),
                1f
        );

        if (!shake){
            return;
        }
        //Dice Jitter
        Random r = new Random();
        float modX = -1f + (1f - (-1f)) * r.nextFloat();
        float modY = -1f + (1f - (-1f)) * r.nextFloat();
        diceX += modX;
        diceY += modY;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            paused = !paused;
        }

        if (paused){
            return false;
        }
        else if (keycode == Input.Keys.DOWN){
            gdxOnTop = false;
        }
        else if (keycode == Input.Keys.S){
            shake = false;
        } else if (keycode == Input.Keys.UP) {
            gdxOnTop = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.S){
            shake = true;
        }

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
