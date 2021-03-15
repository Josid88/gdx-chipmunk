package net.josid.gdx.chipmunk.callback;

import net.josid.gdx.chipmunk.CpSpace;

@FunctionalInterface
public interface CpPostStepFunc<T> {

    public void postStep(CpSpace space, T data);

}
