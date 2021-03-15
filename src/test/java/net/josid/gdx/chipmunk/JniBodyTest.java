package net.josid.gdx.chipmunk;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import net.josid.gdx.chipmunk.constraint.JniConstraintTypeTest;
import net.josid.gdx.chipmunk.shape.JniShapeTypeTest;


public class JniBodyTest {

    public static long newBody(float mass, float moment) {
       return JniBody.newBody(mass, moment);
    }

    public static void freeBody(long body) {
        JniBody.free(body);
    }

    public static void setAngle(long body, float angle) {
        JniBody.setAngle(body, angle);
    }

    private long body;
    private float[] floats = new float[16];
    private Vector2 vector2 = new Vector2();

    @Before
    public void setup() {
        JniChipmunk.init();
        body = JniBody.newBody(1.5f, 1.4f);
    }

    @After
    public void cleanUp() {
        JniBody.free(body);
    }

    @Test
    public void testAlloc() {
        long body = JniBody.alloc();
        JniBody.free(body);
    }

    @Test
    public void testInit() {
        JniBody.init(body, 2.5f, 2.3f);
        assertEquals(CpBody.Type.Dynamic, CpBody.Type.valueOf(JniBody.getType(body)));
        assertEquals(2.5f, JniBody.getMass(body), .001f);
        assertEquals(2.3f, JniBody.getMoment(body), .001f);
    }

    @Test
    public void testNewBody() {
        long body = JniBody.newBody(3.3f, 3.1f);
        assertEquals(CpBody.Type.Dynamic, CpBody.Type.valueOf(JniBody.getType(body)));
        assertEquals(3.3f, JniBody.getMass(body), .001f);
        assertEquals(3.1f, JniBody.getMoment(body), .001f);
        JniBody.free(body);
    }

    @Test
    public void testNewKinematic() {
        long body = JniBody.newKinematic();
        assertEquals(CpBody.Type.Kinematic, CpBody.Type.valueOf(JniBody.getType(body)));
        assertEquals(Float.POSITIVE_INFINITY, JniBody.getMass(body), .001f);
        assertEquals(Float.POSITIVE_INFINITY, JniBody.getMoment(body), .001f);
        JniBody.free(body);
    }

    @Test
    public void testNewStatic() {
        long body = JniBody.newStatic();
        assertEquals(CpBody.Type.Static, CpBody.Type.valueOf(JniBody.getType(body)));
        assertEquals(Float.POSITIVE_INFINITY, JniBody.getMass(body), .001f);
        assertEquals(Float.POSITIVE_INFINITY, JniBody.getMoment(body), .001f);
        JniBody.free(body);
    }

    @Test
    public void testDestroy() {
        JniBody.destroy(body);
    }

    @Test
    public void testFree() {
        long body = JniBody.alloc();
        JniBody.free(body);
    }

    @Test
    public void testActivate() {
        long space = JniSpace.newSpace();
        JniSpace.setSleepTimeThreshold(space, .5f);

        JniSpace.addBody(space, body);
        assertFalse(JniBody.isSleeping(body));
        JniBody.sleep(body);
        assertTrue(JniBody.isSleeping(body));
        JniBody.activate(body);
        assertFalse(JniBody.isSleeping(body));

        JniSpace.removeBody(space, body);
        JniSpace.free(space);
    }

    @Test
    @Ignore
    public void testActivateStatic() {
        fail("Not yet implemented");
    }

    @Test
    public void testSleepWithGroup() {
        long space = JniSpace.newSpace();
        JniSpace.setSleepTimeThreshold(space, .5f);

        long bodyA = JniBody.newBody(1f, 1f);
        JniSpace.addBody(space, bodyA);
        long bodyB = JniBody.newBody(1f, 1f);
        JniSpace.addBody(space, bodyB);
        long body1 = JniBody.newBody(1f, 1f);
        JniSpace.addBody(space, body1);

        assertFalse(JniBody.isSleeping(bodyA));
        assertFalse(JniBody.isSleeping(bodyB));
        assertFalse(JniBody.isSleeping(body1));

        JniBody.sleepWithGroup(bodyA, 0);
        JniBody.sleepWithGroup(bodyB, bodyA);
        JniBody.sleepWithGroup(body1, 0);
        assertTrue(JniBody.isSleeping(bodyA));
        assertTrue(JniBody.isSleeping(bodyB));
        assertTrue(JniBody.isSleeping(body1));

        JniBody.activate(bodyA);
        assertFalse(JniBody.isSleeping(bodyA));
        assertFalse(JniBody.isSleeping(bodyB));
        assertTrue(JniBody.isSleeping(body1));

        JniSpace.removeBody(space, bodyA);
        JniSpace.removeBody(space, bodyB);
        JniSpace.removeBody(space, body1);

        JniBody.free(bodyA);
        JniBody.free(bodyB);
        JniBody.free(body1);
        JniSpace.free(space);
    }

