package dungeonmania.Logical.LogicalRules;

import java.util.List;

import dungeonmania.Logical.LogicalRule;
import dungeonmania.entities.Conductors.Conductor;

public class LogicalOr implements LogicalRule {
    public LogicalOr() {
    }

    public boolean achieved(Conductor conductor) {
        List<Conductor> conductors = conductor.getNonLogicals();
        for (Conductor conductor2 : conductors) {
            if (conductor2.isActivated()) {
                return true;
            }
        }
        return false;
    }
}
