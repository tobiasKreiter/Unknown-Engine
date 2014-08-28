package com.engine.gui.component.container.scroll;

import com.badlogic.gdx.graphics.Color;
import com.engine.gui.component.Component;
import com.engine.gui.component.ComponentType;
import com.engine.gui.graphics.Graphics;

/**
 * Created by Felix on 02.08.2014.
 */
public class ScrollBar extends Component {

    public static final int RIGHT = 0;
    public static final int BOTTOM = 1;
    public static final int LEFT = 2;
    public static final int TOP = 3;


    protected ScrollComponent scrollComponent;
    /**
     * Indicates what type of scrollbar it is.
     */
    protected int orientation;

    /**
     * Width of the slider.
     */
    protected int sliderWidth;
    /**
     * Height of the slider.
     */
    protected int sliderHeight;
    /**
     * X-Pos of slider.
     */
    protected int sliderX;
    /**
     * Y-Pos of slider.
     */
    protected int sliderY;
    /**
     * Relative x-position on slider where the mouse clicked.
     */
    protected int relativeClickX;
    /**
     * Relative y-position on slider where the mouse clicked.
     */
    protected int relativeClickY;
    /**
     * Color of the scrollbar.
     */
    protected Color scrollBarColor = Color.GRAY;
    /**
     * Color of the Slider.
     */
    protected Color sliderColor = Color.WHITE;
    /**
     * Distance of slider and scrollbar.
     */
    protected int sliderMargin = 3;
    /**
     * Max width or height of the slider. Its depending on the orientation. If its vertical then this is the max width.
     * Otherwise its the max height.
     */
    protected final int MAX_SLIDER_SIZE = 20;

    public ScrollBar(int orientation) {
        super(ComponentType.SCROLLBAR);
        this.orientation = orientation;
    }

    /**
     * Sets the scrollcomponent and adjusts the scrollbar depending on the size of the scrollcomponent.
     *
     * @param scrollComponent new scrollcomponent.
     */
    public void setScrollComponent(ScrollComponent scrollComponent) {
        this.scrollComponent = scrollComponent;
        adjustScrollbar();
    }

    /**
     * Positions the scrollbar depending on the orientation.
     */
    public void adjustScrollbar() {
        registerScrollbar();
        switch (getOrientation()) {
            case ScrollBar.TOP:
                initHorizontalTopScrollBar();
                break;
            case ScrollBar.RIGHT:
                initVerticalRightScrollBar();
                break;
            case ScrollBar.BOTTOM:
                initHorizontalBottomScrollBar();
                break;
            case ScrollBar.LEFT:
                initVerticalLeftScrollBar();
                break;
        }
    }

    /**
     * Registers the component at the parent of the scrollComponent or if it has no parent directly on the layer.
     */
    private void registerScrollbar() {
        /*if (scrollComponent.getParent() == null) {
            ((ComponentLayer) scrollComponent.getParentLayer()).addComponent(this);
        } else {
            //TODO change way to add scrollbar because instanceof is very slow
            if(scrollComponent.getParent() instanceof Window) {
                ((ComponentLayer) scrollComponent.getParentLayer()).addComponent(this);
                return;
            }
            ((Container) scrollComponent.getParent()).addChild(this);
        }*/
    }

    /**
     * Adjusts the slider after the position or the size changed.
     */
    public void adjustSlider() {
        switch (getOrientation()) {
            case ScrollBar.TOP:
                adjustHorizontalTopSlider();
                break;
            case ScrollBar.RIGHT:
                adjustVerticalRightSlider();
                break;
            case ScrollBar.BOTTOM:
                adjustHorizontalBottomSlider();
                break;
            case ScrollBar.LEFT:
                adjustVerticalLeftSlider();
                break;
        }
    }

    /**
     * Adjusts the left scrollbar left of the scrollcomponent.
     */
    private void initVerticalLeftScrollBar() {
        setHeight(scrollComponent.getHeight());

        int width = (int) (scrollComponent.getWidth() * 0.07);
        setWidth(width > MAX_SLIDER_SIZE ? MAX_SLIDER_SIZE : width);

        setX(scrollComponent.getX() - getWidth());
        setY(scrollComponent.getY());

        adjustVerticalLeftSlider();
    }

    /**
     * Inits the slider for the left scrollbar. This method is exetued when the position or the size of the
     * scrollbar changed.
     */
    private void adjustVerticalLeftSlider() {
        setSliderWidth(getWidth() - (getSliderMargin() * 2));
        setSliderX(getX() + getSliderMargin());
        setSliderY(getY() + getSliderMargin());

        double ratioHeight = (double) scrollComponent.getHeight() / scrollComponent.getRealHeight();
        ratioHeight = ratioHeight > 1 ? 1 : ratioHeight;
        setSliderHeight((int) ((ratioHeight * getHeight()) - (getSliderMargin() * 2)));
    }

