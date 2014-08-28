package com.engine.gui.component;

import com.badlogic.gdx.graphics.Color;
import com.engine.gui.component.container.window.Window;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.main.GuiManager;

/**
 * Created by Felix on 27.08.2014.
 */
public class Background {

    private Component comp;

    public void render() {
        if (comp != null) {
            Graphics.drawFilledRect(comp.getX(), comp.getY(), comp.getWidth(), comp.getHeight(), Color.valueOf("00A651"));
        }
    }

    public void setComponent(Component comp) {
        this.comp = comp;
    }
}
