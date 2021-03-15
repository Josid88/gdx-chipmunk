package net.josid.gdx.chipmunk;

import com.badlogic.gdx.jnigen.JniGenSharedLibraryLoader;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.SharedLibraryLoader;

import net.josid.gdx.chipmunk.callback.CpPostStepFuncData;
import net.josid.gdx.chipmunk.collision.CpCollisionHandler;
import net.josid.gdx.chipmunk.constraint.CpDampedRotarySpring;
import net.josid.gdx.chipmunk.constraint.CpDampedSpring;

//@off
/*JNI
#include <chipmunk/gdx/gdxStructs.h>
#include <chipmunk/gdx/gdxChipmunkUtils.h>
*/


final class JniChipmunk {

    final long nativeAddress;
    final ChipmunkRegistry registry;

    static final long bodyPositionJniFunc;
    static final long bodyVelocityJniFunc;
    static final long bodyShapeIteratorJniFunc;
    static final long bodyConstraintIteratorJniFunc;
    static final long bodyArbiterIteratorJniFunc;

    static final long constraintPreSolveJniFunc;
    static final long constraintPostSolveJniFunc;
    static final long dampedRotarySpringTorqueJniFunc;
    static final long dampedSpringForceJniFunc;

    static final long spacePostStepJniFunc;
    static final long spacePointQueryJniFunc;
    static final long spaceSegmentQueryJniFunc;
    static final long spaceBbQueryJniFunc;
    static final long spaceShapeQueryJniFunc;
    static final long spaceBodyIteratorJniFunc;
    static final long spaceShapeIteratorJniFunc;
    static final long spaceConstraintIteratorJniFunc;

    public JniChipmunk(ChipmunkRegistry registry) {
        this.registry = registry;
        nativeAddress = jniInit();
    }
    private native long jniInit(); /*
        jobject globalObj = env->NewGlobalRef(object);
        GdxChipmunk* gdxChipmunk = gdxChipmunkNew(env, (uintptr_t)globalObj);

        jclass thisClass = env->GetObjectClass(globalObj);
        gdxChipmunk->bodyPositionFuncID = (uintptr_t)env->GetMethodID(thisClass, "bodyPositionFunc", "(JF)V");
        gdxChipmunk->bodyVelocityFuncID = (uintptr_t)env->GetMethodID(thisClass, "bodyVelocityFunc", "(JFFFF)V");
        gdxChipmunk->bodyShapeIteratorFuncID = (uintptr_t)env->GetMethodID(thisClass, "bodyShapeIteratorFunc", "(JJ)V");
        gdxChipmunk->bodyConstraintIteratorFuncID = (uintptr_t)env->GetMethodID(thisClass, "bodyConstraintIteratorFunc", "(JJ)V");
        gdxChipmunk->bodyArbiterIteratorJniFuncID = (uintptr_t)env->GetMethodID(thisClass, "bodyArbiterIteratorJniFunc", "(JJ)V");

        gdxChipmunk->constraintPreSolveFuncID = (uintptr_t)env->GetMethodID(thisClass, "constraintPreSolveFunc", "(JJ)V");
        gdxChipmunk->constraintPostSolveFuncID = (uintptr_t)env->GetMethodID(thisClass, "constraintPostSolveFunc", "(JJ)V");
        gdxChipmunk->dampedRotarySpringTorqueFuncID = (uintptr_t)env->GetMethodID(thisClass, "dampedRotarySpringTorqueFunc", "(JF)F");
        gdxChipmunk->dampedSpringForceFuncID = (uintptr_t)env->GetMethodID(thisClass, "dampedSpringForceFunc", "(JF)F");

        gdxChipmunk->spacePostStepFuncID = (uintptr_t)env->GetMethodID(thisClass, "spacePostStepFunc", "(JJ)V");
        gdxChipmunk->spacePointQueryFuncID = (uintptr_t)env->GetMethodID(thisClass, "spacePointQueryFunc", "(JJFFFFF)V");
        gdxChipmunk->spaceSegmentQueryFuncID = (uintptr_t)env->GetMethodID(thisClass, "spaceSegmentQueryFunc", "(JJFFFFF)V");
        gdxChipmunk->spaceBbQueryFuncID = (uintptr_t)env->GetMethodID(thisClass, "spaceBbQueryFunc", "(JJ)V");
        gdxChipmunk->spaceShapeQueryFuncID = (uintptr_t)env->GetMethodID(thisClass, "spaceShapeQueryFunc", "(JJI)V");
        gdxChipmunk->spaceBodyIteratorFuncID = (uintptr_t)env->GetMethodID(thisClass, "spaceBodyIteratorFunc", "(JJ)V");
        gdxChipmunk->spaceShapeIteratorFuncID =  (uintptr_t)env->GetMethodID(thisClass, "spaceShapeIteratorFunc", "(JJ)V");
        gdxChipmunk->spaceConstraintIteratorFuncID =  (uintptr_t)env->GetMethodID(thisClass, "spaceConstraintIteratorFunc", "(JJ)V");

        gdxChipmunk->collisionBeginFuncID =  (uintptr_t)env->GetMethodID(thisClass, "collisionBeginFunc", "(JJJJ)Z");
        gdxChipmunk->collisionPreSolveFuncID =  (uintptr_t)env->GetMethodID(thisClass, "collisionPreSolveFunc", "(JJJJ)Z");
        gdxChipmunk->collisionPostSolveFuncID =  (uintptr_t)env->GetMethodID(thisClass, "collisionPostSolveFunc", "(JJJJ)V");
        gdxChipmunk->collisionSeparateFuncID =  (uintptr_t)env->GetMethodID(thisClass, "collisionSeparateFunc", "(JJJJ)V");

        return (jlong) gdxChipmunk;
    */

