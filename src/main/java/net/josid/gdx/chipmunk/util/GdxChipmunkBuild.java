package net.josid.gdx.chipmunk.util;
import com.badlogic.gdx.jnigen.AntScriptGenerator;
import com.badlogic.gdx.jnigen.BuildConfig;
import com.badlogic.gdx.jnigen.BuildExecutor;
import com.badlogic.gdx.jnigen.BuildTarget;
import com.badlogic.gdx.jnigen.NativeCodeGenerator;
import com.badlogic.gdx.jnigen.BuildTarget.TargetOs;

/*
 * CHIPMUNK CHANGES:
 * 
 * cpHastySpace.c
 * modified lines 15 -> 25 for mingw and android pthread support
 * 
 * 
 * CONFIGURATION:
 * 
 * Win32:
 * Install mingw-get-setup.exe, install with the GUI, choose mingw32-base and mingw32-gcc-g++ and add bin to path
 * clone [g++.exe, gcc.exe, strip.exe] and remove ".exe" in clones
 * 
 * Win64:
 * Unzip mingw64 and add bin to path
 * clone [x86_64-w64-mingw32-g++.exe, x86_64-w64-mingw32-gcc.exe] and remove ".exe" in clones
 * 
 * 
 * TO RUN:
 * 
 * Add Apache ant to path
 * Explode manually dependency jars in ./target/classes/
 * 
 */
public class GdxChipmunkBuild {

    public static void main(String[] args) throws Exception {
        //String cFlags = ""; // Use this to enable debug
        String cFlags = " -DNDEBUG";
        String[] headerDirs = { "Chipmunk2D/include" };
        String[] cIncludes = { "Chipmunk2D/src/*.c" };
        
        BuildTarget win32 = BuildTarget.newDefaultTarget(TargetOs.Windows, false);
        win32.compilerPrefix = "";
        win32.headerDirs = headerDirs;
        win32.cIncludes = cIncludes;
        win32.cFlags += cFlags + " -std=c99";

        BuildTarget win64 = BuildTarget.newDefaultTarget(TargetOs.Windows, true);
        win64.headerDirs = headerDirs;
        win64.cIncludes = cIncludes;
        win64.cFlags += cFlags + " -std=c99";

        BuildTarget android = BuildTarget.newDefaultTarget(TargetOs.Android, false);
        android.headerDirs = headerDirs;
        android.cIncludes = cIncludes;
        android.cFlags += cFlags;

        new NativeCodeGenerator().generate("src/main/java", "target/classes", "jni");
        new AntScriptGenerator().generate(new BuildConfig("gdx-chipmunk"), win32, win64);

        BuildExecutor.executeAnt("jni/build-windows32.xml", "-v", "-Drelease=true", "clean", "postcompile");
        BuildExecutor.executeAnt("jni/build-windows64.xml", "-v", "-Drelease=true", "clean", "postcompile");

        BuildExecutor.executeAnt("jni/build.xml", "-v", "pack-natives");
    }
}
