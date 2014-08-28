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
public class Width extends NumberDeclaration {

    @Override
    public void doAction(Label label) {

    }

    @Override
    public void doAction(Button button) {
        button.setWidth(number);
    }

    @Override
    public void doAction(TextField textField) {
        textField.setWidth(number);
    }

    @Override
    public void doAction(TextBox textBox) {
        textBox.setWidth(number);
    }

    @Override
    public void doAction(RadioButton radioButton) {
        radioButton.setWidth(number);
    }

    @Override
    public void doAction(CheckBox checkBox) {
        checkBox.setWidth(number);
    }

    @Override
    public void doAction(DropdownButton dropdownButton) {
        dropdownButton.setWidth(number);
    }

    @Override
    public void doAction(DropdownMenu dropdownMenu) {
        dropdownMenu.setWidth(number);
    }

    @Override
    public void doAction(DropdownLabel dropdownLabel) {

    }

    @Override
    public void doAction(ToggleButton toggleButton) {
        toggleButton.setWidth(number);
    }

    @Override
    public void doAction(Container container) {
        container.setWidth(number);
    }

    @Override
    public void doAction(Window window) {
        window.setWidth(number);
    }

    @Override
    public void doAction(TitleBar titleBar) {
        titleBar.setWidth(number);
    }

    @Override
    public void doAction(Scrollbar scrollbar) {
        //TODO
    }
}
