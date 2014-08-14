package com.engine.gui.component.texture;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Felix on 13.08.2014.
 */
public class GuiTexture extends TextureRegion {
    /**
     * name of texture to find this texture in {@link com.engine.gui.component.texture.TextureManager}
     */
    private String textureName;

    public GuiTexture(Texture texture, String textureName) {
        super(texture);
        this.textureName = textureName;
        this.flip(false, true);
    }

    public GuiTexture(FileHandle textureFile, String textureName) {
        super(new Texture(textureFile));
        this.textureName = textureName;
        this.flip(false, true);
    }

    public String getTextureName() {
        return textureName;
    }

    /**
     * @param textureName set the name
     */
    public void setTextureName(String textureName) {
        this.textureName = textureName;
    }
}
