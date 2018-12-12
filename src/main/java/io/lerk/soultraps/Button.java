package io.lerk.soultraps;

import greenfoot.*;
import greenfoot.Color;

public class Button extends Actor {

    private Handler<Void> handler;

    public Button() {
        this("Button");
    }

    public Button(String text) {
        this(text, 64, 128, 32f);
    }

    public Button(String text, int height, int width, float fontSize) {
        GreenfootImage buttonBg = new GreenfootImage(width, height);
        buttonBg.setFont(Fonts.getFont(Fonts.Types.SKYRIM, fontSize));
        buttonBg.drawString(text, 0, buttonBg.getHeight());
        setImage(buttonBg);
    }

    public void setHandler(Handler<Void> handler) {
        this.handler = handler;
    }

    @Override
    public void act() {
        if(Greenfoot.mouseClicked(this)){
            handler.handle();
        }
    }

}
