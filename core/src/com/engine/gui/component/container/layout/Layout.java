package com.engine.gui.component.container.layout;

import com.engine.gui.component.Component;
import com.engine.gui.component.container.Container;

/**
 * Created by tobias on 23.07.2014.
 */
public abstract class Layout extends Container<Component> {

    public Layout(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
}
