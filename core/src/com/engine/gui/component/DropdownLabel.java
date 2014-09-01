package com.engine.gui.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.main.GuiManager;

/**
 * Created by Hannes on 07.08.2014.
 */
public class DropdownLabel extends Label {

    private DropdownMenu parentMenu;

    protected int alignment = CENTER;

    public static final int CENTER = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    public DropdownLabel(String text, int x, int y, DropdownMenu parentMenu) {
        super(text, x, y, ComponentType.DROPDOWN_LABEL);
        this.parentMenu = parentMenu;
    }

    public DropdownLabel(String text, int x, int y, BitmapFont font, DropdownMenu parentMenu) {
        super(text, x, y, font, ComponentType.DROPDOWN_LABEL);
        this.parentMenu = parentMenu;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        parentMenu.labelPressed(this);
        System.out.println("Text: " + getText());
        return false;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
