package net.josid.gdx.chipmunk.callback;

import net.josid.gdx.chipmunk.CpBody;
import net.josid.gdx.chipmunk.CpConstraint;

@FunctionalInterface
public interface CpBodyConstraintIteratorFunc {

    public void constraint(CpBody body, CpConstraint constraint);

}
