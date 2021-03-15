package net.josid.gdx.chipmunk.def;

import net.josid.gdx.chipmunk.CpBody;
import net.josid.gdx.chipmunk.CpConstraint;

public abstract class CpConstraintDef {

    public boolean _indexBased;
    public int _indexA;
    public int _indexB;
    public boolean _bodyBased;
    public CpBody _bodyA;
    public CpBody _bodyB;
    public OptionalFloat _maxForce = new OptionalFloat(false, Float.POSITIVE_INFINITY);
    public OptionalFloat _errorBias = new OptionalFloat(false, (float)Math.pow(1.0 - 0.1, 60.0));
    public OptionalFloat _maxBias = new OptionalFloat(false, Float.POSITIVE_INFINITY);
    public boolean _collideBodies = false;

    public abstract CpConstraint.Type getType();

    public CpConstraintDef bodies(int a, int b) {
        _indexBased = true;
        _bodyBased = false;
        _indexA = a;
        _indexB = b;
        return this;
    }

    public CpConstraintDef bodies(CpBody a, CpBody b) {
        _indexBased = false;
        _bodyBased = true;
        _bodyA = a;
        _bodyB = b;
        return this;
    }

    /**
     * Set the maximum force that this constraint is allowed to use. (defaults to INFINITY)
     */ 
    public CpConstraintDef maxForce(float maxForce) {
        _maxForce.set(maxForce);
        return this;
    }

    /**
     * Set rate at which joint error is corrected.Defaults to pow(1.0 - 0.1, 60.0) meaning that it willcorrect 10% of the error every 1/60th of a second.
     */ 
    public CpConstraintDef errorBias(float errorBias) {
        _errorBias.set(errorBias);
        return this;
    }

    /**
     * Set the maximum rate at which joint error is corrected. (defaults to INFINITY)
     */ 
    public CpConstraintDef maxBias(float maxBias) {
        _maxBias.set(maxBias);
        return this;
    }

    /**
     * Set if the two bodies connected by the constraint are allowed to collide or not. (defaults to cpFalse)
     */ 
    public CpConstraintDef collideBodies(boolean collideBodies) {
        this._collideBodies = collideBodies;
        return this;
    }

}
