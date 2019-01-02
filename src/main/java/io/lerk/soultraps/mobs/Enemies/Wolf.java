package io.lerk.soultraps.mobs.Enemies;

import java.util.Random;

/**
 * This is one of the easier mobs to deal with.
 * It walks around randomly ond follows the player up to a range of three blocks.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Wolf extends EnemyMob {

    /**
     * Index used for the walking animation.
     */
    private int seqIdx = 0;

    /**
     * Animates walking of the lumberjack.
     */
    @Override
    protected void animateWalking() {
        if (seqIdx > 4) {
            seqIdx = 0;
        }
        if (walking) {
            this.setImage("images/wolf/wolf" + (seqIdx + 1) + ".png");
        } else {
            this.setImage("images/wolf/wolf1.png");
        }
        seqIdx++;
    }

    /**
     * Method that returns the max health of the lumberjack.
     *
     * @return max possible health
     */
    @Override
    protected int maxHealth() {
        return 50;
    }

    /**
     * Throttled act method that is used for anything else than walking.
     */
    @Override
    protected void doAct() {
    }

    /**
     * Determines the damage dealt to the player.
     *
     * @return the damage dealt to the player
     */
    @Override
    public int attack() {
        return new Random().nextInt(15);
    }

    /**
     * Determines if the wolf should block the player's attack.
     *
     * @return false, wolf not blocc, just attacc
     */
    @Override
    public boolean block() {
        return false;
    }

    /**
     * Determines if the wolf should try to run.
     *
     * @return false, wolf not run, wolf just attacc
     */
    @Override
    public boolean run() {
        return false;
    }
}
