package com.engine.gui.animation;

/**
 * Created by Felix on 26.07.2014.
 */
public abstract class MoveAnimation extends Animation {

    public MoveAnimation(int endX, int endY) {
        this.endX = endX;
        this.endY = endY;
    }

    protected int endX;
    protected int endY;

    protected int velX = 1;
    protected int velY = 1;

    @Override
    protected void initAnimation() {
        if (component.getX() > endX) {
            velX *= -1;
        }

        if (component.getY() > endY) {
            velY *= -1;
        }
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
}
