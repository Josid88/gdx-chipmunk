package net.josid.gdx.chipmunk.shape;

import net.josid.gdx.chipmunk.Chipmunk;
import net.josid.gdx.chipmunk.def.shape.CpCircleDef;
import net.josid.gdx.chipmunk.def.shape.CpPolyBoxDef;
import net.josid.gdx.chipmunk.def.shape.CpPolyDef;
import net.josid.gdx.chipmunk.def.shape.CpSegmentDef;

public class InternalShapeType {

    @Deprecated
    public static CpCircle newCircle(Chipmunk chipmunk) {
        return new CpCircle(JniShapeType.circleAlloc(), chipmunk);
    }

    @Deprecated
    public static void circleInit(long shape, long body, CpCircleDef def) {
        JniShapeType.gdxCircleInit(shape, body, def._radius, def._offset.x, def._offset.y,
                def._sensor, def._mass.value, def._density.value, def._elastisity, def._friction, 
                def._surfaceVelocity.x, def._surfaceVelocity.y, 
                null == def._filterDef ? 0 : def._filterDef._group,
                null == def._filterDef ? ~0 : def._filterDef._categories,
                null == def._filterDef ? ~0 : def._filterDef._mask);
    }


    @Deprecated
    public static CpPoly newPoly(Chipmunk chipmunk) {
        return new CpPoly(JniShapeType.polyAlloc(), chipmunk);
    }

    @Deprecated
    public static void polyInit(long shape, long body, CpPolyDef def) {
        JniShapeType.gdxPolyInit(shape, body, def._vertices.length/2, def._vertices, 
                def._offset.x, def._offset.y, def._angle, def._radius,
                def._sensor, def._mass.value, def._density.value, def._elastisity, def._friction, 
                def._surfaceVelocity.x, def._surfaceVelocity.y, 
                null == def._filterDef ? 0 : def._filterDef._group,
                null == def._filterDef ? ~0 : def._filterDef._categories,
                null == def._filterDef ? ~0 : def._filterDef._mask);
    }

    @Deprecated
    public static void boxInit(long shape, long body, CpPolyBoxDef def) {
        JniShapeType.gdxBoxInit(shape, body, def._width, def._height, def._offset.x, def._offset.y, def._angle, def._radius,
                def._sensor, def._mass.value, def._density.value, def._elastisity, def._friction, 
                def._surfaceVelocity.x, def._surfaceVelocity.y, 
                null == def._filterDef ? 0 : def._filterDef._group,
                null == def._filterDef ? ~0 : def._filterDef._categories,
                null == def._filterDef ? ~0 : def._filterDef._mask);
    }


    @Deprecated
    public static CpSegment newSegment(Chipmunk chipmunk) {
        return new CpSegment(JniShapeType.segmentAlloc(), chipmunk);
    }

    @Deprecated
    public static void segmentInit(long shape, long body, CpSegmentDef def) {
        JniShapeType.gdxSegmentInit(shape, body, def._a.x, def._a.y, def._b.x, def._b.y, def._radius,
                def.isNeighbors, def.neighborA.x, def.neighborA.y, def.neighborB.x, def.neighborB.y,
                def._sensor, def._mass.value, def._density.value, def._elastisity, def._friction, 
                def._surfaceVelocity.x, def._surfaceVelocity.y, 
                null == def._filterDef ? 0 : def._filterDef._group,
                null == def._filterDef ? ~0 : def._filterDef._categories,
                null == def._filterDef ? ~0 : def._filterDef._mask);
    }

}
