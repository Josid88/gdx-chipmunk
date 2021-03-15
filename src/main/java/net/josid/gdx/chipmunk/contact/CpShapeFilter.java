package net.josid.gdx.chipmunk.contact;


public class CpShapeFilter {

    /**
     * Two objects with the same non-zero group value do not collide.
     * This is generally used to group objects in a composite object together to disable self collisions.
     */
    public int group;

    /**
     * A bitmask of user definable categories that this object belongs to.
     * The category/mask combinations of both objects in a collision must agree for a collision to occur.
     */
    public int categories;

    /**
     * A bitmask of user definable category types that this object object collides with.
     * The category/mask combinations of both objects in a collision must agree for a collision to occur.
     */
    public int mask;

    public CpShapeFilter set(int[] ints) {
        group = ints[0];
        categories = ints[1];
        mask = ints[2];
        return this;
    }

}
