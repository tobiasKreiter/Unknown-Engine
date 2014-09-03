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

    /**
     * parent component
     */
    private Component comp;

    /**
     * background color
     */
    private Color backgroundColor = null;

    private GuiTexture texture = null;

    public Background(Color backgroundColor, GuiTexture texture) {
        this.backgroundColor = backgroundColor;
        this.texture = texture;
    }

    public void render() {
        if (comp != null) {
            if (backgroundColor != null) {
                Graphics.drawFilledRect(comp.getX(), comp.getY(), comp.getWidth(), comp.getHeight(), backgroundColor);
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
