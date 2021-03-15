package net.josid.gdx.chipmunk.constraint;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.Chipmunk;
import net.josid.gdx.chipmunk.CpConstraint;


public class CpPinJoint extends CpConstraint {

    CpPinJoint(long nativeAddress, Chipmunk chipmunk) {
        super(nativeAddress, chipmunk, Type.PinJoint);
    }

    /**
     * Get the location of the first anchor relative to the first body.
     */ 
    public Vector2 getAnchorA(Vector2 out_a) {
        float[] floats4 = getFloats4();
        JniConstraintType.pinJointGetAnchorA(nativeAddress, floats4);
        return out_a.set(floats4[0], floats4[1]);
    }

    /**
     * Set the location of the first anchor relative to the first body.
     */ 
    public void setAnchorA(float x, float y) {
        JniConstraintType.pinJointSetAnchorA(nativeAddress, x, y);
    }

    /**
     * Get the location of the second anchor relative to the second body.
     */ 
    public Vector2 getAnchorB(Vector2 out_b) {
        float[] floats4 = getFloats4();
        JniConstraintType.pinJointGetAnchorB(nativeAddress, floats4);
        return out_b.set(floats4[0], floats4[1]);
    }

    /**
     * Set the location of the second anchor relative to the second body.
     */ 
    public void setAnchorB(float x, float y) {
        JniConstraintType.pinJointSetAnchorB(nativeAddress, x, y);
    }

    /**
     * Get the distance the joint will maintain between the two anchors.
     * @return 
     */ 
    public float getDist() {
        return JniConstraintType.pinJointGetDist(nativeAddress);
    }

    /**
     * Set the distance the joint will maintain between the two anchors.
     * @param dist
     */ 
    public void setDist(float dist) {
        JniConstraintType.pinJointSetDist(nativeAddress, dist);
    }

}
