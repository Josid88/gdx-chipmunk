package net.josid.gdx.chipmunk.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class MainManualTest {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Plastic Creator";
        config.width = 1200;
        config.height = 700;
        config.vSyncEnabled = false; // Setting to false disables vertical sync
        config.foregroundFPS = 0; // Setting to 0 disables foreground fps throttling
        config.backgroundFPS = 0; // Setting to 0 disables background fps throttling
        new LwjglApplication(new DebuggerManualTest(), config);
    }

}
