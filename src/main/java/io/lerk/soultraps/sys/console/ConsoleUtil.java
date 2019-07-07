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

    public static void closeConsole() {
        moi.consoleOpen = false;
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
        initIfNecessary(level);
        moi.handleInternal();
    }

    private static void initIfNecessary(Level level) {
        if(moi == null) {
            moi = new ConsoleUtil(level);
        } else if (levelChanged(level)) {
            moi = new ConsoleUtil(level);
        }
    }

    private static boolean levelChanged(Level level) {
        return !level.getUniqueId().equals(moi.level.getUniqueId());
    }

    private void handleInternal() {
        if(consoleOpen) {
            if(Greenfoot.isKeyDown("escape")) {
                consoleOpen = false;
            } else if(console == null) {
                console = new Console();
                level.addObject(console, getX(), getY());
            }
        } else if(Greenfoot.isKeyDown("c") && Greenfoot.isKeyDown("shift")) {
            consoleOpen = true;
        } else if(level.getObjects(Console.class).size() > 0) {
            level.removeObjects(level.getObjects(Console.class));
        } else if(console != null) {
            console = null;
        }
    }

    public static boolean isConsoleOpen(Level level) {
        initIfNecessary(level);
        return moi.consoleOpen;
    }
}
