package net.josid.gdx.chipmunk.constraint;

import net.josid.gdx.chipmunk.Chipmunk;
import net.josid.gdx.chipmunk.CpConstraint;


public class CpRatchetJoint extends CpConstraint{

    CpRatchetJoint(long nativeAddress, Chipmunk chipmunk) {
        super(nativeAddress, chipmunk, Type.RatchetJoint);
    }

    /**
     * Get the angle of the current ratchet tooth.
     */ 
    public float getAngle() {
        return JniConstraintType.ratchetJointGetAngle(nativeAddress);
    }

    /**
     * Set the angle of the current ratchet tooth.
     */ 
    public void setAngle(float angle) {
        JniConstraintType.ratchetJointSetAngle(nativeAddress, angle);
    }

    /**
     * Get the phase offset of the ratchet.
     */ 
    public float getPhase() {
        return JniConstraintType.ratchetJointGetPhase(nativeAddress);
    }

    /**
     * Get the phase offset of the ratchet.
     */ 
    public void setPhase(float phase) {
        JniConstraintType.ratchetJointSetPhase(nativeAddress, phase);
    }

    /**
     * Get the angular distance of each ratchet.
     */ 
    public float getRatchet() {
        return JniConstraintType.ratchetJointGetRatchet(nativeAddress);
    }

    /**
     * Set the angular distance of each ratchet.
     */ 
    public void setRatchet(float ratchet) {
        JniConstraintType.ratchetJointSetRatchet(nativeAddress, ratchet);
    }

}
