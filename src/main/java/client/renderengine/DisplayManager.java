package client.renderengine;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.*;

/**
 * Created by USER on 02.06.2017.
 */
public class DisplayManager {

    public static int WIDTH = 1200;
    public static int HEIGHT = 720;
    public static int FPS = 60;

    private static long lastFrameTime;
    private static float delta;


    public static void create(){
        ContextAttribs attribs = new ContextAttribs(3,3)
                .withForwardCompatible(true)
                .withProfileCore(true);
        try {

            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create(new PixelFormat().withSamples(8), attribs);
            GL11.glEnable(GL13.GL_MULTISAMPLE);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        GL11.glViewport(0,0, WIDTH, HEIGHT);
        lastFrameTime = getCurrentTime();
    }

    public static void destroy(){
        Display.destroy();
    }

    public static void update(){
        Display.sync(FPS);
        Display.update();
        long currentFrameTime = getCurrentTime();
        delta = (currentFrameTime - lastFrameTime)/1000f;
        lastFrameTime = currentFrameTime;
        ///FPS showing
        l++;
        if(l==20){
            Display.setTitle("Game. FPS : " + Math.round(1.0/delta));
            l=0;
        }

    }

    private static int l = 0;//for FPS
    public static void updateDisplay(){



    }

    public static float getFrameTimeSeconds(){
        return delta;
    }

    public static void closeDisplay(){
        Display.destroy();
    }

    private static long getCurrentTime(){
        return Sys.getTime()*1000/Sys.getTimerResolution();
    }
}
