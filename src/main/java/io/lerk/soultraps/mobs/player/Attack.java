package io.lerk.soultraps.mobs.player;

import io.lerk.soultraps.mobs.BaseMob;
import io.lerk.soultraps.mobs.Direction;
import io.lerk.soultraps.mobs.Enemies.Enemy;

import java.util.List;

public abstract class Attack extends BaseMob {

    protected boolean visible;

    public abstract int getDamage(Enemy enemy);

    @Override
    protected void doAct() {
        List<Enemy> enemiesInRange = getIntersectingObjects(Enemy.class);
        if (enemiesInRange.size() > 0) {
            enemiesInRange.forEach(enemy -> enemy.dealDamage(getDamage(enemy)));
        }
    }

    public void toggleAttack(boolean visible, Direction direction) {
        this.direction = direction;
        if (this.visible != visible) {
            this.visible = visible;
            if (visible) {
                setRotation(direction.getRotation());
                switch (direction) {
                    case NORTH:
                        Player.getSelf().getWorld().addObject(this,
                                Player.getSelf().getX(),
                                Player.getSelf().getY() - 1);
                        break;
                    case EAST:
                        Player.getSelf().getWorld().addObject(this,
                                Player.getSelf().getX() + 1,
                                Player.getSelf().getY());
                        break;
                    case SOUTH:
                        Player.getSelf().getWorld().addObject(this,
                                Player.getSelf().getX(),
                                Player.getSelf().getY() + 1);
                        break;
                    case WEST:
                    default:
                        Player.getSelf().getWorld().addObject(this,
                                Player.getSelf().getX() - 1,
                                Player.getSelf().getY());
                        break;
                }
            } else {
                Player.getSelf().getWorld().removeObject(this);
            }
        }
    }
}
