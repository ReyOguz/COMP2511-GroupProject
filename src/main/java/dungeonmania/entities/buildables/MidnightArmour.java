package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;

public class MidnightArmour extends Buildable {
    private static final int MIDNIGHT_ARMOUR_HEALTH_VALUE = 0;
    private static final int MIDNIGHT_ARMOUR_ATTACK_MAGNIFIER_VALUE = 1;
    private static final int MIDNIGHT_ARMOUR_DAMAGE_REDUCER_VALUE = 1;
    private static final int MIDNIGHT_ARMOUR_DURABILITY = 1;
    private int attack;
    private int defence;

    public MidnightArmour(int attack, int defence) {
        super(null);
        this.attack = attack;
        this.defence = defence;
    }

    @Override
    public void use(Game game) {
        return;
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(MIDNIGHT_ARMOUR_HEALTH_VALUE, attack, defence,
                MIDNIGHT_ARMOUR_ATTACK_MAGNIFIER_VALUE, MIDNIGHT_ARMOUR_DAMAGE_REDUCER_VALUE));
    }

    @Override
    public int getDurability() {
        return MIDNIGHT_ARMOUR_DURABILITY;
    }
}
