package net.josid.gdx.chipmunk.callback;

import net.josid.gdx.chipmunk.CpConstraint;
import net.josid.gdx.chipmunk.CpSpace;

@FunctionalInterface
public interface CpConstraintSolverFunc {

    public void solve(CpConstraint constraint, CpSpace space);

}
