package io.lerk.soultraps.sys.dialog;

import io.lerk.soultraps.mobs.friendly.DialogMob;
import io.lerk.soultraps.sys.Handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
     * If false, the dialog can only be triggered once per level.
     */
    private boolean recurring;

    /**
     * If this dialog was shown already.
     * @see #recurring
     */
    private boolean shown = false;

    /**
     * The action that should be run when the dialog is done.
     */
    private ArrayList<Handler<Void>> doneActions = new ArrayList<>();

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
    public List<Handler<Void>> getDoneActions() {
        return Collections.unmodifiableList(doneActions);
    }

    /**
     * Adds a done action to the queue.
     *
     * @param doneAction the done action
     */
    public void addDoneAction(Handler<Void> doneAction) {
        doneActions.add(doneAction);
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public boolean wasShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }
}