    void dispose() {
        jniDispose(nativeAddress);
    }
    private native void jniDispose(long nativeAddress); /*
        GdxChipmunk* gdxChipmunk = (GdxChipmunk*) nativeAddress;
        env->DeleteGlobalRef( (jobject) gdxChipmunk->jObj );
        free(gdxChipmunk);
    */


    // BODY CALLBACKS

    final void bodyPositionFunc(long body, float delta) {
        CpBody cpBody = registry.getBody(body);
        if (null != cpBody && null != cpBody.positionFunc)
            cpBody.positionFunc.updateFunc(cpBody, delta);
    }
/*JNI
    static void bodyPositionFunc(cpBody* body, cpFloat delta) {
        GdxChipmunk* gdxChipmunk = gdxCpBodyGetGdxChipmunk(body);
        if (0 != gdxChipmunk) {
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->bodyPositionFuncID, (jlong)body, (jfloat)delta);
        }
    }
*/

    final void bodyVelocityFunc(long body, float gravity_x, float gravity_y, float damping, float delta) {
        CpBody cpBody = registry.getBody(body);
        if (null != cpBody && null != cpBody.velocityFunc)
            cpBody.velocityFunc.update(cpBody, gravity_x, gravity_y, damping, delta);
    }
/*JNI
    static void bodyVelocityFunc(cpBody* body, cpVect gravity, cpFloat damping, cpFloat delta) {
        GdxChipmunk* gdxChipmunk = gdxCpBodyGetGdxChipmunk(body);
        if (0 != gdxChipmunk) {
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->bodyVelocityFuncID, (jlong)body,
                (jfloat)gravity.x, (jfloat)gravity.y, (jfloat)damping, (jfloat)delta);
        }
    }
*/

    final void bodyShapeIteratorFunc(long body, long shape) {
        CpBody cpBody = registry.getBody(body);
        if (null != cpBody && null != cpBody.shapeIteratorFunc)
            cpBody.shapeIteratorFunc.shape(cpBody, registry.getShape(shape));
    }
/*JNI
    static void bodyShapeIteratorFunc(cpBody* body, cpShape* shape, void* data) {
        GdxChipmunk* gdxChipmunk = (GdxChipmunk*)data;
        if (0 != gdxChipmunk) {
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->bodyShapeIteratorFuncID, (jlong)body, (jlong)shape);
        }
    }
*/

