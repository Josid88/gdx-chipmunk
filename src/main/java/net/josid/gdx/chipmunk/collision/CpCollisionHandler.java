package net.josid.gdx.chipmunk.collision;

public class CpCollisionHandler {

    long typeA;
    long typeB;
    public Object userData;

    public CpCollisionBeginFunc beginFunc;
    public CpCollisionPreSolveFunc preSolveFunc;
    public CpCollisionPostSolveFunc postSolveFunc;
    public CpCollisionSeparateFunc separateFunc;

    public long getTypeA() {
        return typeA;
    }
    public long getTypeB() {
        return typeB;
    }

}
