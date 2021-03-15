package net.josid.gdx.chipmunk;

import net.josid.gdx.chipmunk.callback.CpConstraintSolverFunc;


public abstract class CpConstraint {

    protected final long nativeAddress;
    public final Chipmunk chipmunk;
    public final Type type;
    protected CpBody bodyA;
    protected CpBody bodyB;
    CpSpace space;
    private Object userData;

    CpConstraintSolverFunc preSolveFunc;
    CpConstraintSolverFunc postSolveFunc;

    protected CpConstraint(long nativeAddress, Chipmunk chipmunk, Type type) {
        this.nativeAddress = nativeAddress;
        this.chipmunk = chipmunk;
        this.type = type;
    }

    protected void reset() {
        bodyA = null;
        bodyB = null;
        space = null;
        userData = null;

        preSolveFunc = null;
        postSolveFunc = null;
    }

    public Object getUserData() {
        return userData;
    }

    public void setUserData(Object userData) {
        this.userData = userData;
    }

    void setBodies(CpBody a, CpBody b) {
        bodyA = a;
        bodyB = b;
        if (null != a)
            a.constraints.add(this);
        if (null != b)
            b.constraints.add(this);
    }

    protected float[] getFloats4() {
        return chipmunk.arrays.getFloats4(space);
    }

    /**
     * Get the maximum force that this constraint is allowed to use.
     */ 
    public float getMaxForce() {
        return JniConstraint.getMaxForce(nativeAddress);
    }

    /**
     * Set the maximum force that this constraint is allowed to use. (defaults to INFINITY)
     */ 
    public void setMaxForce(float maxForce) {
        JniConstraint.setMaxForce(nativeAddress, maxForce);
    }

    /**
     * Get rate at which joint error is corrected.
     */ 
    public float getErrorBias() {
        return JniConstraint.getErrorBias(nativeAddress);
    }

    /**
     * Set rate at which joint error is corrected.Defaults to pow(1.0 - 0.1, 60.0) meaning that it willcorrect 10% of the error every 1/60th of a second.
     */ 
    public void setErrorBias(float errorBias) {
        JniConstraint.setErrorBias(nativeAddress, errorBias);
    }

    /**
     * Get the maximum rate at which joint error is corrected.
     */ 
    public float getMaxBias() {
        return JniConstraint.getMaxBias(nativeAddress);
    }

    /**
     * Set the maximum rate at which joint error is corrected. (defaults to INFINITY)
     */ 
    public void setMaxBias(float maxBias) {
        JniConstraint.setMaxBias(nativeAddress, maxBias);
    }

    /**
     * Get if the two bodies connected by the constraint are allowed to collide or not.
     */ 
    public boolean getCollideBodies() {
        return JniConstraint.getCollideBodies(nativeAddress);
    }

    /**
     * Set if the two bodies connected by the constraint are allowed to collide or not. (defaults to cpFalse)
     * @param collideBodies
     */ 
    public void setCollideBodies(boolean collideBodies) {
        JniConstraint.setCollideBodies(nativeAddress, collideBodies);
    }

    /**
     * Set the pre-solve function that is called before the solver runs.
     */ 
    public void setPreSolveFunc(CpConstraintSolverFunc func) {
        preSolveFunc = func;
        JniConstraint.setPreSolveFunc(nativeAddress, JniChipmunk.constraintPreSolveJniFunc);
    }

    /**
     * Set the post-solve function that is called before the solver runs.
     */ 
    public void setPostSolveFunc(CpConstraintSolverFunc func) {
        postSolveFunc = func;
        JniConstraint.setPostSolveFunc(nativeAddress, JniChipmunk.constraintPreSolveJniFunc);
    }

    /**
     * Get the last impulse applied by this constraint.
     */ 
    public float getImpulse() {
        return JniConstraint.getImpulse(nativeAddress);
    }


    public static enum Type {
        DampedRotarySpring, DampedSpring, GearJoint, GrooveJoint, PinJoint,
        PivotJoint, RatchetJoint, RotaryLimitJoint, SimpleMotor, SlideJoint
    }

}
