package dungeonmania.evolution;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

// import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
// import dungeonmania.response.models.BattleResponse;
// import dungeonmania.response.models.DungeonResponse;
// import dungeonmania.response.models.EntityResponse;
// import dungeonmania.response.models.RoundResponse;
import dungeonmania.util.Direction;

public class MidnightArmourTest {
    @Test
    @Tag("16-1")
    @DisplayName("Test building armour")
    public void testBuildingArmour() {
        DungeonManiaController controller = new DungeonManiaController();
        // Create a spider to the right of the player, goal is one enemy destroyed
        DungeonResponse res = controller.newGame("d_armourTest", "c_newGoalTest_spiderDies");
        // Spider is still present, so goal remains
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.RIGHT);
        res = assertDoesNotThrow(() -> controller.build("midnight_armour"));
        assertEquals(1, TestUtils.getInventory(res, "midnight_armour").size());
    }

    @Test
    @Tag("16-1")
    @DisplayName("Test building armour")
    public void testCannotBuildArmour() {
        DungeonManiaController controller = new DungeonManiaController();
        // Create a spider to the right of the player, goal is one enemy destroyed
        DungeonResponse res = controller.newGame("d_armourTest", "c_newGoalTest_spiderDies");
        // Spider is still present, so goal remains
        res = controller.tick(Direction.RIGHT);
        assertThrows(InvalidActionException.class, () -> controller.build("midnight_armour"));
        res = controller.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());
    }

    @Test
    @Tag("16-1")
    @DisplayName("Test building armour")
    public void testZombiesPresent() {
        DungeonManiaController controller = new DungeonManiaController();
        // Create a spider to the right of the player, goal is one enemy destroyed
        DungeonResponse res = controller.newGame("d_armourTest_zombies", "c_newGoalTest_spiderDies");
        // Spider is still present, so goal remains
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.RIGHT);
        assertThrows(InvalidActionException.class, () -> controller.build("midnight_armour"));
        res = controller.tick(Direction.RIGHT);
        System.out.println(TestUtils.getEntityPos(res, "zombie_toast"));
        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());
    }

    @Test
    @Tag("16-1")
    @DisplayName("Test building armour")
    public void testZombiePresentThenDies() {
        DungeonManiaController controller = new DungeonManiaController();
        // Create a spider to the right of the player, goal is one enemy destroyed
        DungeonResponse res = controller.newGame("d_zombieTest_doorsAndWalls", "c_newGoalTest_spiderDies");
        // Spider is still present, so goal remains
        assertThrows(InvalidActionException.class, () -> controller.build("midnight_armour"));
        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.DOWN);
        assertThrows(InvalidActionException.class, () -> controller.build("midnight_armour"));
        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.UP);
        res = assertDoesNotThrow(() -> controller.build("midnight_armour"));
        assertEquals(1, TestUtils.getInventory(res, "midnight_armour").size());
    }
}
