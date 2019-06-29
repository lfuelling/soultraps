package io.lerk.soultraps.mobs.stat1c;

import io.lerk.soultraps.mobs.Player;
import io.lerk.soultraps.mobs.friendly.DialogMob;
import io.lerk.soultraps.sys.Handler;
import io.lerk.soultraps.sys.dialog.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

import static io.lerk.soultraps.tiles.Tiles.FILE_SUFFIX;

public class HellCastle extends DialogMob {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(HellCastle.class);

    /**
     * Index used for the animation.
     */
    private int seqIdx = 0;

    /**
     * Constructor.
     */
    public HellCastle() {
        this.setImage("images/hell/castle/castle01.png");
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    protected List<Message> getDialogMessages() {
        return Collections.singletonList(new Message("The flames are too high to step through...", dialog));
        //TODO: add item to go through the flames
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    protected Handler<Void> getDialogDoneAction() {
        return () -> null;
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

    @Override
    protected void doAct() {
        if (getRotation() != 0) {
            setRotation(0);
        }
        if (getObjectsInRange(9, Player.class).size() > 0) {
            if (getObjectsInRange(5, Player.class).size() > 0) {
                if (seqIdx > 3) {
                    seqIdx = 0;
                }
                if (walking) {
                    this.setImage("images/hell/castle/castle02-open" + (seqIdx + 1) + FILE_SUFFIX);
                } else {
                    this.setImage("images/hell/castle/castle02-open1.png");
                }
                seqIdx++;
            } else {
                setImage("images/hell/castle/castle01-open.png");
            }
        } else {
            setImage("images/hell/castle/castle01.png");
        }
    }

    /**
     * Stub. The Castle doesn't walk.
     */
    @Override
    protected void walk() {
    }

    /**
     * Stub. The Castle doesn't walk.
     */
    @Override
    protected void animateWalking() {
    }

    /**
     * Stub. The Castle doesn't walk.
     */
    @Override
    protected void updateWalkingStateNotTalking() {
        walking = true;
    }

}
