package com.engine.gui.component;


/**
 * Created by Felix on 22.07.2014.
 */
public class ToggleButton extends Button {

    public ToggleButton(String text, int x, int y) {
        super(text, x, y, ComponentType.TOGGLEBUTTON);
    }

    /**
     * {@link #setPressed(boolean true)} if toggle button is selected
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!isPressed()) {
            setPressed(true);
        }
        return false;
    }
    /**
     * {@link #setPressed(boolean false)} if toggle button is unselected
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (isPressed()) {
            setPressed(false);
        }
        return false;
    }

}
