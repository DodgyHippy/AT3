/*
Author: Oliver

//This handles the player UI and command interface.
*/

package GroupProject;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    private static int [][] ADJACENCY_TABLE = {
            {1,4,5},                        // 0
            {0,2,4,5,6},                    // 1
            {1,3,5,6,7},                    // 2
            {2,6,7},                        // 3
            {0,1,5,8,9},                    // 4
            {0,1,2,4,6,8,9,10},             // 5
            {1,2,3,5,7,9,10,11},            // 6
            {2,3,6,10,11},                  // 7
            {4,5,9,12,13},                  // 8
            {4,5,6,8,10,12,13,14},          // 9
            {5,6,7,9,11,13,14,15},          // 10
            {6,7,10,14,15},                 // 11
            {8,9,13},                       // 12
            {8,9,10,12,14},                 // 13
            {9,10,11,13,15},                // 14
            {10,11,14}                      // 15
    };

    public static void introSequence() {
        System.out.println("\n...boot complete!\nEPMSS-12 Unmanned Submersible\nThelos Dynamics (c) 2057 - 2084\n");
        System.out.println("\n------ VALUED EMPLOYEE ------");
        System.out.println("We wish you a warm welcome to the MINERACOR family and congratualte your selection for\nthe exiciting new career oppourtunites at -DEEP SEA SUVERY BASE 82-B2-811 ALPHA- !\n");
        System.out.println("TASKING: SURVEY GRID 4-5B1 FOR SEA FLOOR MINERAL DEPOSITS. PROJECTED # OF SITES IN SURVEY GRID: 2\n");
    }

    public static void commandOptions(Player playerObject, ArrayList<Location> gameMap) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter 'help' for a list of commands.\nC:\\> ");

        String inArg = scanner.nextLine();
        inArg = inArg.toLowerCase();
        switch (inArg) {
            case "pos": {
                //System.out.println("DEBUG: pos command!");
                UI.posPlayer(playerObject);
                break;
            }
            case "mov": {
                //System.out.println("DEBUG: move command!");
                UI.movePlayer(playerObject, gameMap);
                break;
            }
            case "inv": {
                //System.out.println("DEBUG: inv command!");
                UI.invPlayer(playerObject);
                break;
            }
            case "scn": {
                //System.out.println("DEBUG: scan command!");
                UI.scanPlayer(playerObject, gameMap);
                break;
            }
            case "int": {
                //System.out.println("DEBUG: interact command!");
                UI.interactPlayer(playerObject, gameMap);
                break;
            }
            case "help": {
                System.out.print("pos: Current submersible position.\nmov: Move the submersible.\ninv: Display submersible cargo manifest.\nscn: Perform geologic survey survey.\nint: Interact with items.\n");
                //System.out.println("DEBUG: help command!");
                break;
            }
            default:
                System.out.print("Error! Please enter a valid command.\n");
        }
    }

    public static void posPlayer(Player playerObject) {
        int currentPlayerPos = playerObject.getPosition();
        System.out.printf("Current positon: %d\n",currentPlayerPos);
    }

    public static void movePlayer(Player playerObject, ArrayList<Location> gameMap) {
        Scanner scanner = new Scanner(System.in);
        int currentPlayerPos = playerObject.getPosition();

        int [] currentAllowedMovement = ADJACENCY_TABLE[currentPlayerPos];

        System.out.print("Valid grids: ");
        for (int _i : currentAllowedMovement) {
            System.out.printf("%d. ",_i);
        }
        System.out.print("enter 'cnl' to cancel this command.\n");

        System.out.print("C:\\>move to grid> ");
        String inArg = scanner.nextLine();
        inArg = inArg.toLowerCase();

        boolean isMatch = false;
        for (int _i: currentAllowedMovement) {
            if (String.valueOf(_i).equals(inArg)) {
                isMatch = true;
                break;
            }
        }

        if (isMatch) {
            playerObject.setPosition(Integer.parseInt(inArg));
            Location currentLocation = gameMap.get(playerObject.getPosition());

            System.out.println("Moved to grid " + playerObject.getPosition() + ".");
            System.out.println("Location: " + currentLocation.getName());
            System.out.println(currentLocation.getDescription());

            if (currentLocation.getItem() != null) {
                System.out.println("Item detected: " + currentLocation.getItem().getName());
                System.out.println("Use 'int' to interact with it.");
            }

        } else if (inArg.equals("cnl")) {
            return;
        } else {
            System.out.println("Error! Please enter a valid command.\n");
            UI.movePlayer(playerObject, gameMap);
        }
    }

    public static void invPlayer(Player playerObject) {
        Scanner scanner = new Scanner(System.in);

        playerObject.getInventory().openInventory();

        System.out.print("Type 'use' to use an item or 'cnl' to cancel.\nC:\\>inventory> ");
        String input = scanner.nextLine().toLowerCase();

        if (input.equals("use")) {
            System.out.print("Enter item name: ");
            String itemName = scanner.nextLine();
            playerObject.useItem(itemName);
        } else if (!input.equals("cnl")) {
            System.out.println("Error! Please enter a valid command.");
        }

    }

    public static void interactPlayer(Player playerObject, ArrayList<Location> gameMap) {
        Location currentLocation = gameMap.get(playerObject.getPosition());

        if (currentLocation.getItem() != null) {
            Item item = currentLocation.getItem();

            if (playerObject.getInventory().addItem(item)) {
                currentLocation.removeItem();
            }

            return;
        }

        if (currentLocation.getSurveySite() && !currentLocation.isSiteSurveyed()) {
            System.out.println("Survey site detected. Use 'scn' to scan this location.");
            return;
        }

        System.out.println("There is nothing useful to interact with here.");
    }

    public static void scanPlayer(Player playerObject, ArrayList<Location> gameMap) {
        int currentPlayerPos = playerObject.getPosition();
        boolean isSurveySite = gameMap.get(currentPlayerPos).getSurveySite();

        if (isSurveySite) {
            System.out.println("MINERALS DETECTED! SURVEY DATA LOGGED IN MANIFEST.");
            gameMap.get(currentPlayerPos).setSiteSurveyed(true);
        } else {
            System.out.println("NO MINERALS DETECTED.");
        }
    }
}
