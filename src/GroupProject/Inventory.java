package GroupProject;

import java.util.ArrayList;
import java.util.List;

/*
Author: Sebastion

This class stores and controls the player's inventory.
It limits the player to five inventory slots, allows items to be added,
removed, and displayed, and prevents the player from collecting items
when the inventory is full.
*/

public class Inventory {
    private final int MAX_SLOTS = 5;
    private final List<Item> items = new ArrayList<>();
    /*Bug: While navigating the player had no limit to what they could pick up or carry. Unnecessary_Scrap became useless mechanically.
      Fix: After imposing a maximum of five inventory slots, the player could no longer pick up every item on the map*/

    public boolean isFull() {
        return items.size() >= MAX_SLOTS;
    }

    public boolean addItem(Item item) {
        if (isFull()) {
            System.out.println("Your manifest is full, you cannot collect " + item.getName() + ".");
            return false;
        }

        items.add(item);
        System.out.println(item.getName() + " was collected.");
        return true;
    }

    public void openInventory() {
        System.out.println("=== Submariner Storage Vat ===");

        if (items.isEmpty()) {
            System.out.println("Your manifest is empty.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ": " + items.get(i).getName());
            }
        }

        System.out.println("===============");
    }

    public boolean removeItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                items.remove(item);
                return true;
            }
        }

        return false;
    }

    public List<Item> getItems() {
        return items;
    }
}
