package dungeonmania.entities.collectables.potions;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.util.Position;

public class InvincibilityPotion extends Potion {
    private static final int INVINCIBLE_POSTION_HEALTH_VALUE = 0;
    private static final int INVINCIBLE_POSTION_ATTACK_VALUE = 0;
    private static final int INVINCIBLE_POSTION_DEFENCE_VALUE = 0;
    private static final int INVINCIBLE_POSTION_ATTACK_MAGNIFIER_VALUE = 1;
    private static final int INVINCIBLE_POSTION_DAMAGE_REDUCER_VALUE = 1;
    private static final boolean INVINCIBLE_POSTION_IS_INVINCIBLE_VALUE = true;
    private static final boolean INVINCIBLE_POSTION_IS_ENABLED_VALUE = true;

    public static final int DEFAULT_DURATION = 8;

    public InvincibilityPotion(Position position, int duration) {
        super(position, duration);
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin,
                new BattleStatistics(INVINCIBLE_POSTION_HEALTH_VALUE, INVINCIBLE_POSTION_ATTACK_VALUE,
                        INVINCIBLE_POSTION_DEFENCE_VALUE, INVINCIBLE_POSTION_ATTACK_MAGNIFIER_VALUE,
                        INVINCIBLE_POSTION_DAMAGE_REDUCER_VALUE, INVINCIBLE_POSTION_IS_INVINCIBLE_VALUE,
                        INVINCIBLE_POSTION_IS_ENABLED_VALUE));
    }
}
