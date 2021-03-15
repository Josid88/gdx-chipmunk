package net.josid.gdx.chipmunk.shape;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.Chipmunk;
import net.josid.gdx.chipmunk.CpShape;


public final class CpSegment extends CpShape {


    CpSegment(long nativeAddress, Chipmunk chipmunk) {
        super(nativeAddress, chipmunk, Type.Segment);
    }


    /**
     * Let Chipmunk know about the geometry of adjacent segments to avoid colliding with endcaps.
     */ 
    public void setNeighbors(float prev_x, float prev_y, float next_x, float next_y) {
        JniShapeType.segmenSetNeighbors(nativeAddress, prev_x, prev_y, next_x, next_y);
    }

    /**
     * Get the first endpoint of a segment shape. 
     */ 
    public Vector2 getA(Vector2 out_a) {
        float[] floats4 = getFloats4();
        JniShapeType.segmentGetA(nativeAddress, floats4);
        return out_a.set(floats4[0], floats4[1]);
    }

    /**
     * Get the second endpoint of a segment shape.
     */ 
    public Vector2 getB(Vector2 out_b) {
        float[] floats4 = getFloats4();
        JniShapeType.segmentGetB(nativeAddress, floats4);
        return out_b.set(floats4[0], floats4[1]);
    }

    /**
     * Get the normal of a segment shape.
     */ 
    public Vector2 getNormal(Vector2 out_normal) {
        float[] floats4 = getFloats4();
        JniShapeType.segmentGetNormal(nativeAddress, floats4);
        return out_normal.set(floats4[0], floats4[1]);
    }

    /**
     * Get the first endpoint of a segment shape.
     */ 
    public float getRadius() {
        return JniShapeType.segmentGetRadius(nativeAddress);
    }

}
