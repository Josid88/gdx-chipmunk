package net.josid.gdx.chipmunk.constraint;

import static net.josid.gdx.chipmunk.JniConstraintTest.free;
import static net.josid.gdx.chipmunk.constraint.JniConstraintType.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.CpGdxTestHelper;
import net.josid.gdx.chipmunk.JniChipmunkTest;
import net.josid.gdx.chipmunk.JniBodyTest;
import net.josid.gdx.chipmunk.JniCpChipmunkTest;
import net.josid.gdx.chipmunk.JniSpaceTest;


public class JniConstraintTypeTest {

    public static long pinJointNew(long a, long b, float anchorA_x, float anchorA_y,
            float anchorB_x, float anchorB_y) {
        return JniConstraintType.pinJointNew(a, b, anchorA_x, anchorA_y, anchorB_x, anchorB_y);
    }

    private long bodyA;
    private long bodyB;
    private float[] floats = new float[16];
    private Vector2 vector2 = new Vector2();

    @Before
    public void setup() {
        JniCpChipmunkTest.init();
        bodyA = JniBodyTest.newBody(1.8f, 1.6f);
        bodyB = JniBodyTest.newBody(1.8f, 1.6f);
    }

    @After
    public void cleanUp() {
        JniBodyTest.freeBody(bodyA);
        JniBodyTest.freeBody(bodyB);
    }

    @Test
    public void testIsDampedRotarySpring() {
        long constraint = dampedRotarySpringNew(0, 0, 0, 0, 0);
        assertTrue(isDampedRotarySpring(constraint));
        free(constraint);
    }

    @Test
    public void testDampedRotarySpringAlloc() {
        long constraint = dampedRotarySpringAlloc();
        free(constraint);
    }

