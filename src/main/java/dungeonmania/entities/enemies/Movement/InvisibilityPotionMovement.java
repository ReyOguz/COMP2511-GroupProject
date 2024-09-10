package dungeonmania.entities.enemies.Movement;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import dungeonmania.Game;
import dungeonmania.entities.Entity;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class InvisibilityPotionMovement implements MovementStrategy {
    public Position getNextPosition(Game game, Entity thisEntity) {
        GameMap map = game.getMap();
        Random randGen = new Random();
        List<Position> pos = thisEntity.getPosition().getCardinallyAdjacentPositions();
        pos = pos.stream().filter(p -> map.canMoveTo(thisEntity, p)).collect(Collectors.toList());
        if (pos.size() == 0) {
            return thisEntity.getPosition();
        } else {
            return pos.get(randGen.nextInt(pos.size()));
        }
    }
}
