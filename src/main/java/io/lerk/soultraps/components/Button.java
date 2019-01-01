package io.lerk.soultraps.components;

import greenfoot.*;
import greenfoot.core.WorldHandler;
import io.lerk.soultraps.sys.Fonts;
import io.lerk.soultraps.sys.Handler;
import io.lerk.soultraps.sys.SoultrapsApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Button extends Actor {

    private static final int DEFAULT_HEIGHT = 32;
    private static final int DEFAULT_WIDTH = 128;
    private static final float DEFAULT_FONT_SIZE = 32f;
    private static final Color DEFAULT_COLOR = Color.BLACK;
    private static final Logger log = LoggerFactory.getLogger(Button.class);

    private final String text;
    private Handler<Void> handler;

    public Button() {
        this("Button");
    }

    public Button(String text) {
        this(text, DEFAULT_HEIGHT, DEFAULT_WIDTH, DEFAULT_FONT_SIZE);
    }

    public Button(String text, Color fontColor) {
        this(text, DEFAULT_HEIGHT, DEFAULT_WIDTH, DEFAULT_FONT_SIZE, fontColor);
    }

    public Button(String text, int height, int width, float fontSize) {
        this(text, height, width, fontSize, DEFAULT_COLOR);
    }

    public Button(String text, int height, int width, float fontSize, Color fontColor) {
        this.text = text;
        GreenfootImage buttonBg = new GreenfootImage(width, height);
        if(SoultrapsApp.DEBUG) {
            buttonBg.setColor(Color.BLACK);
            buttonBg.fillRect(0,0, buttonBg.getWidth(), buttonBg.getHeight());
        }
        buttonBg.setFont(Fonts.getFont(Fonts.Types.SKYRIM, fontSize));
        buttonBg.setColor(fontColor);
        buttonBg.drawString(text, 0, buttonBg.getHeight());
        setImage(buttonBg);
    }

    public String getText() {
        return text;
    }

    public void setHandler(Handler<Void> handler) {
        this.handler = handler;
    }

    @Override
    public void act() {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if(mouseInfo != null) {
            Actor actor = mouseInfo.getActor();
            if(actor instanceof Button) {
                boolean equals = ((Button) actor).getText().equals(this.getText());
            }
        }
        if (Greenfoot.mousePressed(this)) {
            if (this.handler != null) {
                this.handler.handle();
            } else {
                log.debug("No handler specified for button '" + text + "'");
            }
        }
    }

}
