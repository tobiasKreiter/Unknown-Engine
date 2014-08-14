package com.engine.gui.screen.layer;

import com.engine.gui.component.Component;
import com.engine.gui.component.InputListener;
import com.engine.gui.screen.Frame;

/**
 * Created by tobias on 22.07.2014.
 */
public abstract class Layer implements InputListener {

    /**
     * For removing or moving itself in hirachie.
     */
    protected Frame parentFrame;

    public Layer(Frame parentFrame) {
        this.parentFrame = parentFrame;
    }

    /**
     * Where are all calculations done. Is executed before rendering.
     *
     * @param delta to complex to explain
     */
    public abstract void tick(float delta);

    /**
     * Renders the layer.
     */
    public abstract void render();


    /**
     * Returns if the coordinates are in the layer.
     *
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @return contains layer
     */
    public abstract boolean contains(int x, int y);

}
