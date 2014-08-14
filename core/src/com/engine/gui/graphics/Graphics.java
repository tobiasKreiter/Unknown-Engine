package com.engine.gui.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.engine.gui.component.texture.GuiTexture;

/**
 * Created by tobias on 28.06.2014.
 */
public class Graphics {
    /**
     * Contains the perfect frame rate.
     */
    private static final int PERFECT_FRAME_RATE = 60;
    /**
     * Contains the default transparency which should be 1f.
     */
    private static final float DEFAULT_TRANSPARENCY = 1f;
    /**
     * Contains the color with which the framebuffer will be cleared.
     */
    private static Color clearColor = Color.LIGHT_GRAY;
    /**
     * Contains the current transparency. Is a value between 0f and 1f.
     */
    private static float transparency = DEFAULT_TRANSPARENCY;
    /**
     * Contains the old transparency. Is set as trancparency when restoreTransparency is called.
     */
    private static float oldTransparency;
    /**
     * ShapeRenderer-Object to render geometric shapes.
     */
    private static ShapeRenderer renderer;
    /**
     * Is needed to render text.
     */
    private static SpriteBatch batch;
    /**
     * OrthographicsCamera-Object
     */
    private static OrthographicCamera cam;
    /**
     * Default color.
     */
    private static Color defaultColor = Color.BLACK;

