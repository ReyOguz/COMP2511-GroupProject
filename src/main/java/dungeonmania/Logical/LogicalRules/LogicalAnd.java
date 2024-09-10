package dungeonmania.Logical.LogicalRules;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.Logical.LogicalRule;
import dungeonmania.entities.Conductors.Conductor;

public class LogicalAnd implements LogicalRule {
    public LogicalAnd() {
    }

    public boolean achieved(Conductor conductor) {
        List<Conductor> conductors = conductor.getConductors().stream().filter(c -> !c.isLogical())
                .collect(Collectors.toList());
        if (conductors.size() < 2) {
            return false;
        }
        for (Conductor conductor2 : conductors) {
            if (!conductor2.isActivated()) {
                return false;
            }
        }
        return true;
    }
}
