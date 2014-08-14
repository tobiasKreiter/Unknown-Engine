package com.engine.gui.component;

import com.engine.gui.graphics.Graphics;
import com.engine.gui.main.GuiManager;

/**
 * Created by Felix on 23.07.2014.
 */
public class RadioButton extends CheckBox {
    /**
     * If button belongs to a {@link com.engine.gui.component.RadioButtonGroup} group != null
     */
    private RadioButtonGroup group;

    public RadioButton(String text, int x, int y) {
        super(x, y);
        this.setText(text);
    }

    /**
     * render button
     */

    @Override
    public void renderComponent() {
        int rad = getHeight() / 2;
        Graphics.drawCircle(getX() + rad, getY() + rad, rad, foreground);
        if (isChecked()) {
            Graphics.drawFilledCircle(getX() + rad, getY() + rad, rad - 3, foreground);
        }
        Graphics.drawText(getText(), getX() + getHeight() + 2, getY() + 1, foreground, font);
    }

    /**
     * Size of the button adjusts to the {@link Label#text}
     */
    @Override
    public void refreshComponentDimension() {
        setHeight(getTextHeight() + 1);
        setWidth(getTextWidth() + getHeight() + 2);
    }

    /**
     * if button is clicked all other buttons in group will be set state 'unselected'
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        setChecked(true);
        RadioButtonGroup group = getGroup();
        if (group != null) {
            group.checked(this);
        }
        return false;
    }


    public RadioButtonGroup getGroup() {
        return group;
    }
}
