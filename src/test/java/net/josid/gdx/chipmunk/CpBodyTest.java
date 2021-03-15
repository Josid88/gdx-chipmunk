package net.josid.gdx.chipmunk;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.CpBody.Type;
import net.josid.gdx.chipmunk.def.CpBodyDef;
import net.josid.gdx.chipmunk.def.CpBodyInstanceDef;
import net.josid.gdx.chipmunk.def.CpSpaceDef;
import net.josid.gdx.chipmunk.def.constraint.CpPinJointDef;


public class CpBodyTest {

    private Chipmunk chipmunk;
    private Vector2 vector2 = new Vector2();
    private CpBody body; 

    @Before
    public void setup() {
        chipmunk = new Chipmunk();
        body = chipmunk.factory.body(new CpBodyDef(), CpBody.Type.Dynamic);
    }

    @After
    public void cleanUp() {
        chipmunk.dispose();
    }

    @Test
    public void testDefaultFactory() {
        assertEquals(0, body.getAngle(), .001f);
        assertEquals(0, body.getAngularVelocity(), .001f);
        assertEquals(0, body.getCenterOfGravity(vector2).dst(0, 0), .001f);
        assertEquals(0, body.getForce(vector2).dst(0, 0), .001f);
        assertEquals(0, body.getMass(), .001f);
        assertEquals(0, body.getMoment(), .001f);
        assertEquals(0, body.getPosition(vector2).dst(0, 0), .001f);
        assertEquals(0, body.getRotation(vector2).dst(1, 0), .001f);
        assertEquals(0, body.getTorque(), .001f);
        assertEquals(CpBody.Type.Dynamic, body.getType());
        assertEquals(0, body.getVelocity(vector2).dst(0, 0), .001f);
    }

    @Test
    public void testStaticFactory() {
        body = chipmunk.factory.body(new CpBodyDef(), CpBody.Type.Static);
        assertEquals(CpBody.Type.Static, body.getType());
    }

    @Test
    public void testKinematicFactory() {
        body = chipmunk.factory.body(new CpBodyDef(), CpBody.Type.Kinematic);
        assertEquals(CpBody.Type.Kinematic, body.getType());
    }

    @Test
    public void testParametersFactory() {
        CpBodyInstanceDef instanceDef = new CpBodyInstanceDef().angle(3.1416f).angularVeloctiy(1.5f)
                .position(2.4f, -2.4f).velocity(3.3f, -3.3f);
        CpBody body = chipmunk.factory.body(new CpBodyDef(), instanceDef);
        assertEquals(CpBody.Type.Dynamic, body.getType());
        assertEquals(3.1416f, body.getAngle(), .001f);
        assertEquals(1.5f, body.getAngularVelocity(), .001f);
        assertEquals(0, body.getPosition(vector2).dst(2.4f, -2.4f), .001f);
        assertEquals(0, body.getRotation(vector2).dst(-1, 0), .001f);
        assertEquals(0, body.getVelocity(vector2).dst(3.3f, -3.3f), .001f);
    }

    @Test
    public void testSetters() {
        body.setAngle(3.1416f);
        assertEquals(3.1416f, body.getAngle(), .001f);
        body.setAngularVelocity(1.9f);
        assertEquals(1.9f, body.getAngularVelocity(), .001f);
        body.setCenterOfGravity(2.8f, -2.8f);
        assertEquals(0, body.getCenterOfGravity(vector2).dst(2.8f, -2.8f), .001f);
        body.setForce(3.7f, -3.7f);
        assertEquals(0, body.getForce(vector2).dst(3.7f, -3.7f), .001f);
        body.setMass(4.6f);
        assertEquals(4.6f, body.getMass(), .001f);
        body.setMoment(5.5f);
        assertEquals(5.5f, body.getMoment(), .001f);
        body.setPosition(6.4f, -6.4f);
        assertEquals(0, body.getPosition(vector2).dst(6.4f, -6.4f), .001f);
        body.setTorque(7.3f);
        assertEquals(7.3f, body.getTorque(), .001f);
        body.setVelocity(8.2f, -8.2f);
        assertEquals(0, body.getVelocity(vector2).dst(8.2f, -8.2f), .001f);
    }

