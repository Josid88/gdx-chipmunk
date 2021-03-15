package net.josid.gdx.chipmunk.callback;

import net.josid.gdx.chipmunk.CpShape;

@FunctionalInterface
public interface CpSpacePointQueryFunc {

    public void query(CpShape shape, float x, float y, float distance, float gradient_x, float gradient_y);

}
