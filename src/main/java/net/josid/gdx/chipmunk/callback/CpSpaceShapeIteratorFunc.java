package net.josid.gdx.chipmunk.callback;

import net.josid.gdx.chipmunk.CpShape;

@FunctionalInterface
public interface CpSpaceShapeIteratorFunc {

    public void each(CpShape shape);
    
}
