package com.engine.gui.animation;

import com.engine.gui.component.Component;

/**
 * Created by Felix on 25.07.2014.
 */
public abstract class Animation {
    /**
     * Component which will be animated
     */
    protected Component component;

    /**
     * Sets the component.
     *
     * @param component component
     */
    public void setComponent(Component component) {
        this.component = component;
        initAnimation();
    }

    /**
     * Performs the animation
     *
     * @param delta to complicate to explain
     */
    public abstract void animate(float delta);

    /**
     * Is executed when the animation was rendered
     */
    public void animationRendered(){

    }

    /**
     * Returns if the animation is finished. If true it will be removed by the animation manager.
     *
     * @return animation finished
     */
    public abstract boolean isFinished();

    /**
     * Is executed when the animation got a component.
     */
    protected abstract void initAnimation();

    /**
     * Is executed before the animation is going to be removed.
     * Can be overridden.
     */
    public void onFinish() {
    }

}
