package io.lerk.soultraps.mobs.friendly;

import greenfoot.Greenfoot;
import io.lerk.soultraps.items.Axe;
import io.lerk.soultraps.levels.Level;
import io.lerk.soultraps.mobs.Direction;
import io.lerk.soultraps.mobs.player.Player;
import io.lerk.soultraps.sys.Handler;
import io.lerk.soultraps.sys.console.ConsoleUtil;
import io.lerk.soultraps.sys.dialog.Message;
import io.lerk.soultraps.tiles.DesertTiles;
import io.lerk.soultraps.tiles.GrasslandTiles;
import io.lerk.soultraps.tiles.TileActor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static io.lerk.soultraps.sys.Soultraps.Controls.CONV_START;
import static io.lerk.soultraps.tiles.Tiles.FILE_SUFFIX;

/**
 * The lumberjack walks around randomly when no trees are in range, otherwise it moves towards them with some chance.
 * If the player is overlapping the lumberjack, a dialog is started that gives the {@link Axe} to the player.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Lumberjack extends DialogMob {

    /**
     * Index used for the walking animation.
     */
    private int seqIdx = 0;

    /**
     * Updates the walking state according to trees in range or if the player is currently overlapping.
     */
    @Override
    protected void updateWalkingStateNotTalking() {
        if (new Random().nextBoolean()) {
            if (isTreeInRangeNorth()) {
                walking = true;
                direction = Direction.NORTH;
            } else if (isTreeInRangeEast()) {
                walking = true;
                direction = Direction.EAST;
            } else if (isTreeInRangeSouth()) {
                walking = true;
                direction = Direction.SOUTH;
            } else if (isTreeInRangeWest()) {
                walking = true;
                direction = Direction.WEST;
            } else {
                randomWalkingState();
            }
        } else {
            randomWalkingState();
        }
    }

    /**
     * Checks if the {@link Player}'s inventory contains an {@link Axe}.
     *
     * @return true if the player already has an axe.
     */
    private boolean playerHasAxe() {
        return Player.getSelf().getItems().stream().anyMatch(Axe.class::isInstance);
    }

    /**
     * Method to get the lumberjacks dialog.
     *
     * @return the dialog
     */
    @Override
    protected List<Message> getDialogMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("Hi, I'm Jack, a lumberjack."));
        messages.add(new Message("Do you see the trees around you?"));
        messages.add(new Message("You can use this axe to chop them down."));
        return messages;
    }

    @Override
    protected boolean isRecurring() {
        return true;
    }

    /**
     * Method to get the dialog done action handler.
     *
     * @return the handler
     */
    @Override
    protected List<Handler<Void>> getDialogDoneActions() {
        return Collections.singletonList(() -> {
            Player.getSelf().addItem(new Axe());
            return null;
        });
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    protected boolean shouldStartConversation() {
        return isTouching(Player.class) &&
                !playerHasAxe() &&
                (!ConsoleUtil.isConsoleOpen((Level) getWorld())&&Greenfoot.isKeyDown(CONV_START));
    }

    /**
     * Sets {@link #walking} and {@link #direction} to a random value.
     */
    private void randomWalkingState() {
        walking = new Random().nextBoolean();
        direction = (new Random().nextBoolean()) ? Direction.random() : direction;
    }

    /**
     * Checks up to 3 blocks in north direction for trees in range.
     *
     * @return true if there are trees in range.
     */
    private boolean isTreeInRangeNorth() {
        return isTreeInRange(getObjectsAtOffset(0, -1, TileActor.class)) ||
                isTreeInRange(getObjectsAtOffset(0, -2, TileActor.class)) || //TODO: maybe add something like #isAccessible()
                isTreeInRange(getObjectsAtOffset(0, -3, TileActor.class));
    }

    /**
     * Checks up to 3 blocks in east direction for trees in range.
     *
     * @return true if there are trees in range.
     */
    private boolean isTreeInRangeEast() {
        return isTreeInRange(getObjectsAtOffset(1, 0, TileActor.class)) ||
                isTreeInRange(getObjectsAtOffset(2, 0, TileActor.class)) || //TODO: maybe add something like #isAccessible()
                isTreeInRange(getObjectsAtOffset(3, 0, TileActor.class));
    }

    /**
     * Checks up to 3 blocks in south direction for trees in range.
     *
     * @return true if there are trees in range.
     */
    private boolean isTreeInRangeSouth() {
        return isTreeInRange(getObjectsAtOffset(0, 1, TileActor.class)) ||
                isTreeInRange(getObjectsAtOffset(0, 2, TileActor.class)) || //TODO: maybe add something like #isAccessible()
                isTreeInRange(getObjectsAtOffset(0, 3, TileActor.class));
    }

    /**
     * Checks up to 3 blocks in west direction for trees in range.
     *
     * @return true if there are trees in range.
     */
    private boolean isTreeInRangeWest() {
        return isTreeInRange(getObjectsAtOffset(-1, 0, TileActor.class)) ||
                isTreeInRange(getObjectsAtOffset(-2, 0, TileActor.class)) || //TODO: maybe add something like #isAccessible()
                isTreeInRange(getObjectsAtOffset(-3, 0, TileActor.class));
    }

    /**
     * Checks a list of {@link TileActor} for containing a tree.
     *
     * @param tiles the {@link List} of tiles
     * @return true if there are trees
     */
    private boolean isTreeInRange(List<TileActor> tiles) {
        return tiles.stream()
                .filter(t -> t.getTile().equals(GrasslandTiles.Tree01) ||
                        t.getTile().equals(GrasslandTiles.Tree02) ||
                        t.getTile().equals(GrasslandTiles.Tree03) ||
                        t.getTile().equals(DesertTiles.Tree01) ||
                        t.getTile().equals(DesertTiles.Tree02))
                .collect(Collectors.toList()).size() >= 1;
    }

    /**
     * Animates walking of the lumberjack.
     */
    @Override
    protected void animateWalking() {
        if (seqIdx > 4) {
            seqIdx = 0;
        }
        if (walking) {
            this.setImage("images/lumberjack/lumberjack" + (seqIdx + 1) + FILE_SUFFIX);
        } else {
            this.setImage("images/lumberjack/lumberjack1.png");
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
        return 100;
    }

    /**
     * Throttled act method that is used for anything else than walking.
     */
    @Override
    protected void doAct() {
        //TODO chop some trees or something
    }
}
