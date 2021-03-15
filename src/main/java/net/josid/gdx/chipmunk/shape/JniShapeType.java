package net.josid.gdx.chipmunk.shape;

//@off
/*JNI
#include <chipmunk/chipmunk.h>
#include <chipmunk/gdx/gdxChipmunkUtils.h>
//TODO: move this
static cpVect cpVectArray[32];
*/


final class JniShapeType {

    private JniShapeType() { }

    // CUSTOM FUNCTIONS

    static native void gdxCircleInit(long shape, long body, float radius, float offset_x, float offset_y,
            boolean sensor, float mass, float density, float elasticity, float friction,
            float surfaceVelocity_x, float surfaceVelocity_y, int group, int categories, int mask); /*
        cpCircleShapeInit((cpCircleShape*)shape, (cpBody*)body, radius, cpv(offset_x, offset_y));
        gdxShapeSetOnInit((cpShape*)shape, sensor, mass, density, elasticity, friction,
            surfaceVelocity_x, surfaceVelocity_y, group, categories, mask);
    */

    static native void gdxSegmentInit(long shape, long body, float a_x, float a_y, float b_x, float b_y, float radius,
            boolean isNeighbors, float prev_x, float prev_y, float next_x, float next_y,
            boolean sensor, float mass, float density, float elasticity, float friction,
            float surfaceVelocity_x, float surfaceVelocity_y, int group, int categories, int mask); /*
        cpSegmentShapeInit((cpSegmentShape*)shape, (cpBody*)body,  cpv(a_x, a_y), cpv(b_x, b_y), radius);
        if (isNeighbors)
            cpSegmentShapeSetNeighbors((cpShape*)shape, cpv(prev_x, prev_y), cpv(next_x, next_y));
        gdxShapeSetOnInit((cpShape*)shape, sensor, mass, density, elasticity, friction,
            surfaceVelocity_x, surfaceVelocity_y, group, categories, mask);
    */

    static native void gdxPolyInit(long shape, long body, int count, float[] verts, 
            float offset_x, float offset_y, float angle, float radius,
            boolean sensor, float mass, float density, float elasticity, float friction,
            float surfaceVelocity_x, float surfaceVelocity_y, int group, int categories, int mask); /*
        gdxFloatToCpvArray(count, verts, cpVectArray);
        gdxRotateTranslatePolygon(offset_x, offset_y, angle, count, cpVectArray);
        cpPolyShapeInitRaw((cpPolyShape*)shape, (cpBody*)body, count, cpVectArray, radius);
        gdxShapeSetOnInit((cpShape*)shape, sensor, mass, density, elasticity, friction,
            surfaceVelocity_x, surfaceVelocity_y, group, categories, mask);
    */

    static native void gdxBoxInit(long shape, long body, float width, float height,
            float offsetX, float offsetY, float angle, float radius,
            boolean sensor, float mass, float density, float elasticity, float friction,
            float surfaceVelocity_x, float surfaceVelocity_y, int group, int categories, int mask); /*
        gdxSetBox(width, height, cpVectArray);
        gdxRotateTranslatePolygon(offsetX, offsetY, angle, 4, cpVectArray);
        cpPolyShapeInitRaw((cpPolyShape*)shape, (cpBody*)body, 4, cpVectArray, radius);
        gdxShapeSetOnInit((cpShape*)shape, sensor, mass, density, elasticity, friction,
            surfaceVelocity_x, surfaceVelocity_y, group, categories, mask);
    */


    // CHIPMUNK FUNCTIONS

    /**
     * Allocates a circle shape
     * @return 
     * @param void
     */ 
    static native long circleAlloc(); /*
        return (jlong) cpCircleShapeAlloc();
    */

    /**
     * Initialize a circle shape.
     * @return 
     * @param circle
     * @param body
     * @param radius
     * @param offset
     */ 
    static native void circleInit(long shape, long body, float radius, float offset_x, float offset_y); /*
        cpCircleShapeInit((cpCircleShape*)shape, (cpBody*)body, radius, cpv(offset_x, offset_y));
    */

    /**
     * Allocate and initialize a circle shape.
     * @return 
     * @param body
     * @param radius
     * @param offset
     */ 
    static native long circleNew(long body, float radius, float offset_x, float offset_y); /*
        return (jlong) cpCircleShapeNew((cpBody*)body, radius, cpv(offset_x, offset_y));
    */

