package com.engine.gui.component;

import java.util.ArrayList;

/**
 * Created by Felix on 23.07.2014.
 */
public class RadioButtonGroup {

    private ArrayList<RadioButton> buttons = new ArrayList<RadioButton>();

    public void add(RadioButton button) {
        this.buttons.add(button);
    }

    public boolean remove(RadioButton button) {
        return buttons.remove(button);
    }

    /**
     * if button is clicked all other buttons in group will be set state 'unselected'
     */
    public void checked(RadioButton button) {
        for (int i = 0; i < buttons.size(); i++) {
            if (!(buttons.get(i) == button)) {
                buttons.get(i).setChecked(false);
            }
        }
    }

}
