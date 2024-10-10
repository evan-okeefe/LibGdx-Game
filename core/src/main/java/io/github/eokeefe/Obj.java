package io.github.eokeefe;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Obj extends Texture {

    public float x, y;

    public float floor = 0;

    public Obj() {
        super("libgdx.png");
        x = 0;
        y = 0;
    }

    public Obj(String _path) {
        super(_path);
        x = 0;
        y = 0;
    }

    public Obj(String _path, float _x, float _y) {
        super(_path);
        x = _x;
        y= _y;
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, getWidth(), getHeight());
    }

    public void update(){
        return;
    }
}