    @Test
    public void testSetType() {
        JniBody.setType(body, CpBody.Type.Dynamic.value);
        assertEquals(CpBody.Type.Dynamic, CpBody.Type.valueOf(JniBody.getType(body)));
        JniBody.setType(body, CpBody.Type.Static.value);
        assertEquals(CpBody.Type.Static, CpBody.Type.valueOf(JniBody.getType(body)));
        JniBody.setType(body, CpBody.Type.Kinematic.value);
        assertEquals(CpBody.Type.Kinematic, CpBody.Type.valueOf(JniBody.getType(body)));
    }

    @Test
    public void testGetSpace() {
        long space = JniSpace.newSpace();
        JniSpace.addBody(space, body);
        assertEquals(space, JniBody.getSpace(body));
        JniSpace.removeBody(space, body);
        assertEquals(0, JniBody.getSpace(body));
    }

    @Test
    public void testSetMass() {
        JniBody.setMass(body, 8.6f);
        assertEquals(8.6f, JniBody.getMass(body), .001f);
    }

    @Test
    public void testSetMoment() {
        JniBody.setMoment(body, 7.7f);
        assertEquals(7.7f, JniBody.getMoment(body), .001f);
    }

    @Test
    public void testSetPosition() {
        JniBody.setPosition(body, 4.5f, -4.5f);
        JniBody.getPosition(body, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(4.5f, -4.5f), .001f);
    }

    @Test
    public void testSetCenterOfGravity() {
        JniBody.setCenterOfGravity(body, .5f, -8f);
        JniBody.getCenterOfGravity(body, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(.5f, -8f), .001f);
    }

    @Test
    public void testSetVelocity() {
        JniBody.setVelocity(body, 5.3f, 2.8f);
        JniBody.getVelocity(body, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(5.3f, 2.8f), .001f);
    }

    @Test
    public void testSetForce() {
        JniBody.setForce(body, 10.9f, 15.6f);
        JniBody.getForce(body, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(10.9f, 15.6f), .001f);
    }

    @Test
    public void testSetAngle() {
        JniBody.setAngle(body, 3.146f);
        assertEquals(3.146f, JniBody.getAngle(body), .001f);
    }

    @Test
    public void testSetAngularVelocity() {
        JniBody.setAngularVelocity(body, 3.2f);
        assertEquals(3.2f, JniBody.getAngularVelocity(body), .001f);
    }

    @Test
    public void testSetTorque() {
        JniBody.setTorque(body, 4.4f);
        assertEquals(4.4f, JniBody.getTorque(body), .001f);
    }

