package net.josid.gdx.chipmunk;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.LongMap;

import net.josid.gdx.chipmunk.callback.CpPostStepFuncData;
import net.josid.gdx.chipmunk.callback.CpSpaceBbQueryFunc;
import net.josid.gdx.chipmunk.callback.CpSpaceBodyIteratorFunc;
import net.josid.gdx.chipmunk.callback.CpSpaceConstraintIteratorFunc;
import net.josid.gdx.chipmunk.callback.CpSpacePointQueryFunc;
import net.josid.gdx.chipmunk.callback.CpSpaceSegmentQueryFunc;
import net.josid.gdx.chipmunk.callback.CpSpaceShapeIteratorFunc;
import net.josid.gdx.chipmunk.callback.CpSpaceShapeQueryFunc;
import net.josid.gdx.chipmunk.collision.CpCollisionHandler;
import net.josid.gdx.chipmunk.contact.CpContactPointSet;
import net.josid.gdx.chipmunk.contact.CpShapeFilter;
import net.josid.gdx.chipmunk.query.CpPointQueryInfo;
import net.josid.gdx.chipmunk.query.CpSegmentQueryInfo;


public class CpSpace {

    private final CpBody staticBody;
    public final Chipmunk chipmunk;
    final long nativeAddress;
    final long gdxNativeAddress;
    final CpArrays arrays = new CpArrays();
    final CpArbiter tempArbiter;
    final CpShapeFilter allShapesFilter = new CpShapeFilter();

    final LongMap<CpPostStepFuncData> postStepFuncs = new LongMap<>();
    private long postStepFuncKeys = 0;
    CpSpacePointQueryFunc pointQueryFunc;
    CpSpaceSegmentQueryFunc segmentQueryFunc;
    CpSpaceBbQueryFunc bbQueryFunc;
    float[] shapeQueryFuncFloats;
    final CpContactPointSet shapeQueryFuncPointSet = new CpContactPointSet();
    CpSpaceShapeQueryFunc shapeQueryFunc;
    CpSpaceBodyIteratorFunc bodyIteratorFunc;
    CpSpaceShapeIteratorFunc shapeIteratorFunc;
    CpSpaceConstraintIteratorFunc constraintIteratorFunc;
    CpCollisionHandler defaultCollisionHandler;

    private Object userData;


    CpSpace(long nativeAddress, long gdxNativeAddress, Chipmunk chipmunk) {
        this.chipmunk = chipmunk;
        this.nativeAddress = nativeAddress;
        this.gdxNativeAddress = gdxNativeAddress;
        JniSpace.setUserData(nativeAddress, gdxNativeAddress);
        staticBody = new CpBody(JniSpace.getStaticBody(nativeAddress), chipmunk);
        staticBody.space = this;
        tempArbiter = new CpArbiter(chipmunk);
    }

    void reset() {
        postStepFuncs.clear();
        pointQueryFunc = null;
        segmentQueryFunc = null;
        bbQueryFunc = null;
        shapeQueryFuncFloats = null;
        // shapeQueryFuncPointSet;
        shapeQueryFunc = null;
        bodyIteratorFunc = null;
        shapeIteratorFunc = null;
        constraintIteratorFunc = null;

        userData = null;
    }

    public Object getUserData() {
        return userData;
    }

    public void setUserData(Object userData) {
        this.userData = userData;
    }

    /**
     * Initialize a cpSpace.
     */ 
    public void init() {
        JniSpace.init(nativeAddress);
        
    }

    /**
     * The Space provided static body for a given cpSpace.
     * This is merely provided for convenience and you are not required to use it.
     */ 
    public CpBody getStaticBody() {
        return staticBody;
    }

    /**
     * Number of iterations to use in the impulse solver to solve contacts and other constraints.
     */ 
    public int getIterations() {
        return JniSpace.getIterations(nativeAddress);
    }

