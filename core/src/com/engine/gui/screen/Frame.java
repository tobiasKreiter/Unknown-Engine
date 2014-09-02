package com.engine.gui.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.engine.gui.component.texture.TextureManager;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.screen.layer.ComponentLayer;
import com.engine.gui.screen.layer.Layer;

import java.util.ArrayList;

/**
 * Created by tobias on 22.07.2014.
 */
public class Frame implements Screen, InputProcessor {

    /**
     * Contains all layer.
     */
    private ArrayList<Layer> layers = new ArrayList<Layer>();
    /**
     * Contains the current layer. reference changes by clicking another layer.
     */
    private Layer activeLayer = null;

    public Frame() {
        Gdx.input.setInputProcessor(this);
    }

    /**
     * Renders all layer. Hopefully correctly.
     *
     * @param delta to complex to explain
     */
    @Override
    public void render(float delta) {
        Graphics.update();
        delta = Graphics.getFixedDelta();
        for (int i = 0; i < layers.size(); i++) {
            Layer layer = layers.get(i);
            layer.tick(delta);
            layer.render();
        }
    }

    /**
     * Performs a rezsize event for all layers.
     *
     * @param width  new width
     * @param height new height
     */
    @Override
    public void resize(int width, int height) {
        Graphics.initGraphics();
        for (int i = 0; i < layers.size(); i++) {
            layers.get(i).onResize(width, height);
        }
    }

    /**
     * Adds a new layer to the hirachie.
     *
     * @param layer new Layer
     */
    public void addLayer(Layer layer) {
        layers.add(layer);
    }

    /**
     * Moves a leyer to the specific index.
     *
     * @param layer Layer which will be moved
     * @param index new index
     */
    public void moveLayer(Layer layer, int index) {
        layers.remove(layer);
        layers.add(index, layer);
    }

    /**
     * Removes a layer from the hirachie.
     *
     * @param layer layer which will be removed
     */
    public void removeLayer(Layer layer) {
        layers.remove(layer);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        TextureManager.disposeAll();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (activeLayer != null) {
            activeLayer.keyDown(keycode);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (activeLayer != null) {
            activeLayer.keyUp(keycode);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if (activeLayer != null) {
            activeLayer.keyTyped(character);
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Layer newActiveLayer = getLayer(screenX, screenY);
        if (newActiveLayer == null) {
            if (activeLayer != null) {
                activeLayer.onBlur();
            }
            activeLayer = null;
        } else {
            if (newActiveLayer != activeLayer) {
                if (activeLayer != null) {
                    activeLayer.onBlur();
                }
            }
            activeLayer = newActiveLayer;
            activeLayer.touchDown(screenX, screenY, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Layer newActiveLayer = getLayer(screenX, screenY);
        if (newActiveLayer == activeLayer && newActiveLayer != null) {
            activeLayer.touchUp(screenX, screenY, pointer, button);
            activeLayer = newActiveLayer;
        } else if(newActiveLayer == null){
            if(activeLayer != null){
                activeLayer.touchUp(screenX,screenY,pointer,button);
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (activeLayer != null) {
            activeLayer.touchDragged(screenX, screenY, pointer);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (activeLayer != null) {
            activeLayer.mouseMoved(screenX, screenY);
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if (activeLayer != null) {
            activeLayer.scrolled(amount);
        }
        return false;
    }


    private Layer getLayer(int x, int y) {
        for (int i = layers.size() - 1; i >= 0; i--) {
            if (layers.get(i).contains(x, y)) {
                return layers.get(i);
            }
        }

        return null;
    }

    public Texture getScreenShot() {
        FrameBuffer frameBuffer;
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        frameBuffer = new FrameBuffer(Pixmap.Format.RGB888, width, height, false);
        TextureRegion texture = new TextureRegion(frameBuffer.getColorBufferTexture());
        frameBuffer.begin();
        render(1);
        frameBuffer.end();
        frameBuffer = new FrameBuffer(Pixmap.Format.RGB888, width, height, false);
        frameBuffer.begin();
        Graphics.drawImage(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Texture tex = frameBuffer.getColorBufferTexture();
        frameBuffer.end();
        frameBuffer.dispose();
        return tex;
    }
}
