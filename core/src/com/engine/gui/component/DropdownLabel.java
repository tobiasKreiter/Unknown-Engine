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

    protected Color borderColor;

    protected int alignment = CENTER;

    public static final int CENTER = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    protected int borderWidth;

    public DropdownLabel(String text, int x, int y, DropdownMenu parentMenu) {
        super(text, x, y);
        this.parentMenu = parentMenu;
    }

    public DropdownLabel(String text, int x, int y, BitmapFont font, DropdownMenu parentMenu) {
        super(text, x, y, font);
        this.parentMenu = parentMenu;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        parentMenu.labelPressed(this);
        System.out.println("Text: " + getText());
        return false;
    }

    @Override
    public void renderComponent() {
        background.render();
        super.renderComponent();
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }
}
