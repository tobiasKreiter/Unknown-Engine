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
public class BorderWidth extends NumberDeclaration {

    @Override
    public void doAction(Label label) {

    }

    @Override
    public void doAction(Button button) {
        button.setBorderWidth(number);
    }

    @Override
    public void doAction(TextField textField) {
        //TODO textField.setBorderWidth(number);
    }

    @Override
    public void doAction(TextBox textBox) {
        textBox.setBorderWidth(number);
    }

    @Override
    public void doAction(RadioButton radioButton) {

    }

    @Override
    public void doAction(CheckBox checkBox) {

    }

    @Override
    public void doAction(DropdownMenu dropdownMenu) {
        dropdownMenu.setBorderWidth(number);
    }

    @Override
    public void doAction(ToggleButton toggleButton) {

    }

    @Override
    public void doAction(Container container) {

    }

    @Override
    public void doAction(Window window) {
        //TODO window.setBorderWidth(number);
    }

    @Override
    public void doAction(TitleBar titleBar) {
        //TODO titleBar.setBorderWidth(number);
    }

    @Override
    public void doAction(Scrollbar scrollbar) {
        //TODO scrollbar.setBorderWidth(number);
    }
}
