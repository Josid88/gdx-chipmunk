package net.josid.gdx.chipmunk;

import net.josid.gdx.chipmunk.def.CpBodyInstanceDef;
import net.josid.gdx.chipmunk.def.CpConstraintDef;
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
import net.josid.gdx.chipmunk.def.CpBodyDef;
import net.josid.gdx.chipmunk.def.CpShapeDef;
import net.josid.gdx.chipmunk.def.CpSpaceDef;
import net.josid.gdx.chipmunk.def.constraint.CpDampedRotarySpringDef;
import net.josid.gdx.chipmunk.def.constraint.CpDampedSpringDef;
import net.josid.gdx.chipmunk.def.constraint.CpGearJointDef;
import net.josid.gdx.chipmunk.def.constraint.CpGrooveJointDef;
import net.josid.gdx.chipmunk.def.constraint.CpPinJointDef;
import net.josid.gdx.chipmunk.def.constraint.CpPivotJointDef;
import net.josid.gdx.chipmunk.def.constraint.CpRatchetJointDef;
import net.josid.gdx.chipmunk.def.constraint.CpRotaryLimitJointDef;
import net.josid.gdx.chipmunk.def.constraint.CpSimpleMotorDef;
import net.josid.gdx.chipmunk.def.constraint.CpSlideJointDef;
import net.josid.gdx.chipmunk.def.shape.CpCircleDef;
import net.josid.gdx.chipmunk.def.shape.CpPolyBoxDef;
import net.josid.gdx.chipmunk.def.shape.CpPolyDef;
import net.josid.gdx.chipmunk.def.shape.CpSegmentDef;
import net.josid.gdx.chipmunk.shape.CpCircle;
import net.josid.gdx.chipmunk.shape.CpPoly;
import net.josid.gdx.chipmunk.shape.CpSegment;
import net.josid.gdx.chipmunk.shape.InternalShapeType;


public class ChipmunkFactory {

    private final ChipmunkAllocator allocator;
    private final ChipmunkPools pools;
    private final CpBodyInstanceDef staticDef = new CpBodyInstanceDef() {{_type = CpBody.Type.Static;}};
    private final CpBodyInstanceDef kynematicDef = new CpBodyInstanceDef() {{_type = CpBody.Type.Kinematic;}};
    private final CpBodyInstanceDef dynamicDef = new CpBodyInstanceDef() {{_type = CpBody.Type.Dynamic;}};

    public ChipmunkFactory(ChipmunkAllocator allocator, ChipmunkPools pools) {
        super();
        this.allocator = allocator;
        this.pools = pools;
    }

    public CpSpace space() {
        return allocator.newSpace();
    }

    public CpSpace space(CpSpaceDef def) {
        CpSpace space = allocator.newSpace();
        JniSpace.gdxSpaceInit(space.nativeAddress, def._iterations, def._gravityX, def._gravityY,
            def._damping.isSet, def._damping.value, def._idleSpeedThreshold,
            def._sleepTimeThreshold.isSet, def._sleepTimeThreshold.value, def._collisionSlop.isSet, def._collisionSlop.value, 
            def._collisionBias.isSet, def._collisionBias.value, def._collisionPersistence);
        return space;
    }

    public CpBody body(CpBodyDef def, CpBody.Type type) {
        CpBodyInstanceDef instanceDef = null;
        switch (type) {
            case Static: instanceDef = staticDef; break;
            case Kinematic: instanceDef = kynematicDef; break;
            case Dynamic: instanceDef = dynamicDef; break;
            default: throw new IllegalStateException("Body type not implemented");
        }
        return body(def, instanceDef);
    }

    public CpBody body(CpBodyDef def, CpBodyInstanceDef instanceDef) {
        CpBody body = pools.bodies.obtain();
        JniBody.gdxBodyInit(body.nativeAddress, instanceDef._type.value, instanceDef._position.x, instanceDef._position.y,
                instanceDef._angle, instanceDef._velocity.x, instanceDef._velocity.y, instanceDef._angularVeloctiy);
        for (int i = 0; i < def._shapes.size; i++) {
            shape(def._shapes.get(i), body);
        }
        return body;
    }


