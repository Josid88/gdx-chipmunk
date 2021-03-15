package net.josid.gdx.chipmunk;

import com.badlogic.gdx.utils.Disposable;

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
import net.josid.gdx.chipmunk.constraint.InternalConstraintType;
import net.josid.gdx.chipmunk.shape.CpCircle;
import net.josid.gdx.chipmunk.shape.CpPoly;
import net.josid.gdx.chipmunk.shape.CpSegment;
import net.josid.gdx.chipmunk.shape.InternalShapeType;


class ChipmunkAllocator implements Disposable {

    final Chipmunk chipmunk;
    final ChipmunkRegistry registry;
    final JniChipmunk jniChipmunk;

    ChipmunkAllocator(Chipmunk chipmunk, ChipmunkRegistry registry, JniChipmunk jniChipmunk) {
        this.chipmunk = chipmunk;
        this.registry = registry;
        this.jniChipmunk = jniChipmunk;
    }


    CpSpace newSpace() {
        CpSpace space = new CpSpace(JniSpace.newSpace(), JniSpace.gdxSpaceNew(jniChipmunk.nativeAddress), chipmunk);
        registry.addSpace(space.nativeAddress, space);
        return space;
    }

    CpBody newBody() {
        CpBody body = new CpBody(JniBody.alloc(), chipmunk);
        registry.addBody(body.nativeAddress, body);
        return body;
    }

    CpCircle newCircle() {
        @SuppressWarnings("deprecation")
        CpCircle circle = InternalShapeType.newCircle(chipmunk);
        registry.addShape(circle.nativeAddress, circle);
        return circle;
    }

    CpSegment newSgment() {
        @SuppressWarnings("deprecation")
        CpSegment segment = InternalShapeType.newSegment(chipmunk);
        registry.addShape(segment.nativeAddress, segment);
        return segment;
    }

    CpPoly newPolygon() {
        @SuppressWarnings("deprecation")
        CpPoly poly = InternalShapeType.newPoly(chipmunk);
        registry.addShape(poly.nativeAddress, poly);
        return poly;
    }

    CpDampedRotarySpring newDampedRotarySpring() {
        @SuppressWarnings("deprecation")
        CpDampedRotarySpring dampedRotarySpring = InternalConstraintType.newDampedRotarySpring(chipmunk);
        registry.addConstraint(dampedRotarySpring.nativeAddress, dampedRotarySpring);
        return dampedRotarySpring;
    }

    CpDampedSpring newDampedSpring() {
        @SuppressWarnings("deprecation")
        CpDampedSpring dampedSpring = InternalConstraintType.newDampedSpring(chipmunk);
        registry.addConstraint(dampedSpring.nativeAddress, dampedSpring);
        return dampedSpring;
    }

    CpGearJoint newGearJoint() {
        @SuppressWarnings("deprecation")
        CpGearJoint gearJoint = InternalConstraintType.newGearJoint(chipmunk);
        registry.addConstraint(gearJoint.nativeAddress, gearJoint);
        return gearJoint;
    }

    CpGrooveJoint newGrooveJoint() {
        @SuppressWarnings("deprecation")
        CpGrooveJoint grooveJoint = InternalConstraintType.newGrooveJoint(chipmunk);
        registry.addConstraint(grooveJoint.nativeAddress, grooveJoint);
        return grooveJoint;
    }

    CpPinJoint newPinJoint() {
        @SuppressWarnings("deprecation")
        CpPinJoint pinJoint = InternalConstraintType.newPinJoint(chipmunk);
        registry.addConstraint(pinJoint.nativeAddress, pinJoint);
        return pinJoint;
    }

    CpPivotJoint newPivotJoint() {
        @SuppressWarnings("deprecation")
        CpPivotJoint pivotJoint = InternalConstraintType.newPivotJoint(chipmunk);
        registry.addConstraint(pivotJoint.nativeAddress, pivotJoint);
        return pivotJoint;
    }

    CpRatchetJoint newRatchetJoint() {
        @SuppressWarnings("deprecation")
        CpRatchetJoint ratchetJoint = InternalConstraintType.newRatchetJoint(chipmunk);
        registry.addConstraint(ratchetJoint.nativeAddress, ratchetJoint);
        return ratchetJoint;
    }

    CpRotaryLimitJoint newRotaryLimitJoint() {
        @SuppressWarnings("deprecation")
        CpRotaryLimitJoint rotaryLimitJoint = InternalConstraintType.newRotaryLimitJoint(chipmunk);
        registry.addConstraint(rotaryLimitJoint.nativeAddress, rotaryLimitJoint);
        return rotaryLimitJoint;
    }

    CpSimpleMotor newSimpleMotor() {
        @SuppressWarnings("deprecation")
        CpSimpleMotor simpleMotor = InternalConstraintType.newSimpleMotor(chipmunk);
        registry.addConstraint(simpleMotor.nativeAddress, simpleMotor);
        return simpleMotor;
    }

    CpSlideJoint newSlideJoint() {
        @SuppressWarnings("deprecation")
        CpSlideJoint slideJoint = InternalConstraintType.newSlideJoint(chipmunk);
        registry.addConstraint(slideJoint.nativeAddress, slideJoint);
        return slideJoint;
    }


    @Override
    public void dispose() {
        registry.forEachConstraint(constraint -> {
            if (null != constraint.space) constraint.space.removeConstraint(constraint);
            JniConstraint.free(constraint.nativeAddress);
        });
        registry.forEachShape(shape -> {
            if (null != shape.space) shape.space.removeShape(shape);
            JniShape.free(shape.nativeAddress);
        });
        registry.forEachBody(body -> {
            if (null != body.space) body.space.removeBody(body);
            JniBody.free(body.nativeAddress);
        });
        registry.forEachSpace(space -> {
            JniSpace.free(space.nativeAddress);
            JniSpace.gdxSpaceFree(space.gdxNativeAddress);
        });
        registry.clear();
    }

}
