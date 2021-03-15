package net.josid.gdx.chipmunk;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.contact.CpContactPoint;
import net.josid.gdx.chipmunk.contact.CpContactPointSet;
import net.josid.gdx.chipmunk.contact.CpShapeFilter;
import net.josid.gdx.chipmunk.def.CpBodyDef;
import net.josid.gdx.chipmunk.def.shape.CpCircleDef;
import net.josid.gdx.chipmunk.math.CpBoundingBox;
import net.josid.gdx.chipmunk.query.CpPointQueryInfo;
import net.josid.gdx.chipmunk.query.CpSegmentQueryInfo;
import net.josid.gdx.chipmunk.shape.CpCircle;


public class CpShapeTest {

    private CpShape shape;
    private Chipmunk chipmunk;
    private CpBoundingBox boundingBox = new CpBoundingBox();
    private Vector2 vector2 = new Vector2(); 

    @Before
    public void setup() {
        chipmunk = new Chipmunk();
        CpBody body = chipmunk.factory.body(new CpBodyDef().circle().radius(1.5f).offset(2, 1).body(), CpBody.Type.Dynamic);
        shape = body.shapes.get(0);
    }

    @After
    public void cleanUp() {
        chipmunk.dispose();
    }

    @Test
    public void testDefaultFactory() {
        shape.cacheBB(boundingBox);
        assertEquals(7.068f, shape.getArea(), .001f);
        assertEquals(0, shape.getCenterOfGravity(vector2).dst(2, 1), .001f);
        assertEquals(0, shape.getCollisionType());
        assertEquals(0, shape.getDensity(), .001f);
        assertEquals(0, shape.getMass(), .001f);
        assertEquals(0, shape.getMoment(), .001f);
        assertEquals(0, shape.getElasticity(), .001f);
        assertEquals(0, shape.getFriction(), .001f);
        assertFalse(shape.getSensor());
        assertEquals(0, shape.getSurfaceVelocity(vector2).dst(0, 0), .001f);
        CpShapeFilter filter = new CpShapeFilter();
        assertEquals(filter, shape.getFilter(filter));
        assertEquals(0, filter.group);
        assertEquals(~0, filter.categories);
        assertEquals(~0, filter.mask);
    }

    @Test
    public void testParametersFactory() {
        CpBody body = chipmunk.factory.body(new CpBodyDef()
                .circle().radius(1.5f).offset(2, 1)
                .sensor(true).mass(1.5f).elastisity(2.4f).friction(3.3f).surfaceVelocity(4.2f, -4.2f)
                .newFilterDef().group(1234).categories(321).mask(456).body(), CpBody.Type.Dynamic);
        shape = body.shapes.get(0);
        shape.cacheBB(boundingBox);
        assertEquals(.212f, shape.getDensity(), .001f);
        assertEquals(1.5f, shape.getMass(), .001f);
        assertEquals(1.687f, shape.getMoment(), .001f);
        assertEquals(2.4f, shape.getElasticity(), .001f);
        assertEquals(3.3f, shape.getFriction(), .001f);
        assertTrue(shape.getSensor());
        assertEquals(0, shape.getSurfaceVelocity(vector2).dst(4.2f, -4.2f), .001f);
        CpShapeFilter filter = new CpShapeFilter();
        assertEquals(filter, shape.getFilter(filter));
        assertEquals(1234, filter.group);
        assertEquals(321, filter.categories);
        assertEquals(456, filter.mask);
    }

    @Test
    public void testDensityParameterFactory() {
        CpBody body = chipmunk.factory.body(new CpBodyDef()
                .circle().radius(1.5f).offset(2, 1).density(1.5f).body(), CpBody.Type.Dynamic);
        shape = body.shapes.get(0);
        assertEquals(1.5f, shape.getDensity(), .001f);
        assertEquals(10.602f, shape.getMass(), .001f);
        assertEquals(11.928f, shape.getMoment(), .001f);
    }

