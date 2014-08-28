package com.engine.gui.component;

/**
 * Created by Tobias on 28.08.2014.
 */
public enum ComponentType {

    LABEL(1, "label"),
    BUTTON(2, "button"),
    TEXTFIELD(3, "textfield"),
    TEXTBOX(4, "textbox"),
    RADIOBUTTON(5, "radiobutton"),
    CHECKBOX(6, "checkbox"),
    DROPDOWN_BUTTON(7, "dropdown-button"),
    DROPDOWN_MENU(8, "dropdown-menu"),
    DROPDOWN_LABEL(9, "dropdown-label"),
    TOGGLEBUTTON(10, "togglebutton");

    private int typeValue;
    private String typeAsString;

    private ComponentType(int typeValue, String typeAsString) {
        this.typeValue = typeValue;
        this.typeAsString = typeAsString;
    }

    public int getTypeValue() {
        return typeValue;
    }

    public String getTypeAsString() {
        return typeAsString;
    }

    public boolean equals(String name) {
        return typeAsString.equals(name);
    }

}
