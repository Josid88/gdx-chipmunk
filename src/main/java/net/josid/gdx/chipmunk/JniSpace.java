package net.josid.gdx.chipmunk;

//@off
/*JNI
#include <chipmunk/gdx/gdxStructs.h>
#include <chipmunk/gdx/gdxChipmunkUtils.h>
*/


final class JniSpace {

    // CUSTOM FUNCTIONS

    static native long gdxSpaceNew(long gdxChipmunk); /*
    return (jlong) gdxSpaceNew((GdxChipmunk*)gdxChipmunk);
    */
    static native void gdxSpaceFree(long gdxSpace); /*
        gdxSpaceFree((GdxSpace*)gdxSpace);
    */

    static native void gdxSpaceInit(long spaceAddress, int iterations, float gravityX, float gravityY,
            boolean dampingSet, float damping, float idleSpeedThreshold,
            boolean sleepTimeThresholdSet, float sleepTimeThreshold, boolean collisionSlopSet, float collisionSlop, 
            boolean collisionBiasSet, float collisionBias, long collisionPersistence); /*
        cpSpace* space = (cpSpace*)spaceAddress;
        if (10 != iterations) cpSpaceSetIterations(space, iterations);
        if (0 != gravityX || 0 != gravityY) cpSpaceSetGravity(space, cpv(gravityX, gravityY));
        if (dampingSet) cpSpaceSetDamping(space, damping);
        if (0 != idleSpeedThreshold) cpSpaceSetIdleSpeedThreshold(space, idleSpeedThreshold);
        if (sleepTimeThresholdSet) cpSpaceSetSleepTimeThreshold(space, sleepTimeThreshold);
        if (collisionSlopSet) cpSpaceSetCollisionSlop(space, collisionSlop);
        if (collisionBiasSet) cpSpaceSetCollisionBias(space, collisionBias);
        if (3 != collisionPersistence) cpSpaceSetCollisionPersistence(space, collisionPersistence);
    */


    // CHIPMUNK FUNCTIONS

    private JniSpace() {}
    /**
     * Allocate a cpSpace.
     * @return 
     * @param void
     */ 
    public static native long alloc(); /*
        return (jlong) cpSpaceAlloc();
    */

    /**
     * Initialize a cpSpace.
     * @return 
     */ 
    public static native void init(long space); /*
        cpSpaceInit((cpSpace*)space);
    */

    /**
     * Allocate and initialize a cpSpace.
     * @return 
     * @param void
     */ 
    public static native long newSpace(); /*
        return (jlong) cpSpaceNew();
    */

    /**
     * Destroy a cpSpace.
     */ 
    public static native void destroy(long space); /*
        cpSpaceDestroy((cpSpace*)space);
    */

    /**
     * Destroy and free a cpSpace.
     */ 
    public static native void free(long space); /*
        cpSpaceFree((cpSpace*)space);
    */

    /**
     * Number of iterations to use in the impulse solver to solve contacts and other constraints.
     * @return 
     */ 
    public static native int getIterations(long space); /*
        return cpSpaceGetIterations((cpSpace*)space);
    */

    /**
     * 
     * @param iterations
     */ 
    public static native void setIterations(long space, int iterations); /*
        cpSpaceSetIterations((cpSpace*)space, iterations);
    */

    /**
     * Gravity to pass to rigid bodies when integrating velocity.
     * @return 
     */ 
    public static native void getGravity(long space, float[] out_floats); /*
        cpVect v = cpSpaceGetGravity((cpSpace*)space);
        gdxCpVectToFloats(&v, out_floats);
    */

    /**
     * 
     * @param gravity
     */ 
    public static native void setGravity(long space, float gravity_x, float gravity_y); /*
        cpSpaceSetGravity((cpSpace*)space, cpv(gravity_x, gravity_y));
    */

    /**
     * Damping rate expressed as the fraction of velocity bodies retain each second.A value of 0.9 would mean that each body's velocity will drop 10% per second.The default value is 1.0, meaning no damping is applied.@note This damping value is different than those of cpDampedSpring and cpDampedRotarySpring.
     * @return 
     */ 
    public static native float getDamping(long space); /*
        return cpSpaceGetDamping((cpSpace*)space);
    */

    /**
     * 
     * @param damping
     */ 
    public static native void setDamping(long space, float damping); /*
        cpSpaceSetDamping((cpSpace*)space, damping);
    */

    /**
     * Speed threshold for a body to be considered idle.The default value of 0 means to let the space guess a good threshold based on gravity.
     * @return 
     */ 
    public static native float getIdleSpeedThreshold(long space); /*
        return cpSpaceGetIdleSpeedThreshold((cpSpace*)space);
    */

