package net.josid.gdx.chipmunk.callback;

import net.josid.gdx.chipmunk.CpShape;
import net.josid.gdx.chipmunk.contact.CpContactPointSet;

public interface CpSpaceShapeQueryFunc {

    public void query(CpShape shape, CpContactPointSet points);

}