    @Test
    public void testSyncShapes() {
        CpSpace space = chipmunk.factory.space();

        CpBody body = chipmunk.factory.body(new CpBodyDef()
                .circle().radius(1.5f).offset(2, 1).body()
                .box().width(2).height(1).body()
                .segment().a(0, 0).b(0, .1f).body(), CpBody.Type.Dynamic);

        space.addBodyAndShapes(body);
        assertEquals(3, body.shapes.size);
        body.shapes.clear();
        body.syncShapes();
        assertEquals(3, body.shapes.size);
    }

    @Test
    public void testSyncConstraints() {
        CpSpace space = chipmunk.factory.space();

        CpBody body1 = chipmunk.factory.body(new CpBodyDef(), CpBody.Type.Dynamic);
        CpBody body2 = chipmunk.factory.body(new CpBodyDef(), CpBody.Type.Dynamic);
        CpBody body3 = chipmunk.factory.body(new CpBodyDef(), CpBody.Type.Dynamic);
        CpConstraint constraint1 = chipmunk.factory.constraint(new CpPinJointDef().init(0, 0, 0, 0).bodies(body1, body2));
        CpConstraint constraint2 = chipmunk.factory.constraint(new CpPinJointDef().init(0, 0, 0, 0).bodies(body1, body3));

        space.addBody(body1);
        space.addBody(body2);
        space.addBody(body3);
        space.addConstraint(constraint1);
        space.addConstraint(constraint2);

        assertEquals(2, body1.constraints.size);
        body1.constraints.clear();
        body1.syncConstraints();
        assertEquals(2, body1.constraints.size);
    }

    @Test
    public void testActivate() {
        CpSpace space = chipmunk.factory.space(new CpSpaceDef().sleepTimeThreshold(.5f));

        space.addBody(body);
        assertFalse(body.isSleeping());
        body.sleep();
        assertTrue(body.isSleeping());
        body.activate();
        assertFalse(body.isSleeping());
    }

    @Test
    @Ignore
    public void testActivateStatic() {
        fail("Not yet implemented");
    }

    @Test
    public void testSleepWithGroup() {
        CpSpace space = chipmunk.factory.space(new CpSpaceDef().sleepTimeThreshold(.5f));

        CpBodyDef bodyDef = new CpBodyDef();
        CpBody bodyA = chipmunk.factory.body(bodyDef, Type.Dynamic);
        space.addBody(bodyA);
        CpBody bodyB = chipmunk.factory.body(bodyDef, Type.Dynamic);
        space.addBody(bodyB);
        CpBody body1 = chipmunk.factory.body(bodyDef, Type.Dynamic);
        space.addBody(body1);

        assertFalse(bodyA.isSleeping());
        assertFalse(bodyB.isSleeping());
        assertFalse(body1.isSleeping());

        bodyA.sleepWithGroup();
        bodyB.sleepWithGroup(bodyA);
        body1.sleepWithGroup();
        assertTrue(bodyA.isSleeping());
        assertTrue(bodyB.isSleeping());
        assertTrue(body1.isSleeping());

        bodyA.activate();
        assertFalse(bodyA.isSleeping());
        assertFalse(bodyB.isSleeping());
        assertTrue(body1.isSleeping());
    }

    @Test
    public void testSetVelocityUpdateFunc() {
        float dt = .12f;
        float damp = 5.5f;

        CpSpace space = chipmunk.factory.space( new CpSpaceDef().gravity(9.8f, -9.8f).damping(damp));
        space.addBody(body);

        body.setVelocityUpdateFunc((cpB, gravity_x, gravity_y, damping, delta)->{
            cpB.setVelocity(15, 10);
            assertEquals(9.8f, gravity_x, .001f);
            assertEquals(-9.8f, gravity_y, .001f);
            assertEquals(Math.pow(damp, delta), damping, .001f);
            assertEquals(.12f, delta, .001f);
        });
        space.step(dt);
        assertEquals(0, body.getVelocity(vector2).dst(15f, 10f), .001f);
    }

