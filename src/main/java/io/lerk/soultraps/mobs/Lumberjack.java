package io.lerk.soultraps.mobs;

import io.lerk.soultraps.items.Axe;
import io.lerk.soultraps.sys.Tiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Lumberjack extends BaseMob {

    private int seqIdx = 0;

    @Override
    protected void updateWalkingState() {
        if(getIntersectingObjects(Player.class).size() > 0) {
            walking = false;
            Player.getSelf().startDialog(getDialog(), () ->  {
                Player.getSelf().addItem(new Axe());
                return null;
            });
            return;
        }
        if(new Random().nextBoolean()) {
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

    private ArrayList<String> getDialog() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Hi, I'm Jack, a lumberjack.");
        strings.add("Do you see the trees around you?");
        strings.add("You can use this axe to chop them down.");
        return strings;
    }

    private void randomWalkingState() {
        walking = new Random().nextBoolean();
        direction = (new Random().nextBoolean()) ? Direction.random() : direction;
    }

    private boolean isTreeInRangeNorth() {
        return isTreeInRange(getObjectsAtOffset(0, -1, Tiles.Tile.class)) ||
                isTreeInRange(getObjectsAtOffset(0, -2, Tiles.Tile.class)) || //TODO: maybe add something like #isAccessible()
                isTreeInRange(getObjectsAtOffset(0, -3, Tiles.Tile.class));
    }

    private boolean isTreeInRangeEast() {
        return isTreeInRange(getObjectsAtOffset(1, 0, Tiles.Tile.class)) ||
                isTreeInRange(getObjectsAtOffset(2, 0, Tiles.Tile.class)) || //TODO: maybe add something like #isAccessible()
                isTreeInRange(getObjectsAtOffset(3, 0, Tiles.Tile.class));
    }

    private boolean isTreeInRangeSouth() {
        return isTreeInRange(getObjectsAtOffset(0, 1, Tiles.Tile.class)) ||
                isTreeInRange(getObjectsAtOffset(0, 2, Tiles.Tile.class)) || //TODO: maybe add something like #isAccessible()
                isTreeInRange(getObjectsAtOffset(0, 3, Tiles.Tile.class));
    }

    private boolean isTreeInRangeWest() {
        return isTreeInRange(getObjectsAtOffset(-1, 0, Tiles.Tile.class)) ||
                isTreeInRange(getObjectsAtOffset(-2, 0, Tiles.Tile.class)) || //TODO: maybe add something like #isAccessible()
                isTreeInRange(getObjectsAtOffset(-3, 0, Tiles.Tile.class));
    }

    private boolean isTreeInRange(List<Tiles.Tile> tiles) {
        return tiles.stream().filter(t -> t.getTileType().equals(Tiles.Tree01) || t.getTileType().equals(Tiles.Tree02) || t.getTileType().equals(Tiles.Tree03)).collect(Collectors.toList()).size() >= 1;
    }

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

    @Override
    protected int maxHealth() {
        return 100;
    }

    @Override
    protected void doAct() {

    }
}
