package dungeonmania.entities.collectables;

import dungeonmania.util.Position;

public class Treasure extends Collectable {
    public Treasure(Position position) {
        super(position);
    }

    @Override
    public boolean isTreasure() {
        return true;
    }
}