    final void bodyConstraintIteratorFunc(long body, long constraint) {
        CpBody cpBody = registry.getBody(body);
        if (null != cpBody && null != cpBody.constraintIteratorFunc)
            cpBody.constraintIteratorFunc.constraint(cpBody, registry.getConstraint(constraint));
    }
/*JNI
    static void bodyConstraintIteratorFunc(cpBody* body, cpConstraint* constraint, void* data) {
        GdxChipmunk* gdxChipmunk = (GdxChipmunk*)data;
        if (0 != gdxChipmunk) {
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->bodyConstraintIteratorFuncID, (jlong)body, (jlong)constraint);
        }
    }
*/

    final void bodyArbiterIteratorJniFunc(long body, long arbiter) {
        CpBody cpBody = registry.getBody(body);
        if (null != cpBody && null != cpBody.arbiteIteratorFunc)
            cpBody.arbiteIteratorFunc.arbiter(cpBody, cpBody.arbiterIterator.init(arbiter, cpBody.space));
    }
/*JNI
    static void bodyArbiterIteratorJniFunc(cpBody* body, cpArbiter* arbiter, void* data) {
        GdxChipmunk* gdxChipmunk = (GdxChipmunk*)data;
        if (0 != gdxChipmunk) {
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->bodyArbiterIteratorJniFuncID, (jlong)body, (jlong)arbiter);
        }
    }
*/


    // CONSTRAINT CALLBACKS

    final void constraintPreSolveFunc(long constraint, long space) {
        CpConstraint cpConstraint = registry.getConstraint(constraint);
        if (null != cpConstraint && null != cpConstraint.preSolveFunc)
            cpConstraint.preSolveFunc.solve(cpConstraint, registry.getSpace(space));
    }
/*JNI
    static void constraintPreSolveFunc(cpConstraint* constraint, cpSpace* space) {
        GdxChipmunk* gdxChipmunk = gdxCpSpaceGetGdxChipmunk(space);
        if (0 != gdxChipmunk) {
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->constraintPreSolveFuncID, (jlong)constraint, (jlong)space);
        }
    }
*/

    final void constraintPostSolveFunc(long constraint, long space) {
        CpConstraint cpConstraint = registry.getConstraint(constraint);
        if (null != cpConstraint && null != cpConstraint.postSolveFunc)
            cpConstraint.postSolveFunc.solve(cpConstraint, registry.getSpace(space));
    }
/*JNI
    static void constraintPostSolveFunc(cpConstraint* constraint, cpSpace* space) {
        GdxChipmunk* gdxChipmunk = gdxCpSpaceGetGdxChipmunk(space);
        if (0 != gdxChipmunk) {
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->constraintPostSolveFuncID, (jlong)constraint, (jlong)space);
        }
    }
*/

    final float dampedRotarySpringTorqueFunc(long constraint, float relativeAngle) {
        CpDampedRotarySpring dampedRotarySpring = (CpDampedRotarySpring) registry.getConstraint(constraint);
        return (null != dampedRotarySpring && null != dampedRotarySpring.dampedRotarySpringTorqueFunc)
                ? dampedRotarySpring.dampedRotarySpringTorqueFunc.onForce(dampedRotarySpring, relativeAngle)
                : 0;
    }
/*JNI
    static cpFloat dampedRotarySpringTorqueFunc(cpConstraint* constraint, cpFloat relativeAngle) {
        GdxChipmunk* gdxChipmunk = gdxCpConstraintGetGdxChipmunk(constraint);
        cpFloat result = 0;
        if (0 != gdxChipmunk) {
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            result = (cpFloat) env->CallFloatMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->dampedRotarySpringTorqueFuncID,
                (jlong)constraint, (jfloat)relativeAngle);
        }
        return result;
    }
*/

