package com.engine.gui.css;

import com.engine.gui.component.*;
import com.engine.gui.component.Button;
import com.engine.gui.component.Label;
import com.engine.gui.component.TextField;
import com.engine.gui.component.container.Container;
import com.engine.gui.component.container.window.TitleBar;
import com.engine.gui.component.container.window.Window;
import com.engine.gui.component.container.scroll.ScrollBar;
import com.engine.gui.css.declarations.CSSDeclaration;

import java.util.ArrayList;

/**
 * Created by Tobias on 27.08.2014.
 */
public class CSSClass {

    private ComponentType componentType;

    private String className;

    private ArrayList<CSSDeclaration> cssDeclarations = new ArrayList<CSSDeclaration>();

    public CSSClass(ComponentType componentType, String className) {
        this.componentType = componentType;
        this.className = className;
    }

    public boolean isOwnClass() {
        return className != "";
    }

    public String getClassName() {
        return className;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void addCSSDeclaration(CSSDeclaration cssDeclaration) {
        cssDeclarations.add(cssDeclaration);
    }

    public void removeCSSDeclaration(CSSDeclaration cssDeclaration) {
        cssDeclarations.remove(cssDeclaration);
    }

    public int numberOfDeclarations() {
        return cssDeclarations.size();
    }

    public void applyStyle(Component component) {
        if(isOwnClass()) {
            if(!className.equals(component.getClassName())) {
                return;
            }
        }
        for (int i = 0; i < cssDeclarations.size(); i++) {
            cssDeclarations.get(i).doAction(component);
        }
    }
}
