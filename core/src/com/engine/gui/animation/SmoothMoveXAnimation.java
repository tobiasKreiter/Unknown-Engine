package com.engine.gui.animation;

import com.badlogic.gdx.Gdx;

/**
 * Created by tobias on 26.07.2014.
 */
public class SmoothMoveXAnimation extends MoveAnimation {

    /**
     * Is required to fit the function correctly.
     */
    private static final double a = 1.0366312968;
    private static final double b = 0.0179862099;
    /**
     * Contains how long the animation takes.
     */
    private double animationDuration;
    /**
     * Time while animation started.
     */
    private double animationCounter = 0;
    /**
     * Buffer for the start X-Coordinate. Later the X-Coordinate changes so a buffer is needed.
     */
    private int startX;

    public SmoothMoveXAnimation(int endX, int duration) {
        super(endX, 0);
        this.animationDuration = duration;
    }

    /**
     * Saves the x-position of the component.
     */
    @Override
    protected void initAnimation() {
        startX = component.getX();
    }

    /**
     * Moves the component as requiered.
     *
     * @param delta to complicate to explain
     */
    @Override
    public void animate(float delta) {
        double velX = timeToX(counterToTime(animationCounter));
        component.setX((int)(startX + velX));
        animationCounter += 1000 / Gdx.graphics.getFramesPerSecond();
        if(isFinished()){ //sometimes one pixel is missing :D
            component.setX(startX + endX);
        }
    }

    /**
     * Returns true if the animation is finished.
     *
     * @return animation finished
     */
    @Override
    public boolean isFinished() {
        return animationCounter >= animationDuration;
    }

    /**
     * Runs the smooth function so the component moves with the right velocity.
     *
     * @param t relative time elapsed since start
     * @return right velocity for component
     */
    private double timeToX(double t) {
        return (a / (1 + Math.exp(-t + 4)) - b) * endX;
    }

    /**
     * converts the time to the relative time.
     *
     * @param counter time since start
     * @return relative time
     */
    private double counterToTime(double counter) {
        if (counter == 0) {
            return 0;
        }
        return (counter / animationDuration) * 8.0;
    }

}
