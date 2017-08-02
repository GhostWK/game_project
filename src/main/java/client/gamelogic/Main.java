package client.gamelogic;


import client.buffers.Vao;
import client.engine.Camera;
import client.engine.ICamera;
import client.entities.Entity;
import client.entities.Light;
import client.loader.Loader;
import client.loader.OBJLoader;
import client.models.TexturedModel;
import client.renderengine.DisplayManager;
import client.renderengine.Renderer;
import client.shaders.ShaderProgram;
import client.shaders.StaticShader;
import client.utils.MyFile;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;


public class Main{
    private static final Main cycle = new Main();
    private static final Loader loader = new Loader();
    private static StaticShader staticShader;
    private static Renderer renderer;


    private ICamera camera;
    private TexturedModel texturedModel;
    private Entity entity1;
    private Entity tree;
    private Light light;

    MyFile test = new MyFile("pngtextures", "cube.png");
    MyFile tree_obj = new MyFile("objfiles","tree.obj");
    MyFile tree_text = new MyFile("pngtextures","tree.png");
    float[] positions = {
            -0.7f, 0.5f, -0.5f,
            0.5f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.7f, -0.5f, 0.0f,
    };

    float[] normals = {
            0.0f, 0.0f, 1f,
            0.0f, 0.0f, 1f,
            0.0f, 0.0f, 1f,
            0.0f, 0.0f, 1f,
    };

    float[] texture = {
            0.0f, 0.0f,
            1.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
    };

    int[] ind = {
            0,1,2,
            2,1,3
    };

    public static void main(String[] args) {
        System.loadLibrary("");
        DisplayManager.create();
        cycle.init(loader);
        cycle.camera = new Camera(new Vector3f(0,2,10), new Vector3f(0,0,0));
        staticShader = new StaticShader();
        renderer = new Renderer(staticShader, cycle.camera);
        while (!Display.isCloseRequested()){
            cycle.preUpdate(loader, staticShader);
            cycle.render(loader, renderer, staticShader);
            cycle.postUpdate(loader, staticShader);
            DisplayManager.update();
        }
        cycle.clear(loader, staticShader);
        DisplayManager.destroy();
    }



    private void init(Loader loader) {
        light = new Light(new Vector3f(0,0,1000), new Vector3f(1,1,1));
        Vao modelVao = loader.loadToVAO(positions, texture, normals, ind);
        texturedModel = new TexturedModel(modelVao, loader.loadTexture(test));
        entity1 = new Entity(texturedModel, new Vector3f(0,0,-3), new Vector3f(0,0,0),1);

        Vao vaoTree = OBJLoader.loadObjModel(tree_obj, loader);
        TexturedModel texturedTree = new TexturedModel(vaoTree, loader.loadTexture(tree_text));
        tree = new Entity(texturedTree, new Vector3f(0,0,-5), new Vector3f(0,0,0), 1);
    }


    private void preUpdate(Loader loader, ShaderProgram shader) {
        shader.start();
        tree.increaseRotation(new Vector3f(0,0.3f,0));

        ((StaticShader)shader).loadLight(light);
        camera.move();

    }



    private void render(Loader loader, Renderer renderer, ShaderProgram shader) {
        renderer.prepare();
        //OpenGLUtils.goWireframe(true);
        renderer.render(tree, shader);
        //OpenGLUtils.goWireframe(false);

        renderer.render(entity1, shader);
    }


    private void postUpdate(Loader loader, ShaderProgram shader) {
        shader.stop();
    }


    private void clear(Loader loader, ShaderProgram shader) {
        shader.cleanUp();
        loader.cleanUp();
    }
}
