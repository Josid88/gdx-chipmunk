package net.josid.gdx.chipmunk;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.collision.CpBodiesPair;
import net.josid.gdx.chipmunk.collision.CpShapesPair;
import net.josid.gdx.chipmunk.contact.CpContactPointSet;

public class CpArbiter {

    public final Chipmunk chipmunk;
    private long nativeAddress;
    private CpSpace space;
    final CpShapesPair shapes = new CpShapesPair();
    final CpBodiesPair bodies = new CpBodiesPair();

    public CpArbiter(Chipmunk chipmunk) {
        this.chipmunk = chipmunk;
    }

    CpArbiter init(long nativeAddress, CpSpace space) {
        this.nativeAddress = nativeAddress;
        this.space = space;
        return this;
    }


    /**
     * Get the restitution (elasticity) that will be applied to the pair of colliding objects.
     */ 
    public float getRestitution() {
        return JniArbiter.getRestitution(nativeAddress);
    }

    /**
     * Override the restitution (elasticity) that will be applied to the pair of colliding objects.
     */ 
    public void setRestitution(float restitution) {
        JniArbiter.setRestitution(nativeAddress, restitution);
    }

    /**
     * Get the friction coefficient that will be applied to the pair of colliding objects.
     */ 
    public float getFriction() {
        return JniArbiter.getFriction(nativeAddress);
    }

    /**
     * Override the friction coefficient that will be applied to the pair of colliding objects.
     */ 
    public void setFriction(float friction) {
        JniArbiter.setFriction(nativeAddress, friction);
    }

