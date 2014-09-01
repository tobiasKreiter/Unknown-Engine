package com.engine.gui.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.engine.gui.graphics.Graphics;

/**
 * Created by Felix on 07.08.2014.
 */
public class TextField extends Label {

    private static final String VALID_CHARS = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789^°!\"§$%&/()=?\\`´*+~#'/*-+-_.,;:><|";
    protected boolean courserVisible = false;

    protected boolean onFocus = false;

    protected int tickCount = 0;

    protected int lineHeight;

    protected int cursorX = 0;
    protected int cursorY = 0;
    protected int cursorCount = 0;
    protected Color courserColor = Color.valueOf("7E8289");
    protected final int COURSER_SPEED = 150;

    protected int wrappWidth = 200;

    protected int startWidth;
    protected int startHeight;

    protected int markStart = 0;
    protected int markX = 0;
    protected int markY = 0;
    protected Color markColor = Color.valueOf("7E8289");

    protected Color borderColor;

    protected int borderWidth;

    public TextField(int x, int y, int width, int height) {
        super(x, y);
        setWidth(width);
        setHeight(height);
        startWidth = width;
        startHeight = height;
        lineHeight = (int) getFont().getLineHeight();
    }


    /**
     * Das Textfeld wird samt Cursor, Markierung, Text und Hintergrund gezeichnet
     */
    @Override
    public void renderComponent() {
        background.render();
        if (markStart != cursorCount) {
            renderMark();
        }

        if (courserVisible && onFocus) {
            Graphics.drawLine(getX() + cursorX, getY() + cursorY, getX() + cursorX, getY() + cursorY + lineHeight, courserColor);
        }
        Graphics.drawWrappedText(getText(), 1 + getX(), getY(), wrappWidth, fontColor, font, BitmapFont.HAlignment.LEFT);
    }


    private void renderMark() {
        if (markStart < cursorCount) {
            if (markY == cursorY) {
                Graphics.drawFilledRect(getX() + markX, getY() + markY, cursorX - markX, lineHeight, markColor);
            } else {
                Graphics.drawFilledRect(getX() + markX, getY() + markY, wrappWidth - markX, lineHeight, markColor);
                int y = lineHeight;
                for (; markY + y < cursorY; y += lineHeight) {
                    Graphics.drawFilledRect(getX(), getY() + markY + y, wrappWidth, lineHeight, markColor);
                }
                Graphics.drawFilledRect(getX(), getY() + markY + y, cursorX, lineHeight, markColor);
            }
        } else {
            if (markY == cursorY) {
                Graphics.drawFilledRect(getX() + cursorX, getY() + cursorY, markX - cursorX, lineHeight, markColor);
            } else {
                Graphics.drawFilledRect(getX() + cursorX, getY() + cursorY, wrappWidth - cursorX, lineHeight, markColor);
                int y = lineHeight;
                for (; cursorY + y < markY; y += lineHeight) {
                    Graphics.drawFilledRect(getX(), getY() + cursorY + y, wrappWidth, lineHeight, markColor);
                }
                Graphics.drawFilledRect(getX(), getY() + cursorY + y, markX, lineHeight, markColor);
            }
        }
    }

    /**
     * verliert das TextFeld den Fokus, so hört der Courser auf zu blinken
     *
     * @return
     */
    @Override
    public boolean onBlur() {
        Gdx.input.setOnscreenKeyboardVisible(false);
        courserVisible = false;
        onFocus = false;
        tickCount = 0;
        return false;
    }