    @Test
    public void testSetPositionUpdateFunc() {
        CpSpace space = chipmunk.factory.space();
        space.addBody(body);

        float[] dataTest = new float[1];
        body.setPositionUpdateFunc( (cpB, delta)->{
            JniBody.setPosition(cpB.nativeAddress, 4f, 2f);
            dataTest[0] = delta;
        } );
        space.step(.15f);
        assertEquals(0, body.getPosition(vector2).dst(4f, 2f), .001f);
        assertEquals(.15f, dataTest[0], .001f);
    }

    @Test
    public void testUpdateVelocity() {
        body.setMass(1.5f);
        body.setMoment(1.4f);
        body.setVelocity(10, 9);
        body.updateVelocity(15.5f, -20.8f, 1.1f, 1f);
        assertEquals(0, body.getVelocity(vector2).dst(26.5f, -10.9f), .001f);
    }

    @Test
    public void testUpdatePosition() {
        body.setVelocity(4.5f, 8.9f);
        body.updatePosition(1);
        assertEquals(0, body.getPosition(vector2).dst(4.5f, 8.9f), .001f);
    }

    @Test
    public void testLocalToWorld() {
        body.setPosition(1.5f, 2.8f);
        body.setAngle(MathUtils.PI * .5f);
        assertEquals(0, body.localToWorld(1f, 1f, vector2).dst(0.5f, 3.8f), .001f);
    }

    @Test
    public void testWorldToLocal() {
        body.setPosition(1.5f, 2.8f);
        body.setAngle(MathUtils.PI * .5f);
        assertEquals(0, body.worldToLocal(.5f, 3.8f, vector2).dst(1, 1), .001f);
    }

    @Test
    public void testApplyForceAtWorldPoint() {
        body.setMass(8f);
        body.setMoment(3f);
        body.setAngle(MathUtils.PI * .5f);
        body.applyForceAtWorldPoint(20.4f, 5.1f, -1, -1);
        assertEquals(0, body.getForce(vector2).dst(20.4f, 5.1f), .001f);
    }

    @Test
    public void testApplyForceAtLocalPoint() {
        body.setMass(8f);
        body.setMoment(3f);
        body.setAngle(MathUtils.PI * .5f);
        body.applyForceAtLocalPoint(20.4f, 5.1f, -1, -1);
        assertEquals(0, body.getForce(vector2).dst(-5.1f, 20.4f), .001f);
    }

    @Test
    public void testApplyImpulseAtWorldPoint() {
        body.setMass(8f);
        body.setMoment(3f);
        body.setAngle(MathUtils.PI * .5f);
        body.applyImpulseAtWorldPoint(2.8f, .6f, -1, -1);
        assertEquals(0, body.getVelocity(vector2).dst(0.35f, 0.075f), .001f);
    }

    @Test
    public void testApplyImpulseAtLocalPoint() {
        body.setMass(8f);
        body.setMoment(3f);
        body.setAngle(MathUtils.PI * .5f);
        body.applyImpulseAtLocalPoint(2.8f, .6f, -1, -1);
        assertEquals(0, body.getVelocity(vector2).dst(-0.075f, 0.35f), .001f);
    }

    @Test
    public void testGetVelocityAtWorldPoint() {
        body.setAngle(MathUtils.PI * .5f);
        body.setVelocity(1, 0);
        body.setAngularVelocity(1f);
        assertEquals(0, body.getVelocityAtWorldPoint(0, -1, vector2).dst(2.0f, 0f), .001f);
    }

    @Test
    public void testGetVelocityAtLocalPoint() {
        body.setAngle(MathUtils.PI * .5f);
        body.setVelocity(1, 0);
        body.setAngularVelocity(1f);
        assertEquals(0, body.getVelocityAtLocalPoint(0, -1, vector2).dst(1, 1), .001f);
    }

    @Test
    public void testKineticEnergy() {
        body.setMass(9);
        body.setMoment(2);
        body.setVelocity(1, 0);
        body.setAngularVelocity(1f);
        assertEquals(11f, body.kineticEnergy(), .01f);
    }

    @Test
    @Ignore
    public void testEachArbiter() {
        fail("Not yet implemented");
    }

}
