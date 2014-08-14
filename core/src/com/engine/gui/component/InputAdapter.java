package com.engine.gui.component;


import com.engine.gui.screen.layer.ComponentLayer;

/**
 * Created by Felix on 02.08.2014.
 */
public class InputAdapter implements InputListener{
    @Override
    public boolean onBlur() {
        return false;
    }

    @Override
    public boolean onResize(int newWidth, int newHeight) {
        return false;
    }

    @Override
    public boolean onSizeChanged(boolean width) {
        return false;
    }

    @Override
    public boolean added(Component parent, ComponentLayer parentLayer) {
        return false;
    }

    @Override
    public boolean removed(Component parent, ComponentLayer parentLayer) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
