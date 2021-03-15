package net.josid.gdx.chipmunk;

//@off
/*JNI
#include <chipmunk/chipmunk.h>
#include <chipmunk/gdx/gdxChipmunkUtils.h>
*/


final class JniBody {

    private JniBody() { }


    // CUSTOM FUNCTIONS

    static native void gdxBodyInit(long bodyAddress, int type, float pos_x, float pos_y, float angle, 
            float velocity_x, float velocity_y, float angularVeloctiy); /*
        cpBody* body = (cpBody*)bodyAddress;
        cpBodyInit(body, 0, 0);
        cpBodyType bodyType = gdxIntToCpBodyType(type);
        if (CP_BODY_TYPE_DYNAMIC != bodyType) cpBodySetType(body, bodyType);
        if (0 != pos_x || 0 != pos_y) cpBodySetPosition(body, cpv(pos_x, pos_y));
        if (0 != angle) cpBodySetAngle(body, angle);
        if (0 != velocity_x || 0 != velocity_y) cpBodySetVelocity(body, cpv(velocity_x, velocity_y));
        if (0 != angularVeloctiy) cpBodySetAngularVelocity(body, angularVeloctiy);
    */


    // CHIPMUNK FUNCTIONS

    /**
     * Allocate a cpBody.
     * @return
     */ 
    static native long alloc(); /*
        return (jlong) cpBodyAlloc();
    */

    /**
     * Initialize a cpBody.
     * @return 
     * @param mass
     * @param moment
     */ 
    static native void init(long body, float mass, float moment); /*
        cpBodyInit((cpBody*)body, mass, moment);
    */

    /**
     * Allocate and initialize a cpBody.
     * @return
     * @param mass
     * @param moment
     */ 
    static native long newBody(float mass, float moment); /*
        return (jlong) cpBodyNew(mass, moment);
    */

    /**
     * Allocate and initialize a cpBody, and set it as a kinematic body.
     * @return
     */ 
    static native long newKinematic(); /*
        return (jlong) cpBodyNewKinematic();
    */

    /**
     * Allocate and initialize a cpBody, and set it as a static body.
     * @return
     */ 
    static native long newStatic(); /*
        return (jlong) cpBodyNewStatic();
    */

    /**
     * Destroy a cpBody.
     */ 
    static native void destroy(long body); /*
        return cpBodyDestroy((cpBody*)body);
    */

    /**
     * Destroy and free a cpBody.
     */ 
    static native void free(long body); /*
        return cpBodyFree((cpBody*)body);
    */

    /**
     * Wake up a sleeping or idle body.
     */ 
    static native void activate(long body); /*
        return cpBodyActivate((cpBody*)body);
    */

    /**
     * Wake up any sleeping or idle bodies touching a static body.
     * @param filter
     */ 
    static native void activateStatic(long body, long shape); /*
        cpBodyActivateStatic((cpBody*)body, (cpShape*) shape);
    */

    /**
     * Force a body to fall asleep immediately.
     */ 
    static native void sleep(long body); /*
        cpBodySleep((cpBody*)body);
    */

    /**
     * Force a body to fall asleep immediately along with other bodies in a group.
     */ 
    static native void sleepWithGroup(long body, long group); /*
        cpBodySleepWithGroup((cpBody*)body, (cpBody*)group);
    */

    /**
     * Returns true if the body is sleeping.
     * @return 
     */ 
    static native boolean isSleeping(long body); /*
        return (jboolean) cpBodyIsSleeping((cpBody*)body);
    */

    /**
     * Get the type of the body.
     * @return 
     */ 
    static native int getType(long body); /*
        return gdxCpBodyTypeToInt( cpBodyGetType((cpBody*)body) );
    */

    /**
     * Set the type of the body.
     * @param type
     */ 
    static native void setType(long body, int type); /*
        cpBodySetType((cpBody*)body, gdxIntToCpBodyType(type));
    */

    /**
     * Get the space this body is added to.
     * @return 
     */ 
    static native long getSpace(long body); /*
        return (jlong) cpBodyGetSpace((cpBody*)body);
    */

    /**
     * Get the mass of the body.
     * @return 
     */ 
    static native float getMass(long body); /*
        return cpBodyGetMass((cpBody*)body);
    */

    /**
     * Set the mass of the body.
     * @param m
     */ 
    static native void setMass(long body, float m); /*
        cpBodySetMass((cpBody*)body, m);
    */

    /**
     * Get the moment of inertia of the body.
     * @return 
     */ 
    static native float getMoment(long body); /*
        return cpBodyGetMoment((cpBody*)body);
    */

    /**
     * Set the moment of inertia of the body.
     * @param i
     */ 
    static native void setMoment(long body, float i); /*
        cpBodySetMoment((cpBody*)body, i);
    */

