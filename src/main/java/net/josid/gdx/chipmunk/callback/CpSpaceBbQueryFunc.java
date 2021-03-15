package net.josid.gdx.chipmunk.callback;

import net.josid.gdx.chipmunk.CpShape;

@FunctionalInterface
public interface CpSpaceBbQueryFunc {

    public void query(CpShape shape);
    
}
