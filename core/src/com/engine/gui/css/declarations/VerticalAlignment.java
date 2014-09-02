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
 * Created by Tobias on 30.08.2014.
 */
public class VerticalAlignment extends CSSDeclaration {

    private int verticalAlignment;

    private static final String LEFT = "left";
    private static final String CENTER = "center";
    private static final String RIGHT = "right";

    @Override
    public boolean parseValue(String value) {
        if(value.equals(LEFT)) {
            verticalAlignment = Button.LEFT;
            return true;
        } else if(value.equals(CENTER)) {
            verticalAlignment = Button.CENTER;
            return true;
        } else if(value.equals(RIGHT)) {
            verticalAlignment = Button.RIGHT;
            return true;
        }
        return false;
    }

    @Override
    public void doAction(Label label) {

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
    public void doAction(DropdownMenu dropdownMenu) {

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
