package net.josid.gdx.chipmunk.def.constraint;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.CpConstraint.Type;
import net.josid.gdx.chipmunk.def.CpConstraintDef;


public class CpGrooveJointDef extends CpConstraintDef {

    public final Vector2 _grooveA = new Vector2();
    public final Vector2 _grooveB = new Vector2();
    public final Vector2 _anchorB = new Vector2();

    public CpGrooveJointDef init(Vector2 grooveA, Vector2 grooveB, Vector2 anchorB) {
        return init(grooveA.x, grooveA.y, grooveB.x, grooveB.y, anchorB.x, anchorB.y);
    }

    public CpGrooveJointDef init(float grooveA_x, float grooveA_y, float grooveB_x, float grooveB_y,
            float anchorB_x,  float anchorB_y) {
        _grooveA.set(grooveA_x, grooveA_y);
        _grooveB.set(grooveB_x, grooveB_y);
        _anchorB.set(anchorB_x, anchorB_y);
        return this;
    }

    @Override
    public Type getType() {
        return Type.GrooveJoint;
    }
}
