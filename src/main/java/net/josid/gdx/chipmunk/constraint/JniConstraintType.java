package net.josid.gdx.chipmunk.constraint;

//@off
/*JNI
#include <chipmunk/chipmunk.h>
#include <chipmunk/gdx/gdxChipmunkUtils.h>
*/


class JniConstraintType {

    private JniConstraintType() { }

    /**
     * Check if a constraint is a damped rotary springs.
     * @return 
     */ 
    static native boolean isDampedRotarySpring(long constraint); /*
        return cpConstraintIsDampedRotarySpring((cpConstraint*)constraint);
    */

    /**
     * Function type used for damped rotary spring force callbacks.Allocate a damped rotary spring.
     * @return 
     * @param void
     */ 
    static native long dampedRotarySpringAlloc(); /*
        return (jlong) cpDampedRotarySpringAlloc();
    */

    /**
     * Initialize a damped rotary spring.
     * @return 
     * @param joint
     * @param a
     * @param b
     * @param restAngle
     * @param stiffness
     * @param damping
     */ 
    static native void dampedRotarySpringInit(long constraint, long a, long b, 
            float restAngle, float stiffness, float damping); /*
        cpDampedRotarySpringInit((cpDampedRotarySpring*)constraint, (cpBody*)a, (cpBody*)b, restAngle, stiffness, damping);
    */

    /**
     * Allocate and initialize a damped rotary spring.
     * @return 
     * @param a
     * @param b
     * @param restAngle
     * @param stiffness
     * @param damping
     */ 
    static native long dampedRotarySpringNew(long a, long b, float restAngle,
            float stiffness, float damping); /*
        return (jlong) cpDampedRotarySpringNew((cpBody*)a, (cpBody*)b, restAngle, stiffness, damping);
    */

    /**
     * Get the rest length of the spring.
     * @return 
     */ 
    static native float dampedRotarySpringGetRestAngle(long constraint); /*
        return cpDampedRotarySpringGetRestAngle((cpConstraint*)constraint);
    */

    /**
     * Set the rest length of the spring.
     * @param restAngle
     */ 
    static native void dampedRotarySpringSetRestAngle(long constraint, float restAngle); /*
        cpDampedRotarySpringSetRestAngle((cpConstraint*)constraint, restAngle);
    */

    /**
     * Get the stiffness of the spring in force/distance.
     * @return 
     */ 
    static native float dampedRotarySpringGetStiffness(long constraint); /*
        return cpDampedRotarySpringGetStiffness((cpConstraint*)constraint);
    */

    /**
     * Set the stiffness of the spring in force/distance.
     * @param stiffness
     */ 
    static native void dampedRotarySpringSetStiffness(long constraint, float stiffness); /*
        cpDampedRotarySpringSetStiffness((cpConstraint*)constraint, stiffness);
    */

    /**
     * Get the damping of the spring.
     * @return 
     */ 
    static native float dampedRotarySpringGetDamping(long constraint); /*
        return cpDampedRotarySpringGetDamping((cpConstraint*)constraint);
    */

    /**
     * Set the damping of the spring.
     * @param damping
     */ 
    static native void dampedRotarySpringSetDamping(long constraint, float damping); /*
        cpDampedRotarySpringSetDamping((cpConstraint*)constraint, damping);
    */

    /**
     * Get the damping of the spring.
     * @return 
     */ 
    static native long dampedRotarySpringGetSpringTorqueFunc(long constraint); /*
        return (jlong) cpDampedRotarySpringGetSpringTorqueFunc((cpConstraint*)constraint);
    */

    /**
     * Set the damping of the spring.
     * @param springTorqueFunc
     */ 
    static native void dampedRotarySpringSetSpringTorqueFunc(long constraint, long springTorqueFunc); /*
        cpDampedRotarySpringSetSpringTorqueFunc((cpConstraint*)constraint, (cpDampedRotarySpringTorqueFunc)springTorqueFunc);
    */

    /**
     * Allocate a damped spring.
     * @return
     */ 
    static native long dampedSpringAlloc(); /*
        return (jlong) cpDampedSpringAlloc();
    */