    /**
     * 
     * @param idleSpeedThreshold
     */ 
    public static native void setIdleSpeedThreshold(long space, float idleSpeedThreshold); /*
        cpSpaceSetIdleSpeedThreshold((cpSpace*)space, idleSpeedThreshold);
    */

    /**
     * Time a group of bodies must remain idle in order to fall asleep.Enabling sleeping also implicitly enables the the contact graph.The default value of INFINITY disables the sleeping algorithm.
     * @return 
     */ 
    public static native float getSleepTimeThreshold(long space); /*
        return cpSpaceGetSleepTimeThreshold((cpSpace*)space);
    */

    /**
     * 
     * @param sleepTimeThreshold
     */ 
    public static native void setSleepTimeThreshold(long space, float sleepTimeThreshold); /*
        cpSpaceSetSleepTimeThreshold((cpSpace*)space, sleepTimeThreshold);
    */

    /**
     * Amount of encouraged penetration between colliding shapes.Used to reduce oscillating contacts and keep the collision cache warm.Defaults to 0.1. If you have poor simulation quality,increase this number as much as possible without allowing visible amounts of overlap.
     * @return 
     */ 
    public static native float getCollisionSlop(long space); /*
        return cpSpaceGetCollisionSlop((cpSpace*)space);
    */

    /**
     * 
     * @param collisionSlop
     */ 
    public static native void setCollisionSlop(long space, float collisionSlop); /*
        cpSpaceSetCollisionSlop((cpSpace*)space, collisionSlop);
    */

    /**
     * Determines how fast overlapping shapes are pushed apart.Expressed as a fraction of the error remaining after each second.Defaults to pow(1.0 - 0.1, 60.0) meaning that Chipmunk fixes 10% of overlap each frame at 60Hz.
     * @return 
     */ 
    public static native float getCollisionBias(long space); /*
        return cpSpaceGetCollisionBias((cpSpace*)space);
    */

    /**
     * 
     * @param collisionBias
     */ 
    public static native void setCollisionBias(long space, float collisionBias); /*
        cpSpaceSetCollisionBias((cpSpace*)space, collisionBias);
    */

    /**
     * Number of frames that contact information should persist.Defaults to 3. There is probably never a reason to change this value.
     * @return 
     */ 
    public static native long getCollisionPersistence(long space); /*
        return (jlong) cpSpaceGetCollisionPersistence((cpSpace*)space);
    */

    /**
     * 
     * @param collisionPersistence
     */ 
    public static native void setCollisionPersistence(long space, long collisionPersistence); /*
        cpSpaceSetCollisionPersistence((cpSpace*)space, (jlong)collisionPersistence);
    */

    /**
     * User definable data pointer.Generally this points to your game's controller or game stateclass so you can access it when given a cpSpace reference in a callback.
     * @return 
     */ 
    public static native long getUserData(long space); /*
        return (jlong) cpSpaceGetUserData((cpSpace*)space);
    */

    /**
     * 
     * @param userData
     */ 
    public static native void setUserData(long space, long userData); /*
        cpSpaceSetUserData((cpSpace*)space, (cpDataPointer)userData);
    */

    /**
     * The Space provided static body for a given cpSpace.This is merely provided for convenience and you are not required to use it.
     * @return 
     */ 
    public static native long getStaticBody(long space); /*
        return (jlong) cpSpaceGetStaticBody((cpSpace*)space);
    */

    /**
     * Returns the current (or most recent) time step used with the given space.Useful from callbacks if your time step is not a compile-time global.
     * @return 
     */ 
    public static native float getCurrentTimeStep(long space); /*
        return cpSpaceGetCurrentTimeStep((cpSpace*)space);
    */

    /**
     * returns true from inside a callback when objects cannot be added/removed.
     * @return 
     */ 
    public static native boolean isLocked(long space); /*
        return (jboolean) cpSpaceIsLocked((cpSpace*)space);
    */

    /**
     * Create or return the existing collision handler that is called for all collisions that are not handled by a more specific collision handler.
     * @return 
     */ 
    public static native long addDefaultCollisionHandler(long space); /*
        return (jlong) cpSpaceAddDefaultCollisionHandler((cpSpace*)space);
    */

    /**
     * Create or return the existing collision handler for the specified pair of collision types.
     * If wildcard handlers are used with either of the collision types, it's the responibility of the custom handler to invoke the wildcard handlers.
     * @return 
     * @param a
     * @param b
     */ 
    public static native long addCollisionHandler(long space, long a, long b); /*
        return (jlong) cpSpaceAddCollisionHandler((cpSpace*)space, (cpCollisionType)a, (cpCollisionType)b);
    */

