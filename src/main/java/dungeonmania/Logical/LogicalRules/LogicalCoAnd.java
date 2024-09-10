package dungeonmania.Logical.LogicalRules;

import java.util.List;

import dungeonmania.Logical.LogicalRule;
import dungeonmania.entities.Conductors.Conductor;

public class LogicalCoAnd implements LogicalRule {
    public LogicalCoAnd() {
    }

    public boolean achieved(Conductor conductor) {
        List<Conductor> conductors = conductor.getNonLogicals();
        if (conductors.size() < 2) {
            return false;
        }
        int tick = conductors.get(0).getActivationTick();
        for (Conductor conductor2 : conductors) {
            if (!conductor2.isActivated() || tick != conductor2.getActivationTick()) {
                return false;
            }
        }

        return true;
    }
}
