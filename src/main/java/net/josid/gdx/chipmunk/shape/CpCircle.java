package net.josid.gdx.chipmunk.shape;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.Chipmunk;
import net.josid.gdx.chipmunk.CpShape;


public final class CpCircle extends CpShape {


    CpCircle(long nativeAddress, Chipmunk chipmunk) {
        super(nativeAddress, chipmunk, Type.Circle);
    }


    /**
     * Get the offset of a circle shape.
     */ 
    public Vector2 getOffset(Vector2 out_offset) {
        float[] floats4 = getFloats4();
        JniShapeType.circleGetOffset(nativeAddress, floats4);
        return out_offset.set(floats4[0], floats4[1]);
    }

    /**
     * Get the radius of a circle shape.
     */ 
    public float getRadius() {
        return JniShapeType.circleGetRadius(nativeAddress);
    }

}
