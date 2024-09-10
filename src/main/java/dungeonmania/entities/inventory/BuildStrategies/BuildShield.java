package dungeonmania.entities.inventory.BuildStrategies;

import java.util.List;

import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.collectables.Key;
import dungeonmania.entities.collectables.SunStone;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.entities.collectables.Wood;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

public class BuildShield implements BuildStrategy {
    public InventoryItem build(Inventory inventory, boolean remove, EntityFactory factory) {
        if (remove)
            removeShieldItems(inventory);
        return factory.buildShield();
    }

    public void removeShieldItems(Inventory inventory) {

        List<Wood> wood = inventory.getEntities(Wood.class);
        List<Treasure> treasure = inventory.getEntities(Treasure.class);
        List<Key> keys = inventory.getEntities(Key.class);
        List<SunStone> stones = inventory.getEntities(SunStone.class);

        inventory.remove(wood.get(0));
        inventory.remove(wood.get(1));
        if (stones.size() >= 1) {
            return;
        }

        if (treasure.size() >= 1) {
            inventory.remove(treasure.get(0));
        } else {
            inventory.remove(keys.get(0));
        }
    }
}
