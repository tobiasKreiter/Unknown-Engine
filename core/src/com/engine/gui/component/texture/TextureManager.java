package com.engine.gui.component.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;

/**
 * Created by Felix on 13.08.2014.
 */
public class TextureManager {
    /**
     * all loaded textures are stored here
     */
    private static ArrayList<GuiTexture> textures;

    /**
     * Create only one object of {@link com.engine.gui.component.texture.TextureManager}
     */
    private static TextureManager textureManager = new TextureManager();

    private TextureManager() {
        textures = new ArrayList<GuiTexture>();
        loadTexture(Gdx.files.internal("badlogic.jpg"), "badlogic");
    }

    /**
     * Set texture to list and therefore make it static
     *
     * @param texture
     * @return returns falls if a texture with same name is already added
     */
    public static boolean addTexture(GuiTexture texture) {
        for (int i = 0; i < textures.size(); i++) {
            if (textures.get(i).getTextureName().equals(texture.getTextureName())) {
                return false;
            }
        }
        textures.add(texture);
        return true;
    }

    /**
     * Returns whether the texture is loaded or not
     *
     * @param textureName
     * @return
     */
    public static boolean isTextureLoaded(String textureName) {
        for (int i = 0; i < textures.size(); i++) {
            if (textures.get(i).getTextureName().equals(textureName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove @param texture from list
     *
     * @return false if it wasn't possible to remove the texture
     */
    public static boolean removeTexture(GuiTexture texture) {
        return TextureManager.textures.remove(texture);
    }

    /**
     * Returns the texture with textureName = {@param textureName}
     *
     * @return Returns null if there is no texture with the name {@param textureName}
     */
    public static GuiTexture getTexture(String textureName) {
        for (int i = 0; i < textures.size(); i++) {
            if (textures.get(i).getTextureName().equals(textureName)) {
                return textures.get(i);
            }
        }
        return null;
    }

    /**
     * Load and add the texture with location = {@param textureFile} and name = {@param textureName}
     *
     * @return
     */
    public static boolean loadTexture(FileHandle textureFile, String textureName) {
        return addTexture(new GuiTexture(textureFile, textureName));
    }

    /**
     * All textures will be disposed
     */
    public static void disposeAll() {
        for (int i = 0; i < textures.size(); i++) {
            textures.get(i).getTexture().dispose();
        }
    }
}
