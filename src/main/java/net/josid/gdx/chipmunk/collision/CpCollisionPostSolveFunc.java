package net.josid.gdx.chipmunk.collision;

import net.josid.gdx.chipmunk.CpArbiter;
import net.josid.gdx.chipmunk.CpSpace;

@FunctionalInterface
public interface CpCollisionPostSolveFunc {

    public void postSolve(CpArbiter arbiter, CpSpace space, Object data);

}
