package net.josid.gdx.chipmunk.def.constraint;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.CpConstraint.Type;
import net.josid.gdx.chipmunk.def.CpConstraintDef;


public class CpSlideJointDef extends CpConstraintDef {

    public final Vector2 _anchorA = new Vector2();
    public final Vector2 _anchorB = new Vector2();
    public float _min;
    public float _max;

    public CpSlideJointDef init(Vector2 anchorA, Vector2 anchorB, float min, float max) {
        return init(anchorA.x, anchorA.y, anchorB.x, anchorB.y, min, max);
    }

    public CpSlideJointDef init(float anchorA_x, float anchorA_y, float anchorB_x, float anchorB_y,
            float min, float max) {
        this._anchorA.set(anchorA_x, anchorA_y);
        this._anchorB.set(anchorB_x, anchorB_y);
        this._min = min;
        this._max = max;
        return this;
    }

    @Override
    public Type getType() {
        return Type.SlideJoint;
    }

}
