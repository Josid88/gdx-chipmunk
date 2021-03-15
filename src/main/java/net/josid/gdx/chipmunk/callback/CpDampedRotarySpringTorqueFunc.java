package net.josid.gdx.chipmunk.callback;

import net.josid.gdx.chipmunk.constraint.CpDampedRotarySpring;

@FunctionalInterface
public interface CpDampedRotarySpringTorqueFunc {

    public float onForce(CpDampedRotarySpring dampedRotarySpring, float relativeAngle);

}
