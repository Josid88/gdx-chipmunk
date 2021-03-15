package net.josid.gdx.chipmunk;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import net.josid.gdx.chipmunk.callback.CpPostStepFuncData;
import net.josid.gdx.chipmunk.constraint.JniConstraintTypeTest;
import net.josid.gdx.chipmunk.contact.CpContactPoint;
import net.josid.gdx.chipmunk.query.CpPointQueryInfo;
import net.josid.gdx.chipmunk.query.CpSegmentQueryInfo;
import net.josid.gdx.chipmunk.shape.JniShapeTypeTest;


public class JniSpaceTest {

    public static long newSpace() {
        return JniSpace.newSpace();
    }

    public static void addBody(long space, long body) {
        JniSpace.addBody(space, body);
    }

    public static void removeBody(long space, long body) {
        JniSpace.removeBody(space, body);
    }

    public static void addConstraint(long space, long constraint) {
        JniSpace.addConstraint(space, constraint);
    }

    public static void removeConstraint(long space, long constraint) {
        JniSpace.removeConstraint(space, constraint);
    }

    public static void free(long space) {
        JniSpace.free(space);
    }

    public static void step(long space, float delta) {
        JniSpace.step(space, delta);
    }

    private float[] floats = new float[16];
    private Vector2 vector2 = new Vector2();

    @Before
    public void setup() {
        JniChipmunk.init();
    }

    @Test
    public void testAlloc() {
        long space = JniSpace.alloc();
        JniSpace.init(space);
        JniSpace.getStaticBody(space);
        JniSpace.free(space);
    }

    @Test
    public void testInit() {
        long space = JniSpace.alloc();
        JniSpace.init(space);
        JniSpace.free(space);
    }

    @Test
    public void testNewSpace() {
        long space = JniSpace.newSpace();
        JniSpace.free(space);
    }

    @Test
    @Ignore
    public void testDestroy() {
        //It does not work
        long space = JniSpace.newSpace();
        JniSpace.destroy(space);
        JniSpace.free(space);
    }

    @Test
    public void testFree() {
        long space = JniSpace.newSpace();
        JniSpace.free(space);
    }

    @Test
    public void testSetIterations() {
        long space = JniSpace.newSpace();
        JniSpace.setIterations(space, 3);
        assertEquals(3, JniSpace.getIterations(space));
        JniSpace.free(space);
    }

    @Test
    public void testSetGravity() {
        long space = JniSpace.newSpace();
        JniSpace.setGravity(space, 9.8f, -9.8f);
        JniSpace.getGravity(space, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(9.8f, -9.8f), .001f);
        JniSpace.free(space);
    }

    @Test
    public void testSetDamping() {
        long space = JniSpace.newSpace();
        JniSpace.setDamping(space, 3.6f);
        assertEquals(3.6f, JniSpace.getDamping(space), .001f);
        JniSpace.free(space);
    }

    @Test
    public void testSetIdleSpeedThreshold() {
        long space = JniSpace.newSpace();
        JniSpace.setIdleSpeedThreshold(space, 4.4f);
        assertEquals(4.4f, JniSpace.getIdleSpeedThreshold(space), .001f);
        JniSpace.free(space);
    }

    @Test
    public void testSetSleepTimeThreshold() {
        long space = JniSpace.newSpace();
        JniSpace.setSleepTimeThreshold(space, 2.8f);
        assertEquals(2.8f, JniSpace.getSleepTimeThreshold(space), .001f);
        JniSpace.free(space);
    }

    @Test
    public void testSetCollisionSlop() {
        long space = JniSpace.newSpace();
        JniSpace.setCollisionSlop(space, 5.5f);
        assertEquals(5.5f, JniSpace.getCollisionSlop(space), .001f);
        JniSpace.free(space);
    }

    @Test
    public void testSetCollisionBias() {
        long space = JniSpace.newSpace();
        JniSpace.setCollisionBias(space, 6.1f);
        assertEquals(6.1f, JniSpace.getCollisionBias(space), .001f);
        JniSpace.free(space);
    }

    @Test
    public void testSetCollisionPersistence() {
        long space = JniSpace.newSpace();
        JniSpace.setCollisionPersistence(space, 1234589);
        assertEquals(1234589, JniSpace.getCollisionPersistence(space), .001f);
        JniSpace.free(space);
    }

    @Test
    public void testSetUserData() {
        long space = JniSpace.newSpace();
        JniSpace.setUserData(space, 200);
        assertEquals(200, JniSpace.getUserData(space), .001f);
        JniSpace.free(space);
    }

