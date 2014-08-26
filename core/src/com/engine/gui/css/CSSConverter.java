package com.engine.gui.css;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Tobias on 23.08.2014.
 */
public class CSSConverter {

    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String BACKGROUND = "background";
    private static final String FONT_COLOR = "color";

    private static final char CLASSTRIGGER = '#';

    private static final char DECLARATION_START = '{';

    private static final char DECLARATION_END = '}';

    private static final char DECLARATION_BREAK = ';';

    private static final char VALUE_START = ':';

    private String filePath;

    private int readIndex = 0;

    private char[] cssCode;

    public void readCss(String filePath) throws IOException {
        this.filePath = filePath;
        File cssFile = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(cssFile));
        String line = null;
        String cssString = "";
        while ((line = reader.readLine()) != null) {
            cssString += line;
        }
        cssString = cssString.replaceAll("\\s", "");
        cssCode = cssString.toCharArray();
        long start = System.nanoTime();
        while (true) {
            if (isClassDefinition()) {
                readIndex++;
                String className = identifyClass();
                String[][] declarations = getDeclarations();
                System.out.println("ClassName: " + className);
                System.out.println("------------------------------");
                for (int j = 0; j < declarations[0].length; j++) {
                    System.out.println(j + ". " + "[" + declarations[0][j] + "] [" + declarations[1][j] + "]");
                }
                System.out.println("------------------------------");
            } else {
                if (readIndex + 1 < cssCode.length) { //Syntax error
                    printError();
                }
                break; //End of file reached
            }
        }
        System.out.println("NanoSeconds: " + (System.nanoTime() - start));
    }

    private boolean isClassDefinition() {
        if (readIndex + 1 >= cssCode.length) { //End of file reached.
            return false;
        }
        return CLASSTRIGGER == cssCode[readIndex];
    }

    private String identifyClass() {
        String className = "";
        while (DECLARATION_START != cssCode[readIndex]) {
            className += cssCode[readIndex];
            readIndex++;
        }
        readIndex++; //Skip declaration start
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
                if(i==-1) {
                    error+= "  -->  ";
                } else if(i==0) {
                    error+= "  <--  ";
                }
                error+=cssCode[readIndex + i];
            } else {
                break;
            }
        }
        throw new CssCorruptException(filePath, error);
    }

}
