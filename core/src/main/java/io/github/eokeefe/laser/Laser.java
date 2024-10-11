package io.github.eokeefe.laser;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import io.github.eokeefe.Main;
import io.github.eokeefe.Obj;

import java.util.Random;

public class Laser extends Obj {

    Vector2 dir;
    float speed;
    float w, h;
    int edge;

    boolean canMove = false;
    Random r = new Random();

    FakeLaser currentFakeLaser;


    public Laser(float _w, float _h, String _path, float _speed){
        super(_path);
        speed = _speed;
        w = _w;
        h = _h;

        reset();
    }

    @Override
    public void update() {

        if (!canMove){
            return;
        }

        this.x += dir.x * speed;
        this.y += dir.y * speed;

        switch (edge) {
            case 0:
                if (this.x > w) {
                    reset();
                }
                break;
            case 1:
                if (this.x + getWidth() < 0) {
                    reset();
                }
                break;
        }
    }

    public void reset() {
        canMove = false;

        edge = r.nextInt(2);

        randomEdge();

        currentFakeLaser = new FakeLaser(w, h, "laser-1.png", 4, edge, this, x, y, dir);

        Main.renderAdd.add(currentFakeLaser);
    }

    private void randomEdge(){
        float textureWidth = this.getWidth();
        float textureHeight = this.getHeight();
        switch (edge) {
            // Left
            case 0:
                this.x = -textureWidth; // Fully off-screen to the left
                this.y = r.nextFloat() * h - textureHeight / 2;
                dir = new Vector2(1, 0); // Moving right
                break;
            // Right
            case 1:
                this.x = w + textureWidth; // Fully off-screen to the right
                this.y = r.nextFloat() * h - textureHeight / 2;
                dir = new Vector2(-1, 0); // Moving left
                break;
        }
    }

    public void launch(){
        canMove = true;
    }

    public void resetFull() {
        canMove = false;

        edge = r.nextInt(2);

        randomEdge();

        Main.renderRemove.add(currentFakeLaser);

        currentFakeLaser = new FakeLaser(w, h, "laser-1.png", 4, edge, this, x, y, dir);

        Main.renderAdd.add(currentFakeLaser);
    }
}