package io.lerk.soultraps.levels.menu;

import greenfoot.Color;
import greenfoot.Greenfoot;
import io.lerk.soultraps.components.Button;
import io.lerk.soultraps.components.MovingHeading;
import io.lerk.soultraps.levels.playable.IntroLevel;
import io.lerk.soultraps.levels.types.GrasslandLevel;
import io.lerk.soultraps.levels.types.LevelType;
import io.lerk.soultraps.mobs.Enemies.Bat;
import io.lerk.soultraps.mobs.Enemies.Wolf;
import io.lerk.soultraps.mobs.Enemies.Zombie;
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

        Button title = new MovingHeading("Soultraps", 64, 320, 72f, Color.WHITE);
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
     * {@inheritDoc}.
     */
    @Override
    public LevelType getType() {
        return LevelType.MENU;
    }

}
