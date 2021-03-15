package net.josid.gdx.chipmunk.collision;

import net.josid.gdx.chipmunk.CpBody;


public class CpBodiesPair {

    public CpBody a;
    public CpBody b;

    public CpBodiesPair set(CpBody a, CpBody b) {
        this.a = a;
        this.b = b;
        return this;
    }

}
