package net.josid.gdx.chipmunk.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class PolygonHelper {

    private final Vector2 vertex = new Vector2();
    private final Vector2 prevVertex = new Vector2();
    private final Vector2 nextVertex = new Vector2();

    private final Vector2 n1 = new Vector2();
    private final Vector2 n2 = new Vector2();
    private final Vector2 of = new Vector2();
    private final Vector2 v = new Vector2();


    public void addRadius(float[] vertices, float radius) {
        addRadius(vertices, vertices.length/2);
    }

    public void addRadius(float[] vertices, int verticesCount, float radius) {
        for (int i = 0; i < verticesCount; i++) {
            int index = i * 2;
            int prevIndex = ((i+(verticesCount - 1))%verticesCount)*2;
            int nextIndex = ((i+(verticesCount + 1))%verticesCount)*2;

            vertex.set(vertices[index], vertices[index+1]);
            prevVertex.set(vertices[prevIndex], vertices[prevIndex+1]);
            nextVertex.set(vertices[nextIndex], vertices[nextIndex+1]);

            n1.set(vertex).sub(prevVertex).rotate90(-1).nor();
            n2.set(nextVertex).sub(vertex).rotate90(-1).nor();
            of.set(n1).add(n2).scl( 1.0f / (n1.dot(n2)+1.0f) ).scl(radius);
            v.set(vertex).add(of);

            vertices[index] = v.x;
            vertices[index+1] = v.y;
        }
    }

    public float[] createBox(Vector2 dimension, Vector3 transform2d, float[] out_polygon) {
        Vector2 half = n1.set(dimension).scl(.5f);
        setBoxCoord(half.x, half.y, transform2d, 0, out_polygon);
        setBoxCoord(-half.x, half.y, transform2d, 2, out_polygon);
        setBoxCoord(-half.x, -half.y, transform2d, 4, out_polygon);
        setBoxCoord(half.x, -half.y, transform2d, 6, out_polygon);
        return out_polygon;
    }

    private void setBoxCoord(float x, float y, Vector3 transform2d, int index, float[] coordsArray) {
        n2.set(x, y).rotateRad(transform2d.z).add(transform2d.x, transform2d.y);
        coordsArray[index] = n2.x;
        coordsArray[index+1] = n2.y;
    }

    public float[] transformVectors(float[] original, Vector3 transform2d, float[] out_transformed) {
        for (int i = 0; i < out_transformed.length; i+=2) {
            Vector2 coord = n1.set(original[i], original[i+1]);
            coord.rotateRad(transform2d.z).add(transform2d.x, transform2d.y);
            out_transformed[i] = coord.x;
            out_transformed[i+1] = coord.y;
        }
        return out_transformed;
    }
}
