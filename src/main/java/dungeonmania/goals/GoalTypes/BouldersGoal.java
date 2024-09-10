package dungeonmania.goals.GoalTypes;

import dungeonmania.Game;
import dungeonmania.entities.Switch;
import dungeonmania.goals.Goal;

public class BouldersGoal implements Goal {
    public BouldersGoal() {

    }

    public boolean achieved(Game game) {
        return game.getMap().getEntities(Switch.class).stream().allMatch(s -> s.isActivated());
    }

    public String toString(Game game) {
        if (achieved(game))
            return "";
        return ":boulders";
    }
}
