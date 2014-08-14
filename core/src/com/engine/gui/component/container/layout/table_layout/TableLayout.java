package com.engine.gui.component.container.layout.table_layout;

import com.badlogic.gdx.graphics.Color;
import com.engine.gui.component.Component;
import com.engine.gui.component.container.layout.Layout;
import com.engine.gui.graphics.Graphics;

import java.util.ArrayList;

/**
 * Created by tobias on 23.07.2014.
 */
public class TableLayout extends Layout {

    /**
     * TODO: setWidth and setHeight
     */

    /**
     * All rows of the table.
     */
    private TableRow[] tableRows;

    public TableLayout(int x, int y, int width, int height) {
        super(x, y, width, height);
        prepareTable(0, 0);
    }

    public TableLayout(int x, int y, int width, int height, int cols, int rows) {
        super(x, y, width, height);
        prepareTable(cols, rows);
    }

    /**
     * Prepares the table.
     *
     * @param cols number of columns
     * @param rows number of rows
     */
    private void prepareTable(int cols, int rows) {
        tableRows = new TableRow[rows];
        if (rows > 0 && cols > 0) {
            int rowHeight = height / rows;
            for (int i = 0; i < rows; i++) {
                tableRows[i] = new TableRow(getX(), getY() + (i * rowHeight), getWidth(), rowHeight, cols);
            }
        }
    }

    /**
     * Sets a component in the specific table entry.
     *
     * @param component component
     * @param col       column
     * @param row       row
     */
    public void setComponent(Component component, int col, int row) {
        children.add(component);
        children.remove(tableRows[row].getComponent(col));
        tableRows[row].setComponent(component, col);
    }

    /**
     * Removes a component of a table entry.
     *
     * @param col column of entry
     * @param row row of entry
     */
    public void removeComponent(int col, int row) {
        children.remove(tableRows[row].getComponent(col));
        tableRows[row].setComponent(null, col); //Free object to prevent ml
    }

    /**
     * Adds a new row at a specific position.
     *
     * @param column   number of columns
     * @param position position of row
     */
    public void addRow(int column, int position) {
        ArrayList<TableRow> newRows = new ArrayList<TableRow>();
        int newRowHeight = getHeight() / (tableRows.length + 1);
        if (canAddRow(newRowHeight)) {
            if (position == 0) {
                newRows.add(new TableRow(getX(), getY(), getWidth(), newRowHeight, column));
            }
            for (int i = 0; i < tableRows.length; i++) {
                if (i == position && position != 0) {
                    newRows.add(new TableRow(getX(), getY(), getWidth(), newRowHeight, column));
                }
                newRows.add(tableRows[i]);
            }
            if (position == tableRows.length && position != 0) {
                newRows.add(new TableRow(getX(), getY(), getWidth(), newRowHeight, column));
            }
            for (int i = 0; i < newRows.size(); i++) {
                newRows.get(i).setY(getY() + (i * newRowHeight));
                newRows.get(i).setHeight(newRowHeight);
            }
            tableRows = newRows.toArray(new TableRow[newRows.size()]);
        }
    }

    /**
     * Adds a new row at the end of the table layout
     *
     * @param column number of columns
     */
    public void addRow(int column) {
        addRow(column, tableRows.length);
    }

    /**
     * Returns if the new height can be applied.
     *
     * @param newRowHeight new height
     * @return can be applied
     */
    private boolean canAddRow(int newRowHeight) {
        if (tableRows != null && tableRows.length > 0) {
            return tableRows[0].canSetHeight(newRowHeight);
        }
        return true;
    }

    /**
     * Sets the width of a column.
     *
     * @param width new width of column
     * @param col   index of column
     * @param row   row of column
     * @return width changed successfully
     */
    public boolean setColumnWidth(int width, int col, int row) {
        return tableRows[row].setColumnWidth(width, col);
    }

    /**
     * Sets a height of a row.
     *
     * @param height new height
     * @param row    specific row
     * @return height changed successfully
     */
    public boolean setColumnHeight(int height, int row) {
        if (!tableRows[row].canSetHeight(height)) {
            return false;
        }
        tableRows[row].setHeight(height);
        return true;
    }

    /**
     * Positions a component.
     *
     * @param leftMargin   left margin
     * @param rightMargin  right margin
     * @param topMargin    top margin
     * @param bottomMargin bottom margin
     * @param col          column
     * @param row          row
     */
    public void setMargin(int leftMargin, int rightMargin, int topMargin, int bottomMargin, int col, int row) {
        tableRows[row].getColumn(col).setMargin(leftMargin, rightMargin, topMargin, bottomMargin);
    }

    /**
     * Sets the alignment for a table entry.
     *
     * @param horizontalAlignment horizontal alignment
     * @param verticalAlignment   vertical alignment
     * @param col                 column of entry
     * @param row                 row of entry
     */
    public void setAlignment(int horizontalAlignment, int verticalAlignment, int col, int row) {
        tableRows[row].getColumn(col).setHorizontalAlignment(horizontalAlignment);
        tableRows[row].getColumn(col).setVerticalAlignment(verticalAlignment);
    }

    /**
     * Returns the nuumber of rows.
     *
     * @return number of rows
     */
    public int getRows() {
        return tableRows.length;
    }

    /**
     * Returns the number of columns of a row
     *
     * @param row specific row
     * @return number of columns
     */
    public int getColumns(int row) {
        return tableRows[row].getCoulumnSize();
    }

    /**
     * Only for testing purposes
     */
    @Override
    public void render() {
        for (int i = 0; i < children.size(); i++) {
            children.get(i).render();
        }
        for (int i = 0; i < tableRows.length; i++) {
            tableRows[i].render();
        }
        Graphics.drawRect(getX(), getY(), getWidth(), getHeight(), Color.RED);
    }


    //<---- Not allow methods ---->
    @Override

    public void addChild(Component child) {
    }

    @Override
    public void addChildren(ArrayList<Component> children) {
    }
    //<---- End of not allow methods ---->
}
