package com.engine.gui.component;

import com.badlogic.gdx.graphics.Color;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.screen.layer.ComponentLayer;

import java.util.Collection;

/**
 * Created by Hannes on 06.08.2014.
 */
public class DropdownButton extends Button {

    private DropdownMenu dropdownMenu;
    private ComponentLayer componentLayer;

    public DropdownButton(int x, int y, ComponentLayer componentLayer) {
        super("Dropdown Button", x, y);
        setPressedColor(Color.LIGHT_GRAY);

        this.componentLayer = componentLayer;
        dropdownMenu = new DropdownMenu(this);
    }

    public DropdownMenu getDropdownMenu() {
        return dropdownMenu;
    }

    public void labelPressed(DropdownLabel dropdownLabel) {
        setText("Selected: " + dropdownLabel.getText());
        removeAllComponentsFromLayer();
        setPressed(false);
    }

    public void test(String text) {
        dropdownMenu.addDropdownLabel(new DropdownLabel(text, 0,0, dropdownMenu));
    }

    public void renderComponent() {
        background.render();
        if(pressed){
            Graphics.drawText(getText(), getX() + 1, getY() + 1, pressedColor, font);
            dropdownMenu.renderComponent();
        }else {
            Graphics.drawText(getText(), getX() + 1, getY() + 1, foreground, font);
        }
    }

    private  void removeAllComponentsFromLayer() {
        DropdownLabel[] array = dropdownMenu.getLabelArray();
        for (int i = 0; i < array.length; i++) {
            componentLayer.removeComponent(array[i]);
        }
    }

    private void addAllComponentsToLayer() {
        DropdownLabel[] array = dropdownMenu.getLabelArray();
        for (int i = 0; i < array.length; i++) {
            componentLayer.addComponent(array[i]);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        setPressed(!isPressed());
        if (isPressed()) {
            addAllComponentsToLayer();
        } else {
            removeAllComponentsFromLayer();
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
}
