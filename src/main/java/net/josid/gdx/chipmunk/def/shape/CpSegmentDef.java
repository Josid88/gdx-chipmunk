package net.josid.gdx.chipmunk.def.shape;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.def.CpShapeDef;


public class CpSegmentDef extends CpShapeDef {

    public final Vector2 _a = new Vector2();
    public final Vector2 _b = new Vector2();
    public float _radius = 0;
    public boolean isNeighbors = false;
    public Vector2 neighborA = new Vector2();
    public Vector2 neighborB = new Vector2();
    
    public CpSegmentDef a(Vector2 a) {
        _a.set(a);
        return this;
    }
    public CpSegmentDef a(float x, float y) {
        _a.set(x, y);
        return this;
    }
    public CpSegmentDef b(Vector2 b) {
        _b.set(b);
        return this;
    }
    public CpSegmentDef b(float x, float y) {
        _b.set(x, y);
        return this;
    }
    public CpSegmentDef radius(float radius) {
        _radius = radius;
        return this;   
    }
    public CpSegmentDef neighbors(float a_x, float a_y, float b_x, float b_y) {
        isNeighbors = true;
        neighborA.set(a_x, a_y);
        neighborB.set(b_x, b_y);
        return this;
    }
    public CpSegmentDef neighbors(Vector2 a, Vector2 b) {
        isNeighbors = true;
        neighborA.set(a);
        neighborB.set(b);
        return this;
    }
    public CpSegmentDef unsetNeighbors() {
        isNeighbors = false;
        neighborA.setZero();
        neighborB.setZero();
        return this;
    }

    @Override
    public Type getType() {
        return Type.Segment;
    }

}
