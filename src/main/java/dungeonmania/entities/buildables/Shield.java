package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;

public class Shield extends Buildable {
    private static final int SHIELD_HEALTH_VALUE = 0;
    private static final int SHIELD_ATTACK_VALUE = 0;
    private static final int SHIELD_ATTACK_MAGNIFIER_VALUE = 1;
    private static final int SHIELD_DAMAGE_REDUCER_VALUE = 1;
    private static final int SHIELD_NO_DURABILITY_LEFT = 0;

    private int durability;
    private double defence;

    public Shield(int durability, double defence) {
        super(null);
        this.durability = durability;
        this.defence = defence;
    }

    @Override
    public void use(Game game) {
        durability--;
        if (durability <= SHIELD_NO_DURABILITY_LEFT) {
            game.getPlayer().remove(this);
        }
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(SHIELD_HEALTH_VALUE, SHIELD_ATTACK_VALUE,
                defence, SHIELD_ATTACK_MAGNIFIER_VALUE, SHIELD_DAMAGE_REDUCER_VALUE));
    }

    @Override
    public int getDurability() {
        return durability;
    }

}