    /**
     * Surface velocity
     */ 
    public Vector2 getSurfaceVelocity(Vector2 out) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniArbiter.getSurfaceVelocity(nativeAddress, floats4);
        return out.set(floats4[0], floats4[1]);
    }

    /**
     * Surface velocity
     */ 
    public void setSurfaceVelocity(float x, float y) {
        JniArbiter.setSurfaceVelocity(nativeAddress, x, y);
    }

    /**
     * Calculate the total impulse including the friction that was applied by this arbiter.
     * This function should only be called from a post-solve, post-step or cpBodyEachArbiter callback.
     */ 
    public Vector2 totalImpulse(Vector2 out) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniArbiter.totalImpulse(nativeAddress, floats4);
        return out.set(floats4[0], floats4[1]);
    }

    /**
     * Calculate the amount of energy lost in a collision including static, but not dynamic friction.
     * This function should only be called from a post-solve, post-step or cpBodyEachArbiter callback.
     */ 
    public float totalKE() {
        return JniArbiter.totalKE(nativeAddress);
    }

    /**
     * Mark a collision pair to be ignored until the two objects separate.
     * Pre-solve and post-solve callbacks will not be called, but the separate callback will be called.
     */ 
    public boolean ignore() {
        return JniArbiter.ignore(nativeAddress);
    }

    /**
     * Return the colliding shapes involved for this arbiter.
     * The order of their cpSpace.collision_type values will match the order set when the collision handler was registered.
     */ 
    public CpShapesPair getShapes() {
        long[] longs4 = chipmunk.arrays.getlongs4(space);
        JniArbiter.getShapes(nativeAddress, longs4);
        return shapes.set(chipmunk.registry.getShape(longs4[0]), chipmunk.registry.getShape(longs4[1]));
    }

    /**
     * A macro shortcut for defining and retrieving the shapes from an arbiter.
     * Return the colliding bodies involved for this arbiter.
     * The order of the cpSpace.collision_type the bodies are associated with values will matchthe order set when the collision handler was registered.
     * @return 
     */ 
    public CpBodiesPair getBodies() {
        long[] longs4 = chipmunk.arrays.getlongs4(space);
        JniArbiter.getBodies(nativeAddress, longs4);
        return bodies.set(chipmunk.registry.getBody(longs4[0]), chipmunk.registry.getBody(longs4[1]));
    }

    /**
     * Return a contact set from an arbiter.
     */ 
    public CpContactPointSet getContactPointSet(CpContactPointSet out) {
        float[] floats16 = chipmunk.arrays.getFloats16(space);
        int count = JniArbiter.getContactPointSet(nativeAddress, floats16);
        return out.set(count, floats16);
    }

    /**
     * Replace the contact point set for an arbiter.This can be a very powerful feature, but use it with caution!
     */ 
    public void setContactPointSet(CpContactPointSet pointSet) {
        float[] floats16 = chipmunk.arrays.getFloats16(space);
        int count = pointSet.toFloats(floats16);
        JniArbiter.setContactPointSet(nativeAddress, count, floats16);
    }

    /**
     * Returns true if this is the first step a pair of objects started colliding.
     */ 
    public boolean isFirstContact() {
        return JniArbiter.isFirstContact(nativeAddress);
    }

    /**
     * Returns true if the separate callback is due to a shape being removed from the space.
     */ 
    public boolean isRemoval() {
        return JniArbiter.isRemoval(nativeAddress);
    }

    /**
     * Get the number of contact points for this arbiter.
     */ 
    public int getCount() {
        return JniArbiter.getCount(nativeAddress);
    }

    /**
     * Get the normal of the collision.
     */ 
    public Vector2 getNormal(Vector2 out) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniArbiter.getNormal(nativeAddress, floats4);
        return out.set(floats4[0], floats4[1]);
    }

    /**
     * Get the position of the @c ith contact point on the surface of the first shape.
     */ 
    public Vector2 getPointA(int i, Vector2 out) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniArbiter.getPointA(nativeAddress, i, floats4);
        return out.set(floats4[0], floats4[1]);
    }

    /**
     * Get the position of the @c ith contact point on the surface of the second shape.
     */ 
    public Vector2 getPointB(int i, Vector2 out) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniArbiter.getPointB(nativeAddress, i, floats4);
        return out.set(floats4[0], floats4[1]);
    }

    /**
     * Get the depth of the @c ith contact point.
     */ 
    public float getDepth(int i) {
        return JniArbiter.getDepth(nativeAddress, i);
    }

    /**
     * If you want a custom callback to invoke the wildcard callback for the first collision type, you must call this function explicitly.
     * You must decide how to handle the wildcard's return value since it may disagree with the other wildcard handler's return value or your own.
     */ 
    public boolean callWildcardBeginA() {
        return JniArbiter.callWildcardBeginA(nativeAddress, space.nativeAddress);
    }

    /**
     * If you want a custom callback to invoke the wildcard callback for the second collision type, you must call this function explicitly.
     * You must decide how to handle the wildcard's return value since it may disagree with the other wildcard handler's return value or your own.
     */ 
    public boolean callWildcardBeginB() {
        return JniArbiter.callWildcardBeginB(nativeAddress, space.nativeAddress);
    }

    /**
     * If you want a custom callback to invoke the wildcard callback for the first collision type, you must call this function explicitly.You must decide how to handle the wildcard's return value since it may disagree with the other wildcard handler's return value or your own.
     */ 
    public boolean callWildcardPreSolveA() {
        return JniArbiter.callWildcardPreSolveA(nativeAddress, space.nativeAddress);
    }

    /**
     * If you want a custom callback to invoke the wildcard callback for the second collision type, you must call this function explicitly.You must decide how to handle the wildcard's return value since it may disagree with the other wildcard handler's return value or your own.
     */ 
    public boolean callWildcardPreSolveB() {
        return JniArbiter.callWildcardPreSolveB(nativeAddress, space.nativeAddress);
    }

    /**
     * If you want a custom callback to invoke the wildcard callback for the first collision type, you must call this function explicitly.
     */ 
    public void callWildcardPostSolveA() {
        JniArbiter.callWildcardPostSolveA(nativeAddress, space.nativeAddress);
    }

    /**
     * If you want a custom callback to invoke the wildcard callback for the second collision type, you must call this function explicitly.
     */ 
    public void callWildcardPostSolveB() {
        JniArbiter.callWildcardPostSolveB(nativeAddress, space.nativeAddress);
    }

    /**
     * If you want a custom callback to invoke the wildcard callback for the first collision type, you must call this function explicitly.
     */ 
    public void callWildcardSeparateA() {
        JniArbiter.callWildcardSeparateA(nativeAddress, space.nativeAddress);
    }

    /**
     * If you want a custom callback to invoke the wildcard callback for the second collision type, you must call this function explicitly.
     */ 
    public void callWildcardSeparateB() {
        JniArbiter.callWildcardSeparateB(nativeAddress, space.nativeAddress);
    }

}
