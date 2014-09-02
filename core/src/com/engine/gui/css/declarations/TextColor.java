package com.engine.gui.css.declarations;

import com.engine.gui.component.Button;
import com.engine.gui.component.*;
import com.engine.gui.component.Label;
import com.engine.gui.component.TextField;
import com.engine.gui.component.container.Container;
import com.engine.gui.component.container.window.TitleBar;
import com.engine.gui.component.container.window.Window;

import java.awt.*;

/**
 * Created by Tobias on 26.08.2014.
 */
public class TextColor extends ColorDeclarations {

    @Override
    public void doAction(Label label) {
        label.setFontColor(color);
    }

    @Override
    public void doAction(Button button) {
        button.setFontColor(color);
    }

    @Override
    public void doAction(TextField textField) {
        textField.setFontColor(color);
    }

    @Override
    public void doAction(TextBox textBox) {
        textBox.setFontColor(color);
    }

    @Override
    public void doAction(RadioButton radioButton) {
        radioButton.setFontColor(color);
    }

    @Override
    public void doAction(CheckBox checkBox) {
        checkBox.setFontColor(color);
    }

    @Override
    public void doAction(DropdownMenu dropdownMenu) {
    }

    @Override
    public void doAction(ToggleButton toggleButton) {
        toggleButton.setFontColor(color);
    }

    @Override
    public void doAction(Container container) {
    }

    @Override
    public void doAction(Window window) {
    }

    @Override
    public void doAction(TitleBar titleBar) {
        titleBar.setFontColor(color);
    }

    @Override
    public void doAction(Scrollbar scrollbar) {
    }
}
