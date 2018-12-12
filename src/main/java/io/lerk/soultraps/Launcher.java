package io.lerk.soultraps;

import greenfoot.*;
import io.lerk.soultraps.Button;

public class Launcher extends Level {

    public static final int BASE_WIDTH = 1000;
    public static final int BASE_HEIGHT = 750;

    public Launcher() {
        //super(1000, 750, 1);
        super(() -> 0);
        prepare();
    }

    private void prepare() {
        Button title = getTitle();
        addObject(title, getWidth() / 2, 50);
    }

    private Button getTitle() {
        return new Button("Soultraps", 256, 320, 72f) {
            private boolean up = true;
            private int count = 0;
            private int throttleCount = 0;

            private final int throttle = 3;
            private final int hoverAmount = 18;

            @Override
            public void act() {
                if (throttleCount >= throttle) {
                    if (up) {
                        setLocation(getX(), getY() + 1);
                        if (count >= hoverAmount) {
                            count = 0;
                            up = false;
                        } else {
                            count++;
                        }
                    } else {
                        setLocation(getX(), getY() - 1);
                        if (count >= hoverAmount) {
                            count = 0;
                            up = true;
                        } else {
                            count++;
                        }
                    }
                    throttleCount = 0;
                } else {
                    throttleCount++;
                }
            }
        };
    }

}
