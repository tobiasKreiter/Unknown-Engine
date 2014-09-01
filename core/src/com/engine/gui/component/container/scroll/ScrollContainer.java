package com.engine.gui.component.container.scroll;

import com.badlogic.gdx.graphics.Color;
import com.engine.gui.component.Component;
import com.engine.gui.component.container.Container;
import com.engine.gui.graphics.Graphics;

/**
 * Created by tobias on 10.08.2014.
 */
public class ScrollContainer extends Container<Component> {

    /**
     * X-Offset with which the content of the component is moved along the x-axis. Positive values move the content
     * to the left.
     */
    private int scrollX;

    /**
     * Y-Offset with which the content of the component is moved along the y-axis. Positive values move the content
     * up.
     */
    private int scrollY;

    /**
     * Vertical scrollbar which is displayed either on the right or left side. This scrollbar moves the content up
     * and down by changing {@link ScrollContainer#scrollY}. Moving the
     * scrollbar down makes the value of {@link ScrollContainer#scrollY}
     * higher.
     */
    private ScrollBar verticalScrollbar;

    /**
     * Horizontal scrollbar which is displayed either on the top or bottom. This scrollbar moves the content to the
     * right or left by changing {@link ScrollContainer#scrollX}. Moving
     * the scrollbar down makes the value of {@link ScrollContainer#scrollX}
     * higher.
     */
    private ScrollBar horizontalScrollbar;

    /**
     * @param x      X-Coordinate
     * @param y      Y-Coordinate
     * @param width  width
     * @param height height of container
     */
    public ScrollContainer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * Sets a new x - offset for the added Component(s).<br>
     * Positive values move the {@link com.engine.gui.component.Component}(s) left, otherwise if negative right.
     *
     * @param newScrollX delta x-offset for component(s)
     */
    public void moveScrollX(int newScrollX) {
        scrollX += newScrollX;
    }

    /**
     * Sets a new y - offset for the added {@link com.engine.gui.component.Component}(s).<br>
     * Positive values move the {@link com.engine.gui.component.Component}(s) up, otherwise if negative left.
     *
     * @param newScrollY delta y-offset for component(s)
     */
    public void moveScrollY(int newScrollY) {
        scrollY += newScrollY;
    }

    /**
     * Returns the current x-offset of the {@link com.engine.gui.component.Component}(s).
     *
     * @return current x-offset
     */
    private int getScrollX() {
        return scrollX;
    }

    /**
     * Returns the current y-offset of the {@link com.engine.gui.component.Component}(s).
     *
     * @return current y-offset
     */
    private int getScrollY() {
        return scrollY;
    }

    /**
     * Sets the container a new width. Moreover it fits the size of the scrollcontainer and scrollbars.
     *
     * @param width new width of container.
     */
    @Override
    public void setWidth(int width) {
        int scrollBarWidth = verticalScrollbar == null ? 0 : verticalScrollbar.getWidth();
        this.width = width - scrollBarWidth;
        fitScrollBarSize();
    }


    /**
     * Sets a new height to the container. Moreover it fits the size of the scrollcontainer and scrollbars.
     *
     * @param height new height of container
     */
    @Override
    public void setHeight(int height) {
        int scrollbarHeight = horizontalScrollbar == null ? 0 : horizontalScrollbar.getHeight();
        this.height = height - scrollbarHeight;
        fitScrollBarSize();
    }

    /**
     * Fits the added scrollbars to the new size of the container.
     */
    private void fitScrollBarSize() {
        if (verticalScrollbar != null) {
            verticalScrollbar.adjustScrollbar();
        }
        if (horizontalScrollbar != null) {
            horizontalScrollbar.adjustScrollbar();
        }
    }

    /**
     * Updates the scrollbars and is executed when the real size of the scrollcontainer changed.
     */
    public void scrollContainerRealSizeChanged() {
        checkIfScrollbarIsNecessary();
        fitScrollBarSize();
    }

    /**
     * Checks if a scrollbar is needed.
     */
    private void checkIfScrollbarIsNecessary() {
        int maxX = getInnerWidth();
        if (maxX > getWidth()) {
            if (horizontalScrollbar != null) {
                addScrollbar(new ScrollBar(ScrollBar.BOTTOM));
            }
        }
        int maxY = getInnerHeight();
        if (maxY > getHeight()) {
            if (verticalScrollbar != null) {
                addScrollbar(new ScrollBar(ScrollBar.RIGHT));
            }
        }
    }

    /**
     * Returns the highest x-value off all components.
     *
     * @return max x-value
     */
    public int getInnerWidth() {
        int maxX = 0;
        for (int i = 0; i < children.size(); i++) {
            maxX = Math.max(children.get(i).getX() + children.get(i).getWidth(), maxX);
        }
        return maxX;
    }

    /**
     * Returns the highest y-value off all components.
     *
     * @return max y-value
     */
    public int getInnerHeight() {
        int maxY = 0;
        for (int i = 0; i < children.size(); i++) {
            maxY = Math.max(children.get(i).getX() + children.get(i).getWidth(), maxY);
        }
        return maxY;
    }

    /**
     * Adds the scrollbar to the scrollcomponent and removes the previous vertical or horizontal scrollbar.
     *
     * @param scrollBar new scrollbar
     */
    public void addScrollbar(ScrollBar scrollBar) {
        if (scrollBar.getOrientation() == ScrollBar.LEFT || scrollBar.getOrientation() == ScrollBar.RIGHT) {
            if (verticalScrollbar != null) {
                children.remove(verticalScrollbar);
            }
            verticalScrollbar = scrollBar;
            scrollBar.setScrollContainer(this);
            return;
        }
        if (horizontalScrollbar != null) {
            children.remove(horizontalScrollbar);
        }
        horizontalScrollbar = scrollBar;
        scrollBar.setScrollContainer(this);
    }


    /**
     * Translates the projection matrix depending on the offsets. Moreover it executes the abstract render method
     * where subclasses draw everything.
     */
    @Override
    public void render() {
        Graphics.translate(-x, -y);
        Graphics.drawFilledRect(0,0, getWidth(), getHeight(), Color.BLUE);
        Graphics.translate(-scrollX, -scrollY);
        Graphics.limitDrawing(0, 0, getWidth(), getHeight());
        for (int i = 0; i < children.size(); i++) {
            children.get(i).render();
        }
        Graphics.limitEnd();
        Graphics.translate(scrollX, scrollY);
        if (verticalScrollbar != null) {
            verticalScrollbar.render();
        }
        if (horizontalScrollbar != null) {
            horizontalScrollbar.render();
        }
        Graphics.translate(x, y);
        if (animationManager != null) {
            animationManager.tickEnd();
        }
    }
}
