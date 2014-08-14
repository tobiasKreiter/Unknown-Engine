package com.engine.gui.component;

import com.badlogic.gdx.graphics.Color;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.main.GuiManager;
import com.engine.gui.screen.layer.ComponentLayer;

/**
 * Created by Felix on 22.07.2014.
 */
public class Button extends Label {

    /**
     * true when the Button is actually pressed
     */
    protected boolean pressed = false;

    /**
     * button color if the button is pressed
     */
    protected Color pressedColor = Color.valueOf("7E8289");

    public Button(int x, int y) {
        super(x, y);
    }

    public Button(String text, int x, int y) {
        super(x, y);
        this.setText(text);
    }

    @Override
    /**
     * render button
     */
    public void renderComponent() {
        if (pressed) {
            Graphics.drawText(getText(), getX() + 1, getY() + 1, pressedColor, font);
        } else {
            Graphics.drawText(getText(), getX() + 1, getY() + 1, foreground, font);
        }
    }

    /**
     * Size of the button adjusts to the {@link Label#text}
     */
    public void refreshComponentDimension() {
        setHeight(getTextHeight() + 1);
        setWidth(getTextWidth() + 1);
    }

    @Override
    /**
     * Is executed when the button is pressed
     */
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        pressed = true;
        return false;
    }


    @Override
    /**
     * Is executed when the button is no longer be pressed
     */
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        pressed = false;
        return false;
    }

    @Override
    public boolean onBlur() {
        pressed = false;
        return false;
    }

    public Color getPressedColor() {
        return pressedColor;
    }

    public void setPressedColor(Color pressedColor) {
        this.pressedColor = pressedColor;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