    /**
     * Adjusts the right scrollbar.
     */
    private void initVerticalRightScrollBar() {
        int width = (int) (scrollComponent.getWidth() * 0.07);
        setWidth(width > MAX_SLIDER_SIZE ? MAX_SLIDER_SIZE : width);

        setX(scrollComponent.getX() + scrollComponent.getWidth() - width);
        setY(scrollComponent.getY());

        setHeight(scrollComponent.getHeight());

        adjustVerticalRightSlider();

    }

    /**
     * Inits the slider for the right scrollbar. This method is exetued when the position or the size of the
     * scrollbar changed.
     */
    private void adjustVerticalRightSlider() {
        setSliderWidth(getWidth() - (getSliderMargin() * 2));
        setSliderX(getX() + getSliderMargin());
        setSliderY(getY() + getSliderMargin());

        double ratioHeight = (double) getHeight() / scrollComponent.getRealHeight();
        ratioHeight = ratioHeight > 1 ? 1 : ratioHeight;
        setSliderHeight((int) ((ratioHeight * getHeight()) - (getSliderMargin() * 2)));
    }

    /**
     * Adjusts the top scrollbar.
     */
    private void initHorizontalTopScrollBar() {
        setWidth(scrollComponent.getWidth());

        int height = (int) (scrollComponent.getWidth() * 0.07);
        setHeight(height > MAX_SLIDER_SIZE ? MAX_SLIDER_SIZE : height);

        setX(scrollComponent.getX());
        setY(scrollComponent.getY() - getHeight());

        adjustHorizontalTopSlider();
    }

    /**
     * Inits the slider for the top scrollbar. This method is exetued when the position or the size of the
     * scrollbar changed.
     */
    private void adjustHorizontalTopSlider() {
        setSliderHeight(getHeight() - (getSliderMargin() * 2));
        setSliderX(getX() + getSliderMargin());
        setSliderY(getY() + getSliderMargin());

        double ratioWidth = (double) getWidth() / scrollComponent.getRealWidth();
        ratioWidth = ratioWidth > 1 ? 1 : ratioWidth;
        setSliderWidth((int) ((ratioWidth * getWidth()) - (getSliderMargin() * 2)));
    }

    /**
     * Adjusts the bottom scrollbar.
     */
    private void initHorizontalBottomScrollBar() {
        int height = (int) (scrollComponent.getWidth() * 0.07);
        setHeight(height > MAX_SLIDER_SIZE ? MAX_SLIDER_SIZE : height);

        setX(scrollComponent.getX());
        setY(scrollComponent.getY() + scrollComponent.getHeight() - getHeight());

        setWidth(scrollComponent.getWidth());

        adjustHorizontalBottomSlider();
    }

    /**
     * Inits the slider for the bottom scrollbar. This method is exetued when the position or the size of the
     * scrollbar changed.
     */
    private void adjustHorizontalBottomSlider() {
        setSliderHeight(getHeight() - (getSliderMargin() * 2));
        setSliderX(getX() + getSliderMargin());
        setSliderY(getY() + getSliderMargin());

        double ratioWidth = (double) getWidth() / scrollComponent.getRealWidth();
        ratioWidth = ratioWidth > 1 ? 1 : ratioWidth;
        setSliderWidth((int) ((ratioWidth * getWidth()) - (getSliderMargin() * 2)));
    }

    /**
     * Returns the orientation of the scrollbar.
     *
     * @return orientation
     */
    public int getOrientation() {
        return orientation;
    }

    /**
     * Returns the width of the slider.
     *
     * @return width of slider
     */
    public int getSliderWidth() {
        return sliderWidth;
    }

    /**
     * Sets a new slider width.
     *
     * @param sliderWidth new slider width
     */
    protected void setSliderWidth(int sliderWidth) {
        this.sliderWidth = sliderWidth;
    }

    /**
     * Returns the slider height.
     *
     * @return slider height
     */
    public int getSliderHeight() {
        return sliderHeight;
    }

    /**
     * Sets the slider a new height.
     *
     * @param sliderHeight new slider height
     */
    public void setSliderHeight(int sliderHeight) {
        this.sliderHeight = sliderHeight;
    }

    /**
     * Returns the x-pos of the slider.
     *
     * @return x-pos of slider
     */
    public int getSliderX() {
        return sliderX;
    }

    /**
     * Sets the slider a new x-pos.
     *
     * @param sliderX new x-pos for slider
     */
    protected void setSliderX(int sliderX) {
        this.sliderX = sliderX;
    }

    /**
     * Returns the y-pos of the slider.
     *
     * @return y-pos of slider
     */
    public int getSliderY() {
        return sliderY;
    }

    /**
     * Sets the slider a new y-pos.
     *
     * @param sliderY new y-pos for slider
     */
    protected void setSliderY(int sliderY) {
        this.sliderY = sliderY;
    }

    /**
     * Returns the color of the scrollbar NOT the slider.
     *
     * @return color of scrollbar
     */
    public Color getScrollBarColor() {
        return scrollBarColor;
    }

    /**
     * Sets the scrollbar a new color.
     *
     * @param scrollBarColor new scrollbar color
     */
    public void setScrollBarColor(Color scrollBarColor) {
        this.scrollBarColor = scrollBarColor;
    }