    final float dampedSpringForceFunc(long constraint, float dist) {
        CpDampedSpring dampedSpring = (CpDampedSpring) registry.getConstraint(constraint);
        return (null != dampedSpring && null != dampedSpring.dampedSpringForceFunc)
                ? dampedSpring.dampedSpringForceFunc.onForce(dampedSpring, dist)
                : 0;
    }
/*JNI
    static cpFloat dampedSpringForceFunc(cpConstraint* constraint, cpFloat dist) {
        GdxChipmunk* gdxChipmunk = gdxCpConstraintGetGdxChipmunk(constraint);
        cpFloat result = 0;
        if (0 != gdxChipmunk) {
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            result = (cpFloat) env->CallFloatMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->dampedSpringForceFuncID,
                (jlong)constraint, (jfloat)dist);
        }
        return result;
    }
*/


    // SPACE CALLBACKS

    final void spacePostStepFunc(long space, long key) {
        CpSpace cpSpace = registry.getSpace(space);
        if (null == cpSpace)
            return;
        CpPostStepFuncData funcData = cpSpace.postStepFuncs.get(key);
        if (null != funcData)
            funcData.trigger(cpSpace);
    }
/*JNI
    static void spacePostStepFunc(cpSpace* space, void* key, void* data) {
        GdxChipmunk* gdxChipmunk = gdxCpSpaceGetGdxChipmunk(space);
        if (0 != gdxChipmunk) {
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->spacePostStepFuncID, (jlong)space, (jlong)key);
        }
    }
*/

    final void spacePointQueryFunc(long space, long shape, float x, float y, float distance,
            float gradient_x, float gradient_y) {
        CpSpace cpSpace = registry.getSpace(space);
        if (null != cpSpace && null != cpSpace.pointQueryFunc)
            cpSpace.pointQueryFunc.query(registry.getShape(shape), x, y, distance, gradient_x, gradient_y);
    }
/*JNI
    static void spacePointQueryFunc(cpShape* shape, cpVect point, cpFloat distance, cpVect gradient, void* data) {
        cpSpace* space = (cpSpace*)data;
        GdxChipmunk* gdxChipmunk = gdxCpSpaceGetGdxChipmunk(space);
        if (0 != gdxChipmunk) {
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->spacePointQueryFuncID, (jlong)space, (jlong)shape,
                point.x, point.y, distance, gradient.x, gradient.y);
        }
    }
*/

    final void spaceSegmentQueryFunc(long space, long shape, float x, float y, 
            float normal_x, float normal_y, float alpha) {
        CpSpace cpSpace = registry.getSpace(space);
        if (null != cpSpace && null != cpSpace.segmentQueryFunc)
            cpSpace.segmentQueryFunc.query(registry.getShape(shape), x, y, normal_x, normal_y, alpha);
    }
/*JNI
    static void spaceSegmentQueryFunc(cpShape* shape, cpVect point, cpVect normal, cpFloat alpha, void* data) {
        cpSpace* space = (cpSpace*)data;
        GdxChipmunk* gdxChipmunk = gdxCpSpaceGetGdxChipmunk(space);
        if (0 != gdxChipmunk) {
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->spaceSegmentQueryFuncID, (jlong)space, (jlong)shape,
                point.x, point.y, normal.x, normal.y, alpha);
        }
    }
*/

    final void spaceBbQueryFunc(long space, long shape) {
        CpSpace cpSpace = registry.getSpace(space);
        if (null != cpSpace && null != cpSpace.bbQueryFunc)
            cpSpace.bbQueryFunc.query(registry.getShape(shape));
    }
/*JNI
    static void spaceBbQueryFunc(cpShape* shape, void* data) {
        cpSpace* space = (cpSpace*)data;
        GdxChipmunk* gdxChipmunk = gdxCpSpaceGetGdxChipmunk(space);
        if (0 != gdxChipmunk) {
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->spaceBbQueryFuncID, (jlong)space, (jlong)shape);
        }
    }
*/

