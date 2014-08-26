package com.engine.gui.component.container.layout.table_layout;

import com.badlogic.gdx.graphics.Color;
import com.engine.gui.component.Component;
import com.engine.gui.graphics.Graphics;

/**
 * Created by tobias on 23.07.2014.
 */
public class TableColumn {

    /**
     * --------------------------------------------------|--------------
     * TOP-LEFT           TOP-CENTER           TOP-RIGHT |
     * |
     * |
     * |
     * CENTER-LEFT      CENTER-CENTER       CENTER-RIGHT |
     * |
     * |
     * |
     * BOTTOM-LEFT      BOTTOM-CENTER       BOTTOM-RIGHT |
     * --------------------------------------------------|--------------
     * |
     * |
     */

    public static final int HORIZONTAL_ALIGN_CENTER = 1;
    public static final int HORIZONTAL_ALIGN_RIGHT = 2;
    public static final int HORIZONTAL_ALIGN_LEFT = 3;

    public static final int VERTICAL_ALIGN_TOP = 4;
    public static final int VERTICAL_ALIGN_CENTER = 5;
    public static final int VERTICAL_ALIGN_BOTTOM = 6;

    /**
     * Contains the vertical alignment.
     * Default: Center
     */
    private int verticalAlignment = VERTICAL_ALIGN_CENTER;
    /**
     * Contains the horizontal alignment.
     * Default: Center
     */
    private int horizontalAlignment = HORIZONTAL_ALIGN_CENTER;

    /**
     * Margin between left border and component.
     */
    private int leftMargin = 0;
    /**
     * Margin between right border and component.
     */
    private int rightMargin = 0;
    /**
     * Margin between top border and component.
     */
    private int topMargin = 0;
    /**
     * Margin between bottom border and component.
     */
    private int bottomMargin = 0;
    /**
     * X-Coordinate
     */
    private int x;
    /**
     * Y-Coordinate
     */
    private int y;
    /**
     * Width of Row
     */
    private double width;
    /**
     * Height of Row
     */
    private int height;
    /**
     * Reference to the component so the table row can adjust the position dircetly.
     */
    private Component component;

    public TableColumn(Component component, int x, int y, int width, int height) {
        this.component = component;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the height of the cell.
     *
     * @return height of cell
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets a new height for the cell and updates the position of the component.
     *
     * @param height new height
     */
    public void setHeight(int height) {
        this.height = height;
        adjustComponent();
    }

    /**
     * Returns the width of the cell and updates the position of the component.
     *
     * @return width of cell
     */
    public int getWidth() {
        return (int) width;
    }

    public double getWidthf() {
        return width;
    }

    /**
     * Sets a new width for the cell.
     *
     * @param width new width
     */
    public void setWidth(double width) {
        this.width = width;
        adjustComponent();
    }

    /**
     * Returns the X-Coordinate of the cell.
     *
     * @return x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets a new X-Position and updates the position of the component.
     *
     * @param x new X-Position
     */
    public void setX(int x) {
        this.x = x;
        adjustComponent();
    }

    /**
     * Returns the Y-Coordinate.
     *
     * @return Y-Coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets a new Y-Position and updates the position of the component.
     *
     * @param y new Y-Position
     */
    public void setY(int y) {
        this.y = y;
        adjustComponent();
    }

    /**
     * Returns the vertical alignment of the cell.
     *
     * @return vertical alignment
     */
    public int getVerticalAlignment() {
        return verticalAlignment;
    }

    /**
     * Sets the cell a new vertical alignment.
     *
     * @param verticalAlignment new vertical alignment
     */
    public void setVerticalAlignment(int verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
        adjustComponent();
    }

    /**
     * Returns the horizontal alignment.
     *
     * @return horizontal alignment
     */
    public int getHorizontalAlignment() {
        return horizontalAlignment;
    }

    /**
     * Sets the cell a new horizontal alignment.
     *
     * @param horizontalAlignment new horizontal alignment
     */
    public void setHorizontalAlignment(int horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        adjustComponent();
    }

    /**
     * Returns the left distance of component and table row.
     *
     * @return left margin
     */
    public int getLeftMargin() {
        return leftMargin;
    }

    /**
     * Sets the left distance between the table row and component.
     *
     * @param leftMargin left distance
     */
    public void setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
        adjustComponent();
    }

