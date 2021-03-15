package net.josid.gdx.chipmunk.def.constraint;

import net.josid.gdx.chipmunk.CpConstraint.Type;
import net.josid.gdx.chipmunk.def.CpConstraintDef;


public class CpRatchetJointDef extends CpConstraintDef {

    public float _phase;
    public float _ratchet;

    public CpRatchetJointDef init(float phase, float ratchet) {
        this._phase = phase;
        this._ratchet = ratchet;
        return this;
    }

    @Override
    public Type getType() {
        return Type.RatchetJoint;
    }    
}
