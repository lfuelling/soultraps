package io.lerk.soultraps.items;

import io.lerk.soultraps.mobs.Direction;
import io.lerk.soultraps.mobs.player.Player;
import io.lerk.soultraps.tiles.Tile;
import io.lerk.soultraps.tiles.TileActor;

import java.util.Collections;
import java.util.List;

/**
 * Axe.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Axe extends Item {

    private long lastAdded = 0;

    /**
     * Constructor.
     */
    public Axe() {
        super("Axe",
                "Chucks wood.",
                100, 5);
        effects = Collections.singletonList(() -> {
            if (addNextToPlayer(this)) {
                lastAdded = System.currentTimeMillis();
            }
            List<TileActor> tilesInRange = Axe.this.getIntersectingObjects(TileActor.class);
            if (tilesInRange.size() > 0) {
                tilesInRange.forEach(t -> {
                    if (t.getTile().getType().equals(Tile.Type.TREE)) {
                        getWorld().removeObject(t);
                    }
                });
            }
        });
        setImage("images/axe.png");
    }

    @Override
    public void act() {
        Direction direction = Player.getSelf().getDirection();
        switch (direction) {
            case NORTH:
                setLocation(Player.getSelf().getX(), Player.getSelf().getY() - 1);
                break;
            case EAST:
                setLocation(Player.getSelf().getX() + 1, Player.getSelf().getY());
                break;
            case SOUTH:
                setLocation(Player.getSelf().getX(), Player.getSelf().getY() + 1);
                break;
            case WEST:
            default:
                setLocation(Player.getSelf().getX() - 1, Player.getSelf().getY());
                break;
        }
        setRotation(direction.getRotation());
        if (System.currentTimeMillis() - lastAdded >= 200) {
            getWorld().removeObject(this);
        }
    }

}
