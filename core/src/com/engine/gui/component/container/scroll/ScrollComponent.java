package com.engine.gui.component.container.scroll;

import com.engine.gui.component.Component;
import com.engine.gui.component.container.Container;
import com.engine.gui.graphics.Graphics;

/**
 * Created by tobias on 10.08.2014.
 */
public abstract class ScrollComponent extends Container {

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
     * and down by changing {@link com.engine.gui.component.container.scroll.ScrollComponent#scrollY}. Moving the
     * scrollbar down makes the value of {@link com.engine.gui.component.container.scroll.ScrollComponent#scrollY}
     * higher.
     */
    private ScrollBar verticalScrollbar;

    /**
     * Horizontal scrollbar which is displayed either on the top or bottom. This scrollbar moves the content to the
     * right or left by changing {@link com.engine.gui.component.container.scroll.ScrollComponent#scrollX}. Moving
     * the scrollbar down makes the value of {@link com.engine.gui.component.container.scroll.ScrollComponent#scrollX}
     * higher.
     */
    private ScrollBar horizontalScrollbar;

    /**
     * Special Container where size and position can be greater than the container size. In this case scrollbars are
     * added to this container to scroll the container.
     */
    private ScrollContainer scrollContainer;

    /**
     * @param x      X-Coordinate
     * @param y      Y-Coordinate
     * @param width  width
     * @param height height of container
     */
    public ScrollComponent(int x, int y, int width, int height) {
        super(x, y, width, height);
        initScrollContainer();
    }

    /**
     * Creates an empty scrollcontainer and redirects {@link ScrollContainer#renderComponent()} to
     * {@link ScrollComponent#renderComponent()} so subclasses can simply override the renderComponent-method and draw
     * everything automatically in ScrollContainer.
     */
    private void initScrollContainer() {
        scrollContainer = new ScrollContainer(this, 0, 0, width, height) {
            @Override
            public void renderComponent() {
                ScrollComponent.this.renderComponent();
            }
        };
        children.add(scrollContainer);
    }

    /**
     * Sets a new x - offset for the {@link com.engine.gui.component.Component}(s).<br>
     * Positive values move the {@link com.engine.gui.component.Component}(s) left, otherwise if negative right.
     *
     * @param newScrollX delta x-offset for component(s)
     */
    public void moveScrollX(int newScrollX) {
        scrollX += newScrollX;
    }

    /**
     * Sets a new y - offset for the {@link com.engine.gui.component.Component}(s).<br>
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
        super.setWidth(width);
        int scrollBarWidth = verticalScrollbar == null ? 0 : verticalScrollbar.getWidth();
        scrollContainer.setWidth(width - scrollBarWidth);
        fitScrollBarSize();
    }


    /**
     * Sets a new height to the container. Moreover it fits the size of the scrollcontainer and scrollbars.
     *
     * @param height new height of container
     */
    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        int scrollbarHeight = horizontalScrollbar == null ? 0 : horizontalScrollbar.getHeight();
        scrollContainer.setHeight(height - scrollbarHeight);
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
     * Returns the scrollcontainer where all components are added.
     *
     * @return {@link com.engine.gui.component.container.scroll.ScrollContainer}
     */
    public ScrollContainer getScrollContainer() {
        return scrollContainer;
    }

    /**
     * Removes the previous scrollcontainer and adds the new scrollcontainer as child.
     *
     * @param container new scrollcontainer
     */
    public void setScrollContainer(ScrollContainer container) {
        children.remove(scrollContainer);
        this.scrollContainer = container;
        children.add(scrollContainer);
    }

    /**
     * Updates the scrollbars and is executed when the real size of the scrollcontainer changed.
     */
    public void scrollContainerRealSizeChanged() {
        fitScrollBarSize();
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
            scrollBar.setScrollComponent(this);
            return;
        }
        if (horizontalScrollbar != null) {
            children.remove(horizontalScrollbar);
        }
        horizontalScrollbar = scrollBar;
        scrollBar.setScrollComponent(this);
    }

    /**
     * Returns the real height of the scrollcontainer.
     *
     * @return real height of container
     */
    public int getRealHeight() {
        return scrollContainer.getRealHeight();
    }

    /**
     * Returns the real width of the scrollcontainer.
     *
     * @return real width of container
     */
    public int getRealWidth() {
        return scrollContainer.getRealWidth();
    }


    /**
     * Translates the projection matrix depending on the offsets. Moreover it executes the abstract render method
     * where subclasses draw everything.
     */
    @Override
    public void render() {
        Graphics.translate(x, y);
        Graphics.translate(scrollX, scrollY);
        Graphics.limitDrawing(0, 0, getWidth(), getHeight());
        if (scrollContainer != null) {
            scrollContainer.render();
        }
        Graphics.limitEnd();
        Graphics.translate(-scrollX, -scrollY);
        if (verticalScrollbar != null) {
            verticalScrollbar.render();
        }
        if (horizontalScrollbar != null) {
            horizontalScrollbar.render();
        }
        Graphics.translate(-x, -y);
        if (animationManager != null) {
            animationManager.tickEnd();
        }
    }

    @Override
    public void addChild(Component child) {
        scrollContainer.addChild(child);
    }

    @Override
    public void addChildInBackground(Component child) {
        scrollContainer.addChildInBackground(child);
    }

    @Override
    public void removeChildren(Component child) {
        scrollContainer.removeChildren(child);
    }

    @Override
    public void removeChild(int index) {
        scrollContainer.removeChild(index);
    }
}
