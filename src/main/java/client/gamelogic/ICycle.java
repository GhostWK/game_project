package client.gamelogic;

import client.loader.Loader;
import client.renderengine.Renderer;
import client.shaders.ShaderProgram;

/**
 * Created by USER on 03.06.2017.
 */
public interface ICycle {

    void init(Loader loader);
    void preUpdate(Loader loader, ShaderProgram shader);
    void render(Loader loader, Renderer renderer, ShaderProgram shader);
    void postUpdate(Loader loader, ShaderProgram shader);
    void clear(Loader loader, ShaderProgram shader);
}
