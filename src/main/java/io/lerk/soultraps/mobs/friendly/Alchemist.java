package io.lerk.soultraps.mobs.friendly;

import greenfoot.Greenfoot;
import io.lerk.soultraps.levels.Level;
import io.lerk.soultraps.mobs.Direction;
import io.lerk.soultraps.mobs.player.Player;
import io.lerk.soultraps.items.HPPotion;
import io.lerk.soultraps.sys.Handler;
import io.lerk.soultraps.sys.console.ConsoleUtil;
import io.lerk.soultraps.sys.dialog.Message;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.lerk.soultraps.sys.Soultraps.Controls.CONV_START;

/**
 * Old man talking gibberish and then adding some health.
 * <p>
 * Might be used in quests later...
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Alchemist extends DialogMob {

    private boolean talked = false;

    public Alchemist() {
        direction = Direction.EAST;
        setImage("images/alchemist/alchemist.png");
    }

    @Override
    protected void updateWalkingStateNotTalking() {
        // it doesn't walk.
    }

    @Override
    protected List<Message> getDialogMessages() {
        if (talked) {
            return Arrays.asList(new Message("Oh, Hello there. I am the alchemist."),
                    new Message("I am on a secret quest."),
                    new Message("I can't tell you anything. Sorry."));
        } else {
            return Arrays.asList(
                    new Message("Hello there. I am the alchemist."),
                    new Message("I am on a quest to find the legendary"),
                    new Message("Golden Potion!"),
                    new Message("It's said to give an exaggerating 2000 HP ! ! !"),
                    new Message("When being handled by a professional like me,"),
                    new Message("it is even said to give magic powers!"),
                    new Message("Like in the old legends..."),
                    new Message("*looks far off in the distance*"),
                    new Message("..."),
                    new Message("Oh, Hello there. I am the alchemist."),
                    new Message("I am on a secret quest."),
                    new Message("I can't tell you anything. Sorry."),
                    new Message("Here, have a potion.")
            );
        }
    }

    @Override
    protected boolean isRecurring() {
        return true;
    }

    @Override
    protected List<Handler<Void>> getDialogDoneActions() {
        return Collections.singletonList(() -> {
            if (!talked) {
                Player.increaseHealth(HPPotion.AMOUNT);
            }
            talked = true;
            return null;
        });
    }

    @Override
    protected boolean shouldStartConversation() {
        return isTouching(Player.class) &&
                (!ConsoleUtil.isConsoleOpen((Level) getWorld()) && Greenfoot.isKeyDown(CONV_START));
    }

    @Override
    public int maxHealth() {
        return Integer.MAX_VALUE;
    }

    @Override
    protected void doAct() {
    }

    @Override
    protected void animateWalking() {
    }
}
