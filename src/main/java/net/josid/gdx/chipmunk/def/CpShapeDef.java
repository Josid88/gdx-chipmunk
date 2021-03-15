package net.josid.gdx.chipmunk.def;

import com.badlogic.gdx.math.Vector2;


public abstract class CpShapeDef {

    public boolean _sensor = false;
    public final OptionalFloat _mass =  new OptionalFloat();
    public final OptionalFloat _density = new OptionalFloat();
    public float _elastisity = 0;
    public float _friction = 0;
    public final Vector2 _surfaceVelocity = new Vector2();
    public CpFilterDef _filterDef;
    public CpBodyDef _bodyDef;

    public abstract Type getType();

    public CpShapeDef sensor(boolean sensor) {
        _sensor = sensor;
        return this;
    }
    public CpShapeDef elastisity(float elastisity) {
        this._elastisity = elastisity;
        return this;
    }
    public CpShapeDef friction(float friction) {
        this._friction = friction;
        return this;
    }
    public CpShapeDef surfaceVelocity(Vector2 surfaceVelocity) {
        this._surfaceVelocity.set(surfaceVelocity);
        return this;
    }
    public CpShapeDef surfaceVelocity(float surfaceVelocity_x, float surfaceVelocity_y) {
        this._surfaceVelocity.set(surfaceVelocity_x, surfaceVelocity_y);
        return this;
    }
    public CpShapeDef mass(float mass) {
        this._mass.set(mass);
        this._density.unset();
        return this;
    }
    public CpShapeDef density(float density) {
        this._density.set(density);
        this._mass.unset();
        return this;
    }
    public CpShapeDef filterDef(CpFilterDef filterDef) {
        this._filterDef = filterDef;
        return this;
    }
    public CpFilterDef newFilterDef() {
        return _filterDef = new CpFilterDef().shapeDef(this);
    }
    public CpShapeDef set(CpShapeDef def) {
        _sensor = false;
        _mass.copy(def._mass);
        _density.copy(def._density);
        _elastisity = def._elastisity;
        _friction = def._elastisity;
        _surfaceVelocity.set(def._surfaceVelocity);
        if (null == def._filterDef) _filterDef = null;
        else {
            if (null == _filterDef) newFilterDef();
            _filterDef.set(def._filterDef);
        }
        return this;
    }
    protected CpShapeDef setBody(CpBodyDef def) {
        this._bodyDef = def;
        return this;
    }
    public CpBodyDef body() {
        return _bodyDef;
    }

    public static enum Type {
        Circle, Segment, Polygon, Box
    }
}
