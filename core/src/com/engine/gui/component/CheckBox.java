package com.engine.gui.component;

import com.badlogic.gdx.graphics.Color;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.main.GuiManager;

/**
 * Created by Felix on 22.07.2014.
 */
public class CheckBox extends Label {
    protected boolean checked = false;

    protected Color borderColor;

    protected int borderWidth;

    public CheckBox(int x, int y) {
        super(x, y);
    }

    public CheckBox(String text, int x, int y) {
        super(x, y);
        this.setText(text);
    }

    @Override
    /**
     * render CheckBox
     */
    public void renderComponent() {
        background.render();
        Graphics.drawRect(getX() + 1, getY() + 1, getHeight(), getHeight(), foreground);
        if (isChecked()) {
            Graphics.drawLine(getX() + 1, getY() + 1, getHeight(), getHeight(), foreground);
            Graphics.drawLine(getX() + 1, getY() + getHeight(), getHeight(), 1, foreground);
        }
        Graphics.drawText(getText(), getX() + getHeight() + 2, getY() + 1, foreground, font);
    }

    /**
     * CheckBox will be checked and unchecked
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        setChecked(!isChecked());
        return false;
    }

    /**
     * Size of the checkbox adjusts to the {@link Label#text}
     */
    public void refreshComponentDimension() {
        setHeight(getTextHeight() + 1);
        setWidth(getTextWidth() + getHeight() + 2);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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
