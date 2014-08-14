package com.engine.gui.component;

import com.badlogic.gdx.InputProcessor;
import com.engine.gui.screen.layer.ComponentLayer;

/**
 * Created by Felix on 23.07.2014.
 */
public interface InputListener extends InputProcessor {
    /**
     * Is executed when the focus changed to another object.
     */
    public boolean onBlur();

    /**
     * Is executed when the window size changed
     *
     * @param newWidth  new width of window
     * @param newHeight new height of window
     */
    public boolean onResize(int newWidth, int newHeight);

    /**
     * Is executed when the size of a container changed.
     *
     * @param width indicates if the width changed. If not then the height has changed.
     */
    public boolean onSizeChanged(boolean width);

    /**
     * Component was added to another Component @param parent
     * @return
     */
    public boolean added(Component parent,ComponentLayer parentLayer);

    /**
     * Component was removed from another Component @param parent
     * @return
     */
    public boolean removed(Component parent,ComponentLayer parentLayer);
}
