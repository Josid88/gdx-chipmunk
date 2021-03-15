package net.josid.gdx.chipmunk.def.constraint;

import net.josid.gdx.chipmunk.CpConstraint.Type;
import net.josid.gdx.chipmunk.def.CpConstraintDef;


public class CpDampedRotarySpringDef extends CpConstraintDef {

    public float _restAngle;
    public float _stiffness;
    public float _damping;

    public CpDampedRotarySpringDef init(float restAngle, float stiffness, float damping) {
        this._restAngle = restAngle;
        this._stiffness = stiffness;
        this._damping = damping;
        return this;
    }

    @Override
    public Type getType() {
        return Type.DampedRotarySpring;
    }

}
