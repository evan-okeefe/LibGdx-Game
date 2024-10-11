package io.github.eokeefe;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntSet;

public class Player extends Obj implements InputProcessor{
    private Vector2 velocity = new Vector2(0, 0);
    private float speed = 5;
    private IntSet keysPressed = new IntSet();

    public InputProcessor getInputProcessor() {
        return this;
    }

    public Player(String _path, float _x, float _y) {
        super(_path, _x, _y);
    }

    public Player(String _path, float _x, float _y, float _speed) {
        super(_path, _x, _y);
        speed = _speed;
    }

    private void onCreate(){

    }

    @Override
    public void update(){
        velocity = velocity.nor();

        this.x += velocity.x * speed;
        this.y += velocity.y * speed;
    }

    public void collision(Obj col){
    }

    //INPUTS:
    @Override
    public boolean keyDown(int k) {
        keysPressed.add(k);

        if (keysPressed.contains(Input.Keys.W) && keysPressed.contains(Input.Keys.S)){
            velocity.y = 0;
        }
        else if (keysPressed.contains(Input.Keys.W)) {
            velocity.y = 1;
        }
        else if (keysPressed.contains(Input.Keys.S)) {
            velocity.y = -1;
        }
        else {
            velocity.y = 0;
        }

        if (keysPressed.contains(Input.Keys.A) && keysPressed.contains(Input.Keys.D)){
            velocity.x = 0;
        }
        else if (keysPressed.contains(Input.Keys.D)) {
            velocity.x = 1;
        }
        else if (keysPressed.contains(Input.Keys.A)) {
            velocity.x = -1;
        }
        else {
            velocity.x = 0;
        }

        return false;
    }

    @Override
    public boolean keyUp(int k) {
        keysPressed.remove(k);
        if (keysPressed.contains(Input.Keys.W)) {
            velocity.y = 1;
        } else if (keysPressed.contains(Input.Keys.S)) {
            velocity.y = -1;
        } else {
            velocity.y = 0;
        }

        if (keysPressed.contains(Input.Keys.D)) {
            velocity.x = 1;
        } else if (keysPressed.contains(Input.Keys.A)) {
            velocity.x = -1;
        } else {
            velocity.x = 0;
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
