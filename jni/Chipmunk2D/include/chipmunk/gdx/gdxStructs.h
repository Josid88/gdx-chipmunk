#ifndef GDX_STRUCTS_H
#define GDX_STRUCTS_H

#include <chipmunk/chipmunk.h>

struct GdxCollisionHandler;

struct GdxChipmunk {
	void* jEnv;
	uintptr_t jObj;

	uintptr_t bodyPositionFuncID;
	uintptr_t bodyVelocityFuncID;
	uintptr_t bodyShapeIteratorFuncID;
	uintptr_t bodyConstraintIteratorFuncID;
	uintptr_t bodyArbiterIteratorJniFuncID;

	uintptr_t constraintPreSolveFuncID;
	uintptr_t constraintPostSolveFuncID;
	uintptr_t dampedRotarySpringTorqueFuncID;
	uintptr_t dampedSpringForceFuncID;

	uintptr_t spacePostStepFuncID;
	uintptr_t spacePointQueryFuncID;
	uintptr_t spaceSegmentQueryFuncID;
	uintptr_t spaceBbQueryFuncID;
	uintptr_t spaceShapeQueryFuncID;
	uintptr_t spaceBodyIteratorFuncID;
	uintptr_t spaceShapeIteratorFuncID;
	uintptr_t spaceConstraintIteratorFuncID;

	uintptr_t collisionBeginFuncID;
	uintptr_t collisionPreSolveFuncID;
	uintptr_t collisionPostSolveFuncID;
	uintptr_t collisionSeparateFuncID;
};

struct GdxSpace {
	GdxChipmunk* gdxChipmunk;
	GdxCollisionHandler* collisionHandlers;
	void* userData;
};

struct GdxCollisionHandler {
	GdxSpace* gdxSpace;
	cpCollisionType typeA;
	cpCollisionType typeB;
	cpBool begin;
	cpBool preSolve;
	cpBool postSolve;
	cpBool separate;
	GdxCollisionHandler* collisionHandlers;
};


static inline GdxChipmunk* gdxChipmunkNew(void* env, uintptr_t obj) {
	GdxChipmunk* gdxChipmunk = (GdxChipmunk*) malloc(sizeof(GdxChipmunk));
	gdxChipmunk->jEnv = env;
	gdxChipmunk->jObj = obj;
	return gdxChipmunk;
}

static inline GdxSpace* gdxSpaceNew(GdxChipmunk* gdxChipmunk) {
	GdxSpace* gdxSpace = (GdxSpace*) malloc(sizeof(GdxSpace));
	gdxSpace->gdxChipmunk = gdxChipmunk;
	gdxSpace->collisionHandlers = 0;
	gdxSpace->userData = 0;
	return gdxSpace;
}

static inline void gdxSpaceFree(GdxSpace* gdxSpace) {
	GdxCollisionHandler* nextCollisionHandler = gdxSpace->collisionHandlers;
	while (0 != nextCollisionHandler) {
		GdxCollisionHandler* freeThis = nextCollisionHandler;
		nextCollisionHandler = nextCollisionHandler->collisionHandlers;
		free(freeThis);
	}
	free((GdxSpace*)gdxSpace);
}

static inline GdxCollisionHandler* gdxCollisionHandlerNew(GdxSpace* gdxSpace, cpCollisionType typeA, cpCollisionType typeB,
		cpBool begin, cpBool preSolve, cpBool postSolve, cpBool separate) {
	GdxCollisionHandler* gdxCollisionHandler = (GdxCollisionHandler*) malloc(sizeof(GdxCollisionHandler));
	gdxCollisionHandler->gdxSpace = gdxSpace;
	gdxCollisionHandler->typeA = typeA;
	gdxCollisionHandler->typeB = typeB;
	gdxCollisionHandler->begin = begin;
	gdxCollisionHandler->preSolve = preSolve;
	gdxCollisionHandler->postSolve = postSolve;
	gdxCollisionHandler->separate = separate;
	gdxCollisionHandler->collisionHandlers = gdxSpace->collisionHandlers;
	gdxSpace->collisionHandlers = gdxCollisionHandler;
	return gdxCollisionHandler;
}


static inline GdxChipmunk* gdxCpSpaceGetGdxChipmunk(cpSpace* space) {
	GdxSpace* gdxSpace = (GdxSpace*) cpSpaceGetUserData(space);
	return (0 == gdxSpace ? 0 : gdxSpace->gdxChipmunk);
}

static inline GdxChipmunk* gdxCpBodyGetGdxChipmunk(cpBody* body) {
	cpSpace* space = cpBodyGetSpace(body);
	return (0 == space ? 0 : gdxCpSpaceGetGdxChipmunk(space));
}

static inline GdxChipmunk* gdxCpShapeGetGdxChipmunk(cpShape* shape) {
	cpSpace* space = cpShapeGetSpace(shape);
	return (0 == space ? 0 : gdxCpSpaceGetGdxChipmunk(space));
}

static inline GdxChipmunk* gdxCpConstraintGetGdxChipmunk(cpConstraint* constraint) {
	cpSpace* space = cpConstraintGetSpace(constraint);
	return (0 == space ? 0 : gdxCpSpaceGetGdxChipmunk(space));
}

#endif
