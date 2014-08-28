package com.engine.gui.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engine.gui.component.container.window.Window;
import com.engine.gui.component.texture.GuiTexture;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.main.GuiManager;

/**
 * Created by Felix on 27.08.2014.
 */
public class Background {

    private Component comp;

    private Color backgroundColor = null;
    private TextureRegion backgroundImage = null;
    private GuiTexture texture = null;

    public Background() {
        backgroundColor = Color.valueOf("00A651");
    }

    public Background(Color backgroundColor, TextureRegion backgroundImage, GuiTexture texture) {
        this.backgroundColor = backgroundColor;
        this.backgroundImage = backgroundImage;
        this.texture = texture;
    }

    public void render() {
        if (comp != null) {
            if (backgroundColor != null) {
                Graphics.drawFilledRect(comp.getX(), comp.getY(), comp.getWidth(), comp.getHeight(), Color.valueOf("00A651"));
            }
            if (backgroundImage != null) {
                Graphics.drawImage(backgroundImage, comp.getX(), comp.getY(), comp.getWidth(), comp.getHeight());
            }
            if (texture != null) {
                Graphics.drawTexture(texture, comp.getX(), comp.getY(), comp.getWidth(), comp.getHeight());
            }
        }
    }

    public void setComponent(Component comp) {
        this.comp = comp;
    }
}
