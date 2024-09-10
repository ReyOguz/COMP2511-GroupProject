package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;

public class Bow extends Buildable {
    private static final int BOW_HEALTH_VALUE = 0;
    private static final int BOW_ATTACK_VALUE = 0;
    private static final int BOW_DEFENCE_VALUE = 0;
    private static final int BOW_ATTACK_MAGNIFIER_VALUE = 2;
    private static final int BOW_DAMAGE_REDUCER_VALUE = 1;
    private static final int BOW_NO_DURABILITY_LEFT = 0;

    private int durability;

    public Bow(int durability) {
        super(null);
        this.durability = durability;
    }

    @Override
    public void use(Game game) {
        durability--;
        if (durability <= BOW_NO_DURABILITY_LEFT) {
            game.getPlayer().remove(this);
        }
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(BOW_HEALTH_VALUE, BOW_ATTACK_VALUE,
                BOW_DEFENCE_VALUE, BOW_ATTACK_MAGNIFIER_VALUE, BOW_DAMAGE_REDUCER_VALUE));
    }

    @Override
    public int getDurability() {
        return durability;
    }
}
