package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Interactable;
import dungeonmania.entities.Player;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.entities.collectables.potions.InvisibilityPotion;
import dungeonmania.entities.enemies.Movement.InvincibilityPotionMovement;
import dungeonmania.entities.enemies.Movement.InvisibilityPotionMovement;
import dungeonmania.entities.enemies.Movement.MovementStrategy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Mercenary extends Enemy implements Interactable {
    public static final int DEFAULT_BRIBE_AMOUNT = 1;
    public static final int DEFAULT_BRIBE_RADIUS = 1;
    public static final double DEFAULT_ATTACK = 5.0;
    public static final double DEFAULT_HEALTH = 10.0;

    private static final int MERCENARY_HEALTH_VALUE = 0;
    private static final int MERCENARY_ATTACK_MAGNIFIER_VALUE = 1;
    private static final int MERCENARY_DAMAGE_REDUCER_VALUE = 1;

    private int bribeAmount = Mercenary.DEFAULT_BRIBE_AMOUNT;
    private int bribeRadius = Mercenary.DEFAULT_BRIBE_RADIUS;

    private double allyAttack;
    private double allyDefence;
    private boolean isAdjacentToPlayer = false;

    private MovementStrategy enemyMovement;

    public Position getNextPosition(Game game, Entity thisEntity) {
        return enemyMovement.getNextPosition(game, thisEntity);
    }

    public void setMovementStrategy(MovementStrategy strategy) {
        this.enemyMovement = strategy;
    }

    public Mercenary(Position position, double health, double attack, int bribeAmount, int bribeRadius,
            double allyAttack, double allyDefence) {
        super(position, health, attack);
        this.bribeAmount = bribeAmount;
        this.bribeRadius = bribeRadius;
        this.allyAttack = allyAttack;
        this.allyDefence = allyDefence;
    }

    @Override
    public boolean canBeStuck() {
        if (isAllied()) {
            return !isAdjacentToPlayer;
        }
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (isAllied())
            return;
        super.onOverlap(map, entity);
    }

    /**
     * check whether the current merc can be bribed
     * @param player
     * @return
     */
    private boolean canBeBribed(Player player) {
        return bribeRadius >= 0 && player.countEntityOfType(Treasure.class) >= bribeAmount;
    }

    private boolean canBeCrontolled(Player player) {
        return player.hasSceptre();
    }

    /**
     * bribe the merc
     */
    private void bribe(Player player) {
        for (int i = 0; i < bribeAmount; i++) {
            player.use(Treasure.class);
        }

    }

    @Override
    public void interact(Player player, Game game) {
        if (canBeBribed(player)) {
            ally();
            bribe(player);
            isAdjacentToPlayer = Position.isAdjacent(player.getPosition(), getPosition());
        } else if (canBeCrontolled(player)) {
            ally(player.getSceptreDuration());
            isAdjacentToPlayer = Position.isAdjacent(player.getPosition(), getPosition());
        }

    }

    @Override
    public void move(Game game) {
        Position nextPos;
        GameMap map = game.getMap();
        Player player = game.getPlayer();
        if (isControlled()) {
            decrementControlledTimer();
        }
        if (isStuck()) {
            decrementStuckTimer();
            return;
        }
        if (isAllied()) {
            nextPos = isAdjacentToPlayer ? player.getPreviousDistinctPosition()
                    : map.dijkstraPathFind(getPosition(), player.getPosition(), this);
            isAdjacentToPlayer = Position.isAdjacent(player.getPosition(), nextPos);
        } else if (map.getPlayer().getEffectivePotion() instanceof InvisibilityPotion) {
            // Move random
            setMovementStrategy(new InvisibilityPotionMovement());
            nextPos = getNextPosition(game, this);
        } else if (map.getPlayer().getEffectivePotion() instanceof InvincibilityPotion) {
            setMovementStrategy(new InvincibilityPotionMovement());
            nextPos = getNextPosition(game, this);
        } else {
            // Follow hostile
            nextPos = map.dijkstraPathFind(getPosition(), player.getPosition(), this);
        }
        map.moveTo(this, nextPos);
    }

    @Override
    public boolean isInteractable(Player player) {
        return !isAllied() && (canBeBribed(player) || canBeCrontolled(player));
    }

    @Override
    public BattleStatistics getBattleStatistics() {
        if (!isAllied())
            return super.getBattleStatistics();
        return new BattleStatistics(MERCENARY_HEALTH_VALUE, allyAttack, allyDefence, MERCENARY_ATTACK_MAGNIFIER_VALUE,
                MERCENARY_DAMAGE_REDUCER_VALUE);
    }
}
