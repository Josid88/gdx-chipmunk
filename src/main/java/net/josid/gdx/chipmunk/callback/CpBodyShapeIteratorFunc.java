package net.josid.gdx.chipmunk.callback;

import net.josid.gdx.chipmunk.CpBody;
import net.josid.gdx.chipmunk.CpShape;

@FunctionalInterface
public interface CpBodyShapeIteratorFunc {

    public void shape(CpBody body, CpShape shape);

}
