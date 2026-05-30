package GroupProject;

import java.util.Random;
import java.util.Scanner;

/*
Author: Brandon

This class starts and controls the battle loop.
It creates the player and enemy health values, lets the player attack,
calculates enemy attacks, and prints the final battle result.
*/

public class Battle {
    public static void main() {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        // declare participant health values

        int playerHealth = 100;
        int enemyHealth = 80;

        System.out.println("A mass of tentacles lashes from the depths!");

        // if both participants are above 0 health, initiate a round of battle

        while (playerHealth > 0 && enemyHealth > 0) {
            System.out.println("\nPlayer health: " + playerHealth);
            System.out.println("Enemy health: " + enemyHealth);

            System.out.print("Press Enter to attack! ");
            input.nextLine();

            // enemy health reduced by the player damage

            int playerDamage = calculatePlayerDamage(rand);
            enemyHealth -= playerDamage;

            System.out.println("You dealt " + playerDamage + " damage.");

            // enemy doesn't attack if player reduces their health to 0

            if (enemyHealth <= 0) {
                break;
            }

            // player health reduced by the enemy damage

            int enemyDamage = calculateEnemyDamage(rand);
            playerHealth -= enemyDamage;

            System.out.println("The enemy dealt " + enemyDamage + " damage.");
        }

            // win/lose condition

        if (playerHealth > 0) {
            System.out.println("\nThe horror has been stunned! Run now!");
        } else {
            System.out.println("\nYour journey ends here...");
        }
    }

    public static int calculatePlayerDamage(Random rand) {

        // Rolls damage ranging from 10-20 for the player

        int baseDamage = 10;
        int bonusDamage = rand.nextInt(11);
        return baseDamage + bonusDamage;
    }

    public static int calculateEnemyDamage(Random rand) {

        // Rolls damage ranging from 8-15 for the enemy

        int baseDamage = 8;
        int bonusDamage = rand.nextInt(8);
        return baseDamage + bonusDamage;
    }
}