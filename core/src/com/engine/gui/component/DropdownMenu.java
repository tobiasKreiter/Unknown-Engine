package com.engine.gui.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.main.GuiManager;

import java.util.Collection;
import java.util.Vector;

/**
 * Created by Hannes on 06.08.2014.
 */
public class DropdownMenu extends Component {

    //TODO: Scrollbar fehlt noch

    public final int minHeight = 250;

    private DropdownButton parentButton;
    private Vector<DropdownLabel> dropdownLabelVector;

    protected Background background;

    protected Color borderColor = Color.BLACK;

    protected int borderWidth = 2;

    public DropdownMenu(DropdownButton parentButton) {
        super(ComponentType.DROPDOWN_MENU);
        this.parentButton = parentButton;
        dropdownLabelVector = new Vector<DropdownLabel>();

        setX(parentButton.getX());
        setWidth(parentButton.getWidth());
        calcYAndHeight();
    }

    public DropdownMenu(DropdownButton parentButton, Collection<DropdownLabel> dropdownLabels) {
        super(ComponentType.DROPDOWN_MENU);
        this.parentButton = parentButton;
        dropdownLabelVector = new Vector<DropdownLabel>();
        dropdownLabelVector.addAll(dropdownLabels);

        setX(parentButton.getX());
        setWidth(parentButton.getWidth());
        calcYAndHeight();
    }

    public void labelPressed(DropdownLabel dropdownLabel) {
        parentButton.labelPressed(dropdownLabel);
    }

    public void calcYAndHeight() {
        if (Gdx.graphics.getHeight() - parentButton.getY() < minHeight) {
            // zuwenig platz unter button -> menü über button

            if (getMenuHeight() <= parentButton.getY()) {
                // menu passt zwischen oberes ende und dropdownbutton

                setY(parentButton.getY() - getMenuHeight());
                setHeight(getMenuHeight());
            } else {
                // menu länger länger als strecke zwischen oberem ende und dropdownbutton

                setY(0);
                setHeight(parentButton.getY());
            }
        } else {
            // genug platz unter button -> menü unter button

            setY(parentButton.getY() + parentButton.getHeight());
            setHeight(Gdx.graphics.getHeight() - y);
        }
    }

    public void addDropdownLabel(DropdownLabel dropdownLabel) {
        dropdownLabel.setWidth(getWidth());
        dropdownLabel.setX(getX());
        dropdownLabelVector.add(dropdownLabel);
    }

    public void removeDropdownLabel(DropdownLabel dropdownLabel) {
        dropdownLabelVector.remove(dropdownLabel);
    }

    public void removeDropdownLabel(int index) {
        dropdownLabelVector.remove(index);
    }

    public int getMenuHeight() {
        int height = 0;
        for (DropdownLabel dropdownLabel : dropdownLabelVector) {
            height += dropdownLabel.getHeight();
        }
        return height;
    }

    public Vector<DropdownLabel> getLabelVector() {
        return dropdownLabelVector;
    }

    public DropdownLabel[] getLabelArray() {
        DropdownLabel[] array = new DropdownLabel[dropdownLabelVector.size()];
        dropdownLabelVector.toArray(array);
        return array;
    }

    public void calcYofLabelsInVector() {
        int labelY = getY();
        for (DropdownLabel dropdownLabel : dropdownLabelVector) {
            dropdownLabel.setY(labelY);
            labelY += dropdownLabel.getHeight();
        }
    }

    /**
     * render component and execute {@link com.engine.gui.animation.AnimationManager#tickEnd()}
     */
    public void render() {
        /**
        if (background != null) {
            background.render();
        }
         **/
        renderComponent();
        if (animationManager != null) {
            animationManager.tickEnd();
        }
    }

    @Override
    public void renderComponent() {
        calcYAndHeight();
        Graphics.drawFilledRect(getX(), getY(), getWidth(), getMenuHeight(), Color.BLUE);
        renderBorder();
        calcYofLabelsInVector();
        for (DropdownLabel dropdownLabel : dropdownLabelVector) {
            dropdownLabel.renderComponent();
        }
    }

    private void renderBorder() {
        Graphics.drawFilledRect(x, y, getWidth(), borderWidth, borderColor);//Top
        Graphics.drawFilledRect(x, y, borderWidth, getMenuHeight(), borderColor);//Left
        Graphics.drawFilledRect(x + getWidth() - borderWidth, y, borderWidth, getMenuHeight(), borderColor);//Right
        Graphics.drawFilledRect(x, y + getMenuHeight() - borderWidth, getWidth(), borderWidth, borderColor);//Bottom
    }


    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
        background.setComponent(this);
    }
}
