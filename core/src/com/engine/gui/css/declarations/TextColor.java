package com.engine.gui.css.declarations;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.engine.gui.component.*;
import com.engine.gui.component.Button;
import com.engine.gui.component.Component;
import com.engine.gui.component.Label;
import com.engine.gui.component.TextField;
import com.engine.gui.component.container.Container;
import com.engine.gui.component.container.window.TitleBar;
import com.engine.gui.component.container.window.Window;

import java.awt.*;

/**
 * Created by Tobias on 26.08.2014.
 */
public class TextColor extends CSSDeclaration{

    private Color textColor;

    @Override
    public boolean parseValue(String value) {
        try {
            value = value.replace("#", "");
            textColor = Color.valueOf(value);
            return true;
        } catch (Exception ex) {
        }
        return false;
    }

    @Override
    public void doAction(Label label) {
       //TODO wait until method is implemented
    }

    @Override
    public void doAction(Button button) {

    }

    @Override
    public void doAction(TextField textField) {

    }

    @Override
    public void doAction(TextBox textBox) {

    }

    @Override
    public void doAction(RadioButton radioButton) {

    }

    @Override
    public void doAction(CheckBox checkBox) {

    }

    @Override
    public void doAction(DropdownButton dropdownButton) {

    }

    @Override
    public void doAction(DropdownMenu dropdownMenu) {

    }

    @Override
    public void doAction(DropdownLabel dropdownLabel) {

    }

    @Override
    public void doAction(ToggleButton toggleButton) {

    }

    @Override
    public void doAction(Container container) {

    }

    @Override
    public void doAction(Window window) {

    }

    @Override
    public void doAction(TitleBar titleBar) {

    }

    @Override
    public void doAction(Scrollbar scrollbar) {

    }
}