    /**
     * Number of iterations to use in the impulse solver to solve contacts and other constraints.
     */
    public void setIterations(int iterations) {
        JniSpace.setIterations(nativeAddress, iterations);
    }

    /**
     * Gravity to pass to rigid bodies when integrating velocity.
     */ 
    public Vector2 getGravity(Vector2 out) {
        JniSpace.getGravity(nativeAddress, arrays.floats4);
        return out.set(arrays.floats4[0], arrays.floats4[1]);
    }

    /**
     * Gravity to pass to rigid bodies when integrating velocity.
     */ 
    public void setGravity(float x, float y) {
        JniSpace.setGravity(nativeAddress, x, y);
    }

    /**
     * Damping rate expressed as the fraction of velocity bodies retain each second.
     * A value of 0.9 would mean that each body's velocity will drop 10% per second.
     * The default value is 1.0, meaning no damping is applied.
     * @note This damping value is different than those of cpDampedSpring and cpDampedRotarySpring.
     */ 
    public float getDamping() {
        return JniSpace.getDamping(nativeAddress);
    }

    /**
     * Damping rate expressed as the fraction of velocity bodies retain each second.
     * A value of 0.9 would mean that each body's velocity will drop 10% per second.
     * The default value is 1.0, meaning no damping is applied.
     * @note This damping value is different than those of cpDampedSpring and cpDampedRotarySpring.
     */ 
    public void setDamping(float damping) {
        JniSpace.setDamping(nativeAddress, damping);
    }

    /**
     * Speed threshold for a body to be considered idle.
     * The default value of 0 means to let the space guess a good threshold based on gravity.
     */
    public float getIdleSpeedThreshold() {
        return JniSpace.getIdleSpeedThreshold(nativeAddress);
    }

    /**
     * Speed threshold for a body to be considered idle.
     * The default value of 0 means to let the space guess a good threshold based on gravity.
     */ 
    public void setIdleSpeedThreshold(float idleSpeedThreshold) {
        JniSpace.setIdleSpeedThreshold(nativeAddress, idleSpeedThreshold);
    }

    /**
     * Time a group of bodies must remain idle in order to fall asleep.
     * Enabling sleeping also implicitly enables the the contact graph.
     * The default value of INFINITY disables the sleeping algorithm.
     */ 
    public float getSleepTimeThreshold() {
        return JniSpace.getSleepTimeThreshold(nativeAddress);
    }

    /**
     * Time a group of bodies must remain idle in order to fall asleep.
     * Enabling sleeping also implicitly enables the the contact graph.
     * The default value of INFINITY disables the sleeping algorithm.
     */ 
    public void setSleepTimeThreshold(float sleepTimeThreshold) {
        JniSpace.setSleepTimeThreshold(nativeAddress, sleepTimeThreshold);
    }

    /**
     * Amount of encouraged penetration between colliding shapes.
     * Used to reduce oscillating contacts and keep the collision cache warm.
     * Defaults to 0.1.
     * If you have poor simulation quality,increase this number as much as possible without allowing visible amounts of overlap.
     */
    public float getCollisionSlop() {
        return JniSpace.getCollisionSlop(nativeAddress);
    }

    /**
     * Amount of encouraged penetration between colliding shapes.
     * Used to reduce oscillating contacts and keep the collision cache warm.
     * Defaults to 0.1.
     * If you have poor simulation quality,increase this number as much as possible without allowing visible amounts of overlap.
     * @param collisionSlop
     */
    public void setCollisionSlop(float collisionSlop) {
        JniSpace.setCollisionSlop(nativeAddress, collisionSlop);
    }

    /**
     * Determines how fast overlapping shapes are pushed apart.
     * Expressed as a fraction of the error remaining after each second.
     * Defaults to pow(1.0 - 0.1, 60.0) meaning that Chipmunk fixes 10% of overlap each frame at 60Hz.
     */ 
    public float getCollisionBias() {
        return JniSpace.getCollisionBias(nativeAddress);
    }

