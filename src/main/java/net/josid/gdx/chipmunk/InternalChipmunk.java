package net.josid.gdx.chipmunk;

public final class InternalChipmunk {

    @Deprecated
    public static long getDampedRotarySpringTorqueFunc() {
        return JniChipmunk.dampedRotarySpringTorqueJniFunc;
    }

    @Deprecated
    public static long getDampedSpringForceFunc() {
        return JniChipmunk.dampedSpringForceJniFunc;
    }

}
