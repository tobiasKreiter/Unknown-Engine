package com.engine.gui.animation;

import java.util.ArrayList;

/**
 * Created by Felix on 31.07.2014.
 */
public class AnimationGroup extends Animation {
    //TODO: Animation Group testen
    private ArrayList<Animation> animations;

    public AnimationGroup(ArrayList<Animation> animations) {
        this.animations = animations;
    }

    @Override
    public void animate(float delta) {
        for (int i = 0; i < animations.size(); i++) {
            animations.get(i).animate(delta);
            if (animations.get(i).isFinished()) {
                animations.get(i).onFinish();
                animations.remove(animations.get(i));
            }
        }
    }

    @Override
    public boolean isFinished() {
        return animations.size() == 0;
    }

    @Override
    protected void initAnimation() {
        for (int i = 0; i < animations.size(); i++) {
            animations.get(i).setComponent(component);
        }
    }
}
