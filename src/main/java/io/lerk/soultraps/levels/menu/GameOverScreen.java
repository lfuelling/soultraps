package io.lerk.soultraps.levels.menu;

import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.World;
import io.lerk.soultraps.components.Button;
import io.lerk.soultraps.components.MovingHeading;
import io.lerk.soultraps.levels.playable.IntroLevel;
import io.lerk.soultraps.levels.types.GrasslandLevel;
import io.lerk.soultraps.levels.types.LevelType;
import io.lerk.soultraps.mobs.Enemies.Bat;
import io.lerk.soultraps.mobs.Enemies.Wolf;
import io.lerk.soultraps.mobs.Enemies.Zombie;
import io.lerk.soultraps.sys.Savegame;

public class GameOverScreen extends GrasslandLevel {
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
    public GameOverScreen() {
        super();

        addMob(new Wolf());
        addMob(new Bat());
        addMob(new Zombie());

        Button title = new MovingHeading("GameOver", 64, 320, 72f, Color.RED);
        addObject(title, getWidth() / 2, 3);

        Button menuButton = new Button("Main Menu", 32, 200, 32, Color.WHITE);

        menuButton.setHandler(() -> {
            Greenfoot.setWorld(new Launcher());
            return null;
        });

        addObject(menuButton, getWidth() / 2, 11);

    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public LevelType getType() {
        return LevelType.MENU;
    }
}