    /**
     * Create or return the existing wildcard collision handler for the specified type.
     * @return 
     * @param type
     */ 
    public static native long addWildcardHandler(long space, long type); /*
        return (jlong) cpSpaceAddWildcardHandler((cpSpace*)space, (cpCollisionType)type);
    */

    /**
     * Add a collision shape to the simulation.If the shape is attached to a static body, it will be added as a static shape.
     * @return 
     * @param shape
     */ 
    public static native long addShape(long space, long shape); /*
        return (jlong) cpSpaceAddShape((cpSpace*)space, (cpShape*)shape);
    */

    /**
     * Remove a collision shape from the simulation.
     * @param shape
     */ 
    public static native void removeShape(long space, long shape); /*
        cpSpaceRemoveShape((cpSpace*)space, (cpShape*)shape);
    */

    /**
     * Test if a collision shape has been added to the space.
     * @return 
     * @param shape
     */ 
    public static native boolean containsShape(long space, long shape); /*
        return (jboolean) cpSpaceContainsShape((cpSpace*)space, (cpShape*)shape);
    */

    /**
     * Add a rigid body to the simulation.
     * @return 
     * @param body
     */ 
    public static native long addBody(long space, long body); /*
        return (jlong) cpSpaceAddBody((cpSpace*)space, (cpBody*)body);
    */

    /**
     * Remove a rigid body from the simulation.
     * @param body
     */ 
    public static native void removeBody(long space, long body); /*
        cpSpaceRemoveBody((cpSpace*)space, (cpBody*)body);
    */

    /**
     * Test if a rigid body has been added to the space.
     * @return 
     * @param body
     */ 
    public static native boolean containsBody(long space, long body); /*
        return (jboolean) cpSpaceContainsBody((cpSpace*)space, (cpBody*)body);
    */

    /**
     * Add a constraint to the simulation.
     * @return 
     * @param raint
     */ 
    public static native long addConstraint(long space, long constraint); /*
        return (jlong) cpSpaceAddConstraint((cpSpace*)space, (cpConstraint*)constraint);
    */

    /**
     * Remove a constraint from the simulation.
     * @param raint
     */ 
    public static native void removeConstraint(long space, long constraint); /*
        cpSpaceRemoveConstraint((cpSpace*)space, (cpConstraint*)constraint);
    */

    /**
     * Test if a constraint has been added to the space.
     * @return 
     * @param raint
     */ 
    public static native boolean containsConstraint(long space, long constraint); /*
        return cpSpaceContainsConstraint((cpSpace*)space, (cpConstraint*)constraint);
    */

    /**
     * Post Step callback function type.
     * Schedule a post-step callback to be called when cpSpaceStep() finishes.
     * You can only register one callback per unique value for key.
     * Returns true only if key has never been scheduled before.
     * It's possible to pass NULL for func if you only want to mark key as being used.
     * @return 
     * @param func
     * @param key
     * @param data
     */ 
    public static native boolean addPostStepCallback(long space, long func, long key, long data); /*
        return cpSpaceAddPostStepCallback((cpSpace*)space, (cpPostStepFunc)func, (void*)key, (void*)data);
    */

    /**
     * Nearest point query callback function type.Query the space at a point and call @c func for each shape found.
     * @param point
     * @param maxDistance
     * @param filter
     * @param func
     * @param data
     */ 
    public static native void pointQuery(long space, float point_x, float point_y,
            float maxDistance, int filterGroup, int filterCategories, int filterMask,
            long func, long data); /*
        cpSpacePointQuery((cpSpace*)space, cpv(point_x, point_y),
            maxDistance, cpShapeFilterNew(filterGroup, filterCategories, filterMask),
            (cpSpacePointQueryFunc)func, (void*)data);
    */

    /**
     * Query the space at a point and return the nearest shape found. Returns NULL if no shapes were found.
     * @return cpShape*
     * @param point
     * @param maxDistance
     * @param filter
     * @param out
     */ 
    public static native long pointQueryNearest(long space, float point_x, float point_y, float maxDistance,
            int filterGroup, int filterCategories, int filterMask, float[] out_floats5); /*
        cpPointQueryInfo out;
        cpShape* shape = cpSpacePointQueryNearest((cpSpace*)space, cpv(point_x, point_y),
            maxDistance, cpShapeFilterNew(filterGroup, filterCategories, filterMask), &out);
        gdxCpPointQueryInfoToFloats(&out, out_floats5);
        return (jlong) shape;
    */

    /**
     * Segment query callback function type.Perform a directed line segment query (like a raycast) against the space calling @c func for each shape intersected.
     * @param start
     * @param end
     * @param radius
     * @param filter
     * @param func
     * @param data
     */ 
    public static native void segmentQuery(long space, float start_x, float start_y, float end_x, float end_y,
            float radius, int filterGroup, int filterCategories, int filterMask,
            long func, long data); /*
        cpSpaceSegmentQuery((cpSpace*)space, cpv(start_x, start_y), cpv(end_x, end_y), radius,
            cpShapeFilterNew(filterGroup, filterCategories, filterMask),
            (cpSpaceSegmentQueryFunc)func, (void*)data);
    */

