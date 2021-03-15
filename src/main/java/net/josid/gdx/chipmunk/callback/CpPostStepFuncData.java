package net.josid.gdx.chipmunk.callback;

import com.badlogic.gdx.utils.Pool.Poolable;

import net.josid.gdx.chipmunk.CpSpace;


public class CpPostStepFuncData implements Poolable{

    private Object data;
    @SuppressWarnings("rawtypes")
    private CpPostStepFunc postStepFunc;
    
    @SuppressWarnings("unchecked")
    public void trigger(CpSpace space) {
        postStepFunc.postStep(space, data);
    }

    @SuppressWarnings("rawtypes")
    public CpPostStepFuncData set(Object data, CpPostStepFunc postStepFunc) {
        this.data = data;
        this.postStepFunc = postStepFunc;
        return this;
    }

    @Override
    public void reset() {
        data = null;
        postStepFunc = null;
    }
    
}
