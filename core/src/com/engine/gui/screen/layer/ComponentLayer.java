package com.engine.gui.screen.layer;

import com.engine.gui.component.Component;
import com.engine.gui.component.InputListener;
import com.engine.gui.screen.Frame;

import java.util.ArrayList;

/**
 * Created by tobias on 22.07.2014.
 */
public class ComponentLayer extends Layer {

    /**
     * Contains all components which will be updated and drawn.
     */
    private ArrayList<Component> components = new ArrayList<Component>();
    /**
     * Contains the component with the focus.
     */
    private Component activeComponent = null;

    public ComponentLayer(Frame parentFrame) {
        super(parentFrame);
    }

    /**
     * Adds a new component to the layer.
     *
     * @param component new Component
     */
    public void addComponent(Component component) {
        doAdded(component);
        this.components.add(component);
    }

    /**
     * Adds a list of components to the layer.
     *
     * @param components list of components.
     */
    public void addComponents(ArrayList<Component> components) {
        for (int i = 0; i < components.size(); i++) {
            doAdded(components.get(i));
        }
        this.components.addAll(components);
    }

    /**
     * Removes a component from the layer.
     *
     * @param component component which will be removed
     */
    public void removeComponent(Component component) {
        doRemoved(component);
        this.components.remove(component);
    }

    /**
     * Removes a component a a specific index.
     *
     * @param index Index of component
     */
    public void removeComponent(int index) {
        doRemoved(this.components.get(index));
        this.components.remove(index);
    }

    /**
     * Moves a component in the fontColor.
     *
     * @param component component which will be moved
     */
    public void setComponentToForeground(Component component) {
        int index = components.indexOf(component);
        if (index != -1) {
            components.remove(index);
            components.add(component);
        }
    }

    /**
     * Returns the index of the component.
     *
     * @param component component
     * @return index of the component.
     */
    public int getIndexOfComponent(Component component) {
        return components.indexOf(component);
    }

    /**
     * Updates all components.
     *
     * @param delta to complex to explain
     */
    @Override
    public void tick(float delta) {
        for (int i = 0; i < components.size(); i++) {
            components.get(i).tick(delta);
        }
    }

    /**
     * Renders all components.
     */
    @Override
    public void render() {
        for (int i = 0; i < components.size(); i++) {
            components.get(i).render();
        }
    }

    /**
     * Performs a resize event to all components
     *
     * @param newWidth  newWidth
     * @param newHeight newHeight
     */
    @Override
    public boolean onResize(int newWidth, int newHeight) {
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            for (int j = 0; j < component.getInputListener().size(); j++) {
                component.getInputListener().get(j).onResize(newWidth, newHeight);
            }
        }
        return false;
    }

    @Override
    public boolean onSizeChanged(int deltaWidth, int deltaHeight) {
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            for (int j = 0; j < component.getInputListener().size(); j++) {
                component.getInputListener().get(j).onSizeChanged(deltaWidth, deltaHeight);
            }
        }
        return false;
    }


    //Die beiden Event Methoden werden in Component Layer nicht benötigt, müssen jedoch überschrieben werden
    @Override
    public boolean added(Component parent, ComponentLayer parentLayer) {
        return false;
    }

    @Override
    public boolean removed(Component parent, ComponentLayer parentLayer) {
        return false;
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
        for (int i = components.size() - 1; i >= 0; i--) {
            if (components.get(i).contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is executed when the focus changed to an another layer or when the focus changed to another component.
     */
    @Override
    public boolean onBlur() {
        if (activeComponent != null) {
            boolean overridedInListener = false; //indicates if the active component used onBlur.
            ArrayList<InputListener> inputListener = activeComponent.getInputListener();
            for (int i = 0; i < inputListener.size(); i++) {
                if (inputListener.get(i).onBlur()) {
                    overridedInListener = true;
                }
            }
            return overridedInListener;
        }
        return false;
    }

    @Override
    public boolean onFocus() {
        if (activeComponent != null) {
            boolean overridedInListener = false; //indicates if the active component used onBlur.
            ArrayList<InputListener> inputListener = activeComponent.getInputListener();
            for (int i = 0; i < inputListener.size(); i++) {
                if (inputListener.get(i).onFocus()) {
                    overridedInListener = true;
                }
            }
            return overridedInListener;
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (activeComponent != null) {
            boolean overridedInListener = false;
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                if (li.get(i).keyDown(keycode)) {
                    overridedInListener = true;
                }
            }
            return overridedInListener;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (activeComponent != null) {
            boolean overridedInListener = false;
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                if (li.get(i).keyUp(keycode)) {
                    overridedInListener = true;
                }
            }
            return overridedInListener;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if (activeComponent != null) {
            boolean overridedInListener = false;
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                if (li.get(i).keyTyped(character)) {
                    overridedInListener = true;
                }
            }
            return overridedInListener;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Component at = getComponentAt(screenX, screenY);
        if (at != null) {
            if (at != activeComponent) {
                if (activeComponent != null) {
                    activeComponent.onBlur();
                }
                at.onFocus();
                activeComponent = at;
                setComponentToForeground(activeComponent);
            }
            boolean overridedInListener = false;
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                if (li.get(i).touchDown(screenX, screenY, pointer, button)) {
                    overridedInListener = true;
                }
            }
            return overridedInListener;
        }
        return false;
    }

    /**
     * If <i>at</i> doesn't equals with <i>activeComponent</i> the focus is lost (activeComponent == null).
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Component at = getComponentAt(screenX, screenY);
        if (at != activeComponent) {
            if (activeComponent != null) {
                activeComponent.onBlur();
                activeComponent = null;
            }
        } else {
            boolean overridedInListener = false;
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                if (li.get(i).touchUp(screenX, screenY, pointer, button)) {
                    overridedInListener = true;
                }
            }
            return overridedInListener;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (activeComponent != null) {
            boolean overridedInListener = false;
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                if (li.get(i).touchDragged(screenX, screenY, pointer)) {
                    overridedInListener = true;
                }
            }
            return overridedInListener;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        //TODO check if necessary to check every move
        if (activeComponent != null) {
            boolean overridedInListener = false;
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                if (li.get(i).mouseMoved(screenX, screenY)) {
                    overridedInListener = true;
                }
            }
            return overridedInListener;
        }
        return false;
    }


    @Override
    public boolean scrolled(int amount) {
        if (activeComponent != null) {
            boolean overridedInListener = false;
            ArrayList<InputListener> li = activeComponent.getInputListener();
            for (int i = 0; i < li.size(); i++) {
                if (li.get(i).scrolled(amount)) {
                    overridedInListener = true;
                }
            }
            return overridedInListener;
        }
        return false;
    }

    private void doAdded(Component comp) {
        comp.setParent(null);
        comp.setParentLayer(this);
        ArrayList<InputListener> li = comp.getInputListener();
        for (int i = 0; i < li.size(); i++) {
            li.get(i).added(null, this);
        }
    }

    private void doRemoved(Component comp) {
        comp.setParent(null);
        comp.setParentLayer(this);
        ArrayList<InputListener> li = comp.getInputListener();
        for (int i = 0; i < li.size(); i++) {
            li.get(i).removed(null, this);
        }
    }

    private Component getComponentAt(int x, int y) {
        for (int i = components.size() - 1; i >= 0; i--) {
            if (components.get(i).contains(x, y)) {
                return components.get(i);
            }
        }
        return null;
    }
}