    public CpShape shape(CpShapeDef def) {
        return shape(def, null);
    }

    public CpShape shape(CpShapeDef def, CpBody body) {
        switch (def.getType()) {
        case Circle: return circle((CpCircleDef) def, body);
        case Segment: return segment((CpSegmentDef) def, body);
        case Box: return box((CpPolyBoxDef)def, body);
        case Polygon: return poly((CpPolyDef) def, body);
        default: throw new IllegalStateException("Shape type not implemented");
        }
    }

    public CpCircle circle(CpCircleDef def) {
        return circle(def, null);
    }

    @SuppressWarnings("deprecation")
    public CpCircle circle(CpCircleDef def, CpBody body) {
        CpCircle circle = pools.circles.obtain();
        InternalShapeType.circleInit(circle.nativeAddress, addShapeGetBodyAddress(circle, body), def);
        return circle;
    }

    public CpSegment segment(CpSegmentDef def) {
        return segment(def, null);
    }

    @SuppressWarnings("deprecation")
    public CpSegment segment(CpSegmentDef def, CpBody body) {
        CpSegment segment = pools.segments.obtain();
        InternalShapeType.segmentInit(segment.nativeAddress, addShapeGetBodyAddress(segment, body), def);
        return segment;
    }

    public CpPoly poly(CpPolyDef def) {
        return poly(def, null);
    }

    @SuppressWarnings("deprecation")
    public CpPoly poly(CpPolyDef def, CpBody body) {
        CpPoly poly = pools.polygons.obtain();
        InternalShapeType.polyInit(poly.nativeAddress, addShapeGetBodyAddress(poly, body), def);
        return poly;
    }

    public CpPoly box(CpPolyBoxDef def) {
        return box(def, null);
    }

    @SuppressWarnings("deprecation")
    public CpPoly box(CpPolyBoxDef def, CpBody body) {
        CpPoly poly = pools.polygons.obtain();
        InternalShapeType.boxInit(poly.nativeAddress, addShapeGetBodyAddress(poly, body), def);
        return poly;
    }

    private long addShapeGetBodyAddress(CpShape shape, CpBody body) {
        if (null != body) {
            body.shapes.add(shape);
            shape.body = body;
            return body.nativeAddress;
        }
        return 0;
    }


    public CpConstraint constraint(CpConstraintDef def) {
        switch (def.getType()) {
        case DampedRotarySpring: return dampedRotarySpring((CpDampedRotarySpringDef)def);
        case DampedSpring: return dampedSpring((CpDampedSpringDef)def);
        case GearJoint: return gearJoint((CpGearJointDef)def);
        case GrooveJoint: return grooveJoint((CpGrooveJointDef) def);
        case PinJoint: return pinJoint((CpPinJointDef) def);
        case PivotJoint: return pivotJoint((CpPivotJointDef) def);
        case RatchetJoint: return ratchetJoint((CpRatchetJointDef) def);
        case RotaryLimitJoint: return rotaryLimitJoint((CpRotaryLimitJointDef) def);
        case SimpleMotor: return simpleMotor((CpSimpleMotorDef) def);
        case SlideJoint: return slideJoint((CpSlideJointDef) def);
        default: throw new IllegalStateException("Constraint type not implemented");
        }
    }

    @SuppressWarnings("deprecation")
    public CpDampedRotarySpring dampedRotarySpring(CpDampedRotarySpringDef def) {
        CpDampedRotarySpring constraint = pools.dampedRotarySprings.obtain();
        InternalConstraintType.initDampedRotarySpring(constraint.nativeAddress, address(def._bodyA), address(def._bodyB), def);
        initConstraint(def, constraint);
        return constraint;
    }