    final void spaceShapeQueryFunc(long space, long shape, int count) {
        CpSpace cpSpace = registry.getSpace(space);
        if (null != cpSpace && null != cpSpace.shapeQueryFunc)
            cpSpace.shapeQueryFunc.query( registry.getShape(shape),
                    cpSpace.shapeQueryFuncPointSet.set(count, cpSpace.shapeQueryFuncFloats));
    }
/*JNI
    static void spaceShapeQueryFunc(cpShape* shape, cpContactPointSet* points, void* data) {
        cpSpace* space = cpShapeGetSpace(shape);
        if (0 == space)
            return;
        GdxChipmunk* gdxChipmunk = gdxCpSpaceGetGdxChipmunk(space);
        if (0 == gdxChipmunk)
            return;

        float* floats = (float*)data;
        int count = gdxCpContactPointSetToFloats(points, floats);
        JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
        env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->spaceShapeQueryFuncID, (jlong)space, (jlong)shape, (jint)count);
    }
*/

    final void spaceBodyIteratorFunc(long space, long body) {
        CpSpace cpSpace = registry.getSpace(space);
        if (null != cpSpace && null != cpSpace.bodyIteratorFunc)
            cpSpace.bodyIteratorFunc.each(registry.getBody(body));
    }
/*JNI
    static void spaceBodyIteratorFunc(cpBody* body, void* data) {
        cpSpace* space = (cpSpace*)data;
        GdxChipmunk* gdxChipmunk = gdxCpSpaceGetGdxChipmunk(space);
        if (0 != gdxChipmunk){
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->spaceBodyIteratorFuncID, (jlong)space, (jlong)body);
        }
    }
*/

    final void spaceShapeIteratorFunc(long space, long shape) {
        CpSpace cpSpace = registry.getSpace(space);
        if (null != cpSpace && null != cpSpace.shapeIteratorFunc)
            cpSpace.shapeIteratorFunc.each(registry.getShape(shape));
    }
/*JNI
    static void spaceShapeIteratorFunc(cpShape* shape, void* data) {
        cpSpace* space = (cpSpace*)data;
        GdxChipmunk* gdxChipmunk = gdxCpSpaceGetGdxChipmunk(space);
        if (0 != gdxChipmunk){
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->spaceShapeIteratorFuncID, (jlong)space, (jlong)shape);
        }
    }
*/

    final void spaceConstraintIteratorFunc(long space, long constraint) {
        CpSpace cpSpace = registry.getSpace(space);
        if (null != cpSpace && null != cpSpace.constraintIteratorFunc)
            cpSpace.constraintIteratorFunc.each(registry.getConstraint(constraint));
    }
/*JNI
    static void spaceConstraintIteratorFunc(cpConstraint* constraint, void* data) {
        cpSpace* space = (cpSpace*)data;
        GdxChipmunk* gdxChipmunk = gdxCpSpaceGetGdxChipmunk(space);
        if (0 != gdxChipmunk){
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->spaceConstraintIteratorFuncID,
                (jlong)space, (jlong)constraint);
        }
    }
*/


    // COLLISION CALLBACKS

