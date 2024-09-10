package dungeonmania.evolution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class LogicalTest {
    @Test
    @Tag("16-1")
    @DisplayName("Test player battles spider, spider dies, goal is met")
    public void testOrDoor() {
        DungeonManiaController controller = new DungeonManiaController();
        // Create a spider to the right of the player, goal is one enemy destroyed
        DungeonResponse res = controller.newGame("d_logicalTest_Door", "c_newGoalTest_spiderDies");
        // Spider is still present, so goal remains
        Position init = TestUtils.getPlayerPos(res);
        res = controller.tick(Direction.UP);
        assertEquals(init, TestUtils.getPlayerPos(res));
        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.RIGHT);
        assertEquals(Position.translateBy(init, Direction.UP), TestUtils.getPlayerPos(res));
    }

    @Test
    @Tag("16-1")
    @DisplayName("Test player battles spider, spider dies, goal is met")
    public void testAndDoor() {
        DungeonManiaController controller = new DungeonManiaController();
        // Create a spider to the right of the player, goal is one enemy destroyed
        DungeonResponse res = controller.newGame("d_logicalTest_DoorAnd", "c_newGoalTest_spiderDies");
        // Spider is still present, so goal remains
        Position init = TestUtils.getPlayerPos(res);
        res = controller.tick(Direction.UP);
        assertEquals(init, TestUtils.getPlayerPos(res));
        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.DOWN);
        assertEquals(Position.translateBy(init, Direction.UP), TestUtils.getPlayerPos(res));
    }

    @Test
    @Tag("16-1")
    @DisplayName("Test player battles spider, spider dies, goal is met")
    public void testCoAndDoor() {
        DungeonManiaController controller = new DungeonManiaController();
        // Create a spider to the right of the player, goal is one enemy destroyed
        DungeonResponse res = controller.newGame("d_logicalTest_DoorCoAnd", "c_newGoalTest_spiderDies");
        // Spider is still present, so goal remains
        Position init = TestUtils.getPlayerPos(res);
        res = controller.tick(Direction.UP);
        assertEquals(init, TestUtils.getPlayerPos(res));
        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.RIGHT);
        assertEquals(Position.translateBy(init, Direction.UP), TestUtils.getPlayerPos(res));
    }

    @Test
    @Tag("16-1")
    @DisplayName("Test player battles spider, spider dies, goal is met")
    public void testXOrDoor() {
        DungeonManiaController controller = new DungeonManiaController();
        // Create a spider to the right of the player, goal is one enemy destroyed
        DungeonResponse res = controller.newGame("d_logicalTest_DoorXOr", "c_newGoalTest_spiderDies");
        // Spider is still present, so goal remains
        Position init = TestUtils.getPlayerPos(res);
        res = controller.tick(Direction.UP);
        assertEquals(init, TestUtils.getPlayerPos(res));
        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.RIGHT);
        assertEquals(Position.translateBy(init, Direction.UP), TestUtils.getPlayerPos(res));
    }
}
