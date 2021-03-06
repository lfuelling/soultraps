package io.lerk.soultraps.sys.test;

import greenfoot.Greenfoot;
import io.lerk.soultraps.components.HUD;
import io.lerk.soultraps.items.GoldenDistillate;
import io.lerk.soultraps.items.GoldenPotion;
import io.lerk.soultraps.levels.Level;
import io.lerk.soultraps.levels.menu.GameOverScreen;
import io.lerk.soultraps.levels.menu.Launcher;
import io.lerk.soultraps.levels.menu.Options;
import io.lerk.soultraps.levels.playable.IntroLevel;
import io.lerk.soultraps.levels.playable.GenericGrasslandLevel;
import io.lerk.soultraps.mobs.friendly.Alchemist;
import io.lerk.soultraps.mobs.player.Player;
import io.lerk.soultraps.items.HPPotion;
import io.lerk.soultraps.mobs.stat1c.HellPortal;
import io.lerk.soultraps.mobs.stat1c.Portal;
import io.lerk.soultraps.sys.dialog.DialogManager;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

public class TestLevel extends GenericGrasslandLevel {

    private static final Logger log = LoggerFactory.getLogger(TestLevel.class);

    private static TestLevel moi; // It's not a singleton, it's a frankenton.

    private final AtomicReference<Level> previousLevel;

    public TestLevel(Level previousLevel) {
        super(); // call super() or level won't be initialized
        this.previousLevel = new AtomicReference<>(previousLevel);
        moi = this;
    }

    public static void exit() {
        if (moi != null) {
            if (moi.previousLevel != null) {
                Level level = moi.previousLevel.get();
                if (level != null) {
                    reInitializeLevel(level);
                } else {
                    log.error("Level is null!");
                }
            }
        }
    }

    private static void reInitializeLevel(Level level) {
        try {
            Class<? extends Level> levelClass = level.getClass();
            if (levelClass.equals(IntroLevel.class) ||
                    levelClass.equals(Options.class) ||
                    levelClass.equals(Launcher.class) ||
                    levelClass.equals(GameOverScreen.class)) {
                // non-reinitializable levels
                Greenfoot.setWorld(levelClass.newInstance());
            } else {
                Constructor<? extends Level> levelConstructor = levelClass
                        .getConstructor(String[][].class, Pair.class, Pair.class);
                Level reinitializedLevel = levelConstructor
                        .newInstance(level.getLevelTiles(), level.getPortalCoordinates(), level.getFloppyCoordinates());
                Greenfoot.setWorld(reinitializedLevel);
            }
        } catch (NoSuchMethodException |
                IllegalAccessException |
                InstantiationException |
                InvocationTargetException e) {
            log.error("Unable to restore level!", e);
        }
    }

    @Override
    protected void addLevelContent() {
        log.debug("Adding DialogManager...");
        addObject(DialogManager.get(), 0, 0);
        log.info("Adding Potions...");
        for (int i = 0; i < 4; i++) {
            addMob(new HPPotion());
        }
        addMob(new GoldenPotion());
        addMob(new GoldenDistillate());
        log.info("Adding Portal...");
        addMob(new Portal());
        log.info("Adding Hell Portal...");
        addMob(new HellPortal());
        log.info("Adding alchemist...");
        addMob(new Alchemist());
        log.info("Adding player...");
        addMob(Player.getSelf());
        log.info("Adding HUD...");
        addObject(new HUD(), HUD_X, HUD_Y);
    }
}