    @SuppressWarnings("deprecation")
    public CpDampedSpring dampedSpring(CpDampedSpringDef def) {
        CpDampedSpring constraint = pools.dampedSprings.obtain();
        InternalConstraintType.initDampedSpring(constraint.nativeAddress, address(def._bodyA), address(def._bodyB), def);
        initConstraint(def, constraint);
        return constraint;
    }

    @SuppressWarnings("deprecation")
    public CpGearJoint gearJoint(CpGearJointDef def) {
        CpGearJoint constraint = pools.gearJoints.obtain();
        InternalConstraintType.initGearJoint(constraint.nativeAddress, address(def._bodyA), address(def._bodyB), def);
        initConstraint(def, constraint);
        return constraint;
    }

    @SuppressWarnings("deprecation")
    public CpGrooveJoint grooveJoint(CpGrooveJointDef def) {
        CpGrooveJoint constraint = pools.grooveJoints.obtain();
        InternalConstraintType.initGrooveJoint(constraint.nativeAddress, address(def._bodyA), address(def._bodyB), def);
        initConstraint(def, constraint);
        return constraint;
    }

    @SuppressWarnings("deprecation")
    public CpPinJoint pinJoint(CpPinJointDef def) {
        CpPinJoint constraint = pools.pinJoints.obtain();
        InternalConstraintType.initPinJoint(constraint.nativeAddress, address(def._bodyA), address(def._bodyB), def);
        initConstraint(def, constraint);
        return constraint;
    }

    @SuppressWarnings("deprecation")
    public CpPivotJoint pivotJoint(CpPivotJointDef def) {
        CpPivotJoint constraint = pools.pivotJoints.obtain();
        InternalConstraintType.initPivotJoint(constraint.nativeAddress, address(def._bodyA), address(def._bodyB), def);
        initConstraint(def, constraint);
        return constraint;
    }

    @SuppressWarnings("deprecation")
    public CpRatchetJoint ratchetJoint(CpRatchetJointDef def) {
        CpRatchetJoint constraint = pools.ratchetJoints.obtain();
        InternalConstraintType.initRatchetJoint(constraint.nativeAddress, address(def._bodyA), address(def._bodyB), def);
        initConstraint(def, constraint);
        return constraint;
    }

    @SuppressWarnings("deprecation")
    public CpRotaryLimitJoint rotaryLimitJoint(CpRotaryLimitJointDef def) {
        CpRotaryLimitJoint constraint = pools.rotaryLimitJoints.obtain();
        InternalConstraintType.initRotaryLimitJoint(constraint.nativeAddress, address(def._bodyA), address(def._bodyB), def);
        initConstraint(def, constraint);
        return constraint;
    }

    @SuppressWarnings("deprecation")
    public CpSimpleMotor simpleMotor(CpSimpleMotorDef def) {
        CpSimpleMotor constraint = pools.simpleMotors.obtain();
        InternalConstraintType.initSimpleMotor(constraint.nativeAddress, address(def._bodyA), address(def._bodyB), def);
        initConstraint(def, constraint);
        return constraint;
    }

    @SuppressWarnings("deprecation")
    public CpSlideJoint slideJoint(CpSlideJointDef def) {
        CpSlideJoint constraint = pools.slideJoint.obtain();
        InternalConstraintType.initSlideJoint(constraint.nativeAddress, address(def._bodyA), address(def._bodyB), def);
        initConstraint(def, constraint);
        return constraint;
    }

    private long address(CpBody body) {
        return null != body ? body.nativeAddress : 0;
    }

    private void initConstraint(CpConstraintDef def, CpConstraint constraint) {
        JniConstraint.constraintSetOnInit(constraint.nativeAddress, def._maxForce.isSet, def._maxForce.value,
                def._errorBias.isSet, def._errorBias.value, def._maxBias.isSet, def._maxBias.value, def._collideBodies);
        constraint.setBodies(def._bodyA, def._bodyB);
    }

}
