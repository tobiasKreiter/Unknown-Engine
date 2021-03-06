package com.engine.gui.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.main.GuiManager;

/**
 * Created by Felix on 22.07.2014.
 */
public class Label extends Component {

    /**
     * text of this Label
     */
    protected String text = "";

    /**
     * width of the rendered text
     */
    protected int textWidth = 0;
    /**
     * height of the rendered text
     */
    protected int textHeight = 0;

    /**
     * text font
     */
    protected BitmapFont font = GuiManager.normalFont;

    /**
     * fontColor color
     */
    protected Color fontColor;

    protected Background background;

    public Label() {
        super(ComponentType.LABEL);
    }

    public Label(int x, int y) {
        super(x, y, ComponentType.LABEL);
        this.height = (int) getFont().getBounds(" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").height + (int) getFont().getXHeight() / 2;
    }

    public Label(int x, int y, ComponentType type) {
        super(x, y, type);
        this.height = (int) getFont().getBounds(" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").height + (int) getFont().getXHeight() / 2;
    }

    public Label(String text, int x, int y) {
        super(x, y, ComponentType.LABEL);
        this.setText(text);
        refreshComponentDimension();
        this.height = (int) getFont().getBounds(" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").height + (int) getFont().getXHeight() / 2;
    }

    public Label(String text, int x, int y, ComponentType type) {
        super(x, y, type);
        this.setText(text);
        refreshComponentDimension();
        this.height = (int) getFont().getBounds(" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").height + (int) getFont().getXHeight() / 2;
    }

    public Label(String text, int x, int y, BitmapFont font) {
        super(x, y, ComponentType.LABEL);
        this.setText(text);
        refreshComponentDimension();
        setFont(font);
        this.height = (int) getFont().getBounds(" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").height + (int) getFont().getXHeight() / 2;
    }

    public Label(String text, int x, int y, BitmapFont font, ComponentType type) {
        super(x, y, type);
        this.setText(text);
        refreshComponentDimension();
        setFont(font);
        this.height = (int) getFont().getBounds(" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").height + (int) getFont().getXHeight() / 2;
    }

    public String getText() {
        return text;
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
    }

    /**
     * @param text will be attached to {@link #text}
     */
    public void attachText(String text) {
        this.text += text;
        recalculateTextDim();
    }

    /**
     * remove letter with position {@param count} in {@link #text}
     */
    public void removeText(int count) {
        if (count + 1 < text.length()) {
            text = text.substring(0, count) + text.substring(count + 1, text.length());
        } else {
            text = text.substring(0, count);
        }
    }

    /**
     * Remove letters between {@param countStart} and {@param countEnd}
     */
    public void removeText(int countStart, int countEnd) {
        text = text.substring(0, countStart) + text.substring(countEnd, text.length());
    }

    public String getText(int countStart, int countEnd) {
        return text.substring(countStart, countEnd);
    }

    /**
     * Size of the label adjusts to the {@link #text}
     */
    public void recalculateTextDim() {
        textWidth = (int) getFont().getBounds(text).width;
        textHeight = (int) getFont().getBounds(" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").height + (int) getFont().getXHeight() / 2;
    }

    public int getTextWidth() {
        return textWidth;
    }

    public int getTextHeight() {
        return textHeight;
    }


    /**
     * render component and execute {@link com.engine.gui.animation.AnimationManager#tickEnd()}
     */
    public void render() {
        renderComponent();
        if (animationManager != null) {
            animationManager.tickEnd();
        }
    }

    /**
     * render label
     */
    @Override
    public void renderComponent() {
        Graphics.drawText(getText(), getX(), getY(), fontColor, getFont());
    }

    /**
     * component dimension will be refreshed
     */
    public void refreshComponentDimension() {
        height = getTextHeight();
        width = getTextWidth();
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        if(getComponentType()==ComponentType.LABEL) {
            System.out.println("Old Color: "+this.fontColor);
        }
        this.fontColor = fontColor;
        if(getComponentType()==ComponentType.LABEL) {
            System.out.println("New Color: "+this.fontColor);
        }
    }

    public void setWidth(int width) {

    }

    public void setHeight(int height) {

    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
        background.setComponent(this);
    }
}