    /**
     * Initialize a damped spring.
     * @return 
     * @param joint
     * @param a
     * @param b
     * @param anchorA
     * @param anchorB
     * @param restLength
     * @param stiffness
     * @param damping
     */ 
    static native void dampedSpringInit(long constraint, long a, long b, 
            float anchorA_x, float anchorA_y, float anchorB_x, float anchorB_y,
            float restLength, float stiffness, float damping); /*
        cpDampedSpringInit((cpDampedSpring*)constraint, (cpBody*)a, (cpBody*)b,
            cpv(anchorA_x, anchorA_y), cpv(anchorB_x, anchorB_y),
            restLength, stiffness, damping);
    */

    /**
     * Allocate and initialize a damped spring.
     * @return 
     * @param a
     * @param b
     * @param anchorA
     * @param anchorB
     * @param restLength
     * @param stiffness
     * @param damping
     */ 
    static native long dampedSpringNew(long a, long b,
            float anchorA_x, float anchorA_y, float anchorB_x, float anchorB_y,
            float restLength, float stiffness, float damping); /*
        return (jlong) cpDampedSpringNew((cpBody*)a, (cpBody*)b,
            cpv(anchorA_x, anchorA_y), cpv(anchorB_x, anchorB_y), restLength, stiffness, damping);
    */

    /**
     * Get the location of the first anchor relative to the first body.
     * @return 
     */ 
    static native void dampedSpringGetAnchorA(long constraint, float[] out_floats); /*
        cpVect v = cpDampedSpringGetAnchorA((cpConstraint*)constraint);
        gdxCpVectToFloats(&v, out_floats);
    */

    /**
     * Set the location of the first anchor relative to the first body.
     * @param anchorA
     */ 
    static native void dampedSpringSetAnchorA(long constraint, float anchorA_x, float anchorA_y); /*
        cpDampedSpringSetAnchorA((cpConstraint*)constraint, cpv(anchorA_x, anchorA_y));
    */

    /**
     * Get the location of the second anchor relative to the second body.
     * @return 
     */ 
    static native void dampedSpringGetAnchorB(long constraint, float[] out_floats); /*
        cpVect v = cpDampedSpringGetAnchorB((cpConstraint*)constraint);
        gdxCpVectToFloats(&v, out_floats);
    */

    /**
     * Set the location of the second anchor relative to the second body.
     * @param anchorB
     */ 
    static native void dampedSpringSetAnchorB(long constraint, float anchorB_x, float anchorB_y); /*
        cpDampedSpringSetAnchorB((cpConstraint*)constraint, cpv(anchorB_x, anchorB_y));
    */

    /**
     * Get the rest length of the spring.
     * @return 
     */ 
    static native float dampedSpringGetRestLength(long constraint); /*
        return cpDampedSpringGetRestLength((cpConstraint*)constraint);
    */

    /**
     * Set the rest length of the spring.
     * @param restLength
     */ 
    static native void dampedSpringSetRestLength(long constraint, float restLength); /*
        cpDampedSpringSetRestLength((cpConstraint*)constraint, restLength);
    */

    /**
     * Get the stiffness of the spring in force/distance.
     * @return 
     */ 
    static native float dampedSpringGetStiffness(long constraint); /*
        return cpDampedSpringGetStiffness((cpConstraint*)constraint);
    */

    /**
     * Set the stiffness of the spring in force/distance.
     * @param stiffness
     */ 
    static native void dampedSpringSetStiffness(long constraint, float stiffness); /*
        cpDampedSpringSetStiffness((cpConstraint*)constraint, stiffness);
    */

    /**
     * Get the damping of the spring.
     * @return 
     */ 
    static native float dampedSpringGetDamping(long constraint); /*
        return cpDampedSpringGetDamping((cpConstraint*)constraint);
    */

    /**
     * Set the damping of the spring.
     * @param damping
     */ 
    static native void dampedSpringSetDamping(long constraint, float damping); /*
        cpDampedSpringSetDamping((cpConstraint*)constraint, damping);
    */

    /**
     * Get the damping of the spring.
     * @return 
     */ 
    static native long dampedSpringGetSpringForceFunc(long constraint); /*
        return (jlong) cpDampedSpringGetSpringForceFunc((cpConstraint*)constraint);
    */

    /**
     * Set the damping of the spring.
     * @param springForceFunc
     */ 
    static native void dampedSpringSetSpringForceFunc(long constraint, long springForceFunc); /*
        cpDampedSpringSetSpringForceFunc((cpConstraint*)constraint, (cpDampedSpringForceFunc) springForceFunc);
    */

