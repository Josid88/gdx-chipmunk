package net.josid.gdx.chipmunk.callback;

import net.josid.gdx.chipmunk.constraint.CpDampedSpring;

@FunctionalInterface
public interface CpDampedSpringForceFunc {

    public float onForce(CpDampedSpring dampedSpring, float dist);

}
