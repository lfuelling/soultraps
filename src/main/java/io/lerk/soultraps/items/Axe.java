package io.lerk.soultraps.items;

import greenfoot.Greenfoot;
import io.lerk.soultraps.levels.playable.GenericGrasslandLevel;
import io.lerk.soultraps.mobs.player.Player;
import io.lerk.soultraps.sys.Soultraps;
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
            addNextToPlayer();
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
        handleRotation();
        if(System.currentTimeMillis() - lastAdded >= 200) {
            getWorld().removeObject(this);
        }
    }

    private void handleRotation() {
        switch (Player.getSelf().getDirection()) {
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
        setRotation(Player.getSelf().getDirection().getRotation());
    }

    private void addNextToPlayer() {
        switch (Player.getSelf().getDirection()) {
            case NORTH:
                Player.getSelf().getWorld().addObject(this,
                        Player.getSelf().getX(), Player.getSelf().getY() - 1);
                break;
            case SOUTH:
                Player.getSelf().getWorld().addObject(this,
                        Player.getSelf().getX(), Player.getSelf().getY() + 1);
                break;
            case WEST:
                Player.getSelf().getWorld().addObject(this,
                        Player.getSelf().getX() - 1, Player.getSelf().getY());
                break;
            case EAST:
                Player.getSelf().getWorld().addObject(this,
                        Player.getSelf().getX() + 1, Player.getSelf().getY());
                break;
            default:
                return;
        }
        lastAdded = System.currentTimeMillis();
    }
}
