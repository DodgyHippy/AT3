package GroupProject;

/*
Author: Sebastion

This class stores the data for an inventory item.
Each item has a name, item type, point value and health restore value.

*/

public class Item {
    private String name;
    private ItemType type;
    private int restoreHealth;

    public Item(String name, ItemType type, int restoreHealth) {
        this.name = name;
        this.type = type;
        this.restoreHealth = restoreHealth;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public int getRestoreHealth() {
        return restoreHealth;
    }
}