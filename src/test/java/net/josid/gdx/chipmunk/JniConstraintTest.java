package net.josid.gdx.chipmunk;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.utils.Array;

import net.josid.gdx.chipmunk.constraint.JniConstraintTypeTest;


public class JniConstraintTest {

    public static void free(long constraint) {
        JniConstraint.free(constraint);
    }

    private long bodyA;
    private long bodyB;
    private long constraint;

    @Before
    public void setup() {
        JniChipmunk.init();
        bodyA = JniBody.newBody(1f, .8f);
        bodyB = JniBody.newBody(1f, .8f);
        constraint = JniConstraintTypeTest.pinJointNew(bodyA, bodyB, 0, 0, 0, 0);
    }

    @After
    public void cleanUp() {
        JniConstraint.free(constraint);
        JniBody.free(bodyA);
        JniBody.free(bodyB);
    }
    
    @Test
    public void testDestroy() {
        JniConstraint.destroy(constraint);
    }

    @Test
    public void testFree() {
        long constraint = JniConstraintTypeTest.pinJointNew(bodyA, bodyB, 0, 0, 0, 0);
        JniBody.free(constraint);
    }

    @Test
    public void testGetSpace() {
        long space = JniSpace.newSpace();
        JniSpace.addConstraint(space, constraint);
        assertEquals(space, JniConstraint.getSpace(constraint));
        JniSpace.removeConstraint(space, constraint);
        assertEquals(0, JniConstraint.getSpace(space));
        JniSpace.free(space);
    }

    @Test
    public void testGetBodyA() {
        assertEquals(bodyA, JniConstraint.getBodyA(constraint));
    }

    @Test
    public void testGetBodyB() {
        assertEquals(bodyB, JniConstraint.getBodyB(constraint));
    }

    @Test
    public void testSetMaxForce() {
        JniConstraint.setMaxForce(constraint, 1.6f);
        assertEquals(1.6f, JniConstraint.getMaxForce(constraint), .001f);
    }

    @Test
    public void testSetErrorBias() {
        JniConstraint.setErrorBias(constraint, 2.6f);
        assertEquals(2.6f, JniConstraint.getErrorBias(constraint), .001f);
    }

    @Test
    public void testSetMaxBias() {
        JniConstraint.setMaxBias(constraint, 3.6f);
        assertEquals(3.6f, JniConstraint.getMaxBias(constraint), .001f);
    }

    @Test
    public void testSetCollideBodies() {
        assertTrue(JniConstraint.getCollideBodies(constraint));
        JniConstraint.setCollideBodies(constraint, false);
        assertFalse(JniConstraint.getCollideBodies(constraint));
    }

    @Test
    public void testSetPreSolveFunc() {
        CpGdxTestHelper gdxHelper = new CpGdxTestHelper();
        long space = JniSpace.newSpace();
        gdxHelper.addSpace(space);
        JniSpace.addBody(space, bodyA);
        JniSpace.addBody(space, bodyB);
        JniSpace.addConstraint(space, constraint);
        CpConstraint cpConstraint = gdxHelper.newCpConstraint(constraint);
        Array<Object> objects = new Array<Object>(new Object[] { cpConstraint,
                gdxHelper.newCpSpace(space)});
        cpConstraint.preSolveFunc = (cpC, cpS) -> {
            objects.removeValue(cpC, true);
            objects.removeValue(cpS, true);
        };
        JniConstraint.setPreSolveFunc(constraint, JniChipmunk.constraintPreSolveJniFunc);
        JniSpace.step(space, .05f);
        assertEquals(0, objects.size);
        
        JniSpace.removeConstraint(space, constraint);
        JniSpace.removeBody(space, bodyA);
        JniSpace.removeBody(space, bodyB);
        JniSpace.free(space);
        gdxHelper.dispose();
    }

    @Test
    public void testSetPostSolveFunc() {
        CpGdxTestHelper gdxHelper = new CpGdxTestHelper();
        long space = JniSpace.newSpace();
        gdxHelper.addSpace(space);
        JniSpace.addBody(space, bodyA);
        JniSpace.addBody(space, bodyB);
        JniSpace.addConstraint(space, constraint);
        CpConstraint cpConstraint = gdxHelper.newCpConstraint(constraint);
        Array<Object> objects = new Array<Object>(new Object[] { cpConstraint,
                gdxHelper.newCpSpace(space)});
        cpConstraint.postSolveFunc = (cpC, cpS) -> {
            objects.removeValue(cpC, true);
            objects.removeValue(cpS, true);
        };
        JniConstraint.setPostSolveFunc(constraint, JniChipmunk.constraintPostSolveJniFunc);
        JniSpace.step(space, .05f);
        assertEquals(0, objects.size);
        
        JniSpace.removeConstraint(space, constraint);
        JniSpace.removeBody(space, bodyA);
        JniSpace.removeBody(space, bodyB);
        JniSpace.free(space);
        gdxHelper.dispose();
    }

    @Test
    public void testSetUserData() {
        JniConstraint.setUserData(constraint, 1234);
        assertEquals(1234, JniConstraint.getUserData(constraint));
    }

    @Test
    public void testGetImpulse() {
        long space = JniSpace.newSpace();
        JniSpace.addBody(space, bodyA);
        JniSpace.addBody(space, bodyB);
        JniSpace.addConstraint(space, constraint);

        JniBody.setVelocity(bodyA, 8, -8);
        JniSpace.step(space, .05f);
        assertEquals(7.1898623f, JniConstraint.getImpulse(constraint), .001f);

        JniSpace.removeConstraint(space, constraint);
        JniSpace.removeBody(space, bodyA);
        JniSpace.removeBody(space, bodyB);
        JniSpace.free(space);
    }

}
