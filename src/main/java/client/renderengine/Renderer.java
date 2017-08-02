package client.renderengine;

import client.buffers.Vao;
import client.engine.ICamera;
import client.entities.Entity;
import client.models.TexturedModel;
import client.shaders.ShaderProgram;
import client.shaders.StaticShader;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by USER on 03.06.2017.
 */
public class Renderer {

    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;


    private Matrix4f projectionMatrix = null;
    private ICamera camera;

    public Renderer(StaticShader staticShader, ICamera camera) {
        createProjectionMatrix();
        staticShader.start();
        staticShader.getProjectionViewMatrix().loadMatrix(projectionMatrix);
        staticShader.stop();
        this.camera = camera;
    }

    public void prepare(){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.7f, 0.1f, 0.3f, 1);
    }

    public void render(Entity entity, ShaderProgram program){

        TexturedModel texturedModel =  entity.getModel();
        Vao modelVao = texturedModel.getVao();

        modelVao.bind(0, 1, 2);

        if(program instanceof StaticShader){
            ((StaticShader) program).getTransformationMatrix().loadMatrix(entity.getTransformationMatrix());
            ((StaticShader) program).getViewMatrix().loadMatrix(camera.getViewMatrix());
        }

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture_id());
        GL11.glDrawElements(GL11.GL_TRIANGLES, modelVao.getIndexCount(), GL11.GL_UNSIGNED_INT, 0);
        modelVao.unbind(0, 1, 2);
    }

    private void createProjectionMatrix(){
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV/2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
    }
}
