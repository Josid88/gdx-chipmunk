package net.josid.gdx.chipmunk;

import java.util.function.Consumer;

import com.badlogic.gdx.utils.LongMap;
import com.badlogic.gdx.utils.LongMap.Entry;

class ChipmunkRegistry {

    private final LongMap<CpSpace> spaces;
    private final LongMap<CpBody> bodies;
    private final LongMap<CpShape> shapes;
    private final LongMap<CpConstraint> constraints;


    // TODO: map size configuration builder
    ChipmunkRegistry() {
        spaces = new LongMap<CpSpace>();
        bodies = new LongMap<CpBody>();
        shapes = new LongMap<CpShape>();
        constraints = new LongMap<CpConstraint>();
    }


    void addSpace(long id, CpSpace space) {
        spaces.put(id, space);
    }

    CpSpace getSpace(long id) {
        return spaces.get(id);
    }

    void forEachSpace(Consumer<CpSpace> each) {
        for (Entry<CpSpace> entry : spaces) {
            each.accept(entry.value);
        }
    }


    void addBody(long id, CpBody body) {
        bodies.put(id, body);
    }

    CpBody getBody(long id) {
        return bodies.get(id);
    }

    void forEachBody(Consumer<CpBody> each) {
        for (Entry<CpBody> entry : bodies) {
            each.accept(entry.value);
        }
    }


    void addShape(long id, CpShape shape) {
        shapes.put(id, shape);
    }

    CpShape getShape(long id) {
        return shapes.get(id);
    }

    void forEachShape(Consumer<CpShape> each) {
        for (Entry<CpShape> entry : shapes) {
            each.accept(entry.value);
        }
    }


    void addConstraint(long id, CpConstraint constraint) {
        constraints.put(id, constraint);
    }

    CpConstraint getConstraint(long id) {
        return constraints.get(id);
    }

    void forEachConstraint(Consumer<CpConstraint> each) {
        for (Entry<CpConstraint> entry : constraints) {
            each.accept(entry.value);
        }
    }


    void clear() {
        spaces.clear();
        bodies.clear();
        shapes.clear();
        constraints.clear();
    }

}
