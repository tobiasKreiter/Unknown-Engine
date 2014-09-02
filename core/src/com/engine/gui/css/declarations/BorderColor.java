package com.engine.gui.css.declarations;

import com.engine.gui.component.*;
import com.engine.gui.component.Button;
import com.engine.gui.component.Label;
import com.engine.gui.component.TextField;
import com.engine.gui.component.container.Container;
import com.engine.gui.component.container.window.TitleBar;
import com.engine.gui.component.container.window.Window;

import java.awt.*;

/**
 * Created by Tobias on 28.08.2014.
 */
public class BorderColor extends ColorDeclarations {

    @Override
    public void doAction(Label label) {
    }

    @Override
    public void doAction(Button button) {
        button.setBorderColor(color);
    }

    @Override
    public void doAction(TextField textField) {
        //TODO textField.setBorderColor(color);
    }

    @Override
    public void doAction(TextBox textBox) {
        textBox.setBorderColor(color);
    }

    @Override
    public void doAction(RadioButton radioButton) {

    }

    @Override
    public void doAction(CheckBox checkBox) {

    }

    @Override
    public void doAction(DropdownMenu dropdownMenu) {
        dropdownMenu.setBorderColor(color);
    }


    @Override
    public void doAction(ToggleButton toggleButton) {
        toggleButton.setBorderColor(color);
    }

    @Override
    public void doAction(Container container) {

    }

    @Override
    public void doAction(Window window) {
        //TODO window.setBorderColor(color);
    }

    @Override
    public void doAction(TitleBar titleBar) {
        //TODO titleBar.setBorderColor(color);
    }

    @Override
    public void doAction(Scrollbar scrollbar) {

    }
}
