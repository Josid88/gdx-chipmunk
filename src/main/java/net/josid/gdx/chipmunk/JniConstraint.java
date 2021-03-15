package net.josid.gdx.chipmunk;

//@off
/*JNI
#include <chipmunk/chipmunk.h>
#include <chipmunk/gdx/gdxChipmunkUtils.h>
*/


class JniConstraint {

    public static native void constraintSetOnInit(long address, boolean isMaxForce, float maxForce,
            boolean isErrorBias, float errorBias, boolean isMaxBias, float maxBias, boolean collideBodies); /*
        cpConstraint* constraint = (cpConstraint*)address;
        if (isMaxForce) cpConstraintSetMaxForce(constraint, maxForce);
        if (isErrorBias) cpConstraintSetErrorBias(constraint, errorBias);
        if (isMaxBias) cpConstraintSetMaxBias(constraint, maxBias);
        if (collideBodies) cpConstraintSetCollideBodies(constraint, collideBodies);
    */

    /**
     * Destroy a constraint.
     */ 
    public static native void destroy(long constraint); /*
        cpConstraintDestroy((cpConstraint*)constraint);
    */

    /**
     * Destroy and free a constraint.
     */ 
    public static native void free(long constraint); /*
        cpConstraintFree((cpConstraint*)constraint);
    */

    /**
     * Get the cpSpace this constraint is added to.
     * @return 
     */ 
    public static native long getSpace(long constraint); /*
        return (jlong) cpConstraintGetSpace((cpConstraint*)constraint);
    */

    /**
     * Get the first body the constraint is attached to.
     * @return 
     */ 
    public static native long getBodyA(long constraint); /*
        return (jlong) cpConstraintGetBodyA((cpConstraint*)constraint);
    */

    /**
     * Get the second body the constraint is attached to.
     * @return 
     */ 
    public static native long getBodyB(long constraint); /*
        return (jlong) cpConstraintGetBodyB((cpConstraint*)constraint);
    */

    /**
     * Get the maximum force that this constraint is allowed to use.
     * @return 
     */ 
    public static native float getMaxForce(long constraint); /*
        return cpConstraintGetMaxForce((cpConstraint*)constraint);
    */

    /**
     * Set the maximum force that this constraint is allowed to use. (defaults to INFINITY)
     * @param maxForce
     */ 
    public static native void setMaxForce(long constraint, float maxForce); /*
        cpConstraintSetMaxForce((cpConstraint*)constraint, maxForce);
    */

    /**
     * Get rate at which joint error is corrected.
     * @return 
     */ 
    public static native float getErrorBias(long constraint); /*
        return cpConstraintGetErrorBias((cpConstraint*)constraint);
    */

    /**
     * Set rate at which joint error is corrected.Defaults to pow(1.0 - 0.1, 60.0) meaning that it willcorrect 10% of the error every 1/60th of a second.
     * @param errorBias
     */ 
    public static native void setErrorBias(long constraint, float errorBias); /*
        cpConstraintSetErrorBias((cpConstraint*)constraint, errorBias);
    */

    /**
     * Get the maximum rate at which joint error is corrected.
     * @return 
     */ 
    public static native float getMaxBias(long constraint); /*
        return cpConstraintGetMaxBias((cpConstraint*)constraint);
    */

    /**
     * Set the maximum rate at which joint error is corrected. (defaults to INFINITY)
     * @param maxBias
     */ 
    public static native void setMaxBias(long constraint, float maxBias); /*
        cpConstraintSetMaxBias((cpConstraint*)constraint, maxBias);
    */

    /**
     * Get if the two bodies connected by the constraint are allowed to collide or not.
     * @return 
     */ 
    public static native boolean getCollideBodies(long constraint); /*
        return (jboolean) cpConstraintGetCollideBodies((cpConstraint*)constraint);
    */

    /**
     * Set if the two bodies connected by the constraint are allowed to collide or not. (defaults to cpFalse)
     * @param collideBodies
     */ 
    public static native void setCollideBodies(long constraint, boolean collideBodies); /*
        cpConstraintSetCollideBodies((cpConstraint*)constraint, collideBodies);
    */

    /**
     * Get the pre-solve function that is called before the solver runs.
     * @return 
     */ 
    public static native long  getPreSolveFunc(long constraint); /*
        return (jlong) cpConstraintGetPreSolveFunc((cpConstraint*)constraint);
    */

    /**
     * Set the pre-solve function that is called before the solver runs.
     * @param preSolveFunc
     */ 
    public static native void setPreSolveFunc(long constraint, long preSolveFunc); /*
        cpConstraintSetPreSolveFunc((cpConstraint*)constraint, (cpConstraintPreSolveFunc)preSolveFunc);
    */

    /**
     * Get the post-solve function that is called before the solver runs.
     * @return 
     */ 
    public static native long getPostSolveFunc(long constraint); /*
        return (jlong) cpConstraintGetPostSolveFunc((cpConstraint*)constraint);
    */

    /**
     * Set the post-solve function that is called before the solver runs.
     * @param postSolveFunc
     */ 
    public static native void setPostSolveFunc(long constraint, long postSolveFunc); /*
        cpConstraintSetPostSolveFunc((cpConstraint*)constraint, (cpConstraintPostSolveFunc)postSolveFunc);
    */

    /**
     * Get the user definable data pointer for this constraint
     * @return 
     */ 
    public static native long getUserData(long constraint); /*
        return (jlong) cpConstraintGetUserData((cpConstraint*)constraint);
    */

    /**
     * Set the user definable data pointer for this constraint
     * @param userData
     */ 
    public static native void setUserData(long constraint, long userData); /*
        cpConstraintSetUserData((cpConstraint*)constraint, (cpDataPointer) userData);
    */

    /**
     * Get the last impulse applied by this constraint.
     * @return 
     */ 
    public static native float getImpulse(long constraint); /*
        return cpConstraintGetImpulse((cpConstraint*)constraint);
    */
    
}