    /**
     * Set the position of a body.
     * @return 
     */ 
    static native void getPosition(long body, float[] out_floats); /*
        cpVect v = cpBodyGetPosition((cpBody*)body);
        gdxCpVectToFloats(&v, out_floats);
    */

    /**
     * Set the position of the body.
     * @param pos
     */ 
    static native void setPosition(long body, float pos_x, float pos_y); /*
        cpBodySetPosition((cpBody*)body, cpv(pos_x, pos_y));
    */

    /**
     * Get the offset of the center of gravity in body local coordinates.
     * @return 
     */ 
    static native void getCenterOfGravity(long body, float[] out_floats); /*
        cpVect v = cpBodyGetCenterOfGravity((cpBody*)body);
        gdxCpVectToFloats(&v, out_floats);
    */

    /**
     * Set the offset of the center of gravity in body local coordinates.
     * @param cog
     */ 
    static native void setCenterOfGravity(long body, float cog_x, float cog_y); /*
        cpBodySetCenterOfGravity((cpBody*)body, cpv(cog_x, cog_y));
    */

    /**
     * Get the velocity of the body.
     * @return 
     */ 
    static native void getVelocity(long body, float[] out_floats2); /*
        cpVect v = cpBodyGetVelocity((cpBody*)body);
        gdxCpVectToFloats(&v, out_floats2);
    */

    /**
     * Set the velocity of the body.
     * @param velocity
     */ 
    static native void setVelocity(long body, float velocity_x, float velocity_y); /*
        cpBodySetVelocity((cpBody*)body, cpv(velocity_x, velocity_y));
    */

    /**
     * Get the force applied to the body for the next time step.
     * @return 
     */ 
    static native void getForce(long body, float[] out_floats2); /*
        cpVect v = cpBodyGetForce((cpBody*)body);
        gdxCpVectToFloats(&v, out_floats2);
    */

    /**
     * Set the force applied to the body for the next time step.
     * @param force
     */ 
    static native void setForce(long body, float force_x, float force_y); /*
        cpBodySetForce((cpBody*)body, cpv(force_x, force_y));
    */

    /**
     * Get the angle of the body.
     * @return 
     */ 
    static native float getAngle(long body); /*
        return cpBodyGetAngle((cpBody*)body);
    */

    /**
     * Set the angle of a body.
     * @param a
     */ 
    static native void setAngle(long body, float a); /*
        cpBodySetAngle((cpBody*)body, a);
    */

    /**
     * Get the angular velocity of the body.
     * @return 
     */ 
    static native float getAngularVelocity(long body); /*
        return cpBodyGetAngularVelocity((cpBody*)body);
    */

    /**
     * Set the angular velocity of the body.
     * @param angularVelocity
     */ 
    static native void setAngularVelocity(long body, float angularVelocity); /*
        cpBodySetAngularVelocity((cpBody*)body, angularVelocity);
    */

    /**
     * Get the torque applied to the body for the next time step.
     * @return 
     */ 
    static native float getTorque(long body); /*
        return cpBodyGetTorque((cpBody*)body);
    */

    /**
     * Set the torque applied to the body for the next time step.
     * @param torque
     */ 
    static native void setTorque(long body, float torque); /*
        cpBodySetTorque((cpBody*)body, torque);
    */

    /**
     * Get the rotation vector of the body. (The x basis vector of it's transform.)
     * @return 
     */ 
    static native void getRotation(long body, float[] out_floats); /*
        cpVect v = cpBodyGetRotation((cpBody*)body);
        gdxCpVectToFloats(&v, out_floats);
    */

    /**
     * Get the user data pointer assigned to the body.
     * @return 
     */ 
    static native long getUserData(long body); /*
        return (jlong) cpBodyGetUserData((cpBody*)body);
    */

    /**
     * Set the user data pointer assigned to the body.
     * @param userData
     */ 
    static native void setUserData(long body, long userData); /*
        cpBodySetUserData((cpBody*)body, (cpDataPointer)userData);
    */

    /**
     * Set the callback used to update a body's velocity.
     * @param velocityFunc
     */ 
    static native void setVelocityUpdateFunc(long body, long velocityFunc); /*
        cpBodySetVelocityUpdateFunc((cpBody*)body, (cpBodyVelocityFunc)velocityFunc);
    */

    /**
     * Set the callback used to update a body's position.NOTE: It's not generally recommended to override this unless you call the default position update function.
     * @param positionFunc
     */ 
    static native void setPositionUpdateFunc(long body, long positionFunc); /*
        cpBodySetPositionUpdateFunc((cpBody*)body, (cpBodyPositionFunc)positionFunc);
    */

