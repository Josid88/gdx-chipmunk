package net.josid.gdx.chipmunk.constraint;

import net.josid.gdx.chipmunk.Chipmunk;
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


public class InternalConstraintType {


    @Deprecated
    public static CpDampedRotarySpring newDampedRotarySpring(Chipmunk chipmunk) {
        return new CpDampedRotarySpring(JniConstraintType.dampedRotarySpringAlloc(), chipmunk);
    }

    @Deprecated
    public static void initDampedRotarySpring(long constraint, long bodyA, long bodyB, CpDampedRotarySpringDef def) {
        JniConstraintType.dampedRotarySpringInit(constraint, bodyA, bodyB, def._restAngle, def._stiffness, def._damping);
    }


    @Deprecated
    public static CpDampedSpring newDampedSpring(Chipmunk chipmunk) {
        return new CpDampedSpring(JniConstraintType.dampedSpringAlloc(), chipmunk);
    }

    @Deprecated
    public static void initDampedSpring(long constraint, long bodyA, long bodyB, CpDampedSpringDef def) {
        JniConstraintType.dampedSpringInit(constraint, bodyA, bodyB, def._anchorA.x, def._anchorA.y, def._anchorB.x, def._anchorB.y,
                def._restLength, def._stiffness, def._damping);
    }


    @Deprecated
    public static CpGearJoint newGearJoint(Chipmunk chipmunk) {
        return new CpGearJoint(JniConstraintType.gearJointAlloc(), chipmunk);
    }

    @Deprecated
    public static void initGearJoint(long constraint, long bodyA, long bodyB, CpGearJointDef def) {
        JniConstraintType.gearJointInit(constraint, bodyA, bodyB, def._phase, def._ratio);
    }


    @Deprecated
    public static CpGrooveJoint newGrooveJoint(Chipmunk chipmunk) {
        return new CpGrooveJoint(JniConstraintType.grooveJointAlloc(), chipmunk);
    }

    @Deprecated
    public static void initGrooveJoint(long constraint, long bodyA, long bodyB, CpGrooveJointDef def) {
        JniConstraintType.grooveJointInit(constraint, bodyA, bodyB, def._grooveA.x, def._grooveA.y, def._grooveB.x, def._grooveB.y,
                def._anchorB.x, def._anchorB.y);
    }


    @Deprecated
    public static CpPinJoint newPinJoint(Chipmunk chipmunk) {
        return new CpPinJoint(JniConstraintType.pinJointAlloc(), chipmunk);
    }

    @Deprecated
    public static void initPinJoint(long constraint, long bodyA, long bodyB, CpPinJointDef def) {
        JniConstraintType.pinJointInit(constraint, bodyA, bodyB, def._anchorA.x, def._anchorA.y, def._anchorB.x, def._anchorB.y);
    }


    @Deprecated
    public static CpPivotJoint newPivotJoint(Chipmunk chipmunk) {
        return new CpPivotJoint(JniConstraintType.pivotJointAlloc(), chipmunk);
    }

    @Deprecated
    public static void initPivotJoint(long constraint, long bodyA, long bodyB, CpPivotJointDef def) {
        JniConstraintType.pivotJointInit(constraint, bodyA, bodyB, def._anchorA.x, def._anchorA.y, def._anchorB.x, def._anchorB.y);
    }


    @Deprecated
    public static CpRatchetJoint newRatchetJoint(Chipmunk chipmunk) {
        return new CpRatchetJoint(JniConstraintType.ratchetJointAlloc(), chipmunk);
    }

    @Deprecated
    public static void initRatchetJoint(long constraint, long bodyA, long bodyB, CpRatchetJointDef def) {
        JniConstraintType.ratchetJointInit(constraint, bodyA, bodyB, def._phase, def._ratchet);
    }


    @Deprecated
    public static CpRotaryLimitJoint newRotaryLimitJoint(Chipmunk chipmunk) {
        return new CpRotaryLimitJoint(JniConstraintType.rotaryLimitJointAlloc(), chipmunk);
    }

    @Deprecated
    public static void initRotaryLimitJoint(long constraint, long bodyA, long bodyB, CpRotaryLimitJointDef def) {
        JniConstraintType.rotaryLimitJointInit(constraint, bodyA, bodyB, def._min, def._max);
    }


    @Deprecated
    public static CpSimpleMotor newSimpleMotor(Chipmunk chipmunk) {
        return new CpSimpleMotor(JniConstraintType.simpleMotorAlloc(), chipmunk);
    }

    @Deprecated
    public static void initSimpleMotor(long constraint, long bodyA, long bodyB, CpSimpleMotorDef def) {
        JniConstraintType.simpleMotorInit(constraint, bodyA, bodyB, def._rate);
    }


    @Deprecated
    public static CpSlideJoint newSlideJoint(Chipmunk chipmunk) {
        return new CpSlideJoint(JniConstraintType.slideJointAlloc(), chipmunk);
    }

    @Deprecated
    public static void initSlideJoint(long constraint, long bodyA, long bodyB, CpSlideJointDef def) {
        JniConstraintType.slideJointInit(constraint, bodyA, bodyB, def._anchorA.x, def._anchorA.y, def._anchorB.x, def._anchorB.y,
                def._min, def._max);
    }

}
