package net.josid.gdx.chipmunk.math;

public class CpBoundingBox {

    public float left, bottom, right, top;

    public CpBoundingBox set(float[] data) {
        left = data[0];
        bottom = data[1];
        right = data[2];
        top = data[3];
        return this;
    }

    @Override
    public String toString() {
        return String.format("l:%.4f b:%.4f r:%.4f t:%.4f", left, bottom, right, top);
    }

}
