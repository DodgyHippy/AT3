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
    private Location position;

    public Monster(String name, Location position) {
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

    public Location getPosition() {
        return position;
    }

    public void setPosition(Location position) {
        this.position = position;
    }
    /**
     * Reduces the monster's health after taking damage.
     * The method subtracts health and prevents it from dropping below zero.
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
     * The method resets the monster to its default health value.
     */
    public void resetHealth() {
        health = 80;
    }

    public boolean isAlive() {
        return health > 0;
    }
    /**
     * Moves the monster to a random valid nearby location.
     * The method chooses a random direction, checks map boundaries and home base,
     * then updates the monster's position.
     *
     * @param gameMap the list of all map locations
     */
    public void moveRandomly(ArrayList<Location> gameMap) {
        Random random = new Random();
        int[] possibleMoves = {-4, 4, -1, 1};

        int newPosition = position.getLocationInt();
        boolean validMove = false;

        while (!validMove) {
            int movement = possibleMoves[random.nextInt(possibleMoves.length)];
            newPosition = position.getLocationInt() + movement;

            if (newPosition < 0 || newPosition >= gameMap.size()) {
                continue;
            }

            if (movement == -1 && position.getLocationInt() % 4 == 0) {
                continue;
            }

            if (movement == 1 && position.getLocationInt() % 4 == 3) {
                continue;
            }

            if (gameMap.get(newPosition).getHomeBase()) {
                continue;
            }

            validMove = true;
        }

        position.setMonsterPos(false);
        position = gameMap.get(newPosition);
        position.setMonsterPos(true);
    }
}