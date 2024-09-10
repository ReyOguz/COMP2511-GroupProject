package dungeonmania.Logical;

import dungeonmania.entities.Conductors.Conductor;

public interface LogicalRule {
    public boolean achieved(Conductor conductor);
}
