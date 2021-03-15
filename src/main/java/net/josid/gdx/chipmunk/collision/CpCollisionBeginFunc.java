package net.josid.gdx.chipmunk.collision;

import net.josid.gdx.chipmunk.CpArbiter;
import net.josid.gdx.chipmunk.CpSpace;

@FunctionalInterface
public interface CpCollisionBeginFunc {

    public boolean begin(CpArbiter arbiter, CpSpace space, Object data);

}
