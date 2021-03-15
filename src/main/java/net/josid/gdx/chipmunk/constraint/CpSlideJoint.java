package net.josid.gdx.chipmunk.constraint;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.Chipmunk;
import net.josid.gdx.chipmunk.CpConstraint;


public class CpSlideJoint extends CpConstraint {

    protected CpSlideJoint(long nativeAddress, Chipmunk chipmunk) {
        super(nativeAddress, chipmunk, Type.SlideJoint);
    }

    /**
     * Get the location of the first anchor relative to the first body.
     */ 
    public Vector2 getAnchorA(Vector2 out_a) {
        float[] floats4 = getFloats4();
        JniConstraintType.slideJointGetAnchorA(nativeAddress, floats4);
        return out_a.set(floats4[0], floats4[1]);
    }

    /**
     * Set the location of the first anchor relative to the first body.
     */ 
    public void setAnchorA(float x, float y) {
        JniConstraintType.slideJointSetAnchorA(nativeAddress, x, y);
    }

    /**
     * Get the location of the second anchor relative to the second body.
     */ 
    public Vector2 getAnchorB(Vector2 out_b) {
        float[] floats4 = getFloats4();
        JniConstraintType.slideJointGetAnchorB(nativeAddress, floats4);
        return out_b.set(floats4[0], floats4[1]);
    }

    /**
     * Set the location of the second anchor relative to the second body.
     */ 
    public void setAnchorB(float x, float y) {
        JniConstraintType.slideJointSetAnchorB(nativeAddress, x, y);
    }

    /**
     * Get the minimum distance the joint will maintain between the two anchors.
     */ 
    public float getMin() {
        return JniConstraintType.slideJointGetMin(nativeAddress);
    }

    /**
     * Set the minimum distance the joint will maintain between the two anchors.
     */ 
    public void setMin(float min) {
        JniConstraintType.slideJointSetMin(nativeAddress, min);
    }

    /**
     * Get the maximum distance the joint will maintain between the two anchors.
     */ 
    public float getMax() {
        return JniConstraintType.slideJointGetMax(nativeAddress);
    }

    /**
     * Set the maximum distance the joint will maintain between the two anchors.
     */ 
    public void setMax(float max) {
        JniConstraintType.slideJointSetMax(nativeAddress, max);
    }

}
