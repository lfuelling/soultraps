package io.lerk.soultraps.levels;

import greenfoot.Color;
import greenfoot.Greenfoot;
import io.lerk.soultraps.components.Button;

public class Launcher extends Level {

    public static final int BASE_WIDTH = 64;
    public static final int BASE_HEIGHT = 32;

    public Launcher() {
        super();
        Button title = getTitle();
        addObject(title, getWidth() / 2, 0);

        Button startButton = new Button("Start Game", 100, 200, 32, Color.WHITE);
        Button optionsButton = new Button("Options", 100, 200, 32, Color.WHITE);

        startButton.setHandler(() -> {
            Greenfoot.setWorld(new IntroLevel());
            return null;
        });

        optionsButton.setHandler(() -> {
            Greenfoot.setWorld(new Options(Launcher.this));
            return null;
        });

        addObject(startButton, getWidth() / 2, 11);
        addObject(optionsButton, getWidth() / 2, 13);
    }

    private Button getTitle() {
        return new Button("Soultraps", 256, 320, 72f, Color.WHITE) {
            private boolean up = true;
            private int count = 1;
            private int throttleCount = 0;

            private final int throttle = 30;
            private final int hoverAmount = 1;

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
        };
    }

}
