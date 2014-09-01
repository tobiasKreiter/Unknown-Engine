package com.engine.gui.component.texture;

import com.badlogic.gdx.graphics.Color;
import com.engine.gui.component.Background;
import com.engine.gui.component.Button;
import com.engine.gui.component.ComponentType;
import com.engine.gui.component.Label;
import com.engine.gui.component.container.Container;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.main.GuiManager;

import java.util.ArrayList;

/**
 * Created by Felix on 01.09.2014.
 */
public class DropdownMenuTest extends Container<Button> {


    private boolean expanded = false;
    private int foldHeight;

    private Label selected;

    protected Background background;

    protected Color borderColor = Color.valueOf("7E8289");

    protected int borderWidth = 2;

    /**
     * @param x
     * @param y
     * @param width  not extended with
     * @param height not extended height
     */
    public DropdownMenuTest(int x, int y, int width, int height) {
        super(x, y, width, height, ComponentType.DROPDOWN_MENU);
        setBackground(GuiManager.getDEFAULT_BACKGROUND());
        foldHeight = height;
        addChild(new Button("please select", 0, 0));
        selected = children.get(0);
    }

    /**
     * Draws the container.
     */
    @Override
    public void render() {
        renderComponent();
        Graphics.translate(-x, -y);
        Graphics.limitDrawing(0, 0, getWidth(), getHeight());
        if (selected != null) {
            selected.render();
        }
        if (expanded) {
            for (int i = 0; i < children.size(); i++) {
                if (selected != children.get(i)) {
                    children.get(i).render();
                }
            }
        }
        Graphics.limitEnd();
        Graphics.translate(x, y);
        if (animationManager != null) {
            animationManager.tickEnd();
        }
    }

    @Override
    public void renderComponent() {
        if (background != null) {
            //background.render();
        }
        renderBorder();
    }

    private void renderBorder() {
        Graphics.drawFilledRect(x, y, getWidth(), borderWidth, borderColor);//Top
        Graphics.drawFilledRect(x, y, borderWidth, getHeight(), borderColor);//Left
        Graphics.drawFilledRect(x + getWidth() - borderWidth, y, borderWidth, getHeight(), borderColor);//Right
        Graphics.drawFilledRect(x, y + getHeight() - borderWidth, getWidth(), borderWidth, borderColor);//Bottom
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (expanded) {
            fold(screenX, screenY);
        } else {
            expand();
        }
        return false;
    }

    /**
     * Adds a single component to the container.
     *
     * @param child component which will be added
     */
    public void addChild(Button child) {
        child.setWidth(this.width);
        child.setBorderWidth(0);
        child.setParent(this);
        child.added(this, getParentLayer());
        this.children.add(child);
        performModeAction();
    }

    /**
     * Adds a single component in the background of the conatiner.
     *
     * @param child component which will be added
     */
    public void addChildInBackground(Button child) {
        child.setWidth(this.width);
        child.setBorderWidth(0);
        child.setParent(this);
        child.added(this, getParentLayer());
        this.children.add(0, child);
        performModeAction();
    }

    /**
     * Adds a bunch of components to the container.
     *
     * @param children bunch of components
     */
    public void addChildren(ArrayList<Button> children) {
        for (int i = 0; i < children.size(); i++) {
            children.get(i).setWidth(this.width);
            children.get(i).setBorderWidth(0);
            children.get(i).setParent(this);
            children.get(i).added(this, getParentLayer());
        }
        this.children.addAll(children);
        performModeAction();
    }

    public void test(String test) {
        addChild(new Button(test, 0, 0));
    }

    private void expand() {
        expanded = true;
        int height = selected.getHeight();
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i) != selected) {
                children.get(i).setY(height);
                height += children.get(i).getHeight();
            }
        }
        setHeight(height);
    }

    private void fold(int x, int y) {
        x -= getX();
        y -= getY();
        expanded = false;
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).contains(x, y)) {
                selected = children.get(i);
            }
        }
        selected.setY(0);
        setHeight(foldHeight);
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
        background.setComponent(this);
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }
}