    /**
     * Get the offset of a circle shape.
     * @return 
     */ 
    static native void circleGetOffset(long shape, float[] out_floats2); /*
        cpVect v = cpCircleShapeGetOffset((cpShape*)shape);
        gdxCpVectToFloats( &v, out_floats2 );
    */

    /**
     * Get the radius of a circle shape.
     * @return 
     */ 
    static native float circleGetRadius(long shape); /*
        return cpCircleShapeGetRadius((cpShape*)shape);
    */

    /**
     * Allocate a segment shape.
     * @return 
     * @param void
     */ 
    static native long segmentAlloc(); /*
        return (jlong) cpSegmentShapeAlloc();
    */

    /**
     * Initialize a segment shape.
     * @return 
     * @param seg
     * @param body
     * @param a
     * @param b
     * @param radius
     */ 
    static native void sementInit(long shape, long body, float a_x, float a_y, float b_x, float b_y, float radius); /*
        cpSegmentShapeInit((cpSegmentShape*)shape, (cpBody*)body, cpv(a_x, a_y), cpv(b_x, b_y), radius);
    */

    /**
     * Allocate and initialize a segment shape.
     * @return 
     * @param body
     * @param a
     * @param b
     * @param radius
     */ 
    static native long segmentNew(long body, float a_x, float a_y, float b_x, float b_y, float radius); /*
        return (jlong) cpSegmentShapeNew((cpBody*)body, cpv(a_x, a_y), cpv(b_x, b_y), radius);
    */

    /**
     * Let Chipmunk know about the geometry of adjacent segments to avoid colliding with endcaps.
     * @param prev
     * @param next
     */ 
    static native void segmenSetNeighbors(long shape, float prev_x, float prev_y, float next_x, float next_y); /*
        return cpSegmentShapeSetNeighbors((cpShape*)shape, cpv(prev_x, prev_y), cpv(next_x, next_y));
    */

    /**
     * Get the first endpoint of a segment shape.
     * @return 
     */ 
    static native void segmentGetA(long shape, float[] out_floats2); /*
        cpVect v = cpSegmentShapeGetA((cpShape*)shape);
        gdxCpVectToFloats( &v, out_floats2 );
    */

    /**
     * Get the second endpoint of a segment shape.
     * @return 
     */ 
    static native void segmentGetB(long shape, float[] out_floats2); /*
        cpVect v = cpSegmentShapeGetB((cpShape*)shape);
        gdxCpVectToFloats( &v , out_floats2 );
    */

    /**
     * Get the normal of a segment shape.
     * @return 
     */ 
    static native void segmentGetNormal(long shape, float[] out_floats2); /*
        cpVect v = cpSegmentShapeGetNormal((cpShape*)shape);
        gdxCpVectToFloats( &v, out_floats2 );
    */

    /**
     * Get the first endpoint of a segment shape.
     * @return 
     */ 
    static native float segmentGetRadius(long shape); /*
        return cpSegmentShapeGetRadius((cpShape*)shape);
    */

    /**
     * Allocate a polygon shape.
     * @return 
     * @param void
     */ 
    static native long polyAlloc(); /*
        return (jlong) cpPolyShapeAlloc();
    */

    /**
     * Initialize a polygon shape with rounded corners.
     * A convex hull will be created from the vertexes.
     * @return 
     * @param body
     * @param count
     * @param verts
     * @param transform
     * @param radius
     */ 
    static native void polyInit(long poly, long body, int count, float[] verts,
            float offset_x, float offset_y, float angle, float radius); /*
        gdxFloatToCpvArray(count, verts, cpVectArray);
        cpPolyShapeInit((cpPolyShape*)poly, (cpBody*)body, count, cpVectArray,
            gdxCpTransform(offset_x, offset_y, angle), radius);
    */

    /**
     * Initialize a polygon shape with rounded corners.
     * The vertexes must be convex with a counter-clockwise winding.
     * @return 
     * @param body
     * @param count
     * @param verts
     * @param radius
     */ 
    static native void polyInitRaw(long poly, long body, int count, float[] verts, float radius); /*
        gdxFloatToCpvArray(count, verts, cpVectArray);
        cpPolyShapeInitRaw((cpPolyShape*)poly, (cpBody*)body, count, cpVectArray, radius);
    */

