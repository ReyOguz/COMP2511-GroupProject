package dungeonmania.goals.GoalTypes;

import dungeonmania.Game;
import dungeonmania.entities.enemies.ZombieToastSpawner;
import dungeonmania.goals.Goal;

public class EnemiesGoal implements Goal {
    private int target;

    public EnemiesGoal(int target) {
        this.target = target;
    }

    public boolean achieved(Game game) {
        return game.getEnemiesDestroyed() >= target && (game.countEntityOfType(ZombieToastSpawner.class) == 0);
    }

    public String toString(Game game) {
        if (achieved(game))
            return "";
        return ":enemies";
    }
}
