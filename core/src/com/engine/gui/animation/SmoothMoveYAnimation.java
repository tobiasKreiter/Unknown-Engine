package com.engine.gui.animation;

import com.badlogic.gdx.Gdx;

/**
 * Created by tobias on 28.07.2014.
 */
public class SmoothMoveYAnimation extends MoveAnimation {

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
     * Buffer for the start Y-Coordinate. Later the Y-Coordinate changes so a buffer is needed.
     */
    private int startY;

    public SmoothMoveYAnimation(int endY, int duration) {
        super(0, endY);
        this.animationDuration = duration;
    }

    /**
     * Saves the y-position of the component.
     */
    @Override
    protected void initAnimation() {
        startY = component.getY();
    }

    /**
     * Moves the component as requiered.
     *
     * @param delta to complicate to eyplain
     */
    @Override
    public void animate(float delta) {
        double velY = timeToY(counterToTime(animationCounter));
        component.setY((int) (startY + velY));
        animationCounter += 1000 / Gdx.graphics.getFramesPerSecond();
        if (isFinished()) {
            component.setY(startY + endY);
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
    private double timeToY(double t) {
        return (a / (1 + Math.exp(-t + 4)) - b) * endY;
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
