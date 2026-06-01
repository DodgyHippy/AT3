/*
Author: Oliver

This handles game initialization
*/

package GroupProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class Init {
    /**
     * Creates the 4x4 game map and randomly places key locations.
     *
     * @return ArrayList<Location> containing all generated map locations
     */
    public static ArrayList<Location> mapGen() {
        Random random = new Random();
        ArrayList<Location> gameMap = new ArrayList<>();

        // Constants
        int MAP_SIZE = 16;
        int [] VALID_BASE_POS = {0,3,12,15}; // Base positions only valid on map corners
        int [] VALID_SURVEY_POS = {1,2,4,5,6,7,8,9,10,11,13,14}; // Base positions only valid on map corners

        // Vars
        ArrayList<Integer> nonBaseLocations = new ArrayList<>();
        for (int _i : VALID_SURVEY_POS) {
            nonBaseLocations.add(_i);
        }

        // -------------------------------------- Random Gen -------------------------------------- //

        int homeBaseLocation = VALID_BASE_POS[random.nextInt(VALID_BASE_POS.length)];

        // This sets a randomized position for the survey sites. While loop is to ensure there's no double ups
        int [] surveySites = {0,0};
        while (surveySites[0] == surveySites[1]) {
            surveySites[0] = VALID_SURVEY_POS[random.nextInt(VALID_SURVEY_POS.length)];
            surveySites[1] = VALID_SURVEY_POS[random.nextInt(VALID_SURVEY_POS.length)];
        }
        Arrays.sort(surveySites);

        // Remove locations already assigned as survey sites from the array list.
        nonBaseLocations.remove(Arrays.binarySearch(VALID_SURVEY_POS,surveySites[0]));
        nonBaseLocations.remove(Arrays.binarySearch(VALID_SURVEY_POS,surveySites[1]) - 1);

        // Set a random location for the weapon
        int weaponLocation = nonBaseLocations.get(random.nextInt(nonBaseLocations.size()));
        nonBaseLocations.remove((Integer) weaponLocation);
        // Set random locations for four wreckage sites
        int wreckageLocation = nonBaseLocations.get(random.nextInt(nonBaseLocations.size()));
        nonBaseLocations.remove((Integer) wreckageLocation);
        int wreckageLocationTwo = nonBaseLocations.get(random.nextInt(nonBaseLocations.size()));
        nonBaseLocations.remove((Integer) wreckageLocationTwo);
        int wreckageLocationThree = nonBaseLocations.get(random.nextInt(nonBaseLocations.size()));
        nonBaseLocations.remove((Integer) wreckageLocationThree);
        int wreckageLocationFour = nonBaseLocations.get(random.nextInt(nonBaseLocations.size()));
        nonBaseLocations.remove((Integer) wreckageLocationFour);

        // -------------------------------------- Assigning locations to map array list -------------------------------------- //

        for (int _i = 0; _i < MAP_SIZE; _i++) {
            Location currentLocation;

            // Check if the current tile is a survey site
            if (_i == surveySites[0]) {
                currentLocation = new Location(false, true, false, false, "Survey Site", "Mineral traces appear in the sonar data. Use the scan command here.", new Item("Palladium Survey Core", ItemType.PRIMARY, 0), _i);
            } else if (_i == surveySites[1]) {
                currentLocation = new Location(false, true, false, false, "Survey Site", "Mineral traces appear in the sonar data. Use the scan command here.", new Item("Iridium Survey Core", ItemType.PRIMARY, 0),_i);
            } // Check if the current tile is the home base
            else if (_i == homeBaseLocation) {
                currentLocation = new Location(true,false,false,false,"Home Base", "The deep sea survey base. Return here after scanning both survey sites.", null,_i);
            } // Check if the current tile has the weapon
            else if (_i == weaponLocation) {
                currentLocation = new Location(false,false,true,false,"Weapon Cache", "A sealed emergency weapon container rests on the sea floor.", new Item("Harpoon Module",ItemType.SECONDARY,0),_i);
            } // Check if the current tile has a wreckage
            else if (_i == wreckageLocation) {
                currentLocation = new Location(false, false, false, true, "Wreckage", "A broken wreckage lies scattered across the grid.", new Item("Repair Kit", ItemType.SECONDARY, 25),_i);
            } else if (_i == wreckageLocationTwo) {
                currentLocation = new Location(false, false, false, true, "Wreckage", "A crushed section of wreckage is half buried in the silt.", new Item("Repair Kit", ItemType.SECONDARY, 25),_i);
            } else if (_i == wreckageLocationThree) {
                currentLocation = new Location(false, false, false, true, "Wreckage", "A hollow wreckage shell rests silently on the ocean floor.", null,_i);
            } else if (_i == wreckageLocationFour) {
                currentLocation = new Location(false, false, false, true, "Wreckage", "Twisted metal from an old survey vessel is scattered across the seabed.", null,_i);
            } // If none of the above is true, add an empty map location
            else {
                currentLocation = new Location(false,false,false,false,"Open Sea Floor", "A quiet section of ocean floor.", null,_i);
            }
            gameMap.add(currentLocation);
        }
        return gameMap;
    }

    /**
     * Creates the player at the home base location.
     *
     * @param gameMap the list of all map locations
     * @return Player object starting at home base
     */
    public static Player createPlayer(ArrayList<Location> gameMap) {
        int homeBasePosition = 0;

        for (int i = 0; i < gameMap.size(); i++) {
            if (gameMap.get(i).getHomeBase()) {
                homeBasePosition = i;
                break;
            }
        }

        //gameMap.get(homeBasePosition).setPlayerPos(true);
        return new Player("Player", gameMap.get(homeBasePosition));
    }

    /**
     * Creates the monster at a random non-home-base location.
     *
     * @param gameMap the list of all map locations
     * @return Monster object starting away from home base
     */
    public static Monster createMonster(ArrayList<Location> gameMap) {
        Random random = new Random();
        int monsterPosition = random.nextInt(gameMap.size());

        while (gameMap.get(monsterPosition).getHomeBase()) {
            monsterPosition = random.nextInt(gameMap.size());
        }

        gameMap.get(monsterPosition).setMonsterPos(true);
        return new Monster("Monster", gameMap.get(monsterPosition));
    }
}
