package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;

public class Sceptre extends Buildable {
    private static final int SCEPTRE_DEFAULT_DURABILITY_VALUE = 1;
    private static final int SCEPTRE_HEALTH_VALUE = 0;
    private static final int SCEPTRE_ATTACK_VALUE = 0;
    private static final int SCEPTRE_DEFENCE_VALUE = 0;
    private static final int SCEPTRE_ATTACK_MAGNIFIER_VALUE = 1;
    private static final int SCEPTRE_DAMAGE_REDUCER_VALUE = 0;

    private int mindControlDuration;

    public Sceptre(int mindControlDuration) {
        super(null);
        this.mindControlDuration = mindControlDuration;
    }

    @Override
    public void use(Game game) {
        return;
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(SCEPTRE_HEALTH_VALUE, SCEPTRE_ATTACK_VALUE,
                SCEPTRE_DEFENCE_VALUE, SCEPTRE_ATTACK_MAGNIFIER_VALUE, SCEPTRE_DAMAGE_REDUCER_VALUE));
    }

    @Override
    public int getDurability() {
        return SCEPTRE_DEFAULT_DURABILITY_VALUE;
    }

    public int getMindControlDuration() {
        return mindControlDuration;
    }

}
