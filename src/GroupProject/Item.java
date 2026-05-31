package GroupProject;

/*
Author: Sebastion

This class stores the data for an inventory item.
Each item has a name, item type, point value, health restore value,
and energy restore value.
*/

public class Item {
    private String name;
    private ItemType type;
    private int points;
    private int restoreHealth;
    private int restoreEnergy;

    public Item(String name, ItemType type, int points, int restoreHealth, int restoreEnergy) {
        this.name = name;
        this.type = type;
        this.points = points;
        this.restoreHealth = restoreHealth;
        this.restoreEnergy = restoreEnergy;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public int getPoints() {
        return points;
    }

    public int getRestoreHealth() {
        return restoreHealth;
    }

    public int getRestoreEnergy() {
        return restoreEnergy;
    }
}