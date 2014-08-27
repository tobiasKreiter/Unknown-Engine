package com.engine.gui.component.container;

import com.engine.gui.component.Component;
import com.engine.gui.component.InputListener;
import com.engine.gui.component.container.scroll.ScrollComponent;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.screen.layer.ComponentLayer;

import java.util.ArrayList;

/**
 * Created by tobias on 23.07.2014.
 */
public class Container extends Component {

    /**
     * Indicates that the container is in normal mode. For details see {@link Container#containerMode}
     */
    public static final int NORMAL_MODE = 1;
    /**
     * Indicates that the container is in fit mode. For details see {@link Container#containerMode}
     */
    public static final int FIT_MODE = 2;

    /**
     * Contains all children components.
     */
    protected ArrayList<Component> children = new ArrayList<Component>();
    /**
     * Contains the active component. Same as in component layer
     */
    protected Component activeComponent = null;
    /**
     * Indicates in which mode the container. The following modes are possible:<p>
     * <ul>
     * <li>Normal mode: Container has its own size and ignores position of the child. If a child is outside the
     * its not drawn. </li>
     * <li>Fit mode:    The container size depends on the content. If the content is outside of the window it fit
     * its size.</li>
     * </ul>
     */
    private int containerMode = NORMAL_MODE;

    /**
     * @param x      X-Coordinate
     * @param y      Y-Coordinate
     * @param width  width
     * @param height height of container
     */
    public Container(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * Sets the conatiner a new mode. For details see {@link Container#containerMode}
     *
     * @param containerMode new conatiner mode
     */
    public void setContainerMode(int containerMode) {
        this.containerMode = containerMode;
    }

    /**
     * Returns the current container mode. For details see {@link Container#containerMode}
     *
     * @return current container mode
     */
    public int getContainerMode() {
        return containerMode;
    }

    /**
     * Adds a single component to the container.
     *
     * @param child component which will be added
     */
    public void addChild(Component child) {
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
    public void addChildInBackground(Component child) {
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
    public void addChildren(ArrayList<Component> children) {
        for (int i = 0; i < children.size(); i++) {
            children.get(i).setParent(this);
            children.get(i).added(this, getParentLayer());
        }
        this.children.addAll(children);
        performModeAction();
    }

    /**
     * Returns all added children.
     *
     * @return all children
     */
    public ArrayList<Component> getChildren() {
        return children;
    }

    /**
     * Returns the first added children.
     *
     * @return first added children
     */
    public Component getFirstChild() {
        return children.get(0);
    }

    /**
     * Returns the last added children.
     *
     * @return last added children
     */
    public Component getLastChild() {
        return children.get(children.size() > 0 ? children.size() - 1 : 0);
    }

    /**
     * Removes a single child from the container.
     *
     * @param child
     */
    public void removeChildren(Component child) {
        child.removed(this, getParentLayer());
        this.children.remove(child);
        performModeAction();
    }

    /**
     * Removes a single child at a specific position.
     *
     * @param index Index of child
     */
    public void removeChild(int index) {
        this.children.get(index).removed(this, getParentLayer());
        this.children.remove(index);
        performModeAction();
    }

    /**
     * Removes all children from the container.
     */
    public void removeAll() {
        for (int i = 0; i < children.size(); i++) {
            this.children.get(i).removed(this, getParentLayer());
        }
        this.children.clear();
        performModeAction();
    }

    /**
     * Performes actions depending on the mode. On normal mode nothing happens.
     */
    public void performModeAction() {
        switch (containerMode) {
            case NORMAL_MODE:
                //Do nothing
                break;
            case FIT_MODE:
                fitSize();
                break;
        }
    }

    /**
     * Fits the container size to the size of its children. Before checking, the size is set to 0 so the container can
     * shrink.
     */
    public void fitSize() {
        setWidth(0);
        setHeight(0);
        for (int i = 0; i < children.size(); i++) {
            Component child = children.get(i);
            setWidth(Math.max(getWidth(), child.getX() + child.getWidth()));
            setHeight(Math.max(getHeight(), child.getY() + child.getHeight()));
        }
    }


    //<---- Overridng methods so the container can hand off all events to its children ---->

    @Override
    public void tick(float delta) {
        tickComponent(delta);
        if (animationManager != null) {
            animationManager.tick(delta);
        }
        for (int i = 0; i < children.size(); i++) {
            children.get(i).tick(delta);
        }
    }

    /**
     * Draws the container.
     */
    @Override
    public void render() {
        Graphics.translate(-x, -y);
        Graphics.limitDrawing(0, 0, getWidth(), getHeight());
        renderComponent();
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

    }


    public void setWidth(int width) {
        this.width = width;
        onSizeChanged(true);
    }

    public void setHeight(int height) {
        this.height = height;
        onSizeChanged(false);
    }

    /**
     * Checks if a component contains the coordinates.
     * On success the component will be marked as active component
     *
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @return a component contains the coordinates
     */
    @Override
    public boolean contains(int x, int y) {
        for (int i = children.size() - 1; i >= 0; i--) {
            if (children.get(i).contains(x - this.x, y - this.y)) {
                activeComponent = children.get(i);
                return true;
            }
        }
        activeComponent = null;
        return ((x > getX() && x < getX() + getWidth()) && (y > getY() && y < getY() + getHeight()));
    }

    /**
     * <--- Overriding input listener methods
     */

    /**
     * Is executed when the focus changed to an another layer or when the focus changed to another component.
     */
    @Override
    public boolean onBlur() {
        if (activeComponent != null) {
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                li.get(i).onBlur();
            }
            activeComponent = null;
        }
        return false;
    }

    @Override
    public boolean onResize(int newWidth, int newHeight) {
        if (activeComponent != null) {
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                li.get(i).onResize(newWidth, newHeight);
            }
            activeComponent = null;
        }
        return false;
    }

    @Override
    public boolean onSizeChanged(boolean width) {
        if (activeComponent != null) {
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                li.get(i).onSizeChanged(width);
            }
            activeComponent = null;
        }
        return false;
    }

    @Override
    public boolean added(Component parent, ComponentLayer parentLayer) {
        for (int i = 0; i < children.size(); i++) {
            children.get(i).setParentLayer(parentLayer);
        }
        return false;
    }

    @Override
    public boolean removed(Component parent, ComponentLayer parentLayer) {
        for (int i = 0; i < children.size(); i++) {
            children.get(i).setParentLayer(null);
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (activeComponent != null) {
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                li.get(i).keyDown(keycode);
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (activeComponent != null) {
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                li.get(i).keyUp(keycode);
            }
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if (activeComponent != null) {
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                li.get(i).keyTyped(character);
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (activeComponent != null) {
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                li.get(i).touchDown(screenX, screenY, pointer, button);
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (activeComponent != null) {
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                li.get(i).touchUp(screenX, screenY, pointer, button);
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (activeComponent != null) {
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                li.get(i).touchDragged(screenX, screenY, pointer);
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (activeComponent != null) {
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                li.get(i).mouseMoved(screenX, screenY);
            }
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if (activeComponent != null) {
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                li.get(i).scrolled(amount);
            }
            return true;
        }
        return false;
    }
    /**
     * End Overriding ---->
     * */
}
