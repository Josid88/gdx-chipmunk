package net.josid.gdx.chipmunk.def.constraint;

import net.josid.gdx.chipmunk.CpConstraint.Type;
import net.josid.gdx.chipmunk.def.CpConstraintDef;


public class CpGearJointDef extends CpConstraintDef {

    public float _phase = 0;
    public float _ratio = 0;

    public CpGearJointDef init(float phase, float ratio) {
        this._phase = phase;
        this._ratio = ratio;
        return this;
    }

    @Override
    public Type getType() {
        return Type.GearJoint;
    }

}
