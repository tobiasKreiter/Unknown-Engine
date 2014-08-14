package com.engine.gui.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.main.GuiManager;

/**
 * Created by Felix on 23.07.2014.
 */
public class TextBox extends Label {

    /**
     * is true if TextBox size adjusts to text size
     */
    protected boolean resizeTextField = false;

    /**
     * is true if TextBos has focus
     */
    private boolean onFocus = false;

    /**
     * Cursor position in text
     */
    protected int cursorPos = 0;

    /**
     * Distance from cursor to front of text in pixels
     */
    private int distanceToFront = 0;

    /**
     * x coordinate of cursor
     */
    private int cursorX = 0;

    /**
     * cursor color
     */
    protected Color cursorColor = Color.BLACK;

    /**
     * blink speed  of cursor in ms
     */
    protected final int CURSOR_SPEED = 150;

    /**
     * is true if cursor is visible
     */
    private boolean cursorVisible = false;

    /**
     * counter variable for cursor
     */
    private int tickCount = 0;

    /**
     * Distance from start marking to cursor in pixels
     */
    private int markUntil;

    /**
     * marking color
     */
    protected Color markColor = Color.LIGHT_GRAY;

    /**
     * count of letters which are marked
     */
    private int markCount;

    /**
     * is true if ctrl key is pressed
     */
    protected boolean ctrlPressed = false;


    /**
     * moves text in x direction
     */
    private int moveX = 0;

    public TextBox(int x, int y) {
        super(x, y);
        this.setWidth(200);
        replaceCourser();
    }

    public TextBox(int x, int y, int width) {
        super(x, y);
        this.setWidth(width);
        replaceCourser();
    }

    /**
     * render TextField
     */
    @Override
    public void renderComponent() {
        Graphics.limitDrawing(getX(), getY(), getWidth(), getHeight());
        Graphics.drawFilledRect(getX(), getY(), getWidth(), getHeight(), background);
        if (markUntil != 0) {
            if (markUntil < 0) {
                Graphics.drawFilledRect(getX() + markUntil + cursorX, getY(), -markUntil, getHeight(), getMarkColor());
            } else {
                Graphics.drawFilledRect(getX() + cursorX, getY(), markUntil, getHeight(), getMarkColor());
            }
        }
        if (cursorVisible && onFocus) {
            Graphics.drawLine(getX() + cursorX + 1, getY(), getX() + cursorX + 1, getY() + getHeight(), getCursorColor());
        }
        Graphics.drawText(getText(), 1 + getX() - moveX, getY(), foreground, font);
        Graphics.limitEnd();
    }

    /**
     *handle keyDown event
     *
     * @param keycode
     * @return
     */
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                markUntil = 0;
                left();
                break;

            case Input.Keys.RIGHT:
                markUntil = 0;
                right();
                break;

            case Input.Keys.CONTROL_LEFT:
                ctrlPressed = true;
                break;
            case Input.Keys.CONTROL_RIGHT:
                ctrlPressed = true;
                break;

