package io.lerk.soultraps.components;

import greenfoot.Color;

public class MovingHeading extends Button {
    private boolean up = true;
    private int count = 1;
    private int throttleCount = 0;

    private final int throttle = 30;
    private final int hoverAmount = 1;

    public MovingHeading(String text, int height, int width, float fontSize, Color textColor) {
        super(text, height, width, fontSize, textColor);
    }

    @Override
    public void act() {
        if (throttleCount >= throttle) {
            if (up) {
                setLocation(getX(), getY() - 1);
                if (count > hoverAmount) {
                    count = 0;
                    up = false;
                }
                count++;
            } else {
                setLocation(getX(), getY() + 1);
                if (count > hoverAmount) {
                    count = 0;
                    up = true;
                }
                count++;
            }
            throttleCount = 0;
        } else {
            throttleCount++;
        }
    }
}
