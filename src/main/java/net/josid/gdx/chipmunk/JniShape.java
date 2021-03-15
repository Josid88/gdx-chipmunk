package net.josid.gdx.chipmunk;

//@off
/*JNI
#include <chipmunk/chipmunk.h>
#include <chipmunk/gdx/gdxChipmunkUtils.h>
*/


final class JniShape {

    private JniShape() {}

    /**
     * Destroy a shape.
     */ 
    static native void destroy(long shape); /*
        return cpShapeDestroy((cpShape*)shape);
    */

    /**
     * Destroy and Free a shape.
     */ 
    static native void free(long shape); /*
        return cpShapeFree((cpShape*)shape);
    */

    /**
     * Update, cache and return the bounding box of a shape based on the body it's attached to.
     * @return 
     */ 
    static native void cacheBB(long shape, float[] out_floats4); /*
        cpBB bb = cpShapeCacheBB((cpShape*)shape);
        gdxCpBBToFloats( &bb, out_floats4);
    */

    /**
     * Update, cache and return the bounding box of a shape with an explicit transformation.
     * @return 
     * @param transform
     */ 
    static native void update(long shape, float offset_x, float offset_y, float angle, float[] out_floats4); /*
        cpBB bb = cpShapeUpdate((cpShape*)shape, gdxCpTransform(offset_x, offset_y, angle));
        gdxCpBBToFloats( &bb, out_floats4);
    */

    /**
     * Perform a nearest point query.
     * It finds the closest point on the surface of shape to a specific point.
     * @return distance between the points. A negative distance means the point is inside the shape.
     * @param p
     * @param out
     */ 
    static native float pointQuery(long shape, float p_x, float p_y, float[] out_floats5); /*
        cpPointQueryInfo out;
        cpFloat distance = cpShapePointQuery((cpShape*)shape, cpv(p_x, p_y), &out);
        gdxCpPointQueryInfoToFloats(&out, out_floats5);
        return distance;
    */

    /**
     * Perform a segment query against a shape.
     * @return 
     * @param a
     * @param b
     * @param radius
     * @param info
     */ 
    static native boolean segmentQuery(long shape, float a_x, float a_y,
            float b_x, float b_y, float radius, float[] out_floats8); /*
        cpSegmentQueryInfo out;
        cpBool collides = cpShapeSegmentQuery((cpShape*)shape, cpv(a_x, a_y), cpv(b_x, b_y), radius, &out);
        gdxCpSegmentQueryInfoToFloats(&out, out_floats8); 
        return collides;
    */

    /**
     * Return contact information about two shapes.
     * @return 
     */
    static native int collide(long shapeA, long shapeB, float[] out_floats12); /*
        cpContactPointSet contactPointSet = cpShapesCollide((cpShape*)shapeA, (cpShape*)shapeB);
        return gdxCpContactPointSetToFloats(&contactPointSet, out_floats12);
    */

    /**
     * The cpSpace this body is added to.
     * @return 
     */ 
    static native long getSpace(long shape); /*
        return (jlong) cpShapeGetSpace((cpShape*)shape);
    */

    /**
     * The cpBody this shape is connected to.
     * @return 
     */ 
    static native long getBody(long shape); /*
        return (jlong) cpShapeGetBody((cpShape*)shape);
    */

    /**
     * Set the cpBody this shape is connected to.
     * Can only be used if the shape is not currently added to a space.
     * @param body
     */ 
    static native void setBody(long shape, long body); /*
        return cpShapeSetBody((cpShape*)shape, (cpBody*)body);
    */

    /**
     * Get the mass of the shape if you are having Chipmunk calculate mass properties for you.
     * @return 
     */ 
    static native float getMass(long shape); /*
        return cpShapeGetMass((cpShape*)shape);
    */

    /**
     * Set the mass of this shape to have Chipmunk calculate mass properties for you.
     * @param mass
     */ 
    static native void setMass(long shape, float mass); /*
        return cpShapeSetMass((cpShape*)shape, mass);
    */

    /**
     * Get the density of the shape if you are having Chipmunk calculate mass properties for you.
     * @return 
     */ 
    static native float getDensity(long shape); /*
        return cpShapeGetDensity((cpShape*)shape);
    */

    /**
     * Set the density  of this shape to have Chipmunk calculate mass properties for you.
     * @param density
     */ 
    static native void setDensity(long shape, float density); /*
        return cpShapeSetDensity((cpShape*)shape, density);
    */

