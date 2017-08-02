package client.models;

import client.buffers.Vao;

/**
 * Created by USER on 03.06.2017.
 */
public class TexturedModel {

    private Vao vao;
    private int texture_id;

    public TexturedModel(Vao vao, int texture_id) {
        this.vao = vao;
        this.texture_id = texture_id;
    }

    public Vao getVao() {
        return vao;
    }
    public int getTexture_id() {
        return texture_id;
    }
}
