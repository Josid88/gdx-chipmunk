package net.josid.gdx.chipmunk.contact;

import com.badlogic.gdx.math.Vector2;


public class CpContactPointSet {

    /**
     * The normal of the collision.
     */
    public final Vector2 normal = new Vector2();

    /**
     * The number of contact points in the set.
     */
    public int count;

    /**
     * The array of contact points.
     */
    public final CpContactPoint[] contactPoints = new CpContactPoint[] { new CpContactPoint(), new CpContactPoint() };


    public CpContactPointSet set(int count, float[] floats12) {
        this.count = count;
        normal.set(floats12[0], floats12[1]);
        for (int i = 0; i < count; i++) {
            int index = (i * 5) + 2;
            contactPoints[i].pointA.set(floats12[index], floats12[index+1]);
            contactPoints[i].pointB.set(floats12[index+2], floats12[index+3]);
            contactPoints[i].distance = floats12[index+4];
        }
        return this;
    }

    public int toFloats(float[] floats) {
        floats[0] = normal.x;
        floats[1] = normal.y;
        for (int i = 0; i < count; i++) {
            int index = (i * 5) + 2;
            floats[index] = contactPoints[i].pointA.x;
            floats[index+1] = contactPoints[i].pointA.y;
            floats[index+2] = contactPoints[i].pointB.x;
            floats[index+3] = contactPoints[i].pointB.y;
            floats[index+4] = contactPoints[i].distance;
        }
        return count;
    }

}
