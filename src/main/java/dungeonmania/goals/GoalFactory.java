package dungeonmania.goals;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.goals.GoalTypes.AndGoal;
import dungeonmania.goals.GoalTypes.BouldersGoal;
import dungeonmania.goals.GoalTypes.EnemiesGoal;
import dungeonmania.goals.GoalTypes.ExitGoal;
import dungeonmania.goals.GoalTypes.OrGoal;
import dungeonmania.goals.GoalTypes.TreasureGoal;

public class GoalFactory {
    public static Goal createGoal(JSONObject jsonGoal, JSONObject config) {
        JSONArray subgoals;
        switch (jsonGoal.getString("goal")) {
        case "AND":
            subgoals = jsonGoal.getJSONArray("subgoals");
            return createAndGoal(createGoal(subgoals.getJSONObject(0), config),
                    createGoal(subgoals.getJSONObject(1), config));
        case "OR":
            subgoals = jsonGoal.getJSONArray("subgoals");
            return createOrGoal(createGoal(subgoals.getJSONObject(0), config),
                    createGoal(subgoals.getJSONObject(1), config));
        case "exit":
            return createExitGoal();
        case "boulders":
            return createBouldersGoal();
        case "treasure":
            int treasureGoal = config.optInt("treasure_goal", 1);
            return createTreasureGoal(treasureGoal);
        case "enemies":
            int enemyGoal = config.optInt("enemy_goal", 0);
            return createEnemiesGoal(enemyGoal);
        default:
            return null;
        }
    }

    public static Goal createAndGoal(Goal goal1, Goal goal2) {
        return new AndGoal(goal1, goal2);
    }

    public static Goal createOrGoal(Goal goal1, Goal goal2) {
        return new OrGoal(goal1, goal2);
    }

    public static Goal createBouldersGoal() {
        return new BouldersGoal();
    }

    public static Goal createTreasureGoal(int target) {
        return new TreasureGoal(target);
    }

    public static Goal createExitGoal() {
        return new ExitGoal();
    }

    public static Goal createEnemiesGoal(int target) {
        return new EnemiesGoal(target);
    }

}
