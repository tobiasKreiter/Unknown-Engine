package com.engine.gui.component.container.layout.table_layout;

import com.engine.gui.component.Component;

import java.util.ArrayList;

/**
 * Created by tobias on 25.07.2014.
 */
public class TableRow {

    /**
     * X-Position of row.
     */
    private int x;
    /**
     * Y-Position of row.
     */
    private int y;
    /**
     * Width of row. Usually the width of the tablelayout.
     */
    private int width;
    /**
     * Height of row.
     */
    private int height;
    /**
     * Columns of the row. The number of columns can be for every row different.
     */
    private TableColumn[] columns;

    public TableRow(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public TableRow(int x, int y, int width, int height, int columns) {
        this(x, y, width, height);
        this.columns = new TableColumn[columns];
        initColumns();
    }

    /**
     * Returns the X-Coordinate.
     *
     * @return X-Position
     */
    public int getX() {
        return x;
    }

    /**
     * Sets a new X-Coordinate.
     *
     * @param x new X-Position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the Y-Coordinate.
     *
     * @return Y-Position
     */
    public int getY() {
        return y;
    }

    /**
     * Sets  anew Y-Coordinate.
     *
     * @param y new Y-Position
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the width of the row.
     *
     * @return width of row.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Trys to set the new width.
     *
     * @param width new width
     * @return width could be changed
     */
    public boolean setWidth(int width) {
        if (width > getWidth()) {
            double newWidthDif = (width - getWidth()) / (double) columns.length;
            for (int i = 0; i < columns.length; i++) {
                columns[i].setWidth(columns[i].getWidthf() + newWidthDif);
            }
            adjustColumns();
            this.width = width;
            return true;
        }
        int difX = getWidth() - width;
        if (getMaxDifferenceX() < difX) { //columns can not be shrinked
            return false;
        }
        for (int i = columns.length - 1; i >= 0; i++) {
            if (columns[i].getWidth() > columns[i].getMinWidth()) { //column can be shrinked
                int maxDif = columns[i].getWidth() - columns[i].getMinWidth();
                if (maxDif >= difX) {
                    columns[i].setWidth(columns[i].getWidth() - difX);
                    this.width = width;
                    adjustColumns();
                    return true;
                } else {
                    columns[i].setWidth(columns[i].getWidth() - maxDif);
                    difX -= maxDif;
                }
            }
        }
        return false; //Never be returned
    }

    /**
     * Returns the max difference the row can be shrinked.
     *
     * @return max diff
     */
    private int getMaxDifferenceX() {
        int dif = 0;
        for (int i = 0; i < columns.length; i++) {
            dif += columns[i].getWidth() - columns[i].getMinWidth();
        }
        return dif;
    }

    /**
     * Returns the height of the row.
     *
     * @return height of row
     */
    public int getHeight() {
        return height;
    }

    /**
     * Trys to set the new height
     *
     * @param height new height
     */
    public void setHeight(int height) {
        this.height = height;
        adjustColumns();
    }

    /**
     * Returns whether the specific height can be applied.
     *
     * @param height new height
     * @return can be applied
     */
    public boolean canSetHeight(int height) {
        return (height > getHeight() || height > columns[0].getMinWidth());
    }

    /**
     * Resets all colunms and inits the new ones.
     *
     * @param columns new number of columns
     */
    public void setColumns(int columns) {
        this.columns = new TableColumn[columns];
        initColumns();
    }

    /**
     * Returns the number of columns in this row
     *
     * @return number of columns
     */
    public int getCoulumnSize() {
        return columns.length;
    }

    /**
     * Sets position and size of columns.
     */
    private void initColumns() {
        int xStep = getWidth() / columns.length;
        for (int i = 0; i < columns.length; i++) {
            columns[i] = new TableColumn(null, getX() + (i * xStep), y, xStep, height);
        }
    }

    /**
     * Positions the columns and its components.
     */
    private void adjustColumns() {
        int startX = this.x;
        for (int i = 0; i < columns.length; i++) {
            columns[i].setX(startX);
            startX += columns[i].getWidth();
            columns[i].setY(getY());
            columns[i].setHeight(getHeight());
            columns[i].adjustComponent();
        }
    }

    /**
     * Sets a component in the specific column.
     *
     * @param component component which should be added
     * @param column    column index
     */
    public void setComponent(Component component, int column) {
        columns[column < columns.length - 1 ? column : columns.length - 1].setComponent(component);
    }

    /**
     * Returns the component in the specific column
     *
     * @param column column index
     * @return component
     */
    public Component getComponent(int column) {
        return columns[column < columns.length - 1 ? column : columns.length - 1].getComponent();
    }

    /**
     * Returns the specific column
     *
     * @param column column index
     * @return column
     */
    public TableColumn getColumn(int column) {
        return columns[column < columns.length - 1 ? column : columns.length - 1];
    }

    /**
     * Trys to change the with of a column. Fuck never thought that this is so complicated.
     *
     * @param width  new width of column
     * @param column column index
     * @return size changed
     */
    public boolean setColumnWidth(int width, int column) {
        if (width > columns[column].getWidth()) {
            int difX = width - columns[column].getWidth();
            int maxDif = getMaxDifferenceX() - (columns[column].getWidth() - columns[column].getMinWidth());
            if (maxDif >= difX) {
                for (int i = 0; i < columns.length; i++) {
                    if (i != column) {
                        int maxColumnDif = columns[i].getWidth() - columns[i].getMinWidth();
                        if (maxColumnDif >= difX) {
                            columns[i].setWidth(columns[i].getWidth() - difX);
                            break;
                        } else {
                            columns[i].setWidth(columns[i].getWidth() - maxColumnDif);
                            difX -= maxColumnDif;
                        }
                    }
                }
                columns[column].setWidth(width);
                adjustColumns();
                return true;
            }
            return false;
        }
        if (width < columns[column].getMinWidth()) {
            width = columns[column].getMinWidth();
        }
        int difXColumns = (columns[column].getWidth() - width) / (columns.length - 1);
        for (int i = 0; i < columns.length; i++) {
            if (i != column) {
                columns[i].setWidth(columns[i].getWidth() + difXColumns);
            }
        }
        columns[column].setWidth(width);
        adjustColumns();
        return true;
    }

    /**
     * Removes a column and updates the other columns.
     *
     * @param column column index
     */
    public void removeColumn(int column) {
        int columnAdd = columns[column].getWidth() / (columns.length - 1);
        ArrayList<TableColumn> newColmns = new ArrayList<TableColumn>();
        for (int i = 0; i < columns.length; i++) {
            if (i != column) {
                columns[i].setWidth(columns[i].getWidth() + columnAdd);
                newColmns.add(columns[i]);
            }
        }
        columns = newColmns.toArray(new TableColumn[newColmns.size()]);
        adjustColumns();
    }

    public void render() {
        for (int i = 0; i < columns.length; i++) {
            columns[i].render();
        }
    }

}
