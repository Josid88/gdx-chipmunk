package net.josid.gdx.chipmunk.def.constraint;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.CpConstraint.Type;
import net.josid.gdx.chipmunk.def.CpConstraintDef;


public class CpPivotJointDef extends CpConstraintDef {

    public final Vector2 _anchorA = new Vector2();
    public final Vector2 _anchorB = new Vector2();

    public CpPivotJointDef init(Vector2 anchorA, Vector2 anchorB) {
        return init(anchorA.x, anchorA.y, anchorB.x, anchorB.y);
    }

    public CpPivotJointDef init(float anchorA_x, float anchorA_y, float anchorB_x, float anchorB_y) {
        this._anchorA.set(anchorA_x, anchorA_y);
        this._anchorB.set(anchorB_x, anchorB_y);
        return this;
    }

    @Override
    public Type getType() {
        return Type.PivotJoint;
    }

}
