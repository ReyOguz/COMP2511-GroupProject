package dungeonmania.entities.Conductors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.Entity;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public abstract class Conductor extends Entity {
    private List<Conductor> conductors = new ArrayList<>();
    private boolean activated = false;
    private int tick;

    public Conductor(Position position) {
        super(position.asLayer(FLOOR_LAYER));
    }

    public boolean isLogical() {
        return false;
    }

    public void power(int tick) {
        if (activated) {
            return;
        }
        activated = true;
        this.tick = tick;
        for (Conductor conductor : conductors) {
            conductor.power(tick);
        }
    }

    public void powerOff() {
        if (!activated) {
            return;
        }
        activated = false;
        for (Conductor conductor : conductors) {
            conductor.powerOff();
        }
    }

    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    public void addConductor(Conductor c) {
        conductors.add(c);
    }

    public void activate() {
        activated = true;
    }

    public void deactivate() {
        activated = false;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public List<Conductor> getConductors() {
        return conductors;
    }

    public List<Conductor> getNonLogicals() {
        return conductors.stream().filter(c -> !c.isLogical()).collect(Collectors.toList());
    }

    public boolean isActivated() {
        return activated;
    }

    public int getActivationTick() {
        return tick;
    }

}
