package dungeonmania.evolution;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SwampTileTest {
    @Test
    @Tag("17-1")
    @DisplayName("Test Player is not stuck")
    public void testPlayerOnSwampTile() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse initDungonRes = dmc.newGame("d_swampTileTest_player", "c_movementTest_testMovementDown");
        EntityResponse initPlayer = TestUtils.getPlayer(initDungonRes).get();

        // create the expected result
        EntityResponse expectedPlayer = new EntityResponse(initPlayer.getId(), initPlayer.getType(), new Position(3, 1),
                false);

        // move player downward
        DungeonResponse actualDungonRes = dmc.tick(Direction.RIGHT);
        actualDungonRes = dmc.tick(Direction.RIGHT);
        EntityResponse actualPlayer = TestUtils.getPlayer(actualDungonRes).get();

        // assert after movement
        assertTrue(TestUtils.entityResponsesEqual(expectedPlayer, actualPlayer));
    }

    @Test
    @Tag("17-2")
    @DisplayName("Test Merc is stuck")
    public void testMercOnSwampTile() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_swampTileTest_merc", "c_movementTest_testMovementDown");

        //Moving 1 cell to the Right - Merc at 3,1 : which is on swamp tile.
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(3, 1), getMercPos(res));

        // Player moves again but Merc does not move
        res = dmc.tick(Direction.LEFT);
        assertEquals(new Position(3, 1), getMercPos(res));

        // Next tick and Merc is now free
        res = dmc.tick(Direction.LEFT);
        assertEquals(new Position(2, 1), getMercPos(res));
    }

    @Test
    @Tag("17-2")
    @DisplayName("Test Merc is stuck")
    public void testMercAvoidsTile() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_swampTileTest_mercAvoid", "c_movementTest_testMovementDown");

        //Moving 1 cell to the Right - Merc at 3,1 : which is on swamp tile.
        res = dmc.tick(Direction.RIGHT);
        assertNotEquals(new Position(3, 1), getMercPos(res));
        res = dmc.tick(Direction.RIGHT);
    }

    @Test
    @Tag("17-3")
    @DisplayName("Testing that an allied Mercenary adjacent to player does not get stuck on a swamp tile")
    public void testAlliedMercNotStuck() {
        //                                                          Wall     Wall     Wall    Wall    Wall
        // P1       P2/Treasure      P3/Treasure    P4/Treasure      M4       M3       M2     M1      Wall
        //                                                          Wall     Wall     Wall    Wall    Wall
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_mercenaryTest_bribeAmount", "c_mercenaryTest_bribeAmount");

        String mercId = TestUtils.getEntitiesStream(res, "mercenary").findFirst().get().getId();

        // Player pick up first treasure
        res = dmc.tick(Direction.RIGHT);

        // Player pick up second treasure
        res = dmc.tick(Direction.RIGHT);

        // Player pick up third treasure
        res = dmc.tick(Direction.RIGHT);

        // Player achieves bribe
        res = assertDoesNotThrow(() -> dmc.interact(mercId));

        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);

        // allied merc is adjacent to player and on swamp tile
        res = dmc.tick(Direction.DOWN);
        assertEquals(new Position(4, 4), getMercPos(res));

        // allied merc can move off swamp tile and is not stuck
        res = dmc.tick(Direction.DOWN);
        assertEquals(new Position(4, 5), getMercPos(res));
    }

    @Test
    @Tag("17-4")
    @DisplayName("Testing that an allied Mercenary not adjacent to player does get stuck on a swamp tile")
    public void testAlliedNonAdjacentMercStuck() {
        // need an merc to be bribed from far away and then have it stuck
        // same as previous but bribe amt needs to be 1 and bride radius needs to be big enough to reach the Merc
        // and also need a swamp tile between merc and player

        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_swampTileTest_alliedNonAdjacentMercTest",
                "c_swampTileTest_AlliedButNonAdjacentMerc");

        String mercId = TestUtils.getEntitiesStream(res, "mercenary").findFirst().get().getId();

        // Player pick up first treasure and bribe merc
        res = dmc.tick(Direction.RIGHT);
        // Bribe Merc
        res = assertDoesNotThrow(() -> dmc.interact(mercId)); // SECOND TICK

        // Merc on swamp tile
        res = dmc.tick(Direction.LEFT);
        assertEquals(new Position(5, 1), getMercPos(res));

        // Allied merc stuck once
        res = dmc.tick(Direction.LEFT);
        assertEquals(new Position(5, 1), getMercPos(res));

        // Allied Merc stuck twice
        res = dmc.tick(Direction.LEFT);
        assertEquals(new Position(5, 1), getMercPos(res));

        // Merc now released and not stuck anymroe
        res = dmc.tick(Direction.LEFT);
        assertEquals(new Position(4, 1), getMercPos(res));

    }

    @Test
    @Tag("17-5")
    @DisplayName("Testing that a spider does get stuck on a swamp tile")
    public void testSpiderOnSwampTile() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_swampTileTest_spider", "c_spiderTest_basicMovement");
        Position pos = TestUtils.getEntities(res, "spider").get(0).getPosition();

        List<Position> movementTrajectory = new ArrayList<>();
        int x = pos.getX();
        int y = pos.getY();
        movementTrajectory.add(new Position(x, y - 1));
        movementTrajectory.add(new Position(x + 1, y - 1));
        movementTrajectory.add(new Position(x + 1, y));
        movementTrajectory.add(new Position(x + 1, y + 1));
        movementTrajectory.add(new Position(x, y + 1));
        movementTrajectory.add(new Position(x - 1, y + 1));
        movementTrajectory.add(new Position(x - 1, y));
        movementTrajectory.add(new Position(x - 1, y - 1));

        // move spider to swamp tile
        for (int i = 0; i < 5; ++i) {
            res = dmc.tick(Direction.UP);
        }
        Position swampTilePos = new Position(5, 6);

        // Check that spider is on the swamp tile
        assertEquals(swampTilePos, getSpiderPos(res));

        // Try to move spider when stuck on swamp tile
        for (int i = 0; i < 4; ++i) {
            res = dmc.tick(Direction.DOWN);
            assertEquals(swampTilePos, getSpiderPos(res));
        }

        // Spider is now off swamp tile and unstuck
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 6), getSpiderPos(res));
    }

    @Test
    @Tag("17-7")
    @DisplayName("Testing that a zombie does get stuck on a swamp tile")
    public void testZombieOnSwampTile() {
        //      W
        //  W   S   SWMPT
        //      W
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_swampTileTest_zombie", "c_zombieTest_toastCantSpawn");

        assertEquals(1, TestUtils.getEntities(res, "zombie_toast_spawner").size());

        // tick to spawn zombie on  check that it spawns on swamp tile
        res = dmc.tick(Direction.UP);
        assertEquals(1, getZombies(res).size());
        Position swampTilePos = new Position(3, 2);
        assertEquals(swampTilePos, getZombiePos(res));

        // check that the zombie cant move off the swamp tile for required amount of ticks
        for (int i = 0; i < 3; i++) {
            res = dmc.tick(Direction.RIGHT);
            assertEquals(swampTilePos, getZombiePos(res));
        }

        // As per the spec where a random position choice can be to stay in same cell,
        // check that the zombie does eventually move off the swamp tile.
        boolean zombieMoved = false;
        for (int j = 0; j < 50; j++) {
            res = dmc.tick(Direction.UP);
            if (!swampTilePos.equals(getZombiePos(res))) {
                zombieMoved = true;
                break;
            }
        }
        assertTrue(zombieMoved);
    }

    private Position getSpiderPos(DungeonResponse res) {
        return TestUtils.getEntities(res, "spider").get(0).getPosition();
    }

    private Position getMercPos(DungeonResponse res) {
        return TestUtils.getEntities(res, "mercenary").get(0).getPosition();
    }

    private Position getZombiePos(DungeonResponse res) {
        return TestUtils.getEntities(res, "zombie_toast").get(0).getPosition();
    }

    private List<EntityResponse> getZombies(DungeonResponse res) {
        return TestUtils.getEntities(res, "zombie_toast");
    }
}