    @Test
    public void testGetStaticBody() {
        long space = JniSpace.newSpace();
        long staticBody = JniSpace.getStaticBody(space);
        assertEquals(CpBody.Type.Static, CpBody.Type.valueOf(JniBody.getType(staticBody)));
        JniSpace.free(space);
    }

    @Test
    public void testGetCurrentTimeStep() {
        long space = JniSpace.newSpace();
        JniSpace.step(space, .05f);
        assertEquals(.05f, JniSpace.getCurrentTimeStep(space), .001f);
        JniSpace.free(space);
    }

    @Test
    @Ignore
    public void testIsLocked() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddDefaultCollisionHandler() {
        long space = JniSpace.newSpace();
        long defaultCollisionHandler = JniSpace.addDefaultCollisionHandler(space);
        assertNotEquals(0, defaultCollisionHandler);
        JniSpace.free(space);
    }

    @Test
    public void testAddCollisionHandler() {
        long space = JniSpace.newSpace();
        assertNotEquals(0, JniSpace.addCollisionHandler(space, 5, 8));
        JniSpace.free(space);
    }

    @Test
    public void testAddWildcardHandler() {
        long space = JniSpace.newSpace();
        assertNotEquals(0, JniSpace.addWildcardHandler(space, 3));
        JniSpace.free(space);
    }

    @Test
    public void testRemoveShape() {
        long space = JniSpace.newSpace();
        long body = JniBody.newBody(2, 1);
        long circle = JniShapeTypeTest.circleNew(body, .5f, 0, 0);
        JniSpace.addBody(space, body);
        JniSpace.addShape(space, circle);
        assertTrue(JniSpace.containsBody(space, body));
        assertTrue(JniSpace.containsShape(space, circle));
        JniSpace.removeShape(space, circle);
        JniSpace.removeBody(space, body);
        assertFalse(JniSpace.containsBody(space, body));
        assertFalse(JniSpace.containsShape(space, circle));
        JniSpace.free(space);
        JniBody.free(body);
        JniShape.free(circle);
    }

    @Test
    public void testRemoveConstraint() {
        long space = JniSpace.newSpace();
        long bodyA = JniBody.newBody(2, 1);
        long bodyB = JniBody.newBody(2, 1);
        long constraint = JniConstraintTypeTest.pinJointNew(bodyA, bodyB, 0, 0, 0, 0);
        JniSpace.addBody(space, bodyA);
        JniSpace.addBody(space, bodyB);
        JniSpace.addConstraint(space, constraint);
        assertTrue(JniSpace.containsConstraint(space, constraint));
        JniSpace.removeConstraint(space, constraint);
        assertFalse(JniSpace.containsConstraint(space, constraint));
        JniSpace.removeBody(space, bodyA);
        JniSpace.removeBody(space, bodyB);
        JniBody.free(bodyA);
        JniBody.free(bodyB);
        JniConstraint.free(constraint);
        JniSpace.free(space);
    }

    @Test
    public void testAddPostStepCallback() {
        CpGdxTestHelper gdxHelper = new CpGdxTestHelper();
        long space = JniSpace.newSpace();
        gdxHelper.addSpace(space);
        CpSpace cpSpace = gdxHelper.newCpSpace(space);
        int[] found = new int[1];
        cpSpace.postStepFuncs.put(12345, new CpPostStepFuncData().set("value", (s, data)->{
            assertEquals("value", data);
            assertEquals(cpSpace, s);
            found[0] ++;
        }));
        JniSpace.addPostStepCallback(space, JniChipmunk.spacePostStepJniFunc, 12345, 0);
        JniSpace.step(space, .05f);
        JniSpace.step(space, .05f);
        assertEquals(1, found[0]);
        JniSpace.free(space);
        gdxHelper.dispose();
    }

    @Test
    public void testPointQuery() {
        CpGdxTestHelper gdxHelper = new CpGdxTestHelper();
        long space = JniSpace.newSpace();
        gdxHelper.addSpace(space);

        long body = JniBody.newBody(1, .8f);
        long circle = JniShapeTypeTest.circleNew(body, .5f, 0, 0);
        JniSpace.addBody(space, body);
        JniSpace.addShape(space, circle);

        CpSpace cpSpace = gdxHelper.newCpSpace(space);
        CpShape cpShape = gdxHelper.newCpShape(circle);
        int[] found = new int[1];
        cpSpace.pointQueryFunc = (shape, x, y, distance, gradient_x, gradient_y)->{
            assertEquals(shape, cpShape);
            assertEquals(0, vector2.set(x, y).dst(.4f, .3f), .001f);
            assertEquals(2.0f, distance, .001f);
            assertEquals(0, vector2.set(gradient_x, gradient_y).dst(.8f, .6f), .001f);
            found[0]++;
        };
        JniSpace.pointQuery(space, 2f, 1.5f, 3, 0, ~0, ~0, JniChipmunk.spacePointQueryJniFunc, space);
        assertEquals(1, found[0]);

        JniSpace.removeShape(space, circle);
        JniSpace.removeBody(space, body);
        JniSpace.free(space);
        JniShape.free(circle);
        JniBody.free(body);
        gdxHelper.dispose();
    }

