package net.josid.gdx.chipmunk.query;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.CpShape;

public class CpPointQueryInfo {

    public CpShape shape;

    /**
     * The closest point on the shape's surface. (in world space coordinates)
     */
    public final Vector2 point = new Vector2();

    /**
     * The distance to the point. The distance is negative if the point is inside the shape. 
     */
    public float distance;

    /**
     * The gradient of the signed distance function.
     * The value should be similar to info.p/info.d, but accurate even for very small values of info.d.
     */
    public final Vector2 gradient = new Vector2();


    public CpPointQueryInfo set(CpShape shape, float[] data) {
        this.shape = shape;
        this.point.set(data[0], data[1]);
        this.distance = data[2];
        this.gradient.set(data[3], data[4]);
        return this;
    }

}
