package dungeonmania.entities.inventory.BuildStrategies;

import java.util.List;

import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.collectables.SunStone;
import dungeonmania.entities.collectables.Sword;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

public class BuildMidnightArmour implements BuildStrategy {
    public InventoryItem build(Inventory inventory, boolean remove, EntityFactory factory) {
        if (remove)
            removeBowItems(inventory);
        return factory.buildMidnightArmour();
    }

    public void removeBowItems(Inventory inventory) {
        List<SunStone> stones = inventory.getEntities(SunStone.class);
        List<Sword> swords = inventory.getEntities(Sword.class);

        inventory.remove(stones.get(0));
        inventory.remove(swords.get(0));
    }
}