    @Test
    public void testGetRotation() {
        JniBody.setAngle(body, 3.1416f);
        JniBody.getRotation(body, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-1f, 0f), .001f);
    }

    @Test
    public void testSetUserData() {
        JniBody.setUserData(body, 123456);
        assertEquals(123456, JniBody.getUserData(body));
    }

    @Test
    public void testSetPositionUpdateFunc() {
        CpGdxTestHelper gdxHelper = new CpGdxTestHelper();
        CpBody cpBody = gdxHelper.newCpBody(body);

        long space = JniSpace.newSpace();
        gdxHelper.addSpace(space);
        JniSpace.addBody(space, body);

        float[] dataTest = new float[1];
        cpBody.positionFunc = (cpB, delta)->{
            JniBody.setPosition(cpB.nativeAddress, 4f, 2f);
            dataTest[0] = delta;
        };
        JniBody.setPositionUpdateFunc(body, JniChipmunk.bodyPositionJniFunc);
        JniSpace.step(space, .15f);
        JniBody.getPosition(body, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(4f, 2f), .001f);
        assertEquals(.15f, dataTest[0], .001f);

        JniSpace.removeBody(space, body);
        JniSpace.free(space);
        gdxHelper.dispose();
    }

    @Test
    public void testSetVelocityUpdateFunc() {
        float dt = .12f;
        float damp = 5.5f;
        CpGdxTestHelper gdxHelper = new CpGdxTestHelper();
        CpBody cpBody = gdxHelper.newCpBody(body);

        long space = JniSpace.newSpace();
        JniSpace.setGravity(space, 9.8f, -9.8f);
        JniSpace.setDamping(space, damp);
        gdxHelper.addSpace(space);
        JniSpace.addBody(space, body);

        float[] dataTest = new float[4];
        cpBody.velocityFunc = (cpB, gravity_x, gravity_y, damping, delta)->{
            JniBody.setVelocity(body, 15, 10);
            dataTest[0] = gravity_x;
            dataTest[1] = gravity_y;
            dataTest[2] = damping;
            dataTest[3] = delta;
        };
        JniBody.setVelocityUpdateFunc(body, JniChipmunk.bodyVelocityJniFunc);
        
        JniSpace.step(space, dt);
        JniBody.getVelocity(body, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(15f, 10f), .001f);
        assertEquals(9.8f, dataTest[0], .001f);
        assertEquals(-9.8f, dataTest[1], .001f);
        assertEquals(Math.pow(damp, dt), dataTest[2], .001f);
        assertEquals(.12f, dataTest[3], .001f);

        JniSpace.removeBody(space, body);
        JniSpace.free(space);
        gdxHelper.dispose();
    }

    @Test
    public void testUpdatePosition() {
        JniBody.setVelocity(body, 4.5f, 8.9f);
        JniBody.updatePosition(body, 1);
        JniBody.getPosition(body, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(4.5f, 8.9f), .001f);
    }

    @Test
    public void testUpdateVelocity() {
        JniBody.setVelocity(body, 10, 9);
        JniBody.updateVelocity(body, 15.5f, -20.8f, 1.1f, 1f);
        JniBody.getVelocity(body, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(26.5f, -10.9f), .001f);
    }

    @Test
    public void testLocalToWorld() {
        JniBody.setPosition(body, 1.5f, 2.8f);
        JniBody.setAngle(body, MathUtils.PI * .5f);
        JniBody.localToWorld(body, 1f, 1f, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(0.5f, 3.8f), .001f);
    }

    @Test
    public void testWorldToLocal() {
        JniBody.setPosition(body,1.5f, 2.8f);
        JniBody.setAngle(body,MathUtils.PI * .5f);
        JniBody.worldToLocal(body, .5f, 3.8f, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1, 1), .001f);
    }

    @Test
    public void testApplyForceAtWorldPoint() {
        JniBody.init(body, 8, 3);
        JniBody.setAngle(body, MathUtils.PI * .5f);
        JniBody.applyForceAtWorldPoint(body, 20.4f, 5.1f, -1, -1);
        JniBody.getForce(body, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(20.4f, 5.1f), .001f);
    }

    @Test
    public void testApplyForceAtLocalPoint() {
        JniBody.init(body, 8, 3);
        JniBody.setAngle(body,MathUtils.PI * .5f);
        JniBody.applyForceAtLocalPoint(body, 20.4f, 5.1f, -1, -1);
        JniBody.getForce(body, floats);
        assertEquals(0,  vector2.set(floats[0], floats[1]).dst(-5.1f, 20.4f), .001f);
    }

    @Test
    public void testApplyImpulseAtWorldPoint() {
        JniBody.init(body, 8, 3);
        JniBody.setAngle(body, MathUtils.PI * .5f);
        JniBody.applyImpulseAtWorldPoint(body, 2.8f, .6f, -1, -1);
        JniBody.getVelocity(body, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(0.35f, 0.075f), .001f);
    }

    @Test
    public void testApplyImpulseAtLocalPoint() {
        JniBody.init(body, 8, 3);
        JniBody.setAngle(body, MathUtils.PI * .5f);
        JniBody.applyImpulseAtLocalPoint(body, 2.8f, .6f, -1, -1);
        JniBody.getVelocity(body, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-0.075f,0.35f), .001f);
    }

    @Test
    public void testGetVelocityAtWorldPoint() {
        JniBody.setAngle(body, MathUtils.PI*.5f);
        JniBody.setVelocity(body, 1, 0);
        JniBody.setAngularVelocity(body, 1f);
        JniBody.getVelocityAtWorldPoint(body, 0, -1, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.0f, 0f), .001f);
    }

    @Test
    public void testGetVelocityAtLocalPoint() {
        JniBody.setAngle(body, MathUtils.PI*.5f);
        JniBody.setVelocity(body, 1, 0);
        JniBody.setAngularVelocity(body, 1f);
        JniBody.getVelocityAtLocalPoint(body, 0, -1, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1, 1), .001f);
    }

    @Test
    public void testKineticEnergy() {
        JniBody.init(body, 9, 2);
        JniBody.setVelocity(body, 1, 0);
        JniBody.setAngularVelocity(body, 1f);
        assertEquals(11f, JniBody.kineticEnergy(body), .01f);
    }

    @Test
    public void testEachShape() {
        CpGdxTestHelper gdxHelper = new CpGdxTestHelper();
        long space = JniSpace.newSpace();
        long circleA = JniShapeTypeTest.circleNew(body, 1.5f, 0, 0);
        long circleB= JniShapeTypeTest.circleNew(body, 1.5f, 3, 0);
        long circleC= JniShapeTypeTest.circleNew(body, 1.5f, -3, 0);
        JniSpace.addBody(space, body);
        JniSpace.addShape(space, circleA);
        JniSpace.addShape(space, circleB);
        JniSpace.addShape(space, circleC);

        CpBody cpBody = gdxHelper.newCpBody(body);
 
        Array<CpBody> bodies = new Array<CpBody>(new CpBody[] { cpBody, cpBody, cpBody });
        Array<CpShape> shapes = new Array<CpShape>(new CpShape[] { gdxHelper.newCpShape(circleA),
                gdxHelper.newCpShape(circleB), gdxHelper.newCpShape(circleC) });
        cpBody.shapeIteratorFunc = (body, shape) -> {
            bodies.removeValue(body, true);
            shapes.removeValue(shape, true);
        };
        JniBody.eachShape(body, JniChipmunk.bodyShapeIteratorJniFunc, gdxHelper.jniChipmunk.nativeAddress);
        assertEquals(0, bodies.size);
        assertEquals(0, shapes.size);
        
        JniSpace.removeShape(space, circleA);
        JniSpace.removeShape(space, circleB);
        JniSpace.removeShape(space, circleC);
        JniSpace.removeBody(space, body);
        JniShape.setBody(circleA, 0);
        JniShape.setBody(circleB, 0);
        JniShape.setBody(circleC, 0);
        JniShape.free(circleA);
        JniShape.free(circleB);
        JniShape.free(circleC);
        JniSpace.free(space);
        gdxHelper.dispose();
    }

    @Test
    public void testEachConstraint() {
        CpGdxTestHelper gdxHelper = new CpGdxTestHelper();
        long space = JniSpace.newSpace();
        long bodyA = JniBody.newBody(1f,.8f);
        long bodyB = JniBody.newBody(1f,.8f);
        long pinA = JniConstraintTypeTest.pinJointNew(body, bodyA, 0, 0, 0, 0);
        long pinB = JniConstraintTypeTest.pinJointNew(body, bodyB, 0, 0, 0, 0);
        JniSpace.addBody(space, body);
        JniSpace.addBody(space, bodyA);
        JniSpace.addBody(space, bodyB);
        JniSpace.addConstraint(space, pinA);
        JniSpace.addConstraint(space, pinB);

        CpBody cpBody = gdxHelper.newCpBody(body);
        Array<CpBody> bodies = new Array<CpBody>(new CpBody[] { cpBody, cpBody });
        Array<CpConstraint> constraints = new Array<CpConstraint>(new CpConstraint[] { gdxHelper.newCpConstraint(pinA),
                gdxHelper.newCpConstraint(pinB) });

        cpBody.constraintIteratorFunc = (body, constraint) -> {
            bodies.removeValue(body, true);
            constraints.removeValue(constraint, true);
        };
        JniBody.eachConstraint(body, JniChipmunk.bodyConstraintIteratorJniFunc, gdxHelper.jniChipmunk.nativeAddress);
        assertEquals(0, bodies.size);
        assertEquals(0, constraints.size);

        JniSpace.removeConstraint(space, pinA);
        JniSpace.removeConstraint(space, pinB);
        JniSpace.removeBody(space, body);
        JniSpace.removeBody(space, bodyA);
        JniSpace.removeBody(space, bodyB);
        JniConstraint.free(pinA);
        JniConstraint.free(pinB);
        JniBody.free(bodyA);
        JniBody.free(bodyB);
        JniSpace.free(space);
        gdxHelper.dispose();
    }

    @Test
    @Ignore
    public void testEachArbiter() {
        fail("Not yet implemented");
    }

}