    /**
     * Returns the right distance of component and table row.
     *
     * @return right margin
     */
    public int getRightMargin() {
        return rightMargin;
    }

    /**
     * Sets the right distance between component and table row.
     *
     * @param rightMargin new right margin
     */
    public void setRightMargin(int rightMargin) {
        this.rightMargin = rightMargin;
        adjustComponent();
    }

    /**
     * Returns the top distance of component and table row.
     *
     * @return top margin
     */
    public int getTopMargin() {
        return topMargin;
    }

    /**
     * Sets the top distance of component and table row.
     *
     * @param topMargin new top margin
     */
    public void setTopMargin(int topMargin) {
        this.topMargin = topMargin;
        adjustComponent();
    }

    /**
     * Returns the bottom distance of component and table row.
     *
     * @return bottom margin
     */
    public int getBottomMargin() {
        return bottomMargin;
    }

    /**
     * Sets the bottom distance of component and table row.
     *
     * @param bottomMargin new bottom margin
     */
    public void setBottomMargin(int bottomMargin) {
        this.bottomMargin = bottomMargin;
        adjustComponent();
    }

    /**
     * Sets the left, right, top and bottom margin.
     *
     * @param marginLeft   left margin
     * @param marginRight  right margin
     * @param marginTop    top margin
     * @param marginBottom bottom margin
     */
    public void setMargin(int marginLeft, int marginRight, int marginTop, int marginBottom) {
        setLeftMargin(marginLeft);
        setRightMargin(marginRight);
        setTopMargin(marginTop);
        setBottomMargin(marginBottom);
    }

    /**
     * Changes the component and adjusts its position.
     *
     * @param component new component
     */
    public void setComponent(Component component) {
        this.component = component;
        adjustComponent();
    }

    /**
     * Returns the current component.
     *
     * @return component
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Indicates whether the width of the columns can be shrinked.
     *
     * @param newWidth new width of column
     * @return column-width can be shrinked or not
     */
    public boolean canWidthShrink(int newWidth) {
        return newWidth > getMinWidth();
    }

    /**
     * Returns the minimum width of the column
     *
     * @return mimimum width
     */
    public int getMinWidth() {
        if (component != null) {
            return leftMargin + rightMargin + component.getWidth();
        }
        return 1; //If there is no component the minimum width is 1
    }

    /**
     * Indicates whether the height of the columns can be shrinked.
     *
     * @param newHeight new height of column
     * @return column-height can be shrinked or not
     */
    public boolean canHeightShrink(int newHeight) {
        return newHeight > getMinHeight();
    }

    /**
     * Returns the minimum height of the column
     *
     * @return minimum height
     */
    public int getMinHeight() {
        if (component != null) {
            return topMargin + bottomMargin + component.getHeight();
        }
        return 1; //If there is no component the minimum height is 1
    }

    /**
     * Positions the component at the right place
     */
    public void adjustComponent() {
        if (component != null) {
            switch (horizontalAlignment) {
                case HORIZONTAL_ALIGN_LEFT:
                    component.setX(getX() + leftMargin);
                    break;
                case HORIZONTAL_ALIGN_CENTER:
                    component.setX(getX() + (getWidth() / 2 - component.getWidth() / 2));
                    break;
                case HORIZONTAL_ALIGN_RIGHT:
                    component.setX((getX() + getWidth()) - (component.getWidth() + rightMargin));
                    break;
            }
            switch (verticalAlignment) {
                case VERTICAL_ALIGN_TOP:
                    component.setY(getY() + topMargin);
                    break;
                case VERTICAL_ALIGN_CENTER:
                    component.setY(getY() + (getHeight() / 2 - component.getHeight() / 2));
                    break;
                case VERTICAL_ALIGN_BOTTOM:
                    component.setY((getY() + getHeight()) - (component.getHeight() + bottomMargin));
            }
        }
    }

    public void render() {
        Graphics.drawRect(getX(), getY(), getWidth(), getHeight(), Color.WHITE);
        if (component != null) {
            Graphics.drawRect(component.getX() - leftMargin, component.getY() - topMargin, component.getWidth() + rightMargin, component.getHeight() + bottomMargin, Color.GREEN);
        }
    }

}
