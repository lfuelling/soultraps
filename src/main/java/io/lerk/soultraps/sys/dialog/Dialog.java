package io.lerk.soultraps.sys.dialog;

import io.lerk.soultraps.mobs.friendly.DialogMob;
import io.lerk.soultraps.sys.Handler;

import java.util.ArrayList;

/**
 * A dialog that a mob starts with a player.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Dialog {

    /**
     * The messages of the dialog.
     */
    private ArrayList<Message> messages;

    /**
     * The mob that starts this dialog.
     */
    private DialogMob mob;

    /**
     * The action that should be run when the dialog is done.
     */
    private Handler doneAction;

    /**
     * Constructor.
     */
    public Dialog() {
    }

    /**
     * Getter for the dialog's messages.
     *
     * @return the messages
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }

    /**
     * Setter for the dialog's messages.
     *
     * @param messages the messages
     */
    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    /**
     * Getter for the dialogs mob.
     *
     * @return hte mob
     */
    public DialogMob getMob() {
        return mob;
    }

    /**
     * Setter for the dialog's mob.
     *
     * @param mob the mob
     */
    public void setMob(DialogMob mob) {
        this.mob = mob;
    }

    /**
     * Getter for the dialog's done action.
     *
     * @return the done action
     */
    public Handler<Void> getDoneAction() {
        return doneAction;
    }

    /**
     * Setter for the dialog's done action.
     *
     * @param doneAction the done action
     */
    public void setDoneAction(Handler<Void> doneAction) {
        this.doneAction = doneAction;
    }
}
