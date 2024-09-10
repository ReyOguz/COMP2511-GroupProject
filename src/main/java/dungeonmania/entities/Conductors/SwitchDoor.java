package dungeonmania.entities.Conductors;

import dungeonmania.Logical.LogicalRule;
import dungeonmania.entities.Entity;
import dungeonmania.entities.enemies.Spider;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class SwitchDoor extends Conductor {
    private boolean open = false;
    private LogicalRule logicalRule;

    public SwitchDoor(Position position, LogicalRule logicalRule) {
        super(position.asLayer(DOOR_LAYER));
        this.logicalRule = logicalRule;
    }

    @Override
    public boolean isLogical() {
        return true;
    }

    @Override
    public void power(int tick) {
        if (logicalRule.achieved(this)) {
            open = true;
            activate();
            setTick(tick);
        }
    }

    @Override
    public void powerOff() {
        if (!logicalRule.achieved(this)) {
            open = false;
            deactivate();
        }
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        if (open || entity instanceof Spider) {
            return true;
        }
        return false;
    }

    public boolean isOpen() {
        return open;
    }
}
