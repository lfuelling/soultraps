package io.lerk.soultraps.mobs.Enemies;

import java.util.Random;

import static io.lerk.soultraps.tiles.Tiles.FILE_SUFFIX;

/**
 * This mob is a bit more challenging, suitable for more advanced players.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 * @author Nicolai Süper (nico@k40s.net)
 */
public class Zombie extends EnemyMob {

    /**
     * Index used for the walking animation.
     */
    private int seqIdx = 0;

    /**
     * Constructor.
     */
    public Zombie() {
        super();
        this.setImage("images/zombie/zombie_0.png");
    }

    /**
     * Animates walking of the lumberjack.
     */
    @Override
    protected void animateWalking() {
        if (seqIdx > 3) {
            seqIdx = 0;
        }
        if (walking) {
            this.setImage("images/zombie/zombie_" + seqIdx + FILE_SUFFIX);
        } else {
            this.setImage("images/zombie/zombie_0.png");
        }
        seqIdx++;
    }

    /**
     * Method that returns the max health of the lumberjack.
     *
     * @return max possible health
     */
    @Override
    public int maxHealth() {
        return 200;
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
        return new Random().nextInt(50);
    }

    /**
     * Determines if the zombie should block the player's attack.
     *
     * @return random
     */
    @Override
    public boolean block() {
        return new Random().nextBoolean();
    }

    /**
     * Determines if the zombie should try to run.
     *
     * @return false, zombie not run, zombie just attacc
     */
    @Override
    public boolean run() {
        return false;
    }
}
