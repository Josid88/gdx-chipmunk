package net.josid.gdx.chipmunk.def.constraint;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.CpConstraint.Type;
import net.josid.gdx.chipmunk.def.CpConstraintDef;


public class CpPinJointDef extends CpConstraintDef {

    public final Vector2 _anchorA = new Vector2();
    public final Vector2 _anchorB = new Vector2();

    public CpPinJointDef init(Vector2 anchorA, Vector2 anchorB) {
        return init(anchorA.x, anchorA.y, anchorB.x, anchorB.y);
    }

    public CpPinJointDef init(float aX, float aY, float bX, float bY) {
        this._anchorA.set(aX, aY);
        this._anchorB.set(bX, bY);
        return this;
    }

    @Override
    public Type getType() {
        return Type.PinJoint;
    }

}
