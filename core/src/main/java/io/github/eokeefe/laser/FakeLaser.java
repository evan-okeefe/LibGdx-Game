package io.github.eokeefe.laser;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import io.github.eokeefe.Main;
import io.github.eokeefe.Obj;

import java.util.Random;

public class FakeLaser extends Obj {

    Vector2 dir;
    float speed;
    float w, h;
    int edge;

    Laser l;
    Random r = new Random();


    public FakeLaser(float _w, float _h, String _path, float _speed, int _edge, Laser _l, float _x, float _y, Vector2 _dir){
        super(_path);
        speed = _speed;
        w = _w;
        h = _h;
        l = _l;

        dir = _dir;

        this.x = _x;
        this.y = _y;

        edge = _edge;
    }

    @Override
    public void update() {

        this.x += dir.x * speed;
        this.y += dir.y * speed;

        switch (edge) {
            case 0:
                if (this.x > w) {
                   l.launch();
                    Main.renderRemove.add(this);
                }
                break;
            case 1:
                if (this.x + getWidth() < 0) {
                    l.launch();
                    Main.renderRemove.add(this);
                }
                break;
        }
    }
}