package net.josid.gdx.chipmunk;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.contact.CpContactPointSet;
import net.josid.gdx.chipmunk.contact.CpShapeFilter;
import net.josid.gdx.chipmunk.math.CpBoundingBox;
import net.josid.gdx.chipmunk.query.CpPointQueryInfo;
import net.josid.gdx.chipmunk.query.CpSegmentQueryInfo;


public abstract class CpShape {

    protected final long nativeAddress;
    public final Chipmunk chipmunk;
    public final Type type;
    CpBody body;
    CpSpace space;
    private Object userData;

    protected CpShape(long nativeAddress, Chipmunk chipmunk, Type type) {
        this.nativeAddress = nativeAddress;
        this.chipmunk = chipmunk;
        this.type = type;
    }

    protected void reset() {
        body = null;
        space = null;
        userData = null;
    }

    public Object getUserData() {
        return userData;
    }

    public void setUserData(Object userData) {
        this.userData = userData;
    }

    public Type getType() {
        return type;
    }

    public CpSpace getSpace() {
        return space;
    }

    public CpBody getBody() {
        return body;
    }

    public void free() {
        chipmunk.pools.free(this);
    }

    protected float[] getFloats4() {
        return chipmunk.arrays.getFloats4(space);
    }


    /**
     * Update, cache and return the bounding box of a shape based on the body it's attached to.
     */ 
    public CpBoundingBox cacheBB(CpBoundingBox out_boundingBox) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniShape.cacheBB(nativeAddress, floats4);
        return out_boundingBox.set(floats4);
    }

    /**
     * Update, cache and return the bounding box of a shape with an explicit transformation.
     */ 
    public CpBoundingBox update(float offsetX, float offsetY, float angle, CpBoundingBox out_boundingBox) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniShape.update(nativeAddress, offsetX, offsetY, angle, floats4);
        return out_boundingBox.set(floats4);
    }

    /**
     * Perform a nearest point query.
     * It finds the closest point on the surface of shape to a specific point.
     * @return distance between the points. A negative distance means the point is inside the shape.
     */ 
    public CpPointQueryInfo pointQuery(float x, float y, CpPointQueryInfo out_info) {
        float[] floats8 = chipmunk.arrays.getFloats8(space);
        JniShape.pointQuery(nativeAddress, x, y, floats8);
        return out_info.set(this, floats8);
    }

    /**
     * Perform a segment query against a shape.
     */ 
    public CpSegmentQueryInfo segmentQuery(float aX, float aY, float bX, float bY, float radius, CpSegmentQueryInfo out_info) {
        float[] floats8 = chipmunk.arrays.getFloats8(space);
        boolean collides = JniShape.segmentQuery(nativeAddress, aX, aY, bX, bY, radius, floats8);
        return out_info.set(collides, this, floats8);
    }

    /**
     * Return contact information about two shapes.
     */
    public CpContactPointSet collide(CpShape shapeB, CpContactPointSet out_pointSet) {
        float[] floats16 = chipmunk.arrays.getFloats16(space);
        int count = JniShape.collide(nativeAddress, shapeB.nativeAddress, floats16);
        return out_pointSet.set(count, floats16);
    }

    /**
     * Set the cpBody this shape is connected to.
     * Can only be used if the shape is not currently added to a space.
     */ 
    public void setBody(CpBody body) {
        if (null != space) {
            throw new IllegalStateException("Shape currently added to space");
        }            
        JniShape.setBody(nativeAddress, body.nativeAddress);
    }

    /**
     * Get the mass of the shape if you are having Chipmunk calculate mass properties for you.
     */ 
    public float getMass() {
        return JniShape.getMass(nativeAddress);
    }

    /**
     * Set the mass of this shape to have Chipmunk calculate mass properties for you.
     */ 
    public void setMass(float mass) {
        JniShape.setMass(nativeAddress, mass);
    }

    /**
     * Get the density of the shape if you are having Chipmunk calculate mass properties for you.
     */ 
    public float getDensity() {
        return JniShape.getDensity(nativeAddress);
    }

    /**
     * Set the density  of this shape to have Chipmunk calculate mass properties for you.
     */ 
    public void setDensity(float density) {
        JniShape.setDensity(nativeAddress, density);
    }

    /**
     * Get the calculated moment of inertia for this shape.
     */ 
    public float getMoment() {
        return JniShape.getMoment(nativeAddress);
    }

    /**
     * Get the calculated area of this shape.
     */ 
    public float getArea() {
        return JniShape.getArea(nativeAddress);
    }

    /**
     * Get the centroid of this shape.
     */ 
    public Vector2 getCenterOfGravity(Vector2 out_cog) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniShape.getCenterOfGravity(nativeAddress, floats4);
        return out_cog.set(floats4[0], floats4[1]);
    }

    /**
     * Get the bounding box that contains the shape given it's current position and angle.
     */ 
    public CpBoundingBox getBB(CpBoundingBox out_bb) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniShape.getBB(nativeAddress, floats4);
        return out_bb.set(floats4);
    }

    /**
     * Get if the shape is set to be a sensor or not.
     */ 
    public boolean getSensor() {
        return JniShape.getSensor(nativeAddress);
    }

    /**
     * Set if the shape is a sensor or not.
     */ 
    public void setSensor(boolean sensor) {
        JniShape.setSensor(nativeAddress, sensor);
    }

    /**
     * Get the elasticity of this shape.
     */ 
    public float getElasticity() {
        return JniShape.getElasticity(nativeAddress);
    }

    /**
     * Set the elasticity of this shape.
     */ 
    public void setElasticity(float elasticity) {
        JniShape.setElasticity(nativeAddress, elasticity);
    }

    /**
     * Get the friction of this shape.
     */ 
    public float getFriction() {
        return JniShape.getFriction(nativeAddress);
    }

    /**
     * Set the friction of this shape.
     */ 
    public void setFriction(float friction) {
        JniShape.setFriction(nativeAddress, friction);
    }

    /**
     * Get the surface velocity of this shape.
     */ 
    public Vector2 getSurfaceVelocity(Vector2 out_sv) {
        float[] floats4 = chipmunk.arrays.getFloats4(space);
        JniShape.getSurfaceVelocity(nativeAddress, floats4);
        return out_sv.set(floats4[0], floats4[1]);
    }

    /**
     * Set the surface velocity of this shape.
     */ 
    public void setSurfaceVelocity(float velocity_x, float velocity_y) {
        JniShape.setSurfaceVelocity(nativeAddress, velocity_x, velocity_y);
    }

    /**
     * Set the collision type of this shape. 
     */ 
    public long getCollisionType() {
        return JniShape.getCollisionType(nativeAddress);
    }

    /**
     * Get the collision type of this shape.
     */ 
    public void setCollisionType(long collisionType) {
        JniShape.setCollisionType(nativeAddress, collisionType);
    }

    /**
     * Get the collision filtering parameters of this shape.
     */ 
    public CpShapeFilter getFilter(CpShapeFilter out_filter) {
        int[] ints4 = chipmunk.arrays.getInts4(space);
        JniShape.getFilter(nativeAddress, ints4);
        return out_filter.set(ints4);
    }

    /**
     * Set the collision filtering parameters of this shape.
     */ 
    public void setFilter(int group, int categories, int mask) {
        JniShape.setFilter(nativeAddress, group, categories, mask);
    }


    public static enum Type {
        Circle(0), Segment(1), Polygon(2);
        public final int value;

        Type(int value) {
            this.value = value;
        }
        public static Type valueOf(int value) {
            switch (value) {
            case 1: return Segment;
            case 2: return Polygon;
            default: return Circle;
            }
        }
    }
}
