package net.josid.gdx.chipmunk.constraint;

import net.josid.gdx.chipmunk.Chipmunk;
import net.josid.gdx.chipmunk.CpConstraint;


public class CpGearJoint extends CpConstraint {

    CpGearJoint(long nativeAddress, Chipmunk chipmunk) {
        super(nativeAddress, chipmunk, Type.GearJoint);
    }

    /**
     * Get the phase offset of the gears.
     */ 
    public float getPhase() {
        return JniConstraintType.gearJointGetPhase(nativeAddress);
    }

    /**
     * Set the phase offset of the gears.
     */ 
    public void setPhase(float phase) {
        JniConstraintType.gearJointSetPhase(nativeAddress, phase);
    }

    /**
     * Get the angular distance of each ratchet.
     */ 
    public float getRatio() {
        return JniConstraintType.gearJointGetRatio(nativeAddress);
    }

    /**
     * Set the ratio of a gear joint.
     */ 
    public void setRatio(float ratio) {
        JniConstraintType.gearJointSetRatio(nativeAddress, ratio);
    }

}
