package net.josid.gdx.chipmunk.def.constraint;

import net.josid.gdx.chipmunk.CpConstraint.Type;
import net.josid.gdx.chipmunk.def.CpConstraintDef;


public class CpRotaryLimitJointDef extends CpConstraintDef {

    public float _min;
    public float _max;

    public CpRotaryLimitJointDef init(float min, float max) {
        this._min = min;
        this._max = max;
        return this;
    }

    @Override
    public Type getType() {
        return Type.RotaryLimitJoint;
    }

}
