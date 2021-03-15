package net.josid.gdx.chipmunk;

import com.badlogic.gdx.utils.Pool;

import net.josid.gdx.chipmunk.constraint.CpDampedRotarySpring;
import net.josid.gdx.chipmunk.constraint.CpDampedSpring;
import net.josid.gdx.chipmunk.constraint.CpGearJoint;
import net.josid.gdx.chipmunk.constraint.CpGrooveJoint;
import net.josid.gdx.chipmunk.constraint.CpPinJoint;
import net.josid.gdx.chipmunk.constraint.CpPivotJoint;
import net.josid.gdx.chipmunk.constraint.CpRatchetJoint;
import net.josid.gdx.chipmunk.constraint.CpRotaryLimitJoint;
import net.josid.gdx.chipmunk.constraint.CpSimpleMotor;
import net.josid.gdx.chipmunk.constraint.CpSlideJoint;
import net.josid.gdx.chipmunk.shape.CpCircle;
import net.josid.gdx.chipmunk.shape.CpPoly;
import net.josid.gdx.chipmunk.shape.CpSegment;


class ChipmunkPools {

    final Pool<CpBody> bodies;

    final Pool<CpCircle> circles;
    final Pool<CpSegment> segments;
    final Pool<CpPoly> polygons;

    final Pool<CpDampedRotarySpring> dampedRotarySprings;
    final Pool<CpDampedSpring> dampedSprings;
    final Pool<CpGearJoint> gearJoints;
    final Pool<CpGrooveJoint> grooveJoints;
    final Pool<CpPinJoint> pinJoints;
    final Pool<CpPivotJoint> pivotJoints;
    final Pool<CpRatchetJoint> ratchetJoints;
    final Pool<CpRotaryLimitJoint> rotaryLimitJoints;
    final Pool<CpSimpleMotor> simpleMotors;
    final Pool<CpSlideJoint> slideJoint;


    // TODO: Pool size configuration builder
    public ChipmunkPools(Chipmunk chipmunk, ChipmunkAllocator allocator) {
        bodies = new Pool<CpBody>() {
            @Override protected CpBody newObject() { return allocator.newBody(); }
            @Override protected void reset(CpBody object) { object.reset(); }
        };

        circles = new Pool<CpCircle>() {
            @Override protected CpCircle newObject() { return allocator.newCircle();}
            @Override protected void reset(CpCircle object) { object.reset(); }
        };
        segments = new Pool<CpSegment>() {
            @Override protected CpSegment newObject() { return allocator.newSgment(); }
            @Override protected void reset(CpSegment object) { object.reset(); }
        };
        polygons = new Pool<CpPoly>() {
            @Override protected CpPoly newObject() { return allocator.newPolygon(); }
            @Override protected void reset(CpPoly object) { object.reset(); }
        };

        dampedRotarySprings = new Pool<CpDampedRotarySpring>() {
            @Override protected CpDampedRotarySpring newObject() { return allocator.newDampedRotarySpring(); }
            @Override protected void reset(CpDampedRotarySpring object) { object.reset(); }
        };
        dampedSprings = new Pool<CpDampedSpring>() {
            @Override protected CpDampedSpring newObject() { return allocator.newDampedSpring(); }
            @Override protected void reset(CpDampedSpring object) { object.reset(); }
        };
        gearJoints = new Pool<CpGearJoint>() {
            @Override protected CpGearJoint newObject() { return allocator.newGearJoint(); }
            @Override protected void reset(CpGearJoint object) { object.reset(); }
        };
        grooveJoints = new Pool<CpGrooveJoint>() {
            @Override protected CpGrooveJoint newObject() { return allocator.newGrooveJoint(); }
            @Override protected void reset(CpGrooveJoint object) { object.reset(); }
        };
        pinJoints = new Pool<CpPinJoint>() {
            @Override protected CpPinJoint newObject() { return allocator.newPinJoint(); }
            @Override protected void reset(CpPinJoint object) { object.reset(); }
        };
        pivotJoints = new Pool<CpPivotJoint>() {
            @Override protected CpPivotJoint newObject() { return allocator.newPivotJoint(); }
            @Override protected void reset(CpPivotJoint object) { object.reset(); }
        };
        ratchetJoints = new Pool<CpRatchetJoint>() {
            @Override protected CpRatchetJoint newObject() { return allocator.newRatchetJoint(); }
            @Override protected void reset(CpRatchetJoint object) { object.reset(); }
        };
        rotaryLimitJoints = new Pool<CpRotaryLimitJoint>() {
            @Override protected CpRotaryLimitJoint newObject() { return allocator.newRotaryLimitJoint(); }
            @Override protected void reset(CpRotaryLimitJoint object) { object.reset(); }
        };
        simpleMotors = new Pool<CpSimpleMotor>() {
            @Override protected CpSimpleMotor newObject() { return allocator.newSimpleMotor(); }
            @Override protected void reset(CpSimpleMotor object) { object.reset(); }
        };
        slideJoint = new Pool<CpSlideJoint>() {
            @Override protected CpSlideJoint newObject() { return allocator.newSlideJoint(); }
            @Override protected void reset(CpSlideJoint object) { object.reset(); }
        };
    }

    void free(CpBody body) {
        for (int i = 0; i < body.shapes.size; i++)
            free(body.shapes.get(i));
        for (int i = 0; i < body.constraints.size; i++)
            free(body.constraints.get(i));
        bodies.free(body);
    }

    void free(CpShape shape) {
        switch (shape.type) {
        case Circle: circles.free((CpCircle) shape); break;
        case Segment: segments.free((CpSegment) shape); break;
        case Polygon: polygons.free((CpPoly) shape); break;
        }
    }

    void free(CpConstraint constraint) {
        switch (constraint.type) {
        case DampedRotarySpring: dampedRotarySprings.free((CpDampedRotarySpring) constraint); break;
        case DampedSpring: dampedSprings.free((CpDampedSpring) constraint); break;
        case GearJoint: gearJoints.free((CpGearJoint) constraint); break;
        case GrooveJoint: grooveJoints.free((CpGrooveJoint) constraint); break;
        case PinJoint: pinJoints.free((CpPinJoint) constraint); break;
        case PivotJoint: pivotJoints.free((CpPivotJoint) constraint); break;
        case RatchetJoint: ratchetJoints.free((CpRatchetJoint) constraint); break;
        case RotaryLimitJoint: rotaryLimitJoints.free((CpRotaryLimitJoint) constraint); break;
        case SimpleMotor: simpleMotors.free((CpSimpleMotor) constraint); break;
        case SlideJoint: slideJoint.free((CpSlideJoint) constraint); break;
        }
    }

}
