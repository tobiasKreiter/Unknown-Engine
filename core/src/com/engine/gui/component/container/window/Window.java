package com.engine.gui.component.container.window;

import com.badlogic.gdx.Gdx;
import com.engine.gui.component.Background;
import com.engine.gui.component.Component;
import com.engine.gui.component.ComponentType;
import com.engine.gui.component.container.Container;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.main.GuiManager;

import java.util.ArrayList;

/**
 * Created by tobias on 24.07.2014.
 */
public class Window extends Container {

    //TODO: Contains Methode muss überschrieben werden und es darf nur überprüft werden, ob das Window die Koorinaten enthält und nicht der Scroll Container
    private TitleBar titleBar;
    protected Container mainContent;

    protected int scrollContentX;
    protected int scrollContentY;
    private int scrollContentWidth;
    private int scrollContentHeight;

    protected Background background;

    public Window(int x, int y, int width, int height) {
        super(x, y, width, height, ComponentType.WINDOW);
        initWindow("");
    }

    public Window(String title, int x, int y, int width, int height) {
        super(x, y, width, height, ComponentType.WINDOW);
        initWindow(title);
    }

    private void initWindow(String title) {
        initDefaultTitleBar(title);
        mainContent = new Container(0, 0, width, height-titleBar.getHeight());
        mainContent.setY(titleBar.getHeight());
        super.addChild(mainContent);
        setBackground(GuiManager.getDEFAULT_BACKGROUND());
    }

    public void initDefaultTitleBar(String title) {
        setTitleBar(new TitleBar(title, 0, 0, width, 40));
    }

    public void setTitleBar(TitleBar titleBar) {
        if (this.titleBar != null) {
            removeChildren(this.titleBar);
        }
        this.titleBar = titleBar;
        super.addChild(titleBar);
    }



    @Override
    public void render() {
        renderComponent();
        Graphics.translate(-x, -y);
        Graphics.limitDrawing(0, 0, getWidth(), getHeight());
        for (int i = 0; i < children.size(); i++) {
            children.get(i).render();
        }
        Graphics.limitEnd();
        Graphics.translate(x, y);
        if (animationManager != null) {
            animationManager.tickEnd();
        }
    }

    @Override
    public void renderComponent() {
        background.render();
    }

    /**
     * Adds a single component to the container.
     *
     * @param child component which will be added
     */
    public void addChild(Component child) {
        child.setParent(this);
        this.getMainContent().addChild(child);
    }

    /**
     * Adds a single component in the background of the conatiner.
     *
     * @param child component which will be added
     */
    public void addChildInBackground(Component child) {
        child.setParent(this);
        this.getMainContent().addChildInBackground(child);
    }

    /**
     * Adds a bunch of components to the container.
     *
     * @param children bunch of components
     */
    public void addChildren(ArrayList<Component> children) {
        for (int i = 0; i < children.size(); i++) {
            children.get(i).setParent(this);
        }
        this.getMainContent().addChildren(children);
    }

    /**
     * Returns all added children.
     *
     * @return all children
     */
    public ArrayList<Component> getChildren() {
        return getMainContent().getChildren();
    }

    /**
     * Returns the first added children.
     *
     * @return first added children
     */
    public Component getFirstChild() {
        return getMainContent().getFirstChild();
    }

    /**
     * Returns the last added children.
     *
     * @return last added children
     */
    public Component getLastChild() {
        return getMainContent().getLastChild();
    }

    /**
     * Removes a single child from the container.
     *
     * @param child
     */
    public void removeChildren(Component child) {
        child.removed(this, getParentLayer());
        this.getMainContent().removeChildren(child);
    }

    /**
     * Removes a single child at a specific position.
     *
     * @param index Index of child
     */
    public void removeChild(int index) {
        this.getMainContent().removeChild(index);
    }

    /**
     * Removes all children from the container.
     */
    public void removeAll() {
        getMainContent().removeAll();
    }

    public void moveX(int newX) {
        if (newX < 0) {
            setX(0);
        } else if (newX + getWidth() > Gdx.graphics.getWidth()) {
            setX(Gdx.graphics.getWidth() - getWidth());
        } else {
            setX(newX);
        }
    }

    public void moveY(int newY) {
        if (newY < 0) {
            setY(0);
        } else if (newY + getHeight() > Gdx.graphics.getHeight()) {
            setY(Gdx.graphics.getHeight() - getHeight());
        } else {
            setY(newY);
        }
    }

    public boolean isTitleBar() {
        return titleBar != null;
    }

    public TitleBar getTitleBar() {
        return titleBar;
    }

    public int getScrollContentWidth() {
        return scrollContentWidth;
    }

    public int getScrollContentHeight() {
        return scrollContentHeight;
    }

    public Container getMainContent() {
        return mainContent;
    }

    public int getScrollContentX() {
        return scrollContentX;
    }

    public int getScrollContentY() {
        return scrollContentY;
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
        background.setComponent(this);
    }
}
