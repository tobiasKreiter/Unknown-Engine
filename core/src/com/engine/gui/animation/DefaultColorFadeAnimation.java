package com.engine.gui.animation;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by tobias on 30.06.2014.
 */
public class DefaultColorFadeAnimation extends Animation {

    int animationDuration;   // each animation will take 2 seconds
    long animStartTime = 100;         // start time for each animation
    long totalTime;
    private Color startColor;
    private Color endColor;
    private Color animatedColor;

    public DefaultColorFadeAnimation(Color animatedColor, Color endColor, int animationDuration) {
        this.startColor = animatedColor;
        this.endColor = endColor;
        this.animatedColor = animatedColor;
        this.animationDuration = animationDuration;
    }

    @Override
    public void animate(float delta) {
        long currentTime = System.nanoTime() / 1000000;
        totalTime = currentTime - animStartTime;
        if (totalTime > animationDuration) {
            animStartTime = currentTime;
        }
        float fraction = (float) totalTime / animationDuration;
        fraction = Math.min(1.0f, fraction);
        // interpolate between start and end colors with current fraction
        int red = (int) (((int) (endColor.r * 255) - (int) (startColor.r * 255)) * fraction + (int) (startColor.r * 255));
        int green = (int) (((int) (endColor.g * 255) - (int) (startColor.g * 255)) * fraction + (int) (startColor.g * 255));
        int blue = (int) (((int) (endColor.b * 255) - (int) (startColor.b * 255)) * fraction + (int) (startColor.b * 255));
        // set our new color appropriately
        animatedColor = new Color(red / 255f, green / 255f, blue / 255f, 1f);
    }

    @Override
    public boolean isFinished() {
        return (float) totalTime / animationDuration >= 1f;
    }

    @Override
    protected void initAnimation() {

    }
}
