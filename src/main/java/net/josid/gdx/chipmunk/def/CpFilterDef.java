package net.josid.gdx.chipmunk.def;


public class CpFilterDef {

    public int _group = 0;
    public int _categories = ~0;
    public int _mask = ~0;

    public CpShapeDef _shapeDef;
    
    public CpFilterDef group(int _group) {
        this._group = _group;
        return this;
    }
    public CpFilterDef categories(int _categories) {
        this._categories = _categories;
        return this;
    }
    public CpFilterDef mask(int _mask) {
        this._mask = _mask;
        return this;
    }

    CpFilterDef shapeDef(CpShapeDef shapeDef) {
        this._shapeDef = shapeDef;
        return this;
    }
    public CpShapeDef shape() {
        return _shapeDef;
    }
    public CpBodyDef body() {
        return _shapeDef._bodyDef;
    }
    public void set(CpFilterDef def) {
        this._group = def._group;
        this._categories = def._categories;
        this._mask = def._mask;
    }

}
