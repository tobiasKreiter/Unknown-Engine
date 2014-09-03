package com.engine.gui.component;

import com.engine.gui.animation.Animation;
import com.engine.gui.animation.AnimationManager;
import com.engine.gui.screen.layer.ComponentLayer;

import java.util.ArrayList;

/**
 * Created by Felix on 22.07.2014.
 */
public abstract class Component extends InputAdapter {
    /**
     * x coordinate
     */
    protected int x;

    /**
     * y coordinate
     */

    protected int y;

    /**
     * component width
     */
    protected int width;

    /**
     * component height
     */
    protected int height;


    /**
     * {@link com.engine.gui.animation.AnimationManager} manage all animations of one component
     */
    protected AnimationManager animationManager = new AnimationManager(this);

    /**
     * all InputListener of the component
     */
    protected ArrayList<InputListener> inputListener = new ArrayList<InputListener>();

    /**
     * parent component
     */
    protected Component parent;

    /**
     * the Layer where the component is added
     */
    protected ComponentLayer parentLayer;

    private ComponentType type;

    private String className;

    public Component(ComponentType type) {
        addInputListener(this);
        this.type = type;
    }

    public Component(int x, int y, ComponentType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        addInputListener(this);
    }

    public Component(int x, int y, int width, int height, ComponentType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        addInputListener(this);
    }

    /**
     * render component and execute {@link com.engine.gui.animation.AnimationManager#tickEnd()}
     */
    public void render() {
        renderComponent();
        if (animationManager != null) {
            animationManager.tickEnd();
        }
    }

    /**
     * render component itself
     */
    public abstract void renderComponent();

    /**
     * tick method which can be used by overriding
     *
     * @param delta
     */
    public void tickComponent(float delta) {

    }

    /**
     * Execute {@link com.engine.gui.animation.AnimationManager#tick(float delta)} and tick method of
     * component
     *
     * @param delta
     */
    public void tick(float delta) {
        if (animationManager != null) {
            animationManager.tick(delta);
        }
        tickComponent(delta);
    }

    public int getX() {
        return (int) x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return (int) y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addInputListener(InputListener input) {
        inputListener.add(input);
    }

    public boolean removeInputListener(InputListener input) {
        return inputListener.remove(input);
    }

    public boolean contains(int x, int y) {
        return (x > getX() && x < getX() + getWidth()) && (y > getY() && y < getY() + getHeight());
    }

    public AnimationManager getAnimationManager() {
        return animationManager;
    }

    /**
     * New animation is added to the component
     *
     * @param animation
     */
    public void addAnimation(Animation animation) {
        this.animationManager.addAnimation(animation);
    }

    public void setCompDim(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Component getParent() {
        return parent;
    }

    public void setParent(Component parent) {
        this.parent = parent;
    }

    public ComponentLayer getParentLayer() {
        return parentLayer;
    }

    public void setParentLayer(ComponentLayer parentLayer) {
        this.parentLayer = parentLayer;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<InputListener> getInputListener() {
        return inputListener;
    }

    /**
     * Returns the type of the component.
     *
     * @return type of component
     */
    public ComponentType getComponentType() {
        return type;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

}