package net.josid.gdx.chipmunk.def;

import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.CpBody;
import net.josid.gdx.chipmunk.CpBody.Type;

public class CpBodyInstanceDef {

    public CpBody.Type _type = Type.Dynamic;
    public final Vector2 _position = new Vector2();
    public float _angle = 0;
    public final Vector2 _velocity = new Vector2();
    public float _angularVeloctiy = 0;

    public CpBodyInstanceDef reset() {
        _type = Type.Dynamic;
        _position.setZero();
        _angle = 0;
        _velocity.setZero();
        _angularVeloctiy = 0;
        return this;
    }

    public CpBodyInstanceDef type(CpBody.Type type) {
        _type = type;
        return this;
    }

    public CpBodyInstanceDef position(float x, float y) {
        _position.set(x, y);
        return this;
    }

    public CpBodyInstanceDef position(Vector2 position) {
        _position.set(position);
        return this;
    }

    public CpBodyInstanceDef angle(float angle) {
        _angle = angle;
        return this;
    }

    public CpBodyInstanceDef velocity(float x, float y) {
        _velocity.set(x, y);
        return this;
    }

    public CpBodyInstanceDef velocity(Vector2 velocity) {
        _velocity.set(velocity);
        return this;
    }

    public CpBodyInstanceDef angularVeloctiy(float angularVeloctiy) {
        _angularVeloctiy = angularVeloctiy;
        return this;
    }

}