    /**
     * Check if a constraint is a damped rotary springs.
     * @return 
     */ 
    static native boolean isGearJoint(long constraint); /*
        return cpConstraintIsGearJoint((cpConstraint*)constraint);
    */

    /**
     * Allocate a gear joint.
     * @return 
     * @param void
     */ 
    static native long gearJointAlloc(); /*
        return (jlong) cpGearJointAlloc();
    */

    /**
     * Initialize a gear joint.
     * @return 
     * @param joint
     * @param a
     * @param b
     * @param phase
     * @param ratio
     */ 
    static native void gearJointInit(long constraint, long a, long b, float phase, float ratio); /*
        cpGearJointInit((cpGearJoint*)constraint, (cpBody*)a, (cpBody*)b, phase, ratio);
    */

    /**
     * Allocate and initialize a gear joint.
     * @return 
     * @param a
     * @param b
     * @param phase
     * @param ratio
     */ 
    static native long gearJointNew(long a, long b, float phase, float ratio); /*
        return (jlong) cpGearJointNew((cpBody*)a, (cpBody*)b, phase, ratio);
    */

    /**
     * Get the phase offset of the gears.
     * @return 
     */ 
    static native float gearJointGetPhase(long constraint); /*
        return cpGearJointGetPhase((cpConstraint*)constraint);
    */

    /**
     * Set the phase offset of the gears.
     * @param phase
     */ 
    static native void gearJointSetPhase(long constraint, float phase); /*
        cpGearJointSetPhase((cpConstraint*)constraint, phase);
    */

    /**
     * Get the angular distance of each ratchet.
     * @return 
     */ 
    static native float gearJointGetRatio(long constraint); /*
        return cpGearJointGetRatio((cpConstraint*)constraint);
    */

    /**
     * Set the ratio of a gear joint.
     * @param ratio
     */ 
    static native void gearJointSetRatio(long constraint, float ratio); /*
        cpGearJointSetRatio((cpConstraint*)constraint, ratio);
    */

    /**
     * 
     * @return 
     */ 
    static native boolean isGrooveJoint(long constraint); /*
        return cpConstraintIsGrooveJoint((cpConstraint*)constraint);
    */

    /**
     * Allocate a groove joint.
     * @return 
     * @param void
     */ 
    static native long grooveJointAlloc(); /*
        return (jlong) cpGrooveJointAlloc();
    */

    /**
     * Initialize a groove joint.
     * @return 
     * @param joint
     * @param a
     * @param b
     * @param groove_a
     * @param groove_b
     * @param anchorB
     */ 
    static native void grooveJointInit(long constraint, long a, long b,
            float groove_a_x, float groove_a_y, float groove_b_x, float groove_b_y, float anchorB_x, float anchorB_y); /*
        cpGrooveJointInit((cpGrooveJoint*)constraint, (cpBody*)a, (cpBody*)b,
            cpv(groove_a_x, groove_a_y), cpv(groove_b_x, groove_b_y), cpv(anchorB_x, anchorB_y));
    */

    /**
     * Allocate and initialize a groove joint.
     * @return 
     * @param a
     * @param b
     * @param groove_a
     * @param groove_b
     * @param anchorB
     */ 
    static native long grooveJointNew(long a, long b, float groove_a_x, float groove_a_y,
            float groove_b_x, float groove_b_y, float anchorB_x, float anchorB_y); /*
        return (jlong) cpGrooveJointNew((cpBody*)a, (cpBody*)b, cpv(groove_a_x, groove_a_y),
            cpv(groove_b_x, groove_b_y), cpv(anchorB_x, anchorB_y));
    */

    /**
     * Get the first endpoint of the groove relative to the first body.
     * @return 
     */ 
    static native void grooveJointGetGrooveA(long constraint, float[] out_floats2); /*
        cpVect v = cpGrooveJointGetGrooveA((cpConstraint*)constraint);
        gdxCpVectToFloats(&v, out_floats2);
    */

    /**
     * Set the first endpoint of the groove relative to the first body.
     * @param grooveA
     */ 
    static native void grooveJointSetGrooveA(long constraint, float grooveA_x, float grooveA_y); /*
        cpGrooveJointSetGrooveA((cpConstraint*)constraint, cpv(grooveA_x, grooveA_y));
    */

