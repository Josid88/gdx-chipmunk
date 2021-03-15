package net.josid.gdx.chipmunk.constraint;

import net.josid.gdx.chipmunk.Chipmunk;
import net.josid.gdx.chipmunk.CpConstraint;
import net.josid.gdx.chipmunk.InternalChipmunk;
import net.josid.gdx.chipmunk.callback.CpDampedRotarySpringTorqueFunc;


public class CpDampedRotarySpring extends CpConstraint {

    public CpDampedRotarySpringTorqueFunc dampedRotarySpringTorqueFunc;

    CpDampedRotarySpring(long nativeAddress, Chipmunk chipmunk) {
        super(nativeAddress, chipmunk, Type.DampedRotarySpring);
    }

    @Override
    public void reset() {
        super.reset();
        dampedRotarySpringTorqueFunc = null;
    }

    /**
     * Get the rest length of the spring.
     */ 
    public float getRestAngle() {
        return JniConstraintType.dampedRotarySpringGetRestAngle(nativeAddress);
    }

    /**
     * Set the rest length of the spring.
     */ 
    public void setRestAngle(float restAngle) {
        JniConstraintType.dampedRotarySpringSetRestAngle(nativeAddress, restAngle);
    }

    /**
     * Get the stiffness of the spring in force/distance.
     */ 
    public float getStiffness() {
        return JniConstraintType.dampedRotarySpringGetStiffness(nativeAddress);
    }

    /**
     * Set the stiffness of the spring in force/distance.
     */ 
    public void setStiffness(float stiffness) {
        JniConstraintType.dampedRotarySpringSetStiffness(nativeAddress, stiffness);
    }

    /**
     * Get the damping of the spring.
     */ 
    public float getDamping() {
        return JniConstraintType.dampedRotarySpringGetDamping(nativeAddress);
    }

    /**
     * Set the damping of the spring.
     */ 
    public void setDamping(float damping) {
        JniConstraintType.dampedRotarySpringSetDamping(nativeAddress, damping);
    }

    /**
     * Set the damping of the spring.
     */ 
    @SuppressWarnings("deprecation")
    public void setSpringTorqueFunc(CpDampedRotarySpringTorqueFunc func) {
        dampedRotarySpringTorqueFunc = func;
        JniConstraintType.dampedRotarySpringSetSpringTorqueFunc(nativeAddress, InternalChipmunk.getDampedRotarySpringTorqueFunc());
    }

}
