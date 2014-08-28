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


    protected Color borderColor = Color.valueOf("7E8289");

    protected int borderWidth = 2;

    protected int alignmentH = LEFT;
    protected int alignmentV = BOTTOM;

    public static final int CENTER = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int TOP = 3;
    public static final int BOTTOM = 4;

    private int moveX = 0;
    private int moveY = 0;

    protected boolean sizeSet = false;

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
        renderBorder();
        if (pressed) {
            Graphics.drawText(getText(), getX() + moveX, getY() + moveY, pressedColor, font);
        } else {
            Graphics.drawText(getText(), getX() + moveX, getY() + moveY, foreground, font);
        }
    }

    private void renderBorder() {
        Graphics.drawFilledRect(x, y, getWidth(), borderWidth, borderColor);//Top
        Graphics.drawFilledRect(x, y, borderWidth, getHeight(), borderColor);//Left
        Graphics.drawFilledRect(x + getWidth() - borderWidth, y, borderWidth, getHeight(), borderColor);//Right
        Graphics.drawFilledRect(x, y + getHeight() - borderWidth, getWidth(), borderWidth, borderColor);//Bottom
    }

    /**
     * Size of the button adjusts to the {@link Label#text}
     */
    public void refreshComponentDimension() {
        if (!sizeSet) {
            setHeight(getTextHeight() + 1);
            setWidth(getTextWidth() + 1);
        }
    }

    public void refreshAlignment(){
        switch(alignmentH){
            case CENTER:
                moveX = (getWidth() - getTextWidth())/2;
                break;
            case LEFT:
                moveX = 0;
                break;
            case RIGHT:
                moveX = getWidth()-getTextWidth();
                break;
        }

        switch(alignmentV){
            case CENTER:
                moveY = (getHeight() - getTextHeight())/2;
                break;
            case TOP:
                moveY = 0;
                break;
            case BOTTOM:
                moveY = getHeight()-getTextHeight();
                break;
        }
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
        if (width >= textWidth) {
            this.width = width;
            sizeSet = true;
            refreshAlignment();
        }
    }

    public void setHeight(int height) {
        if (height >= textHeight) {
            this.height = height;
            sizeSet = true;
            refreshAlignment();
        }
    }

    /**
     * text is changed and text and component dimension is recalculated
     *
     * @param text new text
     */
    public void setText(String text) {
        this.text = text;
        recalculateTextDim();
        refreshComponentDimension();
        refreshAlignment();
    }
}