    /**
     * Get the first endpoint of the groove relative to the first body.
     * @return 
     */ 
    static native void grooveJointGetGrooveB(long constraint, float[] out_floats2); /*
        cpVect v = cpGrooveJointGetGrooveB((cpConstraint*)constraint);
        gdxCpVectToFloats(&v, out_floats2);
    */

    /**
     * Set the first endpoint of the groove relative to the first body.
     * @param grooveB
     */ 
    static native void grooveJointSetGrooveB(long constraint, float grooveB_x, float grooveB_y); /*
        cpGrooveJointSetGrooveB((cpConstraint*)constraint, cpv(grooveB_x, grooveB_y));
    */

    /**
     * Get the location of the second anchor relative to the second body.
     * @return 
     */ 
    static native void grooveJointGetAnchorB(long constraint, float[] out_floats2); /*
        cpVect v = cpGrooveJointGetAnchorB((cpConstraint*)constraint);
        gdxCpVectToFloats(&v, out_floats2);
    */

    /**
     * Set the location of the second anchor relative to the second body.
     * @param anchorB
     */ 
    static native void grooveJointSetAnchorB(long constraint, float anchorB_x, float anchorB_y); /*
        cpGrooveJointSetAnchorB((cpConstraint*)constraint, cpv(anchorB_x, anchorB_y));
    */

    /**
     * Check if a constraint is a pin joint.
     * @return 
     */ 
    static native boolean isPinJoint(long constraint); /*
        return cpConstraintIsPinJoint((cpConstraint*)constraint);
    */

    /**
     * Allocate a pin joint.
     * @return 
     * @param void
     */ 
    static native long pinJointAlloc(); /*
        return (jlong) cpPinJointAlloc();
    */

    /**
     * Initialize a pin joint.
     * @return 
     * @param joint
     * @param a
     * @param b
     * @param anchorA
     * @param anchorB
     */ 
    static native void pinJointInit(long constraint, long a, long b, 
            float anchorA_x, float anchorA_y, float anchorB_x, float anchorB_y); /*
        cpPinJointInit((cpPinJoint*)constraint, (cpBody*)a, (cpBody*)b,
            cpv(anchorA_x, anchorA_y), cpv(anchorB_x, anchorB_y));
    */

    /**
     * Allocate and initialize a pin joint.
     * @return 
     * @param a
     * @param b
     * @param anchorA
     * @param anchorB
     */ 
    static native long pinJointNew(long a, long b, float anchorA_x, float anchorA_y, float anchorB_x, float anchorB_y); /*
        return (jlong) cpPinJointNew((cpBody*)a, (cpBody*)b,
            cpv(anchorA_x, anchorA_y), cpv(anchorB_x, anchorB_y));
    */

    /**
     * Get the location of the first anchor relative to the first body.
     * @return 
     */ 
    static native void pinJointGetAnchorA(long constraint, float[] out_floats2); /*
        cpVect v = cpPinJointGetAnchorA((cpConstraint*)constraint);
        gdxCpVectToFloats(&v, out_floats2);
    */

    /**
     * Set the location of the first anchor relative to the first body.
     * @param anchorA
     */ 
    static native void pinJointSetAnchorA(long constraint, float anchorA_x, float anchorA_y); /*
        cpPinJointSetAnchorA((cpConstraint*)constraint, cpv(anchorA_x, anchorA_y));
    */

    /**
     * Get the location of the second anchor relative to the second body.
     * @return 
     */ 
    static native void pinJointGetAnchorB(long constraint, float[] out_floats2); /*
        cpVect v = cpPinJointGetAnchorB((cpConstraint*)constraint);
        gdxCpVectToFloats(&v, out_floats2);
    */

    /**
     * Set the location of the second anchor relative to the second body.
     * @param anchorB
     */ 
    static native void pinJointSetAnchorB(long constraint, float anchorB_x, float anchorB_y); /*
        cpPinJointSetAnchorB((cpConstraint*)constraint, cpv(anchorB_x, anchorB_y));
    */

    /**
     * Get the distance the joint will maintain between the two anchors.
     * @return 
     */ 
    static native float pinJointGetDist(long constraint); /*
        return cpPinJointGetDist((cpConstraint*)constraint);
    */

    /**
     * Set the distance the joint will maintain between the two anchors.
     * @param dist
     */ 
    static native void pinJointSetDist(long constraint, float dist); /*
        cpPinJointSetDist((cpConstraint*)constraint, dist);
    */

