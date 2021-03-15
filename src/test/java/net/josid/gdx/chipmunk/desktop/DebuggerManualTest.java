package net.josid.gdx.chipmunk.desktop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;

import net.josid.gdx.chipmunk.Chipmunk;
import net.josid.gdx.chipmunk.CpBody;
import net.josid.gdx.chipmunk.CpSpace;
import net.josid.gdx.chipmunk.CpSpaceDebugger;
import net.josid.gdx.chipmunk.def.CpBodyDef;
import net.josid.gdx.chipmunk.def.CpBodyInstanceDef;
import net.josid.gdx.chipmunk.def.CpShapeDef;
import net.josid.gdx.chipmunk.def.CpSpaceDef;
import net.josid.gdx.chipmunk.def.shape.CpShapeSpecDef;


public class DebuggerManualTest extends ApplicationAdapter{

    private FillViewport viewport;
    private Chipmunk chipmunk;
    private CpSpaceDebugger debugger;
    private CpSpace space;
    private Array<CpBodyDef> bodyDefs;
    private RandomXS128 random;
    long time = 0;

    @Override
    public void create() {
        viewport = new FillViewport(40, 20);
        chipmunk = new Chipmunk();

        CpShapeDef wallShapeSpec = new CpShapeSpecDef().friction(.3f).elastisity(.05f);

        space = chipmunk.factory.space(new CpSpaceDef().gravity(0, -9.8f).sleepTimeThreshold(.2f));
        CpBody floor = chipmunk.factory.body(new CpBodyDef()
                .segment().a(-15, -8).b(15, -8).radius(.2f).set(wallShapeSpec).body()
                .segment().a(-15, -8).b(-15, 6).radius(.2f).set(wallShapeSpec).body()
                .segment().a(15, -8).b(15, 6).radius(.2f).set(wallShapeSpec).body(), CpBody.Type.Static);
        space.addBodyAndShapes(floor);
        debugger = new CpSpaceDebugger();

        CpShapeDef shapesSpec = new CpShapeSpecDef().density(1).friction(.3f).elastisity(.05f);

        CpBodyDef circleBodyDef = new CpBodyDef()
                .circle().radius(.5f).set(shapesSpec).body();
        CpBodyDef boxBodyDef = new CpBodyDef()
                .box().width(1f).height(.5f).set(shapesSpec).body();
        CpBodyDef triBodyDef = new CpBodyDef()
                .poly().vertices(getPolygon(3, .5f)).set(shapesSpec).body();
        CpBodyDef pentBodyDef = new CpBodyDef()
                .poly().vertices(getPolygon(5, .5f)).set(shapesSpec).body();
        CpBodyDef hexBodyDef = new CpBodyDef()
                .poly().vertices(getPolygon(6, .5f)).set(shapesSpec).body();
        CpBodyDef heptBodyDef = new CpBodyDef()
                .poly().vertices(getPolygon(7, .5f)).set(shapesSpec).body();
        CpBodyDef octBodyDef = new CpBodyDef()
                .poly().vertices(getPolygon(8, .5f)).set(shapesSpec).body();

        bodyDefs = new Array<>(new CpBodyDef[] {circleBodyDef, boxBodyDef, triBodyDef, pentBodyDef,
                hexBodyDef, heptBodyDef, octBodyDef});
        
        random = new RandomXS128();
        random.setSeed(1234568);

        instanceDef = new CpBodyInstanceDef();

        time = System.currentTimeMillis();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        viewport.apply();
    }

    int i = 0;
    int stopStep = 0;
    private CpBodyInstanceDef instanceDef;
    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(.2f, .2f, .2f, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        if (i < 650) {
            CpBodyDef bodyDef = bodyDefs.get(random.nextInt(bodyDefs.size));
            instanceDef.position(random.nextFloat() * 16f - 8f, 8);
            instanceDef.angle(random.nextFloat() * MathUtils.PI2);
            CpBody body = chipmunk.factory.body(bodyDef, instanceDef);
            space.addBodyAndShapes(body);
            i++;
        }

        space.step(1f/60f);
        debugger.render(space, viewport.getCamera());
        
        stopStep++;
        if (stopStep == 1000) {
            time = System.currentTimeMillis() - time;
            System.out.println(time);
        }
    }

    @Override
    public void dispose() {
        chipmunk.dispose();
    }

    private final Vector2 direction = new Vector2();
    private float[] getPolygon(int count, float radius) {
        float angle = MathUtils.PI2 / count;
        float[] vertices = new float[count*2];
        direction.set(radius, 0);
        for (int i = 0; i < count; i++) {
            int index = i*2;
            direction.rotateRad(angle);
            vertices[index] = direction.x;
            vertices[index+1] = direction.y;
        }
        return vertices;
    }

}
