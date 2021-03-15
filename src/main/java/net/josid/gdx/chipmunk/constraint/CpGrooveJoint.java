package net.josid.gdx.chipmunk.constraint;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.Chipmunk;
import net.josid.gdx.chipmunk.CpConstraint;


public class CpGrooveJoint extends CpConstraint {

    CpGrooveJoint(long nativeAddress, Chipmunk chipmunk) {
        super(nativeAddress, chipmunk, Type.GrooveJoint);
    }

    /**
     * Get the first endpoint of the groove relative to the first body.
     */ 
    public Vector2 getGrooveA(Vector2 out_a) {
        float[] floats4 = getFloats4();
        JniConstraintType.grooveJointGetGrooveA(nativeAddress, floats4);
        return out_a.set(floats4[0], floats4[1]);
    }

    /**
     * Set the first endpoint of the groove relative to the first body.
     */ 
    public void setGrooveA(float x, float y) {
        JniConstraintType.grooveJointSetGrooveA(nativeAddress, x, y);
    }

    /**
     * Get the first endpoint of the groove relative to the first body.
     */ 
    public Vector2 getGrooveB(Vector2 out_b) {
        float[] floats4 = getFloats4();
        JniConstraintType.grooveJointGetGrooveB(nativeAddress, floats4);
        return out_b.set(floats4[0], floats4[1]);
    }

    /**
     * Set the first endpoint of the groove relative to the first body.
     */ 
    public void setGrooveB(float x, float y) {
        JniConstraintType.grooveJointSetGrooveB(nativeAddress, x, y);
    }

    /**
     * Get the location of the second anchor relative to the second body.
     */ 
    public Vector2 getAnchorB(Vector2 out_b) {
        float[] floats4 = getFloats4();
        JniConstraintType.grooveJointGetAnchorB(nativeAddress, floats4);
        return out_b.set(floats4[0], floats4[1]);
    }

    /**
     * Set the location of the second anchor relative to the second body.
     */ 
    public void setAnchorB(float x, float y) {
        JniConstraintType.grooveJointSetAnchorB(nativeAddress, x, y);
    }

}
