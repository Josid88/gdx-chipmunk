package net.josid.gdx.chipmunk;

public class CpGdxTestHelper {

    public ChipmunkRegistry registry;
    public JniChipmunk jniChipmunk;
    public long gdxSpace;

    public CpGdxTestHelper() {
        registry = new ChipmunkRegistry();
        jniChipmunk = new JniChipmunk(registry);
        gdxSpace = JniSpace.gdxSpaceNew(jniChipmunk.nativeAddress);
    }

    public void dispose() {
        JniSpace.gdxSpaceFree(gdxSpace);
        jniChipmunk.dispose();
    }

    public void addSpace(long space) {
        JniSpace.setUserData(space, gdxSpace);
    }

    public CpBody newCpBody(long body) {
        CpBody cpBody = new CpBody(body, null);
        registry.addBody(body, cpBody);
        return cpBody;
    }

    public CpShape newCpShape(long shape) {
        CpShape cpShape = new CpShape(shape, null, null) {};
        registry.addShape(shape, cpShape);
        return cpShape;
    }

    public CpConstraint newCpConstraint(long constraint) {
        CpConstraint cpConstraint = new CpConstraint(constraint, null, null) {};
        registry.addConstraint(constraint, cpConstraint);
        return cpConstraint;
    }

    public CpSpace newCpSpace(long space) {
        CpSpace cpSpace = new CpSpace(space, gdxSpace, null);
        registry.addSpace(space, cpSpace);
        return cpSpace;
    }

    public <T extends CpConstraint> T addConstraint(T cpConstraint) {
        registry.addConstraint(cpConstraint.nativeAddress, cpConstraint);
        return cpConstraint;
    }

}
