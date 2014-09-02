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
public class Height extends NumberDeclaration {
    @Override
    public void doAction(Label label) {

    }

    @Override
    public void doAction(Button button) {
        button.setHeight(number);
    }

    @Override
    public void doAction(TextField textField) {
        textField.setHeight(number);
    }

    @Override
    public void doAction(TextBox textBox) {
        textBox.setHeight(number);
    }

    @Override
    public void doAction(RadioButton radioButton) {
        radioButton.setHeight(number);
    }

    @Override
    public void doAction(CheckBox checkBox) {
        checkBox.setHeight(number);
    }

    @Override
    public void doAction(DropdownMenu dropdownMenu) {
        dropdownMenu.setHeight(number);
    }

    @Override
    public void doAction(ToggleButton toggleButton) {
        toggleButton.setHeight(number);
    }

    @Override
    public void doAction(Container container) {
        container.setHeight(number);
    }

    @Override
    public void doAction(Window window) {
        window.setHeight(number);
    }

    @Override
    public void doAction(TitleBar titleBar) {
        titleBar.setHeight(number);
    }

    @Override
    public void doAction(Scrollbar scrollbar) {
        //TODO scrollbar.setHeight(number);
    }
}
