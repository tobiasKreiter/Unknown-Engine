package com.engine.gui.component;

/**
 * Created by Tobias on 28.08.2014.
 */
public enum ComponentType {

    UNDEFINIED_TYPE(-1, "error"),
    LABEL(1, "label"),
    BUTTON(2, "button"),
    TEXTFIELD(3, "textfield"),
    TEXTBOX(4, "textbox"),
    RADIOBUTTON(5, "radiobutton"),
    CHECKBOX(6, "checkbox"),
    DROPDOWN_MENU(7, "dropdown-menu"),
    TOGGLEBUTTON(8, "togglebutton"),
    CONTAINER(9, "container"),
    WINDOW(10, "window"),
    TITLEBAR(11, "titlebar"),
    SCROLLBAR(12, "scrollbar");

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

    public static ComponentType getByString(String name) {
        for(int i = 0;i<ComponentType.values().length;i++) {
            ComponentType type = ComponentType.values()[i];
            if(type.equals(name)) {
                return type;
            }
        }
        return UNDEFINIED_TYPE;
    }

}
