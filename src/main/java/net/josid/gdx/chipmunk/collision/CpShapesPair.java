package net.josid.gdx.chipmunk.collision;

import net.josid.gdx.chipmunk.CpShape;

public class CpShapesPair {

    public CpShape a;
    public CpShape b;

    public CpShapesPair set(CpShape a, CpShape b) {
        this.a = a;
        this.b = b;
        return this;
    }

}
