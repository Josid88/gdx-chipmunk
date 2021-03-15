package net.josid.gdx.chipmunk.def.constraint;

import net.josid.gdx.chipmunk.CpConstraint.Type;
import net.josid.gdx.chipmunk.def.CpConstraintDef;


public class CpSimpleMotorDef extends CpConstraintDef {

    public float _rate;

    public CpSimpleMotorDef init(float rate) {
        this._rate = rate;
        return this;
    }

    @Override
    public Type getType() {
        return Type.SimpleMotor;
    }

}
