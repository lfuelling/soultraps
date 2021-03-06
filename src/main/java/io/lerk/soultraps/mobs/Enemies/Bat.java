package io.lerk.soultraps.mobs.Enemies;

import java.util.Random;

import static io.lerk.soultraps.tiles.Tiles.FILE_SUFFIX;

/**
 * This is probably the asiest enemy mobs in the game.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 * @author Nicolai Süper (nico@k40s.net)
 */
public class Bat extends EnemyMob {

    /**
     * Index used for the walking animation.
     */
    private int seqIdx = 0;

    /**
     * Constructor.
     */
    public Bat() {
        super();
        this.setImage("images/bat/bat_0.png");
    }

    /**
     * Animates walking of the lumberjack.
     */
    @Override
    protected void animateWalking() {
        if (seqIdx > 7) {
            seqIdx = 0;
        }
        if (walking) {
            this.setImage("images/bat/bat_" + seqIdx + FILE_SUFFIX);
        } else {
            this.setImage("images/bat/bat_0.png");
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
        return 25;
    }

    /**
     * Throttled act method that is used for anything else than walking.
     */
    @Override
    protected void doAct() {
    }

    @Override
    public Type getType() {
        return Type.ANIMAL;
    }

    /**
     * Determines the damage dealt to the player.
     *
     * @return the damage dealt to the player
     */
    @Override
    public int attack() {
        return new Random().nextInt(5);
    }

    /**
     * Determines if the bat should block the player's attack.
     *
     * @return false, bat not blocc, just attacc
     */
    @Override
    public boolean block() {
        return false;
    }

    /**
     * Determines if the bat should try to run.
     *
     * @return random
     */
    @Override
    public boolean run() {
        return new Random().nextBoolean();
    }
}
