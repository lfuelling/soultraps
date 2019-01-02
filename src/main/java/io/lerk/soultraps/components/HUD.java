package io.lerk.soultraps.components;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import io.lerk.soultraps.mobs.Player;

public class HUD extends Actor {
    private final Player player;
    private long lastUpdateMillis;

    public HUD(Player player) {
        this.player = player;
    }

    @Override
    public void act() {
        if (System.currentTimeMillis() - 100 >= lastUpdateMillis) {
            updateImage();
            lastUpdateMillis = System.currentTimeMillis();
        }
    }

    private void updateImage() {
        //FIXME implement
        setImage(new GreenfootImage(256, 64));
    }
}