    /**
     * Lässt den Courser blinken
     */
    @Override
    public void tickComponent(float delta) {
        if (onFocus) {
            tickCount++;
            if ((tickCount / 60) * 1000 >= COURSER_SPEED / 2) {
                courserVisible = !courserVisible;
                tickCount = 0;
            }
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.input.setOnscreenKeyboardVisible(true);
        courserVisible = true;
        onFocus = true;
        cursorCount = getCursorPos(screenX, screenY);
        recalculateCursorPos();
        markStart = cursorCount;
        recalculateMarkPos();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        cursorCount = getCursorPos(screenX, screenY);
        recalculateCursorPos();
        return false;
    }

    /**
     * Alle Funktionen des Textfelds, die mit der Tastatur gesteuert werden, werden hier abgearbeitet
     *
     * @param keycode
     * @return
     */
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                left();
                break;
            case Input.Keys.RIGHT:
                right();
                break;
        }
        return false;
    }

    private void left() {
        if (-cursorCount < text.length()) {
            cursorCount--;
            markStart = cursorCount;
            recalculateCursorPos();
        }
    }

    private void right() {
        if (cursorCount < 0) {
            cursorCount++;
            markStart = cursorCount;
            recalculateCursorPos();
        }
    }

    private void backspace() {
        removeText(text.length() + cursorCount - 1);
        recalculateCursorPos();
    }

    @Override
    public boolean keyTyped(char character) {
        if (String.valueOf(character).equals("\b")) {//Backspace
            if (getText().length() + cursorCount > 0) {
                if (markStart == cursorCount) {
                    backspace();
                    markStart = cursorCount;
                }
            }
        }
        if (String.valueOf(character).equals("\u007F")) {//Delete
            if (cursorCount < 0) {
                if (markStart == cursorCount) {
                    right();
                    backspace();
                    markStart = cursorCount;
                }
            }
        }
        removeMarkedText();
        if (VALID_CHARS.contains(String.valueOf(character))) {
            addText(String.valueOf(character), cursorCount);
        }
        refreshComponentDimension();
        return false;
    }

    /**
     * Count ist immer negativ(oder 0) und entspricht der Courserposition.
     */
    private void addText(String text, int count) {
        if (count == 0) {
            setText(getText() + text);
        } else {
            setText(getText().substring(0, getText().length() + count) + text + getText().substring(count + getText().length(), getText().length()));
        }
        recalculateTextDim();
        recalculateCursorPos();
    }

    private void recalculateCursorPos() {
        int x = 0, y = 0;

        for (int i = 0; i + 1 <= text.length() + cursorCount; i++) {
            x += getFont().getBounds(getText(i, i + 1)).width;
            if (x > wrappWidth) {
                x = (int) getFont().getBounds(getText(i, i + 1)).width;
                y += lineHeight;
            }
        }
        cursorX = x;
        cursorY = y;
    }

    private void recalculateMarkPos() {
        int x = 0, y = 0;

        for (int i = 0; i + 1 <= text.length() + markStart; i++) {
            x += getFont().getBounds(getText(i, i + 1)).width;
            if (x > wrappWidth) {
                x = (int) getFont().getBounds(getText(i, i + 1)).width;
                y += lineHeight;
            }
        }
        markX = x;
        markY = y;
    }

    private void removeMarkedText() {
        if (markStart != cursorCount) {
            System.out.println((text.length() + markStart) + " : " + (text.length() + cursorCount));
            if (markStart < cursorCount) {
                removeText(text.length() + markStart, text.length() + cursorCount);
            } else {
                removeText(text.length() + cursorCount, text.length() + markStart);
                cursorCount = markStart;
                recalculateCursorPos();
            }
            markStart = cursorCount;
        }
    }

    private int getCursorPos(int screenX, int screenY) {
        int y = 0;
        int xOld = 0;
        int x = 0;
        for (int i = 0; i + 1 <= text.length(); i++) {
            int xNew = (int) getFont().getBounds(getText(i, i + 1)).width;
            int yNew = y + lineHeight;
            if (TextField.this.getX() + (x - xOld) <= screenX && screenX <= TextField.this.getX() + x + xNew / 2) {
                if (TextField.this.getY() + y <= screenY && screenY <= TextField.this.getY() + yNew) {
                    return i - text.length();
                }
            }
            if (x + xNew > wrappWidth) {
                xNew = (int) getFont().getBounds(getText(i, i + 1)).width;
                x = 0;
                xOld = 0;
                y += lineHeight;
                yNew += lineHeight;
            }
            if (TextField.this.getX() + (x - xOld) <= screenX && screenX <= TextField.this.getX() + x + xNew / 2) {
                if (TextField.this.getY() + y <= screenY && screenY <= TextField.this.getY() + yNew) {
                    return i - text.length();
                }
            }
            xOld = xNew / 2;
            x += xNew;
        }
        return 0;
    }

    /**
     * Die Textdimensionen werden neu berechnet
     */
    public void recalculateTextDim() {
        textWidth = (int) getFont().getWrappedBounds(text, wrappWidth).width;
        textHeight = (int) getFont().getWrappedBounds(text, wrappWidth).height;
    }

    /**
     * Die Groesse der Komponente wird in Abhängigkeit vom Text und Design gesetzt
     */
    public void refreshComponentDimension() {
        if (startWidth < getTextWidth()) {
            setWidth(getTextWidth());
        } else {
            setWidth(startWidth);
        }
        if (startHeight < getTextHeight()) {
            setHeight(getTextHeight());
        } else {
            setHeight(startHeight);
        }
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
        super.setWidth(width);
        this.width = width;
    }

    public void setHeight(int height) {
        super.setHeight(height);
        this.height = height;
    }
}
