package client.buffers;


import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 02.06.2017.
 */
public class Vao {

    private static final int BYTES_PER_FLOAT = 4;
    private static final int BYTES_PER_INT = 4;
    private final int id;
    private List<Vbo> vbos = new ArrayList<>();
    private Vbo indexVbo;
    private int indexCount;


    public static Vao create(){
        return new Vao(GL30.glGenVertexArrays());
    }
    public Vao(int id) {
        this.id = id;
    }
    public void createIndexBuffer(int[] indices, int drawType){
        this.indexVbo = Vbo.create(GL15.GL_ELEMENT_ARRAY_BUFFER, drawType);
        indexVbo.bind();
        indexVbo.storeData(indices);
        this.indexCount = indices.length;
    }
    public void createAttribute(int attribute, float[] data, int attrSize, int drawType){
        Vbo dataVbo = Vbo.create(GL15.GL_ARRAY_BUFFER, drawType);
        dataVbo.bind();
        dataVbo.storeData(data);
        GL20.glVertexAttribPointer(attribute, attrSize, GL11.GL_FLOAT, false, attrSize * BYTES_PER_FLOAT, 0);
        dataVbo.unbind();
        vbos.add(dataVbo);
    }
    public void createIntAttribute(int attribute, int[] data, int attrSize, int drawType){
        Vbo dataVbo = Vbo.create(GL15.GL_ARRAY_BUFFER, drawType);
        dataVbo.bind();
        dataVbo.storeData(data);
        GL30.glVertexAttribIPointer(attribute, attrSize, GL11.GL_INT, attrSize * BYTES_PER_INT, 0);
        dataVbo.unbind();
        vbos.add(dataVbo);
    }
    public void bind(int... attributes){
        bind();
        for (int i : attributes) {
            GL20.glEnableVertexAttribArray(i);
        }
    }
    public void unbind(int... attributes){
        for (int i : attributes) {
            GL20.glDisableVertexAttribArray(i);
        }
        unbind();
    }
    public void delete() {
        GL30.glDeleteVertexArrays(id);
        for(Vbo vbo : vbos){
            vbo.delete();
        }
        indexVbo.delete();
    }
    public void bind(){
        GL30.glBindVertexArray(id);
    }
    public void unbind(){
        GL30.glBindVertexArray(0);
    }
    public void add(Vbo vbo){
        vbos.add(vbo);
    }


    public int getId() {
        return id;
    }
    public List<Vbo> getVbos() {
        return vbos;
    }
    public Vbo getIndexVbo() {
        return indexVbo;
    }
    public int getIndexCount() {
        return indexCount;
    }

    public void setVbos(List<Vbo> vbos) {
        this.vbos = vbos;
    }
    public void setIndexVbo(Vbo indexVbo) {
        this.indexVbo = indexVbo;
    }
    public void setIndexCount(int indexCount) {
        this.indexCount = indexCount;
    }
}

