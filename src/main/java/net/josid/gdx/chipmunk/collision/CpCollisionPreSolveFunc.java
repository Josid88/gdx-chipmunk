package net.josid.gdx.chipmunk.collision;

import net.josid.gdx.chipmunk.CpArbiter;
import net.josid.gdx.chipmunk.CpSpace;


@FunctionalInterface
public interface CpCollisionPreSolveFunc {

    public boolean preSolve(CpArbiter arbiter, CpSpace space, Object data);

}
