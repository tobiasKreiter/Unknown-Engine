package com.engine.gui.animation;

import com.engine.gui.component.Component;

import java.util.ArrayList;

/**
 * Created by Felix on 25.07.2014.
 */
public class AnimationManager {

    /**
     * List of animations which will be executed. If animations are finished they will be removed from the list.
     */
    private ArrayList<Animation> animations = new ArrayList<Animation>();
    /**
     * Component-Object
     */
    private Component component;

    public AnimationManager(Component component) {
        this.component = component;
    }

    /**
     * Executes all animations.
     *
     * @param delta
     */
    public void tick(float delta) {
        if (!animations.isEmpty()) {
            if (animations.get(0).isFinished()) {
                animations.get(0).onFinish();
                animations.remove(0);
            }
            if (!animations.isEmpty()) {
                animations.get(0).animate(delta);
            }
        }
    }

    public void tickEnd() {
        if (!animations.isEmpty()) {
            animations.get(0).animationRendered();
        }
    }

    /**
     * Adds an animation to the component.
     *
     * @param animation animation
     */
    public void addAnimation(Animation animation) {
        animation.setComponent(component);
        this.animations.add(animation);
    }


}
