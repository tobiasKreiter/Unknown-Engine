package com.engine.gui.component;

import com.badlogic.gdx.graphics.Color;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.main.GuiManager;
import com.engine.gui.screen.layer.ComponentLayer;

import javax.swing.border.Border;

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


    protected Color borderColor;

    protected int borderWidth;

    protected int alignment = CENTER;

    public static final int CENTER = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    public Button(int x, int y) {
        super(x, y, ComponentType.BUTTON);
    }

    public Button(String text, int x, int y) {
        super(x, y, ComponentType.BUTTON);
        this.setText(text);
    }

    public Button(String text, int x, int y, ComponentType type) {
        super(x, y, type);
        this.setText(text);
    }

    @Override
    /**
     * render button
     */
    public void renderComponent() {
        background.render();
        if (pressed) {
            Graphics.drawText(getText(), getX() + 1, getY() + 1, pressedColor, font);
        } else {
            Graphics.drawText(getText(), getX() + 1, getY() + 1, foreground, font);
        }
    }

    private void renderBorder(){
        Graphics.drawFilledRect(x,y,getWidth(),borderWidth);//Top
        Graphics.drawFilledRect(x,y,borderWidth,getHeight());//Left
        Graphics.drawFilledRect(x+getWidth()-borderWidth,y,borderWidth,getHeight());//Right
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


    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
