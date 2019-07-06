package io.lerk.soultraps.mobs.stat1c;

import io.lerk.soultraps.levels.Level;
import io.lerk.soultraps.mobs.Direction;
import io.lerk.soultraps.mobs.friendly.DialogMob;
import io.lerk.soultraps.mobs.player.Player;
import io.lerk.soultraps.sys.Handler;
import io.lerk.soultraps.sys.Savegame;
import io.lerk.soultraps.sys.dialog.Message;
import io.lerk.soultraps.tiles.TileActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

import static io.lerk.soultraps.tiles.Tiles.FILE_SUFFIX;

/**
 * The icon that lets a player save the game.
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
        return Collections.singletonList(new Message("You stepped onto a floppy disk. Your progress will be saved."));
    }

    @Override
    protected boolean isRecurring() {
        return false;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    protected List<Handler<Void>> getDialogDoneActions() {
        return Collections.singletonList(() -> {
            if(!savingCalled) {
                savingCalled = true;
                log.info("Player stepped on a floppy. Saving...");
                Savegame savegame = new Savegame(((Level) getWorld()), Player.getSelf());
                if(Savegame.isErrorShown()) {
                    return null;
                }
                savegame.write();
                placePlayerNextToFloppy();
            }
            return null;
        });
    }

    /**
     * Places the player to the next free spot next to a floppy
     * to avoid triggering the save function all the time.
     */
    private void placePlayerNextToFloppy() {
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
    public int maxHealth() {
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
            this.setImage("images/floppy/floppy" + (seqIdx + 1) + FILE_SUFFIX);
        } else {
            this.setImage("images/floppy/floppy1.png");
        }
        seqIdx++;
    }
}