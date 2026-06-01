package GroupProject;

/*
Author: Brandon

This class stores and controls the player object.
It keeps track of the player's name, health, damage, and current map position.
It also contains methods for taking damage, healing, changing position,
and checking whether the player is still alive.

*/

public class Player {
    private String name;
    private int health;
    private int maxHealth;
    private int damage;
    private Location position;
    private Inventory inventory;

    public Player(String name, Location position) {
        this.name = name;
        this.maxHealth = 100;
        this.health = maxHealth;
        this.damage = 10;
        this.position = position;
        this.inventory = new Inventory();
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;

    }

    public int getDamage() {
        if (hasItem("Harpoon Module")) {
            return damage + 10;
        }

        return damage;
    }

    public Location getPosition() {
        return position;
    }

    public void setPosition(Location position) {
        this.position = position;
    }

    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Reduces the player's health after taking damage.
     *
     * @param damageAmount the amount of health removed
     */
    public void takeDamage(int damageAmount) {
        health -= damageAmount;

        if (health < 0) {
            health = 0;
        }
    }
    /**
     * Uses a healing item from the player's inventory.
     * Requires the item to exist, be secondary, and have a health restore value.
     *
     * @param itemName the name of the item to use
     * @return boolean showing whether the item was successfully used
     */
    public boolean useItem(String itemName) {
        for (Item item : inventory.getItems()) {
            if (item.getName().equalsIgnoreCase(itemName) && item.getType() == ItemType.SECONDARY && item.getRestoreHealth() > 0) {
                heal(item.getRestoreHealth());
                inventory.removeItem(item.getName());
                System.out.println(item.getName() + " was used.");
                return true;
            }
        }

        System.out.println("You cannot use that item.");
        return false;
    }
    /**
     * Collects an item from a location and adds it to inventory.
     *
     * @param location the location being looted
     * @return boolean showing whether the item was collected
     */
    public boolean collectItem(Location location) {
        if (location.getItem() == null) {
            System.out.println("There is no item to collect here.");
            return false;
        }

        Item item = location.getItem();

        if (inventory.addItem(item)) {
            location.removeItem();
            return true;
        }

        return false;
    }
    /**
     * Checks whether the player has a specific item.
     *
     * @param itemName the name of the item being searched for
     * @return boolean showing whether the item is in the inventory
     */
    public boolean hasItem(String itemName) {
        for (Item item : inventory.getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }

        return false;
    }
    /**
     * The method increases health and prevents it from going above maximum health.
     *
     * @param healAmount the amount of health restored
     */
    public void heal(int healAmount) {
        health += healAmount;

        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }
}