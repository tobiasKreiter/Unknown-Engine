package com.unknown.engine.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.engine.gui.main.GuiManager;

import java.awt.*;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = (int) dimension.getWidth();
        config.height = (int) dimension.getHeight();
        //config.fullscreen = true;
        new LwjglApplication(new GuiManager(), config);
    }
}
