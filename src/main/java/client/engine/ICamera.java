package client.engine;

import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by USER on 08.07.2017.
 */
public interface ICamera {
    public Matrix4f getViewMatrix();
    public void move();
}