    final boolean collisionBeginFunc(long arb, long space, long typeA, long typeB) {
        CpSpace cpSpace = registry.getSpace(space);
        boolean result = true;
        if (null != cpSpace.defaultCollisionHandler && null != cpSpace.defaultCollisionHandler.beginFunc) {
            result = cpSpace.defaultCollisionHandler.beginFunc.begin(cpSpace.tempArbiter.init(arb, cpSpace),
                    cpSpace, cpSpace.defaultCollisionHandler.userData);
        }
        return result;
    }
    final boolean collisionPreSolveFunc(long arb, long space, long typeA, long typeB) {
        CpSpace cpSpace = registry.getSpace(space);
        boolean result = true;
        if (null != cpSpace.defaultCollisionHandler && null != cpSpace.defaultCollisionHandler.preSolveFunc) {
            result = cpSpace.defaultCollisionHandler.preSolveFunc.preSolve(cpSpace.tempArbiter.init(arb, cpSpace),
                    cpSpace, cpSpace.defaultCollisionHandler.userData);
        }
        return result;
    }
    final void collisionPostSolveFunc(long arb, long space, long typeA, long typeB) {
        CpSpace cpSpace = registry.getSpace(space);
        if (null != cpSpace.defaultCollisionHandler && null != cpSpace.defaultCollisionHandler.postSolveFunc) {
            cpSpace.defaultCollisionHandler.postSolveFunc.postSolve(cpSpace.tempArbiter.init(arb, cpSpace),
                    cpSpace, cpSpace.defaultCollisionHandler.userData);
        }
    }
    final void collisionSeparateFunc(long arb, long space, long typeA, long typeB) {
        CpSpace cpSpace = registry.getSpace(space);
        if (null != cpSpace.defaultCollisionHandler && null != cpSpace.defaultCollisionHandler.separateFunc) {
            cpSpace.defaultCollisionHandler.separateFunc.separate(cpSpace.tempArbiter.init(arb, cpSpace),
                    cpSpace, cpSpace.defaultCollisionHandler.userData);
        }
    }
/*JNI
    static cpBool collisionBeginFunc(cpArbiter* arb, cpSpace* space, cpDataPointer userData) {
        GdxCollisionHandler* gdxCollisionHandler = (GdxCollisionHandler*)userData;
        cpBool result = cpTrue;
        if (gdxCollisionHandler->begin) {
            GdxChipmunk* gdxChipmunk = gdxCollisionHandler->gdxSpace->gdxChipmunk;
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            result = (cpBool) env->CallBooleanMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->collisionBeginFuncID,
                (jlong)arb, (jlong)space, (jlong)gdxCollisionHandler->typeA, (jlong)gdxCollisionHandler->typeB);
        }
        return result;
    }
    static cpBool collisionPreSolveFunc(cpArbiter* arb, cpSpace* space, cpDataPointer userData) {
        GdxCollisionHandler* gdxCollisionHandler = (GdxCollisionHandler*)userData;
        cpBool result = cpTrue;
        if (gdxCollisionHandler->preSolve) {
            GdxChipmunk* gdxChipmunk = gdxCollisionHandler->gdxSpace->gdxChipmunk;
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            result = (cpBool) env->CallBooleanMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->collisionPreSolveFuncID,
                (jlong)arb, (jlong)space, (jlong)gdxCollisionHandler->typeA, (jlong)gdxCollisionHandler->typeB);
        }
        return result;
    }
    static void collisionPostSolveFunc(cpArbiter* arb, cpSpace* space, cpDataPointer userData) {
        GdxCollisionHandler* gdxCollisionHandler = (GdxCollisionHandler*)userData;
        if (gdxCollisionHandler->postSolve) {
            GdxChipmunk* gdxChipmunk = gdxCollisionHandler->gdxSpace->gdxChipmunk;
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->collisionPostSolveFuncID,
                (jlong)arb, (jlong)space, (jlong)gdxCollisionHandler->typeA, (jlong)gdxCollisionHandler->typeB);
        }
    }
    static void collisionSeparateFunc(cpArbiter* arb, cpSpace* space, cpDataPointer userData) {
        GdxCollisionHandler* gdxCollisionHandler = (GdxCollisionHandler*)userData;
        if (gdxCollisionHandler->separate) {
            GdxChipmunk* gdxChipmunk = gdxCollisionHandler->gdxSpace->gdxChipmunk;
            JNIEnv* env = (JNIEnv*) gdxChipmunk->jEnv;
            env->CallVoidMethod((jobject)gdxChipmunk->jObj, (jmethodID)gdxChipmunk->collisionSeparateFuncID,
                (jlong)arb, (jlong)space, (jlong)gdxCollisionHandler->typeA, (jlong)gdxCollisionHandler->typeB);
        }
    }
*/
    void registerCollisionHandler(long gdxSpaceAddress, long collisionHandlerAddress, CpCollisionHandler collisionHandler) {
        registerCollisionHandler(nativeAddress, collisionHandlerAddress,
                null != collisionHandler.beginFunc, null != collisionHandler.preSolveFunc,
                null != collisionHandler.postSolveFunc, null != collisionHandler.separateFunc);
    }
    private native void registerCollisionHandler(long gdxSpaceAddress, long collisionHandlerAddress,
            boolean begin, boolean preSolve, boolean postSolve, boolean separate); /*
        GdxSpace* gdxSpace = (GdxSpace*)gdxSpaceAddress;
        cpCollisionHandler* collisionHandler = (cpCollisionHandler*)collisionHandlerAddress;
        GdxCollisionHandler* gdxCollisionHandler = gdxCollisionHandlerNew(gdxSpace, collisionHandler->typeA, collisionHandler->typeB,
            begin, preSolve, postSolve, separate);
        collisionHandler->userData = (cpDataPointer)gdxCollisionHandler;
        collisionHandler->beginFunc = (cpCollisionBeginFunc)collisionBeginFunc;
        collisionHandler->preSolveFunc = (cpCollisionPreSolveFunc)collisionPreSolveFunc;
        collisionHandler->postSolveFunc = (cpCollisionPostSolveFunc)collisionPostSolveFunc;
        collisionHandler->separateFunc = (cpCollisionSeparateFunc)collisionSeparateFunc;
    */