    /**
     * Get the calculated moment of inertia for this shape.
     * @return 
     */ 
    static native float getMoment(long shape); /*
        return cpShapeGetMoment((cpShape*)shape);
    */

    /**
     * Get the calculated area of this shape.
     * @return 
     */ 
    static native float getArea(long shape); /*
        return cpShapeGetArea((cpShape*)shape);
    */

    /**
     * Get the centroid of this shape.
     * @return 
     */ 
    static native void getCenterOfGravity(long shape, float[] out_floats2); /*
        cpVect v = cpShapeGetCenterOfGravity((cpShape*)shape);
        gdxCpVectToFloats( &v, out_floats2 );
    */

    /**
     * Get the bounding box that contains the shape given it's current position and angle.
     * @return 
     */ 
    static native void getBB(long shape, float[] out_floats4); /*
        cpBB bb = cpShapeGetBB((cpShape*)shape);
        gdxCpBBToFloats( &bb, out_floats4 );
    */

    /**
     * Get if the shape is set to be a sensor or not.
     * @return 
     */ 
    static native boolean getSensor(long shape); /*
        return cpShapeGetSensor((cpShape*)shape);
    */

    /**
     * Set if the shape is a sensor or not.
     * @param sensor
     */ 
    static native void setSensor(long shape, boolean sensor); /*
        return cpShapeSetSensor((cpShape*)shape, sensor);
    */

    /**
     * Get the elasticity of this shape.
     * @return 
     */ 
    static native float getElasticity(long shape); /*
        return cpShapeGetElasticity((cpShape*)shape);
    */

    /**
     * Set the elasticity of this shape.
     * @param elasticity
     */ 
    static native void setElasticity(long shape, float elasticity); /*
        return cpShapeSetElasticity((cpShape*)shape, elasticity);
    */

    /**
     * Get the friction of this shape.
     * @return 
     */ 
    static native float getFriction(long shape); /*
        return cpShapeGetFriction((cpShape*)shape);
    */

    /**
     * Set the friction of this shape.
     * @param friction
     */ 
    static native void setFriction(long shape, float friction); /*
        return cpShapeSetFriction((cpShape*)shape, friction);
    */

    /**
     * Get the surface velocity of this shape.
     * @return 
     */ 
    static native void getSurfaceVelocity(long shape, float[] out_floats2); /*
        cpVect v = cpShapeGetSurfaceVelocity((cpShape*)shape);
        gdxCpVectToFloats( &v, out_floats2 );
    */

    /**
     * Set the surface velocity of this shape.
     * @param surfaceVelocity
     */ 
    static native void setSurfaceVelocity(long shape, float surfaceVelocity_x, float surfaceVelocity_y); /*
        return cpShapeSetSurfaceVelocity((cpShape*)shape, cpv(surfaceVelocity_x, surfaceVelocity_y));
    */

    /**
     * Get the user definable data pointer of this shape.
     * @return 
     */ 
    static native long getUserData(long shape); /*
        return (jlong) cpShapeGetUserData((cpShape*)shape);
    */

    /**
     * Set the user definable data pointer of this shape.
     * @param userData
     */ 
    static native void setUserData(long shape, long userData); /*
        return cpShapeSetUserData((cpShape*)shape, (cpDataPointer)userData);
    */

    /**
     * Set the collision type of this shape.
     * @return 
     */ 
    static native long getCollisionType(long shape); /*
        return (jlong) cpShapeGetCollisionType((cpShape*)shape);
    */

    /**
     * Get the collision type of this shape.
     * @param collisionType
     */ 
    static native void setCollisionType(long shape, long collisionType); /*
        return cpShapeSetCollisionType((cpShape*)shape, (cpCollisionType) collisionType);
    */

    /**
     * Get the collision filtering parameters of this shape.
     * @return 
     */ 
    static native void getFilter(long shape, int[] out_ints3); /*
        gdxCpShapeFilterToInts( cpShapeGetFilter((cpShape*)shape), out_ints3 );
    */

    /**
     * Set the collision filtering parameters of this shape.
     * @param filter
     */ 
    static native void setFilter(long shape, int group, int categories, int mask); /*
        return cpShapeSetFilter((cpShape*)shape, cpShapeFilterNew(group, categories, mask));
    */

}
