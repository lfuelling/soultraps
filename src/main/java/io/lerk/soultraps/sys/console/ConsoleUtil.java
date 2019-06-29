package io.lerk.soultraps.sys.console;

import greenfoot.Greenfoot;
import io.lerk.soultraps.levels.Level;

public final class ConsoleUtil {

    private static ConsoleUtil moi;
    private final Level level;
    private Console console = null;

    private boolean consoleOpen = false;

    private ConsoleUtil(Level level) {
        this.level = level;
    }

    private int getY() {
        return calculateCenter(level.getHeight(), console.getImage().getHeight());
    }

    private int getX() {
        return calculateCenter(level.getWidth(), console.getImage().getWidth());
    }

    private int calculateCenter(int i, int ii) {
        return (i / 2) - (ii / 2);
    }

    public static void handle(Level level) {
        if(moi == null) {
            moi = new ConsoleUtil(level);
        } else if (levelChanged(level)) {
            moi = new ConsoleUtil(level);
        }
        moi.handleInternal();
    }

    private static boolean levelChanged(Level level) {
        return !level.getClass().equals(moi.level.getClass());
    }

    private void handleInternal() {
        if(consoleOpen) {
            if(Greenfoot.isKeyDown("escape")) {
                consoleOpen = false;
            } else if(console == null) {
                console = new Console();
                level.addObject(console, getX(), getY());
            }
        } else if(Greenfoot.isKeyDown("c")) {
            consoleOpen = true;
        } else if(console != null) {
            level.removeObject(console);
            console = null;
        }
    }
}
