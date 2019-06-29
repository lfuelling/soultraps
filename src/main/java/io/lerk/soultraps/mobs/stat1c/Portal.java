package io.lerk.soultraps.mobs.stat1c;

import greenfoot.Greenfoot;
import io.lerk.soultraps.levels.playable.RegularDesertLevel;
import io.lerk.soultraps.levels.playable.RegularGrasslandLevel;
import io.lerk.soultraps.mobs.friendly.DialogMob;
import io.lerk.soultraps.mobs.Player;
import io.lerk.soultraps.sys.Handler;
import io.lerk.soultraps.sys.dialog.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The portal that takes the player to the next level.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Portal extends DialogMob {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(Portal.class);

    /**
     * Index used for the animation.
     */
    private int seqIdx = 0;

    /**
     * Constructor.
     */
    public Portal() {
        this.setImage("images/portal/portal1.png");
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    protected List<Message> getDialogMessages() {
        return Collections.singletonList(new Message("You stepped onto a portal. You will now be teleported.", dialog));
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    protected Handler<Void> getDialogDoneAction() {
        return () -> {
            log.info("Player stepped on a portal. Teleporting...");
            Greenfoot.setWorld((new Random().nextBoolean()) ? new RegularDesertLevel() : new RegularGrasslandLevel());
            return null;
        };
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
     * Stub. the portal does nothing.
     */
    @Override
    protected void doAct() {
    }

    /**
     * Stub. The Portal doesn't walk.
     */
    @Override
    protected void walk() {
    }

    /**
     * Stub. The Portal doesn't walk.
     */
    @Override
    protected void updateWalkingStateNotTalking() {
        walking = true;
    }

    /**
     * Stub. The portal has no walking animation.
     */
    @Override
    protected void animateWalking() {
        if (seqIdx > 4) {
            seqIdx = 0;
        }
        if (walking) {
            this.setImage("images/portal/portal" + (seqIdx + 1) + ".png");
        } else {
            this.setImage("images/portal/portal1.png");
        }
        seqIdx++;
    }
}
