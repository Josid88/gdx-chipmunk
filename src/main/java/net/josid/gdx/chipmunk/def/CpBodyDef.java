package net.josid.gdx.chipmunk.def;

import com.badlogic.gdx.utils.Array;

import net.josid.gdx.chipmunk.def.shape.CpCircleDef;
import net.josid.gdx.chipmunk.def.shape.CpPolyBoxDef;
import net.josid.gdx.chipmunk.def.shape.CpPolyDef;
import net.josid.gdx.chipmunk.def.shape.CpSegmentDef;

public class CpBodyDef {

    public final Array<CpShapeDef> _shapes = new Array<>();

    public CpBodyDef add(CpShapeDef shape) {
        _shapes.add(shape);
        return this;
    }

    public CpCircleDef circle() {
        return addShape(new CpCircleDef());
    }

    public CpSegmentDef segment() {
        return addShape(new CpSegmentDef());
    }

    public CpPolyBoxDef box() {
        return addShape(new CpPolyBoxDef());
    }

    public CpPolyDef poly() {
        return addShape(new CpPolyDef());
    }

    private <T extends CpShapeDef> T addShape(T shapeDef) {
        shapeDef.setBody(this);
        _shapes.add(shapeDef);
        return shapeDef;
    }

}
