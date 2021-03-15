package net.josid.gdx.chipmunk.def.shape;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.def.CpShapeDef;


public class CpPolyBoxDef extends CpShapeDef {

    public float _width = 0;
    public float _height = 0;
    public float _radius = 0;
    public final Vector2 _offset = new Vector2();
    public float _angle = 0;

    public CpPolyBoxDef width(float width) {
        this._width = width;
        return this;
    }
    public CpPolyBoxDef height(float height) {
        this._height = height;
        return this;
    }
    public CpPolyBoxDef radius(float radius) {
        this._radius = radius;
        return this;
    }
    public CpPolyBoxDef offset(Vector2 offset) {
        this._offset.set(offset);
        return this;
    }
    public CpPolyBoxDef offset(float offset_x, float offset_y) {
        this._offset.set(offset_x, offset_y);
        return this;
    }
    public CpPolyBoxDef angle(float angle) {
        this._angle = angle;
        return this;
    }
    
    @Override
    public Type getType() {
        return Type.Box;
    }

}
