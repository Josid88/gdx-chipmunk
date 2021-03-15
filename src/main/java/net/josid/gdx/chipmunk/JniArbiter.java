package net.josid.gdx.chipmunk;

//@off
/*JNI
#include <chipmunk/chipmunk.h>
#include <chipmunk/gdx/gdxChipmunkUtils.h>
*/


class JniArbiter {

    /**
     * Get the restitution (elasticity) that will be applied to the pair of colliding objects.
     * @return 
     */ 
    public static native float getRestitution(long arb); /*
        return cpArbiterGetRestitution((cpArbiter*)arb);
    */

    /**
     * Override the restitution (elasticity) that will be applied to the pair of colliding objects.
     * @param restitution
     */ 
    public static native void setRestitution(long arb, float restitution); /*
        cpArbiterSetRestitution((cpArbiter*)arb, restitution);
    */

    /**
     * Get the friction coefficient that will be applied to the pair of colliding objects.
     * @return 
     */ 
    public static native float getFriction(long arb); /*
        return cpArbiterGetFriction((cpArbiter*)arb);
    */

    /**
     * Override the friction coefficient that will be applied to the pair of colliding objects.
     * @param friction
     */ 
    public static native void setFriction(long arb, float friction); /*
        cpArbiterSetFriction((cpArbiter*)arb, friction);
    */

    /**
     * 
     * @return 
     */ 
    public static native void getSurfaceVelocity(long arb, float[] out_floats); /*
        cpVect v = cpArbiterGetSurfaceVelocity((cpArbiter*)arb);
        gdxCpVectToFloats(&v, out_floats);
    */

    /**
     * 
     * @param vr
     */ 
    public static native void setSurfaceVelocity(long arb, float vr_x, float vr_y); /*
        cpArbiterSetSurfaceVelocity((cpArbiter*)arb, cpv(vr_x, vr_y));
    */

    /**
     * Get the user data pointer associated with this pair of colliding objects.
     * @return 
     */ 
    public static native long getUserData(long arb); /*
        return (jlong) cpArbiterGetUserData((cpArbiter*)arb);
    */

    /**
     * Set a user data point associated with this pair of colliding objects.
     * If you need to perform any cleanup for this pointer, you must do it yourself, in the separate callback for instance.
     * @param userData
     */ 
    public static native void setUserData(long arb, long userData); /*
        cpArbiterSetUserData((cpArbiter*)arb, (cpDataPointer)userData);
    */

    /**
     * Calculate the total impulse including the friction that was applied by this arbiter.
     * This function should only be called from a post-solve, post-step or cpBodyEachArbiter callback.
     * @return 
     */ 
    public static native void totalImpulse(long arb, float[] out_floats); /*
        cpVect v = cpArbiterTotalImpulse((cpArbiter*)arb);
        gdxCpVectToFloats(&v, out_floats);
    */

    /**
     * Calculate the amount of energy lost in a collision including static, but not dynamic friction.
     * This function should only be called from a post-solve, post-step or cpBodyEachArbiter callback.
     * @return 
     */ 
    public static native float totalKE(long arb); /*
        return cpArbiterTotalKE((cpArbiter*)arb);
    */

    /**
     * Mark a collision pair to be ignored until the two objects separate.Pre-solve and post-solve callbacks will not be called, but the separate callback will be called.
     * @return 
     */ 
    public static native boolean ignore(long arb); /*
        return cpArbiterIgnore((cpArbiter*)arb);
    */

    /**
     * Return the colliding shapes involved for this arbiter.
     * The order of their cpSpace.collision_type values will match the order set when the collision handler was registered.
     * @param a
     * @param b
     */ 
    public static native void getShapes(long arb, long[] out_shapes); /*
        cpShape* a = 0;
        cpShape* b = 0;
        cpArbiterGetShapes((cpArbiter*)arb, &a, &b);
        out_shapes[0] = (jlong) a;
        out_shapes[1] = (jlong) b;
    */

    /**
     * A macro shortcut for defining and retrieving the shapes from an arbiter.
     * Return the colliding bodies involved for this arbiter.
     * The order of the cpSpace.collision_type the bodies are associated with values will matchthe order set when the collision handler was registered.
     * @param a
     * @param b
     */ 
    public static native void getBodies(long arb, long[] out_bodies); /*
        cpBody* a = 0;
        cpBody* b = 0;
        cpArbiterGetBodies((cpArbiter*)arb, &a, &b);
        out_bodies[0] = (jlong) a;
        out_bodies[1] = (jlong) b;
    */

    /**
     * Return a contact set from an arbiter.
     * @return 
     */ 
    public static native int getContactPointSet(long arb, float[] out_floats12); /*
        cpContactPointSet contactPointSet = cpArbiterGetContactPointSet((cpArbiter*)arb);
        return gdxCpContactPointSetToFloats(&contactPointSet, out_floats12);
    */

