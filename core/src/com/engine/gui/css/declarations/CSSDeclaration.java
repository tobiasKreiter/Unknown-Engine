package com.engine.gui.css.declarations;

import com.engine.gui.component.*;
import com.engine.gui.component.Button;
import com.engine.gui.component.Component;
import com.engine.gui.component.Label;
import com.engine.gui.component.TextField;
import com.engine.gui.component.container.Container;
import com.engine.gui.component.container.window.TitleBar;
import com.engine.gui.component.container.window.Window;

import java.awt.*;

import static com.engine.gui.component.Component.*;

/**
 * Created by Tobias on 26.08.2014.
 */
public abstract class CSSDeclaration {

    public abstract boolean parseValue(String value);

    public abstract void doAction(Label label);

    public abstract void doAction(Button button);

    public abstract void doAction(TextField textField);

    public abstract void doAction(TextBox textBox);

    public abstract void doAction(RadioButton radioButton);

    public abstract void doAction(CheckBox checkBox);

    public abstract void doAction(DropdownButton dropdownButton);

    public abstract void doAction(DropdownMenu dropdownMenu);

    public abstract void doAction(DropdownLabel dropdownLabel);

    public abstract void doAction(ToggleButton toggleButton);

    public abstract void doAction(Container container);

    public abstract void doAction(Window window);

    public abstract void doAction(TitleBar titleBar);

    public abstract void doAction(Scrollbar scrollbar);

}
