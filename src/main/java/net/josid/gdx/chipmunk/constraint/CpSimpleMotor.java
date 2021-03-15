package net.josid.gdx.chipmunk.constraint;

import net.josid.gdx.chipmunk.Chipmunk;
import net.josid.gdx.chipmunk.CpConstraint;


public class CpSimpleMotor extends CpConstraint {

    CpSimpleMotor(long nativeAddress, Chipmunk chipmunk) {
        super(nativeAddress, chipmunk, Type.SimpleMotor);
    }

    /**
     * Get the rate of the motor.
     */ 
    public float getRate() {
        return JniConstraintType.simpleMotorGetRate(nativeAddress);
    }

    /**
     * Set the rate of the motor.
     */ 
    public void setRate(float rate) {
        JniConstraintType.simpleMotorSetRate(nativeAddress, rate);
    }

}
