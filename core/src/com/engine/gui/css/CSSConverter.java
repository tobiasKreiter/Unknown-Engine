package com.engine.gui.css;

import com.engine.gui.component.ComponentType;
import com.engine.gui.css.declarations.CSSDeclaration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Tobias on 23.08.2014.
 */
public class CSSConverter {

    private static final String TEXT_COLOR = "color";
    private static final String BACKGROUND_COLOR = "background";
    private static final String BORDER_COLOR = "border-color";
    private static final String BORDER_WIDTH = "border";

    private static final String FONT = "font";

    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";

    private static final String PADDING = "padding";
    private static final String PADDING_LEFT = "padding-left";
    private static final String PADDING_RIGHT = "padding-right";
    private static final String PADDING_TOP = "padding-top";
    private static final String PADDING_BOTTOM = "padding-bottom";

    private static final String BACKGROUND_IMAGE = "background-image";

    private static final String VERTICAL_ALIGNMENT = "vertical-alignment";

    private static final String HORIZONTAL_ALIGNMENT = "horizontal-alignment";

    private static final String[] ALL_DECLARATIONS = {
            TEXT_COLOR, BACKGROUND_COLOR, BORDER_COLOR, BORDER_WIDTH, FONT, WIDTH, HEIGHT, PADDING, PADDING_LEFT,
            PADDING_RIGHT, PADDING_TOP, PADDING_BOTTOM, BACKGROUND_IMAGE,
            VERTICAL_ALIGNMENT, HORIZONTAL_ALIGNMENT
    };

    private static final String[] VALID_LABEL = {
            TEXT_COLOR, FONT};

    private static final String[] VALID_BUTTON = {TEXT_COLOR, BACKGROUND_COLOR, BORDER_COLOR, FONT, BORDER_WIDTH, WIDTH, HEIGHT,
            PADDING, PADDING_LEFT, PADDING_RIGHT, PADDING_BOTTOM, PADDING_TOP,
            BACKGROUND_IMAGE, VERTICAL_ALIGNMENT, HORIZONTAL_ALIGNMENT};

    private static final String[] VALID_TEXTBOX = {TEXT_COLOR, BACKGROUND_COLOR, BORDER_COLOR, FONT, BORDER_WIDTH, WIDTH, HEIGHT, PADDING, PADDING_LEFT, PADDING_RIGHT, PADDING_BOTTOM, PADDING_TOP,
            BACKGROUND_IMAGE};

    private static final String[] VALID_TEXTFIELD = {TEXT_COLOR, BACKGROUND_COLOR, BORDER_COLOR, FONT, BORDER_WIDTH, WIDTH, HEIGHT, PADDING, PADDING_LEFT, PADDING_RIGHT, PADDING_BOTTOM, PADDING_TOP,
            BACKGROUND_IMAGE};

    private static final String[] VALID_RADIOBUTTON = {TEXT_COLOR, BORDER_COLOR, FONT, BORDER_WIDTH, WIDTH, HEIGHT, PADDING, PADDING_LEFT, PADDING_RIGHT, PADDING_BOTTOM, PADDING_TOP};

    private static final String[] VALID_CHECKBOX = {TEXT_COLOR, BORDER_COLOR, FONT, BORDER_WIDTH, WIDTH, HEIGHT, PADDING, PADDING_LEFT, PADDING_RIGHT, PADDING_BOTTOM, PADDING_TOP};

    private static final String[] VALID_DROPDOWN_BUTTON = {TEXT_COLOR, BACKGROUND_COLOR, BORDER_COLOR, FONT, BORDER_WIDTH, WIDTH, HEIGHT, PADDING, PADDING_LEFT, PADDING_RIGHT, PADDING_BOTTOM, PADDING_TOP,
            BACKGROUND_IMAGE};

    private static final String[] VALID_DROPDOWN_MENU = {BACKGROUND_COLOR, BORDER_COLOR, BORDER_WIDTH, WIDTH, HEIGHT, BACKGROUND_IMAGE};

    private static final String[] VALID_DROPDOWN_LABEL = {TEXT_COLOR, FONT, VERTICAL_ALIGNMENT, HORIZONTAL_ALIGNMENT};

    private static final char CLASSTRIGGER = '#';

    private static final char OWN_CLASSTRIGGER = '.';

    private static final char DECLARATION_START = '{';

    private static final char DECLARATION_END = '}';

    private static final char DECLARATION_BREAK = ';';

    private static final char VALUE_START = ':';

    private String filePath;

    private int readIndex = 0;

    private char[] cssCode;