    /**
     * Determines how fast overlapping shapes are pushed apart.
     * Expressed as a fraction of the error remaining after each second.
     * Defaults to pow(1.0 - 0.1, 60.0) meaning that Chipmunk fixes 10% of overlap each frame at 60Hz.
     */ 
    public void setCollisionBias(float collisionBias) {
        JniSpace.setCollisionBias(nativeAddress, collisionBias);
    }

    /**
     * Number of frames that contact information should persist.
     * Defaults to 3.
     * There is probably never a reason to change this value.
     */ 
    public long getCollisionPersistence() {
        return JniSpace.getCollisionPersistence(nativeAddress);
    }

    /**
     * Number of frames that contact information should persist.
     * Defaults to 3.
     * There is probably never a reason to change this value.
     */
    public void setCollisionPersistence(long collisionPersistence) {
        JniSpace.setCollisionPersistence(nativeAddress, collisionPersistence);
    }

    /**
     * Returns the current (or most recent) time step used with the given space.
     * Useful from callbacks if your time step is not a compile-time global.
     */ 
    public float getCurrentTimeStep() {
        return JniSpace.getCurrentTimeStep(nativeAddress);
    }

    /**
     * returns true from inside a callback when objects cannot be added/removed.
     */ 
    public boolean isLocked() {
        return JniSpace.isLocked(nativeAddress);
    }

    /**
     * Create or return the existing collision handler that is called for all collisions that are not handled by a more specific collision handler.
     */ 
    public void addDefaultCollisionHandler(CpCollisionHandler collisionHandler) {
        this.defaultCollisionHandler = collisionHandler;
        long jniCollisionHandler = JniSpace.addDefaultCollisionHandler(nativeAddress);
        chipmunk.jniChipmunk.registerCollisionHandler(gdxNativeAddress, jniCollisionHandler, collisionHandler);
    }

//    /**
//     * Create or return the existing collision handler for the specified pair of collision types.
//     * If wildcard handlers are used with either of the collision types, it's the responibility of the custom handler to invoke the wildcard handlers.
//     */ 
//    public long addCollisionHandler(long a, long b) {
//    }
//
//    /**
//     * Create or return the existing wildcard collision handler for the specified type.
//     */ 
//    public long addWildcardHandler(long type) {
//    }

    /**
     * Add a collision shape to the simulation.
     * If the shape is attached to a static body, it will be added as a static shape.
     */ 
    public void addShape(CpShape shape) {
        JniSpace.addShape(nativeAddress, shape.nativeAddress);
        shape.space = this;
    }

    /**
     * Remove a collision shape from the simulation.
     */ 
    public void removeShape(CpShape shape) {
        JniSpace.removeShape(nativeAddress, shape.nativeAddress);
        shape.space = null;
    }

    /**
     * Test if a collision shape has been added to the space.
     */ 
    public boolean containsShape(CpShape shape) {
        return JniSpace.containsShape(nativeAddress, shape.nativeAddress);
    }

    /**
     * Add a rigid body to the simulation.
     */ 
    public void addBody(CpBody body) {
        JniSpace.addBody(nativeAddress, body.nativeAddress);
        body.space = this;
    }

    /**
     * Add a rigid body and shapes to the simulation.
     */ 
    public void addBodyAndShapes(CpBody body) {
        addBody(body);
        for (int i = 0; i < body.shapes.size; i++) {
            addShape(body.shapes.get(i));
        }
    }

    /**
     * Remove a rigid body from the simulation.
     */ 
    public void removeBody(CpBody body) {
        JniSpace.removeBody(nativeAddress, body.nativeAddress);
        body.space = null;
    }

    /**
     * Test if a rigid body has been added to the space.
     */ 
    public boolean containsBody(CpBody body) {
        return JniSpace.containsBody(nativeAddress, body.nativeAddress);
    }

