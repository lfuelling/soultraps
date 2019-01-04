package io.lerk.soultraps.components;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import io.lerk.soultraps.sys.Fonts;
import io.lerk.soultraps.sys.Handler;
import io.lerk.soultraps.sys.Soultraps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple Button that shows a black background when {@link Soultraps#DEBUG} is set.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Button extends Actor {

    /**
     * Default button height.
     */
    private static final int DEFAULT_HEIGHT = 32;

    /**
     * Default button width.
     */
    private static final int DEFAULT_WIDTH = 128;

    /**
     * Default font size.
     */
    private static final float DEFAULT_FONT_SIZE = 32f;

    /**
     * Default color.
     */
    private static final Color DEFAULT_COLOR = Color.BLACK;

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(Button.class);

    /**
     * Button text.
     */
    private final String text;

    /**
     * Click handler.
     */
    private Handler<Void> handler;

    /**
     * Constructor.
     */
    public Button() {
        this("Button");
    }

    /**
     * Constructor.
     *
     * @param text button text
     */
    public Button(String text) {
        this(text, DEFAULT_HEIGHT, DEFAULT_WIDTH, DEFAULT_FONT_SIZE);
    }

    /**
     * Constructor.
     *
     * @param text      button text
     * @param fontColor button text color
     */
    public Button(String text, Color fontColor) {
        this(text, DEFAULT_HEIGHT, DEFAULT_WIDTH, DEFAULT_FONT_SIZE, fontColor);
    }

    /**
     * Constructor.
     *
     * @param text     button text
     * @param height   button height
     * @param width    button height
     * @param fontSize font size
     */
    public Button(String text, int height, int width, float fontSize) {
        this(text, height, width, fontSize, DEFAULT_COLOR);
    }

    /**
     * Constructor.
     *
     * @param text      button text
     * @param height    button height
     * @param width     button width
     * @param fontSize  font size
     * @param fontColor button text color
     */
    public Button(String text, int height, int width, float fontSize, Color fontColor) {
        this.text = text;
        GreenfootImage buttonBg = new GreenfootImage(width, height);
        if (Soultraps.DEBUG) {
            buttonBg.setColor(Color.BLACK);
            buttonBg.fillRect(0, 0, buttonBg.getWidth(), buttonBg.getHeight());
        }
        buttonBg.setFont(Fonts.getFont(Fonts.Types.SKYRIM, fontSize));
        buttonBg.setColor(fontColor);
        buttonBg.drawString(text, 0, buttonBg.getHeight());
        setImage(buttonBg);
    }

    /**
     * Getter for button text.
     *
     * @return current button text
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for the button click handler.
     *
     * @param handler the handler.
     */
    public void setHandler(Handler<Void> handler) {
        this.handler = handler;
    }

    /**
     * Runs the click handler if necessary.
     */
    @Override
    public void act() {
        if (Greenfoot.mousePressed(this)) {
            if (this.handler != null) {
                this.handler.handle();
            } else {
                log.debug("No handler specified for button '" + text + "'");
            }
        }
    }

}
