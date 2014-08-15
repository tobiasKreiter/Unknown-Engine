package com.engine.gui.test;

import com.badlogic.gdx.Gdx;
import com.engine.gui.component.Label;
import com.engine.gui.screen.Frame;
import com.engine.gui.screen.layer.ComponentLayer;

/**
 * Created by tobias on 25.07.14.
 */
public class DevelopmentLayer extends ComponentLayer {

    private final int MEASURETRIGGER = 1000;
    private final int PERFECT_FPS = 60;

    private int measureCounter = 0;

    private Label fps;
    private Label javaHeap;
    private Label nativeHeap;
    private Label online;

    public DevelopmentLayer(Frame parentFrame) {
        super(parentFrame);
        initMeasurementComponents();
    }

    private void initMeasurementComponents() {
        fps = new Label("FPS: 0", 10, 10);
        javaHeap = new Label("Heap:", 10, fps.getY() + fps.getHeight() + 10);
        nativeHeap = new Label("N. Heap:", 10, javaHeap.getY() + javaHeap.getHeight() + 10);
        online = new Label("Online: false", 10, nativeHeap.getY() + nativeHeap.getHeight() + 10);
        addComponent(fps);
        addComponent(javaHeap);
        addComponent(nativeHeap);
        addComponent(online);
    }


    @Override
    public void tick(float delta) {
        if (measureCounter < MEASURETRIGGER) {
            measureCounter += (MEASURETRIGGER / PERFECT_FPS) * delta;
        } else {
            measureCounter = 0;
            System.out.println("update");
            updateInfo();
        }
    }

    private void updateInfo() {
        fps.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
        javaHeap.setText("Heap: " + (Math.round((Gdx.app.getJavaHeap() / (1024f * 1024f)) * 100.0) / 100.0) + " mb");
        nativeHeap.setText("N. Heap: " + (Math.round((Gdx.app.getNativeHeap() / (1024f * 1024f)) * 100.0) / 100.0) + " mb");
    }

    @Override
    public boolean contains(int x, int y) {
        return false;
    }

    public void updateUpdateStatus(boolean online) {
        this.online.setText("Online: " + online);
    }
}