    @Test
    public void testPointQueryNearest() {
        long space = JniSpace.newSpace();

        long body = JniBody.newBody(1, .8f);
        long circle = JniShapeTypeTest.circleNew(body, .5f, 0, 0);
        JniSpace.addBody(space, body);
        JniSpace.addShape(space, circle);

        long shape = JniSpace.pointQueryNearest(space, 2f, 1.5f, 3, 0, ~0, ~0, floats);
        CpPointQueryInfo pointQueryInfo = new CpPointQueryInfo().set(null, floats);
        assertEquals(circle, shape);
        assertEquals(0, pointQueryInfo.point.dst(.4f, .3f), .001f);
        assertEquals(2.0f, pointQueryInfo.distance, .001f);
        assertEquals(0, pointQueryInfo.gradient.dst(.8f, .6f), .001f);
        
        JniSpace.removeShape(space, circle);
        JniSpace.removeBody(space, body);
        JniSpace.free(space);
        JniShape.free(circle);
        JniBody.free(body);
    }

    @Test
    public void testSegmentQuery() {
        CpGdxTestHelper gdxHelper = new CpGdxTestHelper();
        long space = JniSpace.newSpace();
        gdxHelper.addSpace(space);

        long body = JniBody.newBody(1, .8f);
        long circle = JniShapeTypeTest.circleNew(body, .5f, 0, 0);
        JniSpace.addBody(space, body);
        JniSpace.addShape(space, circle);

        CpSpace cpSpace = gdxHelper.newCpSpace(space);
        CpShape cpShape = gdxHelper.newCpShape(circle);
        int[] found = new int[1];
        cpSpace.segmentQueryFunc = (shape, x, y, normal_x, normal_y, alpha)->{
            assertEquals(shape, cpShape);
            assertEquals(0, vector2.set(x, y).dst(-0.419f, 0.272f), .001f);
            assertEquals(0, vector2.set(normal_x, normal_y).dst(-0.8381f, 0.545f), .001f);
            assertEquals(0.384f, alpha, .001f);
            found[0]++;
        };
        JniSpace.segmentQuery(space, -2f, .3f, 2, .3f, .05f, 0, ~0, ~0, JniChipmunk.spaceSegmentQueryJniFunc, space);
        assertEquals(1, found[0]);

        JniSpace.removeShape(space, circle);
        JniSpace.removeBody(space, body);
        JniSpace.free(space);
        JniShape.free(circle);
        JniBody.free(body);
        gdxHelper.dispose();
    }

    @Test
    public void testSegmentQueryFirst() {
        long space = JniSpace.newSpace();

        long body = JniBody.newBody(1, .8f);
        long circle = JniShapeTypeTest.circleNew(body, .5f, 0, 0);
        JniSpace.addBody(space, body);
        JniSpace.addShape(space, circle);

        long shape = JniSpace.segmentQueryFirst(space, -2f, .3f, 2, .3f, .05f, 0, ~0, ~0, floats);
        CpSegmentQueryInfo segmentQueryInfo = new CpSegmentQueryInfo().set(null, floats);
        assertEquals(shape, circle);
        assertEquals(0, segmentQueryInfo.point.dst(-0.419f, 0.272f), .001f);
        assertEquals(0, segmentQueryInfo.normal.dst(-0.8381f, 0.545f), .001f);
        assertEquals(0.384f, segmentQueryInfo.alpha, .001f);

        JniSpace.removeShape(space, circle);
        JniSpace.removeBody(space, body);
        JniSpace.free(space);
        JniShape.free(circle);
        JniBody.free(body);
    }

