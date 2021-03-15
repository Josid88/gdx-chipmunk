package net.josid.gdx.chipmunk.util;

import com.badlogic.gdx.graphics.Color;

import net.josid.gdx.chipmunk.CpBody;
import net.josid.gdx.chipmunk.CpShape;

public class CpDebuggerData {

    public int segmentSides = 3;
    public int circleSides = 8;
    public Color staticColor = Color.GREEN;
    public Color staticSensorColor = Color.FOREST;
    public Color kinematicColor = Color.BLUE;
    public Color kinematicSensorColor = Color.SKY;
    public Color dynamicColor = Color.RED;
    public Color dynamicSensorColor = Color.MAROON;
    public Color sleepColor = Color.DARK_GRAY;
    public Color sleepSensorColor = Color.LIGHT_GRAY;

    public Color getShapeColor(CpBody.Type bodyType, boolean isSleeping, CpShape.Type shapeType, boolean isSensor) {
        switch (bodyType) {
            case Dynamic:
                return isSleeping 
                    ? (isSensor ? sleepSensorColor : sleepColor)
                    : (isSensor ? dynamicSensorColor : dynamicColor);
            case Kinematic:
                return isSensor ? kinematicSensorColor : kinematicColor;
            case Static:
                return isSensor ? staticSensorColor : staticColor;
        }
        return Color.BLACK;
    }

}