    @Test
    public void testDampedRotarySpringInit() {
        long constraint = dampedRotarySpringAlloc();
        dampedRotarySpringInit(constraint, bodyA, bodyB, .4f, .5f, .6f);
        assertEquals(.4f, dampedRotarySpringGetRestAngle(constraint), .001f);
        assertEquals(.5f, dampedRotarySpringGetStiffness(constraint), .001f);
        assertEquals(.6f, dampedRotarySpringGetDamping(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testDampedRotarySpringNew() {
        long constraint = dampedRotarySpringNew(bodyA, bodyB, 1.4f, 1.5f, 1.6f);
        assertEquals(1.4f, dampedRotarySpringGetRestAngle(constraint), .001f);
        assertEquals(1.5f, dampedRotarySpringGetStiffness(constraint), .001f);
        assertEquals(1.6f, dampedRotarySpringGetDamping(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testDampedRotarySpringSetRestAngle() {
        long constraint = dampedRotarySpringNew(0, 0, 0, 0, 0);
        dampedRotarySpringSetRestAngle(constraint, 3.2f);
        assertEquals(3.2f, dampedRotarySpringGetRestAngle(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testDampedRotarySpringSetStiffness() {
        long constraint = dampedRotarySpringNew(0, 0, 0, 0, 0);
        dampedRotarySpringSetStiffness(constraint, 6.8f);
        assertEquals(6.8f, dampedRotarySpringGetStiffness(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testDampedRotarySpringSetDamping() {
        long constraint = dampedRotarySpringNew(0, 0, 0, 0, 0);
        dampedRotarySpringSetDamping(constraint, 7.3f);
        assertEquals(7.3f, dampedRotarySpringGetDamping(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testDampedRotarySpringSetSpringTorqueFunc() {
        long constraint = dampedRotarySpringNew(bodyA, bodyB, 1.4f, 1.5f, 1.6f);
        CpGdxTestHelper gdxHelper = new CpGdxTestHelper();
        long space = JniSpaceTest.newSpace();
        gdxHelper.addSpace(space);
        JniBodyTest.setAngle(bodyA, 3.1416f);
        JniSpaceTest.addBody(space, bodyA);
        JniSpaceTest.addBody(space, bodyB);
        JniSpaceTest.addConstraint(space, constraint);

        CpDampedRotarySpring cpDampedRotarySpring = gdxHelper.addConstraint(new CpDampedRotarySpring(constraint, null));
        int[] execs = new int[1];
        cpDampedRotarySpring.dampedRotarySpringTorqueFunc = (cpD, ra) -> {
            execs[0]++;
            assertEquals(cpDampedRotarySpring, cpD);
            assertEquals(3.1416f, ra, .001f);
            return 1f;
        };
        JniConstraintType.dampedRotarySpringSetSpringTorqueFunc(constraint,
                JniChipmunkTest.getDampedRotarySpringTorqueFunc());
        JniSpaceTest.step(space, .05f);
        assertEquals(1, execs[0]);

        JniSpaceTest.removeConstraint(space, constraint);
        JniSpaceTest.removeBody(space, bodyA);
        JniSpaceTest.removeBody(space, bodyB);
        JniSpaceTest.free(space);
        gdxHelper.dispose();
        free(constraint);
    }

    @Test
    public void testDampedSpringAlloc() {
        long constraint = dampedSpringAlloc();
        free(constraint);
    }

    @Test
    public void testDampedSpringInit() {
        long constraint = dampedSpringAlloc();
        dampedSpringInit(constraint, bodyA, bodyB, .5f, -.5f, 1.5f, -1.5f, 1.2f, 1.3f, 1.4f);
        dampedSpringGetAnchorA(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(.5f, -.5f), .001f);
        dampedSpringGetAnchorB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1.5f, -1.5f), .001f);
        assertEquals(1.2f, dampedSpringGetRestLength(constraint), .001f);
        assertEquals(1.3f, dampedSpringGetStiffness(constraint), .001f);
        assertEquals(1.4f, dampedSpringGetDamping(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testDampedSpringNew() {
        long constraint = dampedSpringNew(bodyA, bodyB, 1.5f, -1.5f, 2.5f, -2.5f, 2.2f, 2.3f, 2.4f);
        dampedSpringGetAnchorA(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1.5f, -1.5f), .001f);
        dampedSpringGetAnchorB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.5f, -2.5f), .001f);
        assertEquals(2.2f, dampedSpringGetRestLength(constraint), .001f);
        assertEquals(2.3f, dampedSpringGetStiffness(constraint), .001f);
        assertEquals(2.4f, dampedSpringGetDamping(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testDampedSpringSetAnchorA() {
        long constraint = dampedSpringNew(0, 0, 0, 0, 0, 0, 0, 0, 0);
        dampedSpringSetAnchorA(constraint, 5.6f, -5.6f);
        dampedSpringGetAnchorA(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(5.6f, -5.6f), .001f);
        free(constraint);
    }

    @Test
    public void testDampedSpringSetAnchorB() {
        long constraint = dampedSpringNew(0, 0, 0, 0, 0, 0, 0, 0, 0);
        dampedSpringSetAnchorB(constraint, 2.3f, -2.3f);
        dampedSpringGetAnchorB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.3f, -2.3f), .001f);
        free(constraint);
    }

    @Test
    public void testDampedSpringSetRestLength() {
        long constraint = dampedSpringNew(0, 0, 0, 0, 0, 0, 0, 0, 0);
        dampedSpringSetRestLength(constraint, 5.4f);
        assertEquals(5.4f, dampedSpringGetRestLength(constraint), .001f);
        free(constraint);
        
    }

    @Test
    public void testDampedSpringSetStiffness() {
        long constraint = dampedSpringNew(0, 0, 0, 0, 0, 0, 0, 0, 0);
        dampedSpringSetStiffness(constraint, 8.1f);
        assertEquals(8.1f, dampedSpringGetStiffness(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testDampedSpringSetDamping() {
        long constraint = dampedSpringNew(0, 0, 0, 0, 0, 0, 0, 0, 0);
        dampedSpringSetDamping(constraint, 4.4f);
        assertEquals(4.4f, dampedSpringGetDamping(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testDampedSpringSetSpringForceFunc() {
        long constraint = dampedSpringNew(bodyA, bodyB, 1.5f, -1.5f, 2.5f, -2.5f, 2.2f, 2.3f, 2.4f);
        CpGdxTestHelper gdxHelper = new CpGdxTestHelper();
        long space = JniSpaceTest.newSpace();
        gdxHelper.addSpace(space);
        JniSpaceTest.addBody(space, bodyA);
        JniSpaceTest.addBody(space, bodyB);
        JniSpaceTest.addConstraint(space, constraint);

        int[] execs = new int[1];
        CpDampedSpring cpDampedSpring = gdxHelper.addConstraint(new CpDampedSpring(constraint, null));
        cpDampedSpring.dampedSpringForceFunc = (cpD, dist) -> {
            execs[0]++;
            assertEquals(cpDampedSpring, cpD);
            assertEquals(1.4142135f, dist, .001f);
            return 1f;
        };
        JniConstraintType.dampedSpringSetSpringForceFunc(constraint,
                JniChipmunkTest.getDampedSpringForceFunc());
        JniSpaceTest.step(space, .05f);
        assertEquals(1, execs[0]);

        JniSpaceTest.removeConstraint(space, constraint);
        JniSpaceTest.removeBody(space, bodyA);
        JniSpaceTest.removeBody(space, bodyB);
        JniSpaceTest.free(space);
        gdxHelper.dispose();
        free(constraint);
    }

    @Test
    public void testIsGearJoint() {
        long constraint = gearJointNew(0, 0, 0, 0);
        assertTrue(isGearJoint(constraint));
        free(constraint);
    }

    @Test
    public void testGearJointAlloc() {
        long constraint = gearJointAlloc();
        free(constraint);
    }

    @Test
    public void testGearJointInit() {
        long constraint = gearJointAlloc();
        gearJointInit(constraint, bodyA, bodyB, 1.2f, 2.2f);
        assertEquals(1.2f, gearJointGetPhase(constraint), .001f);
        assertEquals(2.2f, gearJointGetRatio(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testGearJointNew() {
        long constraint = gearJointNew(bodyA, bodyB, 3.2f, 4.2f);
        assertEquals(3.2f, gearJointGetPhase(constraint), .001f);
        assertEquals(4.2f, gearJointGetRatio(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testGearJointSetPhase() {
        long constraint = gearJointNew(0, 0, 0, 0);
        gearJointSetPhase(constraint, 6.4f);
        assertEquals(6.4f, gearJointGetPhase(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testGearJointSetRatio() {
        long constraint = gearJointNew(0, 0, 0, 0);
        gearJointSetPhase(constraint, 5.5f);
        assertEquals(5.5f, gearJointGetPhase(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testGrooveJointAlloc() {
        long constraint = grooveJointAlloc();
        free(constraint);
    }

    @Test
    public void testGrooveJointInit() {
        long constraint = grooveJointAlloc();
        grooveJointInit(constraint, bodyA, bodyB, 1.1f, -1.1f, 1.2f, -1.2f, 1.3f, -1.3f);
        grooveJointGetGrooveA(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1.1f, -1.1f), .001f);
        grooveJointGetGrooveB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1.2f, -1.2f), .001f);
        grooveJointGetAnchorB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1.3f, -1.3f), .001f);
        free(constraint);
    }

    @Test
    public void testGrooveJointNew() {
        long constraint = grooveJointNew(bodyA, bodyB, 2.1f, -2.1f, 2.2f, -2.2f, 2.3f, -2.3f);
        grooveJointGetGrooveA(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.1f, -2.1f), .001f);
        grooveJointGetGrooveB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.2f, -2.2f), .001f);
        grooveJointGetAnchorB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.3f, -2.3f), .001f);
        free(constraint);
    }

    @Test
    public void testGrooveJointSetGrooveA() {
        long constraint = grooveJointNew(0, 0, 0, 0, 0, 0, 0, 0);
        grooveJointSetGrooveA(constraint, 5.6f, -5.6f);
        grooveJointGetGrooveA(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(5.6f, -5.6f), .001f);
        free(constraint);
    }

    @Test
    public void testGrooveJointSetGrooveB() {
        long constraint = grooveJointNew(0, 0, 0, 0, 0, 0, 0, 0);
        grooveJointSetGrooveB(constraint, 4.6f, -4.6f);
        grooveJointGetGrooveB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(4.6f, -4.6f), .001f);
        free(constraint);
    }

    @Test
    public void testGrooveJointSetAnchorB() {
        long constraint = grooveJointNew(0, 0, 0, 0, 0, 0, 0, 0);
        grooveJointSetAnchorB(constraint, 3.6f, -3.6f);
        grooveJointGetAnchorB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(3.6f, -3.6f), .001f);
        free(constraint);
    }

    @Test
    public void testIsPinJoint() {
        long constraint = pinJointNew(0, 0, 0, 0, 0, 0);
        assertTrue(isPinJoint(constraint));
        free(constraint);
    }

    @Test
    public void testPinJointAlloc() {
        long constraint = pinJointAlloc();
        free(constraint);
    }

    @Test
    public void testPinJointInit() {
        long constraint = pinJointAlloc();
        pinJointInit(constraint, bodyA, bodyB, 1.1f, -1.1f, 1.2f, -1.2f);
        pinJointGetAnchorA(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1.1f, -1.1f), .001f);
        pinJointGetAnchorB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1.2f, -1.2f), .001f);
        free(constraint);
    }

    @Test
    public void testPinJointNew() {
        long constraint = pinJointNew(bodyA, bodyB, 2.1f, -2.1f, 2.2f, -2.2f);
        pinJointGetAnchorA(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.1f, -2.1f), .001f);
        pinJointGetAnchorB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.2f, -2.2f), .001f);
        free(constraint);
    }

    @Test
    public void testPinJointSetAnchorA() {
        long constraint = pinJointNew(0, 0, 0, 0, 0, 0);
        pinJointSetAnchorA(constraint, 3.6f, -3.6f);
        pinJointGetAnchorA(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(3.6f, -3.6f), .001f);
        free(constraint);
    }

    @Test
    public void testPinJointSetAnchorB() {
        long constraint = pinJointNew(0, 0, 0, 0, 0, 0);
        pinJointSetAnchorB(constraint, 3.6f, -3.6f);
        pinJointGetAnchorB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(3.6f, -3.6f), .001f);
        free(constraint);
    }

    @Test
    public void testPinJointSetDist() {
        long constraint = pinJointNew(bodyA, bodyB, 2.1f, -2.1f, 2.2f, -2.2f);
        assertEquals(0.14142156f, pinJointGetDist(constraint), .001f);
        pinJointSetDist(constraint, 1.2f);
        assertEquals(1.2f, pinJointGetDist(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testIsPivotJoint() {
        long constraint = pivotJointNew(0, 0, 0, 0);
        assertTrue(isPivotJoint(constraint));
        free(constraint);
    }

    @Test
    public void testPivotJointAlloc() {
        long constraint = pivotJointNew(0, 0, 0, 0);
        free(constraint);
    }

    @Test
    public void testPivotJointInit() {
        long constraint = pivotJointAlloc();
        pivotJointInit(constraint, bodyA, bodyB, 1.1f, -1.1f, 1.2f, -1.2f);
        pivotJointGetAnchorA(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1.1f, -1.1f), .001f);
        pivotJointGetAnchorB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1.2f, -1.2f), .001f);
        free(constraint);
    }

    @Test
    public void testPivotJointNew() {
        long constraint = pivotJointNew(bodyA, bodyB, 3.1f, -3.1f);
        pivotJointGetAnchorA(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(3.1f, -3.1f), .001f);
        pivotJointGetAnchorB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(3.1f, -3.1f), .001f);
        free(constraint);
    }

    @Test
    public void testPivotJointNew2() {
        long constraint = pivotJointNew2(bodyA, bodyB, 2.1f, -2.1f, 2.2f, -2.2f);
        pivotJointGetAnchorA(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.1f, -2.1f), .001f);
        pivotJointGetAnchorB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.2f, -2.2f), .001f);
        free(constraint);
    }

    @Test
    public void testPivotJointSetAnchorA() {
        long constraint = pivotJointNew(0, 0, 0, 0);
        pivotJointSetAnchorA(constraint, 3.6f, -3.6f);
        pivotJointGetAnchorA(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(3.6f, -3.6f), .001f);
        free(constraint);
    }

    @Test
    public void testPivotJointSetAnchorB() {
        long constraint = pivotJointNew(0, 0, 0, 0);
        pivotJointSetAnchorB(constraint, 4.6f, -4.6f);
        pivotJointGetAnchorB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(4.6f, -4.6f), .001f);
        free(constraint);
    }

    @Test
    public void testIsRatchetJoint() {
        long constraint = ratchetJointNew(0, 0, 0, 0);
        assertTrue(isRatchetJoint(constraint));
        free(constraint);
    }

    @Test
    public void testRatchetJointAlloc() {
        long constraint = ratchetJointAlloc();
        free(constraint);
    }

    @Test
    public void testRatchetJointInit() {
        long constraint = ratchetJointAlloc();
        ratchetJointInit(constraint, bodyA, bodyB, 3.8f, 4.5f);
        assertEquals(3.8f, ratchetJointGetPhase(constraint), .001f);
        assertEquals(4.5f, ratchetJointGetRatchet(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testRatchetJointNew() {
        long constraint = ratchetJointNew(bodyA, bodyB, 2.8f, 1.5f);
        assertEquals(2.8f, ratchetJointGetPhase(constraint), .001f);
        assertEquals(1.5f, ratchetJointGetRatchet(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testRatchetJointSetAngle() {
        long constraint = ratchetJointNew(0, 0, 0, 0);
        ratchetJointSetAngle(constraint, 6.4f);
        assertEquals(6.4f, ratchetJointGetAngle(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testRatchetJointSetPhase() {
        long constraint = ratchetJointNew(0, 0, 0, 0);
        ratchetJointSetPhase(constraint, 7.4f);
        assertEquals(7.4f, ratchetJointGetPhase(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testRatchetJointSetRatchet() {
        long constraint = ratchetJointNew(0, 0, 0, 0);
        ratchetJointSetRatchet(constraint, 8.4f);
        assertEquals(8.4f, ratchetJointGetRatchet(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testIsRotaryLimitJoint() {
        long constraint = rotaryLimitJointNew(0, 0, 0, 0);
        assertTrue(isRotaryLimitJoint(constraint));
        free(constraint);
    }

    @Test
    public void testRotaryLimitJointAlloc() {
        long constraint = rotaryLimitJointAlloc();
        free(constraint);
    }

    @Test
    public void testRotaryLimitJointInit() {
        long constraint = rotaryLimitJointAlloc();
        rotaryLimitJointInit(constraint, bodyA, bodyB, 1.2f, 1.3f);
        free(constraint);
    }

    @Test
    public void testRotaryLimitJointNew() {
        long constraint = rotaryLimitJointNew(bodyA, bodyB, 1.2f, 1.3f);
        assertEquals(1.2f, rotaryLimitJointGetMin(constraint), .001f);
        assertEquals(1.3f, rotaryLimitJointGetMax(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testRotaryLimitJointSetMin() {
        long constraint = rotaryLimitJointNew(0, 0, 0, 0);
        rotaryLimitJointSetMin(constraint, 9.2f);
        assertEquals(9.2f, rotaryLimitJointGetMin(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testRotaryLimitJointSetMax() {
        long constraint = rotaryLimitJointNew(0, 0, 0, 0);
        rotaryLimitJointSetMax(constraint, 8.1f);
        assertEquals(8.1f, rotaryLimitJointGetMax(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testIsSimpleMotor() {
        long constraint = simpleMotorNew(0, 0, 0);
        assertTrue(isSimpleMotor(constraint));
        free(constraint);
    }

    @Test
    public void testSimpleMotorAlloc() {
        long constraint = simpleMotorAlloc();
        free(constraint);
    }

    @Test
    public void testSimpleMotorInit() {
        long constraint = simpleMotorAlloc();
        simpleMotorInit(constraint, bodyA, bodyB, 1.5f);
        assertEquals(1.5f, simpleMotorGetRate(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testSimpleMotorNew() {
        long constraint = simpleMotorNew(bodyA, bodyB, 2.5f);
        assertEquals(2.5f, simpleMotorGetRate(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testSimpleMotorSetRate() {
        long constraint = simpleMotorNew(0, 0, 0);
        simpleMotorSetRate(constraint, 7.2f);
        assertEquals(7.2f, simpleMotorGetRate(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testIsSlideJoint() {
        long constraint = slideJointNew(0, 0, 0, 0, 0, 0, 0, 0);
        assertTrue(isSlideJoint(constraint));
        free(constraint);
    }

    @Test
    public void testSlideJointAlloc() {
        long constraint = slideJointAlloc();
        free(constraint);
    }

    @Test
    public void testSlideJointInit() {
        long constraint = slideJointAlloc();
        slideJointInit(constraint, bodyA, bodyB, -1.1f, 1.1f, -1.2f, 1.2f, -1.3f, 1.3f);
        slideJointGetAnchorA(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-1.1f, 1.1f), .001f);
        slideJointGetAnchorB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-1.2f, 1.2f), .001f);
        assertEquals(-1.3f, slideJointGetMin(constraint), .001f);
        assertEquals(1.3f, slideJointGetMax(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testSlideJointNew() {
        long constraint = slideJointNew(bodyA, bodyB, -2.1f, 2.1f, -2.2f, 2.2f, -2.3f, 2.3f);
        slideJointGetAnchorA(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-2.1f, 2.1f), .001f);
        slideJointGetAnchorB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-2.2f, 2.2f), .001f);
        assertEquals(-2.3f, slideJointGetMin(constraint), .001f);
        assertEquals(2.3f, slideJointGetMax(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testSlideJointSetAnchorA() {
        long constraint = slideJointNew(0, 0, 0, 0, 0, 0, 0, 0);
        slideJointSetAnchorA(constraint, 3.5f, -3.5f);
        slideJointGetAnchorA(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(3.5f, -3.5f), .001f);
        free(constraint);
    }

    @Test
    public void testSlideJointSetAnchorB() {
        long constraint = slideJointNew(0, 0, 0, 0, 0, 0, 0, 0);
        slideJointSetAnchorB(constraint, 3.9f, -3.9f);
        slideJointGetAnchorB(constraint, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(3.9f, -3.9f), .001f);
        free(constraint);
    }

    @Test
    public void testSlideJointSetMin() {
        long constraint = slideJointNew(0, 0, 0, 0, 0, 0, 0, 0);
        slideJointSetMin(constraint, 4.7f);
        assertEquals(4.7, slideJointGetMin(constraint), .001f);
        free(constraint);
    }

    @Test
    public void testSlideJointSetMax() {
        long constraint = slideJointNew(0, 0, 0, 0, 0, 0, 0, 0);
        slideJointSetMax(constraint, 8.6f);
        assertEquals(8.6f, slideJointGetMax(constraint), .001f);
        free(constraint);
    }

}
