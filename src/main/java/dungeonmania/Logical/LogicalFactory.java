package dungeonmania.Logical;

import dungeonmania.Logical.LogicalRules.LogicalAnd;
import dungeonmania.Logical.LogicalRules.LogicalCoAnd;
import dungeonmania.Logical.LogicalRules.LogicalOr;
import dungeonmania.Logical.LogicalRules.LogicalXOr;

public class LogicalFactory {
    public static LogicalRule createLogicalRule(String string) {
        switch (string) {
        case "and":
            return new LogicalAnd();
        case "or":
            return new LogicalOr();
        case "xor":
            return new LogicalXOr();
        case "co_and":
            return new LogicalCoAnd();
        default:
            return null;
        }
    }
}