    /**
     * Returns the current color of the slider.
     *
     * @return current color of slider
     */
    public Color getSliderColor() {
        return sliderColor;
    }

    /**
     * Sets the slider a new color.
     *
     * @param sliderColor new color for slider
     */
    public void setSliderColor(Color sliderColor) {
        this.sliderColor = sliderColor;
    }

    /**
     * Returns the distance of scrollbar and slider for oe side.
     *
     * @return distance of slider and scrollbar
     */
    public int getSliderMargin() {
        return sliderMargin;
    }

    /**
     * Sets a new margin for the slider.
     *
     * @param sliderMargin new margin for slider
     */
    public void setSliderMargin(int sliderMargin) {
        this.sliderMargin = sliderMargin;
    }

    @Override
    public boolean onSizeChanged(boolean width) {
        adjustSlider();
        return false;
    }

    /**
     * Saves the click position for dragging.
     *
     * @param screenX x-pos of mouse or finger
     * @param screenY y-pos of mouse or finger
     * @param pointer unimportant
     * @param button  unimportant
     * @return false
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        relativeClickX = screenX - (getX() + getSliderX() + scrollComponent.getX());
        relativeClickY = screenY - (getY() + getSliderY() + scrollComponent.getY());
        return false;
    }

    /**
     * Draggs the slider.
     *
     * @param screenX current x-pos of mouse or finger
     * @param screenY current y-pos of mouse or finger
     * @param pointer
     * @return false
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        int rClickX = screenX - (getX() + scrollComponent.getX());
        int rClickY = screenY - (getY() + scrollComponent.getY());
        rClickX -= relativeClickX;
        rClickY -= relativeClickY;
        moveSlider(rClickX, rClickY);
        return false;
    }

    /**
     * Draws the slider
     */
    @Override
    public void renderComponent() {
        Graphics.drawFilledRect(getX(), getY(), getWidth(), getHeight(), scrollBarColor);
        Graphics.drawFilledRect(sliderX, sliderY, sliderWidth, sliderHeight, sliderColor);
    }

    /**
     * Moves the slider horizontally or vertically depending on the orientation.
     *
     * @param dX delta x
     * @param dY delta y
     */
    protected void moveSlider(int dX, int dY) {
        if (orientation == LEFT || orientation == RIGHT) {
            moveVerticalSlider(dY);
        } else {
            moveHorizontalSlider(dX);
        }
    }

    /**
     * Moves the slider vertically. Should only be done for right and left slider.
     *
     * @param newY new y-pos
     */
    protected void moveVerticalSlider(int newY) {
        int minY = getY() + getSliderMargin();
        int maxY = getY() + getHeight() - (getSliderHeight() + getSliderMargin());
        if (newY < minY) {
            setSliderY(minY);
        } else if (newY > maxY) {
            setSliderY(maxY);
        } else {
            setSliderY(newY);
        }
        updateScrollContainerY();
    }

    /**
     * Moves the scroll container vertically depending of the position of the slider.
     */
    protected void updateScrollContainerY() {
        double ratio = (double) ((scrollComponent.getRealHeight()) - scrollComponent.getHeight()) / (getHeight() - getSliderHeight() - (getSliderMargin() * 2));
        if (Double.isNaN(ratio) || Double.isInfinite(ratio)) {
            ratio = 0.0;
        }
        int relativeSliderY = getSliderY() - (getY() + getSliderMargin());
        scrollComponent.moveScrollY((int) (relativeSliderY * ratio));
    }

    /**
     * Moves the slider horizontally. Should only be done to bottom and top scrollbars.
     *
     * @param newX new x-position
     */
    protected void moveHorizontalSlider(int newX) {
        int minX = getX() + getSliderMargin();
        int maxX = getX() + getWidth() - (getSliderWidth() + getSliderMargin());
        if (newX < minX) {
            setSliderX(minX);
        } else if (newX > maxX) {
            setSliderX(maxX);
        } else {
            setSliderX(newX);
        }
        updateScrollContainerX();
    }

    /**
     * Moves the scrollcontainer horizontally depending of the position of the slider.
     */
    protected void updateScrollContainerX() {
        double ratio = (double) ((scrollComponent.getRealWidth()) - getWidth()) / (getWidth() - getSliderWidth() - (getSliderMargin() * 2));
        if (Double.isNaN(ratio) || Double.isInfinite(ratio)) {
            ratio = 0.0;
        }
        int relativeSliderX = getSliderX() - (getX() + getSliderMargin());
        scrollComponent.moveScrollX((int) (relativeSliderX * ratio));
    }

    /**
     * Overriding setter for position and size so the slider gets automatically adjusted.
     */

    @Override
    public void setX(int x) {
        super.setX(x);
        adjustSlider();
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        adjustSlider();
    }

    public void setWidth(int width) {
        this.width = width;
        adjustSlider();
    }

    public void setHeight(int height) {
        this.height = height;
        adjustSlider();
    }
}
