package GroupProject;

import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {

        ArrayList<Location> gameMap;

        gameMap = Init.mapGen();

        Player player = Init.createPlayer(gameMap);
        Monster monster = Init.createMonster(gameMap);
        Location currentTile = gameMap.get(player.getPosition());

        monster.moveRandomly(gameMap);
        if (monster.getPosition() == player.getPosition()) {
            System.out.println("The monster has found you!");
            Battle.startBattle(player, monster);
        }

        //currentTile.getDeposit();
        //System.out.println(currentTile.getHomeBase());
        //Battle.main();
    }
}
