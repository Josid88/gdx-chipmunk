#ifndef GDX_CHIPMUNK_UTILS_H
#define GDX_CHIPMUNK_UTILS_H

#include <chipmunk/cpBB.h>


static inline void gdxFloatToCpvArray(int vertexCount, float* floatArray, cpVect* vectArray)
{
  for(int i = 0; i < vertexCount; i++){
    int index = i * 2;
    cpVect* v = &(vectArray[i]);
    v->x = floatArray[ index ];
    v->y = floatArray[ index + 1 ];
  }
}

static inline cpTransform gdxCpTransform(float x, float y, float angle)
{
	return cpTransformRigid(cpv(x, y), angle);
}

static inline void gdxCpVectToFloats(cpVect* v, float* out_floats)
{
	out_floats[0] = v->x;
	out_floats[1] = v->y;
}

static inline void gdxCpBBToFloats(cpBB* bb, float* floats)
{
  floats[0] = bb->l;
  floats[1] = bb->b;
  floats[2] = bb->r;
  floats[3] = bb->t;
}

static inline void gdxCpPointQueryInfoToFloats(cpPointQueryInfo* pointQueryInfo, float* floats)
{
  floats[0] = pointQueryInfo->point.x;
  floats[1] = pointQueryInfo->point.y;
  floats[2] = pointQueryInfo->distance;
  floats[3] = pointQueryInfo->gradient.x;
  floats[4] = pointQueryInfo->gradient.y;
}

static inline void gdxCpSegmentQueryInfoToFloats(cpSegmentQueryInfo* segmentQueryInfo, float* floats)
{
  floats[0] = segmentQueryInfo->point.x;
  floats[1] = segmentQueryInfo->point.y;
  floats[2] = segmentQueryInfo->normal.x;
  floats[3] = segmentQueryInfo->normal.y;
  floats[4] = segmentQueryInfo->alpha;
}

static inline void gdxCpShapeFilterToInts(cpShapeFilter shapeFilter, int* ints)
{
  ints[0] = (int) shapeFilter.group;
  ints[1] = (int) shapeFilter.categories;
  ints[2] = (int) shapeFilter.mask;
}

static inline int gdxCpContactPointSetToFloats(cpContactPointSet* contactPointSet, float* floats)
{
	floats[0] = contactPointSet->normal.x;
	floats[1] = contactPointSet->normal.y;
	for (int i = 0; i < contactPointSet->count; i++) {
		int index = (i * 5) + 2;
		floats[index] = contactPointSet->points[i].pointA.x;
		floats[index+1] = contactPointSet->points[i].pointA.y;
		floats[index+2] = contactPointSet->points[i].pointB.x;
		floats[index+3] = contactPointSet->points[i].pointB.y;
		floats[index+4] = contactPointSet->points[i].distance;
	}
	return contactPointSet->count;
}

static inline void gdxFloatsToCpContactPointSet(int count, float* floats, cpContactPointSet* contactPointSet)
{
	contactPointSet->normal.x = floats[0];
	contactPointSet->normal.y = floats[1];
	contactPointSet->count = count;
	for (int i = 0; i < count; i++) {
		int index = (i * 5) + 2;
		contactPointSet->points[i].pointA.x = floats[index];
		contactPointSet->points[i].pointA.y = floats[index+1];
		contactPointSet->points[i].pointB.x = floats[index+2];
		contactPointSet->points[i].pointB.y = floats[index+3];
		contactPointSet->points[i].distance = floats[index+4];
	}
}

static inline int gdxCpBodyTypeToInt(cpBodyType type)
{
  switch(type){
    case CP_BODY_TYPE_KINEMATIC: return 1;
    case CP_BODY_TYPE_STATIC: return 2;
    default: return 0;
  }
}

static inline cpBodyType gdxIntToCpBodyType(int type)
{
  switch(type){
    case 1: return CP_BODY_TYPE_KINEMATIC;
    case 2: return CP_BODY_TYPE_STATIC;
    default: return CP_BODY_TYPE_DYNAMIC;
  }
}

static inline void gdxSetBox(float width, float height, cpVect* verts)
{
  float hw = width * .5f,
		hh = height * .5f;
  verts[0].x = -hw; verts[0].y = hh;
  verts[1].x = -hw;  verts[1].y = -hh;
  verts[2].x = hw;  verts[2].y = -hh;
  verts[3].x = hw; verts[3].y = hh;
}

static inline void gdxRotateTranslatePolygon(float offsetX, float offsetY, float angle, int count, cpVect* verts)
{

  float angleCos = angle == 0 ? 0 : cpfcos(angle);
  float angleSin = angle == 0 ? 0 : cpfsin(angle);
  for (int i = 0; i < count; i++) {
    float x = verts[i].x;
    float y = verts[i].y;
    if (angle != 0) {
      float oldX = x;
      x = angleCos * x - angleSin * y;
      y = angleSin * oldX + angleCos * y;
    }
    verts[i].x = x + offsetX;
    verts[i].y = y + offsetY;
  }
}

static inline void gdxShapeSetOnInit(cpShape* shape, cpBool sensor, cpFloat mass, cpFloat density, cpFloat elasticity,
		cpFloat friction, cpFloat surfaceVelocity_x, cpFloat surfaceVelocity_y,
		cpGroup group, cpBitmask categories, cpBitmask mask)
{
	if (sensor) cpShapeSetSensor(shape, sensor);
	if (0 != mass) cpShapeSetMass(shape, mass);
	if (0 != density) cpShapeSetDensity(shape, density);
	if (0 != elasticity) cpShapeSetElasticity(shape, elasticity);
	if (0 != friction) cpShapeSetFriction(shape, friction);
	if (0 != surfaceVelocity_x || 0 != surfaceVelocity_y)
		cpShapeSetSurfaceVelocity(shape, cpv(surfaceVelocity_x, surfaceVelocity_y));
	if (CP_NO_GROUP != group || CP_ALL_CATEGORIES != categories || CP_ALL_CATEGORIES != mask)
		cpShapeSetFilter(shape, cpShapeFilterNew(group, categories, mask));

}

#endif
