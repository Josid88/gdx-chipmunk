package net.josid.gdx.chipmunk.shape;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.JniCpChipmunkTest;
import net.josid.gdx.chipmunk.JniShapeTest;


public class JniShapeTypeTest {

    public static long circleNew(long body, float radius, float offset_x, float offset_y) {
        return JniShapeType.circleNew(body, radius, offset_x, offset_y);
    }

    private float[] floats = new float[16];
    private Vector2 vector2 = new Vector2();


    @Before
    public void setup() {
        JniCpChipmunkTest.init();
    }

    @Test
    public void testCircleAlloc() {
        long circle = JniShapeType.circleAlloc();
        JniShapeTest.free(circle);
    }

    @Test
    public void testCircleInit() {
        long circle = JniShapeType.circleAlloc();
        JniShapeType.circleInit(circle, 0, .5f, 1.5f, -1.5f);
        assertEquals(0, JniShapeTest.getBody(circle));
        assertEquals(.5f, JniShapeType.circleGetRadius(circle), 001f);
        JniShapeType.circleGetOffset(circle, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1.5f, -1.5f), 001f);
        JniShapeTest.free(circle);
    }

    @Test
    public void testCircleNew() {
        long circle = JniShapeType.circleNew(0, 1.5f, 2.5f, -2.5f);
        assertEquals(0, JniShapeTest.getBody(circle));
        assertEquals(1.5f, JniShapeType.circleGetRadius(circle), 001f);
        JniShapeType.circleGetOffset(circle, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.5f, -2.5f), 001f);
        JniShapeTest.free(circle);
    }

    @Test
    public void testSegmentAlloc() {
        long segment = JniShapeType.segmentAlloc();
        JniShapeTest.free(segment);
    }

    @Test
    public void testSementInit() {
        long segment = JniShapeType.segmentAlloc();
        JniShapeType.sementInit(segment, 0, 1.4f, -1.6f, 2.9f, -2.2f, .2f);
        assertEquals(0, JniShapeTest.getBody(segment));
        JniShapeType.segmentGetA(segment, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1.4f, -1.6f), 001f);
        JniShapeType.segmentGetB(segment, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.9f, -2.2f), 001f);
        JniShapeType.segmentGetNormal(segment, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-0.3713f, -0.9284f), 001f);
        assertEquals(.2, JniShapeType.segmentGetRadius(segment), 001f);
        JniShapeTest.free(segment);
    }

    @Test
    public void testSegmentNew() {
        long segment = JniShapeType.segmentNew(0, 2.4f, -2.6f, 3.9f, -3.2f, .3f);
        assertEquals(0, JniShapeTest.getBody(segment));
        JniShapeType.segmentGetA(segment, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.4f, -2.6f), 001f);
        JniShapeType.segmentGetB(segment, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(3.9f, -3.2f), 001f);
        JniShapeType.segmentGetNormal(segment, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-0.3713f, -0.9284f), 001f);
        assertEquals(.3f, JniShapeType.segmentGetRadius(segment), 001f);
        JniShapeTest.free(segment);
    }

    @Test
    public void testSegmenSetNeighbors() {
        long segment = JniShapeType.segmentNew(0, -1, 0, 1, 0, .3f);
        JniShapeType.segmenSetNeighbors(segment, -2, .5f, 2, .5f);
        JniShapeTest.free(segment);
    }

    @Test
    public void testPolyAlloc() {
        long poly = JniShapeType.polyAlloc();
        JniShapeTest.free(poly);
    }

    @Test
    public void testPolyInit() {
        long poly = JniShapeType.polyAlloc();
        float [] vertices = new float[] {
            0, .5f,
            -.5f, 0,
            .5f, 0
        };

        JniShapeType.polyInit(poly, 0, 3, vertices, 1f, -1f, 3.1416f, .1f);
        assertEquals(0, JniShapeTest.getBody(poly));
        assertEquals(3, JniShapeType.polyGetCount(poly));
        assertEquals(.1f, JniShapeType.polyGetRadius(poly), .001f);
        JniShapeType.polyGetVert(poly, 0, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(0.5f,-1f), 001f);
        JniShapeType.polyGetVert(poly, 1, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1f,-1.5f), 001f);
        JniShapeType.polyGetVert(poly, 2, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1.5f,-1), 001f);
        
        JniShapeTest.free(poly);
    }

    @Test
    public void testPolyInitRaw() {
        long poly = JniShapeType.polyAlloc();
        float [] vertices = new float[] {
            0, .5f,
            -.5f, 0,
            .5f, 0
        };

        JniShapeType.polyInitRaw(poly, 0, 3, vertices, .2f);
        assertEquals(0, JniShapeTest.getBody(poly));
        assertEquals(3, JniShapeType.polyGetCount(poly));
        assertEquals(.2f, JniShapeType.polyGetRadius(poly), .001f);
        JniShapeType.polyGetVert(poly, 0, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(0f, .5f), 001f);
        JniShapeType.polyGetVert(poly, 1, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-.5f, 0f), 001f);
        JniShapeType.polyGetVert(poly, 2, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(.5f, 0f), 001f);

        JniShapeTest.free(poly);
    }

    @Test
    public void testNewPoly() {
        float [] vertices = new float[] {
            1f, 1.5f,
            -1.5f, -1,
            1.5f, -1
        };

        long poly = JniShapeType.newPoly( 0, 3, vertices, 10f, -15f, 3.1416f, .1f);
        assertEquals(0, JniShapeTest.getBody(poly));
        assertEquals(3, JniShapeType.polyGetCount(poly));
        assertEquals(.1f, JniShapeType.polyGetRadius(poly), .001f);
        JniShapeType.polyGetVert(poly, 0, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(8.5f, -14), 001f);
        JniShapeType.polyGetVert(poly, 1, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(9f, -16.5f), 001f);
        JniShapeType.polyGetVert(poly, 2, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(11.5f, -14f), 001f);
        
        JniShapeTest.free(poly);
    }

    @Test
    public void testNewPolyRaw() {
        long poly = JniShapeType.polyAlloc();
        float [] vertices = new float[] {
            2f, 2.5f,
            -2.5f, -2f,
            2.5f, -2f
        };

        JniShapeType.polyInitRaw(poly, 0, 3, vertices, .2f);
        assertEquals(0, JniShapeTest.getBody(poly));
        assertEquals(3, JniShapeType.polyGetCount(poly));
        assertEquals(.2f, JniShapeType.polyGetRadius(poly), .001f);
        JniShapeType.polyGetVert(poly, 0, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2f, 2.5f), 001f);
        JniShapeType.polyGetVert(poly, 1, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-2.5f, -2f), 001f);
        JniShapeType.polyGetVert(poly, 2, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.5f, -2f), 001f);

        JniShapeTest.free(poly);
    }

    @Test
    public void testBoxInit() {
        long poly = JniShapeType.polyAlloc();
        JniShapeType.boxInit(poly, 0, 4f, 2f, .5f);

        assertEquals(0, JniShapeTest.getBody(poly));
        assertEquals(4, JniShapeType.polyGetCount(poly));
        assertEquals(.5f, JniShapeType.polyGetRadius(poly), .001f);
        JniShapeType.polyGetVert(poly, 0, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.0f, -1.0f), 001f);
        JniShapeType.polyGetVert(poly, 1, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.0f, 1.0f), 001f);
        JniShapeType.polyGetVert(poly, 2, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-2.0f , 1.0f), 001f);
        JniShapeType.polyGetVert(poly, 3, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-2.0f, -1.0f), 001f);

        JniShapeTest.free(poly);
    }

    @Test
    public void testBoxInit2() {
        long poly = JniShapeType.polyAlloc();
        JniShapeType.boxInit2(poly, 0, -1f, -2f, 1f, 2f, .5f);

        assertEquals(0, JniShapeTest.getBody(poly));
        assertEquals(4, JniShapeType.polyGetCount(poly));
        assertEquals(.5f, JniShapeType.polyGetRadius(poly), .001f);
        JniShapeType.polyGetVert(poly, 0, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1.0f, -2.0f), 001f);
        JniShapeType.polyGetVert(poly, 1, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1.0f, 2.0f), 001f);
        JniShapeType.polyGetVert(poly, 2, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-1.0f , 2.0f), 001f);
        JniShapeType.polyGetVert(poly, 3, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-1.0f, -2.0f), 001f);

        JniShapeTest.free(poly);
    }

    @Test
    public void testBoxInit3() {
        long poly = JniShapeType.polyAlloc();
        JniShapeType.boxInit3(poly, 0, 2f, 1f, 6, 4, 3.1416f*.5f, .4f);

        assertEquals(0, JniShapeTest.getBody(poly));
        assertEquals(4, JniShapeType.polyGetCount(poly));
        assertEquals(.4f, JniShapeType.polyGetRadius(poly), .001f);
        JniShapeType.polyGetVert(poly, 0, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(5.5f, 3.0f), 001f);
        JniShapeType.polyGetVert(poly, 1, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(6.5f, 3.0f), 001f);
        JniShapeType.polyGetVert(poly, 2, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(6.5f , 5.0f), 001f);
        JniShapeType.polyGetVert(poly, 3, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(5.5f, 5.0f), 001f);

        JniShapeTest.free(poly);
    }

    @Test
    public void testBoxNew() {
        long poly = JniShapeType.boxNew(0, 4f, 2f, .5f);

        assertEquals(0, JniShapeTest.getBody(poly));
        assertEquals(4, JniShapeType.polyGetCount(poly));
        assertEquals(.5f, JniShapeType.polyGetRadius(poly), .001f);
        JniShapeType.polyGetVert(poly, 0, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.0f, -1.0f), 001f);
        JniShapeType.polyGetVert(poly, 1, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(2.0f, 1.0f), 001f);
        JniShapeType.polyGetVert(poly, 2, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-2.0f , 1.0f), 001f);
        JniShapeType.polyGetVert(poly, 3, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-2.0f, -1.0f), 001f);

        JniShapeTest.free(poly);
    }

    @Test
    public void testBoxNew2() {
        long poly = JniShapeType.boxNew2(0, -1f, -2f, 1f, 2f, .5f);

        assertEquals(0, JniShapeTest.getBody(poly));
        assertEquals(4, JniShapeType.polyGetCount(poly));
        assertEquals(.5f, JniShapeType.polyGetRadius(poly), .001f);
        JniShapeType.polyGetVert(poly, 0, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1.0f, -2.0f), 001f);
        JniShapeType.polyGetVert(poly, 1, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(1.0f, 2.0f), 001f);
        JniShapeType.polyGetVert(poly, 2, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-1.0f , 2.0f), 001f);
        JniShapeType.polyGetVert(poly, 3, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(-1.0f, -2.0f), 001f);

        JniShapeTest.free(poly);
    }

    @Test
    public void testBoxNew3() {
        long poly = JniShapeType.boxNew3(0, 2f, 1f, 6, 4, 3.1416f*.5f, .4f);

        assertEquals(0, JniShapeTest.getBody(poly));
        assertEquals(4, JniShapeType.polyGetCount(poly));
        assertEquals(.4f, JniShapeType.polyGetRadius(poly), .001f);
        JniShapeType.polyGetVert(poly, 0, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(5.5f, 3.0f), 001f);
        JniShapeType.polyGetVert(poly, 1, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(6.5f, 3.0f), 001f);
        JniShapeType.polyGetVert(poly, 2, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(6.5f , 5.0f), 001f);
        JniShapeType.polyGetVert(poly, 3, floats);
        assertEquals(0, vector2.set(floats[0], floats[1]).dst(5.5f, 5.0f), 001f);

        JniShapeTest.free(poly);
    }

}
