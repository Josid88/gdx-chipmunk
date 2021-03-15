package net.josid.gdx.chipmunk.def;

public class CpSpaceDef {

    public int _iterations = 10;
    public float _gravityX = 0;
    public float _gravityY = 0;
    public OptionalFloat _damping = new OptionalFloat(false, 1.0f);
    public float _idleSpeedThreshold = 0;
    public OptionalFloat _sleepTimeThreshold = new OptionalFloat(false, Float.POSITIVE_INFINITY);
    public OptionalFloat _collisionSlop = new OptionalFloat(false, .1f) {{value = .1f;}};
    public OptionalFloat _collisionBias = new OptionalFloat(false, (float) Math.pow(1.0 - 0.1, 60.0));
    public long _collisionPersistence = 3;

    /**
     * Number of iterations to use in the impulse solver to solve contacts and other constraints.
     */
    public CpSpaceDef iterations(int iterations) {
        this._iterations = iterations;
        return this;
    }

    /**
     * Gravity to pass to rigid bodies when integrating velocity.
     */
    public CpSpaceDef gravity(float x, float y) {
        this._gravityX = x;
        this._gravityY = y;
        return this;
    }

    /**
     * Damping rate expressed as the fraction of velocity bodies retain each second.
     * A value of 0.9 would mean that each body's velocity will drop 10% per second.
     * The default value is 1.0, meaning no damping is applied.
     * @note This damping value is different than those of cpDampedSpring and cpDampedRotarySpring.
     */
    public CpSpaceDef damping(float damping) {
        this._damping.set(damping);
        return this;
    }

    /**
     * Speed threshold for a body to be considered idle.
     * The default value of 0 means to let the space guess a good threshold based on gravity.
     */
    public CpSpaceDef idleSpeedThreshold(float idleSpeedThreshold) {
        this._idleSpeedThreshold = idleSpeedThreshold;
        return this;
    }

    /**
     * Time a group of bodies must remain idle in order to fall asleep.
     * Enabling sleeping also implicitly enables the the contact graph.
     * The default value of INFINITY disables the sleeping algorithm.
     */
    public CpSpaceDef sleepTimeThreshold(float sleepTimeThreshold) {
        this._sleepTimeThreshold.set(sleepTimeThreshold);
        return this;
    }

    /**
     * Amount of encouraged penetration between colliding shapes.
     * Used to reduce oscillating contacts and keep the collision cache warm.
     * Defaults to 0.1.
     * If you have poor simulation quality,increase this number as much as possible without allowing visible amounts of overlap.
     * @return 
     */
    public CpSpaceDef collisionSlop(float collisionSlop) {
        this._collisionSlop.set(collisionSlop);
        return this;
    }

    /**
     * Determines how fast overlapping shapes are pushed apart.
     * Expressed as a fraction of the error remaining after each second.
     * Defaults to pow(1.0 - 0.1, 60.0) meaning that Chipmunk fixes 10% of overlap each frame at 60Hz.
     * @return 
     */
    public CpSpaceDef collisionBias(float collisionBias) {
        this._collisionBias.set(collisionBias);
        return this;
    }

    /**
     * Number of frames that contact information should persist.
     * Defaults to 3.
     * There is probably never a reason to change this value.
     * @param collisionPersistence
     */
    public CpSpaceDef collisionPersistence(long collisionPersistence) {
        this._collisionPersistence = collisionPersistence;
        return this;
    }

}
