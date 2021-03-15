package net.josid.gdx.chipmunk;

public class JniChipmunkTest {

    public static long getDampedRotarySpringTorqueFunc() {
        return JniChipmunk.dampedRotarySpringTorqueJniFunc;
    }
    public static long getDampedSpringForceFunc() {
        return JniChipmunk.dampedSpringForceJniFunc;
    }

}
