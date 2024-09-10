package dungeonmania.entities.Conductors;

import dungeonmania.Logical.LogicalRule;
import dungeonmania.util.Position;

public class LightBulb extends Conductor {
    private LogicalRule logicalRule;

    public LightBulb(Position position, LogicalRule logicalRule) {
        super(position.asLayer(FLOOR_LAYER));
        this.logicalRule = logicalRule;
    }

    @Override
    public boolean isLogical() {
        return true;
    }

    @Override
    public void power(int tick) {
        if (logicalRule.achieved(this)) {
            activate();
            setTick(tick);
        }
    }

    public void powerOff() {
        if (!logicalRule.achieved(this)) {
            deactivate();
        }
    }
}
