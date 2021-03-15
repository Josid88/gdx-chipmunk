package net.josid.gdx.chipmunk.callback;

import net.josid.gdx.chipmunk.CpBody;


@FunctionalInterface
public interface CpBodyPositionFunc {

    public void updateFunc(CpBody body, float delta);

}
