package net.josid.gdx.chipmunk;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import net.josid.gdx.chipmunk.util.CpDebuggerData;
import net.josid.gdx.chipmunk.util.PolygonHelper;

//@off
/*JNI
#include <chipmunk/gdx/gdxDebugger.h>

static jclass thisClass = 0;
static jmethodID renderSpaceCallbackID = 0;
*/


public final class CpSpaceDebugger {

    private final ShapeRenderer renderer = new ShapeRenderer();
    private byte[] bytes = new byte[128];
    private float[] floats = new float[512];
    private final PolygonHelper polygonHelper = new PolygonHelper();
    private Vector2 vector2_1 = new Vector2();
    private Vector2 vector2_2 = new Vector2();
    private Vector2 vector2_3 = new Vector2();
    private final CpDebuggerData data = new CpDebuggerData();

    private CpBody.Type bodyType;
    private boolean bodyIsSleeping;
    private Vector2 bodyPosition = new Vector2();
    private float bodyAngle;
    private int byteIndex;
    private int floatIndex;
    private final float[] polygon = new float[64];
    private Color shapeColor;


    public CpSpaceDebugger() {
        jniInit();
    }


    private native void jniInit(); /*
        if (0 == thisClass) {
            thisClass = env->GetObjectClass(object);
            renderSpaceCallbackID = env->GetMethodID(thisClass, "renderSpaceCallback", "()V");
        }
    */

    public void render(CpSpace space, Camera camera) {
        renderer.setProjectionMatrix( camera.combined );
        renderer.begin(ShapeType.Line);
        renderSpace(space.nativeAddress, bytes, bytes.length, floats, floats.length);
        renderSpaceCallback();
        renderer.end();
    }

    final void renderSpaceCallback() {
        byteIndex = 0;
        floatIndex = 0;
        int bodies = nextByte();
        for (int i = 0; i < bodies; i++) {            
            renderBody();
        }
    }

    private void renderBody() {
        bodyType = CpBody.Type.valueOf(nextByte());
        bodyIsSleeping = nextByte() > 0 ? true : false;
        int shapes = nextByte();
        bodyPosition.set(nextFloat(), nextFloat());
        bodyAngle = nextFloat();
        for (int i = 0; i < shapes; i++) {
            renderShape();
        }
    }

    private void renderShape() {
        CpShape.Type shapeType = CpShape.Type.valueOf(nextByte());
        boolean isSensor = nextByte() > 0 ? true : false;
        Color color = data.getShapeColor(bodyType, bodyIsSleeping, shapeType, isSensor);
        if (shapeColor != color) {
            shapeColor = color;
            renderer.setColor(shapeColor);
        }
        switch (shapeType) {
            case Circle: renderCircle(); break;
            case Segment: renderSegment(); break;
            case Polygon: renderPolygon(); break;
        }
    }

    private void renderCircle() {
        float circleX = nextFloat();
        float circleY = nextFloat();
        float circleRadius = nextFloat();
        Vector2 direction = vector2_1.set(circleRadius, 0).setAngleRad(bodyAngle).add(circleX, circleY);

        renderer.line(circleX, circleY, direction.x, direction.y);
        renderCircleFraction(circleX, circleY,  direction.x, direction.y,
                MathUtils.PI2, data.circleSides);
    }

    private float nextFloat() {
        return floats[floatIndex++];
    }

    private byte nextByte() {
        return bytes[byteIndex++];
    }

    private void renderSegment() {
        float aX = nextFloat();
        float aY = nextFloat();
        float bX = nextFloat();
        float bY = nextFloat();
        float segmentRadius = nextFloat();
        if( segmentRadius == 0 ) {
            renderer.line(aX, aY, bX, bY);
                
        } else {
            float lineAngle = MathUtils.atan2(bY - aY, bX - aX);
            Vector2 lineRadius = vector2_3.set(0, segmentRadius).rotateRad(lineAngle);

            renderer.line( lineRadius.x + aX, lineRadius.y + aY, lineRadius.x + bX, lineRadius.y + bY );
            renderCircleFraction( aX, aY, lineRadius.x + aX, lineRadius.y + aY, MathUtils.PI, data.segmentSides);
            lineRadius.scl(-1);
            renderer.line( lineRadius.x + aX, lineRadius.y + aY, lineRadius.x + bX, lineRadius.y + bY );
            renderCircleFraction( bX, bY, lineRadius.x + bX, lineRadius.y + bY, MathUtils.PI, data.segmentSides);
        }
    }

    private void renderPolygon() {
        int vertices = nextByte();
        float polygonRadius = nextFloat();
        int index = 0;
        for (int i = 0; i < vertices; i++) {
            index = i*2;
            polygon[index] = nextFloat();
            polygon[index+1] = nextFloat();
        }
        polygonHelper.addRadius(polygon, vertices, polygonRadius);
        index = vertices*2;
        float aX = polygon[index-2];
        float aY = polygon[index-1];
        for (int i = 0; i < vertices; i++) {
            index = i*2;
            float bX = polygon[index];
            float bY = polygon[index+1];
            renderer.line(aX, aY, bX, bY);
            aX = bX;
            aY = bY;
        }
    }

    private void renderCircleFraction(float centerX, float centerY, float startX, float startY, float fractionRad, int segments) {
        Vector2 pos1 = vector2_1.set(startX - centerX, startY - centerY);

        float angle = fractionRad / segments;
        for (int i = 0; i < segments; i++) {
            Vector2 pos2 = vector2_2.set(pos1).rotateRad(angle);
            renderer.line( pos1.x + centerX, pos1.y + centerY, pos2.x + centerX, pos2.y + centerY );
            pos1.set(pos2);
        }
    }

/*JNI
    static void registerShapeRender(cpBody* body, cpShape* shape, void* data) {
        RenderSpaceContext* context = (RenderSpaceContext*)data;
        if (!registerShape(shape, context)) {
            JNIEnv* env = (JNIEnv*)context->env;
            env->CallVoidMethod((jobject)context->object, renderSpaceCallbackID);
            clearShapes(context);
            registerShape(shape, context);
        }
    }

    static void registerBodyRender(cpBody* body, void* data) {
        RenderSpaceContext* context = (RenderSpaceContext*)data;
        if (!registerBody(body, context)) {
            JNIEnv* env = (JNIEnv*)context->env;
            env->CallVoidMethod((jobject)context->object, renderSpaceCallbackID);
            clearBodies(context);
            registerBody(body, context);
        }
        cpBodyEachShape((cpBody*)body, (cpBodyShapeIteratorFunc)registerShapeRender, data);
    }
*/
    private native void renderSpace(long space, byte[] bytes, int bytesSize, float[] floats, int floatsSize); /*
        RenderSpaceContext context;
        initRenderSpaceContext(&context, (cpSpace*)space, (void*)env, (uintptr_t)object,
            bytes, bytesSize, floats, floatsSize);
        cpSpaceEachBody((cpSpace*)space, (cpSpaceBodyIteratorFunc)registerBodyRender, (void*)&context);
    */

}
