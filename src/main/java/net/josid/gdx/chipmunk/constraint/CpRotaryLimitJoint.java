package net.josid.gdx.chipmunk.constraint;

import net.josid.gdx.chipmunk.Chipmunk;
import net.josid.gdx.chipmunk.CpConstraint;


public class CpRotaryLimitJoint extends CpConstraint {

    CpRotaryLimitJoint(long nativeAddress, Chipmunk chipmunk) {
        super(nativeAddress, chipmunk, Type.RotaryLimitJoint);
    }

    /**
     * Get the minimum distance the joint will maintain between the two anchors.
     */ 
    public float getMin() {
        return JniConstraintType.rotaryLimitJointGetMin(nativeAddress);
    }

    /**
     * Set the minimum distance the joint will maintain between the two anchors.
     */ 
    public void setMin(float min) {
        JniConstraintType.rotaryLimitJointSetMin(nativeAddress, min);
    }

    /**
     * Get the maximum distance the joint will maintain between the two anchors.
     */ 
    public float getMax() {
        return JniConstraintType.rotaryLimitJointGetMax(nativeAddress);
    }

    /**
     * Set the maximum distance the joint will maintain between the two anchors.
     */ 
    public void setMax(float max) {
        JniConstraintType.rotaryLimitJointSetMax(nativeAddress, max);
    }

}
