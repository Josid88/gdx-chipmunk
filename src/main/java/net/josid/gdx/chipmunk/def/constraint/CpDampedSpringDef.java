package net.josid.gdx.chipmunk.def.constraint;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.CpConstraint.Type;
import net.josid.gdx.chipmunk.def.CpConstraintDef;


public class CpDampedSpringDef extends CpConstraintDef {

    public final Vector2 _anchorA = new Vector2();
    public final Vector2 _anchorB = new Vector2();
    public float _restLength = 0;
    public float _stiffness = 0;
    public float _damping = 0;

    public CpDampedSpringDef init(Vector2 anchorA, Vector2 anchorB, float restLength, float stiffness, float damping) {
        return init(anchorA.x, anchorA.y, anchorB.x, anchorB.y, restLength, stiffness, damping);
    }

    public CpDampedSpringDef init(float anchorA_x, float anchorA_y, float anchorB_x, float anchorB_y,
            float restLength, float stiffness, float damping) {
        this._anchorA.set(anchorA_x, anchorA_y);
        this._anchorB.set(anchorB_x, anchorB_y);
        this._restLength = restLength;
        this._stiffness = stiffness;
        this._damping = damping;
        return this;
    }

    @Override
    public Type getType() {
        return Type.DampedSpring;
    }

}
