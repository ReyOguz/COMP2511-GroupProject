package dungeonmania.entities.inventory.BuildStrategies;

import java.util.List;

import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.collectables.Arrow;
import dungeonmania.entities.collectables.Key;
import dungeonmania.entities.collectables.SunStone;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.entities.collectables.Wood;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

public class BuildSceptre implements BuildStrategy {
    public InventoryItem build(Inventory inventory, boolean remove, EntityFactory factory) {
        if (remove)
            removeSceptreItems(inventory);
        return factory.buildSceptre();
    }

    public void removeSceptreItems(Inventory inventory) {

        List<Wood> wood = inventory.getEntities(Wood.class);
        List<Arrow> arrows = inventory.getEntities(Arrow.class);
        List<Treasure> treasure = inventory.getEntities(Treasure.class);
        List<Key> keys = inventory.getEntities(Key.class);
        List<SunStone> stones = inventory.getEntities(SunStone.class);

        // 1 wood or 2 arrows
        if (wood.size() > 0) {
            inventory.remove(wood.get(0));
        } else {
            inventory.remove(arrows.get(0));
            inventory.remove(arrows.get(1));
        }

        // 1 key or 1 treasure OR 1 stone that is not removed
        if (keys.size() > 0) {
            inventory.remove(keys.get(0));
        } else if (treasure.size() >= 1) {
            inventory.remove(treasure.get(0));
        }

        // Remove a stone that is a named ingredient
        inventory.remove(stones.get(0));
    }
}
