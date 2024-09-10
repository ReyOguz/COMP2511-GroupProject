package dungeonmania.Logical.LogicalRules;

import java.util.List;

import dungeonmania.Logical.LogicalRule;
import dungeonmania.entities.Conductors.Conductor;

public class LogicalXOr implements LogicalRule {
    public LogicalXOr() {
    }

    public boolean achieved(Conductor conductor) {
        List<Conductor> conductors = conductor.getNonLogicals();
        boolean activated = false;
        for (Conductor conductor2 : conductors) {
            if (activated) {
                return false;
            }
            if (conductor2.isActivated()) {
                activated = true;
            }
        }
        return activated;
    }
}