    static {
        init();
        long[] adr = new long[32];
        jniGetNativeAddresses(adr);
        int i = 0;
        bodyPositionJniFunc = adr[i++];
        bodyVelocityJniFunc = adr[i++];
        bodyShapeIteratorJniFunc = adr[i++];
        bodyConstraintIteratorJniFunc = adr[i++];
        bodyArbiterIteratorJniFunc = adr[i++];

        constraintPreSolveJniFunc = adr[i++];
        constraintPostSolveJniFunc = adr[i++];
        dampedRotarySpringTorqueJniFunc = adr[i++];
        dampedSpringForceJniFunc = adr[i++];

        spacePostStepJniFunc = adr[i++];
        spacePointQueryJniFunc = adr[i++];
        spaceSegmentQueryJniFunc = adr[i++];
        spaceBbQueryJniFunc = adr[i++];
        spaceShapeQueryJniFunc = adr[i++];
        spaceBodyIteratorJniFunc = adr[i++];
        spaceShapeIteratorJniFunc = adr[i++];
        spaceConstraintIteratorJniFunc = adr[i++];
    }
    private static native void jniGetNativeAddresses(long[] adr); /*
        int i = 0;
        adr[i++] = (jlong) bodyPositionFunc;
        adr[i++] = (jlong) bodyVelocityFunc;
        adr[i++] = (jlong) bodyShapeIteratorFunc;
        adr[i++] = (jlong) bodyConstraintIteratorFunc;
        adr[i++] = (jlong) bodyArbiterIteratorJniFunc;

        adr[i++] = (jlong) constraintPreSolveFunc;
        adr[i++] = (jlong) constraintPostSolveFunc;
        adr[i++] = (jlong) dampedRotarySpringTorqueFunc;
        adr[i++] = (jlong) dampedSpringForceFunc;

        adr[i++] = (jlong) spacePostStepFunc;
        adr[i++] = (jlong) spacePointQueryFunc;
        adr[i++] = (jlong) spaceSegmentQueryFunc;
        adr[i++] = (jlong) spaceBbQueryFunc;
        adr[i++] = (jlong) spaceShapeQueryFunc;
        adr[i++] = (jlong) spaceBodyIteratorFunc;
        adr[i++] = (jlong) spaceShapeIteratorFunc;
        adr[i++] = (jlong) spaceConstraintIteratorFunc;
    */

    private static boolean sharedLibraryLoaded;
    static void init() {
        if (!sharedLibraryLoaded) {
            try {
                new SharedLibraryLoader().load("gdx-chipmunk");
            } catch (GdxRuntimeException e) {
                new JniGenSharedLibraryLoader("libs/gdx-chipmunk-natives.jar").load("gdx-chipmunk");
            }
            sharedLibraryLoaded = true;
        }
    }

}
