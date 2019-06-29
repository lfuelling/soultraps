package io.lerk.soultraps.levels.menu;

import greenfoot.Color;
import greenfoot.Greenfoot;
import io.lerk.soultraps.components.Button;
import io.lerk.soultraps.components.MovingHeading;
import io.lerk.soultraps.levels.types.HellLevel;
import io.lerk.soultraps.levels.types.LevelType;
import io.lerk.soultraps.mobs.Enemies.Zombie;
import io.lerk.soultraps.mobs.Player;

public class GameOverScreen extends HellLevel {

    /**
     * Constructor.
     */
    public GameOverScreen() {
        super();

        addMob(new Zombie());
        addMob(new Zombie());

        Button title = new MovingHeading("GameOver", 64, 320, 72f, Color.RED);
        addObject(title, getWidth() / 2, 3);

        Button menuButton = new Button("Main Menu", 32, 200, 32, Color.WHITE);

        menuButton.setHandler(() -> {
            Greenfoot.setWorld(new Launcher());
            return null;
        });

        addObject(menuButton, getWidth() / 2, 11);

        Player.getSelf().reset(); // reset player
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public LevelType getType() {
        return LevelType.MENU;
    }
}
