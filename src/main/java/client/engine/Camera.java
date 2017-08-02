package client.engine;

import client.renderengine.DisplayManager;
import client.utils.Maths;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by USER on 08.07.2017.
 */
public class Camera implements ICamera{
    private Vector3f position;
    private Vector3f direction;
    private Matrix4f viewMatrix;

    private static float speed = 50;
    private static float rotSpeed = 180;

    public Camera(Vector3f position, Vector3f direction) {
        this.position = position;
        this.direction = direction;
        viewMatrix = Maths.createViewMatrix(position, direction);
    }

    @Override
    public Matrix4f getViewMatrix() {
        return viewMatrix;
    }

    @Override
    public void move() {
        float delta = DisplayManager.getFrameTimeSeconds();
        float deltaspeed = delta * speed;
        double dirYtoRad = Math.toRadians(direction.y);
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            increasePosition(new Vector3f((float) (deltaspeed * Math.sin(dirYtoRad)), 0,(float) (-deltaspeed * Math.cos(dirYtoRad))));
        }else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            increasePosition(new Vector3f((float) (-deltaspeed * Math.sin(dirYtoRad)), 0,(float) (deltaspeed * Math.cos(dirYtoRad))));
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            increasePosition(new Vector3f((float) (-deltaspeed * Math.cos(dirYtoRad)), 0,(float) (-deltaspeed * Math.sin(dirYtoRad))));
        }else if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            increasePosition(new Vector3f((float) (deltaspeed * Math.cos(dirYtoRad)), 0,(float) (deltaspeed * Math.sin(dirYtoRad))));
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
            increasePosition(new Vector3f(0,deltaspeed,0));
        }else if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
            increasePosition(new Vector3f(0,-deltaspeed,0));
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            increaseRotation(new Vector3f(0,-rotSpeed*delta,0));
        }else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            increaseRotation(new Vector3f(0,rotSpeed*delta,0));
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
            increaseRotation(new Vector3f(-rotSpeed*delta,0,0));
        }else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            increaseRotation(new Vector3f(rotSpeed*delta,0,0));
        }

        viewMatrix = Maths.createViewMatrix(position, direction);

    }

    private void increasePosition(Vector3f dPosition) {
        position = Vector3f.add(position, dPosition, position);
    }

    private void increaseRotation(Vector3f dDirection) {
        direction = Vector3f.add(direction, dDirection, direction);
    }
}
