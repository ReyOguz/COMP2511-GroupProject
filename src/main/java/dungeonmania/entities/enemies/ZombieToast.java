package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.entities.Entity;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.entities.enemies.Movement.InvincibilityPotionMovement;
import dungeonmania.entities.enemies.Movement.InvisibilityPotionMovement;
import dungeonmania.entities.enemies.Movement.MovementStrategy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class ZombieToast extends Enemy {
    public static final double DEFAULT_HEALTH = 5.0;
    public static final double DEFAULT_ATTACK = 6.0;
    // bring into method
    private MovementStrategy enemyMovement;

    public Position getNextPosition(Game game, Entity thisEntity) {
        return enemyMovement.getNextPosition(game, thisEntity);
    }

    public void setMovementStrategy(MovementStrategy strategy) {
        this.enemyMovement = strategy;
    }

    public ZombieToast(Position position, double health, double attack) {
        super(position, health, attack);
    }

    @Override
    public void move(Game game) {
        Position nextPos;
        GameMap map = game.getMap();
        if (isStuck()) {
            decrementStuckTimer();
            return;
        }
        if (map.getPlayer().getEffectivePotion() instanceof InvincibilityPotion) {
            setMovementStrategy(new InvincibilityPotionMovement());
        } else {
            setMovementStrategy(new InvisibilityPotionMovement());
        }
        nextPos = getNextPosition(game, this);
        game.getMap().moveTo(this, nextPos);

    }

}
