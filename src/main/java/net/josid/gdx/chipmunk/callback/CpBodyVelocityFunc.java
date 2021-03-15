package net.josid.gdx.chipmunk.callback;

import net.josid.gdx.chipmunk.CpBody;

@FunctionalInterface
public interface CpBodyVelocityFunc {

    public void update(CpBody body, float gravity_x, float gravity_y, float damping, float delta);

}
