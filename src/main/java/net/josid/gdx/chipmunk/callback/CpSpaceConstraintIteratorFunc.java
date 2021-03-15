package net.josid.gdx.chipmunk.callback;

import net.josid.gdx.chipmunk.CpConstraint;


@FunctionalInterface
public interface CpSpaceConstraintIteratorFunc {

    public void each(CpConstraint constraint);

}
