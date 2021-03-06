package io.lerk.soultraps.mobs.Enemies;

/**
 * Interface that mobs implement to be able to make fights.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public interface Enemy {

    Type getType();

    /**
     * This method is called in combat and determines the damage the player gets.
     *
     * @return the damage dealt in this attack
     */
    int attack();

    /**
     * This method decides if the enemy should block a player's attack.
     *
     * @return true if the attack was blocked
     */
    boolean block();

    /**
     * This method decides if the enemy should try to run.
     *
     * @return true if the enemy should try to run
     */
    boolean run();

    /**
     * This method deals damage to the enemy.
     *
     * @param damage the amount of damage.
     */
    void dealDamage(int damage);

    enum Type {
        ANIMAL, HUMANOID, UNDEAD, STATIC;
    }
}
