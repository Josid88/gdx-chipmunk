package net.josid.gdx.chipmunk.constraint;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.Chipmunk;
import net.josid.gdx.chipmunk.CpConstraint;
import net.josid.gdx.chipmunk.InternalChipmunk;
import net.josid.gdx.chipmunk.callback.CpDampedSpringForceFunc;


public class CpDampedSpring extends CpConstraint {

    public CpDampedSpringForceFunc dampedSpringForceFunc;

    CpDampedSpring(long nativeAddress, Chipmunk chipmunk) {
        super(nativeAddress, chipmunk, Type.DampedSpring);
    }


    /**
     * Get the location of the first anchor relative to the first body.
     */ 
    public Vector2 getAnchorA(Vector2 out_a) {
        float[] floats4 = getFloats4();
        JniConstraintType.dampedSpringGetAnchorA(nativeAddress, floats4);
        return out_a.set(floats4[0], floats4[1]);
    }

    /**
     * Set the location of the first anchor relative to the first body.
     */ 
    public void setAnchorA(float x, float y) {
        JniConstraintType.dampedSpringSetAnchorA(nativeAddress, x, y);
    }

    /**
     * Get the location of the second anchor relative to the second body.
     */ 
    public Vector2 getAnchorB(Vector2 out_b) {
        float[] floats4 = getFloats4();
        JniConstraintType.dampedSpringGetAnchorB(nativeAddress, floats4);
        return out_b.set(floats4[0], floats4[1]);
    }

    /**
     * Set the location of the second anchor relative to the second body.
     */ 
    public void setAnchorB(float x, float y) {
        JniConstraintType.dampedSpringSetAnchorB(nativeAddress, x, y);
    }

    /**
     * Get the rest length of the spring.
     */ 
    public float getRestLength() {
        return JniConstraintType.dampedSpringGetRestLength(nativeAddress);
    }

    /**
     * Set the rest length of the spring.
     */ 
    public void setRestLength(float restLength) {
        JniConstraintType.dampedSpringSetRestLength(nativeAddress, restLength);
    }

    /**
     * Get the stiffness of the spring in force/distance.
     */ 
    public float getStiffness() {
        return JniConstraintType.dampedSpringGetStiffness(nativeAddress);
    }

    /**
     * Set the stiffness of the spring in force/distance.
     */ 
    public void setStiffness(float stiffness) {
        JniConstraintType.dampedSpringSetStiffness(nativeAddress, stiffness);
    }

    /**
     * Get the damping of the spring.
     */ 
    public float getDamping() {
        return JniConstraintType.dampedSpringGetDamping(nativeAddress);
    }

    /**
     * Set the damping of the spring.
     */ 
    public void setDamping(float damping) {
        JniConstraintType.dampedSpringSetDamping(nativeAddress, damping);
    }

    /**
     * Set the damping of the spring.
     */ 
    @SuppressWarnings("deprecation")
    public void setSpringForceFunc(CpDampedSpringForceFunc func) {
        this.dampedSpringForceFunc = func;
        JniConstraintType.dampedRotarySpringSetSpringTorqueFunc(nativeAddress, InternalChipmunk.getDampedSpringForceFunc());
    }

}
