package io.lerk.soultraps.levels.menu;

import greenfoot.Color;
import greenfoot.Greenfoot;
import io.lerk.soultraps.components.Button;
import io.lerk.soultraps.levels.playable.IntroLevel;
import io.lerk.soultraps.levels.types.GrasslandLevel;
import io.lerk.soultraps.levels.types.LevelType;
import io.lerk.soultraps.mobs.Enemies.Bat;
import io.lerk.soultraps.mobs.Enemies.Wolf;
import io.lerk.soultraps.mobs.Enemies.Zombie;
import io.lerk.soultraps.mobs.Player;
import io.lerk.soultraps.sys.Savegame;

/**
 * The launcher or main menu.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Launcher extends GrasslandLevel {

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

        addMob(new Wolf());
        addMob(new Bat());
        addMob(new Zombie());

        Button title = getTitle();
        addObject(title, getWidth() / 2, 3);

        Button startButton = new Button("New Game", 32, 200, 32, Color.WHITE);
        Button optionsButton = new Button("Options", 32, 200, 32, Color.WHITE);

        Button loadButton = new Button("Load Game", 32, 200, 32, Color.WHITE);

        startButton.setHandler(() -> {
            Greenfoot.setWorld(new IntroLevel());
            return null;
        });

        optionsButton.setHandler(() -> {
            Greenfoot.setWorld(new Options(Launcher.this));
            return null;
        });

        addObject(startButton, getWidth() / 2, 11);

        int btnY = 14;
        Savegame savegame = Savegame.read();
        if (savegame != null) {
            loadButton.setHandler(() -> {
                savegame.restorePlayer();
                Greenfoot.setWorld(savegame.getLevel());
                return null;
            });
            addObject(loadButton, getWidth() / 2, btnY);
            btnY += 3;
        }

        addObject(optionsButton, getWidth() / 2, btnY);
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

    /**
     * {@inheritDoc}.
     */
    @Override
    public LevelType getType() {
        return LevelType.MENU;
    }

}
