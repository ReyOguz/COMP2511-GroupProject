package dungeonmania.entities.inventory.BuildStrategies;

import java.util.List;

import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.collectables.Arrow;
import dungeonmania.entities.collectables.Wood;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

public class BuildBow implements BuildStrategy {
    public InventoryItem build(Inventory inventory, boolean remove, EntityFactory factory) {
        if (remove)
            removeBowItems(inventory);
        return factory.buildBow();
    }

    public void removeBowItems(Inventory inventory) {
        List<Wood> wood = inventory.getEntities(Wood.class);
        List<Arrow> arrows = inventory.getEntities(Arrow.class);

        inventory.remove(wood.get(0));
        inventory.remove(arrows.get(0));
        inventory.remove(arrows.get(1));
        inventory.remove(arrows.get(2));
    }
}
