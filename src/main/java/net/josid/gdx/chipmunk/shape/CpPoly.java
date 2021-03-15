package net.josid.gdx.chipmunk.shape;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.Chipmunk;
import net.josid.gdx.chipmunk.CpShape;


public final class CpPoly extends CpShape {

    CpPoly(long nativeAddress, Chipmunk chipmunk) {
        super(nativeAddress, chipmunk, Type.Polygon);
    }

    /**
     * Get the number of verts in a polygon shape.
     */ 
    public int getVerticesCount() {
        return JniShapeType.polyGetCount(nativeAddress);
    }

    /**
     * Get the ith vertex of a polygon shape.
     */ 
    public Vector2 getVertex(int index, Vector2 out_vertex) {
        float[] floats4 = getFloats4();
        JniShapeType.polyGetVert(nativeAddress, index, floats4);
        return out_vertex.set(floats4[0], floats4[1]);
    }

    /**
     * Get the radius of a polygon shape.
     */ 
    public float getRadius() {
        return JniShapeType.polyGetRadius(nativeAddress);
    }
}
