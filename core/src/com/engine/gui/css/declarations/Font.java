package com.engine.gui.css.declarations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
public class Font extends CSSDeclaration {

    private BitmapFont font;

    @Override
    public boolean parseValue(String value) {
        try {
            String[] splitted = value.split(",");
            String fontPath = splitted[0];
            String fontSize = splitted[1];
            fontSize.replace("px", "");
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
            FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
            param.flip = true;
            param.size = Integer.parseInt(fontSize);
            font = generator.generateFont(param);
            generator.dispose();
            return true;
        } catch(Exception ex) {
        }
        return false;
    }

    @Override
    public void doAction(Label label) {
        label.setFont(font);
    }

    @Override
    public void doAction(Button button) {
        button.setFont(font);
    }

    @Override
    public void doAction(TextField textField) {
        //TODO
    }

    @Override
    public void doAction(TextBox textBox) {
        textBox.setFont(font);
    }

    @Override
    public void doAction(RadioButton radioButton) {
        radioButton.setFont(font);
    }

    @Override
    public void doAction(CheckBox checkBox) {
        checkBox.setFont(font);
    }

    @Override
    public void doAction(DropdownButton dropdownButton) {
        dropdownButton.setFont(font);
    }

    @Override
    public void doAction(DropdownMenu dropdownMenu) {
    }

    @Override
    public void doAction(DropdownLabel dropdownLabel) {
        dropdownLabel.setFont(font);
    }

    @Override
    public void doAction(ToggleButton toggleButton) {
        toggleButton.setFont(font);
    }

    @Override
    public void doAction(Container container) {

    }

    @Override
    public void doAction(Window window) {

    }

    @Override
    public void doAction(TitleBar titleBar) {
        //TODO
    }

    @Override
    public void doAction(Scrollbar scrollbar) {

    }
}
