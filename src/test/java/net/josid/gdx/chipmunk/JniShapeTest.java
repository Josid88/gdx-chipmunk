package net.josid.gdx.chipmunk;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.contact.CpContactPoint;
import net.josid.gdx.chipmunk.contact.CpContactPointSet;
import net.josid.gdx.chipmunk.contact.CpShapeFilter;
import net.josid.gdx.chipmunk.math.CpBoundingBox;
import net.josid.gdx.chipmunk.query.CpPointQueryInfo;
import net.josid.gdx.chipmunk.query.CpSegmentQueryInfo;
import net.josid.gdx.chipmunk.shape.JniShapeTypeTest;


public class JniShapeTest {

    public static void free(long shape) {
        JniShape.free(shape);
    }

    public static long getBody(long shape) {
        return JniShape.getBody(shape);
    }

    private long shape;
    private float[] floats = new float[16];
    private int[] ints = new int[16];
    private Vector2 vector2 = new Vector2();

    @Before
    public void setup() {
        JniChipmunk.init();
        shape = JniShapeTypeTest.circleNew(0, 1.5f, 2, 1);
    }

    @After
    public void cleanUp() {
        JniShape.free(shape);
    }

    
    @Test
    public void testDestroy() {
        JniShape.destroy(shape);
    }

    @Test
    public void testFree() {
        long circle = JniShapeTypeTest.circleNew(0, 1.5f, 2, 1);
        JniShape.free(circle);
    }

    @Test
    public void testCacheBB() {
        long body = JniBody.newBody(0, 0);
        JniShape.setBody(shape, body);
        
        JniShape.cacheBB(shape, floats);
        CpBoundingBox boundingBox = new CpBoundingBox().set(floats);
        assertEquals(.5f, boundingBox.left, .001f);
        assertEquals(-.5f, boundingBox.bottom, .001f);
        assertEquals(3.5f, boundingBox.right, .001f);
        assertEquals(2.5f, boundingBox.top, .001f);
        
        JniShape.setBody(shape, 0);
        JniBody.free(body);
    }

    @Test
    public void testUpdate() {
        JniShape.update(shape, 2, 1, 3.1416f, floats);
        CpBoundingBox boundingBox = new CpBoundingBox().set(floats);
        assertEquals(-1.5f, boundingBox.left, .001f);
        assertEquals(-1.5f, boundingBox.bottom, .001f);
        assertEquals(1.5f, boundingBox.right, .001f);
        assertEquals(1.5f, boundingBox.top, .001f);
    }

    @Test
    public void testPointQuery() {
        JniShape.update(shape, 0, 0, 0, floats);
        float distance = JniShape.pointQuery(shape, .6f, 1, floats);
        CpPointQueryInfo pointQuery = new CpPointQueryInfo().set(null, floats);
        assertEquals(-.1f, distance, .001f);
        assertEquals(-.1f, pointQuery.distance, .001f);
        assertEquals(0, pointQuery.point.dst(.5f, 1f), .001f);
        assertEquals(0, pointQuery.gradient.dst(-1f, 0f), .001f);
    }

    @Test
    public void testSegmentQuery() {
        JniShape.update(shape, 0, 0, 0, floats);
        boolean collides = JniShape.segmentQuery(shape, -.7f, .8f, 1.7f, 2.2f, .1f, floats);
        CpSegmentQueryInfo segmentQueryInfo = new CpSegmentQueryInfo().set(null, floats);
        assertTrue(collides);
        assertEquals(0, segmentQueryInfo.point.dst(0.5f, 1.4f), .1f);
        assertEquals(0, segmentQueryInfo.normal.dst(-0.9f,0.3f), .1f);
        assertEquals(.48f, segmentQueryInfo.alpha, .01f);
    }

    @Test
    public void testCollide() {
        long circle2 = JniShapeTypeTest.circleNew(0, .6f, 0, 1);
        JniShape.update(shape, 0, 0, 0, floats);
        JniShape.update(circle2, 0, 0, 0, floats);

        int count = JniShape.collide(shape, circle2, floats);
        CpContactPointSet contactPointSet = new CpContactPointSet().set(count, floats);
        assertEquals(1, count);
        assertEquals(1, contactPointSet.count);
        assertEquals(0, contactPointSet.normal.dst(-1, 0), .001f);
        CpContactPoint contactPoint = contactPointSet.contactPoints[0];
        assertEquals(0, contactPoint.pointA.dst(0.5f, 1.0f), .001f);
        assertEquals(0, contactPoint.pointB.dst(0.6f, 1.0f), .001f);
        assertEquals(-.1f, contactPoint.distance, .001f);

        JniShape.free(circle2);
    }

