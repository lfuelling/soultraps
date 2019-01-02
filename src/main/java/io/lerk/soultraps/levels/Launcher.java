package io.lerk.soultraps.levels;

import greenfoot.Color;
import greenfoot.Greenfoot;
import io.lerk.soultraps.components.Button;

/**
 * The launcher or main menu.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Launcher extends Level {

    /**
     * Base world width.
     */
    public static final int BASE_WIDTH = 64;

    /**
     * Base world height.
     */
    public static final int BASE_HEIGHT = 32;

    /**
     * Constructor.
     */
    public Launcher() {
        super();
        Button title = getTitle();
        addObject(title, getWidth() / 2, 3);

        Button startButton = new Button("Start Game", 32, 200, 32, Color.WHITE);
        Button optionsButton = new Button("Options", 32, 200, 32, Color.WHITE);

        startButton.setHandler(() -> {
            Greenfoot.setWorld(new IntroLevel());
            return null;
        });

        optionsButton.setHandler(() -> {
            Greenfoot.setWorld(new Options(Launcher.this));
            return null;
        });

        addObject(startButton, getWidth() / 2, 11);
        addObject(optionsButton, getWidth() / 2, 14);
    }

    /**
     * Generates the title which is a {@link Button} anonymous inner class.
     *
     * @return the title.
     */
    private Button getTitle() {
        return new Button("Soultraps", 64, 320, 72f, Color.WHITE) {
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