    /**
     * Allocate and initialize a polygon shape with rounded corners.
     * A convex hull will be created from the vertexes.
     * @return 
     * @param body
     * @param count
     * @param verts
     * @param transform
     * @param radius
     */ 
    static native long newPoly(long body, int count, float[] verts, 
            float offset_x, float offset_y, float angle, float radius); /*
        gdxFloatToCpvArray(count, verts, cpVectArray);
        return (jlong) cpPolyShapeNew((cpBody*)body, count, cpVectArray,
            gdxCpTransform(offset_x, offset_y, angle), radius);
    */

    /**
     * Allocate and initialize a polygon shape with rounded corners.
     * The vertexes must be convex with a counter-clockwise winding.
     * @return 
     * @param body
     * @param count
     * @param verts
     * @param radius
     */ 
    static native long newPolyRaw(long body, int count, float[] verts, float radius); /*
        gdxFloatToCpvArray(count, verts, cpVectArray);
        return (jlong) cpPolyShapeNewRaw((cpBody*)body, count, cpVectArray, radius);
    */

    /**
     * Initialize a box shaped polygon shape with rounded corners.
     * @return 
     * @param body
     * @param width
     * @param height
     * @param radius
     */ 
    static native void boxInit(long poly, long body, float width, float height, float radius); /*
        cpBoxShapeInit((cpPolyShape*)poly, (cpBody*)body, width, height, radius);
    */

    /**
     * Initialize an offset box shaped polygon shape with rounded corners.
     * @return 
     * @param body
     * @param box
     * @param radius
     */ 
    static native long boxInit2(long poly, long body, 
            float left, float bottom, float right, float top, float radius); /*
        return (jlong) cpBoxShapeInit2((cpPolyShape*)poly, (cpBody*)body,
            cpBBNew(left, bottom, right, top), radius);
    */

    /**
     * Initialize a transformed box with rounded corners.
     * @return 
     * @param body
     * @param width
     * @param height
     * @param offset
     * @param angle
     * @param radius
     */
    static native void boxInit3(long poly, long body, float width, float height,
            float offsetX, float offsetY, float angle, float radius); /*
        gdxSetBox(width, height, cpVectArray);
        gdxRotateTranslatePolygon(offsetX, offsetY, angle, 4, cpVectArray);
        cpPolyShapeInitRaw((cpPolyShape*)poly, (cpBody*)body, 4, cpVectArray, radius);
    */

    /**
     * Allocate and initialize a box shaped polygon shape.
     * @return 
     * @param body
     * @param width
     * @param height
     * @param radius
     */ 
    static native long boxNew(long body, float width, float height, float radius); /*
        return (jlong) cpBoxShapeNew((cpBody*)body, width, height, radius);
    */

    /**
     * Allocate and initialize an offset box shaped polygon shape.
     * @return 
     * @param body
     * @param box
     * @param radius
     */ 
    static native long boxNew2(long body, 
            float left, float bottom, float right, float top, float radius); /*
        return (jlong) cpBoxShapeNew2((cpBody*)body,
            cpBBNew(left, bottom, right, top), radius);
    */

    /**
     * Allocate and initialize a transformed box with rounded corners.
     * @return 
     * @param body
     * @param width
     * @param height
     * @param offset
     * @param angle
     * @param radius
     */
    static native long boxNew3(long body, float width, float height,
            float offsetX, float offsetY, float angle, float radius); /*
        gdxSetBox(width, height, cpVectArray);
        gdxRotateTranslatePolygon(offsetX, offsetY, angle, 4, cpVectArray);
        return (jlong) cpPolyShapeNewRaw((cpBody*)body, 4, cpVectArray, radius);
    */

    /**
     * Get the number of verts in a polygon shape.
     * @return 
     * @param shape
     */ 
    static native int polyGetCount(long shape); /*
        return cpPolyShapeGetCount((cpShape*)shape);
    */

    /**
     * Get the ith vertex of a polygon shape.
     * @return 
     * @param shape
     * @param index
     */ 
    static native void polyGetVert(long shape, int index, float[] out_floats); /*
        cpVect v = cpPolyShapeGetVert((cpShape*)shape, index);
        gdxCpVectToFloats(&v, out_floats);
    */

    /**
     * Get the radius of a polygon shape.
     * @return 
     * @param shape
     */ 
    static native float polyGetRadius(long shape); /*
        return cpPolyShapeGetRadius((cpShape*)shape);
    */
}
