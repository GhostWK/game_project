package client.entities;

import client.shaders.ShaderProgram;
import client.shaders.StaticShader;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by USER on 09.07.2017.
 */
public class Light {
    private Vector3f lightPosition;
    private Vector3f color;

    public Light(Vector3f lightDirection, Vector3f color) {
        this.lightPosition = lightDirection;
        this.color = color;
    }

    public Vector3f getLightPosition() {
        return lightPosition;
    }

    public void setLightPosition(Vector3f lightPosition) {
        this.lightPosition = lightPosition;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public void loadLight(ShaderProgram shaderProgram){
        if(shaderProgram instanceof StaticShader){
            ((StaticShader) shaderProgram).getLightPosition().loadVec3(lightPosition);
            ((StaticShader) shaderProgram).getLightColor().loadVec3(color);

        }
    }
}
