package dungeonmania.entities.Conductors;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.Logical.LogicalRule;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Player;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class ConductorBomb extends Conductor {
    private LogicalRule logicalRule;
    public static final int DEFAULT_RADIUS = 1;
    private int radius;

    public ConductorBomb(Position position, int radius, LogicalRule logicalRule) {
        super(position.asLayer(FLOOR_LAYER));
        this.logicalRule = logicalRule;
        this.radius = radius;
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
            // explode(map);
        }
    }

    public void powerOff() {
        if (!logicalRule.achieved(this)) {
            deactivate();
        }
    }

    public void explode(GameMap map) {
        int x = getPosition().getX();
        int y = getPosition().getY();
        for (int i = x - radius; i <= x + radius; i++) {
            for (int j = y - radius; j <= y + radius; j++) {
                List<Entity> entities = map.getEntities(new Position(i, j));
                entities = entities.stream().filter(e -> !(e instanceof Player)).collect(Collectors.toList());
                for (Entity e : entities) {
                    map.destroyEntity(e);
                }
            }
        }
    }
}