    /**
     * Default velocity integration function..
     * @param gravity
     * @param damping
     * @param dt
     */ 
    static native void updateVelocity(long body, float gravity_x, float gravity_y, float damping, float dt); /*
        cpBodyUpdateVelocity((cpBody*)body, cpv(gravity_x, gravity_y), damping, dt);
    */

    /**
     * Default position integration function.
     * @param dt
     */ 
    static native void updatePosition(long body, float dt); /*
        cpBodyUpdatePosition((cpBody*)body, dt);
    */

    /**
     * Convert body relative/local coordinates to absolute/world coordinates.
     * @return 
     * @param point
     */ 
    static native void localToWorld(long body, float point_x, float point_y, float[] out_floats2); /*
        cpVect v = cpBodyLocalToWorld((cpBody*)body, cpv(point_x, point_y));
        gdxCpVectToFloats(&v, out_floats2);
    */

    /**
     * Convert body absolute/world coordinates to  relative/local coordinates.
     * @return 
     * @param point
     */ 
    static native void worldToLocal(long body, float point_x, float point_y, float[] out_floats2); /*
        cpVect v = cpBodyWorldToLocal((cpBody*)body, cpv(point_x, point_y));
        gdxCpVectToFloats(&v, out_floats2);
    */

    /**
     * Apply a force to a body. Both the force and point are expressed in world coordinates.
     * @param force
     * @param point
     */ 
    static native void applyForceAtWorldPoint(long body, float force_x, float force_y, float point_x, float point_y); /*
        cpBodyApplyForceAtWorldPoint((cpBody*)body, cpv(force_x, force_y), cpv(point_x, point_y));
    */

    /**
     * Apply a force to a body. Both the force and point are expressed in body local coordinates.
     * @param force
     * @param point
     */ 
    static native void applyForceAtLocalPoint(long body, float force_x, float force_y, float point_x, float point_y); /*
        cpBodyApplyForceAtLocalPoint((cpBody*)body, cpv(force_x, force_y), cpv(point_x, point_y));
    */

    /**
     * Apply an impulse to a body. Both the impulse and point are expressed in world coordinates.
     * @param impulse
     * @param point
     */ 
    static native void applyImpulseAtWorldPoint(long body, float impulse_x, float impulse_y, float point_x, float point_y); /*
        cpBodyApplyImpulseAtWorldPoint((cpBody*)body, cpv(impulse_x, impulse_y), cpv(point_x, point_y));
    */

    /**
     * Apply an impulse to a body. Both the impulse and point are expressed in body local coordinates.
     * @param impulse
     * @param point
     */ 
    static native void applyImpulseAtLocalPoint(long body, float impulse_x, float impulse_y, float point_x, float point_y); /*
        cpBodyApplyImpulseAtLocalPoint((cpBody*)body, cpv(impulse_x, impulse_y), cpv(point_x, point_y));
    */

    /**
     * Get the velocity on a body (in world units) at a point on the body in world coordinates.
     * @return 
     * @param point
     */ 
    static native void getVelocityAtWorldPoint(long body, float point_x, float point_y, float[] out_floats2); /*
        cpVect v = cpBodyGetVelocityAtWorldPoint((cpBody*)body, cpv(point_x, point_y));
        gdxCpVectToFloats(&v, out_floats2);
    */

    /**
     * Get the velocity on a body (in world units) at a point on the body in local coordinates.
     * @return 
     * @param point
     */ 
    static native void getVelocityAtLocalPoint(long body, float point_x, float point_y, float[] out_floats2); /*
        cpVect v = cpBodyGetVelocityAtLocalPoint((cpBody*)body, cpv(point_x, point_y));
        gdxCpVectToFloats(&v, out_floats2 );
    */

    /**
     * Get the amount of kinetic energy contained by the body.
     * @return 
     */ 
    static native float kineticEnergy(long body); /*
        return cpBodyKineticEnergy((cpBody*)body);
    */

    /**
     * Body/shape iterator callback function type.
     * Call func once for each shape attached to body and added to the space.
     * @param func
     * @param data
     */ 
    static native void eachShape(long body, long func, long data); /*
        cpBodyEachShape((cpBody*)body, (cpBodyShapeIteratorFunc)func, (void*)data);
    */

    /**
     * Body/constraint iterator callback function type.
     * Call func once for each constraint attached to body and added to the space.
     * @param func
     * @param data
     */ 
    static native void eachConstraint(long body, long func, long data); /*
        cpBodyEachConstraint((cpBody*)body, (cpBodyConstraintIteratorFunc)func, (void*)data);
    */

    /**
     * Body/arbiter iterator callback function type.
     * Call func once for each arbiter that is currently active on the body.
     * @param func
     * @param data
     */ 
    static native void eachArbiter(long body, long func, long data); /*
        cpBodyEachArbiter((cpBody*)body, (cpBodyArbiterIteratorFunc)func, (void*)data);
    */

}
