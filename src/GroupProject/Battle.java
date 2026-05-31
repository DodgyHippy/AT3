package GroupProject;

import java.util.Random;
import java.util.Scanner;

/*
Author: Brandon

This class starts and controls the battle loop.
It uses the player and monster objects, lets the player attack,
calculates monster attacks, and updates the real health values.

*/

public class Battle {
    public static void startBattle(Player player, Monster monster) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("A mass of tentacles lashes from the depths!");

        while (player.isAlive() && monster.isAlive()) {
            System.out.println("\nPlayer health: " + player.getHealth());
            System.out.println("Monster health: " + monster.getHealth());

            System.out.print("Press Enter to attack! ");
            input.nextLine();

            int playerDamage = calculatePlayerDamage(player, rand);
            monster.takeDamage(playerDamage);

            System.out.println("You dealt " + playerDamage + " damage.");

            if (!monster.isAlive()) {
                break;
            }

            int monsterDamage = calculateMonsterDamage(monster, rand);
            player.takeDamage(monsterDamage);

            System.out.println("The monster dealt " + monsterDamage + " damage.");
        }

        if (player.isAlive()) {
            System.out.println("\nThe horror has been stunned! Run now!");
        } else {
            System.out.println("\nYour journey ends here...");
        }
        monster.resetHealth();
    }

    public static int calculatePlayerDamage(Player player, Random rand) {
        int baseDamage = player.getDamage();
        int bonusDamage = rand.nextInt(6);
        return baseDamage + bonusDamage;
    }

    public static int calculateMonsterDamage(Monster monster, Random rand) {
        int baseDamage = monster.getDamage();
        int bonusDamage = rand.nextInt(6);
        return baseDamage + bonusDamage;
    }
}