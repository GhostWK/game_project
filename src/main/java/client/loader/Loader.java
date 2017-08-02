package client.loader;

import client.buffers.Vao;
import client.buffers.Vbo;
import client.utils.MyFile;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 03.06.2017.
 */
public class Loader {
    private List<Vao> vaos = new ArrayList<>();
    private List<Integer> textures = new ArrayList<>();

    public Vao loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices){
        Vao vao = Vao.create();
        vao.bind();
        bindIndicesVbo(vao, indices);
        bindDataVbo(vao, 0, 3, positions, GL15.GL_STATIC_DRAW);
        bindDataVbo(vao, 1, 2, textureCoords, GL15.GL_STATIC_DRAW);
        bindDataVbo(vao, 2, 3, normals, GL15.GL_STATIC_DRAW);
        vao.unbind();
        vaos.add(vao);
        vao.setIndexCount(indices.length);
        return vao;
    }
    public int loadTexture(MyFile myFile){


        Texture texture = null;
        try {
            texture = TextureLoader.getTexture("PNG", myFile.getInputStream());
            GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, 0);

            if(GLContext.getCapabilities().GL_EXT_texture_filter_anisotropic){
                float amount = Math.min(4f, GL11.glGetFloat(EXTTextureFilterAnisotropic.GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT));
                GL11.glTexParameterf(GL11.GL_TEXTURE_2D, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, amount);
            }else {
                System.err.println("Anisotropic filter is not supported");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int textureID = texture.getTextureID();
        textures.add(textureID);

        return textureID;
    }


    private void bindIndicesVbo(Vao vao, int[] indices){
        Vbo indVbo = Vbo.create(GL15.GL_ELEMENT_ARRAY_BUFFER, GL15.GL_STATIC_DRAW);
        indVbo.bind();
        indVbo.storeData(indices);
        //indVbo.unbind();
        vao.setIndexVbo(indVbo);
    }
    private void bindDataVbo(Vao vao, int attribute, int coordinateSize, float[] data, int drawType){
        Vbo dataVbo = Vbo.create(GL15.GL_ARRAY_BUFFER, drawType);
        dataVbo.bind();
        dataVbo.storeData(data);
        GL20.glVertexAttribPointer(attribute,coordinateSize,GL11.GL_FLOAT,false,0,0);
        dataVbo.unbind();
        vao.add(dataVbo);
    }



    public void cleanUp(){
        for(Vao x : vaos)x.delete();
        for(int x : textures){
            GL11.glDeleteTextures(x);
        }
    }

    public List<Vao> getVaos() {
        return vaos;
    }
}