    /**
     * Replace the contact point set for an arbiter.This can be a very powerful feature, but use it with caution!
     * @param set
     */ 
    public static native void setContactPointSet(long arb, int count, float[] pointSet); /*
        cpContactPointSet contactPointSet;
        gdxFloatsToCpContactPointSet(count, pointSet, &contactPointSet);
        cpArbiterSetContactPointSet((cpArbiter*)arb, &contactPointSet);
    */

    /**
     * Returns true if this is the first step a pair of objects started colliding.
     * @return 
     */ 
    public static native boolean isFirstContact(long arb); /*
        return cpArbiterIsFirstContact((cpArbiter*)arb);
    */

    /**
     * Returns true if the separate callback is due to a shape being removed from the space.
     * @return 
     */ 
    public static native boolean isRemoval(long arb); /*
        return cpArbiterIsRemoval((cpArbiter*)arb);
    */

    /**
     * Get the number of contact points for this arbiter.
     * @return 
     */ 
    public static native int getCount(long arb); /*
        return cpArbiterGetCount((cpArbiter*)arb);
    */

    /**
     * Get the normal of the collision.
     * @return 
     */ 
    public static native void getNormal(long arb, float[] out_floats); /*
        cpVect v = cpArbiterGetNormal((cpArbiter*)arb);
        gdxCpVectToFloats(&v, out_floats);
    */

    /**
     * Get the position of the @c ith contact point on the surface of the first shape.
     * @return 
     * @param i
     */ 
    public static native void getPointA(long arb, int i, float[] out_floats); /*
        cpVect v = cpArbiterGetPointA((cpArbiter*)arb, i);
        gdxCpVectToFloats(&v, out_floats);
    */

    /**
     * Get the position of the @c ith contact point on the surface of the second shape.
     * @return 
     * @param i
     */ 
    public static native void getPointB(long arb, int i, float[] out_floats); /*
        cpVect v = cpArbiterGetPointB((cpArbiter*)arb, i);
        gdxCpVectToFloats(&v, out_floats);
    */

    /**
     * Get the depth of the @c ith contact point.
     * @return 
     * @param i
     */ 
    public static native float getDepth(long arb, int i); /*
        return cpArbiterGetDepth((cpArbiter*)arb, i);
    */

    /**
     * If you want a custom callback to invoke the wildcard callback for the first collision type, you must call this function explicitly.
     * You must decide how to handle the wildcard's return value since it may disagree with the other wildcard handler's return value or your own.
     * @return 
     * @param space
     */ 
    public static native boolean callWildcardBeginA(long arb, long space); /*
        return cpArbiterCallWildcardBeginA((cpArbiter*)arb, (cpSpace*)space);
    */

    /**
     * If you want a custom callback to invoke the wildcard callback for the second collision type, you must call this function explicitly.
     * You must decide how to handle the wildcard's return value since it may disagree with the other wildcard handler's return value or your own.
     * @return 
     * @param space
     */ 
    public static native boolean callWildcardBeginB(long arb, long space); /*
        return cpArbiterCallWildcardBeginB((cpArbiter*)arb, (cpSpace*)space);
    */

    /**
     * If you want a custom callback to invoke the wildcard callback for the first collision type, you must call this function explicitly.You must decide how to handle the wildcard's return value since it may disagree with the other wildcard handler's return value or your own.
     * @return 
     * @param space
     */ 
    public static native boolean callWildcardPreSolveA(long arb, long space); /*
        return cpArbiterCallWildcardPreSolveA((cpArbiter*)arb, (cpSpace*)space);
    */

    /**
     * If you want a custom callback to invoke the wildcard callback for the second collision type, you must call this function explicitly.You must decide how to handle the wildcard's return value since it may disagree with the other wildcard handler's return value or your own.
     * @return 
     * @param space
     */ 
    public static native boolean callWildcardPreSolveB(long arb, long space); /*
        return cpArbiterCallWildcardPreSolveB((cpArbiter*)arb, (cpSpace*)space);
    */

    /**
     * If you want a custom callback to invoke the wildcard callback for the first collision type, you must call this function explicitly.
     * @param space
     */ 
    public static native void callWildcardPostSolveA(long arb, long space); /*
        cpArbiterCallWildcardPostSolveA((cpArbiter*)arb, (cpSpace*)space);
    */

    /**
     * If you want a custom callback to invoke the wildcard callback for the second collision type, you must call this function explicitly.
     * @param space
     */ 
    public static native void callWildcardPostSolveB(long arb, long space); /*
        cpArbiterCallWildcardPostSolveB((cpArbiter*)arb, (cpSpace*)space);
    */

    /**
     * If you want a custom callback to invoke the wildcard callback for the first collision type, you must call this function explicitly.
     * @param space
     */ 
    public static native void callWildcardSeparateA(long arb, long space); /*
        cpArbiterCallWildcardSeparateA((cpArbiter*)arb, (cpSpace*)space);
    */

    /**
     * If you want a custom callback to invoke the wildcard callback for the second collision type, you must call this function explicitly.
     * @param space
     */ 
    public static native void callWildcardSeparateB(long arb, long space); /*
        cpArbiterCallWildcardSeparateB((cpArbiter*)arb, (cpSpace*)space);
    */

}
