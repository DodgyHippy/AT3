/*
Author: Oliver

This handles game initialization
*/

package GroupProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class Init {


    // This is the map generation method. Should only be called once in Main
    public static ArrayList<Location> mapGen() {
        Random random = new Random();
        ArrayList<Location> gameMap = new ArrayList<>();

        // Constants
        int MAP_SIZE = 16;
        int [] VALID_BASE_POS = {0,3,12,15}; // Base positions only valid on map corners
        int [] VALID_SURVEY_POS = {1,2,4,5,6,7,8,9,10,11,13,14}; // Base positions only valid on map corners

        // Vars
        boolean homeBaseAdded = false;
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
        // Set a random location for the wreckage
        int wreckageLocation = nonBaseLocations.get(random.nextInt(nonBaseLocations.size()));
        //System.out.println(wreckageLocation);
        nonBaseLocations.remove((Integer) wreckageLocation);

        // -------------------------------------- Assigning locations to map array list -------------------------------------- //

        for (int _i = 0; _i < MAP_SIZE; _i++) {
            Location currentLocation;

            // Check if the current tile is a survey site
            if (Arrays.binarySearch(surveySites,_i) >= 0) {
                currentLocation = new Location(false,true,false,false);
                System.out.printf("(DEBUG: Survey Site: %d)\n",_i);
            } // Check if the current tile is the home base
            else if (_i == homeBaseLocation) {
                currentLocation = new Location(true,false,false,false);
                currentLocation.setPlayerPos(true);
                System.out.printf("(DEBUG: Home Base: %d)\n",_i);
            } // Check if the current tile has the weapon
            else if (_i == weaponLocation) { // Check if the current tile is the home base
                currentLocation = new Location(false,false,true,false);
                System.out.printf("(DEBUG: Weapon Location: %d)\n",_i);
            } // Check if the current tile has the wreckage
            else if (_i == wreckageLocation) {
                currentLocation = new Location(false,false,false,true);
                System.out.printf("(DEBUG: Wreckage Location: %d)\n",_i);
            } // If none of the above is true, add an empty map location
            else {
                currentLocation = new Location(false,false,false,false);
            }
            gameMap.add(currentLocation);
        }
        return gameMap;
    }
}
