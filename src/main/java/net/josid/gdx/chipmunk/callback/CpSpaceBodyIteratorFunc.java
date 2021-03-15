package net.josid.gdx.chipmunk.callback;

import net.josid.gdx.chipmunk.CpBody;

@FunctionalInterface
public interface CpSpaceBodyIteratorFunc {

    public void each(CpBody body);

}
