package dungeonmania.entities.enemies.Movement;

import dungeonmania.Game;
import dungeonmania.entities.Entity;
import dungeonmania.util.Position;

public interface MovementStrategy {
    public Position getNextPosition(Game game, Entity thisEntity);

}
