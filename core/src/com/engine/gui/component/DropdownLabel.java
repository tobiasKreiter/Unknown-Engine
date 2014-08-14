package com.engine.gui.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.engine.gui.graphics.Graphics;

/**
 * Created by Hannes on 07.08.2014.
 */
public class DropdownLabel extends Label {

    private DropdownMenu parentMenu;

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
        Graphics.drawFilledRect(getX(), getY(), getWidth(), getHeight(), background);
        super.renderComponent();
    }
}