    /**
     * Add a constraint to the simulation.
     */ 
    public void addConstraint(CpConstraint constraint) {
        JniSpace.addConstraint(nativeAddress, constraint.nativeAddress);
        constraint.space = this;
    }

    /**
     * Remove a constraint from the simulation.
     */ 
    public void removeConstraint(CpConstraint constraint) {
        JniSpace.removeConstraint(nativeAddress, constraint.nativeAddress);
        constraint.space = null;
    }

    /**
     * Test if a constraint has been added to the space.
     */ 
    public boolean containsConstraint(CpConstraint constraint) {
        return JniSpace.containsConstraint(nativeAddress, constraint.nativeAddress);
    }

    /**
     * Post Step callback function type.
     * Schedule a post-step callback to be called when cpSpaceStep() finishes.
     * Returns the key to be able to delete later
     */
    public long addPostStepCallback(CpPostStepFuncData funcData) {
        long key = postStepFuncKeys++;
        postStepFuncs.put(key, funcData);
        JniSpace.addPostStepCallback(nativeAddress, JniChipmunk.spacePostStepJniFunc, key, 0);
        return key;
    }

    /**
     * Invalidate postStepCallback
     */
    public void invalidatePostStepCallback(long key) {
        postStepFuncs.remove(key);
        JniSpace.addPostStepCallback(nativeAddress, 0, key, 0);
    }

    /**
     * Nearest point query callback function type.Query the space at a point and call @c func for each shape found.
     */ 
    public void pointQuery(CpSpacePointQueryFunc func, float pointX, float pointY, float maxDistance) {
        pointQuery(func, pointX, pointY, maxDistance, allShapesFilter);
    }

    /**
     * Nearest point query callback function type.Query the space at a point and call @c func for each shape found.
     */ 
    public void pointQuery(CpSpacePointQueryFunc func, float pointX, float pointY, float maxDistance, CpShapeFilter filter) {
        this.pointQueryFunc = func;
        JniSpace.pointQuery(nativeAddress, pointX, pointY, maxDistance, filter.group, filter.categories, filter.mask,
                JniChipmunk.spacePointQueryJniFunc, nativeAddress);
        this.pointQueryFunc = null;
    }

    /**
     * Query the space at a point and return the nearest shape found.
     */ 
    public CpPointQueryInfo pointQueryNearest(float pointX, float pointY, float maxDistance, CpPointQueryInfo out_pointQueryInfo) {
        return pointQueryNearest(pointX, pointY, maxDistance, allShapesFilter, out_pointQueryInfo);
    }

    /**
     * Query the space at a point and return the nearest shape found.
     */ 
    public CpPointQueryInfo pointQueryNearest(float pointX, float pointY, float maxDistance, CpShapeFilter filter,
            CpPointQueryInfo out_pointQueryInfo) {
        float[] floats8 = arrays.floats8;
        long shape = JniSpace.pointQueryNearest(nativeAddress, pointX, pointY, maxDistance,
                filter.group, filter.categories, filter.mask, floats8);
        return out_pointQueryInfo.set(chipmunk.registry.getShape(shape), floats8);
    }

    /**
     * Segment query callback function type.
     * Perform a directed line segment query (like a raycast) against the space calling @c func for each shape intersected.
     */ 
    public void segmentQuery(CpSpaceSegmentQueryFunc func, float startX, float startY, float endX, float endY, float radius) {
        segmentQuery(func, startX, startY, endX, endY, radius, allShapesFilter);
    }

    /**
     * Segment query callback function type.
     * Perform a directed line segment query (like a raycast) against the space calling @c func for each shape intersected.
     */ 
    public void segmentQuery(CpSpaceSegmentQueryFunc func, float startX, float startY, float endX, float endY,
            float radius, CpShapeFilter filter) {
        this.segmentQueryFunc = func;
        JniSpace.segmentQuery(nativeAddress, startX, startY, endX, endX, radius, 
                filter.group, filter.categories, filter.mask, JniChipmunk.spaceSegmentQueryJniFunc, nativeAddress);
        func = null;
    }

