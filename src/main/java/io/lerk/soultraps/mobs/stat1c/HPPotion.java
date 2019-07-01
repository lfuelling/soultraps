package io.lerk.soultraps.mobs.stat1c;

import io.lerk.soultraps.mobs.BaseMob;
import io.lerk.soultraps.mobs.Direction;
import io.lerk.soultraps.mobs.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HPPotion extends BaseMob {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(HPPotion.class);

    public static final int AMOUNT = 25;

    /**
     * Constructor.
     */
    public HPPotion() {
        direction = Direction.EAST;
        this.setImage("images/hp-potion.png");
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
     * Stub. the potion does nothing.
     */
    @Override
    protected void doAct() {
        if (isTouching(Player.class)) {
            log.info("Player picked up a hp potion!");
            Player.increaseHealth(AMOUNT);
            getWorld().removeObject(this);
        }
    }

    /**
     * Stub. The potion doesn't walk.
     */
    @Override
    protected void walk() {
    }

    /**
     * Stub. The potion has no walking animation.
     */
    @Override
    protected void animateWalking() {
    }

    /**
     * Stub. The potion has no walking animation.
     */
    @Override
    protected void updateWalkingState() {
    }

}
