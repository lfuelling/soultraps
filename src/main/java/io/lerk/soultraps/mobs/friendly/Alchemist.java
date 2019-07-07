package io.lerk.soultraps.mobs.friendly;

import greenfoot.Greenfoot;
import io.lerk.soultraps.items.GoldenDistillate;
import io.lerk.soultraps.items.GoldenPotion;
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

    private static boolean talked = false;
    private static boolean potionCollected = false;
    private static boolean distillateGiven = false;

    public Alchemist() {
        direction = Direction.EAST;
        setImage("images/alchemist/alchemist.png");
    }

    @Override
    protected void updateWalkingStateNotTalking() {
        List<GoldenPotion> goldenPotionsInRange = getWorld().getObjects(GoldenPotion.class);
        if (goldenPotionsInRange.size() > 0 && !isTouching(GoldenPotion.class)) {
            goldenPotionsInRange.forEach(p -> {
                double a = Math.atan2((double)(p.getY() - this.getY()), (double)(p.getX() - this.getX()));
                direction = Direction.fromDegrees((int)Math.toDegrees(a));
                walking = true;
            });
        } else {
            walking = false;
            direction = Direction.EAST;
        }
    }

    @Override
    protected List<Message> getDialogMessages() {
        if (playerHasGoldenPotion()) {
            return Arrays.asList(new Message("Oh, Hello there. I am the alchemist."),
                    new Message("By the gods, is this the legendary GOLDEN POTION?"),
                    new Message("It's said to give " + GoldenPotion.AMOUNT + " HP!"),
                    new Message("Good thing you didn't use it."),
                    new Message("I know how to distill it so it has magic powers!"),
                    new Message("I have searched for it all my life!"),
                    new Message("Would you mind helping an old man fulfill his dream?"),
                    new Message("You can have the distillate. I just want to do it!"));
        } else if (potionCollected) {
            return Arrays.asList(new Message("I can't believe it! I finally did it!"),
                    new Message("The process worked flawlessly!"),
                    new Message("I bet the potion is a lot stronger now. Be careful!"));
        } else if (talked) {
            return Arrays.asList(new Message("Oh, Hello there. I am the alchemist."),
                    new Message("I am on a secret quest."),
                    new Message("I can't tell you anything. Sorry."));
        } else {
            return Arrays.asList(
                    new Message("Hello there. I am the alchemist."),
                    new Message("I am on a quest to find the legendary"),
                    new Message("Golden Potion!"),
                    new Message("It's said to give an exaggerating " + GoldenPotion.AMOUNT + " HP ! ! !"),
                    new Message("When being handled by a professional like me,"),
                    new Message("it is even said to give magic powers!"),
                    new Message("Like in the old legends..."),
                    new Message("*looks far off in the distance*"),
                    new Message("..."),
                    new Message(". . ."),
                    new Message("Oh, Hello there. I am the alchemist."),
                    new Message("I am on a secret quest."),
                    new Message("I can't tell you anything. Sorry."),
                    new Message("Here, have a potion.")
            );
        }
    }

    private boolean playerHasGoldenPotion() {
        return Player.getSelf().getItems().stream()
                .anyMatch(i -> i.getClass().equals(GoldenPotion.class));
    }

    @Override
    protected boolean isRecurring() {
        return true;
    }

    @Override
    protected List<Handler<Void>> getDialogDoneActions() {
        return Collections.singletonList(() -> {
            if (!talked) {
                Player.getSelf().addItem(new HPPotion());
            } else if (!distillateGiven && potionCollected) {
                Player.getSelf().addItem(new GoldenDistillate());
                distillateGiven = true;
            }
            talked = true;
            return null;
        });
    }

    @Override
    protected boolean shouldStartConversation() {
        return playerWantsToTalk() || collectedGoldenPotion();
    }

    private boolean collectedGoldenPotion() {
        List<GoldenPotion> potionsInRange = getIntersectingObjects(GoldenPotion.class);
        if (potionsInRange.size() > 0) {
            potionsInRange.forEach(p -> {
                getWorld().removeObject(p);
                potionCollected = true;
            });
        }
        return false;
    }

    private boolean playerWantsToTalk() {
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
