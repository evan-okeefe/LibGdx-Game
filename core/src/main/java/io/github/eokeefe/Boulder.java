package io.github.eokeefe;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Boulder extends Obj{

    Vector2 dir;
    float speed;
    float w, h;
    int edge;
    Random r = new Random();

    public Boulder(float _w, float _h, String _path, float _speed){
        super(_path);
        speed = _speed;
        w = _w;
        h = _h;



        edge = r.nextInt(2);
        randomEdge();
    }

    @Override
    public void update() {

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
            case 2:
                if (this.y + getHeight() < 0) {
                    reset();
                }
                break;
            case 3:
                if (this.y > h) {
                    reset();
                }
                break;
        }
    }

    public void reset() {
        edge = r.nextInt(4);

        randomEdge();
    }

    private void randomEdge(){
        float textureWidth = this.getWidth();
        float textureHeight = this.getHeight();
        switch (edge) {
            // Left
            case 0:
                this.x = 0f - textureWidth / 2;
                this.y = r.nextFloat() * h - textureHeight / 2;
                dir = new Vector2(1, 0);
                break;
            // Right
            case 1:
                this.x = w + textureWidth / 2;
                this.y = r.nextFloat() * h - textureHeight / 2;
                dir = new Vector2(-1, 0);
                break;
            // Top
            case 2:
                this.x = r.nextFloat() * w - textureWidth / 2;
                this.y = h + textureHeight / 2;
                dir = new Vector2(0, -1);
                break;
            // Bottom
            case 3:
                this.x = r.nextFloat() * w - textureWidth / 2;
                this.y = 0f - textureHeight / 2;
                dir = new Vector2(0, 1);
                break;
        }
    }
}
