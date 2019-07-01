package io.lerk.soultraps.mobs.friendly;

import io.lerk.soultraps.mobs.BaseMob;
import io.lerk.soultraps.sys.Handler;
import io.lerk.soultraps.sys.dialog.Dialog;
import io.lerk.soultraps.sys.dialog.DialogManager;
import io.lerk.soultraps.sys.dialog.Message;

import java.util.ArrayList;
import java.util.List;

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
            walking = false;
            if (shouldStartConversation()) {
                DialogManager.startDialog(getDialog());
            } else {
                updateWalkingStateNotTalking();
            }
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
    protected abstract List<Message> getDialogMessages();

    /**
     * Method to get the mobs dialog.
     *
     * @return the dialog
     */
    protected Dialog getDialog() {
        Dialog dialog = new Dialog();
        dialog.setMob(this);
        dialog.setMessages(new ArrayList<>(getDialogMessages()));
        getDialogDoneActions().forEach(dialog::addDoneAction);
        return dialog;
    }

    /**
     * Method to get the {@link Handler} that is run after the dialog is done.
     *
     * @return the Handler to be run
     */
    protected abstract List<Handler<Void>> getDialogDoneActions();

    /**
     * Method to determine if the mob should start a conversation.
     *
     * @return true if a conversation should be started.
     */
    protected abstract boolean shouldStartConversation();

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