    /**
     * Initializes the shaperenderer and the spritebatch.
     */
    public static void initGraphics() {
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.update();
        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(cam.combined);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, 1);
    }

    /**
     * Returns the correct delta. Now movement can be multiplied by delta.
     *
     * @return fixed delta
     */
    public static float getFixedDelta() {
        float delta = 1 + (1 - ((float) Gdx.graphics.getFramesPerSecond() / PERFECT_FRAME_RATE));
        delta = delta == 2 ? 1 : delta;
        return delta;
    }

    /**
     * Sets a new transparency. The old value is saved in 'oldTransparency'.
     *
     * @param trans new transparency
     */
    public static void setTransparency(float trans) {
        oldTransparency = transparency;
        transparency = trans;
        updateDefaultColor();
    }

    /**
     * Restores the old transparency.
     */
    public static void restoreTransparency() {
        transparency = oldTransparency;
        updateDefaultColor();
    }

    /**
     * Sets the default transparency.
     */
    public static void resetTransparency() {
        transparency = DEFAULT_TRANSPARENCY;
        updateDefaultColor();
    }

    /**
     * Returns the current transparency.
     *
     * @return current transparency
     */
    public static float getTransparency() {
        return transparency;
    }

    /**
     * Sets the default color.
     *
     * @param color new default color
     */
    public static void setDefaultColor(Color color) {
        defaultColor = color;
    }

    /**
     * Updates the transpanency of the default color.
     */
    private static void updateDefaultColor() {
        defaultColor.set(defaultColor.r, defaultColor.g, defaultColor.b, transparency);
    }

    public static void setClearColor(Color color) {
        clearColor = color;
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, 1);
    }

    /**
     * Resets the matrix and clears the screen.
     */
    public static void update() {
        cam.update();
        renderer.setProjectionMatrix(cam.combined);
        batch.setProjectionMatrix(cam.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Starts limiting the draw area.
     *
     * @param x      X-Coordinate
     * @param y      Y-Coordinate
     * @param width  Width
     * @param height Height
     */
    public static void limitDrawing(int x, int y, int width, int height) {
        Rectangle scissors = new Rectangle();
        Rectangle clipBounds = new Rectangle(x, y, width, height);
        ScissorStack.calculateScissors(cam, batch.getTransformMatrix(), clipBounds, scissors);
        ScissorStack.pushScissors(scissors);
    }

    /**
     * Ends limiting the draw area.
     */
    public static void limitEnd() {
        batch.flush();
        ScissorStack.popScissors();
    }

    /**
     * Draws a filled rectangle with specific position, size and the default color.
     *
     * @param x      X-Coordiante
     * @param y      Y-Coordinate
     * @param width  Width of rectangle
     * @param height Height of rectangle
     */
    public static void drawFilledRect(int x, int y, int width, int height) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(defaultColor);
        renderer.rect(x, y, width, height);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Draws a filled rectangle with specific position, size and color.
     *
     * @param x      X-Coordiante
     * @param y      Y-Coordinate
     * @param width  Width of rectangle
     * @param height Height of rectangle
     * @param color  Color of the rectangle
     */
    public static void drawFilledRect(int x, int y, int width, int height, Color color) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        color.set(color.r, color.g, color.b, transparency);
        renderer.setColor(color);
        renderer.rect(x, y, width, height);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Draws a filled rectangle with specific position, size, color and rotation.
     * !Untested!
     *
     * @param x        X-Coordiante
     * @param y        Y-Coordinate
     * @param width    Width of rectangle
     * @param height   Height of rectangle
     * @param rotation angle in degrees
     * @param color    Color of the rectangle
     */
    public static void drawFilledRect(int x, int y, int width, int height, float rotation, Color color) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        color.set(color.r, color.g, color.b, transparency);
        renderer.setColor(color);
        renderer.identity();
        renderer.translate(x + (width / 2), y + (height / 2), 0);
        renderer.rotate(0, 0, 1, rotation);
        renderer.rect(-width / 2, -height / 2, width, height);
        renderer.rotate(0, 0, 1, -rotation);
        renderer.translate(-(x + (width / 2)), -(y + (height / 2)), 0);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Draws a rectangle with specific position, size and the default color.
     *
     * @param x      X-Coordiante
     * @param y      Y-Coordinate
     * @param width  Width of rectangle
     * @param height Height of rectangle
     */
    public static void drawRect(int x, int y, int width, int height) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(defaultColor);
        renderer.rect(x, y, width, height);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Draws a rectangle with specific position, size and color.
     *
     * @param x      X-Coordiante
     * @param y      Y-Coordinate
     * @param width  Width of rectangle
     * @param height Height of rectangle
     * @param color  Color of the rectangle
     */
    public static void drawRect(int x, int y, int width, int height, Color color) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        color.set(color.r, color.g, color.b, transparency);
        renderer.setColor(color);
        renderer.rect(x, y, width, height);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Draws a rectangle with specific position, size and color.
     * !Untested!
     *
     * @param x        X-Coordiante
     * @param y        Y-Coordinate
     * @param width    Width of rectangle
     * @param height   Height of rectangle
     * @param rotation angle in degrees
     * @param color    Color of the rectangle
     */
    public static void drawRect(int x, int y, int width, int height, float rotation, Color color) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        color.set(color.r, color.g, color.b, transparency);
        renderer.setColor(color);
        renderer.translate(x + (width / 2), y + (height / 2), 0);
        renderer.rotate(0, 0, 1, rotation);
        renderer.rect(-width / 2, -height / 2, width, height);
        renderer.rotate(0, 0, 1, -rotation);
        renderer.translate(-(x + (width / 2)), -(y + (height / 2)), 0);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Draws a line with start- and endpoint and specific color.
     *
     * @param x     Start-X
     * @param y     Start-Y
     * @param x2    End-X
     * @param y2    End-Y
     * @param color Color of line
     */
    public static void drawLine(int x, int y, int x2, int y2, Color color) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        color.set(color.r, color.g, color.b, transparency);
        renderer.setColor(color);
        renderer.line(x, y, x2, y2);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Draws a line with start- and endpoint and the default color.
     *
     * @param x  Start-X
     * @param y  Start-Y
     * @param x2 End-X
     * @param y2 End-Y
     */
    public static void drawLine(int x, int y, int x2, int y2) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(defaultColor);
        renderer.line(x, y, x2, y2);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Draws a circle with specific position and radius.
     * !Untested!
     *
     * @param x      X-Coordiante
     * @param y      Y-Coordinate
     * @param radius Radius of circle
     */
    public static void drawCircle(int x, int y, int radius) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(defaultColor);
        renderer.circle(x, y, radius);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Draws a circle with specific position, radius and color.
     * !Untested!
     *
     * @param x      X-Coordiante
     * @param y      Y-Coordinate
     * @param radius Radius of circle
     */
    public static void drawCircle(int x, int y, int radius, Color color) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        color.set(color.r, color.g, color.b, transparency);
        renderer.setColor(color);
        renderer.circle(x, y, radius);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Draws a filled circle with specific position and radius.
     * !Untested!
     *
     * @param x      X-Coordiante
     * @param y      Y-Coordinate
     * @param radius Radius of circle
     */
    public static void drawFilledCircle(int x, int y, int radius) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(defaultColor);
        renderer.circle(x, y, radius);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Draws a filled circle with specific position, radius and color.
     * !Untested!
     *
     * @param x      X-Coordiante
     * @param y      Y-Coordinate
     * @param radius Radius of circle
     */
    public static void drawFilledCircle(int x, int y, int radius, Color color) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        color.set(color.r, color.g, color.b, transparency);
        renderer.setColor(color);
        renderer.circle(x, y, radius);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Draws a text at a specific position.
     *
     * @param text Text which will be displayed
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     * @param font Needed font
     */
    public static void drawText(String text, int x, int y, BitmapFont font) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.begin();
        font.setColor(defaultColor);
        font.draw(batch, text, x, y);
        batch.end();
    }

    /**
     * Draws a text at a specific position and color.
     *
     * @param text  Text which will be displayed
     * @param x     X-Coordinate
     * @param y     Y-Coordinate
     * @param color Color of text
     * @param font  Needed font
     */
    public static void drawText(String text, int x, int y, Color color, BitmapFont font) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.begin();
        color.set(color.r, color.g, color.b, transparency);
        font.setColor(color);
        font.draw(batch, text, x, y);
        batch.end();
        batch.flush();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Draws a text at a specific position and color.
     *
     * @param text  Text which will be displayed
     * @param x     X-Coordinate
     * @param y     Y-Coordinate
     * @param color Color of text
     * @param font  Needed font
     */
    public static void drawText(CharSequence text, int x, int y, Color color, BitmapFont font) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.begin();
        color.set(color.r, color.g, color.b, transparency);
        font.setColor(color);
        font.draw(batch, text, x, y);
        batch.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public static void drawWrappedText(CharSequence text, int x, int y, int wrapWidth, Color color, BitmapFont font, BitmapFont.HAlignment alignment) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.begin();
        color.set(color.r, color.g, color.b, transparency);
        font.setColor(color);
        font.drawWrapped(batch, text, x, y, wrapWidth, alignment);
        batch.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Draws an textureregion at a specific position and size.
     *
     * @param texture Texture to be drawn
     * @param x       X-Coordinate
     * @param y       Y-Coordinate
     */
    public static void drawTexture(GuiTexture texture, int x, int y) {
        drawTexture(texture, x, y, 0, 0);
    }

    /**
     * Draws an textureregion at a specific position and size.
     *
     * @param texture Texture to be drawn
     * @param x       X-Coordinate
     * @param y       Y-Coordinate
     * @param width   Width of texture
     * @param height  Height of texture
     */
    public static void drawTexture(GuiTexture texture, int x, int y, int width, int height) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.begin();
        batch.setColor(1f, 1f, 1f, transparency);
        if (width == 0 || height == 0) {
            batch.draw(texture, x, y);
        } else {
            batch.draw(texture, x, y, width, height);
        }
        batch.setColor(1f, 1f, 1f, 1f);
        batch.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }


    //TODO: Fix drawImage <--> drawTexture
    /**
     * Draws an texture at a specific position and size.
     *
     * @param image  Image to be drawn
     * @param x      X-Coordinate
     * @param y      Y-Coordinate
     * @param width  Width of image
     * @param height Height of image
     */
    public static void drawImage(Texture image, int x, int y, int width, int height) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.begin();
        batch.setColor(1f, 1f, 1f, transparency);
        batch.draw(image, x, y + height, width, -height); //flips image correctly
        batch.setColor(1f, 1f, 1f, 1f);
        batch.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Draws an textureregion at a specific position and size.
     *
     * @param image  Image to be drawn
     * @param x      X-Coordinate
     * @param y      Y-Coordinate
     * @param width  Width of image
     * @param height Height of image
     */
    public static void drawImage(TextureRegion image, int x, int y, int width, int height) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.begin();
        batch.setColor(1f, 1f, 1f, transparency);
        batch.draw(image, x, y + height, width, -height); //flips image correctly
        batch.setColor(1f, 1f, 1f, 1f);
        batch.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }


    /**
     * Set origin to x and y
     *
     * @param x X-Coordinate of new origin
     * @param y Y-Coordinate of new origin
     */
    public static void translate(float x, float y) {
        cam.translate(x, y);
        cam.update();
        renderer.setProjectionMatrix(cam.combined);
        batch.setProjectionMatrix(cam.combined);
    }

}
