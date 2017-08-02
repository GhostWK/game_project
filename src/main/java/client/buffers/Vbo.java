package client.buffers;


import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
/**
 * Created by USER on 02.06.2017.
 */


public class Vbo {

    private final int vboId;
    /**
     * GL15.GL_ELEMENT_ARRAY_BUFFER
     * GL15.GL_ARRAY_BUFFER
     */
    private final int type;
    /**
     * GL15.GL_STATIC_DRAW
     * GL15.GL_DYNAMIC_DRAW
     * GL15.GL_STREAM_DRAW
     */
    private final int drawType;

    public Vbo(int vboId, int type, int drawType){
        this.vboId = vboId;
        this.type = type;
        this.drawType = drawType;
    }
    public static Vbo create(int type, int drawType){
        int id = GL15.glGenBuffers();
        return new Vbo(id, type, drawType);
    }

    public void bind(){
        GL15.glBindBuffer(type, vboId);
    }
    public void unbind(){
        GL15.glBindBuffer(type, 0);
    }
    public void storeData(float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        storeData(buffer);
    }
    public void storeData(int[] data){
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        storeData(buffer);
    }
    public void storeData(IntBuffer data){
        GL15.glBufferData(type, data, drawType);
    }
    public void storeData(FloatBuffer data){
        GL15.glBufferData(type, data, drawType);
    }
    public void delete(){
        GL15.glDeleteBuffers(vboId);
    }

    public int getVboId() {
        return vboId;
    }
    public int getType() {
        return type;
    }

}

