package com.engine.gui.test;

import com.badlogic.gdx.graphics.Color;
import com.engine.gui.component.container.scroll.ScrollComponent;
import com.engine.gui.graphics.Graphics;

/**
 * Created by Tobias on 15.08.2014.
 */
public class ScrollTest extends ScrollComponent {
    /**
     * @param x      X-Coordinate
     * @param y      Y-Coordinate
     * @param width  width
     * @param height height of container
     */
    public ScrollTest(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void renderComponent() {
        Graphics.drawFilledRect(0, 0, getWidth(), getHeight(), Color.RED);
    }
}
