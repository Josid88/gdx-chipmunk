package net.josid.gdx.chipmunk;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import net.josid.gdx.chipmunk.callback.CpBodyArbiterIteratorFunc;
import net.josid.gdx.chipmunk.callback.CpBodyConstraintIteratorFunc;
import net.josid.gdx.chipmunk.callback.CpBodyPositionFunc;
import net.josid.gdx.chipmunk.callback.CpBodyShapeIteratorFunc;
import net.josid.gdx.chipmunk.callback.CpBodyVelocityFunc;


public class CpBody {

    public final Chipmunk chipmunk;
    final long nativeAddress;
    final CpArbiter arbiterIterator;
    final Array<CpShape> shapes = new Array<>(false, 8);
    final Array<CpConstraint> constraints = new Array<>(false, 4);
    CpSpace space;
    private Object userData;

    CpBodyPositionFunc positionFunc;
    CpBodyVelocityFunc velocityFunc;
    CpBodyShapeIteratorFunc shapeIteratorFunc = syncShapeFunc;
    CpBodyConstraintIteratorFunc constraintIteratorFunc = syncConstraintFunc;
    CpBodyArbiterIteratorFunc arbiteIteratorFunc;


    CpBody(long nativeAddress, Chipmunk chipmunk) {
        this.nativeAddress = nativeAddress;
        this.chipmunk = chipmunk;
        this.arbiterIterator = new CpArbiter(chipmunk);
    }

    void reset() {
        shapes.clear();
        constraints.clear();
        space = null;
        userData = null;

        positionFunc = null;
        velocityFunc = null;
        shapeIteratorFunc = syncShapeFunc;
        constraintIteratorFunc = syncConstraintFunc;
        arbiteIteratorFunc = null;
    }

    public CpSpace getSpace() {
        return space;
    }

    public Object getUserData() {
        return userData;
    }

    public void setUserData(Object userData) {
        this.userData = userData;
    }

    public void syncShapes() {
        shapes.clear();
        JniBody.eachShape(nativeAddress, JniChipmunk.bodyShapeIteratorJniFunc, chipmunk.jniChipmunk.nativeAddress);
    }

    static final CpBodyShapeIteratorFunc syncShapeFunc = (body, shape)->{
        body.shapes.add(shape);
    };

    public void syncConstraints() {
        constraints.clear();
        JniBody.eachConstraint(nativeAddress, JniChipmunk.bodyConstraintIteratorJniFunc, chipmunk.jniChipmunk.nativeAddress);
    }

    /**
     * Wake up a sleeping or idle body.
     */ 
    public void activate() {
        JniBody.activate(nativeAddress);
    }

    /**
     * Wake up any sleeping or idle bodies touching a static body.
     */ 
    public void activateStatic(CpShape shape) {
        JniBody.activateStatic(nativeAddress, shape.nativeAddress);
    }

    /**
     * Force a body to fall asleep immediately.
     */ 
    public void sleep() {
        JniBody.sleep(nativeAddress);
    }

    /**
     * Force a body to fall asleep immediately along with other bodies in a group.
     */ 
    public void sleepWithGroup() {
        JniBody.sleepWithGroup(nativeAddress, 0);
    }

    /**
     * Force a body to fall asleep immediately along with other bodies in a group.
     */ 
    public void sleepWithGroup(CpBody group) {
        JniBody.sleepWithGroup(nativeAddress, group.nativeAddress);
    }

    /**
     * Returns true if the body is sleeping.
     */ 
    public boolean isSleeping() {
        return JniBody.isSleeping(nativeAddress);
    }

    /**
     * Get the type of the body.
     */ 
    public Type getType() {
        return Type.valueOf( JniBody.getType(nativeAddress) );
    }

    /**
     * Set the type of the body.
     */ 
    public void setType(Type type) {
        JniBody.setType(nativeAddress, type.value);
    }

    /**
     * Get the mass of the body.
     */ 
    public float getMass() {
        return JniBody.getMass(nativeAddress);
    }

    /**
     * Set the mass of the body.s
     */ 
    public void setMass(float mass) {
        JniBody.setMass(nativeAddress, mass);
    }

