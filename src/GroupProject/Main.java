package GroupProject;

import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {

        ArrayList<Location> gameMap;

        gameMap = Init.mapGen();

        Location currentTile = gameMap.get(3); // valid locations = 0 to 15

        //currentTile.getDeposit();
        //System.out.println(currentTile.getHomeBase());
        //Battle.main();
    }
}
