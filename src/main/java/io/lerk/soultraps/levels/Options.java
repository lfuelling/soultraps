package io.lerk.soultraps.levels;

import greenfoot.Color;
import greenfoot.Greenfoot;
import io.lerk.soultraps.components.Button;
import io.lerk.soultraps.levels.Level;

public class Options extends Level {
    public Options(final Launcher launcher) {
        super();
        Button title = new Button("Options", 100, 200, 48f, Color.WHITE);
        addObject(title, getWidth() / 2, 5);

        Button difficultyButton = new Button("Difficulty", 70, 200, 32, Color.WHITE);
        Button backButton = new Button("< Back", 70, 200, 28, Color.WHITE);

        difficultyButton.setHandler(() -> {
            return null;
        });

        backButton.setHandler(() -> {
            Greenfoot.setWorld(launcher);
            return null;
        });

        addObject(difficultyButton, getWidth() / 2, 11);
        addObject(backButton, getWidth() / 2, getHeight() - 5);
    }
}
