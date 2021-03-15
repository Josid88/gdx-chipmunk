package net.josid.gdx.chipmunk;

class CpArrays {

    public final byte[] bytes4 = new byte[4];

    public final int[] ints4 = new int[4];

    public final float[] floats4 = new float[4];
    public final float[] floats8 = new float[8];
    public final float[] floats16 = new float[16];

    public final long[] longs4 = new long[4];

    public float[] getFloats4(CpSpace space) {
        return space == null ? floats4 : space.arrays.floats4;
    }

    public float[] getFloats8(CpSpace space) {
        return space == null ? floats8 : space.arrays.floats8;
    }

    public float[] getFloats16(CpSpace space) {
        return space == null ? floats16 : space.arrays.floats16;
    }

    public int[] getInts4(CpSpace space) {
        return space == null ? ints4 : space.arrays.ints4;
    }

    public long[] getlongs4(CpSpace space) {
        return space == null ? longs4 : space.arrays.longs4;
    }

}
