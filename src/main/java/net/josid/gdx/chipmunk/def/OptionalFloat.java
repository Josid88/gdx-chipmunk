package net.josid.gdx.chipmunk.def;

public class OptionalFloat {

    public boolean isSet;
    public float value;

    public OptionalFloat() {
    }

    public OptionalFloat(float value) {
        this.isSet = true;
        this.value = value;
    }

    public OptionalFloat(boolean isSet, float value) {
        this.isSet = isSet;
        this.value = value;
    }

    public OptionalFloat set(float value) {
        this.value = value;
        isSet = true;
        return this;
    }

    public OptionalFloat unset() {
        this.value = 0;
        isSet = false;
        return this;
    }

    public void copy(OptionalFloat base) {
        this.value = base.value;
        this.isSet = base.isSet;
    }
    
}