    /**
     * Perform a directed line segment query (like a raycast) against the space and return the first shape hit. Returns NULL if no shapes were hit.
     * @return 
     * @param start
     * @param end
     * @param radius
     * @param filter
     * @param out
     */ 
    public static native long segmentQueryFirst(long space, float start_x, float start_y, float end_x, float end_y,
            float radius, int filterGroup, int filterCategories, int filterMask, float[] out_floats8); /*
        cpSegmentQueryInfo out;
        cpShape* shape = cpSpaceSegmentQueryFirst((cpSpace*)space, cpv(start_x, start_y), cpv(end_x, end_y),
            radius, cpShapeFilterNew(filterGroup, filterCategories, filterMask), &out);
        gdxCpSegmentQueryInfoToFloats(&out, out_floats8);
        return (jlong) shape;
    */

    /**
     * Rectangle Query callback function type.Perform a fast rectangle query on the space calling @c func for each shape found.Only the shape's bounding boxes are checked for overlap, not their full shape.
     * @param bb
     * @param filter
     * @param func
     * @param data
     */ 
    public static native void bBQuery(long space, float bbLeft, float bbBottom, float bbRight, float bbTop,
            int filterGroup, int filterCategories, int filterMask, long func, long data); /*
        cpSpaceBBQuery((cpSpace*)space, cpBBNew(bbLeft, bbBottom, bbRight, bbTop),
            cpShapeFilterNew(filterGroup, filterCategories, filterMask), (cpSpaceBBQueryFunc)func, (void*)data);
    */

    /**
     * Shape query callback function type.Query a space for any shapes overlapping the given shape and call @c func for each shape found.
     * @return 
     * @param shape
     * @param func
     * @param data
     */ 
    public static native boolean shapeQuery(long space, long shape, long func, long data); /*
        return cpSpaceShapeQuery((cpSpace*)space, (cpShape*)shape, (cpSpaceShapeQueryFunc)func, (void*)data);
    */

    /**
     * Shape query callback function type.Query a space for any shapes overlapping the given shape and call @c func for each shape found.
     * @return 
     * @param shape
     * @param func
     * @param data
     */ 
    public static native boolean shapeQuery(long space, long shape, long func, float[] data); /*
        return cpSpaceShapeQuery((cpSpace*)space, (cpShape*)shape, (cpSpaceShapeQueryFunc)func, (void*)data);
    */

    /**
     * Space/body iterator callback function type.Call @c func for each body in the space.
     * @param func
     * @param data
     */ 
    public static native void eachBody(long space, long func, long data); /*
        cpSpaceEachBody((cpSpace*)space, (cpSpaceBodyIteratorFunc)func, (void*)data);
    */

    /**
     * Space/body iterator callback function type.Call @c func for each shape in the space.
     * @param func
     * @param data
     */ 
    public static native void eachShape(long space, long func, long data); /*
        cpSpaceEachShape((cpSpace*)space, (cpSpaceShapeIteratorFunc)func, (void*)data);
    */

    /**
     * Space/constraint iterator callback function type.Call @c func for each shape in the space.
     * @param func
     * @param data
     */ 
    public static native void eachConstraint(long space, long func, long data); /*
        cpSpaceEachConstraint((cpSpace*)space, (cpSpaceConstraintIteratorFunc)func, (void*)data);
    */

    /**
     * Update the collision detection info for the static shapes in the space.
     */ 
    public static native void reindexStatic(long space); /*
        cpSpaceReindexStatic((cpSpace*)space);
    */

    /**
     * Update the collision detection data for a specific shape in the space.
     * @param shape
     */ 
    public static native void reindexShape(long space, long shape); /*
        cpSpaceReindexShape((cpSpace*)space, (cpShape*)shape);
    */

    /**
     * Update the collision detection data for all shapes attached to a body.
     * @param body
     */ 
    public static native void reindexShapesForBody(long space, long body); /*
        cpSpaceReindexShapesForBody((cpSpace*)space, (cpBody*)body);
    */

    /**
     * Switch the space to use a spatial has as it's spatial index.
     * @param dim
     * @param count
     */ 
    public static native void useSpatialHash(long space, float dim, int count); /*
        cpSpaceUseSpatialHash((cpSpace*)space, dim, count);
    */

    /**
     * Step the space forward in time by delta.
     * @param dt
     */ 
    public static native void step(long space, float dt); /*
        cpSpaceStep((cpSpace*)space, dt);
    */

}
