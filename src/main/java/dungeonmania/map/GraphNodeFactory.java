package dungeonmania.map;

import org.json.JSONObject;

import dungeonmania.entities.Entity;
import dungeonmania.entities.EntityFactory;

public class GraphNodeFactory {
    public static GraphNode createEntity(JSONObject jsonEntity, EntityFactory factory) {
        return constructEntity(jsonEntity, factory);
    }

    private static GraphNode constructEntity(JSONObject jsonEntity, EntityFactory factory) {
        switch (jsonEntity.getString("type")) {
        case "player":
        case "zombie_toast":
        case "zombie_toast_spawner":
        case "mercenary":
        case "wall":
        case "boulder":
        case "switch":
        case "exit":
        case "treasure":
        case "wood":
        case "arrow":
        case "bomb":
        case "invisibility_potion":
        case "invincibility_potion":
        case "portal":
        case "sword":
        case "spider":
        case "door":
        case "key":
        case "swamp_tile":
        case "sun_stone":
        case "midnight_armour":
        case "light_bulb_off":
        case "switch_door":
        case "wire":
            Entity entity = factory.createEntity(jsonEntity);
            return new GraphNode(entity, entity.getWeighting());
        default:
            return null;
        }
    }
}
