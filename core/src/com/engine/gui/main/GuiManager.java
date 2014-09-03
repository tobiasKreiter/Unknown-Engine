package com.engine.gui.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.engine.gui.component.Background;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.screen.Frame;
import com.engine.gui.test.TestScreen;
import com.engine.gui.transition.Transition;
import javafx.stage.Screen;

/**
 * Created by tobias on 22.07.2014.
 */
public class GuiManager extends Game {

    /**
     * Constants for generated fonts. Height depends on screen height.
     */
    private static int HEADLINE_HEIGHT;
    private static int NORMAL_HEIGHT;
    private static int MINI_HEIGHT;

    public static BitmapFont normalFont;
    public static BitmapFont headlineFont;
    public static BitmapFont miniFont;

    public static final Color DEFAULT_FONT_COLOR = Color.BLUE;

    public static String clipboard = null;
    /**
     * Recommend
     */
    @Override
    public void create() {
        Graphics.initGraphics();
        Graphics.setClearColor(Color.valueOf("29313B"));
        initBitmapFonts();
        Frame test = new TestScreen();
        setScreen(test);
    }

    /**
     * Generates the normal, mini and headline font.
     */
    public void initBitmapFonts() {
        HEADLINE_HEIGHT = toY(0.1f);
        NORMAL_HEIGHT = toY(0.04f);
        MINI_HEIGHT = toY(0.02f);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/calibri.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.flip = true;
        param.size = NORMAL_HEIGHT;
        normalFont = generator.generateFont(param);
        param.size = HEADLINE_HEIGHT;
        headlineFont = generator.generateFont(param);
        param.size = MINI_HEIGHT;
        miniFont = generator.generateFont(param);
        generator.dispose();
    }


    /**
     * Creates a BitmapFont with a specific size. HEAVY OPERATION!!
     *
     * @param heightInPercent height of font in percent
     * @return desired Font
     */
    public static BitmapFont generateFont(float heightInPercent) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.flip = true;
        param.size = toY(heightInPercent);
        BitmapFont font = generator.generateFont(param);
        generator.dispose();
        return font;
    }

    /**
     * Creates a BitmapFont with a specific size and specific font file. HEAVY OPERATION!!
     *
     * @param heightInPercent height of font in percent
     * @param font            font file
     * @return desired Font
     */
    public static BitmapFont generateFont(float heightInPercent, FileHandle font) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(font);
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.flip = true;
        param.size = toY(heightInPercent);
        BitmapFont gFont = generator.generateFont(param);
        generator.dispose();
        return gFont;
    }

    /**
     * Changes the current screen.
     *
     * @param transition transition which animates the change
     */
    public void changeScreen(Transition transition) {
        transition.setGuiManager(this);
        setScreen(transition);
    }

    /**
     * Returns the generated normal font.
     *
     * @return normal font
     */
    public static BitmapFont getNormalFont() {
        return normalFont;
    }

    /**
     * Returns the generated mini font.
     *
     * @return mini font
     */
    public static BitmapFont getMiniFont() {
        return miniFont;
    }

    /**
     * Returns the generated headline font
     *
     * @return headline font
     */
    public static BitmapFont getHeadlineFont() {
        return headlineFont;
    }

    /**
     * Converts a percent value into a x-coordinate. Very important for components and layouts.
     *
     * @param percent how many percent of the total screen width
     * @return relative x-position
     */
    public static int toX(float percent) {
        return (int) (Gdx.graphics.getWidth() * percent);
    }

    /**
     * Converts a percent value into a y-coordinate.Very important for components and layouts.
     *
     * @param percent how many percent of the total screen height
     * @return relative y-position
     */
    public static int toY(float percent) {
        return (int) (Gdx.graphics.getHeight() * percent);
    }

    //69/255f,90/255f,100/255f,1f
    public static Background getDEFAULT_BACKGROUND(){
        return new Background();
    }
}
