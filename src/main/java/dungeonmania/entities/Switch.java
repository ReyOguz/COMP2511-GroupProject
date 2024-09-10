package dungeonmania.entities;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.Conductors.Conductor;
import dungeonmania.entities.collectables.Bomb;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Switch extends Conductor {
    private int tick;
    private List<Bomb> bombs = new ArrayList<>();

    public Switch(Position position) {
        super(position.asLayer(Entity.ITEM_LAYER));
    }

    public int getActivationTick() {
        return tick;
    }

    public void power(int tick) {
        return;
    }

    public void powerOn(int tick) {
        if (isActivated()) {
            return;
        }
        activate();
        List<Conductor> conductors = getConductors();
        this.tick = tick;
        for (Conductor conductor : conductors) {
            conductor.power(tick);
        }
    }

    public void powerOff() {
        if (!isActivated()) {
            return;
        }
        deactivate();
        List<Conductor> conductors = getConductors();
        for (Conductor conductor : conductors) {
            conductor.powerOff();
        }
    }

    public void subscribe(Bomb b) {
        bombs.add(b);
    }

    public void subscribe(Bomb bomb, GameMap map) {
        bombs.add(bomb);
        if (isActivated()) {
            bombs.stream().forEach(b -> b.notify(map));
        }
    }

    public void unsubscribe(Bomb b) {
        bombs.remove(b);
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            powerOn(map.getTick());
            bombs.stream().forEach(b -> b.notify(map));
        }
    }

    @Override
    public void onMovedAway(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            powerOff();
        }
    }
}