    /**
     * Get the moment of inertia of the body. 
     */ 
    public float getMoment() {
        return JniBody.getMoment(nativeAddress);
    }

    /**
     * Set the moment of inertia of the body.
     */ 
    public void setMoment(float moment) {
        JniBody.setMoment(nativeAddress, moment);
    }

    /**
     * Set the position of a body.
     */ 
    public Vector2 getPosition(Vector2 out_pos) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniBody.getPosition(nativeAddress, floats4);
        return out_pos.set(floats4[0], floats4[1]);
    }

    /**
     * Set the position of the body.
     */ 
    public void setPosition(float x, float y) {
        JniBody.setPosition(nativeAddress, x, y);
    }

    /**
     * Get the offset of the center of gravity in body local coordinates.
     */ 
    public Vector2 getCenterOfGravity(Vector2 out_cog) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniBody.getCenterOfGravity(nativeAddress, floats4);
        return out_cog.set(floats4[0], floats4[1]);
    }

    /**
     * Set the offset of the center of gravity in body local coordinates.
     */ 
    public void setCenterOfGravity(float x, float y) {
        JniBody.setCenterOfGravity(nativeAddress, x, y);
    }

    /**
     * Get the velocity of the body. 
     */ 
    public Vector2 getVelocity(Vector2 out_vel) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniBody.getVelocity(nativeAddress, floats4);
        return out_vel.set(floats4[0], floats4[1]);
    }

    /**
     * Set the velocity of the body.
     */ 
    public void setVelocity(float x, float y) {
        JniBody.setVelocity(nativeAddress, x, y);
    }

    /**
     * Get the force applied to the body for the next time step.
     */ 
    public Vector2 getForce(Vector2 out_force) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniBody.getForce(nativeAddress, floats4);
        return out_force.set(floats4[0], floats4[1]);
    }

    /**
     * Set the force applied to the body for the next time step.
     */ 
    public void setForce(float x, float y) {
        JniBody.setForce(nativeAddress, x, y);
    }

    /**
     * Get the angle of the body.
     */ 
    public float getAngle() {
        return JniBody.getAngle(nativeAddress);
    }

    /**
     * Set the angle of a body.
     */ 
    public void setAngle(float angle) {
        JniBody.setAngle(nativeAddress, angle);
    }

    /**
     * Get the angular velocity of the body.
     */ 
    public float getAngularVelocity() {
        return JniBody.getAngularVelocity(nativeAddress);
    }

    /**
     * Set the angular velocity of the body.
     */ 
    public void setAngularVelocity(float angularVelocity) {
        JniBody.setAngularVelocity(nativeAddress, angularVelocity);
    }

    /**
     * Get the torque applied to the body for the next time step.
     */ 
    public float getTorque() {
        return JniBody.getTorque(nativeAddress);
    }

    /**
     * Set the torque applied to the body for the next time step.
     */ 
    public void setTorque(float torque) {
        JniBody.setTorque(nativeAddress, torque);
    }

    /**
     * Get the rotation vector of the body. (The x basis vector of it's transform.)
     * @return 
     */ 
    public Vector2 getRotation(Vector2 out_rot) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniBody.getRotation(nativeAddress, floats4);
        return out_rot.set(floats4[0], floats4[1]);
    }

    // TODO add default callback
    /**
     * Set the callback used to update a body's velocity.
     */ 
    public void setVelocityUpdateFunc(CpBodyVelocityFunc velocityFunc) {
        this.velocityFunc = velocityFunc;
        JniBody.setVelocityUpdateFunc(nativeAddress, JniChipmunk.bodyVelocityJniFunc);
    }

    // TODO add default callback
    /**
     * Set the callback used to update a body's position.
     * NOTE: It's not generally recommended to override this unless you call the default position update function.
     */ 
    public void setPositionUpdateFunc(CpBodyPositionFunc positionFunc) {
        this.positionFunc = positionFunc;
        JniBody.setPositionUpdateFunc(nativeAddress, JniChipmunk.bodyPositionJniFunc);
    }

    /**
     * Default velocity integration function..
     */ 
    public void updateVelocity(float gravity_x, float gravity_y, float damping, float dt) {
        JniBody.updateVelocity(nativeAddress, gravity_x, gravity_y, damping, dt);
    }

    /**
     * Default position integration function.
     */ 
    public void updatePosition(float delta) {
        JniBody.updatePosition(nativeAddress, delta);
    }

    /**
     * Convert body relative/local coordinates to absolute/world coordinates.
     */ 
    public Vector2 localToWorld(float point_x, float point_y, Vector2 out_world) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniBody.localToWorld(nativeAddress, point_x, point_y, floats4);
        return out_world.set(floats4[0], floats4[1]);
    }

    /**
     * Convert body absolute/world coordinates to  relative/local coordinates.
     */ 
    public Vector2 worldToLocal(float point_x, float point_y, Vector2 out_local) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniBody.worldToLocal(nativeAddress, point_x, point_y, floats4);
        return out_local.set(floats4[0], floats4[1]);
    }

    /**
     * Apply a force to a body. Both the force and point are expressed in world coordinates.
     */ 
    public void applyForceAtWorldPoint(float force_x, float force_y, float point_x, float point_y) {
        JniBody.applyForceAtWorldPoint(nativeAddress, force_x, force_y, point_x, point_y);
    }

    /**
     * Apply a force to a body. Both the force and point are expressed in body local coordinates.
     */ 
    public void applyForceAtLocalPoint(float force_x, float force_y, float point_x, float point_y) {
        JniBody.applyForceAtLocalPoint(nativeAddress, force_x, force_y, point_x, point_y);
    }

    /**
     * Apply an impulse to a body. Both the impulse and point are expressed in world coordinates.
     */ 
    public void applyImpulseAtWorldPoint(float impulse_x, float impulse_y, float point_x, float point_y) {
        JniBody.applyImpulseAtWorldPoint(nativeAddress, impulse_x, impulse_y, point_x, point_y);
    }

    /**
     * Apply an impulse to a body. Both the impulse and point are expressed in body local coordinates.
     */ 
    public void applyImpulseAtLocalPoint(float impulse_x, float impulse_y, float point_x, float point_y) {
        JniBody.applyImpulseAtLocalPoint(nativeAddress, impulse_x, impulse_y, point_x, point_y);
    }

    /**
     * Get the velocity on a body (in world units) at a point on the body in world coordinates.
     */ 
    public Vector2 getVelocityAtWorldPoint(float point_x, float point_y, Vector2 out_vel) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniBody.getVelocityAtWorldPoint(nativeAddress, point_x, point_y, floats4);
        return out_vel.set(floats4[0], floats4[1]);
    }

    /**
     * Get the velocity on a body (in world units) at a point on the body in local coordinates.
     */ 
    public Vector2 getVelocityAtLocalPoint(float point_x, float point_y, Vector2 out_vel) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniBody.getVelocityAtLocalPoint(nativeAddress, point_x, point_y, floats4);
        return out_vel.set(floats4[0], floats4[1]);
    }

    /**
     * Get the amount of kinetic energy contained by the body.
     * @return 
     */ 
    public float kineticEnergy() {
        return JniBody.kineticEnergy(nativeAddress);
    }

    static final CpBodyConstraintIteratorFunc syncConstraintFunc = (body, constraint)->{
        body.constraints.add(constraint);
    };

    /**
     * Body/arbiter iterator callback function type.
     * Call func once for each arbiter that is currently active on the body.
     */ 
    public void eachArbiter(CpBodyArbiterIteratorFunc iteratorFunc) {
        this.arbiteIteratorFunc = iteratorFunc;
        JniBody.eachArbiter(nativeAddress, JniChipmunk.bodyArbiterIteratorJniFunc,  chipmunk.jniChipmunk.nativeAddress);
        this.arbiteIteratorFunc = null;
    }


    public static enum Type {
        Dynamic(0), Kinematic(1), Static(2);
        public final int value;
        
        Type(int value) {
            this.value = value;
        }
        public static Type valueOf(int value) {
            switch (value) {
            case 1: return Kinematic;
            case 2: return Static;
            default: return Dynamic;
            }
        }
    }
}