    @Test
    public void testBBQuery() {
        CpGdxTestHelper gdxHelper = new CpGdxTestHelper();
        long space = JniSpace.newSpace();
        gdxHelper.addSpace(space);

        long body = JniBody.newBody(1, .8f);
        long circle = JniShapeTypeTest.circleNew(body, .5f, 0, 0);
        JniSpace.addBody(space, body);
        JniSpace.addShape(space, circle);

        CpSpace cpSpace = gdxHelper.newCpSpace(space);
        CpShape cpShape = gdxHelper.newCpShape(circle);
        int[] found = new int[1];
        cpSpace.bbQueryFunc = shape->{
            assertEquals(shape, cpShape);
            found[0]++;
        };
        JniSpace.bBQuery(space, -3, -2, 3, 2, 0, ~0, ~0, JniChipmunk.spaceBbQueryJniFunc, space);
        assertEquals(1, found[0]);

        JniSpace.removeShape(space, circle);
        JniSpace.removeBody(space, body);
        JniSpace.free(space);
        JniShape.free(circle);
        JniBody.free(body);
        gdxHelper.dispose();
    }

    @Test
    public void testShapeQuery() {
        CpGdxTestHelper gdxHelper = new CpGdxTestHelper();
        long space = JniSpace.newSpace();
        gdxHelper.addSpace(space);

        long circleShape = JniShapeTypeTest.circleNew(0, 1.5f, 0, 0);

        long body = JniBody.newBody(1, .8f);
        long circle = JniShapeTypeTest.circleNew(body, .5f, 0, 0);
        JniSpace.addBody(space, body);
        JniSpace.addShape(space, circle);

        CpSpace cpSpace = gdxHelper.newCpSpace(space);
        CpShape cpShape = gdxHelper.newCpShape(circle);
        int[] found = new int[1];
        cpSpace.shapeQueryFunc = (s, p)-> {
            assertEquals(cpShape, s);
            assertEquals(1, p.count);
            assertEquals(0, p.normal.dst(1.0f, 0.0f), .001f);
            CpContactPoint contactPoints = p.contactPoints[0];
            assertEquals(-2f, contactPoints.distance, .001f);
            assertEquals(0, contactPoints.pointA.dst(1.5f, 0.0f), .001f);
            assertEquals(0, contactPoints.pointB.dst(-0.5f, 0.0f), .001f);
            found[0]++;
        };
        cpSpace.shapeQueryFuncFloats = new float[16];
        JniSpace.shapeQuery(space, circleShape, JniChipmunk.spaceShapeQueryJniFunc, cpSpace.shapeQueryFuncFloats);
        assertEquals(1, found[0]);

        JniSpace.removeShape(space, circle);
        JniSpace.removeBody(space, body);
        JniSpace.free(space);
        JniShape.free(circle);
        JniBody.free(body);
        gdxHelper.dispose();
    }

    @Test
    public void testEachBody() {
        CpGdxTestHelper gdxHelper = new CpGdxTestHelper();
        long space = JniSpace.newSpace();
        gdxHelper.addSpace(space);

        long body1 = JniBody.newBody(1, .8f);
        JniSpace.addBody(space, body1);
        long body2 = JniBody.newBody(1, .8f);
        JniSpace.addBody(space, body2);
        long body3 = JniBody.newBody(1, .8f);
        JniSpace.addBody(space, body3);

        CpSpace cpSpace = gdxHelper.newCpSpace(space);
        Array<CpBody> bodies = new Array<CpBody>(new CpBody[] {
                gdxHelper.newCpBody(body1), gdxHelper.newCpBody(body2), gdxHelper.newCpBody(body3)
        });
        cpSpace.bodyIteratorFunc = (b)->{
            bodies.removeValue(b, true);
        };
        JniSpace.eachBody(space, JniChipmunk.spaceBodyIteratorJniFunc, space);
        assertEquals(0, bodies.size);

        JniSpace.removeBody(space, body1);
        JniSpace.removeBody(space, body2);
        JniSpace.removeBody(space, body3);
        JniBody.free(body1);
        JniBody.free(body2);
        JniBody.free(body3);
        gdxHelper.dispose();
        JniSpace.free(space);
    }

    @Test
    public void testEachShape() {
        CpGdxTestHelper gdxHelper = new CpGdxTestHelper();
        long space = JniSpace.newSpace();
        gdxHelper.addSpace(space);

        long body = JniBody.newBody(1, .8f);
        long circle1 = JniShapeTypeTest.circleNew(body, .5f, 0, 0);
        long circle2 = JniShapeTypeTest.circleNew(body, .5f, 0, 0);
        long circle3 = JniShapeTypeTest.circleNew(body, .5f, 0, 0);
        JniSpace.addBody(space, body);
        JniSpace.addShape(space, circle1);
        JniSpace.addShape(space, circle2);
        JniSpace.addShape(space, circle3);

        CpSpace cpSpace = gdxHelper.newCpSpace(space);
        Array<CpShape> shapes = new Array<CpShape>(new CpShape[] {
            gdxHelper.newCpShape(circle1), gdxHelper.newCpShape(circle2), gdxHelper.newCpShape(circle3)
        });
        cpSpace.shapeIteratorFunc = (s)->{
            shapes.removeValue(s, true);
        };
        JniSpace.eachShape(space, JniChipmunk.spaceShapeIteratorJniFunc, space);
        assertEquals(0, shapes.size);

        JniSpace.removeShape(space, circle1);
        JniSpace.removeShape(space, circle2);
        JniSpace.removeShape(space, circle3);
        JniSpace.removeBody(space, body);
        JniBody.free(body);
        JniShape.free(circle1);
        JniShape.free(circle2);
        JniShape.free(circle3);
        gdxHelper.dispose();
        JniSpace.free(space);
    }

