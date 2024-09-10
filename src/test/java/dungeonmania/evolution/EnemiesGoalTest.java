package dungeonmania.evolution;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;

public class EnemiesGoalTest {
    @Test
    @Tag("16-1")
    @DisplayName("Test player battles spider, spider dies, goal is met")
    public void testSingleSpiderGoal() {
        DungeonManiaController controller = new DungeonManiaController();
        // Create a spider to the right of the player, goal is one enemy destroyed
        DungeonResponse res = controller.newGame("d_newGoalTest_spiderDies", "c_newGoalTest_spiderDies");
        // Spider is still present, so goal remains
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));
        res = controller.tick(Direction.RIGHT);
        List<EntityResponse> entities = res.getEntities();
        // Spider is dead so goal is met
        assertTrue(TestUtils.countEntityOfType(entities, "spider") == 0);
        assertTrue(!TestUtils.getGoals(res).contains(":enemies"));
    }

    @Test
    @Tag("16-2")
    @DisplayName("Test player battles spider, spider dies, goal is not met")
    public void testTwoSpiderGoal() {
        DungeonManiaController controller = new DungeonManiaController();
        // Create a spider to the right of the player, goal is two enemies destroyed
        DungeonResponse res = controller.newGame("d_newGoalTest_spiderDies", "c_newGoalTest_spiderDies_GoalNotMet");
        // Spider is still present, so goal remains
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));
        res = controller.tick(Direction.RIGHT);
        List<EntityResponse> entities = res.getEntities();
        // Spider is dead so but goal not met
        assertTrue(TestUtils.countEntityOfType(entities, "spider") == 0);
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));
    }

    @Test
    @Tag("16-2")
    @DisplayName("Test player battles spider, spider dies, goal is not met")
    public void testSpawnerGoal() {

        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_newGoalTest_oneSpawner", "c_newGoalTest_oneSpawner");
        assertEquals(1, TestUtils.getEntities(res, "zombie_toast_spawner").size());
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));
        List<EntityResponse> entities = res.getEntities();
        assertTrue(TestUtils.countEntityOfType(entities, "spider") == 1);
        String spawnerId = TestUtils.getEntities(res, "zombie_toast_spawner").get(0).getId();

        // pick up sword
        res = dmc.tick(Direction.DOWN);
        // move right
        res = dmc.tick(Direction.RIGHT);
        // Destroy spawner
        res = assertDoesNotThrow(() -> dmc.interact(spawnerId));
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));
        // Check destruction and goal still remains
        assertEquals(0, TestUtils.countType(res, "zombie_toast_spawner"));
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        entities = res.getEntities();
        assertTrue(TestUtils.countEntityOfType(entities, "spider") == 0);
        assertTrue(!TestUtils.getGoals(res).contains(":enemies"));
    }
}