    /**
     * Perform a directed line segment query (like a raycast) against the space and return the first shape hit. Returns NULL if no shapes were hit.
     */ 
    public CpSegmentQueryInfo segmentQueryFirst(float startX, float startY, float endX, float endY, float radius, 
            CpSegmentQueryInfo out_info) {
        return segmentQueryFirst(startX, startY, endX, endY, radius, allShapesFilter, out_info);
    }

    /**
     * Perform a directed line segment query (like a raycast) against the space and return the first shape hit. Returns NULL if no shapes were hit.
     */ 
    public CpSegmentQueryInfo segmentQueryFirst(float startX, float startY, float endX, float endY, float radius,
            CpShapeFilter filter, CpSegmentQueryInfo out_info) {
        float[] floats8 = arrays.floats8;
        long shape = JniSpace.segmentQueryFirst(nativeAddress, startX, startY, endX, endY, radius,
            filter.group, filter.categories, filter.mask, floats8);
        return out_info.set(chipmunk.registry.getShape(shape), floats8);
    }

    /**
     * Rectangle Query callback function type.Perform a fast rectangle query on the space calling @c func for each shape found.Only the shape's bounding boxes are checked for overlap, not their full shape.
     */ 
    public void boundingBoxQuery(CpSpaceBbQueryFunc func, float left, float bottom, float right, float top) {
        boundingBoxQuery(func, left, bottom, right, top, allShapesFilter);
    }

    /**
     * Rectangle Query callback function type.Perform a fast rectangle query on the space calling @c func for each shape found.Only the shape's bounding boxes are checked for overlap, not their full shape.
     */ 
    public void boundingBoxQuery(CpSpaceBbQueryFunc func, float left, float bottom, float right, float top,
            CpShapeFilter filter) {
        this.bbQueryFunc = func;
        JniSpace.bBQuery(nativeAddress, left, bottom, right, top, filter.group, filter.categories, filter.mask,
            JniChipmunk.spaceBbQueryJniFunc, nativeAddress);
        this.bbQueryFunc = null;
    }

    /**
     * Shape query callback function type.Query a space for any shapes overlapping the given shape and call @c func for each shape found.
     */ 
    public boolean shapeQuery(CpSpaceShapeQueryFunc func, CpShape shape) {
        this.shapeQueryFunc = func;
        this.shapeQueryFuncFloats = arrays.floats16;
        boolean result = JniSpace.shapeQuery(nativeAddress, shape.nativeAddress, JniChipmunk.spaceShapeQueryJniFunc, this.shapeQueryFuncFloats);
        shapeQueryFuncFloats = null;
        this.shapeQueryFunc = null;
        return result;
    }

    /**
     * Update the collision detection info for the static shapes in the space.
     */ 
    public void reindexStatic() {
        JniSpace.reindexStatic(nativeAddress);
    }

    /**
     * Update the collision detection data for a specific shape in the space.
     */ 
    public void reindexShape(CpShape shape) {
        JniSpace.reindexShape(nativeAddress, shape.nativeAddress);
    }

    /**
     * Update the collision detection data for all shapes attached to a body.
     */ 
    public void reindexShapesForBody(CpBody body) {
        JniSpace.reindexShapesForBody(nativeAddress, body.nativeAddress);
    }

    /**
     * Switch the space to use a spatial has as it's spatial index.
     */ 
    public void useSpatialHash(float dim, int count) {
        JniSpace.useSpatialHash(nativeAddress, dim, count);
    }

    /**
     * Step the space forward in time by delta.
     */ 
    public void step(float delta) {
        JniSpace.step(nativeAddress, delta);
        postStepFuncs.clear();
    }

}
