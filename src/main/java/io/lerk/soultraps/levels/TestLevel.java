package io.lerk.soultraps.levels;

import greenfoot.Greenfoot;
import io.lerk.soultraps.levels.playable.RegularGrasslandLevel;
import io.lerk.soultraps.mobs.Player;
import io.lerk.soultraps.sys.dialog.DialogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

public class TestLevel extends RegularGrasslandLevel {

    private static final Logger log = LoggerFactory.getLogger(TestLevel.class);

    private static TestLevel moi;
    private final AtomicReference<Level> previousLevel;

    private TestLevel(Level previousLevel) {
        super(); // call super() or level won't be initialized
        this.previousLevel = new AtomicReference<>(previousLevel);
    }

    public static TestLevel get(Level level) {
        if (moi == null) {
            moi = new TestLevel(level);
        } else if (levelChanged(level)) {
            moi.previousLevel.set(level);
        }
        return moi;
    }

    private static boolean levelChanged(Level previousLevel) {
        Level level = moi.previousLevel.get();
        return level == null || !previousLevel.getClass().equals(level.getClass());
    }

    public static void exit() {
        if(moi != null) {
            if(moi.previousLevel != null) {
                Level level = moi.previousLevel.get();
                if(level != null) {
                    Greenfoot.setWorld(level);
                } else {
                    log.error("Level is null!");
                }
            }
        }
    }

    @Override
    protected void addLevelContent() {
        log.debug("Adding DialogManager...");
        addObject(DialogManager.get(), 0, 0);
        log.info("Adding player...");
        addMob(Player.getSelf());
    }
}
