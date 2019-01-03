package io.lerk.soultraps.mobs;

import io.lerk.soultraps.items.Axe;
import io.lerk.soultraps.sys.Tiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
        if (getIntersectingObjects(Player.class).size() > 0 && !playerHasAxe()) {
            walking = false;
            //TODO: add different dialog if player has an axe.
            Player.getSelf().startDialog(getDialog(), () -> {
                Player.getSelf().addItem(new Axe());
                return null;
            }, Lumberjack.this);
            return;
        }
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
     * Method to get the lumberjacks dialog messages.
     *
     * @return the dialog contents
     */
    @Override
    protected ArrayList<String> getDialog() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Hi, I'm Jack, a lumberjack.");
        strings.add("Do you see the trees around you?");
        strings.add("You can use this axe to chop them down.");
        return strings;
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
        return isTreeInRange(getObjectsAtOffset(0, -1, Tiles.Tile.class)) ||
                isTreeInRange(getObjectsAtOffset(0, -2, Tiles.Tile.class)) || //TODO: maybe add something like #isAccessible()
                isTreeInRange(getObjectsAtOffset(0, -3, Tiles.Tile.class));
    }

    /**
     * Checks up to 3 blocks in east direction for trees in range.
     *
     * @return true if there are trees in range.
     */
    private boolean isTreeInRangeEast() {
        return isTreeInRange(getObjectsAtOffset(1, 0, Tiles.Tile.class)) ||
                isTreeInRange(getObjectsAtOffset(2, 0, Tiles.Tile.class)) || //TODO: maybe add something like #isAccessible()
                isTreeInRange(getObjectsAtOffset(3, 0, Tiles.Tile.class));
    }

    /**
     * Checks up to 3 blocks in south direction for trees in range.
     *
     * @return true if there are trees in range.
     */
    private boolean isTreeInRangeSouth() {
        return isTreeInRange(getObjectsAtOffset(0, 1, Tiles.Tile.class)) ||
                isTreeInRange(getObjectsAtOffset(0, 2, Tiles.Tile.class)) || //TODO: maybe add something like #isAccessible()
                isTreeInRange(getObjectsAtOffset(0, 3, Tiles.Tile.class));
    }

    /**
     * Checks up to 3 blocks in west direction for trees in range.
     *
     * @return true if there are trees in range.
     */
    private boolean isTreeInRangeWest() {
        return isTreeInRange(getObjectsAtOffset(-1, 0, Tiles.Tile.class)) ||
                isTreeInRange(getObjectsAtOffset(-2, 0, Tiles.Tile.class)) || //TODO: maybe add something like #isAccessible()
                isTreeInRange(getObjectsAtOffset(-3, 0, Tiles.Tile.class));
    }

    /**
     * Checks a list of {@link io.lerk.soultraps.sys.Tiles.Tile} for containing a tree.
     *
     * @param tiles the {@link List} of tiles
     * @return true if there are trees
     */
    private boolean isTreeInRange(List<Tiles.Tile> tiles) {
        return tiles.stream().filter(t -> t.getTileType().equals(Tiles.Tree01) || t.getTileType().equals(Tiles.Tree02) || t.getTileType().equals(Tiles.Tree03)).collect(Collectors.toList()).size() >= 1;
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
            this.setImage("images/lumberjack/lumberjack" + (seqIdx + 1) + ".png");
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
    protected int maxHealth() {
        return 100;
    }

    /**
     * Throttled act method that is used for anything else than walking.
     */
    @Override
    protected void doAct() {
    }
}
