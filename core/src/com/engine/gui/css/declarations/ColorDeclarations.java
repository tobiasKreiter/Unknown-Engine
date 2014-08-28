package com.engine.gui.css.declarations;


import com.badlogic.gdx.graphics.Color;

/**
 * Created by Tobias on 28.08.2014.
 */
public abstract class ColorDeclarations extends CSSDeclaration {

    protected Color color;

    @Override
    public boolean parseValue(String value) {
        try {
            value = value.replace("#", "");
            color = Color.valueOf(value);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
