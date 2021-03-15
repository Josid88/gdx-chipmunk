package net.josid.gdx.chipmunk;

import com.badlogic.gdx.utils.Disposable;


public final class Chipmunk implements Disposable {

    final ChipmunkRegistry registry;
    final ChipmunkAllocator allocator;
    final ChipmunkPools pools;
    final CpArrays arrays;
    final JniChipmunk jniChipmunk;
    public final ChipmunkFactory factory;

    public Chipmunk() {
        registry = new ChipmunkRegistry();
        jniChipmunk = new JniChipmunk(registry);
        allocator = new ChipmunkAllocator(this, registry, jniChipmunk);
        pools = new ChipmunkPools(this, allocator);
        arrays = new CpArrays();
        factory = new ChipmunkFactory(allocator, pools);
    }

    @Override
    public void dispose() {
        allocator.dispose();
        jniChipmunk.dispose();
    }

    static {
        JniChipmunk.init();
    }

}
