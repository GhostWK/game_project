package client.entities;

import client.models.TexturedModel;
import client.shaders.UniformMatrix;
import client.utils.Maths;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by USER on 06.06.2017.
 */
public class Entity {
    private TexturedModel model;
    private Vector3f position;
    private Vector3f rotation;
    private float scale;
    private Matrix4f transformationMatrix;


    public Entity(TexturedModel model, Vector3f position, Vector3f rotation, float scale) {
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        updateTransformationMatrix();
    }

    public void increasePosition(Vector3f dPosition) {
        position = Vector3f.add(position, dPosition, position);
        updateTransformationMatrix();
    }

    public void increaseRotation(Vector3f dRotation) {
        rotation = Vector3f.add(rotation, dRotation, rotation);
        updateTransformationMatrix();
    }

    private void updateTransformationMatrix(){
        transformationMatrix = Maths.createTransformationMatrix(position, rotation, scale);
    }

    public TexturedModel getModel() {
        return model;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
        updateTransformationMatrix();

    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
        updateTransformationMatrix();

    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
        updateTransformationMatrix();

    }

    public Matrix4f getTransformationMatrix() {
        return transformationMatrix;
    }
}
