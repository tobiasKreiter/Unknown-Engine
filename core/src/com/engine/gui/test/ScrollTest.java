package com.engine.gui.test;

import com.badlogic.gdx.graphics.Color;
import com.engine.gui.component.Component;
import com.engine.gui.component.ComponentType;
import com.engine.gui.graphics.Graphics;

/**
 * Created by Tobias on 15.08.2014.
 */
public class ScrollTest extends Component {
    /**
     * @param width  width
     * @param height height of container
     */
    public ScrollTest(int width, int height) {
        super(0, 0, width, height, ComponentType.CONTAINER);
    }

    @Override
    public void renderComponent() {
        Graphics.drawFilledRect(0, 0, getWidth(), getHeight(), Color.RED);
    }

    @Override
    public void tick(float delta) {
        setWidth(getWidth() + 1);
    }
}
