package dungeonmania.entities.Conductors;

import dungeonmania.util.Position;

public class Wire extends Conductor {
    public Wire(Position position) {
        super(position.asLayer(FLOOR_LAYER));
    }
}
