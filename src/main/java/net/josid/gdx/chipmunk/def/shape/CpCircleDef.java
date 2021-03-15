package net.josid.gdx.chipmunk.def.shape;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.def.CpShapeDef;


public class CpCircleDef extends CpShapeDef {

    public float _radius = 0;
    public final Vector2 _offset = new Vector2();

    public CpCircleDef radius(float radius) {
        this._radius = radius;
        return this;
    }
    public CpCircleDef offset(Vector2 offset) {
        this._offset.set(offset);
        return this;
    }
    public CpCircleDef offset(float offset_x, float offset_y) {
        this._offset.set(offset_x, offset_y);
        return this;
    }

    @Override
    public Type getType() {
        return Type.Circle;
    }    
}
