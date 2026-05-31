package GroupProject;

import java.util.ArrayList;
import java.util.Random;

/*
Author: Brandon

This class stores and controls the monster object.
It keeps track of the monster's name, health, damage, and current map position.
It also contains methods for taking damage, checking whether the monster is alive,
and randomly moving the monster around the game map.

*/

public class Monster {
    private String name;
    private int health;
    private int damage;
    private int position;

    public Monster(String name, int position) {
        this.name = name;
        this.health = 80;
        this.damage = 10;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void takeDamage(int damageAmount) {
        health -= damageAmount;

        if (health < 0) {
            health = 0;
        }
    }

    public void resetHealth() {
        health = 80;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void moveRandomly(ArrayList<Location> gameMap) {
        Random random = new Random();
        int[] possibleMoves = {-4, 4, -1, 1};

        int newPosition = position;
        boolean validMove = false;

        while (!validMove) {
            int movement = possibleMoves[random.nextInt(possibleMoves.length)];
            newPosition = position + movement;

            if (newPosition < 0 || newPosition >= gameMap.size()) {
                continue;
            }

            if (movement == -1 && position % 4 == 0) {
                continue;
            }

            if (movement == 1 && position % 4 == 3) {
                continue;
            }

            if (gameMap.get(newPosition).getHomeBase()) {
                continue;
            }

            validMove = true;
        }

        gameMap.get(position).setMonsterPos(false);
        position = newPosition;
        gameMap.get(position).setMonsterPos(true);
    }
}