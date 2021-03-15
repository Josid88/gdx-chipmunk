package net.josid.gdx.chipmunk.query;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.CpShape;

public class CpSegmentQueryInfo {
    
    /**
     * True if collision occured.
     */
    public boolean collides;

    /**
     * The shape that was hit, or NULL if no collision occured.
     */
    public CpShape shape;

    /**
     * The point of impact.
     */
    public final Vector2 point = new Vector2();

    /**
     * The normal of the surface hit.
     */
    public final Vector2 normal = new Vector2();

    /**
     * The normalized distance along the query segment in the range [0, 1].
     */
    public float alpha;


    public CpSegmentQueryInfo set(CpShape shape, float[] floats5) {
        this.collides = true;
        this.shape = shape;
        this.point.x = floats5[0];
        this.point.y = floats5[1];
        this.normal.x = floats5[2];
        this.normal.y = floats5[3];
        this.alpha = floats5[4];
        return this;
    }


    public CpSegmentQueryInfo set(boolean collides, CpShape shape, float[] floats5) {
        this.collides = collides;
        return set(collides ? shape : null,  floats5);
    }
    
}
