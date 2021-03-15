package net.josid.gdx.chipmunk.callback;

import net.josid.gdx.chipmunk.CpShape;


@FunctionalInterface
public interface CpSpaceSegmentQueryFunc {

    public void query(CpShape shape, float x, float y, float normal_x, float normal_y, float alpha);
    
}