    /**
     * Check if a constraint is a slide joint.
     * @return 
     */ 
    static native boolean isPivotJoint(long constraint); /*
        return cpConstraintIsPivotJoint((cpConstraint*)constraint);
    */

    /**
     * Allocate a pivot joint
     * @return 
     * @param void
     */ 
    static native long pivotJointAlloc(); /*
        return (jlong) cpPivotJointAlloc();
    */

    /**
     * Initialize a pivot joint.
     * @return 
     * @param joint
     * @param a
     * @param b
     * @param anchorA
     * @param anchorB
     */ 
    static native void pivotJointInit(long constraint, long a, long b,
            float anchorA_x, float anchorA_y, float anchorB_x, float anchorB_y); /*
        cpPivotJointInit((cpPivotJoint*)constraint, (cpBody*)a, (cpBody*)b,
            cpv(anchorA_x, anchorA_y), cpv(anchorB_x, anchorB_y));
    */

    /**
     * Allocate and initialize a pivot joint.
     * @return 
     * @param a
     * @param b
     * @param pivot
     */ 
    static native long pivotJointNew(long a, long b, float pivot_x, float pivot_y); /*
        return (jlong) cpPivotJointNew((cpBody*)a, (cpBody*)b,
            cpv(pivot_x, pivot_y));
    */

    /**
     * Allocate and initialize a pivot joint with specific anchors.
     * @return 
     * @param a
     * @param b
     * @param anchorA
     * @param anchorB
     */ 
    static native long pivotJointNew2(long a, long b, float anchorA_x, float anchorA_y, float anchorB_x, float anchorB_y); /*
        return (jlong) cpPivotJointNew2((cpBody*)a, (cpBody*)b,
            cpv(anchorA_x, anchorA_y), cpv(anchorB_x, anchorB_y));
    */

    /**
     * Get the location of the first anchor relative to the first body.
     * @return 
     */ 
    static native void pivotJointGetAnchorA(long constraint, float[] out_floats); /*
        cpVect v = cpPivotJointGetAnchorA((cpConstraint*)constraint);
        gdxCpVectToFloats(&v, out_floats);
    */

    /**
     * Set the location of the first anchor relative to the first body.
     * @param anchorA
     */ 
    static native void pivotJointSetAnchorA(long constraint, float anchorA_x, float anchorA_y); /*
        cpPivotJointSetAnchorA((cpConstraint*)constraint, cpv(anchorA_x, anchorA_y));
    */

    /**
     * Get the location of the second anchor relative to the second body.
     * @return 
     */ 
    static native void pivotJointGetAnchorB(long constraint, float[] out_floats); /*
        cpVect v = cpPivotJointGetAnchorB((cpConstraint*)constraint);
        gdxCpVectToFloats(&v, out_floats);
    */

    /**
     * Set the location of the second anchor relative to the second body.
     * @param anchorB
     */ 
    static native void pivotJointSetAnchorB(long constraint, float anchorB_x, float anchorB_y); /*
        cpPivotJointSetAnchorB((cpConstraint*)constraint, cpv(anchorB_x, anchorB_y));
    */

    /**
     * 
     * @return 
     */ 
    static native boolean isRatchetJoint(long constraint); /*
        return cpConstraintIsRatchetJoint((cpConstraint*)constraint);
    */

    /**
     * Allocate a ratchet joint.
     * @return 
     * @param void
     */ 
    static native long ratchetJointAlloc(); /*
        return (jlong) cpRatchetJointAlloc();
    */

    /**
     * Initialize a ratched joint.
     * @return 
     * @param joint
     * @param a
     * @param b
     * @param phase
     * @param ratchet
     */ 
    static native void ratchetJointInit(long constraint, long a, long b, float phase, float ratchet); /*
        cpRatchetJointInit((cpRatchetJoint*)constraint, (cpBody*)a, (cpBody*)b, phase, ratchet);
    */

    /**
     * Allocate and initialize a ratchet joint.
     * @return 
     * @param a
     * @param b
     * @param phase
     * @param ratchet
     */ 
    static native long ratchetJointNew(long a, long b, float phase, float ratchet); /*
        return (jlong) cpRatchetJointNew((cpBody*)a, (cpBody*)b, phase, ratchet);
    */

    /**
     * Get the angle of the current ratchet tooth.
     * @return 
     */ 
    static native float ratchetJointGetAngle(long constraint); /*
        return cpRatchetJointGetAngle((cpConstraint*)constraint);
    */

