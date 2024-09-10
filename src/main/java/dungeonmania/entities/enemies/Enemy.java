package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.battles.Battleable;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Player;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public abstract class Enemy extends Entity implements Battleable {
    private BattleStatistics battleStatistics;
    private boolean allied = false;
    private boolean controlled = false;
    private int mindControlledDurationRemaining = 0;
    private int ticksStuckRemaining = 0;

    public Enemy(Position position, double health, double attack) {
        super(position.asLayer(Entity.CHARACTER_LAYER));
        battleStatistics = new BattleStatistics(health, attack, 0, BattleStatistics.DEFAULT_DAMAGE_MAGNIFIER,
                BattleStatistics.DEFAULT_ENEMY_DAMAGE_REDUCER);
    }

    public boolean isControlled() {
        return controlled;
    }

    public void decrementControlledTimer() {
        this.mindControlledDurationRemaining--;
        if (mindControlledDurationRemaining == 0) {
            allied = false;
            controlled = false;
        }
    }

    public int getMindControlledDurationRemaining() {
        return mindControlledDurationRemaining;
    }

    public void ally(int duration) {
        ally();
        mindControlledDurationRemaining = duration;
        controlled = true;
    }

    public void ally() {
        this.allied = true;
    }

    public boolean isAllied() {
        return allied;
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return entity instanceof Player;
    }

    @Override
    public BattleStatistics getBattleStatistics() {
        return battleStatistics;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            map.getGame().battle(player, this);
        }
    }

    @Override
    public void onDestroy(GameMap map) {
        Game g = map.getGame();
        map.getPlayer().incrementEnemiesDestroyed();
        g.unsubscribe(getId());
    }

    public abstract void move(Game game);

    public boolean canBeStuck() {
        return !allied;
    }

    public void stickFor(int movementFactor) {
        this.ticksStuckRemaining = movementFactor;
    }

    public boolean isStuck() {
        return ticksStuckRemaining >= 1;
    }

    public void decrementStuckTimer() {
        ticksStuckRemaining--;
    }
}
