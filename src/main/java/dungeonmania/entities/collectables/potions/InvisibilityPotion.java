package dungeonmania.entities.collectables.potions;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.util.Position;

public class InvisibilityPotion extends Potion {
    private static final int INVISIBLE_POSTION_HEALTH_VALUE = 0;
    private static final int INVISIBLE_POSTION_ATTACK_VALUE = 0;
    private static final int INVISIBLE_POSTION_DEFENCE_VALUE = 0;
    private static final int INVISIBLE_POSTION_ATTACK_MAGNIFIER_VALUE = 1;
    private static final int INVISIBLE_POSTION_DAMAGE_REDUCER_VALUE = 1;
    private static final boolean INVISIBLE_POSTION_IS_INVINCIBLE_VALUE = false;
    private static final boolean INVISIBLE_POSTION_IS_ENABLED_VALUE = false;

    public static final int DEFAULT_DURATION = 8;

    public InvisibilityPotion(Position position, int duration) {
        super(position, duration);
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin,
                new BattleStatistics(INVISIBLE_POSTION_HEALTH_VALUE, INVISIBLE_POSTION_ATTACK_VALUE,
                        INVISIBLE_POSTION_DEFENCE_VALUE, INVISIBLE_POSTION_ATTACK_MAGNIFIER_VALUE,
                        INVISIBLE_POSTION_DAMAGE_REDUCER_VALUE, INVISIBLE_POSTION_IS_INVINCIBLE_VALUE,
                        INVISIBLE_POSTION_IS_ENABLED_VALUE));
    }

}
