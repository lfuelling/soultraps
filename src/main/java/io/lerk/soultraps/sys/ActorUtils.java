package io.lerk.soultraps.sys;

import greenfoot.Actor;
import io.lerk.soultraps.mobs.Direction;
import io.lerk.soultraps.mobs.player.Player;

public final class ActorUtils {
    public static void handleRotation(Actor actor, boolean mirror) {
        Direction direction = Player.getSelf().getDirection();
        switch (direction) {
            case NORTH:
                actor.setLocation(Player.getSelf().getX(), Player.getSelf().getY() - 1);
                break;
            case EAST:
                actor.setLocation(Player.getSelf().getX() + 1, Player.getSelf().getY());
                break;
            case SOUTH:
                actor.setLocation(Player.getSelf().getX(), Player.getSelf().getY() + 1);
                break;
            case WEST:
            default:
                actor.setLocation(Player.getSelf().getX() - 1, Player.getSelf().getY());
                break;
        }
        if (mirror) {
            actor.setRotation(direction.getRotation());
            if (direction.isMirroredV()) {
                actor.getImage().mirrorVertically();
            }
            if (direction.isMirroredH()) {
                actor.getImage().mirrorHorizontally();
            }
        }
    }
}
