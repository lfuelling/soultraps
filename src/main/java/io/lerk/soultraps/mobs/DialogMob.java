package io.lerk.soultraps.mobs;

import java.util.ArrayList;

/**
 * A mob that can talk.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public abstract class DialogMob extends BaseMob {

    /**
     * Mob currently talking.
     */
    private boolean talking = false;

    /**
     * Only calls {@link #updateWalkingStateNotTalking()} when not talking.
     * Otherwise walking will be set to false.
     */
    @Override
    protected void updateWalkingState() {
        if (!talking) {
            updateWalkingStateNotTalking();
        } else {
            walking = false;
        }
    }

    /**
     * Same thing {@link BaseMob#updateWalkingState()} does but only when the mob is not talking.
     *
     * @see #updateWalkingState()
     */
    protected abstract void updateWalkingStateNotTalking();

    /**
     * Method to get a {@link DialogMob}'s dialog strings.
     *
     * @return the full dialog of the mob.
     */
    protected abstract ArrayList<String> getDialog();

    /**
     * Getter for {@link #talking}.
     *
     * @return true if the mob is currently talking.
     */
    public boolean isTalking() {
        return talking;
    }

    /**
     * Setter for {@link #talking}.
     *
     * @param talking if the mob is currently talking
     */
    public void setTalking(boolean talking) {
        this.talking = talking;
    }
}
