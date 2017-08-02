package client.shaders;

import client.entities.Light;
import client.utils.MyFile;

/**
 * Created by USER on 03.06.2017.
 */
public class StaticShader extends ShaderProgram{
    private static final MyFile vertSh = new MyFile("shaders", "vertShader.glsl");
    private static final MyFile fragmSh = new MyFile("shaders", "fragmShader.glsl");

    private UniformMatrix projectionViewMatrix = new UniformMatrix("projectionViewMatrix");
    private UniformMatrix transformationMatrix = new UniformMatrix("transformationMatrix");
    private UniformMatrix viewMatrix = new UniformMatrix("viewMatrix");
    private UniformVec3 lightPosition = new UniformVec3("lightPosition");
    private UniformVec3 lightColor = new UniformVec3("lightColor");


    public StaticShader() {
        super(vertSh, fragmSh, "position", "textureCoordinates", "normal");
        super.storeAllUniformLocations(transformationMatrix, projectionViewMatrix, viewMatrix, lightPosition, lightColor);
    }

    public UniformMatrix getProjectionViewMatrix() {
        return projectionViewMatrix;
    }

    public UniformMatrix getTransformationMatrix() {
        return transformationMatrix;
    }

    public UniformMatrix getViewMatrix() {
        return viewMatrix;
    }

    public UniformVec3 getLightPosition() {
        return lightPosition;
    }

    public UniformVec3 getLightColor() {
        return lightColor;
    }

    public void loadLight(Light light){

        lightPosition.loadVec3(light.getLightPosition());
        lightColor.loadVec3(light.getColor());
    }
}