            case Input.Keys.C:
                if (isCtrlPressed()) {
                    if (markUntil != 0) {
                        if (markCount > getCursorPos()) {
                            GuiManager.clipboard = getText(getCursorPos() + text.length(), markCount + text.length());
                        } else {
                            GuiManager.clipboard = getText(markCount + text.length(), getCursorPos() + text.length());
                        }
                    }
                }
                break;
            case Input.Keys.X:
                if (isCtrlPressed()) {
                    if (markUntil != 0) {
                        if (markCount > getCursorPos()) {
                            GuiManager.clipboard = getText(getCursorPos() + text.length(), markCount + text.length());
                        } else {
                            GuiManager.clipboard = getText(markCount + text.length(), getCursorPos() + text.length());
                        }
                        removeMarkedText();
                    }
                }
                break;
            case Input.Keys.V:
                if (GuiManager.clipboard != null) {
                    if (markUntil != 0) {
                        removeMarkedText();
                    }
                    addText(GuiManager.clipboard, getCursorPos());
                    replaceCourser();
                }
                break;
            case Input.Keys.A:
                if (isCtrlPressed()) {
                    if (getCursorPos() != 0) {
                        do {
                            right();
                        } while (getCursorPos() != 0);
                        replaceCourser();
                    }
                    markUntil = getDistanceTo(-getText().length());
                    markCount = -getText().length();
                }
                break;
        }
        return false;
    }

    /**
     * if ctrl key is released {@link #ctrlPressed} is set to false
     *
     * @param keycode
     * @return
     */
    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.CONTROL_LEFT:
                ctrlPressed = false;
                break;
            case Input.Keys.CONTROL_RIGHT:
                ctrlPressed = false;
                break;
        }
        return false;
    }

    /**
     * keyTyped event is handled
     *
     * @param character
     * @return
     */
    @Override
    public boolean keyTyped(char character) {
        String abc = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789^°!\"§$%&/()=?\\`´*+~#'/*-+-_.,;:><|";
        if (abc.contains(String.valueOf(character)) && !isCtrlPressed()) {
            if (markUntil != 0) {
                removeMarkedText();
            }
            addText(String.valueOf(character), getCursorPos());
            replaceCourser();
        }
        if (String.valueOf(character).equals("\b")) {//Backspace
            if (markUntil != 0) {
                removeMarkedText();
            } else {
                if (getText().length() + getCursorPos() > 0) {
                    backspace();
                }
            }
        }
        if (String.valueOf(character).equals("\u007F")) {//Delete
            if (markUntil != 0) {
                removeMarkedText();
            } else {
                if (getCursorPos() < 0) {
                    right();
                    backspace();
                }
            }
        }
        return false;
    }

    /**
     * set cursor to click position if its possible
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        markUntil = 0;
        markCount = getCursorPosition(screenX);
        Gdx.input.setOnscreenKeyboardVisible(true);
        cursorVisible = true;
        onFocus = true;
        moveCourser(screenX);
        return false;
    }

    /**
     * set marking
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @return
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        markUntil += -1 * getDistanceTo(getCursorPosition(screenX));
        moveCourser(screenX);
        return false;
    }

    /**
     * if TextField lose its focus cursor stops blinking
     *
     * @return
     */
    @Override
    public boolean onBlur() {
        Gdx.input.setOnscreenKeyboardVisible(false);
        cursorVisible = false;
        onFocus = false;
        tickCount = 0;
        return false;
    }

    /**
     * component dimension will be refreshed
     */
    public void refreshComponentDimension() {
        if (isResizeTextField()) {
            setWidth(getTextWidth() + 2);
        }
        setCompDim(getX(), getY(), getWidth(), getHeight());
    }


    /**
     * calculate moving of text in x direction
     */
    private void calculateMoveX() {
        if (getCursorPos() == 0) {
            if (getTextWidth() > getWidth()) {
                moveX = getTextWidth() - getWidth();
                if (distanceToFront > getWidth()) {
                    moveX -= distanceToFront - getWidth();
                }
            } else {
                moveX = 0;
            }
            replaceCourser();
        } else if (cursorX >= getX() + getWidth() - 2) {
            if (getCursorPos() == 0) {
                if (getTextWidth() > getWidth()) {
                    moveX = getTextWidth() - (getWidth() - 2);
                    if (distanceToFront > getWidth()) {
                        moveX -= (distanceToFront) - getWidth();
                    }
                } else {
                    moveX = 0;
                }
            }
            replaceCourser();
        }

    }

    /**
     * calculate cursor x coordinate
     */
    private void replaceCourser() {
        cursorX = getTextWidth() - moveX - distanceToFront;
        if (cursorX < 2) {
            moveX -= -cursorX;
            cursorX = 2;
        } else if (cursorX > getWidth() - 2) {
            moveX += cursorX - getWidth();
            cursorX = getWidth() - 2;
        }
    }


    /**
     * moves cursor to left
     */
    private void left() {
        if (getCursorPos() > -getText().length()) {
            cursorPos = getCursorPos() - 1;
            distanceToFront += getFont().getBounds(getText().substring(getText().length() + getCursorPos(), getCursorPos() + 1 + getText().length())).width;
        }
        calculateMoveX();
        replaceCourser();
    }

    /**
     * moves cursor to right
     */
    private void right() {
        if (getCursorPos() < 0) {
            distanceToFront -= getFont().getBounds(getText().substring(getText().length() + getCursorPos(), getCursorPos() + 1 + getText().length())).width;
            cursorPos = getCursorPos() + 1;
        }
        calculateMoveX();
        replaceCourser();
    }

    /**
     * moves cursor to {@param screenX}
     */
    private void moveCourser(int screenX) {
        int newPos = getCursorPosition(screenX);
        do {
            if (getCursorPos() > newPos) {
                left();
            } else if (getCursorPos() < newPos) {
                right();
            }
        } while (getCursorPos() != newPos);
        replaceCourser();
    }

    /**
     * letter before the cursor is deleted
     */
    private void backspace() {
        removeText(text.length() + getCursorPos() - 1);
        recalculateTextDim();
        calculateMoveX();
        replaceCourser();
    }

    /**
     * attach text at position {@param count}
     */
    private void addText(String text, int count) {
        if (count == 0) {
            setText(getText() + text);
        } else {
            setText(getText().substring(0, getText().length() + count) + text + getText().substring(count + getText().length(), getText().length()));
        }
        recalculateTextDim();
        calculateMoveX();
    }

    /**
     * blink speed is handled
     */
    @Override
    public void tickComponent(float delta) {
        if (onFocus) {
            tickCount++;
            if ((tickCount / 60) * 1000 >= getCURSOR_SPEED() / 2) {
                cursorVisible = !cursorVisible;
                tickCount = 0;
            }
        }
    }

    /**
     * converts mouse position in pixels to cursorPosition
     */
    private int getCursorPosition(int mouseX) {
        int distance = distanceToFront + ((cursorX + getX()) - mouseX);
        int textLength = 0;
        int letterWidthOld = 0;
        if (distance <= 0) {
            return 0;
        } else {
            for (int i = 0; i <= getText().length(); i++) {
                if (i < getText().length()) {
                    int letterWidth = (int) getFont().getBounds(getText().substring(getText().length() - 1 - i, getText().length() - i)).width;
                    if (distance >= textLength - letterWidthOld / 2 && distance <= (letterWidth / 2) + textLength) {
                        return -i;
                    } else {
                        textLength += letterWidth;
                        letterWidthOld = letterWidth;
                    }
                } else {
                    textLength = (int) getFont().getBounds(getText()).width;
                    if (distance >= textLength - letterWidthOld / 2 && distance <= textLength) {
                        return -i;
                    }
                }
            }
        }
        return getCursorPos();
    }

    /**
     * returns distance from {@param pos} to {@link #cursorPos} in pixels
     *
     * @param pos
     * @return
     */
    private int getDistanceTo(int pos) {
        int course = getCursorPos();
        int dis = 0;
        if (pos < course) {
            do {
                dis += getFont().getBounds(getText().substring(getText().length() + course - 1, course + getText().length())).width;
                course--;
            } while (course != pos);
        }
        if (pos > course) {
            do {
                dis -= getFont().getBounds(getText().substring(getText().length() + course, course + 1 + getText().length())).width;
                course++;
            } while (course != pos);
        }
        return dis * (-1);
    }

    /**
     * removes marked text
     */
    private void removeMarkedText() {
        if (markCount > getCursorPos()) {
            int diff = markCount - getCursorPos();
            for (int i = 0; i < diff; i++) {
                right();
                backspace();
            }
        } else {
            int diff = getCursorPos() - markCount;
            for (int i = 0; i < diff; i++) {
                backspace();
            }
        }
        markUntil = 0;
        recalculateTextDim();
        calculateMoveX();
        replaceCourser();
    }


    public boolean isResizeTextField() {
        return resizeTextField;
    }

    public int getCursorPos() {
        return cursorPos;
    }

    public Color getCursorColor() {
        return cursorColor;
    }

    public int getCURSOR_SPEED() {
        return CURSOR_SPEED;
    }

    public Color getMarkColor() {
        return markColor;
    }

    public boolean isCtrlPressed() {
        return ctrlPressed;
    }

    public void setResizeTextField(boolean resizeTextField) {
        this.resizeTextField = resizeTextField;
    }

    public void setCursorColor(Color cursorColor) {
        this.cursorColor = cursorColor;
    }

    public void setMarkColor(Color markColor) {
        this.markColor = markColor;
    }
}
