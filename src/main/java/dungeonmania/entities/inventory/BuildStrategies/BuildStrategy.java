package dungeonmania.entities.inventory.BuildStrategies;

import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

public interface BuildStrategy {
    public InventoryItem build(Inventory inventory, boolean remove, EntityFactory factory);
}
