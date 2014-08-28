package com.engine.gui.css.declarations;

/**
 * Created by Tobias on 28.08.2014.
 */
public abstract class NumberDeclaration extends CSSDeclaration {

    protected int number;

    @Override
    public boolean parseValue(String value) {
        try {
            value = value.replace("px", "");
            number = Integer.parseInt(value);
            return true;
        } catch (Exception ex) {}
        return false;
    }
}
