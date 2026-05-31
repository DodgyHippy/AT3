package GroupProject;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Location> gameMap = Init.mapGen();

        Player player = Init.createPlayer(gameMap);
        Monster monster = Init.createMonster(gameMap);

        int totalSurveyDataNeeded = 2;
        boolean gameRunning = true;

        UI.introSequence();

        while (gameRunning && player.isAlive()) {

            UI.commandOptions(player, gameMap);

            Location currentTile = gameMap.get(player.getPosition());

            int surveyDataCollected = countSurveyedSites(gameMap);

            if (surveyDataCollected == totalSurveyDataNeeded && currentTile.getHomeBase()) {
                System.out.println("You returned to home base with all survey data. Mission complete!");
                gameRunning = false;
            }

            if (gameRunning && player.isAlive()) {
                monster.moveRandomly(gameMap);

                if (monster.getPosition() == player.getPosition()) {
                    System.out.println("The monster has found you!");
                    Battle.startBattle(player, monster);
                }
            }
        }

        if (!player.isAlive()) {
            System.out.println("You have died. Game over.");
        }
    }

    public static int countSurveyedSites(ArrayList<Location> gameMap) {
        int count = 0;

        for (Location location : gameMap) {
            if (location.getSurveySite() && location.isSiteSurveyed()) {
                count++;
            }
        }

        return count;
    }
}