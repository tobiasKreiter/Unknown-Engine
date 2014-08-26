package com.engine.gui.css;

/**
 * Created by Tobias on 24.08.2014.
 */
public class CssCorruptException extends IllegalStateException {

    public CssCorruptException(String filePath, String cssError) {
        super("Corrupt css file [" + filePath + "]: " + cssError);
    }

}