    @Test
    public void testSetters() {
        shape.cacheBB(boundingBox);
        shape.setCollisionType(123456);
        assertEquals(123456, shape.getCollisionType());
        shape.setDensity(1.1f);
        assertEquals(1.1f, shape.getDensity(), .001f);
        assertEquals(8.747f, shape.getMoment(), .001f);
        shape.setElasticity(2.2f);
        assertEquals(2.2f, shape.getElasticity(), .001f);
        shape.setFilter(8967, -52648, 461354);
        CpShapeFilter filter = new CpShapeFilter();
        assertEquals(filter, shape.getFilter(filter));
        assertEquals(8967, filter.group);
        assertEquals(-52648, filter.categories);
        assertEquals(461354, filter.mask);
        shape.setSensor(true);
        assertTrue(shape.getSensor());
        shape.setFriction(3.3f);
        assertEquals(3.3f, shape.getFriction(), .001f);
        shape.setMass(4.4f);
        assertEquals(4.4f, shape.getMass(), .001f);
        assertEquals(4.95f, shape.getMoment(), .001f);
        shape.setSurfaceVelocity(5.5f, -5.5f);
        assertEquals(0, shape.getSurfaceVelocity(vector2).dst(5.5f, -5.5f), .001f);
    }

    @Test
    public void testCacheBB() {
        assertEquals(boundingBox, shape.cacheBB(boundingBox));
        assertEquals(.5f, boundingBox.left, .001f);
        assertEquals(-.5f, boundingBox.bottom, .001f);
        assertEquals(3.5f, boundingBox.right, .001f);
        assertEquals(2.5f, boundingBox.top, .001f);
    }

    @Test
    public void testUpdate() {
        assertEquals(boundingBox, shape.update(2, 1, 3.1416f, boundingBox));
        assertEquals(-1.5f, boundingBox.left, .001f);
        assertEquals(-1.5f, boundingBox.bottom, .001f);
        assertEquals(1.5f, boundingBox.right, .001f);
        assertEquals(1.5f, boundingBox.top, .001f);
    }

    @Test
    public void testPointQuery() {
        shape.cacheBB(boundingBox);
        CpPointQueryInfo info = new CpPointQueryInfo();
        assertEquals(info, shape.pointQuery(.6f, 1, info));
        assertEquals(-.1f, info.distance, .001f);
        assertEquals(0, info.point.dst(.5f, 1f), .001f);
        assertEquals(0, info.gradient.dst(-1f, 0f), .001f);
    }

    @Test
    public void testSegmentQuery() {
        shape.cacheBB(boundingBox);
        CpSegmentQueryInfo info = new CpSegmentQueryInfo();
        assertEquals(info, shape.segmentQuery(-.7f, .8f, 1.7f, 2.2f, .1f, info));
        assertTrue(info.collides);
        assertEquals(0, info.point.dst(0.5f, 1.4f), .1f);
        assertEquals(0, info.normal.dst(-0.9f,0.3f), .1f);
        assertEquals(.48f, info.alpha, .01f);
    }

    @Test
    public void testCollide() {
        shape.cacheBB(boundingBox);

        CpCircle circle = chipmunk.factory.circle(new CpCircleDef().radius(.6f).offset(0, 1));
        circle.update(0, 0, 0, boundingBox);

        CpContactPointSet points = new CpContactPointSet();
        assertEquals(points, shape.collide(circle, points));
        assertEquals(1, points.count);
        assertEquals(0, points.normal.dst(-1, 0), .001f);
        CpContactPoint point = points.contactPoints[0];
        assertEquals(0, point.pointA.dst(0.5f, 1.0f), .001f);
        assertEquals(0, point.pointB.dst(0.6f, 1.0f), .001f);
        assertEquals(-.1f, point.distance, .001f);
    }

    @Test
    @Ignore
    public void testSetBody() {
        // TODO change to new body;
        fail("Not implemented");
    }

}
