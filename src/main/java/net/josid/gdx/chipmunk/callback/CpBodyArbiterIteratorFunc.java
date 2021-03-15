package net.josid.gdx.chipmunk.callback;

import net.josid.gdx.chipmunk.CpBody;
import net.josid.gdx.chipmunk.CpArbiter;

@FunctionalInterface
public interface CpBodyArbiterIteratorFunc {

    public void arbiter(CpBody body, CpArbiter arbiter);

}
