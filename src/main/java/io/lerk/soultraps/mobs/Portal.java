package io.lerk.soultraps.mobs;

import greenfoot.Greenfoot;
import io.lerk.soultraps.levels.RegularLevel;
import io.lerk.soultraps.sys.Handler;
import io.lerk.soultraps.sys.dialog.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

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
     * In this method it's checked if teh player is stepping on the portal.
     * If so, a dialog will be shown and the player will be moved afterwards.
     */
    @Override
    protected void updateWalkingStateNotTalking() {
        walking = false;
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
            Greenfoot.setWorld(new RegularLevel());
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
    protected int maxHealth() {
        return Integer.MAX_VALUE;
    }

    /**
     * Stub. the portal does nothing.
     */
    @Override
    protected void doAct() {
    }

    /**
     * Stub. The portal has no walking animation.
     */
    @Override
    protected void animateWalking() {
    }
}
