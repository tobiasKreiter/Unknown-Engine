package com.engine.gui.component.container.scroll;

import com.engine.gui.component.Component;
import com.engine.gui.component.container.Container;

/**
 * Created by Tobias on 14.08.2014.
 */
public class ScrollContainer extends Container {

    /**
     * Scrollcomponent which manages scrollbars and the scrollcontainer. When the size of the scrollcontainer is changed
     * scrollcomponent adjusts the size of the scrollbars.
     */
    private ScrollComponent scrollComponent;

    /**
     * Contains the real width which is mostly larger than the width which is displayed. The horizontal scrollbar uses
     * realWidth to calculate the {@link com.engine.gui.component.container.scroll.ScrollBar#sliderWidth} and the ratio
     * with which the content is moved along the x-axis.
     */
    private int realWidth;

    /**
     * Contains the real height which is mostly larger than the width which is displayed. The vertical scrollbar uses
     * realHeight to calculate the {@link com.engine.gui.component.container.scroll.ScrollBar#sliderHeight} and the
     * ratio with which the content is moved along the y-axis.
     */
    private int realHeight;

    /**
     * Indicates whether the realSize is calculated depending on the added or components or if it is set manually.
     */
    private boolean fitModeOn = true;

    /**
     * @param x      X-Coordinate
     * @param y      Y-Coordinate
     * @param width  width
     * @param height height of container
     */
    public ScrollContainer(ScrollComponent scrollComponent, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.scrollComponent = scrollComponent;
    }

    /**
     * Returns an attribute which contains the width of the component which it really has but is for the user
     * not visible.
     *
     * @return real width
     */
    public int getRealWidth() {
        return realWidth;
    }

    /**
     * Sets a new realWidth. If fitmode is activated this doesn't change the width.
     *
     * @param realWidth new realWidth.
     */
    public void setRealWidth(int realWidth) {
        if (!fitModeOn) {
            this.realWidth = realWidth;
            scrollComponent.scrollContainerRealSizeChanged();
        }
    }

    /**
     * Returns an attribute which contains the height of the component which it really has but is for the user
     * not visible.
     *
     * @return real height
     */
    public int getRealHeight() {
        return realHeight;
    }

    /**
     * Sets a new realHeight. If fitmode is activated this doesn't change the height.
     *
     * @param realHeight new realHeight
     */
    public void setRealHeight(int realHeight) {
        if (!fitModeOn) {
            this.realHeight = realHeight;
            scrollComponent.scrollContainerRealSizeChanged();
        }
    }

    /**
     * Activates or deactivates the fitmode. Explanation at
     * {@link com.engine.gui.component.container.scroll.ScrollContainer#fitModeOn}
     *
     * @param fitModeOn fitmode on or off
     */
    public void setFitMode(boolean fitModeOn) {
        this.fitModeOn = fitModeOn;
    }

    /**
     * Recalculates the new real size of the container.
     */
    private void fitRealSize() {
        realWidth = 0;
        realHeight = 0;
        for (int i = 0; i < children.size(); i++) {
            Component child = children.get(i);
            realWidth = Math.max(realWidth, child.getX() + child.getWidth());
            realHeight = Math.max(realHeight, child.getY() + child.getHeight());
        }
        scrollComponent.scrollContainerRealSizeChanged();
    }

    /*
         <---- Overriding add and remove methods ---->
     */

    @Override
    public void addChild(Component child) {
        super.addChild(child);
        if (fitModeOn) {
            fitRealSize();
        }
    }

    @Override
    public void addChildInBackground(Component child) {
        super.addChildInBackground(child);
        if (fitModeOn) {
            fitRealSize();
        }
    }

    @Override
    public void removeChild(int index) {
        super.removeChild(index);
        if(fitModeOn) {
            fitRealSize();
        }
    }

    @Override
    public void removeChildren(Component child) {
        super.removeChildren(child);
        if(fitModeOn) {
            fitRealSize();
        }
    }

    /*
        <---- End Overriding ---->
     */
}