    private ArrayList<CSSClass> cssClasses = new ArrayList<CSSClass>();

    public void readCss(String filePath) throws IOException {
        this.filePath = filePath;
        File cssFile = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(cssFile));
        String line;
        String cssString = "";
        while ((line = reader.readLine()) != null) {
            cssString += line;
        }
        cssString = cssString.replaceAll("\\s", "");
        cssCode = cssString.toCharArray();
        long start = System.currentTimeMillis();
        while (true) {
            if (isClassDefinition()) {
                readIndex++;
                String className = identifyClass();
                ComponentType componentType = ComponentType.getByString(className);

                if (componentType == ComponentType.UNDEFINIED_TYPE) {
                    printError();
                }

                String ownClassName = "";
                if (isOwnClass()) {
                    readIndex++;
                    ownClassName = identifyOwnClassName();
                } else {
                    readIndex++;
                }
                CSSClass cssClass = new CSSClass(componentType, ownClassName);
                String[][] declarations = getDeclarations();
                for (int j = 0; j < declarations[0].length; j++) {
                    CSSDeclaration cssDeclaration = findDeclaration(declarations[0][j], declarations[1][j], componentType);
                    if (cssDeclaration != null) {
                        cssClass.addCSSDeclaration(cssDeclaration);
                    }
                }
            } else {
                if (readIndex + 1 < cssCode.length) { //Syntax error
                    printError();
                }
                break; //End of file reached
            }
        }
        System.out.println("MilliSeconds: " + (System.currentTimeMillis() - start));
    }

    private boolean isClassDefinition() {
        if (readIndex + 1 >= cssCode.length) { //End of file reached.
            return false;
        }
        return CLASSTRIGGER == cssCode[readIndex];
    }

    private boolean isOwnClass() {
        return OWN_CLASSTRIGGER == cssCode[readIndex];
    }

    private String identifyClass() {
        String className = "";
        while (DECLARATION_START != cssCode[readIndex] && OWN_CLASSTRIGGER != cssCode[readIndex]) {
            className += cssCode[readIndex];
            readIndex++;
        }
        return className;
    }

    private String identifyOwnClassName() {
        String className = "";
        while (DECLARATION_START != cssCode[readIndex]) {
            className += cssCode[readIndex];
            readIndex++;
        }
        return className;
    }

    private String[][] getDeclarations() {
        ArrayList<String> preferences = new ArrayList<String>();
        ArrayList<String> values = new ArrayList<String>();
        String currentDeclaration = "";
        while (DECLARATION_END != cssCode[readIndex]) {
            if (DECLARATION_BREAK == cssCode[readIndex]) {
                values.add(currentDeclaration);
                currentDeclaration = "";
            } else if (VALUE_START == cssCode[readIndex]) {
                preferences.add(currentDeclaration);
                currentDeclaration = "";
            } else {
                currentDeclaration += cssCode[readIndex];
            }
            readIndex++;
        }
        readIndex++; //Skip declaration end
        String[] prefs = preferences.toArray(new String[preferences.size()]);
        String[] vals = values.toArray(new String[values.size()]);
        String[][] declarations = new String[2][vals.length];
        for (int i = 0; i < prefs.length; i++) {
            declarations[0][i] = prefs[i];
        }
        for (int j = 0; j < vals.length; j++) {
            declarations[1][j] = vals[j];
        }
        return declarations;
    }

    private String identifyPreferenceName() {
        String preferenceName = "";
        while (VALUE_START != cssCode[readIndex]) {
            preferenceName += cssCode[readIndex];
        }
        readIndex++; //Skip value start
        return preferenceName;
    }

    private void printError() {
        String error = "";
        for (int i = -10; i < 10; i++) {
            if (readIndex + 1 + i < cssCode.length) {
                if (readIndex+i > 0) {
                    error += cssCode[readIndex + i];
                }
            } else {
                break;
            }
        }
        throw new CssCorruptException(filePath, error);
    }

    private CSSDeclaration findDeclaration(String declaration, String value, ComponentType componentType) {
        CSSDeclarations cssDeclarations = CSSDeclarations.getDeclaration(declaration);
        if (cssDeclarations != CSSDeclarations.NOT_VALID) {
            if (cssDeclarations.isValidForComponent(componentType)) {
                CSSDeclaration cssDeclaration = CSSDeclarations.findCSSDeclaration(cssDeclarations);
                if (cssDeclaration != null) {
                    if (cssDeclaration.parseValue(value)) {
                        return cssDeclaration;
                    }
                }
            }
        }
        return null;
    }
}
