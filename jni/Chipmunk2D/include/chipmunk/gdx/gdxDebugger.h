#ifndef GDX_DEBUGGER_H
#define GDX_DEBUGGER_H

#include <chipmunk/chipmunk_private.h>
#include <chipmunk/gdx/gdxChipmunkUtils.h>

struct RenderSpaceContext {
	cpSpace* space;
	void* env;
	uintptr_t object;
	char* bytes;
	int bytesSize;
	int bytesIndex;
	float* floats;
	int floatsSize;
	int floatsIndex;
	int bodyShapeCountIndex;
	int bodyFloatIndex;
};

static inline void addByte(RenderSpaceContext* context, char value) {
    context->bytes[context->bytesIndex++] = value;
}

static inline void addFloat(RenderSpaceContext* context, float value) {
    context->floats[context->floatsIndex++] = value;
}

static inline void addShapeCounter(RenderSpaceContext* context) {
    context->bytes[context->bodyShapeCountIndex]++;
}

static inline bool checkAvailability(RenderSpaceContext* context, int bytes, int floats) {
	int availableBytes = context->bytesSize - context->bytesIndex;
	int availableFloats = context->floatsSize - context->floatsIndex;
	return availableBytes > bytes && availableFloats > floats;
}

static inline void clearBodies(RenderSpaceContext* context) {
	context->bytes[0] = 0;
    context->bytesIndex = 1;
    context->floatsIndex = 0;
    context->bodyShapeCountIndex = 0;
    context->bodyFloatIndex = 0;
}

static inline void clearShapes(RenderSpaceContext* context) {
	char bodyType = context->bytes[context->bodyShapeCountIndex - 2];
	char bodyIsSleeping = context->bytes[context->bodyShapeCountIndex - 1];
	float pointX = context->floats[context->bodyFloatIndex];
	float pointY = context->floats[context->bodyFloatIndex + 1];
	float angle = context->floats[context->bodyFloatIndex + 2];
	clearBodies(context);
	context->bytes[0] = 1;
	addByte(context, bodyType);
	addByte(context, bodyIsSleeping);
	context->bodyShapeCountIndex = context->bytesIndex;
	addByte(context, 0);
	context->bodyFloatIndex = context->floatsIndex;
	addFloat(context, pointX);
	addFloat(context, pointY);
	addFloat(context, angle);
}

static inline void initRenderSpaceContext(RenderSpaceContext* context, cpSpace* space, void* env, uintptr_t object,
        char* bytes, int bytesSize, float* floats, int floatsSize) {
    context->space = space;
    context->env = env;
    context->object = object;
    context->bytes = bytes;
    context->bytesSize = bytesSize;
    context->floats = floats;
    context->floatsSize = floatsSize;
    clearBodies(context);
}

static inline bool registerBody(cpBody* body, RenderSpaceContext* context) {
	bool isAvailable = checkAvailability(context, 3, 3);
	if (isAvailable) {
		char bodyType = (char)gdxCpBodyTypeToInt(cpBodyGetType(body));
		char bodyIsSleeping = (char)cpBodyIsSleeping(body);
		char shapeCounter = 0;
		cpVect position = cpBodyGetPosition(body);
		float angle = (float)cpBodyGetAngle(body);

		// Body counter
		context->bytes[0]++;

		// 3 bytes
		addByte(context, bodyType);
		addByte(context, bodyIsSleeping);
		context->bodyShapeCountIndex = context->bytesIndex;
		addByte(context, shapeCounter);

		// 3 floats
		context->bodyFloatIndex = context->floatsIndex;
		addFloat(context, (float)position.x);
		addFloat(context, (float)position.y);
		addFloat(context, angle);
	}
	return isAvailable;
}

static inline bool registerShape(cpShape* shape, RenderSpaceContext* context) {
	char type = 0;
	int requiredBytes = 0;
	int requiredFloats = 0;
	switch (shape->klass->type) {
		case CP_CIRCLE_SHAPE:
			type = 0;
			requiredBytes = 2;
			requiredFloats = 3;
			break;
		case CP_SEGMENT_SHAPE:
			type = 1;
			requiredBytes = 2;
			requiredFloats = 5;
			break;
		case CP_POLY_SHAPE:
			type = 2;
			requiredBytes = 3;
			requiredFloats = 1 + ((cpPolyShape*)shape)->count * 2;
			break;
		default: return true;
	}
	bool isAvailable = checkAvailability(context, requiredBytes, requiredFloats);
	if (isAvailable) {
		char isSensor = (char)cpShapeGetSensor(shape);
		addShapeCounter(context);
		// 2 bytes
		addByte(context, type);
		addByte(context, isSensor);
		// Code from cpSpaceDebug.h
		switch (shape->klass->type) {
			case CP_CIRCLE_SHAPE: {
				cpCircleShape *circle = (cpCircleShape *)shape;
				// 3 floats
				addFloat(context, (float)circle->tc.x);
				addFloat(context, (float)circle->tc.y);
				addFloat(context, (float)circle->r);
				break;
			}
			case CP_SEGMENT_SHAPE: {
				cpSegmentShape *seg = (cpSegmentShape *)shape;
				// 5 floats
				addFloat(context, (float)seg->ta.x);
				addFloat(context, (float)seg->ta.y);
				addFloat(context, (float)seg->tb.x);
				addFloat(context, (float)seg->tb.y);
				addFloat(context, (float)seg->r);
				break;
			}
			case CP_POLY_SHAPE: {
				cpPolyShape *poly = (cpPolyShape *)shape;
				int count = poly->count;
				cpSplittingPlane* planes = poly->planes;
				// 1 byte
				addByte(context, (char)count);
				// 1 + (count*2) floats
				addFloat(context, (float)poly->r);
				for(int i=0; i<count; i++) {
					addFloat(context, (float)planes[i].v0.x);
					addFloat(context, (float)planes[i].v0.y);
				}
				break;
			}
			default: return true;
		}
	}
	return isAvailable;
}

#endif
