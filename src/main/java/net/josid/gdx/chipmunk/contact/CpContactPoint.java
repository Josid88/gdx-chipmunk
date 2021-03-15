package net.josid.gdx.chipmunk.contact;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;


public class CpContactPoint implements Poolable{

    public final Vector2 pointA = new Vector2();
    public final Vector2 pointB = new Vector2();

    /**
     * Penetration distance of the two shapes. Overlapping means it will be negative.
     * This value is calculated as cpvdot(cpvsub(point2, point1), normal) and is ignored by cpArbiterSetContactPointSet().
     */
    public float distance;

    @Override
    public void reset() {
        pointA.setZero();
        pointB.setZero();
        distance = 0;
    }

}