    /**
     * Set the angle of the current ratchet tooth.
     * @param angle
     */ 
    static native void ratchetJointSetAngle(long constraint, float angle); /*
        cpRatchetJointSetAngle((cpConstraint*)constraint, angle);
    */

    /**
     * Get the phase offset of the ratchet.
     * @return 
     */ 
    static native float ratchetJointGetPhase(long constraint); /*
        return cpRatchetJointGetPhase((cpConstraint*)constraint);
    */

    /**
     * Get the phase offset of the ratchet.
     * @param phase
     */ 
    static native void ratchetJointSetPhase(long constraint, float phase); /*
        cpRatchetJointSetPhase((cpConstraint*)constraint, phase);
    */

    /**
     * Get the angular distance of each ratchet.
     * @return 
     */ 
    static native float ratchetJointGetRatchet(long constraint); /*
        return cpRatchetJointGetRatchet((cpConstraint*)constraint);
    */

    /**
     * Set the angular distance of each ratchet.
     * @param ratchet
     */ 
    static native void ratchetJointSetRatchet(long constraint, float ratchet); /*
        cpRatchetJointSetRatchet((cpConstraint*)constraint, ratchet);
    */

    /**
     * Check if a constraint is a damped rotary springs.
     * @return 
     */ 
    static native boolean isRotaryLimitJoint(long constraint); /*
        return cpConstraintIsRotaryLimitJoint((cpConstraint*)constraint);
    */

    /**
     * Allocate a damped rotary limit joint.
     * @return 
     * @param void
     */ 
    static native long rotaryLimitJointAlloc(); /*
        return (jlong) cpRotaryLimitJointAlloc();
    */

    /**
     * Initialize a damped rotary limit joint.
     * @return 
     * @param joint
     * @param a
     * @param b
     * @param min
     * @param max
     */ 
    static native void rotaryLimitJointInit(long constraint, long a, long b, float min, float max); /*
        cpRotaryLimitJointInit((cpRotaryLimitJoint*)constraint, (cpBody*)a, (cpBody*)b, min, max);
    */

    /**
     * Allocate and initialize a damped rotary limit joint.
     * @return 
     * @param a
     * @param b
     * @param min
     * @param max
     */ 
    static native long rotaryLimitJointNew(long a, long b, float min, float max); /*
        return (jlong) cpRotaryLimitJointNew((cpBody*)a, (cpBody*)b, min, max);
    */

    /**
     * Get the minimum distance the joint will maintain between the two anchors.
     * @return 
     */ 
    static native float rotaryLimitJointGetMin(long constraint); /*
        return cpRotaryLimitJointGetMin((cpConstraint*)constraint);
    */

    /**
     * Set the minimum distance the joint will maintain between the two anchors.
     * @param min
     */ 
    static native void rotaryLimitJointSetMin(long constraint, float min); /*
        cpRotaryLimitJointSetMin((cpConstraint*)constraint, min);
    */

    /**
     * Get the maximum distance the joint will maintain between the two anchors.
     * @return 
     */ 
    static native float rotaryLimitJointGetMax(long constraint); /*
        return cpRotaryLimitJointGetMax((cpConstraint*)constraint);
    */

    /**
     * Set the maximum distance the joint will maintain between the two anchors.
     * @param max
     */ 
    static native void rotaryLimitJointSetMax(long constraint, float max); /*
        cpRotaryLimitJointSetMax((cpConstraint*)constraint, max);
    */

    /**
     * Check if a constraint is a damped rotary springs.
     * @return 
     */ 
    static native boolean isSimpleMotor(long constraint); /*
        return cpConstraintIsSimpleMotor((cpConstraint*)constraint);
    */

    /**
     * Allocate a simple motor.
     * @return 
     * @param void
     */ 
    static native long simpleMotorAlloc(); /*
        return (jlong) cpSimpleMotorAlloc();
    */

    /**
     * initialize a simple motor.
     * @return 
     * @param joint
     * @param a
     * @param b
     * @param rate
     */ 
    static native void simpleMotorInit(long constraint, long a, long b, float rate); /*
        cpSimpleMotorInit((cpSimpleMotor*)constraint, (cpBody*)a, (cpBody*)b, rate);
    */

