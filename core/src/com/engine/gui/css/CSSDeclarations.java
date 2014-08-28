package com.engine.gui.css;

import com.engine.gui.component.ComponentType;
import com.engine.gui.css.declarations.*;

import static com.engine.gui.component.ComponentType.*;

/**
 * Created by Tobias on 28.08.2014.
 */
public enum CSSDeclarations {

    NOT_VALID("error", new ComponentType[]{}),
    TEXT_COLOR("color", new ComponentType[]{LABEL, BUTTON, TEXTBOX, TEXTFIELD, RADIOBUTTON, CHECKBOX, DROPDOWN_BUTTON,
            DROPDOWN_LABEL, TOGGLEBUTTON, TITLEBAR}),
    BACKGROUND_COLOR("background-color", new ComponentType[]{BUTTON, TEXTBOX, TEXTFIELD, DROPDOWN_BUTTON, DROPDOWN_MENU,
            CONTAINER, WINDOW, TITLEBAR, SCROLLBAR}),
    BORDER_COLOR("border", new ComponentType[]{BUTTON, TEXTBOX, TEXTFIELD, DROPDOWN_BUTTON, DROPDOWN_MENU,
            CONTAINER, WINDOW, TITLEBAR, SCROLLBAR}),
    BORDER_WIDTH("border-width", new ComponentType[]{BUTTON, TEXTBOX, TEXTFIELD, DROPDOWN_BUTTON, DROPDOWN_MENU,
            CONTAINER, WINDOW, TITLEBAR, SCROLLBAR}),
    FONT("font", new ComponentType[]{LABEL, BUTTON, TEXTBOX, TEXTFIELD, RADIOBUTTON, CHECKBOX, DROPDOWN_BUTTON,
            DROPDOWN_LABEL, TOGGLEBUTTON, WINDOW, TITLEBAR}),
    WIDTH("width", new ComponentType[]{BUTTON, TEXTBOX, TEXTFIELD, RADIOBUTTON, CHECKBOX, DROPDOWN_BUTTON, DROPDOWN_MENU,
            TOGGLEBUTTON, CONTAINER, WINDOW, TITLEBAR, SCROLLBAR}),
    HEIGHT("height", new ComponentType[]{BUTTON, TEXTBOX, TEXTFIELD, RADIOBUTTON, CHECKBOX, DROPDOWN_BUTTON, DROPDOWN_MENU,
            TOGGLEBUTTON, CONTAINER, WINDOW, TITLEBAR, SCROLLBAR}),
    PADDING("padding", new ComponentType[]{BUTTON, TEXTBOX, TEXTFIELD, RADIOBUTTON, CHECKBOX, DROPDOWN_BUTTON, DROPDOWN_MENU,
            DROPDOWN_LABEL, TOGGLEBUTTON, CONTAINER, WINDOW, TITLEBAR}),
    PADDING_LEFT("padding-left", new ComponentType[]{BUTTON, TEXTBOX, TEXTFIELD, RADIOBUTTON, CHECKBOX, DROPDOWN_BUTTON, DROPDOWN_MENU,
            DROPDOWN_LABEL, TOGGLEBUTTON, CONTAINER, WINDOW, TITLEBAR}),
    PADDING_RIGHT("padding-right", new ComponentType[]{BUTTON, TEXTBOX, TEXTFIELD, RADIOBUTTON, CHECKBOX, DROPDOWN_BUTTON, DROPDOWN_MENU,
            DROPDOWN_LABEL, TOGGLEBUTTON, CONTAINER, WINDOW, TITLEBAR}),
    PADDING_TOP("padding-top", new ComponentType[]{BUTTON, TEXTBOX, TEXTFIELD, RADIOBUTTON, CHECKBOX, DROPDOWN_BUTTON, DROPDOWN_MENU,
            DROPDOWN_LABEL, TOGGLEBUTTON, CONTAINER, WINDOW, TITLEBAR}),
    PADDING_BOTTOM("padding-bottom", new ComponentType[]{BUTTON, TEXTBOX, TEXTFIELD, RADIOBUTTON, CHECKBOX, DROPDOWN_BUTTON, DROPDOWN_MENU,
            DROPDOWN_LABEL, TOGGLEBUTTON, CONTAINER, WINDOW, TITLEBAR}),
    BACKGROUND_IMAGE("background-image", new ComponentType[]{BUTTON, TEXTBOX, TEXTFIELD, DROPDOWN_BUTTON, DROPDOWN_MENU,
            CONTAINER, WINDOW, TITLEBAR}),
    VERTICAL_ALIGNMENT("vertical-alignment", new ComponentType[]{BUTTON, DROPDOWN_BUTTON, DROPDOWN_MENU, DROPDOWN_LABEL,
            TITLEBAR}),
    HORIZONTAL_ALIGNMENT("horizontal-alignment", new ComponentType[]{BUTTON, DROPDOWN_BUTTON, DROPDOWN_MENU, DROPDOWN_LABEL,
            TITLEBAR});

    private String declarationAsString;

    private ComponentType[] validComponentTypes;

    private CSSDeclarations(String declarationAsString, ComponentType[] validComponentTypes) {
        this.declarationAsString = declarationAsString;
        this.validComponentTypes = validComponentTypes;
    }

    public boolean equals(String declarationAsString) {
        if (this.declarationAsString.equals(declarationAsString)) {
            return true;
        }
        return false;
    }

    public boolean isValidForComponent(ComponentType componentType) {
        for (int i = 0; i < validComponentTypes.length; i++) {
            if (validComponentTypes[i] == componentType) {
                return true;
            }
        }
        return false;
    }

    public static CSSDeclarations getDeclaration(String declarationAsString) {
        for (int i = 0; i < CSSDeclarations.values().length; i++) {
            CSSDeclarations cssDeclaration = CSSDeclarations.values()[i];
            if (cssDeclaration.equals(declarationAsString)) {
                return cssDeclaration;
            }
        }
        return NOT_VALID;
    }

    public static CSSDeclaration findCSSDeclaration(CSSDeclarations cssDeclarations) {
        switch (cssDeclarations) {
            case TEXT_COLOR:
                return new TextColor();
            case BACKGROUND_COLOR:
                return new BackgroundColor();
            case BORDER_COLOR:
                return new BorderColor();
            case BORDER_WIDTH:
                return new BorderWidth();
            case FONT:
                return new Font();
            case WIDTH:
                return new Width();
            case HEIGHT:

            default:
                return null;
        }
    }

}