    @Test
    public void testGetSpace() {
        long body = JniBody.newBody(1, 1);
        JniShape.setBody(shape, body);
        assertEquals(0, JniShape.getSpace(shape));

        long space = JniSpace.newSpace();
        JniSpace.addBody(space, body);
        JniSpace.addShape(space, shape);
        assertEquals(space, JniShape.getSpace(shape));

        JniSpace.removeShape(space, shape);
        assertEquals(0, JniShape.getSpace(shape));

        JniShape.setBody(shape, 0);
        JniBody.free(body);
        JniSpace.free(space);
    }

    @Test
    public void testSetBody() {
        assertEquals(0, JniShape.getBody(shape));

        long body = JniBody.newBody(1, 1);
        JniShape.setBody(shape, body);
        assertEquals(body, JniShape.getBody(shape));

        JniShape.setBody(shape, 0);
        assertEquals(0, JniShape.getBody(shape));

        JniBody.free(body);
    }

    @Test
    public void testSetMass() {
        assertEquals(0, JniShape.getMass(shape), .001f);
        JniShape.setMass(shape, 1.5f);
        assertEquals(1.5f, JniShape.getMass(shape), .001f);
    }

    @Test
    public void testSetDensity() {
        assertEquals(0, JniShape.getDensity(shape), .001f);
        JniShape.setDensity(shape, 1.8f);
        assertEquals(1.8f, JniShape.getDensity(shape), .001f);
    }

    @Test
    public void testGetMoment() {
        assertEquals(0, JniShape.getMoment(shape), .001f);
        JniShape.setMass(shape, .8f);
        assertEquals(0.9, JniShape.getMoment(shape), .001f);
    }

    @Test
    public void testGetArea() {
        assertEquals(7.06858f, JniShape.getArea(shape), .001f);
    }

    @Test
    public void testGetCenterOfGravity() {
        JniShape.getCenterOfGravity(shape, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2f, 1f), .001f);
    }

    @Test
    public void testGetBB() {
        JniShape.update(shape, 0, 0, 0, floats);
        JniShape.getBB(shape, floats);
        CpBoundingBox bb = new CpBoundingBox().set(floats);
        assertEquals(.5f, bb.left, .001f);
        assertEquals(-.5f, bb.bottom, .001f);
        assertEquals(3.5f, bb.right, .001f);
        assertEquals(2.5f, bb.top, .001f);
    }

    @Test
    public void testSetSensor() {
        assertFalse(JniShape.getSensor(shape));
        JniShape.setSensor(shape, true);
        assertTrue(JniShape.getSensor(shape));
    }

    @Test
    public void testSetElasticity() {
        assertEquals(0, JniShape.getElasticity(shape), .001f);
        JniShape.setElasticity(shape, .6f);
        assertEquals(.6f, JniShape.getElasticity(shape), .001f);
    }

    @Test
    public void testSetFriction() {
        assertEquals(0, JniShape.getFriction(shape), .001f);
        JniShape.setFriction(shape, .8f);
        assertEquals(.8f, JniShape.getFriction(shape), .001f);
    }

    @Test
    public void testSetSurfaceVelocity() {
        JniShape.getSurfaceVelocity(shape, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(0, 0), .001f);
        JniShape.setSurfaceVelocity(shape, .3f, .8f);
        JniShape.getSurfaceVelocity(shape, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(.3f, .8f), .001f);
    }

    @Test
    public void testSetUserData() {
        JniShape.setUserData(shape, 50000);
        assertEquals(50000, JniShape.getUserData(shape));
    }

    @Test
    public void testSetCollisionType() {
        assertEquals(0, JniShape.getCollisionType(shape));
        JniShape.setCollisionType(shape, 1500);
        assertEquals(1500, JniShape.getCollisionType(shape));
    }

    @Test
    public void testSetFilter() {
        JniShape.getFilter(shape, ints);
        CpShapeFilter filter = new CpShapeFilter().set(ints);
        assertEquals(0, filter.group);
        assertEquals(~0, filter.categories);
        assertEquals(~0, filter.mask);

        JniShape.setFilter(shape, 78945635, 2345, Integer.MAX_VALUE);
        JniShape.getFilter(shape, ints);
        filter.set(ints);
        assertEquals(78945635, filter.group);
        assertEquals(2345, filter.categories);
        assertEquals(Integer.MAX_VALUE, filter.mask);
    }

}
