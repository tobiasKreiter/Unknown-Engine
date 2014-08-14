package com.engine.gui.transition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.main.GuiManager;
import com.engine.gui.screen.Frame;

/**
 * Created by Felix on 27.07.2014.
 */
public class Transition extends Frame {

    protected Texture oldScreenTex;
    protected int oldX = 0;
    protected int oldY = 0;

    protected Texture newScreenTex;
    protected Frame newScreen;
    protected int newX;
    protected int newY;

    protected GuiManager guiManager;

    public Transition(Frame oldS, Frame newS, int newScreenStartX, int newScreenStartY) {
        oldScreenTex = oldS.getScreenShot();
        newScreenTex = newS.getScreenShot();
        this.newScreen = newS;
        newX = newScreenStartX;
        newY = newScreenStartY;
    }

    @Override
    public void render(float delta) {
        if (newX != 0 || newY != 0) {
            doTransition();
            Graphics.drawImage(oldScreenTex, oldX, oldY, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            Graphics.drawImage(newScreenTex, newX, newY, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        } else {
            guiManager.setScreen(newScreen);
        }
    }

    public void doTransition() {
        if (newX != 0) {
            newX++;
            oldX++;
        }
        if (newY != 0) {
            newY++;
            newX++;
        }
    }

    public void setGuiManager(GuiManager guiManager) {
        this.guiManager = guiManager;
    }
}
