package com.engine.gui.animation;

/**
 * Created by Felix on 26.07.2014.
 */
public class SimpleMoveAnimation extends MoveAnimation {

    public SimpleMoveAnimation(int endX, int endY) {
        super(endX, endY);
    }


    @Override
    public void animate(float delta) {
        if (component.getX() != endX || component.getY() != endY) {
            if (component.getX() < endX) {//links
                if (component.getX() + velX > endX) {
                    component.setX(endX);
                } else if (component.getX() + velX < endX) {
                    component.setX(component.getX() + velX);
                }
            } else {//rechts
                if (component.getX() + velX < endX) {
                    component.setX(endX);
                } else if (component.getX() + velX > endX) {
                    component.setX(component.getX() + velX);
                }
            }
            if (component.getY() < endY) {//links
                if (component.getY() + velY > endY) {
                    component.setY(endY);
                } else if (component.getY() + velY < endY) {
                    component.setY(component.getY() + velY);
                }
            } else {//rechts
                if (component.getY() + velY < endY) {
                    component.setY(endY);
                } else if (component.getY() + velY > endY) {
                    component.setY(component.getY() + velY);
                }
            }
        }
    }

    @Override
    public boolean isFinished() {
        return component.getX() ==  endX && component.getY() == endY;
    }
}