    @Test
    public void testEachConstraint() {
        long space = JniSpace.newSpace();
        CpGdxTestHelper gdxHelper = new CpGdxTestHelper();
        gdxHelper.addSpace(space);
        long bodyA = JniBody.newBody(1f,.8f);
        long bodyB = JniBody.newBody(1f,.8f);
        long bodyC = JniBody.newBody(1f,.8f);
        long pinA = JniConstraintTypeTest.pinJointNew(bodyA, bodyB, 0, 0, 0, 0);
        long pinB = JniConstraintTypeTest.pinJointNew(bodyB, bodyC, 0, 0, 0, 0);
        JniSpace.addBody(space, bodyA);
        JniSpace.addBody(space, bodyB);
        JniSpace.addBody(space, bodyC);
        JniSpace.addConstraint(space, pinA);
        JniSpace.addConstraint(space, pinB);

        CpSpace cpSpace = gdxHelper.newCpSpace(space);
        Array<CpConstraint> constraints = new Array<CpConstraint>(new CpConstraint[] {
            gdxHelper.newCpConstraint(pinA), gdxHelper.newCpConstraint(pinB)
        });
        cpSpace.constraintIteratorFunc = (c)->{
            constraints.removeValue(c, true);
        };
        JniSpace.eachConstraint(space, JniChipmunk.spaceConstraintIteratorJniFunc, space);
        assertEquals(0, constraints.size);

        JniSpace.removeConstraint(space, pinA);
        JniSpace.removeConstraint(space, pinB);
        JniSpace.removeBody(space, bodyA);
        JniSpace.removeBody(space, bodyB);
        JniSpace.removeBody(space, bodyC);
        JniConstraint.free(pinA);
        JniBody.free(bodyA);
        JniBody.free(bodyB);
        JniBody.free(bodyC);
        gdxHelper.dispose();
        JniSpace.free(space);
    }

    @Test
    public void testReindexStatic() {
        long space = JniSpace.newSpace();
        JniSpace.reindexStatic(space);
        JniSpace.free(space);
    }

    @Test
    public void testReindexShape() {
        long space = JniSpace.newSpace();
        long body = JniBody.newBody(1, .8f);
        long circle = JniShapeTypeTest.circleNew(body, .5f, 0, 0);
        JniSpace.addBody(space, body);
        JniSpace.addShape(space, circle);

        JniSpace.reindexShape(space, circle);

        JniSpace.removeShape(space, circle);
        JniSpace.removeBody(space, body);
        JniBody.free(body);
        JniShape.free(circle);
        JniSpace.free(space);
    }

    @Test
    public void testReindexShapesForBody() {
        long space = JniSpace.newSpace();
        long body = JniBody.newBody(1, .8f);
        long circle1 = JniShapeTypeTest.circleNew(body, .5f, 0, 0);
        long circle2 = JniShapeTypeTest.circleNew(body, .5f, 0, 0);
        long circle3 = JniShapeTypeTest.circleNew(body, .5f, 0, 0);
        JniSpace.addBody(space, body);
        JniSpace.addShape(space, circle1);
        JniSpace.addShape(space, circle2);
        JniSpace.addShape(space, circle3);

        JniSpace.reindexShapesForBody(space, body);

        JniSpace.removeShape(space, circle1);
        JniSpace.removeShape(space, circle2);
        JniSpace.removeShape(space, circle3);
        JniSpace.removeBody(space, body);
        JniBody.free(body);
        JniShape.free(circle1);
        JniShape.free(circle2);
        JniShape.free(circle3);
        JniSpace.free(space);
    }

    @Test
    public void testUseSpatialHash() {
        long space = JniSpace.newSpace();
        JniSpace.useSpatialHash(space, 10f, 100);
        JniSpace.free(space);
    }

    @Test
    public void testStep() {
        long space = JniSpace.newSpace();
        JniSpace.step(space, .05f);
        JniSpace.free(space);
    }

}
