package dungeonmania.entities.enemies.Movement;

import dungeonmania.Game;
import dungeonmania.entities.Entity;
import dungeonmania.map.GameMap;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class InvincibilityPotionMovement implements MovementStrategy {
    public Position getNextPosition(Game game, Entity thisEntity) {
        GameMap map = game.getMap();
        Position plrDiff = Position.calculatePositionBetween(map.getPlayer().getPosition(), thisEntity.getPosition());

        Position moveX = (plrDiff.getX() >= 0) ? Position.translateBy(thisEntity.getPosition(), Direction.RIGHT)
                : Position.translateBy(thisEntity.getPosition(), Direction.LEFT);
        Position moveY = (plrDiff.getY() >= 0) ? Position.translateBy(thisEntity.getPosition(), Direction.UP)
                : Position.translateBy(thisEntity.getPosition(), Direction.DOWN);
        Position offset = thisEntity.getPosition();
        if (plrDiff.getY() == 0 && map.canMoveTo(thisEntity, moveX))
            offset = moveX;
        else if (plrDiff.getX() == 0 && map.canMoveTo(thisEntity, moveY))
            offset = moveY;
        else if (Math.abs(plrDiff.getX()) >= Math.abs(plrDiff.getY())) {
            if (map.canMoveTo(thisEntity, moveX))
                offset = moveX;
            else if (map.canMoveTo(thisEntity, moveY))
                offset = moveY;
            else
                offset = thisEntity.getPosition();
        } else {
            if (map.canMoveTo(thisEntity, moveY))
                offset = moveY;
            else if (map.canMoveTo(thisEntity, moveX))
                offset = moveX;
            else
                offset = thisEntity.getPosition();
        }
        return offset;
    }
}
