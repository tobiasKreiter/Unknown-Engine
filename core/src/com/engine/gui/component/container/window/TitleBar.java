package com.engine.gui.component.container.window;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.engine.gui.component.*;
import com.engine.gui.component.container.Container;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.main.GuiManager;

/**
 * Created by tobias on 24.07.2014.
 */
public class TitleBar extends Container<Component> {

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
     * Default: 2
     */
    private int margin = 2;

    /**
     * Relative click position
     */
    private int rClickX;
    private int rClickY;

    /**
     * Background of titlebar
     */
    protected Background background;

    /**
     * color of the border of the titlebar
     */
    protected Color borderColor = Color.valueOf("7E8289");

    /**
     * width of border
     */
    protected int borderWidth = 2;

    public TitleBar(String title, int x, int y, int width, int height) {
        super(x, y, width, height, ComponentType.TITLEBAR);
        initCloseButton();
        adjustAll(title);
    }

    /**
     * Draws the container.
     */
    @Override
    public void render() {
        if (background != null) {
            background.render();
        }
        renderBorder();
        renderComponent();
        Graphics.translate(-x, -y);
        Graphics.limitDrawing(0, 0, getWidth(), getHeight());
        for (int i = 0; i < children.size(); i++) {
            children.get(i).render();
        }
        Graphics.limitEnd();
        Graphics.translate(x, y);
        if (animationManager != null) {
            animationManager.tickEnd();
        }
    }

    /**
     * render the border
     */
    private void renderBorder() {
        Graphics.drawFilledRect(x, y, getWidth(), borderWidth, borderColor);//Top
        Graphics.drawFilledRect(x, y, borderWidth, getHeight(), borderColor);//Left
        Graphics.drawFilledRect(x + getWidth() - borderWidth, y, borderWidth, getHeight(), borderColor);//Right
        Graphics.drawFilledRect(x, y + getHeight() - borderWidth, getWidth(), borderWidth, borderColor);//Bottom
    }

    /**
     * initialize the close button
     */
    private void initCloseButton() {
        closeButton = new Button("Close", 0, margin);
        closeButton.setBorderWidth(0);
        closeButton.addInputListener(new InputAdapter() {
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                getParentLayer().removeComponent(getParent());
                return false;
            }
        });
    }

    /**
     * all components are adjusted
     *
     * @param title
     */
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

    /**
     * relative click is stored
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (activeComponent != null) {
            activeComponent.touchDown(screenX, screenY, pointer, button);
        } else {
            rClickX = screenX;
            rClickY = screenY;
            getParentLayer().setComponentToForeground(getParent());
        }
        return false;
    }

    /**
     * Window is moved
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @return
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (activeComponent != null) {
            activeComponent.touchDragged(screenX, screenY, pointer);
        } else {
            ((Window) getParent()).moveX(parent.getX() + screenX - rClickX);
            ((Window) getParent()).moveY(parent.getY() + screenY - rClickY);
        }
        return false;
    }

    public void setFont(BitmapFont font) {
        title.setFont(font);
    }

    public BitmapFont getFont() {
        return title.getFont();
    }

    public void setFontColor(Color color) {
        title.setFontColor(color);
    }

    public Color getFontColor() {
        return title.getFontColor();
    }

    public Background getBackground() {
        return background;
    }

    /**
     * Background is set and {@param background} get the TitleBar
     */
    public void setBackground(Background background) {
        this.background = background;
        background.setComponent(this);
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }
}