    /**
     * Allocate and initialize a simple motor.
     * @return 
     * @param a
     * @param b
     * @param rate
     */ 
    static native long simpleMotorNew(long a, long b, float rate); /*
        return (jlong) cpSimpleMotorNew((cpBody*)a, (cpBody*)b, rate);
    */

    /**
     * Get the rate of the motor.
     * @return 
     */ 
    static native float simpleMotorGetRate(long constraint); /*
        return cpSimpleMotorGetRate((cpConstraint*)constraint);
    */

    /**
     * Set the rate of the motor.
     * @param rate
     */ 
    static native void simpleMotorSetRate(long constraint, float rate); /*
        cpSimpleMotorSetRate((cpConstraint*)constraint, rate);
    */

    /**
     * Check if a constraint is a slide joint.
     * @return 
     */ 
    static native boolean isSlideJoint(long constraint); /*
        return cpConstraintIsSlideJoint((cpConstraint*)constraint);
    */

    /**
     * Allocate a slide joint.
     * @return 
     * @param void
     */ 
    static native long slideJointAlloc(); /*
        return (jlong) cpSlideJointAlloc();
    */

    /**
     * Initialize a slide joint.
     * @return 
     * @param joint
     * @param a
     * @param b
     * @param anchorA
     * @param anchorB
     * @param min
     * @param max
     */ 
    static native void slideJointInit(long constraint, long a, long b,
            float anchorA_x, float anchorA_y, float anchorB_x, float anchorB_y, float min, float max); /*
        cpSlideJointInit((cpSlideJoint*)constraint, (cpBody*)a, (cpBody*)b,
            cpv(anchorA_x, anchorA_y), cpv(anchorB_x, anchorB_y), min, max);
    */

    /**
     * Allocate and initialize a slide joint.
     * @return 
     * @param a
     * @param b
     * @param anchorA
     * @param anchorB
     * @param min
     * @param max
     */ 
    static native long slideJointNew(long a, long b, float anchorA_x, float anchorA_y, 
            float anchorB_x, float anchorB_y, float min, float max); /*
        return (jlong) cpSlideJointNew((cpBody*)a, (cpBody*)b, cpv(anchorA_x, anchorA_y),
            cpv(anchorB_x, anchorB_y), min, max);
    */

    /**
     * Get the location of the first anchor relative to the first body.
     * @return 
     */ 
    static native void slideJointGetAnchorA(long constraint, float[] out_floats); /*
        cpVect v = cpSlideJointGetAnchorA((cpConstraint*)constraint);
        gdxCpVectToFloats(&v, out_floats);
    */

    /**
     * Set the location of the first anchor relative to the first body.
     * @param anchorA
     */ 
    static native void slideJointSetAnchorA(long constraint, float anchorA_x, float anchorA_y); /*
        cpSlideJointSetAnchorA((cpConstraint*)constraint, cpv(anchorA_x, anchorA_y));
    */

    /**
     * Get the location of the second anchor relative to the second body.
     * @return 
     */ 
    static native void slideJointGetAnchorB(long constraint, float[] out_floats); /*
        cpVect v = cpSlideJointGetAnchorB((cpConstraint*)constraint);
        gdxCpVectToFloats(&v, out_floats);
    */

    /**
     * Set the location of the second anchor relative to the second body.
     * @param anchorB
     */ 
    static native void slideJointSetAnchorB(long constraint, float anchorB_x, float anchorB_y); /*
        cpSlideJointSetAnchorB((cpConstraint*)constraint, cpv(anchorB_x, anchorB_y));
    */

    /**
     * Get the minimum distance the joint will maintain between the two anchors.
     * @return 
     */ 
    static native float slideJointGetMin(long constraint); /*
        return cpSlideJointGetMin((cpConstraint*)constraint);
    */

    /**
     * Set the minimum distance the joint will maintain between the two anchors.
     * @param min
     */ 
    static native void slideJointSetMin(long constraint, float min); /*
        cpSlideJointSetMin((cpConstraint*)constraint, min);
    */

    /**
     * Get the maximum distance the joint will maintain between the two anchors.
     * @return 
     */ 
    static native float slideJointGetMax(long constraint); /*
        return cpSlideJointGetMax((cpConstraint*)constraint);
    */

    /**
     * Set the maximum distance the joint will maintain between the two anchors.
     * @param max
     */ 
    static native void slideJointSetMax(long constraint, float max); /*
        cpSlideJointSetMax((cpConstraint*)constraint, max);
    */

}
