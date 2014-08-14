package com.engine.gui.component.container.window;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.engine.gui.component.Button;
import com.engine.gui.component.InputAdapter;
import com.engine.gui.component.Label;
import com.engine.gui.component.container.Container;
import com.engine.gui.main.GuiManager;

/**
 * Created by tobias on 24.07.2014.
 */
public class TitleBar extends Container {

    /**
     * Is displayed as title of a window.
     */
    private Label title;
    /**
     * Button which closes the window
     */
    private Button closeButton;
    /**
     * Contains the margin which has the title and the closebutton to the titlebar.
     * Default: 5
     */
    private int margin = 5;

    /**
     * Relative click position
     */
    private int rClickX;
    private int rClickY;

    public TitleBar(String title, int x, int y, int width, int height) {
        super(x, y, width, height);
        initCloseButton();
        adjustAll(title);
    }

    private void initCloseButton() {
        closeButton = new Button("Close", 0, margin);
        closeButton.addInputListener(new InputAdapter() {
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                getParentLayer().removeComponent(getParent());
                return false;
            }
        });
    }

    private void adjustAll(String title) {
        BitmapFont font = GuiManager.getNormalFont();
        if (font.getBounds(title).width + margin * 3 + closeButton.getWidth() > getWidth()) {
            int i = title.length();
            do {
                title = title.substring(0, i) + "...";
            } while (font.getBounds(title).width + margin * 3 + closeButton.getWidth() > getWidth());
        }
        this.title = new Label(title, margin, margin);
        this.closeButton.setX(getWidth() - margin - closeButton.getWidth()); //Moves button to the left side
        addChild(this.title);
        addChild(closeButton);
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (activeComponent != null) {
            activeComponent.touchDown(screenX, screenY, pointer, button);
        } else {
            rClickX = screenX - parent.getX();
            rClickY = screenY - parent.getY();
            getParentLayer().setComponentToForeground(getParent());
        }
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (activeComponent != null) {
            activeComponent.touchDragged(screenX, screenY, pointer);
        } else {
            ((Window) getParent()).moveX(screenX - rClickX);
            ((Window) getParent()).moveY(screenY - rClickY);
        }
        return false;
    }
}
