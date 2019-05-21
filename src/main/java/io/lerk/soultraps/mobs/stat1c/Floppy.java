package io.lerk.soultraps.mobs.stat1c;

import io.lerk.soultraps.levels.Level;
import io.lerk.soultraps.mobs.DialogMob;
import io.lerk.soultraps.mobs.Player;
import io.lerk.soultraps.sys.Handler;
import io.lerk.soultraps.sys.Savegame;
import io.lerk.soultraps.sys.dialog.Message;
import io.lerk.soultraps.tiles.TileActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * The portal that takes the player to the next level.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Floppy extends DialogMob {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(Floppy.class);

    /**
     * Index used for the animation.
     */
    private int seqIdx = 0;

    private static boolean savingCalled = false;

    /**
     * Constructor.
     */
    public Floppy() {
        this.setImage("images/floppy/floppy1.png");
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    protected List<Message> getDialogMessages() {
        return Collections.singletonList(new Message("You stepped onto a floppy. Your progress will be saved.", dialog));
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    protected Handler<Void> getDialogDoneAction() {
        return () -> {
            if(savingCalled == false) {
                savingCalled = true;
                log.info("Player stepped on a floppy. Saving...");
                Savegame savegame = new Savegame(((Level) getWorld()), Player.getSelf());
                if(Savegame.isErrorShown()) {
                    return null;
                }
                savegame.write();
                placePlayerNextToPortal();
            }
            return null;
        };
    }

    /**
     * Places the player to the next free spot next to a portal
     * to avoid triggering the save function all the time.
     */
    private void placePlayerNextToPortal() {
        int x = Player.getSelf().getX() - 1;
        int y = Player.getSelf().getY() - 1;
        while (getWorld().getObjectsAt(x, y, TileActor.class).stream().anyMatch(TileActor::isOverlappingTileOtherThanEmptyOrGround)) {
            if (x <= 0) {
                x = Level.LEVEL_WIDTH;
            } else {
                x = x - 1;
            }
            if (y <= -Level.LEVEL_HEIGHT) {
                y = 0;
            } else {
                y = y - 1;
            }
        }
        Player.getSelf().setLocation(Player.getSelf().getX(), Player.getSelf().getY());
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    protected boolean shouldStartConversation() {
        return getIntersectingObjects(Player.class).size() > 0;
    }

    /**
     * Stub.
     *
     * @return {@link Integer#MAX_VALUE}
     */
    @Override
    protected int maxHealth() {
        return Integer.MAX_VALUE;
    }

    /**
     * Stub. the floppy does nothing.
     */
    @Override
    protected void doAct() {
    }

    /**
     * Stub. The Floppy doesn't walk.
     */
    @Override
    protected void walk() {
    }

    /**
     * Stub. The Floppy doesn't walk.
     */
    @Override
    protected void updateWalkingStateNotTalking() {
        direction = Direction.EAST;
        walking = true;
    }

    /**
     * Stub. The floppy has no walking animation.
     */
    @Override
    protected void animateWalking() {
        if (seqIdx > 5) {
            seqIdx = 0;
        }
        if (walking) {
            this.setImage("images/floppy/floppy" + (seqIdx + 1) + ".png");
        } else {
            this.setImage("images/floppy/floppy1.png");
        }
        seqIdx++;
    }
}