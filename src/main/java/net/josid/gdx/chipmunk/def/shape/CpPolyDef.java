package net.josid.gdx.chipmunk.def.shape;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.def.CpShapeDef;


public class CpPolyDef extends CpShapeDef {

    public float _radius = 0;
    public float _angle = 0;
    public final Vector2 _offset = new Vector2();
    public float[] _vertices;

    public CpPolyDef radius(float radius) {
        this._radius = radius;
        return this;
    }
    public CpPolyDef angle(float _angle) {
        this._angle = _angle;
        return this;
    }
    public CpPolyDef offset(float offset_x, float offset_y) {
        this._offset.set(offset_x, offset_y);
        return this;
    }
    public CpPolyDef offset(Vector2 offset) {
        this._offset.set(offset);
        return this;
    }
    public CpPolyDef vertices(float[] vertices) {
        this._vertices = vertices;
        return this;
    }
    public CpPolyDef verticesCopy(float[] vertices) {
        this._vertices = new float[vertices.length];
        System.arraycopy(vertices, 0, _vertices, 0, vertices.length);
        return this;
    }

    @Override
    public Type getType() {
        return Type.Polygon;
    }

}
